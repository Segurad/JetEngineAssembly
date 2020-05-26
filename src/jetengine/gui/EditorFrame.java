package jetengine.gui;

import javax.swing.JTextPane;

import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import jetengine.sys.Config;
import jetengine.sys.Message;
import jetengine.sys.StyleConfig;
import jetengine.sys.SynAnalyser;
import jetengine.sys.SystemHandler;
import jetengine.sys.frontcon.Editor;

final class EditorFrame extends AbstractGUIComponent implements Editor {

	private JTextPane editor;
	private StyledDocument doc;
	private MutableAttributeSet[] formid;
	private SynAnalyser san;
	private ArrayList<String> undo = new ArrayList<String>(), redo = new ArrayList<String>();
	private boolean selected = false, saved = false;
	private File file;
	private int id;
    
	
	public EditorFrame(File file) {
		super(file == null ? "NewFile.sim" : file.getName());
		Random r = new Random();
		while(true) {
			int id = r.nextInt(1000);
			if (SystemHandler.getEditorByID(id) == null) {
				this.id = id;
				break;
			}
		}
		saved = file == null ? false : true;
		undo.add("");
		san = new SynAnalyser(SystemHandler.getMemory(), this, id);
		setBackground(ColorSet.boxOutColor);
		SystemHandler.addEditor(this);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		
		GUIToolBar toolBar = new GUIToolBar(this);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
		);
		
