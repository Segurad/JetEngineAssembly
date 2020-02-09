package jetengine.sys;

abstract class Operation {

	public static final Operation 
	ADD,
	AND
	;
	
	public abstract void invoke(Address part1, Address part2, byte value1, byte value2);

	static {
		ADD = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				byte val1 = Address.REGISTER_A.get();
				byte val2 = part1 != null ? part1.get() : value1;
				Address.REGISTER_A.set((byte) (val1 + val2));
			}
		};
		AND = new Operation() {
			@Override
			public void invoke(Address part1, Address part2, byte value1, byte value2) {
				byte val1 = Address.REGISTER_A.get();
				byte val2 = part1 != null ? part1.get() : value1;
				Address.REGISTER_A.set((byte) ByteUtil.bitwiseAND(val1, val2));
			}
		};
	}
}
