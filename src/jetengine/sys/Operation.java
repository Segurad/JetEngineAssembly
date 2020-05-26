package jetengine.sys;

abstract class Operation {

	public static final Operation 
	ADC,
	ADD,
	ANA,
	CALL,
	CALL_FLAG_1,
	CALL_FLAG_0,
	CMA,
	CMC,
	CMP,
	DAA,
	DAD,
	DCR,
	DCX,
	DI,
	EI,
	HLT,
	IN,
	OUT,
	INR,
	INX,
	JMP,
	JMP_FLAG_1,
	JMP_FLAG_0,
	LDA,
	LHLD,
	STA,
	LXI,
	MOV,
	NOP,
	ORA,
	POP,
	PUSH,
	RAL,
	RLC,
	RAR,
	RRC,
	RET,
	RET_FLAG_1,
	RET_FLAG_0,
	RIM,
	SIM,
	RST,
	SBB,
	SHLD,
	STC,
	SUB,
	XCHG,
	XRA,
	PCHL
	;
	public abstract void invoke(Address part1, Address part2, byte value1, byte value2);

	static {
		ADD = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				int val1 = ByteUtil.toUnsignedByte(Address.REG_A.get());
				int val2 = ByteUtil.toUnsignedByte(part1 != null ? part1.get() : value1);
				int newval = val1 + val2;
				Register r = SystemHandler.getRegister();
				if (newval> 0xFF) r.setCy((byte) 1);
				//System.out.println("val1: " + val1 + " val2: " + val2);
				//System.out.println("newval: " + (byte) newval + " " + Integer.toHexString(newval));
				r.setRegWithFlags(Address.REG_A, (byte) newval);
				
				String s = ByteUtil.toBinaryString(val1);
				val1 = Integer.parseInt(s.substring(4));
				s = ByteUtil.toBinaryString(val2);
				val2 = Integer.parseInt(s.substring(4));
				if (val1 + val2 > 0xF) r.setAc((byte) 1); else r.setAc((byte) 0);
			}
		};
		ANA = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				byte mask = part1 != null ? part1.get() : value1;
				r.setRegWithFlags(Address.REG_A, (byte) ByteUtil.bitwiseAND(r.getA(), mask));
				r.setAc((byte) 0);
				r.setCy((byte) 1);
			}
		};
		ADC = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				int val1 = ByteUtil.toUnsignedByte(Address.REG_A.get());
				int val2 = ByteUtil.toUnsignedByte(part1 != null ? part1.get() : value1);
				int newval = val1 + val2;
				{
					String s = ByteUtil.toBinaryString(val1);
					int tval1 = Integer.parseInt(s.substring(4));
					s = ByteUtil.toBinaryString(val2);
					int tval2 = Integer.parseInt(s.substring(4));
					if (tval1 + tval2 > 0xF) r.setAc((byte) 1); else r.setAc((byte) 0);
				}
				val2 = ByteUtil.toUnsignedByte(Address.FLAG_CY.get());
				if (r.getAc() == 0) {
					newval += val2;
					{
						String s = ByteUtil.toBinaryString(val1);
						int tval1 = Integer.parseInt(s.substring(4));
						s = ByteUtil.toBinaryString(val2);
						int tval2 = Integer.parseInt(s.substring(4));
						if (tval1 + tval2 > 0xF) r.setAc((byte) 1); else r.setAc((byte) 0);
					}
				} else {
					newval += val2;
				}
				if (newval> 0xFF) r.setCy((byte) 1);
				r.setRegWithFlags(Address.REG_A, (byte) newval);
			}
		};
		CALL = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				Memory m = SystemHandler.getMemory();
				int pc = r.getPC();
				int sp = r.getStack();
				sp--;
				if (sp < 0) sp = 0xFFFF;
				m.set(sp, ByteUtil.highByte(pc));
				sp--;
				if (sp < 0) sp = 0xFFFF;
				m.set(sp, ByteUtil.lowByte(pc));
				r.setStack(sp);
				r.setPC(ByteUtil.toInt(value1, value2));
			}
		};
		CALL_FLAG_1 = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				if (part1.get() == 1) CALL.invoke(part1, part2, value1, value2);
			}
		};
		CALL_FLAG_0 = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				if (part1.get() == 0) CALL.invoke(part1, part2, value1, value2);
			}
		};
		CMA = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Address.REG_A.set(ByteUtil.bitwiseXOP(Address.REG_A.get(), (byte) 0xFF));
			}
		};
		CMC = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				r.setCy((byte) (r.getCy() == 1 ? 0 : 1));
			}
		};
		CMP = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				byte back = Address.REG_A.get();
				SUB.invoke(part1, part2, value1, value2);
				Address.REG_A.set(back);
			}
		};
		DAA = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				String a = ByteUtil.toHex(r.getA(), 2);
				int low = Integer.parseInt(""+a.charAt(1), 16);
				int high = Integer.parseInt("" + a.charAt(0), 16);
				String addval = "";
				if (high > 9 || r.getCy() == 1) {
					addval+="6";
				} else addval+="0";
				if (low > 0 || r.getAc() == 1) {
					addval+="6";
				} else addval += "0";
				ADD.invoke(null, null, (byte) Integer.parseInt(addval, 16), value2);
			}
		};
		DAD = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				int val1 = ByteUtil.toInt(r.getH(), r.getL());
				int val2 = ByteUtil.toInt(part1.get(), part2.get());
				int res = val1 + val2;
				if (res > 0xFFFF) {
					r.setCy((byte) 1);
				}
				r.setH(ByteUtil.highByte(res));
				r.setL(ByteUtil.lowByte(res));
			}
		};
		DCR = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				int val1 = ByteUtil.toUnsignedByte(part1.get());
				int val2 = 1;
				int newval = val1 - val2;
				Register r = SystemHandler.getRegister();
				r.setRegWithFlags(Address.REG_A, (byte) newval);
				
				String s = ByteUtil.toBinaryString(val1);
				val1 = Integer.parseInt(s.substring(4));
				s = ByteUtil.toBinaryString(val2);
				val2 = Integer.parseInt(s.substring(4));
				if (val1 < val2) r.setAc((byte) 1); else r.setAc((byte) 0);
			}
		};
		DCX = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				int val = ByteUtil.toInt(part1.get(), part2.get());
				val--;
				part1.set(ByteUtil.highByte(val));
				part2.set(ByteUtil.lowByte(val));
			}
		};
		DI = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				SystemHandler.getRegister().setIeff((byte) 0);
			}
		};
		EI = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				SystemHandler.getRegister().setIeff((byte) 1);
			}
		};
		HLT = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				SystemHandler.getExecuter().stop();
			}
		};
		IN = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Address.REG_A.set(SystemHandler.getPorts().getValue(value1));
			}
		};
		OUT = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				SystemHandler.getPorts().set(value1, Address.REG_A.get(), true);
			}
		};
		INR = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				int val1 = ByteUtil.toUnsignedByte(part1.get());
				int val2 = 1;
				int newval = val1 + val2;
				Register r = SystemHandler.getRegister();
				r.setRegWithFlags(part1, (byte) newval);
				
				String s = ByteUtil.toBinaryString(val1);
				val1 = Integer.parseInt(s.substring(4));
				s = ByteUtil.toBinaryString(val2);
				val2 = Integer.parseInt(s.substring(4));
				if (val1 + val2 > 0xF) r.setAc((byte) 1); else r.setAc((byte) 0);
			}
		};
		INX = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				int val = ByteUtil.toInt(part1.get(), part2.get());
				val++;
				part1.set(ByteUtil.highByte(val));
				part2.set(ByteUtil.lowByte(val));
			}
		};
		JMP = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				SystemHandler.getRegister().setPC(ByteUtil.toInt(value1, value2));
			}
		};
		JMP_FLAG_1 = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				if (part1.get() == 1) SystemHandler.getRegister().setPC(ByteUtil.toInt(value1, value2));
			}
		};
		JMP_FLAG_0 = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				if (part1.get() == 0) SystemHandler.getRegister().setPC(ByteUtil.toInt(value1, value2));
			}
		};
		LDA = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				if (part1 == null) Address.REG_A.set(SystemHandler.getMemory().get(value1, value2));
				else Address.REG_A.set(SystemHandler.getMemory().get(part1.get(), part2.get()));
			}
		};
		LHLD = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				int val = ByteUtil.toInt(value1, value2);
				Address.REG_H.set(SystemHandler.getMemory().getValue(val));
				Address.REG_L.set(SystemHandler.getMemory().getValue(val + 1));
			}
		};
		STA = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				if (part1 == null) SystemHandler.getMemory().set(value1, value2, Address.REG_A.get());
				else SystemHandler.getMemory().set(part1.get(), part2.get(), Address.REG_A.get());
			}
		};
		LXI = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				part1.set(value1);
				part2.set(value2);
			}
		};
		MOV = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				part1.set((part2 != null ? part2.get() : value1));
			}
		};
		NOP = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {}
		};
		ORA = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				byte mask = part1 != null ? part1.get() : value1;
				r.setRegWithFlags(Address.REG_A, ByteUtil.bitwiseOR(Address.REG_A.get(), mask));
				r.setAc((byte) 0);
				r.setCy((byte) 0);
			}
		};
		POP = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				Memory m = SystemHandler.getMemory();
				int sp = r.getStack();
				byte low = m.getValue(sp);
				sp++;
				if (sp > 0xFFFF) sp = 0x0000;
				byte high = m.getValue(sp);
				sp++;
				if (sp < 0) sp = 0xFFFF;
				r.setStack(sp);
				part1.set(high);
				part2.set(low);
			}
		};
		PUSH = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				Memory m = SystemHandler.getMemory();
				int sp = r.getStack();
				sp--;
				if (sp < 0) sp = 0xFFFF;
				m.set(sp, part1.get());
				sp--;
				if (sp < 0) sp = 0xFFFF;
				m.set(sp, part2.get());
				r.setStack(sp);
			}
		};
		RAL = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				byte b = Address.REG_A.get();
				byte cy = ByteUtil.getBit(b, 7);
				b = ByteUtil.shiftLeft(b, 1);
				b = (Address.FLAG_CY.get() == 1 ? ByteUtil.setBit(b, 0) : ByteUtil.resetBit(b, 0));
				Address.FLAG_CY.set(cy);
				Address.REG_A.set(b);
			}
		};
		RLC = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				byte b = Address.REG_A.get();
				byte cy = ByteUtil.getBit(b, 7);
				b = ByteUtil.shiftLeft(b, 1);
				b = (cy == 1 ? ByteUtil.setBit(b, 0) : ByteUtil.resetBit(b, 0));
				Address.FLAG_CY.set(cy);
				Address.REG_A.set(b);
			}
		};
		RAR = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				byte b = Address.REG_A.get();
				byte cy = ByteUtil.getBit(b, 0);
				b = ByteUtil.shiftRight(b, 1);
				b = (Address.FLAG_CY.get() == 1 ? ByteUtil.setBit(b, 7) : ByteUtil.resetBit(b, 7));
				Address.FLAG_CY.set(cy);
				Address.REG_A.set(b);
			}
		};
		RRC = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				byte b = Address.REG_A.get();
				byte cy = ByteUtil.getBit(b, 0);
				b = ByteUtil.shiftRight(b, 1);
				b = (cy == 1 ? ByteUtil.setBit(b, 7) : ByteUtil.resetBit(b, 7));
				Address.FLAG_CY.set(cy);
				Address.REG_A.set(b);
			}
		};
		RET = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				Memory m = SystemHandler.getMemory();
				int sp = r.getStack();
				byte low = m.getValue(sp);
				sp++;
				if (sp > 0xFFFF) sp = 0x0000;
				byte high = m.getValue(sp);
				sp++;
				if (sp < 0) sp = 0xFFFF;
				r.setStack(sp);
				r.setPC(ByteUtil.toInt(high, low));
			}
		};
		RET_FLAG_1 = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				if (part1.get() == 1) RET.invoke(part1, part2, value1, value2); 
			}
		};
		RET_FLAG_0 = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				if (part1.get() == 0) RET.invoke(part1, part2, value1, value2);
			}
		};
		RIM = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				String s = "0" + r.getI7()+r.getI6()+r.getI5()+r.getIeff()+r.getM75()+r.getM65()+r.getM55();
				r.setA((byte) Integer.parseInt(s, 2));
			}
		};
		SIM = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				String s = ByteUtil.toBinaryString(r.getA());
				if (s.charAt(4) == '1') {
					r.setM55((byte) (s.charAt(7) == '1' ? 1 : 0));
					r.setM65((byte) (s.charAt(6) == '1' ? 1 : 0));
					r.setM75((byte) (s.charAt(5) == '1' ? 1 : 0));
				}
				r.setReset75((byte) (s.charAt(3) == '1' ? 1 : 0));
			}
		};
		RST = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				PUSH.invoke(Address.REG_PC1, Address.REG_PC2, value1, value2);
				SystemHandler.getRegister().setPC(Integer.parseInt(ByteUtil.toHex(part1.get(), 4),  16));
			}
		};
		SBB = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				int val1 = ByteUtil.toUnsignedByte(Address.REG_A.get());
				int val2 = ByteUtil.toUnsignedByte(part1 != null ? part1.get() : value1);
				int newval = val1 - val2;
				{
					String s = ByteUtil.toBinaryString(val1);
					int tval1 = Integer.parseInt(s.substring(4));
					s = ByteUtil.toBinaryString(val2);
					int tval2 = Integer.parseInt(s.substring(4));
					if (tval1 < tval2) r.setAc((byte) 1); else r.setAc((byte) 0);
				}
				val2 = ByteUtil.toUnsignedByte(Address.FLAG_CY.get());
				if (r.getAc() == 0) {
					newval -= val2;
					{
						String s = ByteUtil.toBinaryString(val1);
						int tval1 = Integer.parseInt(s.substring(4));
						s = ByteUtil.toBinaryString(val2);
						int tval2 = Integer.parseInt(s.substring(4));
						if (tval1 < tval2) r.setAc((byte) 1); else r.setAc((byte) 0);
					}
				} else {
					newval += val2;
				}
				if (newval<0) {
					r.setCy((byte) 1);
				}
				r.setRegWithFlags(Address.REG_A, (byte) newval);
			}
		};
		SHLD = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				int val = ByteUtil.toInt(value1, value2);
				SystemHandler.getMemory().set(val, Address.REG_H.get());
				SystemHandler.getMemory().set(val+1, Address.REG_L.get());
			}
		};
		STC = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				SystemHandler.getRegister().setCy((byte) 1);
			}
		};
		SUB = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				int val1 = ByteUtil.toUnsignedByte(Address.REG_A.get());
				int val2 = ByteUtil.toUnsignedByte(part1 != null ? part1.get() : value1);
				int newval = val1 - val2;
				Register r = SystemHandler.getRegister();
				if (newval<0) {
					r.setCy((byte) 1);
				}
				//System.out.println("val1: " + val1 + " val2: " + val2);
				//System.out.println("newval: " + (byte) newval + " " + Integer.toHexString(newval));
				r.setRegWithFlags(Address.REG_A, (byte) newval);
				
				String s = ByteUtil.toBinaryString(val1);
				val1 = Integer.parseInt(s.substring(4));
				s = ByteUtil.toBinaryString(val2);
				val2 = Integer.parseInt(s.substring(4));
				if (val1 < val2) r.setAc((byte) 1); else r.setAc((byte) 0);
			}
		};
		XCHG = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				byte h = Address.REG_H.get();
				byte l = Address.REG_L.get();
				Address.REG_H.set(part1.get());
				Address.REG_L.set(part2.get());
				part1.set(h);
				part2.set(l);
			}
		};
		XRA = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Register r = SystemHandler.getRegister();
				byte mask = part1 != null ? part1.get() : value1;
				r.setRegWithFlags(Address.REG_A, ByteUtil.bitwiseXOP(r.getA(), mask));
				r.setAc((byte) 0);
				r.setCy((byte) 0);
			}
		};
		PCHL = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				Address.REG_PC1.set(Address.REG_H.get());
				Address.REG_PC2.set(Address.REG_L.get());
			}
		};
	}
}
