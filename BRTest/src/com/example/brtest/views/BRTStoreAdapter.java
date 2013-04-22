package com.example.brtest.views;

import java.util.ArrayList;
import com.example.brtest.R;
import com.example.brtest.model.BRTStore;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A basic view adapter that follows the veiwHolder pattern
 * @author Ehsan Barekati
 *
 */
public class BRTStoreAdapter extends ArrayAdapter<BRTStore> {
	private BRTStore store;
	private ArrayList<BRTStore> stores;
	private Context context;
	private ViewHolder viewHolder;

	public BRTStoreAdapter(Context context, int textViewResourceId,
			ArrayList<BRTStore> objects) {
		super(context, textViewResourceId, objects);
		stores = objects;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		store = stores.get(position);

		if (convertView == null) {
			LayoutInflater inflator = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflator.inflate(R.layout.storelayout, parent, false);
			viewHolder = getViewHolder(convertView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(store.getPhone());
		viewHolder.address.setText(store.getAddress());
		viewHolder.imageview.setImageBitmap(store.getLogo());
		return convertView;
	}

	private ViewHolder getViewHolder(View convertView) {
		viewHolder = new ViewHolder();
		viewHolder.imageview = (ImageView) convertView.findViewById(R.id.logo);
		viewHolder.name = (TextView) convertView.findViewById(R.id.phone);
		viewHolder.address = (TextView) convertView.findViewById(R.id.address);
		return viewHolder;
	}

	static private class ViewHolder {
		ImageView imageview;
		TextView name;
		TextView address;
	}
}
