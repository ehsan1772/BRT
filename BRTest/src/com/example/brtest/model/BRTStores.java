package com.example.brtest.model;

import java.util.List;

/**
 * This class represents all the stores that are shown in the application
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTStores {
	private List<BRTStore> stores;
	private BRTStore theStore;

	public BRTStores(List<BRTStore> stores) {
		this.stores = stores;
	}

	public List<BRTStore> getStores() {
		return stores;
	}

	public void setStores(List<BRTStore> myStores) {
		stores = myStores;
	}

	public BRTStore getStore(int position) {
		return stores.get(position);
	}

	public BRTStore getTheStore() {
		return theStore;
	}

	public void setTheStore(BRTStore theStore) {
		this.theStore = theStore;
	}
}
