package jetengine.gui;

import jetengine.sys.SystemHandler;
import jetengine.sys.event.ConsolListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

final class ConsolFrame extends AbstractGUIComponent implements ConsolListener {

	private static final long serialVersionUID = 1L;
	private final JTextPane textPane;
	
	public ConsolFrame() {
		super("Console");
		setBackground(ColorSet.boxOutColor);
		SystemHandler.addConsolListener(this);
		
		GUIToolBar toolBar = new GUIToolBar(this);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Arial", Font.PLAIN, 16));
		textPane.setBackground(ColorSet.boxInColor);
		textPane.setForeground(ColorSet.boxTextColor);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
	
	@Override
	public void send(String msg) {
		textPane.setText(msg);
	}

	@Override
	public void close() {
		SystemHandler.remvoeConsolListener(this);
		super.close();
	}
	
	@Override
	public void attachDefault() {
		MainFrame.getInstance().addComponentDown(this);
	}
}
