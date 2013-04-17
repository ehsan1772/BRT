package com.example.brtest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class BRAlertDialogue extends AlertDialog.Builder implements DialogInterface.OnClickListener{

private MainActivity mainActivity; 

	public BRAlertDialogue(Context arg0) {
		super(arg0);
		mainActivity = (MainActivity) arg0;
       	this.setTitle("Network error!");
        this.setMessage("A data connection is required, Do you want to create the connection?");
        this.setPositiveButton("Yes", this);
      //  this.setNegativeButton("No", this);
	}

	public void onClick(DialogInterface dialog, int which) {
		
		mainActivity.finish();
	}

}