		editor = new JTextPane();
		editor.setText("");
		editor.setFont(new Font(Config.editorFont, Font.PLAIN, Config.editorFontSize));
		editor.setBackground(ColorSet.boxInColor);
		editor.setForeground(ColorSet.editorTextDefault);
		editor.setCaretColor(ColorSet.caretColor);
		editor.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				san.notifyAnalyser();
				saved = false;
				updateTitle("*" + getFrameName());
			}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});
		editor.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				SystemHandler.selectEditor(EditorFrame.this);
			}
		});
		scrollPane.setViewportView(editor);
		
		//JPanel panel = new JPanel();
		JPanel panel = new TextLineNumber(editor);
		scrollPane.setRowHeaderView(panel);
		this.setLayout(groupLayout);
		this.file = file;
		if (file != null) {
			open();
		} else {
			updateColor();
			updateTitle("*" + getFrameName());
		}
	}
	
	private void open() {
		FileReader reader=null;
        try {
            reader=new FileReader(file);
            editor.read(reader,file);
        } catch(IOException qq) {
            System.err.println("Error while loading text");
        } finally{
        	updateColor();
        	san.notifyAnalyser();
            if(reader!=null) {
                try{
                    reader.close();
                }catch(IOException qw) {
                    System.err.println("Error closing the file reader");
                }
            }
        }
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void close() {
		if (!isSaved()) {
			saveRequest();
		}
		super.close();
		SystemHandler.removeEditor(this);
	}

	@Override
	public void updateColor() {
		doc = editor.getStyledDocument();
		MutableAttributeSet labelattr = new SimpleAttributeSet();
		MutableAttributeSet opcattr = new SimpleAttributeSet();
		MutableAttributeSet oprattr = new SimpleAttributeSet();
		MutableAttributeSet commattr = new SimpleAttributeSet();
		MutableAttributeSet errorattr = new SimpleAttributeSet();
		MutableAttributeSet defaultattr = new SimpleAttributeSet();
		MutableAttributeSet exelinet = new SimpleAttributeSet();
		MutableAttributeSet exelinef = new SimpleAttributeSet();
		
		StyleConstants.setStrikeThrough(errorattr, true);
		StyleConstants.setBackground(errorattr, ColorSet.boxInColor);
        StyleConstants.setForeground(errorattr, ColorSet.editorTextError);

        StyleConstants.setStrikeThrough(defaultattr, false);
        StyleConstants.setBackground(defaultattr, ColorSet.boxInColor);
        StyleConstants.setForeground(defaultattr, ColorSet.editorTextDefault);

        StyleConstants.setStrikeThrough(labelattr, false);
        StyleConstants.setBackground(labelattr, ColorSet.boxInColor);
        StyleConstants.setForeground(labelattr, ColorSet.editorTextLabel);

        StyleConstants.setStrikeThrough(opcattr, false);
        StyleConstants.setBackground(opcattr, ColorSet.boxInColor);
        StyleConstants.setForeground(opcattr, ColorSet.editorTextOpcode);

        StyleConstants.setStrikeThrough(oprattr, false);
        StyleConstants.setBackground(oprattr, ColorSet.boxInColor);
        StyleConstants.setForeground(oprattr, ColorSet.editorTextOpArg);

        StyleConstants.setStrikeThrough(commattr, false);
        StyleConstants.setBackground(commattr, ColorSet.boxInColor);
        StyleConstants.setForeground(commattr, ColorSet.editorTextComment);
        
        StyleConstants.setBackground(exelinet, ColorSet.editorExeLineTrue);
        StyleConstants.setBackground(exelinef, ColorSet.boxInColor);
        
        formid = new MutableAttributeSet[] {
        		errorattr,
        		defaultattr,
        		labelattr,
        		opcattr,
        		oprattr,
        		commattr,
        		exelinet,
        		exelinef
        };
	}
	
	@Override
	public void attachDefault() {
		MainFrame.getInstance().addComponentUp(this);
	}

	@Override
	public String getText() {
		return editor.getText();
	}

	@Override
	public void updateStyle(StyleConfig config) {
		doc.setCharacterAttributes(config.getOffset(), config.getLength(), formid[1], true);
		while (config.hasNext()) {
			int[] next = config.next();
			System.out.println(next[1] + "|" + next[2]+"|" + next[0]);
			doc.setCharacterAttributes(next[1], next[2], formid[next[0]], false);
		}
	}

	@Override
	public void redo() {
		System.out.println("try redo");
		if (redo.size()-1 <0) return;
		String s = redo.remove(redo.size()-1);
		editor.setText(s);
		san.analyse(editor.getText(), false);
		System.out.println(s);
	}

	@Override
	public void undo() {
		System.out.println("try undo");
		if (undo.size()-1 <0) return;
		String s = undo.remove(undo.size()-1);
		editor.setText(s);
		if (redo.size() >= 10) {
			redo.remove(0);
		}
		redo.add(s);
		san.analyse(editor.getText(), false);
		System.out.println(s);
	}

	@Override
	public void setSelected(boolean b) {
		selected = b;
	}

	@Override
	public void backup() {
		if (undo.size() >= 10) {
			undo.remove(0);
		}
		undo.add(editor.getText());
	}

	@Override
	public void save() {
		if (file == null) {
			showSave();
		} else {
			FileWriter writer=null;
            try {
                writer=new FileWriter(file);
                editor.write(writer);
                saved = true;
                updateTitle(getFrameName());
            } catch(IOException qq) {
                System.err.println("Error while saving");
            } finally{
                if(writer!=null) {
                    try {
                        writer.close();
                    } catch(IOException qw) {
                        System.err.println("Error while closing the file writer");
                    }
                }
            }
		}
	}

	private void showSave() {
		System.out.println("try show save");
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
		
		String extension="";
        JFileChooser chooser=new JFileChooser(".");
        chooser.setFileFilter(simFilter);
        chooser.addChoosableFileFilter(txtFilter);
        File selectedFile;
        int status=chooser.showSaveDialog(null);
        if(status==JFileChooser.APPROVE_OPTION) {
            selectedFile=chooser.getSelectedFile();
        }
        else selectedFile=null;
        if(selectedFile!=null) {
            FileWriter writer=null;
            try{
                if(chooser.getFileFilter() == simFilter) extension=".sim";
                else if(chooser.getFileFilter() == txtFilter) extension=".txt";
                String path = selectedFile.getAbsolutePath();
                writer=new FileWriter(path + (path.endsWith(".sim") ? "" : extension));
                editor.write(writer);

                file=selectedFile;
                setFrameName(file.getName());
                saved = true;
                updateTitle(getFrameName());
            } catch(IOException qq) {
                System.err.println("Error while saving");
            } finally {
                if(writer!=null) {
                    try{
                        writer.close();
                    }catch(IOException qw){
                        System.err.println("Error while closing the file writer");
                    }
                }
            }
        }
	}

	@Override
	public void saveAs() {
		showSave();
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public String getEditorName() {
		return getFrameName();
	}

	@Override
	public void compile() {
		san.analyse(editor.getText(), false);
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public void setExeColor(int offset, int length, int state) {
		doc.setCharacterAttributes(offset, length, formid[state], false);
	}

	@Override
	public void updateFont(String font, int size) {
		editor.setFont(new Font(font, Font.PLAIN, size));
	}

	@Override
	public boolean isSaved() {
		return saved;
	}

	@Override
	public void saveRequest() {
		if (editor.getText().replace(" ", "").equals("")) return;
		int status = JOptionPane.showConfirmDialog(this, "Save changes?", Message.JETENGINE + Config.verison, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (status == JOptionPane.YES_OPTION) {
			save();
		}
	}
}
