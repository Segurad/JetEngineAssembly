package jetengine.gui.options;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;

import javax.swing.SpinnerNumberModel;

import jetengine.gui.BaseFrame;
import jetengine.gui.ColorSet;
import jetengine.sys.SystemHandler;

import javax.swing.JButton;

/**
 * Frame to edit the clock frequency
 * @author Segurad
 */
public final class OptionFrameClock extends BaseFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OptionFrameClock() {
		super("Clock");
		JLabel lblClock = new JLabel("Clock");
		lblClock.setForeground(ColorSet.boxTextColor);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(SystemHandler.getClock(), 0, null, 1));
		spinner.setBackground(ColorSet.boxInColor);
		spinner.setForeground(ColorSet.boxTextColor);
		
		JButton btnApply = new JButton("Apply and Close");
		btnApply.setBackground(ColorSet.boxButton);
		btnApply.addActionListener((e) -> {
			int clock = (int) spinner.getValue();
			SystemHandler.setClock(clock);
			OptionFrameClock.this.dispose();
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(ColorSet.boxButton);
		btnCancel.addActionListener((e) -> {
			OptionFrameClock.this.dispose();
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblClock)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner))
						.addComponent(btnApply, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClock)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApply)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		pack();
	}
}
