package jetengine.sys.event;

public interface RegListener {

	public void updateRegA(String hexString);

	public void updateRegB(String hexString);

	public void updateRegC(String hexString);

	public void updateRegD(String hexString);

	public void updateRegE(String hexString);

	public void updateRegL(String hexString);

	public void updateRegH(String hexString);

	public void updateStack(String hexString);

	public void updatePC(String hexString);

	public void updateFlagCarry(String hexString);

	public void updateFlagParity(String hexString);

	public void updateFlagAuxillaryCarry(String hexString);

	public void updateFlagZero(String hexString);

	public void updateFlagSign(String hexString);

}
