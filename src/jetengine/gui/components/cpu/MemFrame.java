package jetengine.gui.components.cpu;

import jetengine.gui.ColorSet;
import jetengine.gui.MainFrame;
import jetengine.gui.components.AbstractGUIComponent;
import jetengine.gui.components.GUIToolBar;
import jetengine.gui.options.OptionFrameMem;
import jetengine.sys.ByteUtil;
import jetengine.sys.Memory;
import jetengine.sys.Message;
import jetengine.sys.SystemHandler;
import jetengine.sys.event.MemListener;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public final class MemFrame extends AbstractGUIComponent implements MemListener {

	private static final long serialVersionUID = 1L;
	private final JTable table;
	private final JTextField txtStart;
	private final JTextField txtEnd;
	private int start = 0, end = 16;
	
	public MemFrame() {
		super("Memory");
		GUIToolBar toolBar = new GUIToolBar(this, true);
		
		setBackground(ColorSet.boxOutColor);
		SystemHandler.getMemory().addMemListener(this);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBackground(ColorSet.boxInColor);
		
		JLabel lblMemoryLocation = new JLabel("Memory Location");
		lblMemoryLocation.setForeground(ColorSet.boxTextColor);
		lblMemoryLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemoryLocation.setFont(new Font("Consolas", Font.BOLD, 14));
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setForeground(ColorSet.boxTextColor);
		lblValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblValue.setFont(new Font("Consolas", Font.BOLD, 14));
		
		txtStart = new JTextField("0000");
		txtStart.setHorizontalAlignment(SwingConstants.CENTER);
		txtStart.setForeground(ColorSet.boxTextColor);
		txtStart.setBackground(ColorSet.boxInColor);
		txtStart.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtStart.setColumns(10);
		txtStart.setBorder(null);
		txtStart.addActionListener((e) -> {
				updateALL();
		});
		txtStart.setCaretColor(ColorSet.caretColor);
		
		txtEnd = new JTextField("0010");
		txtEnd.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnd.setForeground(ColorSet.boxTextColor);
		txtEnd.setBackground(ColorSet.boxInColor);
		txtEnd.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtEnd.setColumns(10);
		txtEnd.setBorder(null);
		txtEnd.addActionListener((e) -> {
			updateALL();
		});
		txtEnd.setCaretColor(ColorSet.caretColor);
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setFont(new Font("Consolas", Font.BOLD, 14));
		lblStart.setForeground(ColorSet.boxTextColor);
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setFont(new Font("Consolas", Font.BOLD, 14));
		lblEnd.setForeground(ColorSet.boxTextColor);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(ColorSet.boxButton);
		btnUpdate.setFocusable(false);
		btnUpdate.addActionListener((e) -> {
			updateALL();
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStart)
								.addComponent(lblEnd))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtStart, 0, 0, Short.MAX_VALUE)
								.addComponent(txtEnd, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnUpdate))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMemoryLocation)
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addComponent(lblValue, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStart)
								.addComponent(txtStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEnd)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(btnUpdate)))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMemoryLocation)
						.addComponent(lblValue, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
		);
		
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) { 
                return column == 0 ? false : true;          
			}
		};
		table.setFont(new Font("Consolas", Font.PLAIN, 16));
		table.setBackground(ColorSet.boxInColor);
		table.setForeground(ColorSet.boxTextColor);
		table.setSelectionBackground(ColorSet.boxOutColor);
		table.setSelectionForeground(ColorSet.boxTextColor);
	
		DefaultTableModel model = new DefaultTableModel(end + 1 - start, 2);
		addListener(model);
		table.setModel(model);
		JPanel panel4 = new JPanel();
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
		updateALL();
	}
	
	@Override
	public int getRangeMin() {
		return start;
	}

	@Override
	public int getRangeMax() {
		return end;
	}

	private void updateALL() {
		if (txtStart.getText().length() != 4 || !ByteUtil.validHex(txtStart.getText())) {
			SystemHandler.sendMessage(Message.NO_VALID_HEX);
			txtStart.setText("0000");
			start = 0;
		} else {
			start = Integer.parseInt(txtStart.getText(), 16);
		}
		if (txtEnd.getText().length() != 4 || !ByteUtil.validHex(txtEnd.getText())) {
			SystemHandler.sendMessage(Message.NO_VALID_HEX);
			txtEnd.setText("0010");
			end = 16;
		} else {
			end = Integer.parseInt(txtEnd.getText(), 16);
		}
		if (start > end) {
			SystemHandler.sendMessage(Message.MEM_START_TO_HIGH);
			return;
		}
		int index = 0;
		DefaultTableModel model = new DefaultTableModel(end + 1 - start, 2);
		addListener(model);
		table.setModel(model);
		Memory m = SystemHandler.getMemory();
		for (int i = start; i<=end; i++) {
			table.setValueAt(ByteUtil.toHex(i, 4), index, 0);
			table.setValueAt(ByteUtil.toHex(m.getValue(i), 2), index, 1);
			index++;
		}
	}
	
	@Override
	public void update(int index, int[] values) {
		for (int val : values) {
			table.setValueAt(ByteUtil.toHex(val, 2), index, 1);
			index++;
		}
	}

	@Override
	public void attachDefault() {
		MainFrame.getInstance().addComponentLeft(this);
	}
	
	@Override
	public void close() {
		SystemHandler.getMemory().removeMemListener(this);
		super.close();
	}
	
	private void addListener(DefaultTableModel model) {
		model.addTableModelListener((e) -> {
			int row = table.getEditingRow();
			if (row == -1) return;
			String k = (String) model.getValueAt(row, 0);
			String v = (String) model.getValueAt(row, 1);
			SystemHandler.getMemory().set(Integer.parseInt(k, 16), (byte) Integer.parseInt(v, 16), false);
		});
	}
	
	@Override
	public void openOptions() {
		new OptionFrameMem().setVisible(true);
	}
}
