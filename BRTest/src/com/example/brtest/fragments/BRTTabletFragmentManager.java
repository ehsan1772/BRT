package com.example.brtest.fragments;

import com.example.brtest.R;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * This class extends the abstract BRTFragmentManager class and implements the
 * methods to fit a tablet device.
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTTabletFragmentManager extends BRTFragmentManager {
	@Override
	public void startStoreDetail(int arg, Context context) {
		super.startStoreDetail(arg, context);
		secondFragment = new BRTStoreDetailFragment();
		runTransaction(secondFragment, context);
	}

	@Override
	public void startStoreList(Object arg) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method starts and commits a transaction to replace the fragment with
	 * an embedded animation
	 * 
	 * @param fragment
	 *            : The new fragment to be replaced
	 * @param context
	 */
	private void runTransaction(Fragment fragment, Context context) {
		FragmentActivity fragmentActivity = (FragmentActivity) context;
		FragmentManager fragmentManager = fragmentActivity
				.getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
		transaction.replace(R.id.second_layout, fragment);
		transaction.commit();
	}
}
