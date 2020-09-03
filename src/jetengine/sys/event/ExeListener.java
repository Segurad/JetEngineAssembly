package jetengine.sys.event;

public interface ExeListener {

	public void updateState(State state);
	
	public enum State {
		EXE_ALL,
		EXE_LINE,
		STOP;
	}
}
