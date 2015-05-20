/**
 * 
 */
package com.alphasystem.arabickeyboard.ui;

import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;

import java.awt.Font;

/**
 * @author sali
 * 
 */
public final class FontUtilities {

	public static final String FONT_NAME = "Traditional Arabic";

	public static final Font FONT_BOLD_14 = new Font(FONT_NAME, BOLD, 14);

	public static final Font FONT_BOLD_16 = FONT_BOLD_14.deriveFont(16.0F);

	public static final Font FONT_BOLD_20 = FONT_BOLD_16.deriveFont(20.0F);

	public static final Font FONT_BOLD_24 = FONT_BOLD_20.deriveFont(24.0F);

	public static final Font FONT_BOLD_36 = FONT_BOLD_20.deriveFont(36.0F);

	public static final Font FONT_PLAIN_16 = FONT_BOLD_20.deriveFont(PLAIN,
			16.0F);

	public static final Font FONT_PLAIN_20 = FONT_PLAIN_16.deriveFont(20.0F);
	
	public static final Font FONT_PLAIN_24 = FONT_PLAIN_16.deriveFont(20.0F);
	
	public static final Font FONT_PLAIN_30 = FONT_PLAIN_16.deriveFont(30.0F);

	public static final Font FONT_PLAIN_36 = FONT_PLAIN_16.deriveFont(36.0F);
}
