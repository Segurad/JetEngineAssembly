package jetengine.gui.options;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;

import java.awt.GraphicsEnvironment;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;

import jetengine.gui.BaseFrame;
import jetengine.gui.ColorSet;
import jetengine.sys.Config;
import jetengine.sys.SystemHandler;

import javax.swing.JButton;

/**
 * Frame for editor settings
 * @author Segurad
 */
public final class OptionFrameEditor extends BaseFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OptionFrameEditor() {
		super("Editor");
		JLabel lblFont = new JLabel("Font");
		lblFont.setForeground(ColorSet.boxTextColor);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(32);
		comboBox.setBorder(null);
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		comboBox.setModel(new DefaultComboBoxModel(fonts));
		comboBox.setSelectedIndex(Config.editorFont);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(ColorSet.boxTextColor);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(16, 0, null, 1));
		spinner.setBackground(ColorSet.boxInColor);
		spinner.setForeground(ColorSet.boxTextColor);
		
		JButton btnApply = new JButton("Apply and Close");
		btnApply.setBackground(ColorSet.boxButton);
		btnApply.addActionListener((e) -> {
			String font = (String) comboBox.getModel().getSelectedItem();
			int size = (int) spinner.getValue();
			Config.editorFontName = font;
			Config.editorFont = comboBox.getSelectedIndex();
			Config.editorFontSize = size;
			SystemHandler.getEditors().forEach(ed -> ed.updateFont());
			OptionFrameEditor.this.dispose();
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(ColorSet.boxButton);
		btnCancel.addActionListener((e) -> {
			OptionFrameEditor.this.dispose();
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFont)
						.addComponent(lblSize))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnApply, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFont)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSize)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnApply)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		pack();
	}
}
