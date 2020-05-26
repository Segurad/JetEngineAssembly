package jetengine.gui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public abstract class AbstractGUIComponent extends JPanel {
	

	private static final long serialVersionUID = 1L;
	private JTabbedPane attached, old;
	private String name, tname;
	private JFrame window;
	
	public AbstractGUIComponent(String name) {
		this.name = name;
		tname = name;
	}
	
	public void close() {
		if (isAttached()) attached.remove(this);
		if (window != null) window.dispose();
	}
	
	public boolean isAttached() {
		return attached != null;
	}
	
	public void attach(JTabbedPane pane) {
		attached = pane;
		attached.addTab(tname, this);
	}
	
	public void openInWindow() {
		if (isAttached()) attached.remove(this);
		if (window != null) {
			attach(old);
			window.dispose();
			window = null;
		} else {
			old = attached;
			attached = null;
			window = new JFrame(tname);
			window.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/jetengine/icon.png")));
			window.setContentPane(this);
			window.setVisible(true);
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			window.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					window = null;
					close();
				}
			});
			window.pack();
		}
	}

	public void moveTabLeft() {
		if (attached == null) return;
		int index = attached.indexOfComponent(this);
		if (index <= 0) return;
		index--;
		AbstractGUIComponent[] comps = new AbstractGUIComponent[attached.getTabCount()];
		for (int i = 0; i < attached.getTabCount(); i++) {
			Component comp = attached.getComponentAt(i);
			if (i == index) {
				comps[i] = this;
				i++;
				comps[i] = (AbstractGUIComponent) comp;
			} else comps[i] = (AbstractGUIComponent) comp;
		}
		attached.removeAll();
		final JTabbedPane p = attached;
		for (AbstractGUIComponent comp : comps) {
			comp.attach(p);
		}
	}

	public void moveTabRight() {
		if (attached == null) return;
		int index = attached.indexOfComponent(this);
		if (index +1 >= attached.getTabCount()) return;
		AbstractGUIComponent[] comps = new AbstractGUIComponent[attached.getTabCount()];
		for (int i = 0; i < attached.getTabCount(); i++) {
			Component comp = attached.getComponentAt(i);
			if (i == index) {
				comps[i] = (AbstractGUIComponent) attached.getComponent(i+1);
				i++;
				comps[i] = this;
			} else comps[i] = (AbstractGUIComponent) comp;
		}
		attached.removeAll();
		final JTabbedPane p = attached;
		for (AbstractGUIComponent comp : comps) {
			comp.attach(p);
		}
	}
	
	public abstract void attachDefault();
	
	protected void setFrameName(String name) {
		this.name = name;
	}
	
	protected String getFrameName() {
		return name;
	}
	
	public void updateTitle(String title) {
		tname = title;
		if (isAttached()) {
			for (int i = 0; i < attached.getTabCount(); i++) {
				if (attached.getComponentAt(i) == this) {
					attached.setTitleAt(i, title);
					return;
				}
			}
		} else if (window != null) {
			window.setTitle(title);
		}
	}
	
	public void openOptions() {}
}
