package com.example.brtest.views;

import com.example.brtest.activities.MainActivity;
import com.example.brtest.network.BRTCacheManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * This class extends AlertDialog.Builder and creates an alert dialogue that is
 * shown when the connection to network cannot be established.
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTNetworkAlertDialogue extends AlertDialog.Builder implements
		DialogInterface.OnClickListener {
	private MainActivity mainActivity;

	public BRTNetworkAlertDialogue(Context arg0) {
		super(arg0);
		mainActivity = (MainActivity) arg0;
		this.setTitle("No network connection");
		this.setMessage("Accessing network is taking too long!");
		this.setPositiveButton("Ok", this);
	}

	public void onClick(DialogInterface arg0, int arg1) {
		if (BRTCacheManager.openStoreList() == null)
			mainActivity.finish();
	}

}
