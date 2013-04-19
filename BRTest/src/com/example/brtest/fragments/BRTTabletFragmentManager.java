package com.example.brtest.fragments;


import com.example.brtest.R;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class BRTTabletFragmentManager extends BRTFragmentManager {

	@Override
	public void startStoreDetail(Object arg, Context context) {
		FragmentActivity fragmentActivity = (FragmentActivity) context;
		FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
		Fragment secondFragment = new BRTStoreDetailFragment();
		FragmentTransaction transaction = fragmentManager
				.beginTransaction();
//		transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
		transaction.replace(R.id.second_layout, secondFragment);
		
		transaction.commit();
		
	}

	@Override
	public void startStoreList(Object arg) {
		// TODO Auto-generated method stub
		
	}

}
