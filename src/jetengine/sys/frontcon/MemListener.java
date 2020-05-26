package jetengine.sys.frontcon;

public interface MemListener {

	public int getRangeMin();
	public int getRangeMax();
	public void update(int start, int[] values);
}