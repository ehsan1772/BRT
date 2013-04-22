package com.example.brtest.network;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;

/**
 * This class is a generic abstract class that should be extended to parse JSON
 * feeds into objects
 * 
 * @author Ehsan Barekati
 * 
 * @param <T>
 *            The type of the object that JSON feed should be parsed to
 */
public abstract class BRTGenericJSONParser<T> {
	protected String jSONString;
	protected String urlString;
	protected JSONObject jSONObject;
	protected JSONArray jSONArray;
	protected Activity activity;

	public BRTGenericJSONParser(String urlString) throws JSONException {
		this.urlString = urlString;
		jSONObject = BRTJSONParser.getJSONFromUrl(urlString);
		jSONString = jSONObject.toString();
	}

	public BRTGenericJSONParser(String urlString, Activity activity)
			throws JSONException {
		this.activity = activity;
		this.urlString = urlString;
		jSONObject = BRTJSONParser.getJSONFromUrl(urlString);
		this.jSONString = jSONObject.toString();
	}

	public void setArrayTag(String tag) throws JSONException {
		jSONArray = jSONObject.getJSONArray(tag);
	}

	public abstract List<T> getObjects();
}
