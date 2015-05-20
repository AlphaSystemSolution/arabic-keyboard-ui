/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

import static com.alphasystem.arabic.model.ArabicWord.fromUnicode;
import static com.alphasystem.arabickeyboard.ui.FontUtilities.FONT_BOLD_36;
import static com.alphasystem.arabickeyboard.ui.KeyboardButtonHelper.DEFAULT_DOUBLE_WIDTH;
import static com.alphasystem.arabickeyboard.ui.KeyboardButtonHelper.DEFAULT_HEIGHT;
import static com.jidesoft.swing.ButtonStyle.TOOLBOX_STYLE;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BorderFactory.createEtchedBorder;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.SwingUtilities.invokeLater;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.ui.AbstractComponentAction;
import com.jidesoft.swing.JideButton;

/**
 * @author sali
 * 
 */
public class ArabicKeyboardFrame extends JFrame {

	private static final long serialVersionUID = -5265527604836253655L;

	public static void main(String[] args) {
		final ArabicKeyboardFrame frame = new ArabicKeyboardFrame();
		invokeLater(new Runnable() {

			@Override
			public void run() {
				frame.setVisible(true);
				frame.keyboarPanel.shiftFocus();
			}
		});
	}

	private static void removeHighlights(JTextComponent textComp) {
		Highlighter hilite = textComp.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();

		for (int i = 0; i < hilites.length; i++) {
			hilite.removeHighlight(hilites[i]);
		}
	}

	private ArabicJideKeyboardPanel keyboarPanel;

	public ArabicKeyboardFrame() {
		super("Arabic Keyboard");

		JPanel panel = createTextPanel();

		JPanel contentPanel = new JPanel(new BorderLayout());
		keyboarPanel = new ArabicJideKeyboardPanel();
		contentPanel.add(keyboarPanel, CENTER);
		contentPanel.add(panel, SOUTH);
		setContentPane(contentPanel);
		pack();
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JideButton createButton(Action action, int width, int height) {
		JideButton button = new JideButton(action);
		button.setButtonStyle(TOOLBOX_STYLE);
		Dimension dimension = new Dimension(width, height);
		button.setPreferredSize(dimension);
		button.setMaximumSize(dimension);
		button.setMinimumSize(dimension);
		return button;
	}

	private JPanel createTextPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		final JTextPane previewPane = new JTextPane();
		previewPane.setFont(FONT_BOLD_36);
		previewPane.setComponentOrientation(RIGHT_TO_LEFT);
		JScrollPane scrollPane = new JScrollPane(previewPane);
		JTextComponentKeyboardTarget<JTextPane> target = new JTextComponentKeyboardTarget<JTextPane>(
				previewPane);
		@SuppressWarnings("unused")
		KeyboardButtonHelper buttonHelper = KeyboardButtonHelper
				.getInstance(target);
		panel.add(scrollPane, CENTER);

		JPanel buttonPanel = new JPanel();

		JideButton button = createButton(new AbstractComponentAction("Copy",
				"copy") {

			private static final long serialVersionUID = -5692965647020352486L;

			@Override
			public void actionPerformed(ActionEvent e) {
				previewPane.selectAll();
				previewPane.copy();
				removeHighlights(previewPane);
			}
		}, DEFAULT_DOUBLE_WIDTH, DEFAULT_HEIGHT);
		buttonPanel.add(button);

		button = createButton(new AbstractComponentAction("Paste", "paste") {

			private static final long serialVersionUID = -5692965647020352486L;

			@Override
			public void actionPerformed(ActionEvent e) {
				previewPane.paste();
			}
		}, DEFAULT_DOUBLE_WIDTH, DEFAULT_HEIGHT);
		buttonPanel.add(button);

		button = createButton(new AbstractComponentAction(
				"Show Buck Walter Encoding", "showBuckWalterEncoding") {

			private static final long serialVersionUID = -1182551830650525981L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = previewPane.getText();
				if (!isBlank(text)) {
					ArabicWord aw = fromUnicode(text);
					showInputDialog(ArabicKeyboardFrame.this,
							"BuckWalter Encoding", null,
							JOptionPane.INFORMATION_MESSAGE, null, null,
							aw.toBuckWalter());
				}
			}
		}, DEFAULT_DOUBLE_WIDTH + 100, DEFAULT_HEIGHT);
		buttonPanel.add(button);

		panel.add(buttonPanel, SOUTH);

		panel.setBorder(BorderFactory.createCompoundBorder(
				createEmptyBorder(3, 3, 3, 3), createEtchedBorder()));
		return panel;
	}

}
