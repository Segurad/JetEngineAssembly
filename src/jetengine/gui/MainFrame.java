package jetengine.gui;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {
	public MainFrame() {
		
		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(10);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setDividerSize(10);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setDividerSize(10);
		splitPane_1.setLeftComponent(splitPane_2);
		splitPane_2.setDividerLocation(400);
		splitPane_1.setDividerLocation(400);
		splitPane.setDividerLocation(150);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		pack();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
