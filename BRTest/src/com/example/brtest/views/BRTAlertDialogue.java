package com.example.brtest.views;

import com.example.brtest.activities.MainActivity;
import com.example.brtest.network.BRTNetworkManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * This class extends AlertDialog.Builder and creates an alert dialogue that is
 * shown when there is no access to the network if there is no cache available,
 * it closes the app
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTAlertDialogue extends AlertDialog.Builder implements
		DialogInterface.OnClickListener {
	private MainActivity mainActivity;
	private boolean closeApp;
	private Context context;

	public BRTAlertDialogue(Context arg0, boolean closeApp) {
		super(arg0);
		context = arg0;
		this.closeApp = closeApp;
		mainActivity = (MainActivity) arg0;
		this.setTitle("No network connection");
		this.setMessage("No network access is available. Do you want to enable it?");
		this.setPositiveButton("Yes", this);
		this.setNegativeButton("No", this);
	}

	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case DialogInterface.BUTTON_POSITIVE:
			try {
				BRTNetworkManager.connect(context, true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case DialogInterface.BUTTON_NEGATIVE:
			if (closeApp)
				mainActivity.finish();
			break;
		}

	}
}
