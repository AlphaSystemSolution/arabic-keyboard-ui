/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

import java.util.Observable;

/**
 * @author sali
 * 
 */
public class ObservableObject extends Observable {

	private static ObservableObject instance;

	public static synchronized ObservableObject getInstance() {
		if (instance == null) {
			instance = new ObservableObject();
		}
		return instance;
	}

	/**
	 * 
	 */
	private ObservableObject() {
	}

	/**
	 * @param context
	 */
	public void notifyKeyEvent(ObservableObjectContext context) {
		setChanged();
		notifyObservers(context);
	}

}
