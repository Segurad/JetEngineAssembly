package jetengine.gui;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import jetengine.sys.ByteUtil;
import jetengine.sys.Message;

import javax.swing.JTextPane;

final class JetEengineHelp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MutableAttributeSet textColor = new SimpleAttributeSet();

	@SuppressWarnings("serial")
	public JetEengineHelp() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(ColorSet.boxOutColor);
		setTitle(Message.JETENGINE+"Help");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/jetengine/icon.png")));
		StyleConstants.setBackground(textColor, ColorSet.boxInColor);
        StyleConstants.setForeground(textColor, ColorSet.boxTextColor);
		
		JList<String> list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {
				"What is JetEngine?",
				"Overview",
				"First steps",
				"Assembler Directives",
				"Samples"
			};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		list.setBackground(ColorSet.boxInColor);
		list.setForeground(ColorSet.boxTextColor);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(list)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
						.addComponent(list, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		scrollPane.setViewportView(textPane);
		getContentPane().setLayout(groupLayout);
		textPane.setBackground(ColorSet.boxInColor);
		textPane.setForeground(ColorSet.boxTextColor);
		textPane.setEditable(false);
		try {
			File f = new File(getClass().getResource("/jetengine/html/overview.html").getFile());
			textPane.read(new InputStreamReader(new FileInputStream(f), "UTF-8"),f);
			textPane.setText(textPane.getText()
					.replace("color: #000000", "color: #" + ByteUtil.toHex(ColorSet.boxTextColor.getRGB(), 6).substring(2))
					.replace("%img%", getClass().getResource("/jetengine/html/img/").toString()));
			textPane.setCaretPosition(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pack();
	}
}
