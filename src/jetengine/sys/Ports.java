package jetengine.sys;

public class Ports {

	private Byte[] mem;
	
	public Ports() {
		mem = new Byte[256];
	}
	
	public byte get(byte x) {
		if (mem[x] == null) return 0;
		return mem[x];
	}
	
	public void set(byte x, byte value) {
		mem[x] = value;
	}
}
