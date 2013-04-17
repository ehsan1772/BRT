package com.example.brtest;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreAdapter extends ArrayAdapter<Store> {

	Store store;
	ArrayList<Store> stores;
	Context context;

	public StoreAdapter(Context context, int textViewResourceId,
			ArrayList<Store> objects) {
		super(context, textViewResourceId, objects);
		stores = objects;
		this.context = context;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		store = stores.get(position);

		LayoutInflater inflator = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowview = inflator.inflate(R.layout.storelayout, parent, false);

		ImageView imageview = (ImageView) rowview.findViewById(R.id.logo);
		TextView name = (TextView) rowview.findViewById(R.id.phone);
		TextView address = (TextView) rowview.findViewById(R.id.address);

		name.setText(store.getPhone());
		address.setText(store.getAddress());
		imageview.setImageBitmap(store.getLogo());

		return rowview;

	}

}
