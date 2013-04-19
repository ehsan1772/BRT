package com.example.brtest.network;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;

public abstract class BRTGenericJSONParser<T> {
	
	protected String jSONString;
	protected String urlString;
	protected JSONObject jSONObject;
	protected JSONArray jSONArray;
	protected Activity activity;
	protected BRTJSONParser jParser;
	
	public BRTGenericJSONParser(String urlString) throws JSONException {
		jParser = new BRTJSONParser();
		this.urlString = urlString;
		jSONObject = jParser.getJSONFromUrl(urlString);
		this.jSONString = jSONObject.toString();
	}
	
	public BRTGenericJSONParser(String urlString, Activity activity) throws JSONException {
		this.activity = activity;
		jParser = new BRTJSONParser();
		this.urlString = urlString;
		jSONObject = jParser.getJSONFromUrl(urlString);
		this.jSONString = jSONObject.toString();
	}
	
	public void setArrayTag(String tag) throws JSONException {
		jSONArray = jSONObject.getJSONArray(tag);
	}
	
	public abstract List<T> getObjects();

}
