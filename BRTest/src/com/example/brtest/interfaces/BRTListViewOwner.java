package com.example.brtest.interfaces;

/**
 * This interface should be implemented by any activity that hosts MyListView
 * class
 * 
 * @author Ehsan Barekati
 * 
 */
public interface BRTListViewOwner {
	/**
	 * 
	 * @param position
	 *            an integer representing the clicked row
	 * @return The object that's represented by the clicked view
	 */
	Object getClickedItem(int position);

	/**
	 * Callback method that should be invoke to request an item deletion
	 * 
	 * @param position
	 *            position of the row to be deleted
	 */
	void deleteClickedItem(int position);
}
