/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

import static java.lang.String.valueOf;
import static javax.swing.SwingUtilities.invokeLater;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.DiacriticType;

/**
 * @author sali
 * 
 */
public class JTextComponentKeyboardTarget<T extends JTextComponent> extends
		DefaultKeyboardTarget {

	private T previewPane;

	/**
	 * @param previewPane
	 * @throws NullPointerException
	 *             if <code>previewPane</code> is null.
	 */
	public JTextComponentKeyboardTarget(T previewPane)
			throws NullPointerException {
		if (previewPane == null) {
			throw new NullPointerException("preview pane is not initialized");
		}
		this.previewPane = previewPane;
		Document document = this.previewPane.getDocument();
		document.addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				final KeyboardButtonHelper buttonHelper = KeyboardButtonHelper
						.getInstance();
				final Document doc = (Document) e.getDocument();
				final int length = e.getLength();
				final int offset = e.getOffset();
				invokeLater(new Runnable() {

					@Override
					public void run() {
						String text = null;
						try {
							text = doc.getText(offset, length);
						} catch (BadLocationException ex) {
						}
						if (text != null
								&& buttonHelper.getKeys().contains(
										text.toUpperCase())) {
							try {
								doc.remove(offset, length);
							} catch (BadLocationException ex) {
							}
						}
					}
				});
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}

		});
	}

	@Override
	public void insertLetter(ArabicLetterType arabicLetterType) {
		if (arabicLetterType != null) {
			updateDocument(valueOf(arabicLetterType.getUnicode()));
		}
	}

	@Override
	public void insertLetter(char unicode) {
		updateDocument(valueOf(unicode));
	}

	@Override
	public void insertLetter(DiacriticType diacriticType) {
		if (diacriticType != null) {
			updateDocument(valueOf(diacriticType.getUnicode()));
		}
	}

	@Override
	public void insertLetter(String unicode) {
		updateDocument(unicode);
	}

	private void updateDocument(String s) {
		Document document = previewPane.getDocument();
		try {
			document.insertString(previewPane.getCaretPosition(), s, null);
		} catch (BadLocationException e) {
		}
	}
}
