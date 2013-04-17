package com.example.brtest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StoreView extends Activity{

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        setContentView(R.layout.singlestore);

		super.onCreate(savedInstanceState);
		
		String address = getIntent().getExtras().getString("Address");
		String phone = getIntent().getExtras().getString("Phone");
		String name = getIntent().getExtras().getString("LongName");
		String coordinate = getIntent().getExtras().getString("Coordinate");
		byte[] logoByte = getIntent().getExtras().getByteArray("Logo");
		
		Bitmap bLogo = BitmapFactory.decodeByteArray(logoByte, 0, logoByte.length);
		
		ImageView logoView = (ImageView) findViewById(R.id.storeLogo);
		TextView nameView = (TextView) findViewById(R.id.sname);
		TextView phoneView = (TextView) findViewById(R.id.sphone);
		TextView addressView = (TextView) findViewById(R.id.saddress);
		TextView coordinateView = (TextView) findViewById(R.id.coordinate);
		
		logoView.setImageBitmap(bLogo);
		nameView.setText(name);
		phoneView.setText(phone);
		addressView.setText(address);
		coordinateView.setText(coordinate);
		coordinateView.setTextColor(Color.GRAY);

		
	}

}
