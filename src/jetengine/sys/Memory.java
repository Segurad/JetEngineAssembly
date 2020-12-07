package jetengine.sys;

import java.util.ArrayList;
import java.util.List;

import jetengine.sys.event.MemListener;

public final class Memory {

	private Byte[] mem;
	private int[][] editormask;
	private List<MemListener> listeners = new ArrayList<MemListener>();
	
	public Memory() {
		mem = new Byte[0xFFFF + 1];
		editormask = new int[0xFFFF + 1][];
	}
	
	public byte getValue(int key) {
		if (mem[key] == null) return 8;
		return mem[key];
	}
	
	public byte get(byte key1, byte key2) {
		return getValue(ByteUtil.toInt(key1, key2));
	}
	
	public void set(byte key1, byte key2, byte value) {
		set(ByteUtil.toInt(key1, key2), value);
	}
	
	public void set(int key, byte value) {
		set(key, value, true);
	}
	
	public void set(int key, byte value, int offset, int length, int id) {
		set(key, value, true);
		editormask[key] = new int[] { offset, length, id };
	}

	public void removeMemListener(MemListener listener) {
		listeners.add(listener);
	}
	
	public void addMemListener(MemListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void set(int key, byte value, boolean update) {
		mem[key] = value;
		if (update) for (MemListener l : listeners) {
			if (l.getRangeMin() <= key && key <= l.getRangeMax()) l.update(key-l.getRangeMin(), new int[] {ByteUtil.toUnsignedByte(value)});
		}
	}
	
	public int[] getEditorMask(int key) {
		return editormask[key];
	}

	public Byte[] getMemory() {
		return mem;
	}
	
}
