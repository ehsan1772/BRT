package com.example.brtest.fragments;

import com.example.brtest.activities.BRTStoreViewActivity;

import android.content.Context;
import android.content.Intent;

/**
 * An extension of BRTFragmentManager that initializes different activities on a
 * handset
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTPhoneFragmentManager extends BRTFragmentManager {
	@Override
	public void startStoreDetail(int arg, Context context) {
		super.startStoreDetail(arg, context);
		Intent intent = new Intent(context, BRTStoreViewActivity.class);
		context.startActivity(intent);
	}

	@Override
	public void startStoreList(Object arg) {
	}
}
