/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

/**
 * @author sali
 * 
 */
public class SystemOutKeyboardTarget extends DefaultKeyboardTarget {

	@Override
	public void insertLetter(String unicode) {
		System.out.print(unicode);
	}
}
