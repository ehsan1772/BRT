package com.example.brtest.network;

import java.lang.reflect.Method;
import com.example.brtest.activities.MainActivity;
import com.example.brtest.views.BRTNetworkAlertDialogue;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;

/**
 * This class includes static methods that can be used to check and alter the
 * network status
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTNetworkManager {
	private static BroadcastReceiver receiver;
	public static Context context;
	private boolean waiting;

	/**
	 * This method determines if the network connectivity is available
	 * 
	 * @return true if the network is available
	 */
	public static boolean isConnected() {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	/**
	 * This methods enables mobile data and wifi and registers a broadcast
	 * receiver to monitor state changes. it also registers a timer to terminate
	 * the request if it is taking too long
	 * 
	 * @param context
	 *            The context of the activity that requesting the connection
	 * @param enabled
	 *            a boolean value that should be true to enable the network.
	 * @throws Exception
	 */
	public static void connect(Context context, boolean enabled)
			throws Exception {
		BRTNetworkManager.connectToWiFi();
		BRTNetworkManager.connectToMobileData(enabled);
		BRTNetworkManager current = new BRTNetworkManager();
		current.registerReciever(context);
		current.connectionTimer();
	}

	/**
	 * This private method enables mobile data
	 * @param enabled a boolean value that should be true to enable the network.
	 * @throws Exception
	 */
	private static void connectToMobileData(boolean enabled) throws Exception {
		final ConnectivityManager conman = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		@SuppressWarnings("rawtypes")
		final Class conmanClass = Class.forName(conman.getClass().getName());
		final java.lang.reflect.Field iConnectivityManagerField = conmanClass
				.getDeclaredField("mService");
		iConnectivityManagerField.setAccessible(true);
		final Object iConnectivityManager = iConnectivityManagerField
				.get(conman);
		@SuppressWarnings("rawtypes")
		final Class iConnectivityManagerClass = Class
				.forName(iConnectivityManager.getClass().getName());
		@SuppressWarnings("unchecked")
		final Method setMobileDataEnabledMethod = iConnectivityManagerClass
				.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
		setMobileDataEnabledMethod.setAccessible(true);
		setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
	}

	/**
	 * This private method enables wifi
	 */
	private static void connectToWiFi() {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		wifiManager.setWifiEnabled(true);
	}

	/**
	 * This method registers the broadcast reciever
	 * @param context
	 */
	private void registerReciever(Context context) {
		IntentFilter filter = new IntentFilter(
				android.net.ConnectivityManager.CONNECTIVITY_ACTION);
		filter.addAction(android.net.wifi.WifiManager.NETWORK_STATE_CHANGED_ACTION);
		receiver = new BRTNetworkReciever();
		context.registerReceiver(receiver, filter);
	}

	/**
	 * This method initiates a background thread to give the device 15 seconds to create the connection
	 */
	private void connectionTimer() {
		DelayHandler delayHandler = new DelayHandler();
		delayHandler.execute();
	}

	/**
	 * A private Broadcast receiver to monitor network connectivity
	 * If a connection is established, the application will restart and refresh the data
	 * @author Ehsan Barekati
	 *
	 */
	private class BRTNetworkReciever extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (BRTNetworkManager.isConnected()) {
				context.unregisterReceiver(receiver);
				((MainActivity) context).restartApplication();
			}
		}
	}

	/**
	 * A private Asynctask to give the device 15 seconds to establish the connection
	 * @author Ehsan Barekati
	 *
	 */
	private class DelayHandler extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPostExecute(Void result) {
			context.unregisterReceiver(receiver);
			if (!BRTNetworkManager.isConnected()) {
				BRTNetworkAlertDialogue dialogue = new BRTNetworkAlertDialogue(
						context);
				dialogue.show();
			}
			super.onPostExecute(result);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
