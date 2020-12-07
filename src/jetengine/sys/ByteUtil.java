package jetengine.sys;

public final class ByteUtil {

	public static byte bitwiseNOT(byte bytee) {
		return (byte) ~bytee;
	}
	
	public static byte bitwiseAND(byte bytee, byte mask) {
		return (byte) (bytee & mask);
	}
	
	public static byte bitwiseOR(byte bytee, byte mask) {
		return (byte) (bytee | mask);
	}
	
	public static byte bitwiseXOP(byte bytee, byte mask) {
		return (byte) (bytee ^ mask);
	}
	
	public static byte shiftLeft(byte bytee, int times) {
		return (byte) (bytee << times);
	}
	
	public static byte shiftRight(byte bytee, int times) {
		return (byte) (bytee >>> times);
	}
	/**
	 * 
	 * @param bytee the byte
	 * @param pos the bit's position
	 * @return 1 or 0
	 */
	public static byte getBit(byte bytee, int pos) {
		String s = ByteUtil.toBinaryString(bytee);
		return (byte) (s.charAt(7 - pos) == '1' ? 1 : 0);
	}
	
	/**
	 * 
	 * @param bytee
	 * @param pos
	 * @return a new byte
	 */
	public static byte setBit(byte bytee, int pos) {
		return (byte) (bytee | (1 << pos));
	}
	
	/**
	 * 
	 * @param bytee
	 * @param pos
	 * @return a new byte
	 */
	public static byte resetBit(byte bytee, int pos) {
		return (byte) (bytee & ~(1 << pos));
	}
	
	/**
	 * 
	 * @param bytee
	 * @param pos
	 * @return a new byte
	 */
	public static byte invertBit(byte bytee, int pos) {
		return (byte) (bytee ^ (1 << pos));
	}
	
	public static int toInt(byte bytee1, byte bytee2) {
		return bytee1 << 8 + bytee2;
	}
	
	public static int toUnsignedByte(byte bytee) {
		return bytee & 0xFF;
	}

	public static byte highByte(int val) {
		return (byte) (val >>> 8);
	}

	public static byte lowByte(int val) {
		return (byte) val;
	}
	
	public static boolean validHex(String hex) {
		try
        {
            @SuppressWarnings("unused")
			int n=Integer.parseInt(hex, 16);
            return true;
        }
        catch(NumberFormatException q)
        {
            return false;
        }
	}
	
	public static String toBinaryString(byte bytee) {
		return toBinaryString(ByteUtil.toUnsignedByte(bytee));
	}
	
	public static String toBinaryString(int value) {
		String binstr = Integer.toBinaryString(value);
		while(binstr.length() < 8) {
			binstr = "0"+binstr;
		}
		return binstr;
	}
	
	public static String toHex(int value, int length) {
		String hexstr = Integer.toHexString(value);
		hexstr = hexstr.toUpperCase();
		while(hexstr.length() < length) {
			hexstr = "0" + hexstr;
		}
		return hexstr;
	}
	
	public static String toHex(byte value, int length) {
		return toHex(toUnsignedByte(value), length);
	}
}
