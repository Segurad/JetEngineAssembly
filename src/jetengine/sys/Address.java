package jetengine.sys;

interface Address {

	public final static Address 
	REGISTER_A = new Address() { public byte get() { return SystemHandler.getRegister().getA(); }	public void set(byte value) { SystemHandler.getRegister().setA(value); } },
	REGISTER_B = new Address() { public byte get() { return SystemHandler.getRegister().getB(); }	public void set(byte value) { SystemHandler.getRegister().setB(value); } },
	REGISTER_C = new Address() { public byte get() { return SystemHandler.getRegister().getC(); }	public void set(byte value) { SystemHandler.getRegister().setC(value); } },
	REGISTER_D = new Address() { public byte get() { return SystemHandler.getRegister().getD(); }	public void set(byte value) { SystemHandler.getRegister().setD(value); } },
	REGISTER_E = new Address() { public byte get() { return SystemHandler.getRegister().getE(); }	public void set(byte value) { SystemHandler.getRegister().setE(value); } },
	REGISTER_H = new Address() { public byte get() { return SystemHandler.getRegister().getH(); }	public void set(byte value) { SystemHandler.getRegister().setH(value); } },
	REGISTER_L = new Address() { public byte get() { return SystemHandler.getRegister().getL(); }	public void set(byte value) { SystemHandler.getRegister().setL(value); } },
	REGISTER_M = new Address() { public byte get() { return SystemHandler.getMemory().get(REGISTER_H.get(), REGISTER_L.get()); }	public void set(byte value) { SystemHandler.getRegister().setL(value); } };
	;
	
	public byte get();
	public void set(byte value);
}
