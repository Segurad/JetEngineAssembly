package jetengine.sys;

import java.awt.SplashScreen;

import jetengine.gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		SplashScreen splash = SplashScreen.getSplashScreen();
		if (splash != null) {
			splash.createGraphics();
		}
		new SystemHandler();
		new MainFrame().setVisible(true);
		if (splash != null) splash.close();
	}
}
