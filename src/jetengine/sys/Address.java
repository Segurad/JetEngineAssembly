package jetengine.sys;

interface Address {

	public final static Address 
	REG_A = new Address() { public byte get() { return SystemHandler.getRegister().getA(); }	public void set(byte value) { SystemHandler.getRegister().setA(value); } },
	REG_B = new Address() { public byte get() { return SystemHandler.getRegister().getB(); }	public void set(byte value) { SystemHandler.getRegister().setB(value); } },
	REG_C = new Address() { public byte get() { return SystemHandler.getRegister().getC(); }	public void set(byte value) { SystemHandler.getRegister().setC(value); } },
	REG_D = new Address() { public byte get() { return SystemHandler.getRegister().getD(); }	public void set(byte value) { SystemHandler.getRegister().setD(value); } },
	REG_E = new Address() { public byte get() { return SystemHandler.getRegister().getE(); }	public void set(byte value) { SystemHandler.getRegister().setE(value); } },
	REG_H = new Address() { public byte get() { return SystemHandler.getRegister().getH(); }	public void set(byte value) { SystemHandler.getRegister().setH(value); } },
	REG_L = new Address() { public byte get() { return SystemHandler.getRegister().getL(); }	public void set(byte value) { SystemHandler.getRegister().setL(value); } },
	REG_M = new Address() { public byte get() { return SystemHandler.getMemory().get(REG_H.get(), REG_L.get()); }	public void set(byte value) {} },
	REG_SP1 = new Address() { public byte get() { return ByteUtil.highByte(SystemHandler.getRegister().getStack()); }	public void set(byte value) {
		Register r = SystemHandler.getRegister();
		r.setStack(ByteUtil.toInt(value, ByteUtil.lowByte(r.getStack())));
	} },
	REG_SP2 = new Address() { public byte get() { return ByteUtil.lowByte(SystemHandler.getRegister().getStack()); }	public void set(byte value) {
		Register r = SystemHandler.getRegister();
		r.setStack(ByteUtil.toInt(ByteUtil.highByte(r.getStack()), value));
	} },
	REG_F = new Address() { public byte get() { return SystemHandler.getRegister().getFlags(); }	public void set(byte value) { SystemHandler.getRegister().setFlags(value); } },
	REG_PC1 = new Address() { public byte get() { return ByteUtil.highByte(SystemHandler.getRegister().getPC()); }	public void set(byte value) {
		Register r = SystemHandler.getRegister();
		r.setPC(ByteUtil.toInt(value, ByteUtil.lowByte(r.getPC())));
	} },
	REG_PC2 = new Address() { public byte get() { return ByteUtil.lowByte(SystemHandler.getRegister().getPC()); }	public void set(byte value) {
		Register r = SystemHandler.getRegister();
		r.setPC(ByteUtil.toInt(ByteUtil.highByte(r.getPC()), value));
	} },
	
	FLAG_CY = new Address() { public byte get() { return SystemHandler.getRegister().getCy(); }	public void set(byte value) { SystemHandler.getRegister().setCy(value); } },
	FLAG_S = new Address() { public byte get() { return SystemHandler.getRegister().getS(); }	public void set(byte value) { SystemHandler.getRegister().setS(value); } },
	FLAG_P = new Address() { public byte get() { return SystemHandler.getRegister().getP(); }	public void set(byte value) { SystemHandler.getRegister().setP(value); } },
	FLAG_AC = new Address() { public byte get() { return SystemHandler.getRegister().getAc(); }	public void set(byte value) { SystemHandler.getRegister().setAc(value); } },
	FLAG_Z = new Address() { public byte get() { return SystemHandler.getRegister().getZ(); }	public void set(byte value) { SystemHandler.getRegister().setZ(value); } },
	
	RST_0 = new Address() { public byte get() { return 0x00; }	public void set(byte value) {} },
	RST_1 = new Address() { public byte get() { return 0x08; }	public void set(byte value) {} },
	RST_2 = new Address() { public byte get() { return 0x10; }	public void set(byte value) {} },
	RST_3 = new Address() { public byte get() { return 0x18; }	public void set(byte value) {} },
	RST_4 = new Address() { public byte get() { return 0x20; }	public void set(byte value) {} },
	RST_5 = new Address() { public byte get() { return 0x28; }	public void set(byte value) {} },
	RST_6 = new Address() { public byte get() { return 0x30; }	public void set(byte value) {} },
	RST_7 = new Address() { public byte get() { return 0x38; }	public void set(byte value) {} }
	;
	
	public byte get();
	public void set(byte value);
}
