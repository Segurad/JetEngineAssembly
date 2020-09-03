package jetengine.gui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import jetengine.sys.SystemHandler;

final class PortFrame extends AbstractGUIComponent {

	private PortPanel[] panels = new PortPanel[5];
	private final JScrollPane scrollPane;
	
	public PortFrame() {
		super("Ports");
		setBackground(ColorSet.boxOutColor);
		setMinimumSize(new Dimension(390, 125));
		setPreferredSize(getMinimumSize());
		
		JButton btnNew = new JButton("Neuer Port");
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPortPanel();
			}
		});;
		btnNew.setBackground(ColorSet.boxButton);
		btnNew.setFocusable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		GUIToolBar toolBar = new GUIToolBar(this);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNew)
					.addContainerGap(315, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(btnNew)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panels[0] = new PortPanel(this, "00");
		panels[1] = new PortPanel(this, "01");
		panels[2] = new PortPanel(this, "02");
		panels[3] = new PortPanel(this, "03");
		panels[4] = new PortPanel(this, "04");
		rebuildPanel();
		this.setLayout(groupLayout);
	}
	
	public void addPortPanel() {
		panels = Arrays.copyOf(panels, panels.length + 1);
		panels[panels.length-1] = new PortPanel(this);
		rebuildPanel();
	}
	
	public void removePort(PortPanel panel) {
		PortPanel[] newpanels = new PortPanel[panels.length -1];
		int i = 0;
		for (PortPanel p : panels) {
			if (p == panel) {
				SystemHandler.getPorts().removeListener(panel);
				continue;
			}
			newpanels[i] = p;
			i++;
		}
		panels = newpanels;
		rebuildPanel();
	}
	
	private void rebuildPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(ColorSet.boxInColor);
		panel.setMinimumSize(new Dimension(350, panel.getMinimumSize().height));
		scrollPane.setViewportView(panel);
		GroupLayout gl_panel = new GroupLayout(panel);
		
		ParallelGroup pgroup11 = gl_panel.createParallelGroup(Alignment.TRAILING);
		SequentialGroup sqgroup1 = gl_panel.createSequentialGroup();
		sqgroup1.addContainerGap();
		ParallelGroup pgroup12 = gl_panel.createParallelGroup(Alignment.LEADING);
		for (PortPanel p : panels) {
			pgroup12.addComponent(p, GroupLayout.DEFAULT_SIZE, p.getPreferredSize().width, Short.MAX_VALUE);
		}
		sqgroup1.addGroup(pgroup12).addGap(27);
		pgroup11.addGroup(sqgroup1);
		gl_panel.setHorizontalGroup(pgroup11);
		
		
		ParallelGroup pgroup21 = gl_panel.createParallelGroup(Alignment.LEADING);
		SequentialGroup sqgroup2 = gl_panel.createSequentialGroup();
		sqgroup2.addGap(10);
		for (int i = 0; i < panels.length; i++) {
			sqgroup2.addComponent(panels[i], GroupLayout.PREFERRED_SIZE, panels[i].getPreferredSize().height, GroupLayout.PREFERRED_SIZE);
			if (i < panels.length-1) sqgroup2.addPreferredGap(ComponentPlacement.RELATED);
		}
		sqgroup2.addContainerGap(10, Short.MAX_VALUE);
		pgroup21.addGroup(sqgroup2);
		gl_panel.setVerticalGroup(pgroup21);
		panel.setLayout(gl_panel);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void close() {
		for(PortPanel p : panels) {
			SystemHandler.getPorts().removeListener(p);
		}
		super.close();
	}

	@Override
	public void attachDefault() {
		MainFrame.getInstance().addComponentRight(this);
	}
}
