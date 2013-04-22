package com.example.brtest;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;

/**
 * This class has one public method that specifies the orientation and screen
 * size of the device
 * 
 * @author Ehsan Barekati
 * 
 */
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

	/**
	 * 
	 * @param context
	 *            : The context of the activity
	 * @return a DeviceInfo enum that shows device's screen size
	 */
	private static DeviceInfo screenSize(Context context) {
		int layoutAndMask = context.getResources().getConfiguration().screenLayout
				& Configuration.SCREENLAYOUT_SIZE_MASK;
		switch (layoutAndMask) {
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			return DeviceInfo.LARGE;
		case Configuration.SCREENLAYOUT_SIZE_XLARGE:
			return DeviceInfo.LARGE;
		}
		return DeviceInfo.NORMAL;
	}

	/**
	 * 
	 * @param context
	 *            : The context of the activity
	 * @return a DeviceInfo enum that shows device's screen orientation
	 */
	private static DeviceInfo screenOrientation(Context context) {
		switch (context.getResources().getConfiguration().orientation) {
		case (Configuration.ORIENTATION_LANDSCAPE):
			return DeviceInfo.HORIZONTAL;
		case (Configuration.ORIENTATION_PORTRAIT):
			return DeviceInfo.VERTICAL;
		}
		return null;
	}
}
