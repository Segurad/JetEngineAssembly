package jetengine.gui.options;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import jetengine.gui.BaseFrame;
import jetengine.gui.ColorSet;
import jetengine.gui.MainFrame;
import jetengine.sys.ByteUtil;
import jetengine.sys.Memory;
import jetengine.sys.SystemHandler;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;

/**
 * Frame for handling and editing the memory
 * @author Segurad
 */
public final class OptionFrameMem extends BaseFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextField tfStart;
	private final JTextField tfEnd;
	private final JTextField tfSet;
	private int start = 0, end = 16;
	
	public OptionFrameMem() {
		super("Memory Manager");
		JLabel lblStart = new JLabel("Start");
		lblStart.setFont(new Font("Consolas", Font.BOLD, 14));
		lblStart.setForeground(ColorSet.boxTextColor);
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setFont(new Font("Consolas", Font.BOLD, 14));
		lblEnd.setForeground(ColorSet.boxTextColor);
		
		tfStart = new JTextField("0000");
		tfStart.setPreferredSize(new Dimension(30, 23));
		tfStart.setHorizontalAlignment(SwingConstants.CENTER);
		tfStart.setColumns(4);
		tfStart.setBackground(ColorSet.boxInColor);
		tfStart.setForeground(ColorSet.boxTextColor);
		tfStart.setCaretColor(ColorSet.caretColor);
		tfStart.setBorder(null);
		tfStart.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		tfEnd = new JTextField("0010");
		tfEnd.setPreferredSize(new Dimension(30, 23));
		tfEnd.setHorizontalAlignment(SwingConstants.CENTER);
		tfEnd.setColumns(4);
		tfEnd.setBackground(ColorSet.boxInColor);
		tfEnd.setForeground(ColorSet.boxTextColor);
		tfEnd.setCaretColor(ColorSet.caretColor);
		tfEnd.setBorder(null);
		tfEnd.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		
		JButton btnRand = new JButton("All random");
		btnRand.setBackground(ColorSet.boxButton);
		btnRand.addActionListener((e) -> {
			if (!checkAddress()) return;
			Memory m = SystemHandler.getMemory();
			Random r = new Random();
			for (int i = start; i < end+1; i++) {
				m.set(i, (byte) r.nextInt(0xFF+1));
			}
		});
		btnRand.setFocusable(false);
		
		tfSet = new JTextField("08");
		tfSet.setPreferredSize(new Dimension(18, 23));
		tfSet.setHorizontalAlignment(SwingConstants.CENTER);
		tfSet.setColumns(2);
		tfSet.setBackground(ColorSet.boxInColor);
		tfSet.setForeground(ColorSet.boxTextColor);
		tfSet.setCaretColor(ColorSet.caretColor);
		tfSet.setBorder(null);
		tfSet.setFont(new Font("Consolas", Font.PLAIN, 14));
		
		JButton btnSet = new JButton("Fill");
		btnSet.setBackground(ColorSet.boxButton);
		btnSet.addActionListener((e) -> {
			if (!checkAddress()) return;
			if (tfSet.getText().length() == 2) {
				if (!ByteUtil.validHex(tfSet.getText())) {
					tfSet.setText("08");
					return;
				}
			} else {
				tfSet.setText("08");
				return;
			}
			Memory m = SystemHandler.getMemory();
			byte val = (byte) Integer.parseInt(tfSet.getText(), 16);
			for (int i = start; i < end+1; i++) {
				m.set(i, val);
			}
		});
		btnSet.setFocusable(false);
		
		JButton btnRandTF = new JButton();
		btnRandTF.setToolTipText("Random");
		btnRandTF.setBackground(ColorSet.boxButton);
		btnRandTF.setBorder(border);
		btnRandTF.setIcon(new ImageIcon(OptionFrameReg.class.getResource("/jetengine/assets/random_btn.png")));
		btnRandTF.addActionListener((e) -> {
			tfSet.setText(ByteUtil.toHex(new Random().nextInt(0xFF+1), 2));
		});
		btnRandTF.setFocusable(false);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(ColorSet.boxButton);
		btnClear.addActionListener((e) -> {
			if (!checkAddress()) return;
			Memory m = SystemHandler.getMemory();
			for (int i = start; i < end+1; i++) {
				m.set(i, (byte) 8);
			}
		});
		btnClear.setFocusable(false);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(ColorSet.boxButton);
		btnCancel.addActionListener((e) -> {
			OptionFrameMem.this.dispose();
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnClear, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnSet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnRand, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfSet, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRandTF, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblStart)
								.addComponent(lblEnd))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tfStart)
								.addComponent(tfEnd, GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(117, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStart)
						.addComponent(tfStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnd)
						.addComponent(tfEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRand)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSet)
						.addComponent(btnRandTF, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(tfSet, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClear)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		pack();
	}

	private boolean checkAddress() {
		int num = MainFrame.validateHexInput(tfStart, 4, 0);
		if (num == -1) return false;
		start = num;
		num = MainFrame.validateHexInput(tfEnd, 4, 16);
		if (num == -1) return false;
		if (end < start) {
			tfStart.setText("0000");
			tfEnd.setText("0010");
			return false;
		}
		return true;
	}
}
