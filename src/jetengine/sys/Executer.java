package jetengine.sys;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import jetengine.sys.event.ExeListener;
import jetengine.sys.event.ExeListener.State;

public class Executer {

	private Timer t;
	private Memory m;
	private Register r;
	private int clock, compadr = 0;
	private int[] oldmask;
	private boolean run;
	private ArrayList<ExeListener> listeners = new ArrayList<ExeListener>();
	
	public void start(int address) {
		System.out.println("try start executer");
		if (run) return;
		System.out.println("start executer");
		r = SystemHandler.getRegister();
		m = SystemHandler.getMemory();
		clock = 5;
		r.setPC(address);
		t = new Timer();
		run = true;
		listeners.forEach(l -> l.updateState(State.EXE_ALL));
		run();
	}
	
	public void startSignleLine(int address) {
		if (run && t == null) {
			execute();
		} else {
			r = SystemHandler.getRegister();
			m = SystemHandler.getMemory();
			run = true;
			r.setPC(address);
			listeners.forEach(l -> l.updateState(State.EXE_LINE));
			execute();
		}
	}
	
	private void run() {
		System.out.println("try run executer");
		if (!execute()) return;
		TimerTask t = new TimerTask() {
			public void run() {
				Executer.this.run();
			}
		};
		this.t.schedule(t, 1000*clock/SystemHandler.getClock());
	}
	
	private boolean execute() {
		System.out.println("execute");
		int pc = r.getPC();
		pc = pcCheck(pc);
		
		byte raw_opc = m.getValue(pc);
		System.out.println("PC: " + ByteUtil.toHex(pc, 4) + " RawOPC: " + raw_opc + " Hex:" + ByteUtil.toHex(raw_opc, 2));
		Opcode opc = Opcode.getOpcodeByHex(ByteUtil.toHex(raw_opc, 2));
		if (opc == null) { 
			errorOpcodeNull();
			return false;
		}
		int[] mask = m.getEditorMask(pc);
		byte size = opc.getSize();
		clock = opc.getClock();
		Operation op = opc.getOperation();
		
		Address part1 = opc.getAddress1(), part2 = opc.getAddress2();
		byte val1 = 0, val2 = 0;
		String s = "Executing: " + opc.name().replace("_", " ");
		
		if (size == 2) {
			pc++;
			val1 = m.getValue(pc);
			s = s + " " + ByteUtil.toHex(ByteUtil.toUnsignedByte(val1), 2);
		}
		if (size >= 3) {
			pc++;
			val2 = m.getValue(pc);
			pc++;
			val1 = m.getValue(pc);
			s = s + " " + ByteUtil.toHex(val1, 2) + ByteUtil.toHex(val2, 2);
		}
		pc++;
		r.setPC(pc);
		System.out.println("Invoke: " + opc.name() + " v1:" + val1 + " v2:" + val2);
		op.invoke(part1, part2, val1, val2);
		SystemHandler.sendMessage(s);
		if (oldmask != null) {
			SystemHandler.setEditorExeColor(oldmask, StyleConfig.EXE_FALSE);
		}
		if (mask != null) {
			SystemHandler.setEditorExeColor(mask, StyleConfig.EXE_TRUE);
		}
		oldmask = mask;
		if (r.getReset75() == 1) {
			r.setI7((byte) 0);
			r.setReset75((byte) 0);
		}
		if (SystemHandler.getTRAP()) {
			SystemHandler.setTRAP(false);
			OperationRegistry.PUSH.invoke(Address.REG_PC1, Address.REG_PC2, (byte) 0, (byte) 0);
			r.setPC(0x0024);
		} else if (r.getIeff() == 1) {
			if (r.getI7() == 1 && r.getM75() == 0) {
				r.setIeff((byte) 0);
				r.setI7((byte) 0);
				OperationRegistry.PUSH.invoke(Address.REG_PC1, Address.REG_PC2, (byte) 0, (byte) 0);
				r.setPC(0x003C);
			} else if (r.getI6() == 1 && r.getM65() == 0) {
				r.setIeff((byte) 0);
				r.setI6((byte) 0);
				OperationRegistry.PUSH.invoke(Address.REG_PC1, Address.REG_PC2, (byte) 0, (byte) 0);
				r.setPC(0x0034);
			} else if (r.getI5() == 1 && r.getM55() == 0) {
				r.setIeff((byte) 0);
				r.setI5((byte) 0);
				OperationRegistry.PUSH.invoke(Address.REG_PC1, Address.REG_PC2, (byte) 0, (byte) 0);
				r.setPC(0x002C);
			} else if (SystemHandler.getINTR() >= 0) {
				int i = SystemHandler.getINTR();
				r.setIeff((byte) 0);
				SystemHandler.setINTR(-1);
				switch (i) {
				case 0:
					OperationRegistry.RST.invoke(Address.RST_0, null, (byte) 0, (byte) 0);
					break;
				case 1:
					OperationRegistry.RST.invoke(Address.RST_1, null, (byte) 0, (byte) 0);
					break;
				case 2:
					OperationRegistry.RST.invoke(Address.RST_2, null, (byte) 0, (byte) 0);
					break;
				case 3:
					OperationRegistry.RST.invoke(Address.RST_3, null, (byte) 0, (byte) 0);
					break;
				case 4:
					OperationRegistry.RST.invoke(Address.RST_4, null, (byte) 0, (byte) 0);
					break;
				case 5:
					OperationRegistry.RST.invoke(Address.RST_5, null, (byte) 0, (byte) 0);
					break;
				case 6:
					OperationRegistry.RST.invoke(Address.RST_6, null, (byte) 0, (byte) 0);
					break;
				case 7:
					OperationRegistry.RST.invoke(Address.RST_7, null, (byte) 0, (byte) 0);
					break;
				}
			}
		}
		return true;
	}
	
	private void errorOpcodeNull() {
		stop();
		SystemHandler.sendMessage(Message.ERR_EXE_OPCODE_NULL);
		if (oldmask != null) {
			SystemHandler.setEditorExeColor(oldmask, StyleConfig.EXE_FALSE);
		}
	}

	public void stop() {
		if (!run) return;
		run = false;
		if (oldmask != null) {
			SystemHandler.setEditorExeColor(oldmask, StyleConfig.EXE_FALSE);
		}
		listeners.forEach(l -> l.updateState(State.STOP));
		if (t == null) return;
		t.cancel();
		t = null;
	}
	
	public boolean isExecuting() {
		return run;
	}
	
	private int pcCheck(int pc) {
		if (pc > 0xFFFF) {
			pc = 0x0000;
		} else
		if (pc < 0x0000) {
			pc = 0xFFFF;
		}
		return pc;
	}

	public int getStartAddress() {
		return compadr;
	}
	
	public void setStartAddress(int address) {
		this.compadr = address;
	}

	public void addListener(ExeListener listener) {
		if (!listeners.contains(listener)) listeners.add(listener);
	}
	
	public void removeListener(ExeListener listener) {
		listeners.remove(listener);
	}

}
