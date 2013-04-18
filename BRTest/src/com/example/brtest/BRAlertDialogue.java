package com.example.brtest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class BRAlertDialogue extends AlertDialog.Builder implements DialogInterface.OnClickListener{

private MainActivity mainActivity; 

	public BRAlertDialogue(Context arg0) {
		super(arg0);
		mainActivity = (MainActivity) arg0;
       	this.setTitle("No network connection");
        this.setMessage("Mobile data is disabled. Connect to Wi-Fi network instead, or enable mobile data and try again");
        this.setPositiveButton("Yes", this);
	}

	public void onClick(DialogInterface dialog, int which) {
		mainActivity.finish();
	}

}
