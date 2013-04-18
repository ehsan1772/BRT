package com.example.brtest.Network;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.brtest.Store;

import android.app.Activity;
import android.content.Context;

public class CacheManager {

	public static Activity activity;
	private final static String fileName = "brtstores";
	public static ArrayList<Store> openStoreList()
	{
	 try{
		File filePath = activity.getFileStreamPath(fileName + ".str");
		FileInputStream file_input_stream = new FileInputStream(filePath);
		ObjectInputStream ois = new ObjectInputStream(file_input_stream);
		ArrayList<Store> g= (ArrayList<Store>) ois.readObject();
		return g;
	 } catch (Exception e) {
		 return null;
	 }
	 
	}
	
	public static void saveStoreList(List<Store> stores)
	{
		try
		{
		final FileOutputStream fos = activity.openFileOutput(fileName + ".str", Context.MODE_PRIVATE);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		 ObjectOutput out = new ObjectOutputStream(bos); 
         out.writeObject(stores);
         byte[] buf = bos.toByteArray(); 
		//bitmap.compress(CompressFormat.JPEG, 100, fos);
         fos.write(buf);
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
	
}