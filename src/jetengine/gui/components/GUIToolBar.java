package jetengine.gui.components;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import jetengine.assets.Assets;
import jetengine.gui.ColorSet;
import jetengine.gui.MainFrame;

public final class GUIToolBar extends JToolBar {
	
	private final AbstractGUIComponent master;
	
	public GUIToolBar() {
		this(null);
	}
	
	public GUIToolBar(AbstractGUIComponent panel) {
		this(panel, false);
	}
	
	public GUIToolBar(AbstractGUIComponent panel, boolean options) {
		master=panel;
		setBackground(ColorSet.boxOutColor);
		setBorder(null);
		setFloatable(false);
		setRollover(true);
		
		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		
		if (options) {
			JButton btnOptions = new JButton();
			btnOptions.setIcon(new ImageIcon(GUIToolBar.class.getResource(Assets.ICON_OPTION)));
			btnOptions.setOpaque(false);
			btnOptions.setBorder(border);
	        btnOptions.setToolTipText("Options");
	        btnOptions.setFocusable(false);
			btnOptions.addActionListener((e) -> {
					master.openOptions();
			});
			add(btnOptions);
		}
		
		JPanel mainseparator = new JPanel();
		mainseparator.setBackground(ColorSet.boxOutColor);
        add(mainseparator);
		
		JButton btnDetach = new JButton();
		btnDetach.setIcon(new ImageIcon(GUIToolBar.class.getResource(Assets.ICON_DETACH)));
		btnDetach.setOpaque(false);
		btnDetach.setBorder(border);
        btnDetach.setToolTipText("Detach/Attach");
        btnDetach.setFocusable(false);
		btnDetach.addActionListener((e) -> {
				master.openInWindow();
		});
        
        
		JButton btnOrg = new JButton();
		btnOrg.setIcon(new ImageIcon(GUIToolBar.class.getResource(Assets.ICON_ORG)));
		btnOrg.setBorder(border);
        btnOrg.setOpaque(false);
        btnOrg.setToolTipText("Organisation");
        btnOrg.setFocusable(false);
        
        JButton btnX = new JButton();
		btnX.setIcon(new ImageIcon(GUIToolBar.class.getResource(Assets.ICON_CLOSE)));
		btnX.setBorder(border);
		btnX.setOpaque(false);
        btnX.setToolTipText("Close");
        btnX.setFocusable(false);
        btnX.addActionListener((e) -> {
				System.out.println(" try close " + master.getFrameName());
				master.close();
		});
        
        add(btnDetach);
        add(btnOrg);
        add(btnX);
		
		
        JPopupMenu popupMenu = new JPopupMenu();
		
		JMenuItem mntmMoveLeft = new JMenuItem("Move left");
		popupMenu.add(mntmMoveLeft);
		mntmMoveLeft.addActionListener((e) -> {
			MainFrame.getInstance().addComponentLeft(master);
		});
		
		JMenuItem mntmMoveRight = new JMenuItem("Move right");
		popupMenu.add(mntmMoveRight);
		mntmMoveRight.addActionListener((e) -> {
			MainFrame.getInstance().addComponentRight(master);
		});
		
		JMenuItem mntmMoveUp = new JMenuItem("Move up");
		popupMenu.add(mntmMoveUp);
		mntmMoveUp.addActionListener((e) -> {
				MainFrame.getInstance().addComponentUp(master);
		});
		
		JMenuItem mntmMoveDown = new JMenuItem("Move down");
		popupMenu.add(mntmMoveDown);
		mntmMoveDown.addActionListener((e) -> {
			MainFrame.getInstance().addComponentDown(master);
		});
		mntmMoveLeft.addActionListener((e) -> {
			MainFrame.getInstance().addComponentLeft(master);
		});
		
		JSeparator separator = new JSeparator();
		popupMenu.add(separator);
		
		JMenuItem mntmMoveTabLeft = new JMenuItem("Move tab left");
		popupMenu.add(mntmMoveTabLeft);
		mntmMoveTabLeft.addActionListener((e) -> {
			master.moveTabLeft();
		});
		
		JMenuItem mntmMoveTabRight = new JMenuItem("Move tab right");
		popupMenu.add(mntmMoveTabRight);
		mntmMoveTabRight.addActionListener((e) -> {
			master.moveTabRight();
		});
		
		btnOrg.addActionListener((e) -> {
			if (!popupMenu.isShowing()) {
				popupMenu.show(btnOrg, 16, 16);
			} else popupMenu.setVisible(false);
		});
		btnOrg.setComponentPopupMenu(popupMenu);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
