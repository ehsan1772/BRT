package com.example.brtest.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * This class includes static methods that can be used to check and alter the network status
 * @author Ehsan Barekati
 *
 */
public class BRTNetworkManager {
	
	public static Context context;

	/**
	 * This method determines if the network connectivity is available    
	 * @return true if the network is available
	 */
	public static boolean isConnected() {
	    ConnectivityManager cm =
	        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	public static boolean isConnectedWithDialogue() {
	    if (isConnected())
	    	return true;
	    
	    return false;
	}
}
