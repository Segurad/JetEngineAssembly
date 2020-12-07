package jetengine.sys;

import java.awt.GraphicsEnvironment;

public class Config {
	
	public static final String verison = "v0.0.14";
	public static int editorFont;
	public static int editorFontSize;
	public static String editorFontName;
	
	static {
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int font = 1;
		for (int i = 0; i < fonts.length; i++) {
			if (fonts[i].equals("Consolas")) {
				font = i;
				break;
			} else if (fonts[i].equals("Sans Serif")) {
				font = i;
			}
		}
		if (font > -1)
		editorFont = font;
		editorFontName = fonts[font];
		editorFontSize = 16;
	}
}
