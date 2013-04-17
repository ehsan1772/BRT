package com.example.brtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class BRListView extends ListView implements AdapterView.OnItemClickListener {

	private Context context;
	
	public BRListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.setOnItemClickListener(this);
	}


	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

		BRStores.showStore(arg2, context);
	}

}
