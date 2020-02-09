package jetengine.sys;

public class Register {

	private int a = 0, b = 0, c = 0, d = 0, e = 0, h = 0, l = 0, stack = 0, pc = 0;
	private byte cy,p,ac,z,s;
	private byte ieff=1,m75,m65,m55,i7,i6,i5;
	private Byte reset75 = null;
	
	public byte getA() {
		return (byte) a;
	}
	public void setA(byte a) {
		this.a = a;
	}
	public byte getB() {
		return (byte) b;
	}
	public void setB(byte b) {
		this.b = b;
	}
	public byte getC() {
		return (byte) c;
	}
	public void setC(byte c) {
		this.c = c;
	}
	public byte getD() {
		return (byte) d;
	}
	public void setD(byte d) {
		this.d = d;
	}
	public byte getE() {
		return (byte) e;
	}
	public void setE(byte e) {
		this.e = e;
	}
	public byte getH() {
		return (byte) h;
	}
	public void setH(byte h) {
		this.h = h;
	}
	public byte getL() {
		return (byte) l;
	}
	public void setL(byte l) {
		this.l = l;
	}
	public int getStack() {
		return stack;
	}
	public void setStack(int stack) {
		this.stack = stack;
	}
	public int getPC() {
		return pc;
	}
	public void setPC(int pc) {
		this.pc = pc;
	}
	public byte getCy() {
		return cy;
	}
	public void setCy(byte cy) {
		this.cy = cy;
	}
	public byte getP() {
		return p;
	}
	public void setP(byte p) {
		this.p = p;
	}
	public byte getAc() {
		return ac;
	}
	public void setAc(byte ac) {
		this.ac = ac;
	}
	public byte getZ() {
		return z;
	}
	public void setZ(byte z) {
		this.z = z;
	}
	public byte getS() {
		return s;
	}
	public void setS(byte s) {
		this.s = s;
	}
	public byte getIeff() {
		return ieff;
	}
	public void setIeff(byte ieff) {
		this.ieff = ieff;
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
	public Byte getReset75() {
		return reset75;
	}
	public void setReset75(Byte reset75) {
		this.reset75 = reset75;
	}
}
