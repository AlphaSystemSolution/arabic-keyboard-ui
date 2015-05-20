/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.DiacriticType;

/**
 * @author sali
 *
 */
public interface KeyboardTarget {

	/**
	 * @param arabicLetter
	 */
	public void insertLetter(ArabicLetterType arabicLetter);
	
	/**
	 * @param unicode
	 */
	public void insertLetter(char unicode);
	
	/**
	 * @param diacriticType
	 */
	public void insertLetter(DiacriticType diacriticType);

	/**
	 * @param unicode
	 */
	public void insertLetter(String unicode);
}
