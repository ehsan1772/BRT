package com.example.brtest.fragments;

import com.example.brtest.R;
import com.example.brtest.model.BRTStoreDisplay;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BRTStoreDetailFragment extends Fragment {

	private View root;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		root = inflater.inflate(R.layout.singlestore, container, false);

		
		String address = BRTStoreDisplay.getLongAddress();
		String phone = BRTStoreDisplay.getPhone();
		String name = BRTStoreDisplay.getLongName();
		String coordinate = BRTStoreDisplay.getCoordinate();
		byte[] logoByte = BRTStoreDisplay.getBitmapdata();
		
		Bitmap bLogo = BitmapFactory.decodeByteArray(logoByte, 0, logoByte.length);
		
		ImageView logoView = (ImageView) root.findViewById(R.id.storeLogo);
		TextView nameView = (TextView) root.findViewById(R.id.sname);
		TextView phoneView = (TextView) root.findViewById(R.id.sphone);
		TextView addressView = (TextView) root.findViewById(R.id.saddress);
		TextView coordinateView = (TextView) root.findViewById(R.id.coordinate);
		
		logoView.setImageBitmap(bLogo);
		nameView.setText(name);
		phoneView.setText(phone);
		addressView.setText(address);
		coordinateView.setText(coordinate);
		coordinateView.setTextColor(Color.GRAY);
		
		Log.d("coordinate = ", coordinate);
		
		return root;
	}

}
