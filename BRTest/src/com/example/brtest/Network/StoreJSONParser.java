package com.example.brtest.Network;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.brtest.Store;

public class StoreJSONParser extends GenericJSONParser<Store>{

	
public StoreJSONParser(String urlString) throws JSONException {
		super(urlString);
		// TODO Auto-generated constructor stub
	}

//public StoreJSONParser(String jSONString, Activity activity)
//			throws JSONException {
//		super(jSONString, activity);
//		// TODO Auto-generated constructor stub
//	}


	
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
	public List<Store> getObjects() {
		
		List<Store> storesList = new ArrayList<Store>();
        try {
		setArrayTag(TAG_STORES);
        for(int i = 0; i < jSONArray.length(); i++){
        	

	            JSONObject c = jSONArray.getJSONObject(i);

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
	            store.setLogo(storeLogoURL);
	            
//	            if (loadingCache) {
//	            	Bitmap logo = CacheManager.openBitmap(storeID, activity);
//	            	store.setLogo(logo);
//	            } else {
	           // 	Bitmap logo = store.setLogo(storeLogoURL);
	          //  	CacheManager.saveBitmap(logo, storeID, activity);
//	            }
	                           
	            storesList.add(store);
	            Log.d("List Size=", String.valueOf(storesList.size()));

        }        
        } catch (JSONException e) {
        	e.printStackTrace();
        }
        Log.d("List Size=", String.valueOf(storesList.size()));
            return storesList;
	}
	
}
