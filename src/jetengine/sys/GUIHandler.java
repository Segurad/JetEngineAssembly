package jetengine.sys;

public interface GUIHandler {

	public void updateMemory(Memory mem);
	public void updateRegA(String value);
	public void updateRegB(String value);
	public void updateRegC(String value);
	public void updateRegD(String value);
	public void updateRegE(String value);
	public void updateRegH(String value);
	public void updateRegL(String value);
	public void updateRegStack(String value);
	public void updateRegPC(String value);
	public void updateFlagSign(String value);
	public void updateFlagZero(String value);
	public void updateFlagAuxillaryCarry(String value);
	public void updateFlagParity(String value);
	public void updateFlagCarry(String value);
	public void updatePort(String value, Byte bytee);
	public void updateExecuting(int line);
	public void updateLine(int line, int start, int end, LineState state);
	public void startExecuting();
	public void stopExecuting();
	public void setMessage(String message);
}
