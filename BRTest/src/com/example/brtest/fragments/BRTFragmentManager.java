package com.example.brtest.fragments;

import com.example.brtest.model.BRTStore;
import com.example.brtest.model.BRTStores;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * An abstract class that should be extended separately for tablets and handsets
 * to define how to start different fragments
 * 
 * @author Ehsan Barekati
 * 
 */
public abstract class BRTFragmentManager {
	protected BRTStore storeToShow;
	protected BRTStores stores;
	protected BRTStoreDetailFragment secondFragment;
	protected BRTStoreListFragment firstFragment;

	public void startStoreDetail(int arg, Context context) {
		setStoreToShow(this.stores.getStore(arg));
	}

	public abstract void startStoreList(Object arg);

	public BRTStore getStoreToShow() {
		return storeToShow;
	}

	public void setStoreToShow(BRTStore storeToShow) {
		this.storeToShow = storeToShow;
	}

	public BRTStores getStores() {
		return stores;
	}

	public void setStores(BRTStores stores) {
		this.stores = stores;
	}

	public BRTStoreDetailFragment getSecondFragment() {
		return secondFragment;
	}

	public void setSecondFragment(BRTStoreDetailFragment secondFragment) {
		this.secondFragment = secondFragment;
	}

	public BRTStoreListFragment getFirstFragment() {
		return firstFragment;
	}

	public void setFirstFragment(BRTStoreListFragment firstFragment) {
		this.firstFragment = firstFragment;
	}
}
