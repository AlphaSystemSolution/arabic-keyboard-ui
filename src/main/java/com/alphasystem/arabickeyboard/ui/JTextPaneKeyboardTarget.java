/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

import javax.swing.JTextPane;

/**
 * @author sali
 * 
 */
public class JTextPaneKeyboardTarget extends
		JTextComponentKeyboardTarget<JTextPane> {

	/**
	 * @param previewPane
	 * @throws NullPointerException
	 */
	public JTextPaneKeyboardTarget(JTextPane previewPane)
			throws NullPointerException {
		super(previewPane);
	}

}
