package com.example.brtest.model;

public class BRTStoreDisplay {

	private static byte[] bitmapdata;
	private static String longAddress;
	private static String longName;
	private static String coordinate;
	private static String phone;
	public static byte[] getBitmapdata() {
		return bitmapdata;
	}
	public static void setBitmapdata(byte[] bitmapdata) {
		BRTStoreDisplay.bitmapdata = bitmapdata;
	}
	public static String getLongAddress() {
		return longAddress;
	}
	public static void setLongAddress(String longAddress) {
		BRTStoreDisplay.longAddress = longAddress;
	}
	public static String getLongName() {
		return longName;
	}
	public static void setLongName(String longName) {
		BRTStoreDisplay.longName = longName;
	}
	public static String getPhone() {
		return phone;
	}
	public static void setPhone(String phone) {
		BRTStoreDisplay.phone = phone;
	}
	public static String getCoordinate() {
		return coordinate;
	}
	public static void setCoordinate(String coordinate) {
		BRTStoreDisplay.coordinate = coordinate;
	}
}
