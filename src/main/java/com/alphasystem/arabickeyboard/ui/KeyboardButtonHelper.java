/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

import static com.alphasystem.arabic.model.ArabicLetterType.AIN;
import static com.alphasystem.arabic.model.ArabicLetterType.ALIF;
import static com.alphasystem.arabic.model.ArabicLetterType.ALIF_HAMZA_ABOVE;
import static com.alphasystem.arabic.model.ArabicLetterType.ALIF_HAMZA_BELOW;
import static com.alphasystem.arabic.model.ArabicLetterType.ALIF_MADDAH;
import static com.alphasystem.arabic.model.ArabicLetterType.ALIF_MAKSURA;
import static com.alphasystem.arabic.model.ArabicLetterType.BA;
import static com.alphasystem.arabic.model.ArabicLetterType.DAL;
import static com.alphasystem.arabic.model.ArabicLetterType.DDAD;
import static com.alphasystem.arabic.model.ArabicLetterType.DTHA;
import static com.alphasystem.arabic.model.ArabicLetterType.FA;
import static com.alphasystem.arabic.model.ArabicLetterType.GHAIN;
import static com.alphasystem.arabic.model.ArabicLetterType.HA;
import static com.alphasystem.arabic.model.ArabicLetterType.HAMZA;
import static com.alphasystem.arabic.model.ArabicLetterType.HHA;
import static com.alphasystem.arabic.model.ArabicLetterType.JEEM;
import static com.alphasystem.arabic.model.ArabicLetterType.KAF;
import static com.alphasystem.arabic.model.ArabicLetterType.KHA;
import static com.alphasystem.arabic.model.ArabicLetterType.LAM;
import static com.alphasystem.arabic.model.ArabicLetterType.MEEM;
import static com.alphasystem.arabic.model.ArabicLetterType.NOON;
import static com.alphasystem.arabic.model.ArabicLetterType.QAF;
import static com.alphasystem.arabic.model.ArabicLetterType.RA;
import static com.alphasystem.arabic.model.ArabicLetterType.SAD;
import static com.alphasystem.arabic.model.ArabicLetterType.SEEN;
import static com.alphasystem.arabic.model.ArabicLetterType.SHEEN;
import static com.alphasystem.arabic.model.ArabicLetterType.TA;
import static com.alphasystem.arabic.model.ArabicLetterType.TATWEEL;
import static com.alphasystem.arabic.model.ArabicLetterType.TA_MARBUTA;
import static com.alphasystem.arabic.model.ArabicLetterType.THA;
import static com.alphasystem.arabic.model.ArabicLetterType.THAL;
import static com.alphasystem.arabic.model.ArabicLetterType.TTA;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW;
import static com.alphasystem.arabic.model.ArabicLetterType.WAW_HAMZA_ABOVE;
import static com.alphasystem.arabic.model.ArabicLetterType.YA;
import static com.alphasystem.arabic.model.ArabicLetterType.YA_HAMZA_ABOVE;
import static com.alphasystem.arabic.model.ArabicLetterType.ZAIN;
import static com.alphasystem.arabic.model.ArabicWord.getWord;
import static com.alphasystem.arabic.model.DiacriticType.DAMMA;
import static com.alphasystem.arabic.model.DiacriticType.DAMMATAN;
import static com.alphasystem.arabic.model.DiacriticType.FATHA;
import static com.alphasystem.arabic.model.DiacriticType.FATHATAN;
import static com.alphasystem.arabic.model.DiacriticType.KASRA;
import static com.alphasystem.arabic.model.DiacriticType.KASRATAN;
import static com.alphasystem.arabic.model.DiacriticType.SHADDA;
import static com.alphasystem.arabic.model.DiacriticType.SUKUN;
import static com.alphasystem.arabickeyboard.ui.FontUtilities.FONT_BOLD_24;
import static java.awt.Font.BOLD;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_B;
import static java.awt.event.KeyEvent.VK_BACK_SLASH;
import static java.awt.event.KeyEvent.VK_C;
import static java.awt.event.KeyEvent.VK_CLOSE_BRACKET;
import static java.awt.event.KeyEvent.VK_COMMA;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_E;
import static java.awt.event.KeyEvent.VK_F;
import static java.awt.event.KeyEvent.VK_G;
import static java.awt.event.KeyEvent.VK_H;
import static java.awt.event.KeyEvent.VK_I;
import static java.awt.event.KeyEvent.VK_J;
import static java.awt.event.KeyEvent.VK_K;
import static java.awt.event.KeyEvent.VK_L;
import static java.awt.event.KeyEvent.VK_M;
import static java.awt.event.KeyEvent.VK_N;
import static java.awt.event.KeyEvent.VK_O;
import static java.awt.event.KeyEvent.VK_OPEN_BRACKET;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_PERIOD;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_QUOTE;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_SEMICOLON;
import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.awt.event.KeyEvent.VK_SLASH;
import static java.awt.event.KeyEvent.VK_T;
import static java.awt.event.KeyEvent.VK_U;
import static java.awt.event.KeyEvent.VK_V;
import static java.awt.event.KeyEvent.VK_W;
import static java.awt.event.KeyEvent.VK_X;
import static java.awt.event.KeyEvent.VK_Y;
import static java.awt.event.KeyEvent.VK_Z;
import static java.lang.String.valueOf;
import static java.util.Collections.sort;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.KeyStroke;

