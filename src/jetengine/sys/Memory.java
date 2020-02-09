package jetengine.sys;

public final class Memory {

	private Byte[] mem;
	
	public Memory() {
		mem = new Byte[65536];
	}
	
	public byte get(int key) {
		if (mem[key] == null) return 0;
		return mem[key];
	}
	
	public byte get(byte key1, byte key2) {
		int key = Integer.parseInt(Integer.toHexString(key1) + Integer.toHexString(key2));
		return get(key);
	}
	
	public void set(byte key1, byte key2, byte value) {
		int key = Integer.parseInt(Integer.toHexString(key1) + Integer.toHexString(key2));
		set(key, value);
	}
	
	public void set(int key, byte value) {
		mem[key] = value;
	}
	
}
