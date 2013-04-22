package com.example.brtest.fragments;

import java.util.ArrayList;

import com.example.brtest.R;
import com.example.brtest.activities.MainActivity;
import com.example.brtest.interfaces.BRTJSONDataLoaderOwner;
import com.example.brtest.model.BRTStore;
import com.example.brtest.model.BRTStores;
import com.example.brtest.network.BRTCacheManager;
import com.example.brtest.network.BRTJSONDataLoader;
import com.example.brtest.network.BRTNetworkManager;
import com.example.brtest.views.BRTAlertDialogue;
import com.example.brtest.views.BRTListView;
import com.example.brtest.views.BRTStoreAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This class is a fragment that displays a list of all the stores
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTStoreListFragment extends Fragment implements
		BRTJSONDataLoaderOwner {
	private View root;
	private BRTStoreAdapter storeadapter;
	private ArrayList<BRTStore> storeslist;
	private BRTAlertDialogue builder;
	private BRTListView listView;
	private static final String URL = "http://strong-earth-32.heroku.com/stores.aspx";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.activity_main, container, false);
		getViews();
		setListView();
		return root;
	}

	private void setListView() {
		BRTCacheManager.activity = getActivity();
		BRTNetworkManager.context = getActivity();
		BRTJSONDataLoader brt = new BRTJSONDataLoader(this);
		brt.execute(URL);
	}

	@SuppressWarnings("unchecked")
	public void onPostJSONLoaderExecute(Object result) {
		if (result == null) {
			builder = new BRTAlertDialogue(getActivity(), true);
			builder.show();
		} else {
			storeslist = (ArrayList<BRTStore>) result;
			BRTStores stores = new BRTStores(storeslist);
			showStores(stores);
		}
	}

	private void showStores(BRTStores stores) {
		MainActivity.getfManager().setStores(stores);
		storeadapter = new BRTStoreAdapter(getActivity(), R.layout.storelayout,
				storeslist);
		listView.setAdapter(storeadapter);
		setStateLoaded();
	}

	private void setStateLoading() {
		((MainActivity) getActivity()).addProgressBar();
	}

	private void setStateLoaded() {
		((MainActivity) getActivity()).removeProgressBar();
	}

	private void getViews() {
		listView = (BRTListView) root.findViewById(R.id.listView1);
	}

	public void onPreJSONLoaderExecute() {
		setStateLoading();
	}
}
