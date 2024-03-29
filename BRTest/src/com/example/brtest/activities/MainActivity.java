package com.example.brtest.activities;

import com.example.brtest.DeviceInfo;
import com.example.brtest.DeviceInfoDetector;
import com.example.brtest.R;
import com.example.brtest.fragments.BRTFragmentManager;
import com.example.brtest.fragments.BRTPhoneFragmentManager;
import com.example.brtest.fragments.BRTTabletFragmentManager;
import com.example.brtest.network.BRTCacheManager;
import com.example.brtest.network.BRTNetworkManager;
import com.example.brtest.views.BRTAlertDialogue;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends FragmentActivity {
	private static BRTFragmentManager fManager;
	private static Resources resources;
	private ProgressBar progressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_activity_store_list);
		initiateFragmentManager(DeviceInfoDetector.getDeviceInfo(this));
		this.getResources();
		resources = getResources();
		progressBar = (ProgressBar) findViewById(R.id.main_progressBar);
	}

	/**
	 * Decides which FragmentManager to instantiate based on the device screen
	 * size and orientation
	 * 
	 * @param deviceInfo
	 *            an enum that shows the device orientation and screen size
	 */
	private void initiateFragmentManager(DeviceInfo deviceInfo) {
		switch (deviceInfo) {
		case PHONE_HORIZONTAL:
			fManager = new BRTPhoneFragmentManager();
			break;
		case PHONE_VERTICAL:
			fManager = new BRTPhoneFragmentManager();
			break;
		case TABLET_HORIZONTAL:
			fManager = new BRTTabletFragmentManager();
			break;
		case TABLET_VERTICAL:
			fManager = new BRTTabletFragmentManager();
			break;
		}
	}

	public static BRTFragmentManager getfManager() {
		return fManager;
	}

	public static Resources getBRTResources() {
		return resources;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.activity_main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		restartApplication();
		return super.onOptionsItemSelected(item);
	}

	/**
	 * If the device has network access, restarts the application
	 */
	public void restartApplication() {
		if (!BRTNetworkManager.isConnected()) {
			(new BRTAlertDialogue(this, false)).show();
		} else {
			if (BRTCacheManager.openStoreList() != null)
			BRTCacheManager.deleteStoreList();
			finish();
			startActivity(getIntent());
		}
	}

	public void addProgressBar() {
		if (progressBar != null) {
			progressBar.setVisibility(View.VISIBLE);
			progressBar.bringToFront();
		}
	}

	public void removeProgressBar() {
		if (progressBar != null) {
			progressBar.setVisibility(View.INVISIBLE);
		}
	}
}
