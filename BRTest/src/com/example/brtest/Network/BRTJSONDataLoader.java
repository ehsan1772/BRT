package com.example.brtest.Network;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.example.brtest.NetworkManager;
import com.example.brtest.Store;
import com.example.brtest.Interfaces.BRTJSONDataLoaderOwner;

import android.os.AsyncTask;

public class BRTJSONDataLoader extends AsyncTask<String, Void, ArrayList<Store>>{
	




	private BRTJSONDataLoaderOwner theOwner;
	private StoreJSONParser theParser;
	private List<Store> results;
	public BRTJSONDataLoader(BRTJSONDataLoaderOwner theOwner) {
		this.theOwner = theOwner;
	}

	@Override
	protected ArrayList<Store> doInBackground(String... params) {
		ArrayList<Store> result = loadFromMemory();
		if (result == null){
			result = loadFromNetwork(params[0]);
			CacheManager.saveStoreList(result);
		}
		return result;
	}

	@Override
	protected void onPostExecute(ArrayList<Store> result) {
		theOwner.onPostJSONLoaderExecute(result);
		super.onPostExecute(result);
	}

	private ArrayList<Store> loadFromNetwork(String uRL){
		if (!NetworkManager.isConnected())
			return null;
		try {
			theParser = new StoreJSONParser(uRL);
			results = theParser.getObjects();
			return (ArrayList<Store>) results;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private ArrayList<Store> loadFromMemory() {
		return CacheManager.openStoreList();
	}
}
