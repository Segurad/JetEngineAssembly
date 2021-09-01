package jetengine.gui;

import java.awt.Toolkit;

import javax.swing.JFrame;

import jetengine.assets.Assets;

public class BaseFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BaseFrame(String name) {
		super(name);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(Assets.ICON_JETENGINE)));
		getContentPane().setBackground(ColorSet.boxOutColor);
	}

}
