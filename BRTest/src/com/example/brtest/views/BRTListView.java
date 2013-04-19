package com.example.brtest.views;

import com.example.brtest.fragments.BRTFragmentManager;
import com.example.brtest.model.BRTStores;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class BRTListView extends ListView implements AdapterView.OnItemClickListener {

	private Context context;
	private static BRTFragmentManager fManager;
	
	public BRTListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.setOnItemClickListener(this);
	}


	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		BRTStores.showStore(arg2, context);
	}


	public static BRTFragmentManager getfManager() {
		return fManager;
	}


}
