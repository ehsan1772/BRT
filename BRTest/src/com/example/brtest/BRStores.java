package com.example.brtest;

import java.io.ByteArrayOutputStream;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

public class BRStores {

	private static List<Store> stores;

	public static List<Store> getStores() {
		return stores;
	}

	public static void setStores(List<Store> myStores) {
		stores = myStores;
	}
	
	public static Store getStore(int position){
		return stores.get(position);
	}
	
	public static void showStore(int position, Context context){
		
    	Store thestore = stores.get(position);
		   
		Intent intent = new Intent(context, StoreView.class);
	
		Bitmap logo = thestore.getLogo();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		logo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] bitmapdata = stream.toByteArray();
		
		String longAddress = thestore.getAddress() + "\n" +
							 thestore.getCity() + ", " + thestore.getState() + " " + thestore.getZipcode();
		
		String longName = thestore.getName() + " (ID:" + thestore.getStoreID() + ")";
		
		String coordinate = thestore.getLatitude() + ", " + thestore.getLongitude();
		
		intent.putExtra("Coordinate", coordinate);
		intent.putExtra("Address", longAddress);
		intent.putExtra("Phone", thestore.getPhone());
		intent.putExtra("LongName", longName);
		intent.putExtra("Logo", bitmapdata);
							
		context.startActivity(intent);
	}
	
}
