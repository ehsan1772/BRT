package com.example.brtest.fragments;

import java.util.ArrayList;

import com.example.brtest.R;
import com.example.brtest.interfaces.BRTJSONDataLoaderOwner;
import com.example.brtest.model.BRTStore;
import com.example.brtest.model.BRTStores;
import com.example.brtest.network.BRTCacheManager;
import com.example.brtest.network.BRTJSONDataLoader;
import com.example.brtest.network.BRTNetworkManager;
import com.example.brtest.views.BRTAlertDialogue;
import com.example.brtest.views.BRTListView;
import com.example.brtest.views.BRTStoreAdapter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BRTStoreListFragment extends Fragment implements BRTJSONDataLoaderOwner{

	private View root;
	private BRTStoreAdapter storeadapter;
	private ArrayList<BRTStore> storeslist;
	private BRTAlertDialogue builder;
	private DialogInterface.OnClickListener dialogClickListener;
	
	private TextView loading;
	private BRTListView listView;
	private ProgressBar pbar;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.activity_main, container, false);
		
        pbar = (ProgressBar) root.findViewById(R.id.progressBar1);
        loading = (TextView) root.findViewById(R.id.loadingtext);
        listView = (BRTListView) root.findViewById(R.id.listView1);
        
        loading.setVisibility(View.INVISIBLE);
        pbar.setVisibility(View.INVISIBLE);
		loading.setTextColor(Color.GRAY);
		
		BRTCacheManager.activity = getActivity();
		BRTNetworkManager.context = getActivity();
        
        
        builder = new BRTAlertDialogue(getActivity(), true);

       
       BRTJSONDataLoader brt = new BRTJSONDataLoader(this);
       brt.execute("http://strong-earth-32.heroku.com/stores.aspx");
       
		return root;
	}

	public void onPostJSONLoaderExecute(Object result) {
		if (result == null){
			builder.show();
		} else {
		storeslist = (ArrayList<BRTStore>) result;
		BRTStores.setStores(storeslist);
        storeadapter = new BRTStoreAdapter(getActivity(), R.layout.storelayout, storeslist);
        listView.setAdapter(storeadapter);
        pbar.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);
		}
		
		
		
	}

	public void onPreJSONLoaderExecute() {
		loading.setText("Loading...");
        pbar.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
		
	}

}
