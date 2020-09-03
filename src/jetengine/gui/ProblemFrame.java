package jetengine.gui;

import java.awt.Font;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import jetengine.sys.SystemHandler;
import jetengine.sys.event.ProblemListener;

final class ProblemFrame extends AbstractGUIComponent implements ProblemListener {
	

	private static final long serialVersionUID = 1L;
	private final JTable table;
	
	public ProblemFrame() {
		super("Problems");
		setBackground(ColorSet.boxOutColor);
		SystemHandler.addProblemListener(this);
		GUIToolBar toolBar = new GUIToolBar(this);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(ColorSet.boxInColor);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) { 
                return column == 0 ? false : true;          
			}
		};
		table.setBorder(null);
		table.setFont(new Font("Consolas", Font.PLAIN, 16));
		table.setBackground(ColorSet.boxInColor);
		table.setForeground(ColorSet.boxTextColor);
		table.setSelectionBackground(ColorSet.boxOutColor);
		table.setSelectionForeground(ColorSet.boxTextColor);
	
		JPanel panel4 = new JPanel();
		panel4.setBorder(null);
		panel4.setBackground(ColorSet.boxOutColor);
		scrollPane.setViewportView(panel4);
		GroupLayout gl_panel4 = new GroupLayout(panel4);
		gl_panel4.setHorizontalGroup(
			gl_panel4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel4.createSequentialGroup()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
		);
		gl_panel4.setVerticalGroup(
			gl_panel4.createParallelGroup(Alignment.LEADING)
				.addComponent(table)
		);
		panel4.setLayout(gl_panel4);
		setLayout(groupLayout);
	}

	@Override
	public void attachDefault() {
		MainFrame.getInstance().addComponentDown(this);
	}
	
	@Override
	public void close() {
		SystemHandler.removeProblemListener(this);
		super.close();
	}

	@Override
	public void updateProblems(List<String> problems) {
		table.setModel(new DefaultTableModel(problems.size(), 1));
		int index = 0;
		for (String prob : problems) {
			table.setValueAt(prob, index, 0);
			index++;
		}
	}

}
