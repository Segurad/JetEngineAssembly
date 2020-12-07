package jetengine.sys;

import java.util.ArrayList;
import java.util.List;

import jetengine.sys.event.PortListener;

public class Ports {

	private List<PortListener> listeners = new ArrayList<PortListener>();
	
	private Byte[] mem;
	
	public Ports() {
		mem = new Byte[256];
	}
	
	public byte getValue(byte port) {
		int index = ByteUtil.toUnsignedByte(port);
		if (mem[index] == null) return 0;
		return mem[index];
	}
	
	public void set(byte x, byte value, boolean update) {
		int index = ByteUtil.toUnsignedByte(x);
		mem[index] = value;
		if (update) {
			for (PortListener pl : listeners) {
				if (pl.getPort() == x) pl.update(value);
			}
		}
	}

	public void removeListener(PortListener listener) {
		listeners.remove(listener);
	}

	public void addListener(PortListener listener) {
		listeners.add(listener);
	}

	public Byte[] getPorts() {
		return mem;
	}
}
