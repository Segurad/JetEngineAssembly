package jetengine.gui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import jetengine.sys.ByteUtil;

import javax.swing.SwingConstants;

public class ConverterFrame extends AbstractGUIComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfHex;
	private JTextField tfDec;
	private JTextField tfBin;

	public ConverterFrame() {
		super("Converter");
		GUIToolBar toolBar = new GUIToolBar(this);
		setBackground(ColorSet.boxOutColor);
		
		Font fL = new Font("Consolas", Font.BOLD, 20);
		Font fD = new Font("Consolas", Font.PLAIN, 20);
		
		JLabel lblHex = new JLabel("Hex");
		lblHex.setFont(fL);
		lblHex.setForeground(ColorSet.boxTextColor);
		
		tfHex = new JTextField("0");
		tfHex.setHorizontalAlignment(SwingConstants.RIGHT);
		tfHex.setColumns(10);
		tfHex.setBorder(null);
		tfHex.setBackground(ColorSet.boxInColor);
		tfHex.setForeground(ColorSet.boxTextColor);
		tfHex.setFont(fD);
		tfHex.setCaretColor(ColorSet.caretColor);
		tfHex.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				if (!ByteUtil.validHex(tfHex.getText())) {
					tfHex.setText("0");
				} i = Integer.parseInt(tfHex.getText(), 16);
				tfBin.setText(Integer.toBinaryString(i));
				tfDec.setText(""+i);
			}
		});
		
		JLabel lblDec = new JLabel("Decimal");
		lblDec.setFont(fL);
		lblDec.setForeground(ColorSet.boxTextColor);
		
		tfDec = new JTextField("0");
		tfDec.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDec.setColumns(10);
		tfDec.setBorder(null);
		tfDec.setBackground(ColorSet.boxInColor);
		tfDec.setForeground(ColorSet.boxTextColor);
		tfDec.setFont(fD);
		tfDec.setCaretColor(ColorSet.caretColor);
		tfDec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				try {
					i = Integer.parseInt(tfBin.getText());
				} catch (NumberFormatException e2) {
					i = 0;
					tfDec.setText(""+0);
				}
				tfHex.setText(Integer.toHexString(i));
				tfBin.setText(Integer.toBinaryString(i));
			}
		});
		
		JLabel lblBin = new JLabel("Binary");
		lblBin.setFont(fL);
		lblBin.setForeground(ColorSet.boxTextColor);
		
		tfBin = new JTextField("0");
		tfBin.setHorizontalAlignment(SwingConstants.RIGHT);
		tfBin.setColumns(10);
		tfBin.setBorder(null);
		tfBin.setBackground(ColorSet.boxInColor);
		tfBin.setForeground(ColorSet.boxTextColor);
		tfBin.setFont(fD);
		tfBin.setCaretColor(ColorSet.caretColor);
		tfBin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				try {
					i = Integer.parseInt(tfBin.getText(), 2);
				} catch (NumberFormatException e2) {
					i = 0;
					tfBin.setText(""+0);
				}
				tfHex.setText(Integer.toHexString(i));
				tfDec.setText(""+i);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblHex)
						.addComponent(lblDec)
						.addComponent(lblBin))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tfBin)
						.addComponent(tfDec)
						.addComponent(tfHex, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tfHex, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfDec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfBin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblHex)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDec)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblBin)))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	@Override
	public void attachDefault() {
		MainFrame.getInstance().addComponentLeft(this);
	}
}
