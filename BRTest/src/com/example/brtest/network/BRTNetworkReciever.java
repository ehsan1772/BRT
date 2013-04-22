package com.example.brtest.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BRTNetworkReciever extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("Connectivity Changed!!", intent.getAction());
		
	}

}
