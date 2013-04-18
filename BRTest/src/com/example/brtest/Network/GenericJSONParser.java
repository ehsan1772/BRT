package com.example.brtest.Network;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.brtest.JSONParser;

import android.app.Activity;

public abstract class GenericJSONParser<T> {
	
	protected String jSONString;
	protected String urlString;
	protected JSONObject jSONObject;
	protected JSONArray jSONArray;
	protected Activity activity;
	protected JSONParser jParser;
	
	public GenericJSONParser(String urlString) throws JSONException {
		jParser = new JSONParser();
		this.urlString = urlString;
		jSONObject = jParser.getJSONFromUrl(urlString);
		this.jSONString = jSONObject.toString();
	}
	
	public GenericJSONParser(String urlString, Activity activity) throws JSONException {
		this.activity = activity;
		jParser = new JSONParser();
		this.urlString = urlString;
		jSONObject = jParser.getJSONFromUrl(urlString);
		this.jSONString = jSONObject.toString();
	}
	
	public void setArrayTag(String tag) throws JSONException {
		jSONArray = jSONObject.getJSONArray(tag);
	}
	
	public abstract List<T> getObjects();

}
