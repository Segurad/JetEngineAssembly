package jetengine.gui;

import jetengine.sys.ByteUtil;
import jetengine.sys.Register;
import jetengine.sys.SystemHandler;
import jetengine.sys.frontcon.RegListener;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;
import javax.swing.border.TitledBorder;

import java.awt.Dimension;

final class RegFrame extends AbstractGUIComponent implements RegListener{

	private JLabel a,b,c,d,e,h,l,s,z,ac,p,cy,sp,pc;
	
	public RegFrame() {
		super("Register");
		setMinimumSize(new Dimension(295, 10));
		setBackground(ColorSet.boxOutColor);
		
		JPanel pRegs = new JPanel();
		pRegs.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, ColorSet.boxTextColor));
		pRegs.setBackground(ColorSet.boxInColor);
		
		JPanel pFlags = new JPanel();
		pFlags.setBorder(new TitledBorder(null, "Flags", TitledBorder.LEADING, TitledBorder.TOP, null, ColorSet.boxTextColor));
		pFlags.setBackground(ColorSet.boxInColor);
		
		GUIToolBar toolBar = new GUIToolBar(this, true);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(pRegs, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pFlags, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pRegs, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
						.addComponent(pFlags, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		Font fL = new Font("Consolas", Font.BOLD, 20);
		Font fD = new Font("Consolas", Font.PLAIN, 20);
		Register r = SystemHandler.getRegister();
		
		s = new JLabel(""+r.getS());
		s.setForeground(ColorSet.boxTextColor);
		s.setFont(fD);
		
		JLabel lSL = new JLabel("S");
		lSL.setForeground(ColorSet.boxTextColor);
		lSL.setFont(fL);
		
		JLabel lZL = new JLabel("Z");
		lZL.setForeground(ColorSet.boxTextColor);
		lZL.setFont(fL);
		
		z = new JLabel(""+r.getZ());
		z.setForeground(ColorSet.boxTextColor);
		z.setFont(fD);
		
		JLabel lACL = new JLabel("AC");
		lACL.setForeground(ColorSet.boxTextColor);
		lACL.setFont(fL);
		
		ac = new JLabel(""+r.getAc());
		ac.setForeground(ColorSet.boxTextColor);
		ac.setFont(fD);
		
		JLabel lPL = new JLabel("P");
		lPL.setForeground(ColorSet.boxTextColor);
		lPL.setFont(fL);
		
		p = new JLabel(""+r.getP());
		p.setForeground(ColorSet.boxTextColor);
		p.setFont(fD);
		
		JLabel lCYL = new JLabel("CY");
		lCYL.setForeground(ColorSet.boxTextColor);
		lCYL.setFont(fL);
		
		cy = new JLabel(""+r.getCy());
		cy.setForeground(ColorSet.boxTextColor);
		cy.setFont(fD);
		
		GroupLayout gl_pFlags = new GroupLayout(pFlags);
		gl_pFlags.setHorizontalGroup(
			gl_pFlags.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pFlags.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pFlags.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lACL, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
						.addComponent(lZL, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
						.addComponent(lCYL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lPL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lSL, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGroup(gl_pFlags.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pFlags.createSequentialGroup()
							.addGap(18)
							.addComponent(s, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_pFlags.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_pFlags.createParallelGroup(Alignment.LEADING)
								.addComponent(ac, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(z, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(cy, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(p, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_pFlags.setVerticalGroup(
			gl_pFlags.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pFlags.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pFlags.createParallelGroup(Alignment.LEADING)
						.addComponent(lSL)
						.addComponent(s))
					.addGap(10)
					.addGroup(gl_pFlags.createParallelGroup(Alignment.BASELINE)
						.addComponent(lZL, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(z, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_pFlags.createParallelGroup(Alignment.BASELINE)
						.addComponent(lACL, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(ac, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_pFlags.createParallelGroup(Alignment.BASELINE)
						.addComponent(lPL, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(p, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_pFlags.createParallelGroup(Alignment.BASELINE)
						.addComponent(lCYL, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(cy, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		pFlags.setLayout(gl_pFlags);
		
		JLabel lBL = new JLabel("B");
		lBL.setFont(fL);
		lBL.setForeground(ColorSet.boxTextColor);
		
		b = new JLabel(ByteUtil.toHex(r.getB(), 2));
		b.setFont(fD);
		b.setForeground(ColorSet.boxTextColor);
		
		JLabel lCL = new JLabel("C");
		lCL.setFont(fL);
		lCL.setForeground(ColorSet.boxTextColor);
		
		c = new JLabel(ByteUtil.toHex(r.getC(), 2));
		c.setFont(fD);
		c.setForeground(ColorSet.boxTextColor);
		
		JLabel lDL = new JLabel("D");
		lDL.setFont(fL);
		lDL.setForeground(ColorSet.boxTextColor);
		
		d = new JLabel(ByteUtil.toHex(r.getD(), 2));
		d.setFont(fD);
		d.setForeground(ColorSet.boxTextColor);
		
		JLabel lEL = new JLabel("E");
		lEL.setFont(fL);
		lEL.setForeground(ColorSet.boxTextColor);
		
		e = new JLabel(ByteUtil.toHex(r.getE(), 2));
		e.setFont(fD);
		e.setForeground(ColorSet.boxTextColor);
		
		JLabel lHL = new JLabel("H");
		lHL.setFont(fL);
		lHL.setForeground(ColorSet.boxTextColor);
		
		h = new JLabel(ByteUtil.toHex(r.getH(), 2));
		h.setFont(fD);
		h.setForeground(ColorSet.boxTextColor);
		
		JLabel lLL = new JLabel("L");
		lLL.setFont(fL);
		lLL.setForeground(ColorSet.boxTextColor);
		
		l = new JLabel(ByteUtil.toHex(r.getL(), 2));
		l.setFont(fD);
		l.setForeground(ColorSet.boxTextColor);
		
		JLabel lAL = new JLabel("A");
		lAL.setFont(fL);
		lAL.setForeground(ColorSet.boxTextColor);
		
		a = new JLabel(ByteUtil.toHex(r.getA(), 2));
		a.setFont(fD);
		a.setForeground(ColorSet.boxTextColor);
		
		JLabel lSPL = new JLabel("SP");
		lSPL.setForeground(ColorSet.boxTextColor);
		lSPL.setFont(fL);
		
		sp = new JLabel(ByteUtil.toHex(r.getStack(), 4));
		sp.setForeground(ColorSet.boxTextColor);
		sp.setFont(fD);
		
		JLabel lPCL = new JLabel("PC");
		lPCL.setForeground(ColorSet.boxTextColor);
		lPCL.setFont(fL);
		
		pc = new JLabel(ByteUtil.toHex(r.getPC(), 4));
		pc.setForeground(ColorSet.boxTextColor);
		pc.setFont(fD);
		
		GroupLayout gl_pRegs = new GroupLayout(pRegs);
		gl_pRegs.setHorizontalGroup(
			gl_pRegs.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pRegs.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pRegs.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pRegs.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_pRegs.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pRegs.createSequentialGroup()
									.addComponent(lDL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(d, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(26)
									.addComponent(lEL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(e, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_pRegs.createSequentialGroup()
									.addComponent(lBL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(b, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addGap(26)
									.addComponent(lCL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(c, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_pRegs.createSequentialGroup()
								.addGroup(gl_pRegs.createParallelGroup(Alignment.TRAILING)
									.addComponent(lPCL)
									.addGroup(gl_pRegs.createSequentialGroup()
										.addComponent(lHL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(h, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
									.addComponent(lSPL))
								.addGroup(gl_pRegs.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_pRegs.createSequentialGroup()
										.addGap(26)
										.addComponent(lLL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
										.addGap(6)
										.addComponent(l, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_pRegs.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_pRegs.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(pc, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(sp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))))))
						.addGroup(gl_pRegs.createSequentialGroup()
							.addGap(37)
							.addComponent(lAL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(a, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_pRegs.setVerticalGroup(
			gl_pRegs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pRegs.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pRegs.createParallelGroup(Alignment.LEADING)
						.addComponent(lAL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(a, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_pRegs.createParallelGroup(Alignment.LEADING)
						.addComponent(lCL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(c, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lBL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(b, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_pRegs.createParallelGroup(Alignment.LEADING)
						.addComponent(lDL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(d, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lEL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(e, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_pRegs.createParallelGroup(Alignment.LEADING)
						.addComponent(lHL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(h, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lLL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(l, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pRegs.createParallelGroup(Alignment.BASELINE)
						.addComponent(lSPL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(sp, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pRegs.createParallelGroup(Alignment.BASELINE)
						.addComponent(lPCL, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(pc, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(208, Short.MAX_VALUE))
		);
		pRegs.setLayout(gl_pRegs);
		setLayout(groupLayout);
		SystemHandler.getRegister().addRegListener(this);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void close() {
		SystemHandler.getRegister().removeRegListener(this);
		super.close();
	}
	
	@Override
	public void updateRegA(String hexString) {
		a.setText(hexString);
	}
	@Override
	public void updateRegB(String hexString) {
		b.setText(hexString);
	}
	@Override
	public void updateRegC(String hexString) {
		c.setText(hexString);
	}
	@Override
	public void updateRegD(String hexString) {
		d.setText(hexString);
	}
	@Override
	public void updateRegE(String hexString) {
		e.setText(hexString);
	}
	@Override
	public void updateRegL(String hexString) {
		l.setText(hexString);
	}
	@Override
	public void updateRegH(String hexString) {
		h.setText(hexString);
	}
	@Override
	public void updateStack(String hexString) {
		sp.setText(hexString);
	}
	@Override
	public void updatePC(String hexString) {
		pc.setText(hexString);
	}
	@Override
	public void updateFlagCarry(String hexString) {
		cy.setText(hexString);
	}
	@Override
	public void updateFlagParity(String hexString) {
		p.setText(hexString);
	}
	@Override
	public void updateFlagAuxillaryCarry(String hexString) {
		ac.setText(hexString);
	}
	@Override
	public void updateFlagZero(String hexString) {
		z.setText(hexString);
	}
	@Override
	public void updateFlagSign(String hexString) {
		s.setText(hexString);
	}

	@Override
	public void attachDefault() {
		MainFrame.getInstance().addComponentLeft(this);
	}
	
	@Override
	public void openOptions() {
		new OptionFrameReg().setVisible(true);
	}
}
