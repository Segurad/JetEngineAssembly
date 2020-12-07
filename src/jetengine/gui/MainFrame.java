package jetengine.gui;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;

import jetengine.sys.ByteUtil;
import jetengine.sys.Config;
import jetengine.sys.Message;
import jetengine.sys.SystemHandler;
import jetengine.sys.event.Editor;
import jetengine.sys.event.ExeListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JCheckBox;

public final class MainFrame extends JFrame implements ExeListener {
	
	private final JTabbedPane tp1, tp2, tp3, tp4;
	private static MainFrame instance;
	private JButton btnExeAll, btnExeLine, btnExeStop;
	
	public MainFrame() {
		instance = this;
		SystemHandler.getExecuter().addListener(this);
		setTitle(Message.JETENGINE+Config.verison);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/jetengine/icon.png")));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				for (Editor ed : SystemHandler.getEditors()) {
					if (!ed.isSaved()) ed.saveRequest();
				}
			}
		});
		
		initMenuBar();
		initToolBar();
		
		tp1 = new JTabbedPane(JTabbedPane.TOP);
		tp2 = new JTabbedPane(JTabbedPane.TOP);
		tp3 = new JTabbedPane(JTabbedPane.TOP);
		tp4 = new JTabbedPane(JTabbedPane.TOP);
		tp1.setFocusable(false);
		tp2.setFocusable(false);
		tp3.setFocusable(false);
		tp4.setFocusable(false);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(ColorSet.boxOutColor);
		GroupLayout gl_panel1 = new GroupLayout(panel1);
		gl_panel1.setHorizontalGroup(
		gl_panel1.createParallelGroup(Alignment.LEADING).addComponent(tp1));
		gl_panel1.setVerticalGroup(
		gl_panel1.createParallelGroup(Alignment.LEADING).addComponent(tp1));
		panel1.setLayout(gl_panel1);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(ColorSet.boxOutColor);
		GroupLayout gl_panel2 = new GroupLayout(panel2);
		gl_panel2.setHorizontalGroup(
		gl_panel2.createParallelGroup(Alignment.LEADING).addComponent(tp2));
		gl_panel2.setVerticalGroup(
		gl_panel2.createParallelGroup(Alignment.LEADING).addComponent(tp2));
		panel2.setLayout(gl_panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(ColorSet.boxOutColor);
		GroupLayout gl_panel3 = new GroupLayout(panel3);
		gl_panel3.setHorizontalGroup(
		gl_panel3.createParallelGroup(Alignment.LEADING).addComponent(tp3));
		gl_panel3.setVerticalGroup(
		gl_panel3.createParallelGroup(Alignment.LEADING).addComponent(tp3));
		panel3.setLayout(gl_panel3);
		
		JPanel panel4 = new JPanel();
		panel4.setBackground(ColorSet.boxOutColor);
		GroupLayout gl_panel4 = new GroupLayout(panel4);
		gl_panel4.setHorizontalGroup(
		gl_panel4.createParallelGroup(Alignment.LEADING).addComponent(tp4));
		gl_panel4.setVerticalGroup(
		gl_panel4.createParallelGroup(Alignment.LEADING).addComponent(tp4));
		panel4.setLayout(gl_panel4);
		
		JSplitPane p1 = new JSplitPane();
		p1.setOneTouchExpandable(true);
		p1.setBorder(null);
		p1.setDividerSize(10);
		getContentPane().add(p1, BorderLayout.CENTER);
		
		JSplitPane p2 = new JSplitPane();
		p2.setOneTouchExpandable(true);
		p2.setBorder(null);
		p2.setDividerSize(10);
		p2.setDividerLocation(500);
		p2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		p1.setRightComponent(p2);
		
		JSplitPane p3 = new JSplitPane();
		p3.setOneTouchExpandable(true);
		p3.setBorder(null);
		p3.setDividerSize(10);
		p2.setLeftComponent(p3);
		p2.setRightComponent(panel3);
		
		
		p3.setLeftComponent(panel2);
		p3.setRightComponent(panel4);
		p3.setDividerLocation(610);
		
		
		p1.setLeftComponent(panel1);
		p1.setDividerLocation(300);
		EditorFrame editor = new EditorFrame(null);
		editor.attachDefault();
		SystemHandler.selectEditor(editor);
		new PortFrame().attachDefault();
		new RegFrame().attachDefault();
		new MemFrame().attachDefault();
		new ProblemFrame().attachDefault();
		new ConsolFrame().attachDefault();
		new InterruptFrame().attachDefault();
		new ConverterFrame().attachDefault();
		pack();
	}
	
	public void addComponentLeft(AbstractGUIComponent comp) {
		comp.attach(tp1);
	}
	
	public void addComponentRight(AbstractGUIComponent comp) {
		comp.attach(tp4);
	}
	
	public void addComponentUp(AbstractGUIComponent comp) {
		comp.attach(tp2);
	}
	
	public void addComponentDown(AbstractGUIComponent comp) {
		comp.attach(tp3);
	}

	static MainFrame getInstance() {
		return instance;
	}
	
	private void initMenuBar() {
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		{
			JMenu mnFile = new JMenu("File");
			bar.add(mnFile);
			
			JMenuItem mntmNew = new JMenuItem("New");
			mnFile.add(mntmNew);
			mntmNew.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new EditorFrame(null).attachDefault();
				}
			});
			
			JMenuItem mntmOpen = new JMenuItem("Open File");
			mnFile.add(mntmOpen);
			mntmOpen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showOpen();
				}
			});
			
			JSeparator separator_1 = new JSeparator();
			mnFile.add(separator_1);
			
			JMenuItem mntmSave = new JMenuItem("Save");
			mnFile.add(mntmSave);
			mntmSave.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Editor ed = SystemHandler.getSelectedEditor();
					if (ed != null) ed.save();
				}
			});
			
			JMenuItem mntmSaveAs = new JMenuItem("Save As...");
			mnFile.add(mntmSaveAs);
			mntmSaveAs.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Editor ed = SystemHandler.getSelectedEditor();
					if (ed != null) ed.saveAs();
				}
			});
			
			JSeparator separator = new JSeparator();
			mnFile.add(separator);
			
			JMenuItem mntmExit = new JMenuItem("Exit");
			mnFile.add(mntmExit);
		}
		{
			JMenu mnManage = new JMenu("Manage");
			bar.add(mnManage);
			
			JMenuItem mntmRegister = new JMenuItem("Register");
			mntmRegister.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/reg_btn.png")));
			mnManage.add(mntmRegister);
			mntmRegister.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new OptionFrameReg().setVisible(true);
				}
			});
			
			JMenuItem mntmMemory = new JMenuItem("Memory");
			mntmMemory.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/mem_btn.png")));
			mnManage.add(mntmMemory);
			mntmMemory.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new OptionFrameMem().setVisible(true);
				}
			});
			
			JMenuItem mntmClock = new JMenuItem("Clock Frequency");
			mntmClock.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/clock_btn.png")));
			mnManage.add(mntmClock);
			mntmClock.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new OptionFrameClock().setVisible(true);
				}
			});
		}
		{
			JMenu mnWindow = new JMenu("Window");
			bar.add(mnWindow);
			{
				JMenu mnShowView = new JMenu("Show View");
				mnWindow.add(mnShowView);
				
				JMenuItem mntmConsol = new JMenuItem("Consol");
				mnShowView.add(mntmConsol);
				mntmConsol.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new ConsolFrame().attachDefault();
					}
				});
				
				JMenuItem mntmProblems = new JMenuItem("Problems");
				mnShowView.add(mntmProblems);
				mntmProblems.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new ProblemFrame().attachDefault();
					}
				});
				
				JMenuItem mntmPorts = new JMenuItem("Ports");
				mnShowView.add(mntmPorts);
				mntmPorts.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new PortFrame().attachDefault();
					}
				});
				
				JMenuItem mntmMemory = new JMenuItem("Memory");
				mntmMemory.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/mem_btn.png")));
				mnShowView.add(mntmMemory);
				mntmMemory.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new MemFrame().attachDefault();
					}
				});
				
				JMenuItem mntmRegister = new JMenuItem("Register");
				mntmRegister.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/reg_btn.png")));
				mnShowView.add(mntmRegister);
				mntmRegister.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new RegFrame().attachDefault();
					}
				});
				
				JMenuItem mntmConverter = new JMenuItem("Converter");
				mnShowView.add(mntmConverter);
				mntmConverter.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new ConverterFrame().attachDefault();
					}
				});
			}
			{
				JMenuItem mnEditor = new JMenuItem("Editor");
				mnWindow.add(mnEditor);
				mnEditor.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new OptionFrameEditor().setVisible(true);
					}
				});
			}
		}
		
		JMenu mnHelp = new JMenu("Help");
		bar.add(mnHelp);
		
		JMenuItem mntmJetEngine = new JMenuItem("JetEngine");
		mnHelp.add(mntmJetEngine);
		mntmJetEngine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JetEengineHelp().setVisible(true);
			}
		});
		
		JMenuItem menuItem = new JMenuItem("8085");
		mnHelp.add(menuItem);
	}
	
	private void initToolBar() {
		JToolBar toolBarMaster = new JToolBar();
		toolBarMaster.setRollover(true);
		toolBarMaster.setBorder(null);
		toolBarMaster.setFloatable(false);
		getContentPane().add(toolBarMaster, BorderLayout.NORTH);
		toolBarMaster.setBackground(ColorSet.boxOutColor);
		
		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		
		{
			JToolBar toolBar1 = new JToolBar();
			toolBarMaster.add(toolBar1);
			toolBar1.setBorder(null);
			toolBar1.setFloatable(false);
			toolBar1.setRollover(true);
			toolBar1.setBackground(ColorSet.boxOutColor);
			
			JButton btnNew = new JButton();
			btnNew.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/new_btn.png")));
			btnNew.setOpaque(false);
			btnNew.setBorder(border);
			btnNew.setToolTipText("New");
			btnNew.setFocusable(false);
			toolBar1.add(btnNew);
			btnNew.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new EditorFrame(null).attachDefault();
				}
			});
			
			JButton btnSave = new JButton();
			btnSave.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/save_btn.png")));
			btnSave.setOpaque(false);
			btnSave.setBorder(border);
			btnSave.setToolTipText("Save");
			btnSave.setFocusable(false);
			toolBar1.add(btnSave);
			btnSave.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Editor ed = SystemHandler.getSelectedEditor();
					if (ed != null) ed.save();
				}
			});
		}
		
		JSeparator separator1 = new JSeparator();
		separator1.setMaximumSize(new Dimension(5, 30));
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setForeground(ColorSet.boxInColor);
		separator1.setBackground(ColorSet.boxButton);
		toolBarMaster.add(separator1);
		
		{
			JToolBar toolBar2 = new JToolBar();
			toolBarMaster.add(toolBar2);
			toolBar2.setBorder(null);
			toolBar2.setFloatable(false);
			toolBar2.setBackground(ColorSet.boxOutColor);
			
			JButton btnUndo = new JButton();
			btnUndo.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/back_btn.png")));
			btnUndo.setOpaque(false);
			btnUndo.setBorder(border);
			btnUndo.setToolTipText("Undo");
			btnUndo.setFocusable(false);
			toolBar2.add(btnUndo);
			btnUndo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Editor ed = SystemHandler.getSelectedEditor();
					if (ed != null) ed.undo();
				}
			});
			
			JButton btnRedo = new JButton();
			btnRedo.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/for_btn.png")));
			btnRedo.setOpaque(false);
			btnRedo.setBorder(border);
			btnRedo.setToolTipText("Redo");
			btnRedo.setFocusable(false);
			toolBar2.add(btnRedo);
			btnRedo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Editor ed = SystemHandler.getSelectedEditor();
					if (ed != null) ed.redo();
				}
			});
		}
		
		JSeparator separator2 = new JSeparator();
		separator2.setMaximumSize(new Dimension(5, 30));
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setForeground(ColorSet.boxInColor);
		separator2.setBackground(ColorSet.boxButton);
		toolBarMaster.add(separator2);
		
		{
			JToolBar toolBar3 = new JToolBar();
			toolBar3.setFloatable(true);
			toolBar3.setBorder(null);
			toolBarMaster.add(toolBar3);
			toolBar3.setRollover(true);
			toolBar3.setBackground(ColorSet.boxOutColor);
			
			JButton btnCompileAll = new JButton();
			btnCompileAll.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/comp_btn.png")));
			btnCompileAll.setOpaque(false);
			btnCompileAll.setBorder(border);
			btnCompileAll.setToolTipText("Compile All");
			btnCompileAll.setFocusable(false);
			toolBar3.add(btnCompileAll);
			btnCompileAll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SystemHandler.compileAll();
				}
			});
			
			JCheckBox cbxExeAddress = new JCheckBox("Start Address:");
			toolBar3.add(cbxExeAddress);
			cbxExeAddress.setFocusable(false);
			cbxExeAddress.setBackground(ColorSet.boxOutColor);
			cbxExeAddress.setForeground(ColorSet.boxTextColor);
			
			JTextField tfExeAddress = new JTextField("0000");
			tfExeAddress.setMaximumSize(new Dimension(50, 30));
			tfExeAddress.setFont(new Font("Consolas", Font.PLAIN, 14));
			tfExeAddress.setHorizontalAlignment(SwingConstants.CENTER);
			toolBar3.add(tfExeAddress);
			tfExeAddress.setColumns(4);
			tfExeAddress.setBorder(border);
			tfExeAddress.setBackground(ColorSet.boxInColor);
			tfExeAddress.setForeground(ColorSet.boxTextColor);
			tfExeAddress.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String s = tfExeAddress.getText();
					if (s.length() == 4) {
						if (!ByteUtil.validHex(s)) tfExeAddress.setText("0000");
					} else tfExeAddress.setText("0000");
				}
			});
			
			btnExeAll = new JButton();
			btnExeAll.setDisabledIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/exe_btn_disable.png")));
			btnExeAll.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/exe_btn.png")));
			btnExeAll.setOpaque(false);
			btnExeAll.setBorder(border);
			btnExeAll.setToolTipText("Execute");
			btnExeAll.setFocusable(false);
			toolBar3.add(btnExeAll);
			btnExeAll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int adr = 0;
					if (cbxExeAddress.isSelected()) {
						String s = tfExeAddress.getText();
						if (s.length() == 4) {
							if (ByteUtil.validHex(s)) {
								adr = Integer.parseInt(s, 16);
							} else tfExeAddress.setText("0000");
						} else tfExeAddress.setText("0000");
					} else adr = SystemHandler.getExecuter().getStartAddress();
					SystemHandler.getExecuter().start(adr);
				}
			});
			
			btnExeLine = new JButton();
			btnExeLine.setDisabledIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/exe_line_btn_disable.png")));
			btnExeLine.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/exe_line_btn.png")));
			btnExeLine.setOpaque(false);
			btnExeLine.setBorder(border);
			btnExeLine.setToolTipText("Execute single line");
			btnExeLine.setFocusable(false);
			toolBar3.add(btnExeLine);
			btnExeLine.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int adr = 0;
					if (cbxExeAddress.isSelected()) {
						String s = tfExeAddress.getText();
						if (s.length() == 4) {
							if (ByteUtil.validHex(s)) {
								adr = Integer.parseInt(s, 16);
							} else tfExeAddress.setText("0000");
						} else tfExeAddress.setText("0000");
					} else adr = SystemHandler.getExecuter().getStartAddress();
					SystemHandler.getExecuter().startSignleLine(adr);
				}
			});
			
			btnExeStop = new JButton();
			btnExeStop.setDisabledIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/exe_stop_btn_disable.png")));
			btnExeStop.setIcon(new ImageIcon(MainFrame.class.getResource("/jetengine/exe_stop_btn.png")));
			btnExeStop.setOpaque(false);
			btnExeStop.setBorder(border);
			btnExeStop.setToolTipText("Execute stop");
			btnExeStop.setFocusable(false);
			btnExeStop.setEnabled(false);
			toolBar3.add(btnExeStop);
			btnExeStop.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SystemHandler.getExecuter().stop();
				}
			});
		}

	}
	
    public void showOpen() {
    	FileFilter simFilter= new FileFilter() {
			@Override
			public boolean accept(File file) {
				if (file == null) return false;
				if(file.isDirectory()) return true;
			    String path=file.getAbsolutePath().toLowerCase();
			    if(path.endsWith(".sim")) return true;
			    return false;
			}

			@Override
			public String getDescription() {
				return "8085 sim files";
			}
		};
	    FileFilter txtFilter= new FileFilter() {
			@Override
			public boolean accept(File file) {
				if (file == null) return false;
				if(file.isDirectory()) return true;
			    String path=file.getAbsolutePath().toLowerCase();
			    if(path.endsWith(".txt")) return true;
			    return false;
			}

			@Override
			public String getDescription() {
				return "Text files";
			}
		};
    	
        JFileChooser chooser=new JFileChooser(".");
        chooser.setFileFilter(simFilter);
        chooser.addChoosableFileFilter(txtFilter);
        File selectedFile;
        int status=chooser.showOpenDialog(null);
        if(status==JFileChooser.APPROVE_OPTION)
        {
            selectedFile=chooser.getSelectedFile();
        }
        else selectedFile=null;
        if(selectedFile!=null)
        {
           EditorFrame e = new EditorFrame(selectedFile);
           e.attachDefault();
        }
    }
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void updateState(State state) {
		if (state == State.EXE_ALL) {
			btnExeAll.setEnabled(false);
			btnExeLine.setEnabled(false);
			btnExeStop.setEnabled(true);
		} else if (state == State.EXE_LINE) {
			btnExeAll.setEnabled(false);
			btnExeLine.setEnabled(true);
			btnExeStop.setEnabled(true);
		} else {
			btnExeAll.setEnabled(true);
			btnExeLine.setEnabled(true);
			btnExeStop.setEnabled(false);
		}
	}

}
