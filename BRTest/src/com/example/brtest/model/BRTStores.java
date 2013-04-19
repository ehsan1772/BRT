package com.example.brtest.model;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.example.brtest.BRTStoreViewActivity;
import com.example.brtest.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

public class BRTStores {

	private static List<BRTStore> stores;
	private static BRTStore theStore;

	public static List<BRTStore> getStores() {
		return stores;
	}

	public static void setStores(List<BRTStore> myStores) {
		stores = myStores;
	}
	
	public static BRTStore getStore(int position){
		return stores.get(position);
	}
	
	public static void showStore(int position, Context context){
		
    	BRTStore thestore = stores.get(position);
    	
		Bitmap logo = thestore.getLogo();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		logo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] bitmapdata = stream.toByteArray();
		
		String longAddress = thestore.getAddress() + "\n" +
							 thestore.getCity() + ", " + thestore.getState() + " " + thestore.getZipcode();
		
		String longName = thestore.getName() + " (ID:" + thestore.getStoreID() + ")";
		
		String coordinate = thestore.getLatitude() + ", " + thestore.getLongitude();
			
		BRTStoreDisplay.setBitmapdata(bitmapdata);
		BRTStoreDisplay.setCoordinate(coordinate);
		BRTStoreDisplay.setLongAddress(longAddress);
		BRTStoreDisplay.setLongName(longName);
		BRTStoreDisplay.setPhone(thestore.getPhone());
		
    	MainActivity.getfManager().startStoreDetail(thestore, context);
		   
//		Intent intent = new Intent(context, BRTStoreViewActivity.class);
//	
//		Bitmap logo = thestore.getLogo();
//		ByteArrayOutputStream stream = new ByteArrayOutputStream();
//		logo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//		byte[] bitmapdata = stream.toByteArray();
//		
//		String longAddress = thestore.getAddress() + "\n" +
//							 thestore.getCity() + ", " + thestore.getState() + " " + thestore.getZipcode();
//		
//		String longName = thestore.getName() + " (ID:" + thestore.getStoreID() + ")";
//		
//		String coordinate = thestore.getLatitude() + ", " + thestore.getLongitude();
//		
//		intent.putExtra("Coordinate", coordinate);
//		intent.putExtra("Address", longAddress);
//		intent.putExtra("Phone", thestore.getPhone());
//		intent.putExtra("LongName", longName);
//		intent.putExtra("Logo", bitmapdata);
//		
//		BRTStoreDisplay.setBitmapdata(bitmapdata);
//		BRTStoreDisplay.setCoordinate(longAddress);
//		BRTStoreDisplay.setLongAddress(coordinate);
//		BRTStoreDisplay.setLongName(longName);
//		BRTStoreDisplay.setPhone(thestore.getPhone());
//							
//		context.startActivity(intent);
	}

	public static BRTStore getTheStore() {
		return theStore;
	}

	public static void setTheStore(BRTStore theStore) {
		BRTStores.theStore = theStore;
	}
	
}
