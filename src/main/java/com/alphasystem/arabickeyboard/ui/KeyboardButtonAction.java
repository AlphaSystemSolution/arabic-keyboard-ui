/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

import java.awt.event.ActionEvent;

import javax.swing.KeyStroke;

import com.alphasystem.ui.AbstractComponentAction;

/**
 * @author sali
 * 
 */
public class KeyboardButtonAction extends AbstractComponentAction {

	private static final long serialVersionUID = -3264615308161274119L;

	private final KeyboardTarget target;

	public KeyboardButtonAction(String name, String actionCommand,
			KeyStroke keyStroke, KeyboardTarget target) {
		super(name);
		setActionCommand(name);
		setKeyStroke(keyStroke);
		this.target = target;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == null) {
			System.out.println("HERE");
			return;
		}
		final String name = (String) getValue(NAME);
		if (JideKeyboardButton.class.isAssignableFrom(source.getClass())) {
			if (target != null && name != null) {
				target.insertLetter(name);
			}
		}
	}

}