/**
 * @author sali
 * 
 */
public class KeyboardButtonHelper {

	private static final Font SYSTEM_BUTTON_FONT = new Font("Georgia", BOLD, 12);

	public static final int DEFAULT_WIDTH = 48;

	public static final int DEFAULT_HEIGHT = 48;

	public static final int DEFAULT_DOUBLE_WIDTH = DEFAULT_WIDTH * 2 + 6;

	private static KeyboardButtonHelper instance;

	private static JideKeyboardButton createArabicKeyboardButton(
			String defaultName, String alternateName, String actionCommand,
			KeyStroke keyStroke, KeyboardTarget target, List<String> keys) {
		keys.add(actionCommand);
		return new JideKeyboardButton(defaultName, alternateName,
				actionCommand, keyStroke, DEFAULT_WIDTH, DEFAULT_HEIGHT, true,
				target).withFont(FONT_BOLD_24);
	}

	public static synchronized KeyboardButtonHelper getInstance() {
		return instance;
	}

	public static synchronized KeyboardButtonHelper getInstance(
			KeyboardTarget target) {
		if (instance == null) {
			instance = new KeyboardButtonHelper(target);
		}
		return instance;
	}

	private static KeyStroke getKeyStroke(int keyCode) {
		return getKeyStroke(keyCode, 0);
	}

	private static KeyStroke getKeyStroke(int keyCode, int modifiers) {
		return KeyStroke.getKeyStroke(keyCode, modifiers, true);
	}

	private final JideKeyboardButton[] buttonRow1;

	private final JideKeyboardButton[] buttonRow2;

	private final JideKeyboardButton[] buttonRow3;

	private final JideKeyboardButton shift1;

	private final JideKeyboardButton shift2;

	private final KeyboardTarget target;

	private final List<String> keys = new ArrayList<String>();

