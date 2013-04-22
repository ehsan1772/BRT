package com.example.brtest.model;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import exceptions.ExceptionHandler;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

/**
 * This class represents one of the stores as a Serializable class that is used
 * in different parts of the application
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTStore implements Serializable {
	private static final long serialVersionUID = -1095871687354780777L;
	private String address;
	private String city;
	private String name;
	private String latitude;
	private String zipcode;
	private String storeLogoURL;
	private transient Bitmap logo;
	private String phone;
	private String longitude;
	private String storeID;
	private String state;
	private byte[] logoByteArray;

	public void setAddress(String value) {
		address = value;
	}

	public String getAddress() {
		return address;
	}

	public void setCity(String value) {
		city = value;
	}

	public String getCity() {
		return city;
	}

	public void setName(String value) {
		name = value;
	}

	public String getName() {
		return name;
	}

	public void setLatitude(String value) {
		latitude = value;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setZipcode(String value) {
		zipcode = value;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setStoreLogoURL(String value) {
		storeLogoURL = value;
	}

	public String getStoreLogoURL() {
		return storeLogoURL;
	}

	public void setPhone(String value) {
		phone = value;
	}

	public String getPhone() {
		return phone;
	}

	public void setLongitude(String value) {
		longitude = value;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setStoreID(String value) {
		storeID = value;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setState(String value) {
		state = value;
	}

	public String getState() {
		return state;
	}

	public Bitmap setLogo(String value) {
		storeLogoURL = value;
		try {
			URL url = new URL(value);
			InputStream content = (InputStream) url.getContent();
			logo = resize(BitmapFactory.decodeStream(content), 70);
		} catch (Exception e) {
			ExceptionHandler.handleSetLogoException(e);
		}
		setByteArrayLogo();
		return logo;
	}

	private void setByteArrayLogo() {
		ByteArrayOutputStream blob = new ByteArrayOutputStream();
		logo.compress(CompressFormat.PNG, 0, blob);
		logoByteArray = blob.toByteArray();
	}

	public void setLogo(Bitmap b) {
		logo = b;
	}

	public Bitmap getLogo() {
		if (logo == null)
			logo = BitmapFactory.decodeByteArray(logoByteArray, 0,
					logoByteArray.length);
		return logo;
	}

	public byte[] getLogoByteArray() {
		return logoByteArray;
	}

	private Bitmap resize(Bitmap bitmap, int newHeight) {
		double nw = (bitmap.getWidth() * newHeight) / bitmap.getHeight();
		Bitmap bitmapOrig = Bitmap.createScaledBitmap(bitmap, (int) nw,
				newHeight, false);
		return bitmapOrig;
	}

	public String getLongAddress() {
		return address + "\n" + city + ", " + state + " " + zipcode;
	}

	public String getCoordinate() {
		return latitude + ", " + longitude;
	}

	public String getLongName() {
		return name + " (ID:" + storeID + ")";
	}
}
