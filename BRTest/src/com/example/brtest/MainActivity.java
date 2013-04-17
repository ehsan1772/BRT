package com.example.brtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	//Network
	boolean loadingCache = false;
	TurnonNetwork turnon = null;
	 
	// JSON
	JSONArray stores = null;
	StoreAdapter storeadapter;
	JSONLoader jsonloader = null;
	ArrayList<Store> storeslist;
	String jstring;

	//Dialog
	//AlertDialog.Builder builder;
	BRAlertDialogue builder;
	DialogInterface.OnClickListener dialogClickListener;

	//Views

	
	TextView loading;
	private BRListView listView;
	ProgressBar pbar;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        
        pbar = (ProgressBar) findViewById(R.id.progressBar1);
        loading = (TextView) findViewById(R.id.loadingtext);
        listView = (BRListView) findViewById(R.id.listView1);
        
        loading.setVisibility(View.INVISIBLE);
        pbar.setVisibility(View.INVISIBLE);
		loading.setTextColor(Color.GRAY);
        
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
//        {
//        	   public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) 
//        	   {
//        		   
//        		    Store thestore = storeslist.get(position);
//        		   
//      				Intent intent = new Intent(getApplicationContext(), StoreView.class);
//    				
//       				Bitmap logo = thestore.getLogo();
//       				ByteArrayOutputStream stream = new ByteArrayOutputStream();
//       				logo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//       				byte[] bitmapdata = stream.toByteArray();
//       				
//       				String longAddress = thestore.getAddress() + "\n" +
//       									 thestore.getCity() + ", " + thestore.getState() + " " + thestore.getZipcode();
//       				
//       				String longName = thestore.getName() + " (ID:" + thestore.getStoreID() + ")";
//       				
//       				String coordinate = thestore.getLatitude() + ", " + thestore.getLongitude();
//       				
//       				intent.putExtra("Coordinate", coordinate);
//       				intent.putExtra("Address", longAddress);
//       				intent.putExtra("Phone", thestore.getPhone());
//       				intent.putExtra("LongName", longName);
//       				intent.putExtra("Logo", bitmapdata);
//       									
//       				startActivity(intent);
//
//        	   } 
//        	
//		});
        

        dialogClickListener = new DialogInterface.OnClickListener() 
        {
			public void onClick(DialogInterface arg0, int arg1) 
			{
				// TODO Auto-generated method stub
		        switch (arg1)
		        {
		        case DialogInterface.BUTTON_POSITIVE:
		            //Yes button clicked
		        	if (turnon == null || turnon.getStatus().compareTo(Status.RUNNING) != 0)
		        	{
		        	turnon = new TurnonNetwork();
		        	turnon.execute(new Void[]{});
		        	}
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		            //No button clicked
		        	finish();
		            break;
		        }
			}
        };
        
            builder = new BRAlertDialogue(this);
    	//builder = new AlertDialog.Builder(this);
