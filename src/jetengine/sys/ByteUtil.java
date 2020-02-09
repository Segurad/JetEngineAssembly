package jetengine.sys;

public class ByteUtil {

	public static int bitwiseNOT(byte bytee) {
		return ~bytee;
	}
	
	public static int bitwiseAND(byte bytee, byte mask) {
		return bytee & mask;
	}
	
	public static int bitwiseOR(byte bytee, byte mask) {
		return bytee | mask;
	}
	
	public static int bitwiseXOP(byte bytee, byte mask) {
		return bytee | mask;
	}
	
	public static int shiftLeft(byte bytee, int times) {
		return bytee << times;
	}
	
	public static int shiftRight(byte bytee, int times) {
		return bytee >>> times;
	}
}
