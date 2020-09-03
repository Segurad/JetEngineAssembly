package jetengine.gui;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.LayoutStyle.ComponentPlacement;

import jetengine.sys.SystemHandler;
import jetengine.sys.event.InterruptListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

final class InterruptFrame extends AbstractGUIComponent implements InterruptListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JLabel lblIEFFVal;

	public InterruptFrame() {
		super("Interrupts");
		setBackground(ColorSet.boxOutColor);
		
		JLabel lblIEFF = new JLabel("IEFF");
		lblIEFF.setFont(new Font("Consolas", Font.BOLD, 16));
		
		lblIEFFVal = new JLabel("0");
		lblIEFFVal.setFont(new Font("Consolas", Font.BOLD, 16));
		
		JButton btnRST55 = new JButton("RST 5.5");
		btnRST55.setFocusable(false);
		btnRST55.setBackground(ColorSet.boxButton);
		btnRST55.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SystemHandler.getRegister().setI5((byte) 1);
			}
		});
		
		JButton btnRST65 = new JButton("RST 6.5");
		btnRST65.setFocusable(false);
		btnRST65.setBackground(ColorSet.boxButton);
		btnRST65.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SystemHandler.getRegister().setI6((byte) 1);
			}
		});
		
		JButton btnRST75 = new JButton("RST 7.5");
		btnRST75.setFocusable(false);
		btnRST75.setBackground(ColorSet.boxButton);
		btnRST75.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SystemHandler.getRegister().setI7((byte) 1);
			}
		});
		
		JButton btnTrap = new JButton("Trap");
		btnTrap.setFocusable(false);
		btnTrap.setBackground(ColorSet.boxButton);
		btnTrap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SystemHandler.setTRAP(true);
			}
		});
		
		GUIToolBar toolBar = new GUIToolBar(this);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 7, 1));
		
		JLabel lblRST = new JLabel("RST");
		lblRST.setFont(new Font("Consolas", Font.BOLD, 16));
		
		JButton btnINTR = new JButton("INTR");
		btnINTR.setFocusable(false);
		btnINTR.setBackground(ColorSet.boxButton);
		btnINTR.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SystemHandler.setINTR((int) spinner.getValue());
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRST55)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblIEFF)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblIEFFVal)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnRST65)
						.addComponent(lblRST))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRST75)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTrap))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnINTR)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIEFF)
						.addComponent(lblIEFFVal)
						.addComponent(lblRST)
						.addComponent(btnINTR)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRST55)
						.addComponent(btnRST65)
						.addComponent(btnTrap)
						.addComponent(btnRST75))
					.addContainerGap())
		);
		setLayout(groupLayout);
		SystemHandler.getRegister().addInterrupListener(this);
	}

	@Override
	public void attachDefault() {
		MainFrame.getInstance().addComponentRight(this);
	}

	@Override
	public void updateIEFF(int val) {
		lblIEFFVal.setText("" + val);
	}
	
	@Override
	public void close() {
		SystemHandler.getRegister().removeInterruptListener(this);
		super.close();
	}
}
