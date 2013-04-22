package exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.util.Log;

/**
 * This class organizes the exceptions and handles them through static methods in a more organized way
 * @author Ehsan Barekati
 *
 */
public class ExceptionHandler {
	/**
	 * This method handles the exceptions that are caught when setting the logo
	 * @param e The caught exception
	 */
	public static void handleSetLogoException(Exception e) {
		if (e instanceof MalformedURLException)
			Log.e("setLogo exception : ", "MalformedURLException");
		if (e instanceof IOException)
			Log.e("setLogo exception : ", "IOException");
		Log.e("error description : ", e.toString());
		e.printStackTrace();
	}
	/**
	 * This method handles the exceptions that are caught when opening the file from the internal storage
	 * @param e Caught exception
	 */
	public static void handleFileException(Exception e) {
		if (e instanceof FileNotFoundException)
			Log.e("setLogo exception : ", "FileNotFoundException");
		if (e instanceof IOException)
			Log.e("setLogo exception : ", "IOException");
		Log.e("error description : ", e.toString());
		e.printStackTrace();
	}
	/**
	 * This method handles the exceptions that are caught when opening the file from the internal storage
	 * @param e Caught exception
	 */
	public static void handleGetJSONFromNetException(Exception e) {
		if (e instanceof UnsupportedEncodingException)
			Log.e("setLogo exception : ", "UnsupportedEncodingException");
		if (e instanceof JSONException)
			Log.e("setLogo exception : ", "JSONException");
		if (e instanceof ClientProtocolException)
			Log.e("setLogo exception : ", "ClientProtocolException");
		if (e instanceof IOException)
			Log.e("setLogo exception : ", "IOException");
		Log.e("error description : ", e.toString());
		e.printStackTrace();
	}
}
