package com.example.brtest.network;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.brtest.model.BRTStore;

/**
 * This class extends the generic BRTGenericJSONParser class and implements the
 * abstract methods to get a List<BRTStore> from the provided URL
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTStoreJSONParser extends BRTGenericJSONParser<BRTStore> {
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
	private String address;
	private String city;
	private String name;
	private String latitude;
	private String zipcode;
	private String storeLogoURL;
	private String phone;
	private String longitude;
	private String storeID;
	private String state;

	public BRTStoreJSONParser(String urlString) throws JSONException {
		super(urlString);
	}

	@Override
	public List<BRTStore> getObjects() {
		List<BRTStore> storesList = new ArrayList<BRTStore>();
		try {
			setArrayTag(TAG_STORES);
			for (int i = 0; i < jSONArray.length(); i++) {
				JSONObject jObject = jSONArray.getJSONObject(i);
				getValues(jObject);
				BRTStore store = getStore();
				storesList.add(store);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return storesList;
	}

	private void getValues(JSONObject c) throws JSONException {
		address = c.getString(TAG_ADDRESS);
		city = c.getString(TAG_CITY);
		name = c.getString(TAG_NAME);
		latitude = c.getString(TAG_LATITUDE);
		zipcode = c.getString(TAG_ZIPCODE);
		storeLogoURL = c.getString(TAG_STORELOGOURL);
		phone = c.getString(TAG_PHONE);
		longitude = c.getString(TAG_LONGITUDE);
		storeID = c.getString(TAG_STOREID);
		state = c.getString(TAG_STATE);
	}

	private BRTStore getStore() {
		BRTStore store = new BRTStore();
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
		return store;
	}
}
