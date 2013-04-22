package com.example.brtest.activities;

import com.example.brtest.R;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * This class is a FragmentActivit that shows the detail informatin about a
 * store
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTStoreViewActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_activity_store_detail);
	}
}
