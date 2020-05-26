package jetengine.sys;

import java.util.Arrays;

public class StyleConfig {
	
	public static final int ERROR = 0, DEFAULT = 1, LABEL = 2, OPCODE = 3, OPARG = 4, COMMENT = 5, EXE_TRUE = 6, EXE_FALSE = 7;
	
	private int[][] style = new int[0][0];
	private int index = -1, offset,length;
	
	public StyleConfig(int offset, int length) {
		this.offset = offset;
		this.length = length;
	}
	
	public boolean hasNext() {
		return index+1 < style.length;
	}
	public int[] next() {
		index++;
		return style[index];
	}
	
	public void addStyle(int id, int start, int length) {
		style = Arrays.copyOf(style, style.length +1);
		style[style.length-1] = new int[] {
				id,start,length
		};
	}
	
	public int getOffset() {
		return offset;
	}
	
	public int getLength() {
		return length;
	}
}
