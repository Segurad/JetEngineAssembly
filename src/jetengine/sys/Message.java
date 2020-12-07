package jetengine.sys;

public class Message {

	public static final String ERR_PREFIX = "[ERROR] = ";
	public static final String NO_VALID_HEX = "No valid Hex!";
	public static final String ERR_MISSING_ARG = ERR_PREFIX + "Missing Argument";
	public static final String ERR_DIRECTIVE_NOT_FOUND = ERR_PREFIX + "Directive not found";
	public static final String ERR_OPCODE_NOT_FOUND = ERR_PREFIX + "Opcode not found";
	public static final String ERR_MISSING_OPCODE = ERR_PREFIX + "Missing Opcode";
	public static final String ERR_MISSING_MARKER = ERR_PREFIX + "Missing Marger";
	public static final String ERR_MISSING_LABEL = ERR_PREFIX + "Missing Label";
	public static final String ERR_EXE_OPCODE_NULL = ERR_PREFIX + "Next Opcode invalid";
	public static final String ERR_TOO_MANY_ARGS = ERR_PREFIX + "Too many Arguments";
	public static final String JETENGINE = "JetEngine-";
	public static final String ERR_HEX_LABEL = ERR_PREFIX + "Label can not be a hex value!";
	public static String MEM_START_TO_HIGH = "The Memory view start value can not be higher than the end value!";

}
