package jetengine.gui.options;

import java.awt.Font;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import jetengine.gui.BaseFrame;
import jetengine.gui.ColorSet;
import jetengine.sys.ByteUtil;
import jetengine.sys.Register;
import jetengine.sys.SystemHandler;

import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * Frame for editing the registers
 * @author Segurad
 */
public final class OptionFrameReg extends BaseFrame {
	
	public OptionFrameReg() {
		super("Register Manager");
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, ColorSet.boxTextColor));
		panel.setBackground(ColorSet.boxOutColor);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Flags", TitledBorder.LEADING, TitledBorder.TOP, null, ColorSet.boxTextColor));
		panel_1.setBackground(ColorSet.boxOutColor);
		
		JButton btnApply = new JButton("Apply and Close");
		btnApply.setBackground(ColorSet.boxButton);
		btnApply.addActionListener((event) -> {
			Register r = SystemHandler.getRegister();
			if (ByteUtil.validHex(a.getText())) {
				r.setA((byte) Integer.parseInt(a.getText(), 16));
			}
			if (ByteUtil.validHex(b.getText())) {
				r.setB((byte) Integer.parseInt(b.getText(), 16));
			}
			if (ByteUtil.validHex(c.getText())) {
				r.setC((byte) Integer.parseInt(c.getText(), 16));
			}
			if (ByteUtil.validHex(d.getText())) {
				r.setD((byte) Integer.parseInt(d.getText(), 16));
			}
			if (ByteUtil.validHex(e.getText())) {
				r.setE((byte) Integer.parseInt(e.getText(), 16));
			}
			if (ByteUtil.validHex(h.getText())) {
				r.setH((byte) Integer.parseInt(h.getText(), 16));
			}
			if (ByteUtil.validHex(l.getText())) {
				r.setL((byte) Integer.parseInt(l.getText(), 16));
			}
			if (ByteUtil.validHex(sp.getText())) {
				r.setStack(Integer.parseInt(sp.getText(), 16));
			}
			if (ByteUtil.validHex(pc.getText())) {
				r.setPC(Integer.parseInt(pc.getText(), 16));
			}
			if (ByteUtil.validHex(s.getText())) {
				r.setS((byte) Integer.parseInt(s.getText(), 16));
			}
			if (ByteUtil.validHex(z.getText())) {
				r.setZ((byte) Integer.parseInt(z.getText(), 16));
			}
			if (ByteUtil.validHex(ac.getText())) {
				r.setAc((byte) Integer.parseInt(ac.getText(), 16));
			}
			if (ByteUtil.validHex(p.getText())) {
				r.setP((byte) Integer.parseInt(p.getText(), 16));
			}
			if (ByteUtil.validHex(cy.getText())) {
				r.setCy((byte) Integer.parseInt(cy.getText(), 16));
			}
			OptionFrameReg.this.dispose();
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(ColorSet.boxButton);
		btnCancel.addActionListener((e) -> {
			OptionFrameReg.this.dispose();
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addGap(20))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnApply, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnApply)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		
		Font fL = new Font("Consolas", Font.BOLD, 20);
		Font fD = new Font("Consolas", Font.PLAIN, 20);
		Register r = SystemHandler.getRegister();
		
		JButton btnFRandAll = new JButton("All random");
		btnFRandAll.setBackground(ColorSet.boxButton);
		btnFRandAll.addActionListener((e) -> {
			Random rand = new Random();
			s.setText("" + rand.nextInt(2));
			z.setText("" + rand.nextInt(2));
			ac.setText("" + rand.nextInt(2));
			p.setText("" + rand.nextInt(2));
			cy.setText("" + rand.nextInt(2));
		});
		btnFRandAll.setFocusable(false);
		
		JButton btnFClearAll = new JButton("Clear");
		btnFClearAll.setBackground(ColorSet.boxButton);
		btnFClearAll.addActionListener((e) -> {
			s.setText("0");
			z.setText("0");
			ac.setText("0");
			p.setText("0");
			cy.setText("0");
		});
		btnFClearAll.setFocusable(false);
		
		JLabel lblS = new JLabel("S");
		lblS.setForeground(ColorSet.boxTextColor);
		lblS.setFont(fL);
		
		JLabel lblP = new JLabel("P");
		lblP.setForeground(ColorSet.boxTextColor);
		lblP.setFont(fL);
		
		JLabel lblCY = new JLabel("CY");
		lblCY.setForeground(ColorSet.boxTextColor);
		lblCY.setFont(fL);
		
		JLabel lblZ = new JLabel("Z");
		lblZ.setForeground(ColorSet.boxTextColor);
		lblZ.setFont(fL);
		
		JLabel lblAC = new JLabel("AC");
		lblAC.setForeground(ColorSet.boxTextColor);
		lblAC.setFont(fL);
		
		s = new JTextField(""+r.getS());
		s.setHorizontalAlignment(SwingConstants.CENTER);
		s.setForeground(ColorSet.boxTextColor);
		s.setFont(fD);
		s.setColumns(1);
		s.setCaretColor(ColorSet.caretColor);
		s.setBorder(null);
		s.setBackground(ColorSet.boxInColor);
		
		z = new JTextField(""+r.getZ());
		z.setHorizontalAlignment(SwingConstants.CENTER);
		z.setForeground(ColorSet.boxTextColor);
		z.setFont(fD);
		z.setColumns(1);
		z.setCaretColor(ColorSet.caretColor);
		z.setBorder(null);
		z.setBackground(ColorSet.boxInColor);
		
		ac = new JTextField(""+r.getAc());
		ac.setHorizontalAlignment(SwingConstants.CENTER);
		ac.setForeground(ColorSet.boxTextColor);
		ac.setFont(fD);
		ac.setColumns(1);
		ac.setCaretColor(ColorSet.caretColor);
		ac.setBorder(null);
		ac.setBackground(ColorSet.boxInColor);
		
		p = new JTextField(""+r.getP());
		p.setHorizontalAlignment(SwingConstants.CENTER);
		p.setForeground(ColorSet.boxTextColor);
		p.setFont(fD);
		p.setColumns(1);
		p.setCaretColor(ColorSet.caretColor);
		p.setBorder(null);
		p.setBackground(ColorSet.boxInColor);
		
		cy = new JTextField(""+r.getCy());
		cy.setHorizontalAlignment(SwingConstants.CENTER);
		cy.setForeground(ColorSet.boxTextColor);
		cy.setFont(fL);
		cy.setColumns(1);
		cy.setCaretColor(ColorSet.caretColor);
		cy.setBorder(null);
		cy.setBackground(ColorSet.boxInColor);
		
		JButton btnRandAC = createRandomButton(ac);
		JButton btnRandP = createRandomButton(p);
		JButton btnRandCY = createRandomButton(cy);
		JButton btnRandS = createRandomButton(s);
		JButton btnRandZ = createRandomButton(z);
		
		JButton btnClearCY = createClearButton(cy);
		JButton btnClearAC = createClearButton(ac);
		JButton btnClearP = createClearButton(p);
		JButton btnClearS = createClearButton(s);
		JButton btnClearZ = createClearButton(z);
	
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblS, 15, 15, 15)
								.addComponent(lblZ, 15, 15, 15)
								.addComponent(lblAC)
								.addComponent(lblP, 15, 15, 15)
								.addComponent(lblCY))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(s, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnRandS, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnClearS, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(z, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnRandZ, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnClearZ, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(ac, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnRandAC, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnClearAC, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(p, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnRandP, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnClearP, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(cy, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnRandCY, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnClearCY, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnFClearAll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnFRandAll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(25))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(btnFRandAll)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFClearAll)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblS, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(lblZ, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(lblAC, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(lblP, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(lblCY, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(s, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandS, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnClearS, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(z, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandZ, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnClearZ, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(ac, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandAC, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnClearAC, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(p, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandP, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnClearP, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(cy, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandCY, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnClearCY, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(155, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblA = new JLabel("A");
		lblA.setFont(fL);
		lblA.setForeground(ColorSet.boxTextColor);
		
		JLabel lblB = new JLabel("B");
		lblB.setFont(fL);
		lblB.setForeground(ColorSet.boxTextColor);
		
		JLabel lblC = new JLabel("C");
		lblC.setFont(fL);
		lblC.setForeground(ColorSet.boxTextColor);
		
		JLabel lblD = new JLabel("D");
		lblD.setFont(fL);
		lblD.setForeground(ColorSet.boxTextColor);
		
		JLabel lblE = new JLabel("E");
		lblE.setFont(fL);
		lblE.setForeground(ColorSet.boxTextColor);
		
		JLabel lblH = new JLabel("H");
		lblH.setFont(fL);
		lblH.setForeground(ColorSet.boxTextColor);
		
		JLabel lblL = new JLabel("L");
		lblL.setFont(fL);
		lblL.setForeground(ColorSet.boxTextColor);
		
		a = new JTextField(ByteUtil.toHex(r.getA(), 2));
		a.setColumns(2);
		a.setForeground(ColorSet.boxTextColor);
		a.setFont(fD);
		a.setBackground(ColorSet.boxInColor);
		a.setBorder(null);
		a.setCaretColor(ColorSet.caretColor);
		a.setHorizontalAlignment(SwingConstants.CENTER);
		
		b = new JTextField(ByteUtil.toHex(r.getB(), 2));
		b.setColumns(2);
		b.setForeground(ColorSet.boxTextColor);
		b.setFont(fD);
		b.setBackground(ColorSet.boxInColor);
		b.setBorder(null);
		b.setCaretColor(ColorSet.caretColor);
		b.setHorizontalAlignment(SwingConstants.CENTER);
		
		c = new JTextField(ByteUtil.toHex(r.getC(), 2));
		c.setColumns(2);
		c.setForeground(ColorSet.boxTextColor);
		c.setFont(fD);
		c.setBackground(ColorSet.boxInColor);
		c.setBorder(null);
		c.setCaretColor(ColorSet.caretColor);
		c.setHorizontalAlignment(SwingConstants.CENTER);
		
		d = new JTextField(ByteUtil.toHex(r.getD(), 2));
		d.setColumns(2);
		d.setForeground(ColorSet.boxTextColor);
		d.setFont(fD);
		d.setBackground(ColorSet.boxInColor);
		d.setBorder(null);
		d.setCaretColor(ColorSet.caretColor);
		d.setHorizontalAlignment(SwingConstants.CENTER);
		
		e = new JTextField(ByteUtil.toHex(r.getE(), 2));
		e.setColumns(2);
		e.setForeground(ColorSet.boxTextColor);
		e.setFont(fD);
		e.setBackground(ColorSet.boxInColor);
		e.setBorder(null);
		e.setCaretColor(ColorSet.caretColor);
		e.setHorizontalAlignment(SwingConstants.CENTER);
		
		h = new JTextField(ByteUtil.toHex(r.getH(), 2));
		h.setColumns(2);
		h.setForeground(ColorSet.boxTextColor);
		h.setFont(fD);
		h.setBackground(ColorSet.boxInColor);
		h.setBorder(null);
		h.setCaretColor(ColorSet.caretColor);
		h.setHorizontalAlignment(SwingConstants.CENTER);
		
		l = new JTextField(ByteUtil.toHex(r.getL(), 2));
		l.setColumns(2);
		l.setForeground(ColorSet.boxTextColor);
		l.setFont(fD);
		l.setBackground(ColorSet.boxInColor);
		l.setBorder(null);
		l.setCaretColor(ColorSet.caretColor);
		l.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnRandA = createRandomButton(a);
		JButton btnRandB = createRandomButton(b);
		JButton btnRandC = createRandomButton(c);
		JButton btnRandD = createRandomButton(d);
		JButton btnRandE = createRandomButton(e);
		JButton btnRandH = createRandomButton(h);
		JButton btnRandL = createRandomButton(l);
		
		JButton btnClearA = createClearButton(a);
		JButton btnClearB = createClearButton(b);
		JButton btnClearC = createClearButton(c);
		JButton btnClearD = createClearButton(d);
		JButton btnClearE = createClearButton(e);
		JButton btnClearH = createClearButton(h);
		JButton btnClearL = createClearButton(l);
		
		JButton btnRRandAll = new JButton("All random");
		btnRRandAll.setBackground(ColorSet.boxButton);
		btnRRandAll.addActionListener((event) -> {
			Random rand = new Random();
			a.setText(ByteUtil.toHex(rand.nextInt(0xFF+1), 2));
			b.setText(ByteUtil.toHex(rand.nextInt(0xFF+1), 2));
			c.setText(ByteUtil.toHex(rand.nextInt(0xFF+1), 2));
			d.setText(ByteUtil.toHex(rand.nextInt(0xFF+1), 2));
			e.setText(ByteUtil.toHex(rand.nextInt(0xFF+1), 2));
			h.setText(ByteUtil.toHex(rand.nextInt(0xFF+1), 2));
			l.setText(ByteUtil.toHex(rand.nextInt(0xFF+1), 2));
			pc.setText(ByteUtil.toHex(rand.nextInt(0xFFFF+1), 4));
			sp.setText(ByteUtil.toHex(rand.nextInt(0xFFFF+1), 4));
		});
		btnRRandAll.setFocusable(false);
		
		JButton btnRClearAll = new JButton("Clear");
		btnRClearAll.setBackground(ColorSet.boxButton);
		btnRClearAll.addActionListener((event) -> {
			a.setText("00");
			b.setText("00");
			c.setText("00");
			d.setText("00");
			e.setText("00");
			h.setText("00");
			l.setText("00");
			pc.setText("0000");
			sp.setText("0000");
		});
		btnRClearAll.setFocusable(false);
		
		JLabel lblSP = new JLabel("SP");
		lblSP.setForeground(ColorSet.boxTextColor);
		lblSP.setFont(fL);
		
		JLabel lblPC = new JLabel("PC");
		lblPC.setForeground(ColorSet.boxTextColor);
		lblPC.setFont(fL);
		
		sp = new JTextField(ByteUtil.toHex(r.getStack(), 4));
		sp.setHorizontalAlignment(SwingConstants.CENTER);
		sp.setColumns(4);
		sp.setBackground(ColorSet.boxInColor);
		sp.setFont(fD);
		sp.setForeground(ColorSet.boxTextColor);
		sp.setBorder(null);
		
		pc = new JTextField(ByteUtil.toHex(r.getPC(), 4));
		pc.setHorizontalAlignment(SwingConstants.CENTER);
		pc.setColumns(4);
		pc.setBackground(ColorSet.boxInColor);
		pc.setFont(fD);
		pc.setForeground(ColorSet.boxTextColor);
		pc.setBorder(null);
		
		JButton btnRandSP = createRandomButton(sp);
		JButton btnRandPC = createRandomButton(pc);
		
		JButton btnClearPC = createClearButton(pc);
		JButton btnClearSP = createClearButton(sp);
        
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblA)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(a, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRandA)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearA))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblB)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(b, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRandB)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearB))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblC)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(c, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRandC)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearC))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblD)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(d, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRandD)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearD))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(e, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRandE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblH)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(h, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRandH)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearH))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnRClearAll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnRRandAll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblPC)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(pc)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRandPC))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblSP)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(sp)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRandSP))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblL)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(l, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRandL)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnClearL)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnClearSP)
								.addComponent(btnClearPC))))
					.addContainerGap(106, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnRRandAll)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRClearAll)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblA)
								.addComponent(a, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandA))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblB)
								.addComponent(b, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandB))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblC)
								.addComponent(c, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandC))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblD)
								.addComponent(d, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandD))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblE)
								.addComponent(e, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblH)
								.addComponent(h, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandH))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblL)
								.addComponent(l, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRandL)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnClearA)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearB)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearC)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearD)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearH)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClearL)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSP)
						.addComponent(sp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRandSP))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPC)
						.addComponent(pc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRandPC))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(301, Short.MAX_VALUE)
					.addComponent(btnClearSP)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClearPC)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		pack();
	}
	
	private JButton createClearButton(JTextField tf) {
		JButton btn = new JButton();
		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		btn.setIcon(new ImageIcon(OptionFrameReg.class.getResource("/jetengine/clear_btn.png")));
		btn.setBackground(ColorSet.boxButton);
		btn.setBorder(border);
        btn.setToolTipText("Clear");
        btn.setFocusable(false);
		btn.addActionListener((e) -> {
			String s = "0";
			for (int i = 1; i < tf.getColumns(); i++) {
				s += "0";
			}
			tf.setText(s);
		});
		return btn;
	}
	
	private JButton createRandomButton(JTextField tf) {
		JButton btn = new JButton();
		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		btn.setToolTipText("Random");
		btn.setFocusable(false);
		btn.setBackground(ColorSet.boxButton);
		btn.setBorder(border);
		btn.setIcon(new ImageIcon(OptionFrameReg.class.getResource("/jetengine/random_btn.png")));
		btn.addActionListener((e) -> {
			String s;
			if (tf.getColumns() == 1) {
				s = "1";
			} else {
				s = "FF";
				for (int i = 2; i < tf.getColumns(); i++) {
					s += "F";
				}
			}
			int i = new Random().nextInt(Integer.parseInt(s, 16)+1);
			tf.setText(ByteUtil.toHex(i, tf.getColumns()));
		});
		return btn;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField a;
	private JTextField b;
	private JTextField c;
	private JTextField d;
	private JTextField e;
	private JTextField h;
	private JTextField l;
	private JTextField sp;
	private JTextField pc;
	private JTextField s;
	private JTextField z;
	private JTextField ac;
	private JTextField p;
	private JTextField cy;
}
