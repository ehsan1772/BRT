package com.example.brtest.views;

import com.example.brtest.activities.MainActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * This class is a ListView that implements OnItemClickListener
 * It's used to show the stores in a listview.
 * @author Ehsan Barekati
 *
 */
public class BRTListView extends ListView implements AdapterView.OnItemClickListener {
	private Context context;
	
	public BRTListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		this.setOnItemClickListener(this);
	}

	/**
	 * Invokes a method on FragmentManager to display the details of the store 
	 */
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		MainActivity.getfManager().startStoreDetail(arg2, context);
	}
}
