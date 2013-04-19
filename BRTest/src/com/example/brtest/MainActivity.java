package com.example.brtest;


import com.example.brtest.fragments.BRTFragmentManager;
import com.example.brtest.fragments.BRTPhoneFragmentManager;
import com.example.brtest.fragments.BRTTabletFragmentManager;
import com.example.brtest.network.BRTCacheManager;
import com.example.brtest.network.BRTNetworkManager;
import com.example.brtest.views.BRTAlertDialogue;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

	private DeviceInfo deviceInfo;
	private static BRTFragmentManager fManager;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.fragment_activity_store_list);
        
        
		int screenLayout = getResources().getConfiguration().screenLayout;
		int sizeMask = Configuration.SCREENLAYOUT_SIZE_MASK;
		
		Log.d("ScreenSize = ", String.valueOf(screenLayout));
		Log.d("ScreenSize = ", String.valueOf(sizeMask));
		
        deviceInfo = DeviceInfoDetector.getDeviceInfo(this);
        switch (deviceInfo){
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
		if (!BRTNetworkManager.isConnected()){
			(new BRTAlertDialogue(this, false)).show();
		} else {
			BRTCacheManager.deleteStoreList();
			finish();
			startActivity(getIntent());
		}
		return super.onOptionsItemSelected(item);
	}



}
