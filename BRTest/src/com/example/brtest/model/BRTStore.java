package com.example.brtest.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Parcel;

public class BRTStore implements Serializable {


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
	
	public BRTStore()
	{
//		logo = null;
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

			ByteArrayOutputStream blob = new ByteArrayOutputStream();
			logo.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, blob);
			logoByteArray = blob.toByteArray();
			
			storeLogoURL = value;
			return logo;

	}
	public void setLogo(Bitmap b)
	{
		logo = b;
	}
	public Bitmap getLogo()
	{
		if (logo == null)
			logo = BitmapFactory.decodeByteArray(logoByteArray , 0, logoByteArray.length);
		return logo;
	}
	

	private Bitmap resize(Bitmap bitmap, int newHeight) {
	    double nw = (bitmap.getWidth() * newHeight) / bitmap.getHeight();
	    Bitmap bitmapOrig = Bitmap.createScaledBitmap(bitmap, (int)nw, newHeight, false);
	    return bitmapOrig;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
    // Converts the Bitmap into a byte array for serialization
//    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
//        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
//        byte bitmapBytes[] = byteStream.toByteArray();
//        out.write(bitmapBytes, 0, bitmapBytes.length);
//    }
//
//    // Deserializes a byte array representing the Bitmap and decodes it
//    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
//        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//        int b;
//        while((b = in.read()) != -1)
//            byteStream.write(b);
//        byte bitmapBytes[] = byteStream.toByteArray();
//        bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
//    }
}
