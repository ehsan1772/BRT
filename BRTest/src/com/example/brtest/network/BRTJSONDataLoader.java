package com.example.brtest.network;


import java.util.ArrayList;
import java.util.List;


import org.json.JSONException;

import com.example.brtest.interfaces.BRTJSONDataLoaderOwner;
import com.example.brtest.model.BRTStore;


import android.os.AsyncTask;

public class BRTJSONDataLoader extends AsyncTask<String, Void, ArrayList<BRTStore>>{
	




	private BRTJSONDataLoaderOwner theOwner;
	private BRTStoreJSONParser theParser;
	private List<BRTStore> results;
	public BRTJSONDataLoader(BRTJSONDataLoaderOwner theOwner) {
		this.theOwner = theOwner;
	}

	@Override
	protected void onPreExecute() {
		theOwner.onPreJSONLoaderExecute();
		super.onPreExecute();
	}

	@Override
	protected ArrayList<BRTStore> doInBackground(String... params) {
		ArrayList<BRTStore> result = loadFromMemory();
		if (result == null){
			result = loadFromNetwork(params[0]);
			BRTCacheManager.saveStoreList(result);
		}
		return result;
	}

	@Override
	protected void onPostExecute(ArrayList<BRTStore> result) {
		theOwner.onPostJSONLoaderExecute(result);
		super.onPostExecute(result);
	}

	private ArrayList<BRTStore> loadFromNetwork(String uRL){
		if (!BRTNetworkManager.isConnected())
			return null;
		try {
			theParser = new BRTStoreJSONParser(uRL);
			results = theParser.getObjects();
			return (ArrayList<BRTStore>) results;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private ArrayList<BRTStore> loadFromMemory() {
		return BRTCacheManager.openStoreList();
	}
}
