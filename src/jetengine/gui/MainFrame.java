package jetengine.gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.border.MatteBorder;
import javax.swing.JSplitPane;

public class MainFrame extends JFrame {
	public MainFrame() {
		//Eigenschaften MainFrame
		setTitle("JetEngine");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 1280, 720);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.GRAY);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		//Inhalte JDesktopPane
		JInternalFrame RegsFlags = new JInternalFrame("Register & Flags");
		RegsFlags.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		RegsFlags.setVisible(true);
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addComponent(RegsFlags, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1009, Short.MAX_VALUE))
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_desktopPane.createSequentialGroup()
					.addComponent(RegsFlags, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(394, Short.MAX_VALUE))
		);
		RegsFlags.getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Inhalte JInternalPane: RegsFlags
		JSplitPane splitPane = new JSplitPane();
		RegsFlags.getContentPane().add(splitPane, BorderLayout.CENTER);
		desktopPane.setLayout(gl_desktopPane);
		
		//Menu Bar + Inhalte
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFiles = new JMenu("Datei");
		menuBar.add(mnFiles);
		
		JMenuItem mntmFileOpen = new JMenuItem("Datei \u00F6ffnen");
		mnFiles.add(mntmFileOpen);
		
		JMenuItem mntmFileSave = new JMenuItem("Speichern");
		mnFiles.add(mntmFileSave);
		
		JMenuItem mntmExit = new JMenuItem("Beenden");
		mnFiles.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Hilfe");
		menuBar.add(mnHelp);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
