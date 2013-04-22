package com.example.brtest.network;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.example.brtest.exceptions.ExceptionHandler;
import com.example.brtest.model.BRTStore;
import android.app.Activity;
import android.content.Context;

/**
 * This class stores the data from the JSON data feed and reopens it on
 * subsequent launches
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTCacheManager {
	private final static String fileName = "brtstores.brt";
	public static Activity activity;

	@SuppressWarnings("unchecked")
	public static ArrayList<BRTStore> openStoreList() {
		try {
			File filePath = activity.getFileStreamPath(fileName);
			FileInputStream file_input_stream = new FileInputStream(filePath);
			ObjectInputStream ois = new ObjectInputStream(file_input_stream);
			ArrayList<BRTStore> stores = (ArrayList<BRTStore>) ois.readObject();
			return stores;
		} catch (Exception e) {
			ExceptionHandler.handleFileException(e);
			return null;
		}
	}

	public static void saveStoreList(List<BRTStore> stores) {
		try {
			final FileOutputStream fos = activity.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = new ObjectOutputStream(bos);
			out.writeObject(stores);
			byte[] buf = bos.toByteArray();
			fos.write(buf);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			ExceptionHandler.handleFileException(e);
		}
	}

	public static boolean deleteStoreList() {
		File file = activity.getFileStreamPath(fileName);
		return file.delete();
	}
}
