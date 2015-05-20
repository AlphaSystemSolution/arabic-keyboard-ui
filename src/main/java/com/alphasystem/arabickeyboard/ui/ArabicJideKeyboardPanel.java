/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

import static com.alphasystem.ui.util.SpringUtilities.makeCompactGrid;
import static java.awt.BorderLayout.CENTER;
import static java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager;
import static java.awt.event.KeyEvent.KEY_PRESSED;
import static java.awt.event.KeyEvent.KEY_TYPED;
import static java.lang.String.format;
import static javax.swing.BorderFactory.createCompoundBorder;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BorderFactory.createEtchedBorder;
import static javax.swing.SwingUtilities.replaceUIActionMap;
import static javax.swing.SwingUtilities.replaceUIInputMap;

import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.plaf.ActionMapUIResource;

import org.apache.commons.lang3.ArrayUtils;

import com.alphasystem.ui.AbstractComponentAction;

/**
 * @author sali
 * 
 */
public class ArabicJideKeyboardPanel extends JPanel implements Observer {

	private static final long serialVersionUID = -8707711710538027716L;

	static {
		getCurrentKeyboardFocusManager().addKeyEventDispatcher(
				new KeyEventDispatcher() {
					public boolean dispatchKeyEvent(KeyEvent e) {
						int eventType = e.getID();
						if (eventType == KEY_TYPED) {
							return false;
						}
						int keyCode = e.getKeyCode();
						ObservableObject oo = ObservableObject.getInstance();
						boolean shiftDown = e.isShiftDown();
						oo.notifyKeyEvent(new ObservableObjectContext(
								"showButtonSelected", keyCode,
								eventType == KEY_PRESSED, shiftDown));
						return false;
					}
				});
	}

	private static void transferFocus(JideKeyboardButton[] buttons) {
		for (JideKeyboardButton button : buttons) {
			button.transferFocus();
		}
	}

	private ActionMap actionMap = new ActionMapUIResource();

	private KeyboardButtonHelper buttonHelper;

	public ArabicJideKeyboardPanel() {
		super(new BorderLayout());

		buttonHelper = KeyboardButtonHelper.getInstance();

		ObservableObject oo = ObservableObject.getInstance();
		oo.addObserver(this);

		JideKeyboardButton shift1 = buttonHelper.getShift1();
		JideKeyboardButton shift2 = buttonHelper.getShift2();
		JideKeyboardButton[] buttonRow1 = buttonHelper.getButtonRow1();
		JideKeyboardButton[] buttonRow2 = buttonHelper.getButtonRow2();
		JideKeyboardButton[] buttonRow3 = buttonHelper.getButtonRow3();

		populateActionMap(shift1);
		populateActionMap(shift2);
		populateActionMap(buttonRow1);
		populateActionMap(buttonRow2);
		populateActionMap(buttonRow3);

		int rows = 0;
		int cols = 1;

		JPanel contentPanel = new JPanel(new SpringLayout());

		JideKeyboardButton[] buttons = null;

		contentPanel.add(createButtonPanel(buttonRow1));
		rows++;

		contentPanel.add(createButtonPanel(buttonRow2));
		rows++;

		buttons = ArrayUtils.add(buttonRow3, 0, shift1);
		buttons = ArrayUtils.add(buttons, shift2);
		contentPanel.add(createButtonPanel(buttons));
		rows++;

		makeCompactGrid(contentPanel, rows, cols);

		add(contentPanel, CENTER);
		setBorder(createCompoundBorder(createEmptyBorder(3, 3, 3, 3),
				createEtchedBorder()));
	}

	private JPanel createButtonPanel(JButton[] buttons) {
		JPanel panel = new JPanel(new SpringLayout());

		for (JButton button : buttons) {
			panel.add(button);
		}

		makeCompactGrid(panel, 1, buttons.length);
		return panel;
	}

	public void showButtonSelected(ObservableObjectContext context) {
		boolean shiftDown = context.isShiftDown();
		JideKeyboardButton[] buttonRow1 = buttonHelper.getButtonRow1();
		JideKeyboardButton[] buttonRow2 = buttonHelper.getButtonRow2();
		JideKeyboardButton[] buttonRow3 = buttonHelper.getButtonRow3();
		buttonHelper.getShift1().setSelected(shiftDown);
		buttonHelper.getShift2().setSelected(shiftDown);
		switchAction(context, buttonRow1);
		switchAction(context, buttonRow2);
		switchAction(context, buttonRow3);
	}

	private void populateActionMap(InputMap keyMap,
			AbstractComponentAction action) {
		KeyStroke keyStroke = action.getKeyStroke();
		String actionMapKey = format("action_%s_%s", keyStroke.getKeyCode(),
				keyStroke.getModifiers());
		keyMap.put(keyStroke, actionMapKey);
		actionMap.put(actionMapKey, action);
	}

	private void populateActionMap(final JideKeyboardButton jideKeyboardButton) {
		InputMap keyMap = new ComponentInputMap(jideKeyboardButton);
		populateActionMap(keyMap, jideKeyboardButton.getDefaultAction());
		populateActionMap(keyMap, jideKeyboardButton.getAlternateAction());
		replaceUIActionMap(jideKeyboardButton, actionMap);
		replaceUIInputMap(jideKeyboardButton, WHEN_IN_FOCUSED_WINDOW, keyMap);
	}

	private void populateActionMap(JideKeyboardButton[] buttons) {
		for (JideKeyboardButton button : buttons) {
			populateActionMap(button);
		}
	}

	public void selectButton(String actionCommand, boolean select) {
		buttonHelper.getShift1().setSelected(select);
	}

	public void shiftFocus() {
		transferFocus(buttonHelper.getButtonRow1());
		transferFocus(buttonHelper.getButtonRow2());
		transferFocus(buttonHelper.getButtonRow3());
		buttonHelper.getShift1().transferFocus();
		buttonHelper.getShift2().transferFocus();
	}

	private void switchAction(ObservableObjectContext context,
			JideKeyboardButton[] buttons) {
		boolean shiftDown = context.isShiftDown();
		for (int i = 0; i < buttons.length; i++) {
			JideKeyboardButton jideKeyboardButton = buttons[i];
			jideKeyboardButton.switchAction(shiftDown);
			if (jideKeyboardButton.getKeyCode() == context.getKeyCode()) {
				jideKeyboardButton.setSelected(context.isSelected());
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg == null) {
			return;
		}
		ObservableObjectContext context = (ObservableObjectContext) arg;
		String command = context.getCommand();
		try {
			Method method = getClass().getMethod(command,
					ObservableObjectContext.class);
			method.invoke(this, context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
