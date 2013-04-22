package activities;

import com.example.brtest.DeviceInfo;
import com.example.brtest.DeviceInfoDetector;
import com.example.brtest.R;
import com.example.brtest.fragments.BRTFragmentManager;
import com.example.brtest.fragments.BRTPhoneFragmentManager;
import com.example.brtest.fragments.BRTTabletFragmentManager;
import com.example.brtest.network.BRTCacheManager;
import com.example.brtest.network.BRTNetworkManager;
import com.example.brtest.views.BRTAlertDialogue;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {
	private static BRTFragmentManager fManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_activity_store_list);
		initiateFragmentManager(DeviceInfoDetector.getDeviceInfo(this));
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
	private void restartApplication() {
		if (!BRTNetworkManager.isConnected()) {
			(new BRTAlertDialogue(this, false)).show();
		} else {
			BRTCacheManager.deleteStoreList();
			finish();
			startActivity(getIntent());
		}
	}
}
