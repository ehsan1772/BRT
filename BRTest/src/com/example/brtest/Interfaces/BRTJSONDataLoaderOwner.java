package com.example.brtest.Interfaces;

/**
 * This interface should be implemented by any class that is using the MyXMLLoader class
 * @author Ehsan Barekati
 *
 */
public interface BRTJSONDataLoaderOwner {
	/**
	 * This callback methods is to be invoked after successful completion 
	 * @param result The result of loading the XML
	 */
	void onPostJSONLoaderExecute(Object result);
	/**
	 * Gets invoked prior to execution to set up the UI
	 */
	void onPreJSONLoaderExecute();
}