//       	builder.setTitle("Network error!");
//        builder.setMessage("A data connection is required, Do you want to create the connection?");
//        builder.setPositiveButton("Yes", dialogClickListener);
//        builder.setNegativeButton("No", dialogClickListener);
        
        
        
    //    builder = new BRAlertDialogue(getApplicationContext());

   	   
       if (isOnline())
       {
       	jsonloader = new JSONLoader();
       	jsonloader.execute(new ListView[]{listView});
       }
       else
       {
       	builder.show();
       }
        

    }

	public boolean isOnline() 
	{
		if(new File(getFilesDir()+File.separator+"json.txt").exists())
				{
			        jstring = openJSON();
			        if(jstring != null)
			        {
						loadingCache = true;
						return true;
			        }
				}
        
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) 
		    return true;

	    return false;
	}
	
	private class JSONLoader extends AsyncTask<ListView, ProgressBar, ArrayList<Store>>{

		// url to make request
		private static final String url = "http://strong-earth-32.heroku.com/stores.aspx";
		 
		// JSON Node names
		private static final String TAG_STORES = "stores";
		private static final String TAG_ADDRESS = "address";
		private static final String TAG_CITY = "city";
		private static final String TAG_NAME = "name";
		private static final String TAG_LATITUDE = "latitude";
		private static final String TAG_ZIPCODE = "zipcode";
		private static final String TAG_STORELOGOURL = "storeLogoURL";
		private static final String TAG_PHONE = "phone";
		private static final String TAG_LONGITUDE = "longitude";
		private static final String TAG_STOREID = "storeID";
		private static final String TAG_STATE = "state";

		@Override
		protected ArrayList<Store> doInBackground(
				ListView... params) {
			
			JSONArray stores = null;
			
	        ArrayList<Store> storesList = new ArrayList<Store>();
	 
	        // Creating JSON Parser instance
	        JSONParser jParser = new JSONParser();
	        
	        JSONObject json = null;

	        if(loadingCache)
	        {
				try {
					json = new JSONObject(jstring);
				} 
				catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	        else
	        {
	        // getting JSON string from URL
	        json = jParser.getJSONFromUrl(url);
	        saveJSON(json.toString());
	        }
	 
	        try {
	            // Getting Array of Contacts
	            stores = json.getJSONArray(TAG_STORES);
	 
	            // looping through All Contacts
	            for(int i = 0; i < stores.length(); i++){
	                JSONObject c = stores.getJSONObject(i);
	                
	                // Storing each json item in variable
	                String address = c.getString(TAG_ADDRESS);
	                String city = c.getString(TAG_CITY);
	                String name = c.getString(TAG_NAME);
	                String latitude = c.getString(TAG_LATITUDE);
	                String zipcode = c.getString(TAG_ZIPCODE);
	                String storeLogoURL = c.getString(TAG_STORELOGOURL);
	                String phone = c.getString(TAG_PHONE);
	                String longitude = c.getString(TAG_LONGITUDE);
	                String storeID = c.getString(TAG_STOREID);
	                String state = c.getString(TAG_STATE);
	                
	                Store store = new Store();
	                
	                store.setAddress(address);
	                store.setCity(city);
	                store.setName(name);
	                store.setLatitude(latitude);
	                store.setZipcode(zipcode);
	                store.setPhone(phone);
	                store.setLongitude(longitude);
	                store.setStoreID(storeID);
	                store.setState(state);
	                
	                if (loadingCache)
	                {
	                	Bitmap logo = openBitmap(storeID);
	                	store.setLogo(logo);
	                }
	                else
	                {
	                	Bitmap logo = store.setLogo(storeLogoURL);
	 	                saveBitmap(logo, storeID);
	                }
	                               
	                storesList.add(store);
	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }

			return storesList;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			loading.setText("Loading...");
            pbar.setVisibility(View.VISIBLE);
            loading.setVisibility(View.VISIBLE);
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(ArrayList<Store> result) {
			// TODO Auto-generated method stub
			storeslist = result;
            storeadapter = new StoreAdapter(getBaseContext(), R.layout.storelayout, storeslist);
            listView.setAdapter(storeadapter);
            pbar.setVisibility(View.INVISIBLE);
            loading.setVisibility(View.INVISIBLE);
			super.onPostExecute(result);
		}
		
		public void saveJSON(String jsonstring)
		{
			BufferedWriter bufferedWriter;
			try {
				bufferedWriter = new BufferedWriter(new FileWriter(new File(getFilesDir()+File.separator+"json.txt")));
				bufferedWriter.write(jsonstring);
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		public void saveBitmap(Bitmap bitmap, String name)
		{
			try
			{
			final FileOutputStream fos = openFileOutput(name + ".jpg", Context.MODE_PRIVATE);
			bitmap.compress(CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
			} catch (FileNotFoundException e) 
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
		public Bitmap openBitmap(String fileName)
		{
			File filePath = getFileStreamPath(fileName + ".jpg");
			return BitmapFactory.decodeFile(filePath.getPath());
		}
		

		
	}
    
	public String openJSON()
	{
		BufferedReader bufferedReader = null;
		File jsonFile = new File(getFilesDir()+File.separator+"json.txt");
		FileReader jasonFileReader = null;
		try {
			jasonFileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

		String read = null;
		StringBuilder builder = new StringBuilder("");
		bufferedReader = new BufferedReader(jasonFileReader);
		
		try {
			while((read = bufferedReader.readLine()) != null){
			builder.append(read);
			bufferedReader.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return read;
	}
	
	 private void setMobileDataEnabled(Context context, boolean enabled) {

	        try {
	        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	        final Class conmanClass = Class.forName(conman.getClass().getName());
	        final java.lang.reflect.Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
	        iConnectivityManagerField.setAccessible(true);
	        final Object iConnectivityManager = iConnectivityManagerField.get(conman);
	        final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
	        final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
	        setMobileDataEnabledMethod.setAccessible(true);

	        setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);

	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (SecurityException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (NoSuchFieldException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IllegalArgumentException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (NoSuchMethodException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (InvocationTargetException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        finally {

	        }
	    }
	 
	 
		private class TurnonNetwork extends AsyncTask<Void, ProgressBar, Boolean>{

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub 
				loading.setText("Connecting...");
	            pbar.setVisibility(View.VISIBLE);
	            loading.setVisibility(View.VISIBLE);
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(Boolean result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
	            pbar.setVisibility(View.INVISIBLE);
	            loading.setVisibility(View.INVISIBLE);
				if (!result)
				{
					
				}
				else
				{
		           	jsonloader = new JSONLoader();
		           	jsonloader.execute(new ListView[]{listView});
				}

			}

			@Override
			protected Boolean doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
	        	setMobileDataEnabled(getBaseContext(), true);
	        	WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	        	wifi.setWifiEnabled(true);
	        	
	        	int counter = 0;
	        	do{
	        	try {
	        		if (counter < 20)
	        		{
	        		Log.d("counter is", String.valueOf(counter));
					Thread.currentThread().sleep(2000);
					counter++;
	        	}
	        	else{
	        		return false;
	        	}
	        	
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	}while (!isOnline());
	        	
	        	
				return true;
			}
		}

}
