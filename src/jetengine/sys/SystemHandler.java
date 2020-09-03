package jetengine.sys;

import java.util.ArrayList;

import jetengine.sys.event.ConsolListener;
import jetengine.sys.event.Editor;
import jetengine.sys.event.ProblemListener;

public class SystemHandler {

	private static SystemHandler instance;
	private Register regs;
	private Memory mem;
	private Ports ports;
	private Executer exe;
	private int clock, intr = -1;
	private boolean trap = false;
	private ArrayList<ConsolListener> cons = new ArrayList<ConsolListener>();
	private ArrayList<ProblemListener> pro = new ArrayList<ProblemListener>();
	private ArrayList<Editor> edit = new ArrayList<Editor>();
	
	public SystemHandler() {
		instance = this;
		regs = new Register();
		mem = new Memory();
		ports = new Ports();
		exe = new Executer();
		clock = 10;
	}
	
	public static Register getRegister() {
		return instance.regs;
	}
	
	public static Memory getMemory() {
		return instance.mem;
	}
	
	public static Ports getPorts() {
		return instance.ports;
	}

	public static Executer getExecuter() {
		return instance.exe;
	}

	public static int getClock() {
		return instance.clock;
	}

	public static void sendMessage(String msg) {
		instance.cons.forEach(c -> c.send(msg));
	}

	public static void addConsolListener(ConsolListener listener) {
		if (!instance.cons.contains(listener)) instance.cons.add(listener);
	}
	
	public static void remvoeConsolListener(ConsolListener listener) {
		instance.cons.remove(listener);
	}

	public static void sendProblems(ArrayList<String> problems) {
		instance.pro.forEach(l -> l.updateProblems(problems));
	}

	public static void addProblemListener(ProblemListener listener) {
		if (!instance.pro.contains(listener)) instance.pro.add(listener);
	}

	public static void removeProblemListener(ProblemListener listener) {
		instance.pro.remove(listener);
	}

	public static void addEditor(Editor editor) {
		if (!instance.edit.contains(editor)) instance.edit.add(editor);
	}

	public static void removeEditor(Editor editor) {
		instance.edit.remove(editor);
	}

	public static void selectEditor(Editor editor) {
		for (Editor e : instance.edit) {
			if (editor == e) e.setSelected(true); else e.setSelected(false);
		}
	}

	public static void compileAll() {
		instance.edit.forEach(e -> e.compile());
	}
	
	public static Editor getSelectedEditor() {
		for (Editor e : instance.edit) {
			if (e.isSelected()) return e;
		}
		return null;
	}
	
	public static Editor getEditorByID(int id) {
		for (Editor e : instance.edit) {
			if (e.getID() == id) return e;
		}
		return null;
	}

	public static void setEditorExeColor(int[] mask, int state) {
		Editor e = getEditorByID(mask[2]);
		if (e != null) e.setExeColor(mask[0], mask[1], state);
	}

	public static ArrayList<Editor> getEditors() {
		return new ArrayList<Editor>(instance.edit);
	}

	public static void setClock(int clock) {
		instance.clock = clock;
	}

	public static void setTRAP(boolean val) {
		instance.trap = val;
	}
	
	public static boolean getTRAP() {
		return instance.trap;
	}

	public static void setINTR(int val) {
		instance.intr = val;
	}
	
	public static int getINTR() {
		return instance.intr;
	}

}
