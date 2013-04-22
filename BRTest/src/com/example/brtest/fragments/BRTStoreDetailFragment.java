package com.example.brtest.fragments;

import com.example.brtest.R;
import com.example.brtest.activities.MainActivity;
import com.example.brtest.model.BRTStore;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This fragment displays the information of one of the stores in detail
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTStoreDetailFragment extends Fragment {
	private View root;
	private BRTStore theStore;
	private ImageView logoView;
	private TextView nameView;
	private TextView phoneView;
	private TextView addressView;
	private TextView coordinateView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = inflater.inflate(R.layout.singlestore, container, false);
		getStore();
		getViews();
		setViews();
		return root;
	}

	/**
	 * Gets the store that should be shown by invoking a static method on the
	 * MainActivity class
	 */
	private void getStore() {
		theStore = MainActivity.getfManager().getStoreToShow();
	}

	/**
	 * Finds all the views that are in the layout
	 */
	private void getViews() {
		logoView = (ImageView) root.findViewById(R.id.storeLogo);
		nameView = (TextView) root.findViewById(R.id.sname);
		phoneView = (TextView) root.findViewById(R.id.sphone);
		addressView = (TextView) root.findViewById(R.id.saddress);
		coordinateView = (TextView) root.findViewById(R.id.coordinate);
	}

	/**
	 * Sets all the views content to the right value.
	 */
	private void setViews() {
		logoView.setImageBitmap(theStore.getLogo());
		nameView.setText(theStore.getLongName());
		phoneView.setText(theStore.getPhone());
		addressView.setText(theStore.getLongAddress());
		coordinateView.setText(theStore.getCoordinate());
		coordinateView.setTextColor(Color.GRAY);
	}
}
