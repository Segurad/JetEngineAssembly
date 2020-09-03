package jetengine.gui;

import java.awt.Toolkit;

import javax.swing.JFrame;

import jetengine.sys.Message;

final class JetEngineAboud extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JetEngineAboud() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(ColorSet.boxOutColor);
		setTitle(Message.JETENGINE+"Aboud");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/jetengine/icon.png")));
		pack();
	}

}
