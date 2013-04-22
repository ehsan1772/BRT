package com.example.brtest.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import exceptions.ExceptionHandler;

/**
 * This class has one public static method that gets a url and returns a JSON object
 * 
 * @author Ehsan Barekati
 * 
 */
public class BRTJSONParser {

	public static JSONObject getJSONFromUrl(String url) {
		try {
			InputStream inputStream = getInputStream(url);
			String jsonString = inputStreamToString(inputStream);
			JSONObject jsonObject = new JSONObject(jsonString);
			return jsonObject;
		} catch (Exception e) {
			ExceptionHandler.handleGetJSONFromNetException(e);
		}
		return null;
	}

	private static InputStream getInputStream(String url) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		HttpEntity httpEntity = httpResponse.getEntity();
		InputStream inputStream = httpEntity.getContent();
		return inputStream;
	}

	private static String inputStreamToString(InputStream inputStream)
			throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream, "iso-8859-1"), 8);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		inputStream.close();
		String jsonString = sb.toString();
		return jsonString;
	}
}
