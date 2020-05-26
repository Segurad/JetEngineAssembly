package jetengine.sys.frontcon;

import jetengine.sys.StyleConfig;

public interface Editor {

	public String getText();
	public void updateStyle(StyleConfig config);
	void updateColor();
	public void redo();
	public void undo();
	public void setSelected(boolean b);
	public void backup();
	public void save();
	public void saveAs();
	public boolean isSelected();
	public String getEditorName();
	public void compile();
	public int getID();
	public void setExeColor(int offset, int length, int state);
	public void updateFont(String font, int size);
	public boolean isSaved();
	public void saveRequest();
}
