package jetengine.sys;

import java.util.ArrayList;
import java.util.List;

import jetengine.sys.event.InterruptListener;
import jetengine.sys.event.RegListener;

public class Register {

	private int stack = 0, pc = 0;
	private byte a = 0, b = 0, c = 0, d = 0, e = 0, h = 0, l = 0;
	private byte cy,p,ac,z,s;
	private byte ieff=1,m75,m65,m55,i7,i6,i5, reset75 = 0;
	private List<RegListener> listeners = new ArrayList<RegListener>();
	private List<InterruptListener> ilisteners = new ArrayList<InterruptListener>();
	
	public byte getA() {
		return a;
	}
	public void setA(byte a) {
		this.a = a;
		listeners.forEach(l -> l.updateRegA(ByteUtil.toHex(a, 2)));
	}
	
	public void setRegWithFlags(Address address, byte value) {
		address.set(value);
		String binstr = ByteUtil.toBinaryString(value);
		//set Sign
		if (binstr.charAt(0) == '0') setS((byte) 0); else setS((byte) 1);
		//set Zero
		if (ByteUtil.toUnsignedByte(value) == 0) setZ((byte) 1); else setZ((byte) 0);
		//set Parity
		int counter = 0;
		for (int i = 0; i < binstr.length(); i++) {
			if (binstr.charAt(i) == '1') counter++;
		}
		if (counter%2 == 0) setP((byte) 1); else setP((byte) 0);
	}
	
	public byte getB() {
		return b;
	}
	public void setB(byte b) {
		this.b = b;
		listeners.forEach(l -> l.updateRegB(ByteUtil.toHex(b, 2)));
	}
	public byte getC() {
		return c;
	}
	public void setC(byte c) {
		this.c = c;
		listeners.forEach(l -> l.updateRegC(ByteUtil.toHex(c, 2)));
	}
	public byte getD() {
		return d;
	}
	public void setD(byte d) {
		this.d = d;
		listeners.forEach(l -> l.updateRegD(ByteUtil.toHex(d, 2)));
	}
	public byte getE() {
		return e;
	}
	public void setE(byte e) {
		this.e = e;
		listeners.forEach(l -> l.updateRegE(ByteUtil.toHex(e, 2)));
	}
	public byte getH() {
		return h;
	}
	public void setH(byte h) {
		this.h = h;
		listeners.forEach(l -> l.updateRegH(ByteUtil.toHex(h, 2)));
	}
	public byte getL() {
		return l;
	}
	public void setL(byte l) {
		this.l = l;
		listeners.forEach(li -> li.updateRegL(ByteUtil.toHex(l, 2)));
	}
	public int getStack() {
		return stack;
	}
	public void setStack(int stack) {
		this.stack = stack;
		listeners.forEach(l -> l.updateStack(ByteUtil.toHex(stack, 4)));
	}
	public int getPC() {
		return pc;
	}
	public void setPC(int pc) {
		this.pc = pc;
		listeners.forEach(l -> l.updatePC(ByteUtil.toHex(pc, 4)));
	}
	public byte getCy() {
		return cy;
	}
	public void setCy(byte cy) {
		this.cy = cy;
		listeners.forEach(l -> l.updateFlagCarry("" + cy));
	}
	public byte getP() {
		return p;
	}
	public void setP(byte p) {
		this.p = p;
		listeners.forEach(l -> l.updateFlagParity("" + p));
	}
	public byte getAc() {
		return ac;
	}
	public void setAc(byte ac) {
		this.ac = ac;
		listeners.forEach(l -> l.updateFlagAuxillaryCarry("" + ac));
	}
	public byte getZ() {
		return z;
	}
	public void setZ(byte z) {
		this.z = z;
		listeners.forEach(l -> l.updateFlagZero("" + z));
	}
	public byte getS() {
		return s;
	}
	public void setS(byte s) {
		this.s = s;
		listeners.forEach(l -> l.updateFlagSign("" + s));
	}
	public byte getIeff() {
		return ieff;
	}
	public void setIeff(byte ieff) {
		this.ieff = ieff;
		ilisteners.forEach(l -> l.updateIEFF(ieff));
	}
	public byte getM75() {
		return m75;
	}
	public void setM75(byte m75) {
		this.m75 = m75;
	}
	public byte getM65() {
		return m65;
	}
	public void setM65(byte m65) {
		this.m65 = m65;
	}
	public byte getM55() {
		return m55;
	}
	public void setM55(byte m55) {
		this.m55 = m55;
	}
	public byte getI7() {
		return i7;
	}
	public void setI7(byte i7) {
		this.i7 = i7;
	}
	public byte getI6() {
		return i6;
	}
	public void setI6(byte i6) {
		this.i6 = i6;
	}
	public byte getI5() {
		return i5;
	}
	public void setI5(byte i5) {
		this.i5 = i5;
	}
	public byte getReset75() {
		return reset75;
	}
	public void setReset75(byte reset75) {
		this.reset75 = reset75;
	}
	
	public byte getFlags() {
		return (byte) Integer.parseInt(s  + "" + z + "0" + ac + "0" + p + "0" + cy, 2);
	}
	
	public void setFlags(byte value) {
		String str = ByteUtil.toBinaryString(value);
		s = (byte) (str.charAt(7) == '0' ? 0 : 1);
		z = (byte) (str.charAt(6) == '0' ? 0 : 1);
		ac = (byte) (str.charAt(4) == '0' ? 0 : 1);
		p = (byte) (str.charAt(2) == '0' ? 0 : 1);
		cy = (byte) (str.charAt(0) == '0' ? 0 : 1);
	}
	public void removeRegListener(RegListener listener) {
		listeners.remove(listener);
	}
	public void addRegListener(RegListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	public void removeInterruptListener(InterruptListener listener) {
		ilisteners.remove(listener);
	}
	public void addInterrupListener(InterruptListener listener) {
		if (!ilisteners.contains(listener)) {
			ilisteners.add(listener);
			listener.updateIEFF(ieff);
		}
	}
}
