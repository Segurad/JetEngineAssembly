package jetengine.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import jetengine.sys.ByteUtil;
import jetengine.sys.SystemHandler;
import jetengine.sys.frontcon.PortListener;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;

@SuppressWarnings("serial")
final class PortPanel extends JPanel implements PortListener {
	
	private byte port;
	private JToggleButton p0,p1,p2,p3,p4,p5,p6,p7;
	private JTextField tfTitle;
	private JLabel lblValue;
	
	public PortPanel() {
		this(null, "00");
	}
	
	public PortPanel(PortFrame frame) {
		this(frame, "00");
	}
	
	public PortPanel(PortFrame frame, String portHex) {
		SystemHandler.getPorts().addListener(this);
		setBorder(BorderFactory.createLineBorder(ColorSet.boxContentBorder, 2));
		setPreferredSize(new Dimension(325, 75));
		JButton btnX = new JButton();
		btnX.setIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/close_btn.png")));
		btnX.setBackground(ColorSet.boxOutColor);
        btnX.setBorderPainted(false);
		btnX.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frame != null) frame.removePort(PortPanel.this);
				SystemHandler.getPorts().removeListener(PortPanel.this);
			}
		});
		
		
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(2);
		textField.setText(portHex);
		textField.setForeground(ColorSet.boxTextColor);
		textField.setCaretColor(ColorSet.caretColor);
		textField.setBackground(ColorSet.boxInColor);
		textField.setBorder(null);
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkText(textField);
			}
		});
		
		p0 = new JToggleButton();
		p0.setIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_off.png")));
		p0.setSelectedIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_on.png")));
		p0.setContentAreaFilled(false);
        p0.setBorderPainted(false);
        
		p1 = new JToggleButton();
		p1.setIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_off.png")));
		p1.setSelectedIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_on.png")));
		p1.setContentAreaFilled(false);
        p1.setBorderPainted(false);
        
		p2 = new JToggleButton();
		p2.setIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_off.png")));
		p2.setSelectedIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_on.png")));
		p2.setContentAreaFilled(false);
        p2.setBorderPainted(false);
        
		p3 = new JToggleButton();
		p3.setIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_off.png")));
		p3.setSelectedIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_on.png")));
		p3.setContentAreaFilled(false);
        p3.setBorderPainted(false);
        
		p4 = new JToggleButton();
		p4.setIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_off.png")));
		p4.setSelectedIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_on.png")));
		p4.setContentAreaFilled(false);
        p4.setBorderPainted(false);
        
		p5 = new JToggleButton();
		p5.setIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_off.png")));
		p5.setSelectedIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_on.png")));
		p5.setContentAreaFilled(false);
        p5.setBorderPainted(false);
        
		p6 = new JToggleButton();
		p6.setIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_off.png")));
		p6.setSelectedIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_on.png")));
		p6.setContentAreaFilled(false);
        p6.setBorderPainted(false);
        
		p7 = new JToggleButton();
		p7.setIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_off.png")));
		p7.setSelectedIcon(new ImageIcon(PortPanel.class.getResource("/jetengine/port_on.png")));
		p7.setContentAreaFilled(false);
        p7.setBorderPainted(false);
		
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String binstr = "";
				binstr += p7.isSelected() ? '1' : '0'; 
				binstr += p6.isSelected() ? '1' : '0'; 
				binstr += p5.isSelected() ? '1' : '0'; 
				binstr += p4.isSelected() ? '1' : '0'; 
				binstr += p3.isSelected() ? '1' : '0'; 
				binstr += p2.isSelected() ? '1' : '0'; 
				binstr += p1.isSelected() ? '1' : '0'; 
				binstr += p0.isSelected() ? '1' : '0';
				SystemHandler.getPorts().set(port, (byte) Integer.parseInt(binstr, 2), true);
				System.out.println(binstr);
			}
		};
		p0.addActionListener(al);
		p1.addActionListener(al);
		p2.addActionListener(al);
		p3.addActionListener(al);
		p4.addActionListener(al);
		p5.addActionListener(al);
		p6.addActionListener(al);
		p7.addActionListener(al);
		
		tfTitle = new JTextField("Port");
		tfTitle.setColumns(10);
		tfTitle.setBackground(ColorSet.boxInColor);
		tfTitle.setForeground(ColorSet.boxTextColor);
		tfTitle.setBorder(null);
		tfTitle.setCaretColor(ColorSet.caretColor);
		
		lblValue = new JLabel("00");
		lblValue.setFont(new Font("Consolas", Font.BOLD, 16));
		lblValue.setForeground(ColorSet.boxTextColor);
		
		GroupLayout gl_pPortPanel = new GroupLayout(this);
		gl_pPortPanel.setHorizontalGroup(
			gl_pPortPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pPortPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pPortPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(tfTitle, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pPortPanel.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(p7, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(p6, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(p5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(p4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pPortPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pPortPanel.createSequentialGroup()
							.addComponent(p3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(p2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(p1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(p0, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblValue))
						.addComponent(btnX, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pPortPanel.setVerticalGroup(
			gl_pPortPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pPortPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pPortPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(tfTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnX, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pPortPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(p7, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(p6, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(p5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(p4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(p3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(p2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(p1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pPortPanel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblValue)
							.addComponent(p0, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(gl_pPortPanel);
		setBackground(ColorSet.boxOutColor);
		checkText(textField);
	}

	@Override
	public byte getPort() {
		return port;
	}

	@Override
	public void update(byte value) {
		String binstr=ByteUtil.toBinaryString(value);
		lblValue.setText(ByteUtil.toHex(value, 2));
		System.out.println(port + " " + binstr);
		p7.setSelected(binstr.charAt(0) == '0' ? false : true);
		p6.setSelected(binstr.charAt(1) == '0' ? false : true);
		p5.setSelected(binstr.charAt(2) == '0' ? false : true);
		p4.setSelected(binstr.charAt(3) == '0' ? false : true);
		p3.setSelected(binstr.charAt(4) == '0' ? false : true);
		p2.setSelected(binstr.charAt(5) == '0' ? false : true);
		p1.setSelected(binstr.charAt(6) == '0' ? false : true);
		p0.setSelected(binstr.charAt(7) == '0' ? false : true);
	}
	
	private void checkText(JTextField textField) {
		String txt = textField.getText();
		if (txt.length() != 2) {
			textField.setText("00");
			txt = "00";
		}
		if (!ByteUtil.validHex(txt)) {
			textField.setText("00");
			txt = "00";
		}
		port = (byte) Integer.parseInt(txt, 16);
		update(SystemHandler.getPorts().getValue(port));
	}
}
