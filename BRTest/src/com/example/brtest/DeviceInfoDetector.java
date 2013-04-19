package com.example.brtest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class DeviceInfoDetector {

	public static DeviceInfo getDeviceInfo(Activity context) {

		DeviceInfo size = screenSize(context);
		DeviceInfo orientation = screenOrientation(context);
		
		if (size == DeviceInfo.NORMAL && orientation == DeviceInfo.HORIZONTAL)
			return DeviceInfo.PHONE_HORIZONTAL;
		if (size == DeviceInfo.NORMAL && orientation == DeviceInfo.VERTICAL)
			return DeviceInfo.PHONE_VERTICAL;
		if (size == DeviceInfo.LARGE && orientation == DeviceInfo.HORIZONTAL)
			return DeviceInfo.TABLET_HORIZONTAL;
		if (size == DeviceInfo.LARGE && orientation == DeviceInfo.VERTICAL)
			return DeviceInfo.TABLET_VERTICAL;
		
		return DeviceInfo.PHONE_VERTICAL;
		
	}

	@SuppressWarnings("static-access")
	private static DeviceInfo screenSize(Activity context) {

		int screenLayout = context.getResources().getConfiguration().screenLayout;
		int sizeMask = context.getResources().getConfiguration().SCREENLAYOUT_SIZE_MASK;
		int large = context.getResources().getConfiguration().SCREENLAYOUT_SIZE_LARGE;
		int xlarge = context.getResources().getConfiguration().SCREENLAYOUT_SIZE_XLARGE;

			
		if ((screenLayout &	sizeMask) == large) {
			return DeviceInfo.LARGE;
		} else if ((screenLayout &	sizeMask) == xlarge) {
			return DeviceInfo.LARGE;
		}

		return DeviceInfo.NORMAL;
	}

	private static DeviceInfo screenOrientation(Activity context) {

		int orientation = context.getResources().getConfiguration().orientation;
		int horizontal = context.getResources().getConfiguration().ORIENTATION_LANDSCAPE;
		int vertical = context.getResources().getConfiguration().ORIENTATION_PORTRAIT;
		
		if (orientation == horizontal) {
			return DeviceInfo.HORIZONTAL;
		} else if (orientation == vertical) {
			return DeviceInfo.VERTICAL;
		}
		
		return DeviceInfo.VERTICAL;

	}


}
