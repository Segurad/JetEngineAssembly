package jetengine.sys;

import java.util.HashMap;
import java.util.Map;

enum Opcode {
	
	ADD_A(Address.REGISTER_A, null, Operation.ADD, 1, "87", 4),
	ADD_B(Address.REGISTER_B, null, Operation.ADD, 1, "80", 4),
	ADD_C(Address.REGISTER_C, null, Operation.ADD, 1, "81", 4),
	ADD_D(Address.REGISTER_D, null, Operation.ADD, 1, "82", 4),
	ADD_E(Address.REGISTER_E, null, Operation.ADD, 1, "83", 4),
	ADD_H(Address.REGISTER_H, null, Operation.ADD, 1, "84", 4),
	ADD_L(Address.REGISTER_L, null, Operation.ADD, 1, "85", 4),
	ADD_M(Address.REGISTER_M, null, Operation.ADD, 1, "86", 7),
	ADI(null, null, Operation.ADD, 1, "C6", 7),
	ANA_A(Address.REGISTER_A, null, Operation.AND, 1, "A7", 4),
	ANA_B(Address.REGISTER_B, null, Operation.AND, 1, "A0", 4),
	ANA_C(Address.REGISTER_C, null, Operation.AND, 1, "A1", 4),
	ANA_D(Address.REGISTER_D, null, Operation.AND, 1, "A2", 4),
	ANA_E(Address.REGISTER_E, null, Operation.AND, 1, "A3", 4),
	ANA_H(Address.REGISTER_H, null, Operation.AND, 1, "A4", 4),
	ANA_L(Address.REGISTER_L, null, Operation.AND, 1, "A5", 4),
	ANA_M(Address.REGISTER_M, null, Operation.AND, 1, "A6", 7),
	ANI(null, null, Operation.ADD, 2, "E6", 7),
	;
	private Address part1;
	private Address part2;
	private Operation op;
	private byte size;
	private String hex;
	private byte clock;
	
	private static final Map<String, Opcode> BY_NAME;
	
	static {
		BY_NAME = new HashMap<String, Opcode>();
		Opcode[] arr = values();
		for (Opcode oc : arr) {
			BY_NAME.put(oc.name(), oc);
		}
	}
	
	private Opcode(Address part1, Address part2, Operation op, int size, String hex, int clock) {
		this.part1 = part1;
		this.part2 = part2;
		this.op = op;
		this.size = (byte) size;
		this.hex = hex;
		this.clock = (byte) clock;
	}
	
	public Opcode getOpcode(String code) {
		code.toUpperCase().replace(" ", "_");
		return BY_NAME.get(code);
	}
	
	public Address getAdress1() { return part1; }
	public Address getAdress2() { return part2; }
	public Operation getOperation() { return op; }
	public byte getSize() { return size; }
	public String getHex() { return hex; }
	public byte getClock() { return clock; }
}