	/**
	 * Don't let anyone instantiate this class
	 */
	private KeyboardButtonHelper(KeyboardTarget target) {
		this.target = target == null ? new SystemOutKeyboardTarget() : target;

		shift1 = new JideKeyboardButton("Shift", "Shift",
				valueOf((char) VK_SHIFT), getKeyStroke(VK_SHIFT),
				DEFAULT_DOUBLE_WIDTH, DEFAULT_HEIGHT, false, null)
				.withFont(SYSTEM_BUTTON_FONT);

		shift2 = new JideKeyboardButton("Shift", "Shift",
				valueOf((char) VK_SHIFT), getKeyStroke(VK_SHIFT),
				DEFAULT_DOUBLE_WIDTH, DEFAULT_HEIGHT, false, null)
				.withFont(SYSTEM_BUTTON_FONT);

		buttonRow1 = new JideKeyboardButton[] {
				createArabicKeyboardButton(DDAD.toUnicode(), FATHA.toUnicode(),
						valueOf((char) VK_Q), getKeyStroke(VK_Q), target, keys),
				createArabicKeyboardButton(SAD.toUnicode(),
						FATHATAN.toUnicode(), valueOf((char) VK_W),
						getKeyStroke(VK_W), target, keys),
				createArabicKeyboardButton(THA.toUnicode(), DAMMA.toUnicode(),
						valueOf((char) VK_E), getKeyStroke(VK_E), target, keys),
				createArabicKeyboardButton(QAF.toUnicode(),
						DAMMATAN.toUnicode(), valueOf((char) VK_R),
						getKeyStroke(VK_R), target, keys),
				createArabicKeyboardButton(FA.toUnicode(),
						getWord(LAM, ALIF_HAMZA_BELOW).toUnicode(),
						valueOf((char) VK_T), getKeyStroke(VK_T), target, keys),
				createArabicKeyboardButton(GHAIN.toUnicode(),
						ALIF_HAMZA_BELOW.toUnicode(), valueOf((char) VK_Y),
						getKeyStroke(VK_Y), target, keys),
				createArabicKeyboardButton(AIN.toUnicode(), valueOf('\u2018'),
						valueOf((char) VK_U), getKeyStroke(VK_U), target, keys),
				createArabicKeyboardButton(HHA.toUnicode(), valueOf('\u00F7'),
						valueOf((char) VK_I), getKeyStroke(VK_I), target, keys),
				createArabicKeyboardButton(KHA.toUnicode(), valueOf('\u00D7'),
						valueOf((char) VK_O), getKeyStroke(VK_O), target, keys),
				createArabicKeyboardButton(HA.toUnicode(), valueOf('\u061B'),
						valueOf((char) VK_P), getKeyStroke(VK_P), target, keys),
				createArabicKeyboardButton(JEEM.toUnicode(), valueOf('\u007B'),
						valueOf((char) VK_OPEN_BRACKET),
						getKeyStroke(VK_OPEN_BRACKET), target, keys),
				createArabicKeyboardButton(DAL.toUnicode(), valueOf('\u007D'),
						valueOf((char) VK_CLOSE_BRACKET),
						getKeyStroke(VK_CLOSE_BRACKET), target, keys),
				createArabicKeyboardButton(THAL.toUnicode(),
						SHADDA.toUnicode(), valueOf((char) VK_BACK_SLASH),
						getKeyStroke(VK_BACK_SLASH), target, keys) };

		buttonRow2 = new JideKeyboardButton[] {
				createArabicKeyboardButton(SHEEN.toUnicode(),
						valueOf('\u005C\u005C'), valueOf((char) VK_A),
						getKeyStroke(VK_A), target, keys),
				createArabicKeyboardButton(SEEN.toUnicode(), valueOf(' '),
						valueOf((char) VK_S), getKeyStroke(VK_S), target, keys),
				createArabicKeyboardButton(YA.toUnicode(), valueOf('\u005D'),
						valueOf((char) VK_D), getKeyStroke(VK_D), target, keys),
				createArabicKeyboardButton(BA.toUnicode(), valueOf('\u005B'),
						valueOf((char) VK_F), getKeyStroke(VK_F), target, keys),
				createArabicKeyboardButton(LAM.toUnicode(),
						getWord(LAM, ALIF_HAMZA_ABOVE).toUnicode(),
						valueOf((char) VK_G), getKeyStroke(VK_G), target, keys),
				createArabicKeyboardButton(ALIF.toUnicode(),
						ALIF_HAMZA_ABOVE.toUnicode(), valueOf((char) VK_H),
						getKeyStroke(VK_H), target, keys),
				createArabicKeyboardButton(TA.toUnicode(), TATWEEL.toUnicode(),
						valueOf((char) VK_J), getKeyStroke(VK_J), target, keys),
				createArabicKeyboardButton(NOON.toUnicode(), valueOf('\u060C'),
						valueOf((char) VK_K), getKeyStroke(VK_K), target, keys),
				createArabicKeyboardButton(MEEM.toUnicode(), valueOf('\u002F'),
						valueOf((char) VK_L), getKeyStroke(VK_L), target, keys),
				createArabicKeyboardButton(KAF.toUnicode(),
						valueOf((char) VK_SEMICOLON), valueOf('\u003A'),
						getKeyStroke(VK_SEMICOLON), target, keys),
				createArabicKeyboardButton(TTA.toUnicode(), valueOf('\u0022'),
						valueOf((char) VK_QUOTE), getKeyStroke(VK_QUOTE),
						target, keys) };

		buttonRow3 = new JideKeyboardButton[] {
				createArabicKeyboardButton(YA_HAMZA_ABOVE.toUnicode(),
						valueOf('\u007E'), valueOf((char) VK_Z),
						getKeyStroke(VK_Z), target, keys),
				createArabicKeyboardButton(HAMZA.toUnicode(),
						SUKUN.toUnicode(), valueOf((char) VK_X),
						getKeyStroke(VK_X), target, keys),
				createArabicKeyboardButton(WAW_HAMZA_ABOVE.toUnicode(),
						KASRA.toUnicode(), valueOf((char) VK_C),
						getKeyStroke(VK_C), target, keys),
				createArabicKeyboardButton(RA.toUnicode(),
						KASRATAN.toUnicode(), valueOf((char) VK_V),
						getKeyStroke(VK_V), target, keys),
				createArabicKeyboardButton(getWord(LAM, ALIF).toUnicode(),
						getWord(LAM, ALIF_MADDAH).toUnicode(),
						valueOf((char) VK_B), getKeyStroke(VK_B), target, keys),
				createArabicKeyboardButton(ALIF_MAKSURA.toUnicode(),
						ALIF_MADDAH.toUnicode(), valueOf((char) VK_N),
						getKeyStroke(VK_N), target, keys),
				createArabicKeyboardButton(TA_MARBUTA.toUnicode(),
						valueOf('\u2019'), valueOf((char) VK_M),
						getKeyStroke(VK_M), target, keys),
				createArabicKeyboardButton(WAW.toUnicode(), valueOf('\u002C'),
						valueOf((char) VK_COMMA), getKeyStroke(VK_COMMA),
						target, keys),
				createArabicKeyboardButton(ZAIN.toUnicode(), valueOf('\u002E'),
						valueOf((char) VK_PERIOD), getKeyStroke(VK_PERIOD),
						target, keys),
				createArabicKeyboardButton(DTHA.toUnicode(), valueOf('\u061F'),
						valueOf((char) VK_SLASH), getKeyStroke(VK_SLASH),
						target, keys) };

		keys.add("|");
		keys.add("'");
		keys.add("?");
		sort(keys);
	}

	public JideKeyboardButton[] getButtonRow1() {
		return buttonRow1;
	}

	public JideKeyboardButton[] getButtonRow2() {
		return buttonRow2;
	}

	public JideKeyboardButton[] getButtonRow3() {
		return buttonRow3;
	}

	public List<String> getKeys() {
		return keys;
	}

	public JideKeyboardButton getShift1() {
		return shift1;
	}

	public JideKeyboardButton getShift2() {
		return shift2;
	}

	public KeyboardTarget getTarget() {
		return target;
	}

}
