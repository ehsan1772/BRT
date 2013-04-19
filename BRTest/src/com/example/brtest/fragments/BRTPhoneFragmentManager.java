package com.example.brtest.fragments;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.example.brtest.BRTStoreViewActivity;
import com.example.brtest.model.BRTStore;
import com.example.brtest.model.BRTStoreDisplay;

public class BRTPhoneFragmentManager extends BRTFragmentManager {

	@Override
	public void startStoreDetail(Object arg, Context context) {
    	BRTStore thestore = (BRTStore) arg;
    	
		   
		Intent intent = new Intent(context, BRTStoreViewActivity.class);

							
		context.startActivity(intent);
		
	}

	@Override
	public void startStoreList(Object arg) {
		// TODO Auto-generated method stub
		
	}

}
