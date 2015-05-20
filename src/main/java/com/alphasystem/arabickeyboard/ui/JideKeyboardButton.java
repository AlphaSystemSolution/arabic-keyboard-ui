/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;
import static java.awt.event.InputEvent.SHIFT_MASK;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.KeyStroke;

import com.jidesoft.swing.JideButton;

/**
 * @author sali
 * 
 */
public class JideKeyboardButton extends JideButton {

	private static final long serialVersionUID = -8819113004568177610L;

	private KeyboardButtonAction defaultAction;

	private KeyboardButtonAction alternateAction;

	private int keyCode;

	public JideKeyboardButton(String defaultName, String alternateName,
			String actionCommand, KeyStroke keyStroke, int width, int height,
			boolean rightToLeft, KeyboardTarget target) {
		super();
		keyCode = keyStroke.getKeyCode();
		defaultAction = new KeyboardButtonAction(defaultName, actionCommand,
				keyStroke, target);
		setAction(defaultAction);
		alternateAction = new KeyboardButtonAction(alternateName,
				actionCommand, KeyStroke.getKeyStroke(keyStroke.getKeyCode(),
						SHIFT_MASK), target);
		if (rightToLeft) {
			setComponentOrientation(RIGHT_TO_LEFT);
		}
		setButtonStyle(TOOLBOX_STYLE);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		setHorizontalTextPosition(CENTER);
		Dimension dimension = new Dimension(width, height);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);
	}

	public KeyboardButtonAction getAlternateAction() {
		return alternateAction;
	}

	public KeyboardButtonAction getDefaultAction() {
		return defaultAction;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public void switchAction(boolean doSwitch) {
		KeyboardButtonAction a = doSwitch ? alternateAction : defaultAction;
		setAction(a);
	}

	/**
	 * @param font
	 * @return
	 */
	public JideKeyboardButton withFont(Font font) {
		if (font != null) {
			setFont(font);
		}
		return this;
	}

	/**
	 * @param keyListener
	 * @return
	 */
	public JideKeyboardButton withKeyListener(KeyListener keyListener) {
		if (keyListener != null) {
			addKeyListener(keyListener);
		}
		return this;
	}

	/**
	 * @param listener
	 * @return
	 */
	public JideKeyboardButton withMouseListener(MouseListener listener) {
		if (listener != null) {
			addMouseListener(listener);
		}
		return this;
	}

}
