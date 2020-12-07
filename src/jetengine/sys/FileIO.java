package jetengine.sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jetengine.sys.event.Editor;

public class FileIO {

	private static final String tag = "JetEngine-8085 File";
	private static final byte[] tagBytes = tag.getBytes();
	
	public static boolean exportProject(File file, boolean mem, boolean ports, boolean editor, int[] ids) throws IOException {
		final FileOutputStream out = new FileOutputStream(file);
		out.write(tagBytes.length);
		out.write(tagBytes);
		if (mem) {
			out.write(1);
			Memory memory = SystemHandler.getMemory();
			final Byte[] bytes = memory.getMemory();
			int count = 0;
			for (Byte b : bytes) {
				if (b != null && b != 0x08) count++;
			}
			out.write(count);
			count = -1;
			for (Byte b : bytes) {
				count++;
				if (b == null || b == 0x08) continue;
				out.write(count);
				out.write(b);
			}
 		} else out.write(0);
		if (ports) {
			out.write(1);
			final Ports p = SystemHandler.getPorts();
			int count = 0;
			final Byte[] bytes = p.getPorts();
			for (Byte b : bytes) {
				if (b != null && b != 0) count++;
			}
			out.write(count);
			count = -1;
			for (Byte b : bytes) {
				count++;
				if (b == null || b == 0) continue;
				out.write(count);
				out.write(b);
			}
		} else out.write(0);
		if (editor) {
			out.write(1);
			out.write(ids.length);
			for (int id : ids) {
				Editor e = SystemHandler.getEditorByID(id);
				String t = e.getText();
				byte[] bytes = t.getBytes();
				out.write(bytes.length);
				out.write(bytes);
			}
		} else out.write(0);
		out.close();
		return true;
	}
	
	public static boolean importProject(File file) throws IOException {
		final FileInputStream in = new FileInputStream(file);
		return true;
	}
}
