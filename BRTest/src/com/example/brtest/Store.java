package com.example.brtest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Store {


	private String address;
	private String city;
	private String name;
	private String latitude;
	private String zipcode;
	private String storeLogoURL;
	private Bitmap logo;
	private String phone;
	private String longitude;
	private String storeID;
	private String state;
	
	public Store()
	{
		logo = null;
	}
	
	public void setAddress(String value)
	{
		address = value;
	}
	public String getAddress()
	{
		return address;
	}
	
	public void setCity(String value)
	{
		city = value;
	}
	public String getCity()
	{
		return city;
	}
	
	public void setName(String value)
	{
		name = value;
	}
	public String getName()
	{
		return name;
	}
	
	public void setLatitude(String value)
	{
		latitude = value;
	}
	public String getLatitude()
	{
		return latitude;
	}
	
	public void setZipcode(String value)
	{
		zipcode = value;
	}
	public String getZipcode()
	{
		return zipcode;
	}
	
	public void setStoreLogoURL(String value)
	{
		storeLogoURL = value;
	}
	public String getStoreLogoURL()
	{
		return storeLogoURL;
	}
	
	public void setPhone(String value)
	{
		phone = value;
	}
	public String getPhone()
	{
		return phone;
	}
	
	public void setLongitude(String value)
	{
		longitude = value;
	}
	public String getLongitude()
	{
		return longitude;
	}
	
	public void setStoreID(String value)
	{
		storeID = value;
	}
	public String getStoreID()
	{
		return storeID;
	}
	
	public void setState(String value)
	{
		state = value;
	}
	public String getState()
	{
		return state;
	}
	
	public Bitmap setLogo(String value)
	{
		
		URL url;
		
			try {
				url = new URL(value);
				InputStream content = (InputStream) url.getContent();			
				logo = resize(BitmapFactory.decodeStream(content), 70);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			storeLogoURL = value;
			return logo;

	}
	public void setLogo(Bitmap b)
	{
		logo = b;
	}
	public Bitmap getLogo()
	{
		return logo;
	}
	

	private Bitmap resize(Bitmap bitmap, int newHeight) {
	    double nw = (bitmap.getWidth() * newHeight) / bitmap.getHeight();
	    Bitmap bitmapOrig = Bitmap.createScaledBitmap(bitmap, (int)nw, newHeight, false);
	    return bitmapOrig;
	}
}
