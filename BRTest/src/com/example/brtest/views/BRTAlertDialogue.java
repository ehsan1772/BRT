package com.example.brtest.views;

import com.example.brtest.MainActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class BRTAlertDialogue extends AlertDialog.Builder implements DialogInterface.OnClickListener{

private MainActivity mainActivity; 
private boolean closeApp;

	public BRTAlertDialogue(Context arg0, boolean closeApp) {
		super(arg0);
		this.closeApp = closeApp;
		mainActivity = (MainActivity) arg0;
       	this.setTitle("No network connection");
        this.setMessage("Mobile data is disabled. Connect to Wi-Fi network instead, or enable mobile data and try again");
        this.setPositiveButton("Ok", this);
	}

	public void onClick(DialogInterface dialog, int which) {
		if (closeApp)
		mainActivity.finish();
	}

}
