package jetengine.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import jetengine.sys.event.Editor;

public class SynAnalyser {

	private Memory mem;
	private Editor editor;
	private Timer timer;
	private HashMap<String, String> labelvals;
	private HashMap<Integer, CodePart> missingvars;
	private ArrayList<String> problems;
	private int address, offset, id;
	
	public SynAnalyser(Memory mem, Editor editor, int id) {
		this.mem = mem;
		this.editor = editor;
		this.id = id;
	}
	
	public void notifyAnalyser() {
		System.out.println("syn notify");
		if (timer != null) timer.cancel();
		this.timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				timer = null;
				analyse(editor.getText(), true);
			}
		};
		timer.schedule(task, 750);
	}
	/**
	 * 
	 * @param text the text that should be analysed
	 * @param back weather or not the editor should get a text backup
	 */
	
	public void analyse(String text, boolean back) {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		System.out.println("--------------------------");
		System.out.println("SynAnalyser: " + editor.getEditorName());
		System.out.println("--------------------------");
		address = 0;
		offset = 0;
		labelvals = new HashMap<String, String>();
		missingvars = new HashMap<Integer, CodePart>();
		problems = new ArrayList<String>();
		if (back) editor.backup();
		ArrayList<CodePart> codeParts = new ArrayList<CodePart>();
		boolean marker = false;
		Scanner scanLine = new Scanner(text);
		int line = 0;
		while (scanLine.hasNext()) {
			line++;
			System.out.println("--------------------------");
			String rawback = scanLine.nextLine();
			String raws = rawback;
			raws = raws.replace(",", " ");
			int last = 0;
			ArrayList<String> comments = new ArrayList<String>();
			ArrayList<String> op = new ArrayList<String>();
			ArrayList<String> rawParts = new ArrayList<String>();
			for (int i = 0; i < raws.length(); i++) {
				if (raws.charAt(i) == ';') {
					System.out.println("char ;");
					if (marker) {
						System.out.println("marker true");
						if (i>0) {
							System.out.println("i>0");
							if (raws.charAt(i-1) == '*') {
								System.out.println("*");
								marker = false;
								i++;
								comments.add(raws.substring(last, i));
								rawParts.add(raws.substring(last, i));
								last=i;
							}
						}
					}else if (i+1 < raws.length()) {
						System.out.println("i < length");
						if (raws.charAt(i+1) == '*') {
							System.out.println("*");
							marker = true;
							String sub =raws.substring(last, i);
							last = i;
							if (!sub.equals("")) {
								op.add(sub);
								rawParts.add(sub);
							}
						} else {
							System.out.println("else");
							String sub =raws.substring(last, i);
							last = i;
							if (!sub.equals("")) {
								op.add(sub);
								rawParts.add(sub);
							}
							comments.add(raws.substring(i));
							rawParts.add(raws.substring(i));
							last = raws.length();
							break;
						}
					} else {
						System.out.println("else");
						comments.add(raws.substring(last, raws.length()));
						rawParts.add(raws.substring(last, raws.length()));
						raws.length();
						break;
					}
				}
			}
			if (last != raws.length()) {
				if (marker) {
					comments.add(raws.substring(last, raws.length()));
					rawParts.add(raws.substring(last, raws.length()));
				} else {
					op.add(raws.substring(last, raws.length()));
					rawParts.add(raws.substring(last, raws.length()));
				}
			}
			for (String s : comments) System.out.println("comment: " +s);
			for (String s : op) System.out.println("opcode?: " + s);
			System.out.println("op length " + op.size());
			if (op.isEmpty()) {
				codeParts.add(new CodeComment(rawback));
				continue;
			}
			String rawOpcode = "";
			for (String s : op) rawOpcode = rawOpcode + s;
			Scanner scanCode = new Scanner(rawOpcode);
			System.out.println("--- Scan Opcode ---");
			/*
			 * 0 = label
			 * 1 = op1
			 * 2 = op2
			 * 3 = op3
			 * 4 = arg1
			 * 5 = arg2
			 */
			if (rawOpcode.replace(" ", "").equals("")) {
				codeParts.add(new CodePart() {
					public void paint() {
						offset+=rawback.length()+1;
					}
					public void org(boolean arg) {
						offset+=rawback.length()+1;
					}
				});
				continue;
			}
			String[] partsOrg = new String[6];
			int index = 1;
			while (scanCode.hasNext()) {
				String scans = scanCode.next();
				System.out.println(scans);
				if (index > 5) break;
				if (scans.contains(":")) {
					if (scans.endsWith(":")) {
						partsOrg[0] = scans;
						continue;
					} else {
						String[] parts = scans.split(":");
						partsOrg[0] = parts[0] + ":";
						partsOrg[index] = parts[1];
						index++;
						continue;
					}
				}
				if (index > 1 && ByteUtil.validHex(scans)) {
					if (scans.length() == 4) {
						partsOrg[4] = scans.substring(0, 2);
						partsOrg[5] = scans.substring(2);
						continue;
					} else if (scans.length() == 2) {
						if (partsOrg[4] == null) {
							partsOrg[4] = scans;
						} else partsOrg[5] = scans;
						continue;
					}
				}
				if (index >= 6) continue;
				partsOrg[index] = scans;
				index++;
			}
			System.out.println("try to assemble");
			System.out.println("arr0 " + partsOrg[0]);
			System.out.println("arr1 " + partsOrg[1]);
			System.out.println("arr2 " + partsOrg[2]);
			System.out.println("arr3 " + partsOrg[3]);
			System.out.println("arr4 " + partsOrg[4]);
			System.out.println("arr5 " + partsOrg[5]);
			codeParts.add(new CodeOpcode(comments, rawParts, rawback, partsOrg, line));
			scanCode.close();
		}
		scanLine.close();
		System.out.println("--- Assemble ---");
		
		for (CodePart part : codeParts) {
			part.org(true);
		}
		for (int mvar : new ArrayList<Integer>(missingvars.keySet())) {
			address = mvar;
			CodePart part = missingvars.get(mvar);
			part.org(false);
		}
		offset = 0;
		int i = 1;
		for (CodePart part : codeParts) {
			System.out.println("--- paint line " + i + " ---");
			i++;
			part.paint();
		}
		SystemHandler.sendProblems(problems);
		labelvals = null;
		missingvars = null;
		problems = null;
	}
	
	private interface CodePart {
		public void org(boolean arg);
		public void paint();
	}
	
	private final class CodeOpcode implements CodePart {
		String label, op1,op2,op3,arg1,arg2, marker, rawback, errs;
		Opcode op;
		boolean err;
		ArrayList<String> comments, rawParts;
		String[] partsOrg;
		int line;
		public CodeOpcode(ArrayList<String> comments, ArrayList<String> rawParts, String rawback, String[] partsOrg, int line) {
			label = partsOrg[0];
			arg1 = partsOrg[4];
			arg2 = partsOrg[5];
			op1 = partsOrg[1]; 
			op2 = partsOrg[2];
			op3 = partsOrg[3];
			this.rawback = rawback;
			this.comments = comments;
			this.rawParts = rawParts;
			this.partsOrg = partsOrg;
			this.line = line;
			String test = "";
			if (op1 != null) {
				if (!op1.startsWith("@")) {
					test = test + op1;
					if (op2 != null) test = test + "_" + op2;
					if (op3 != null) test = test + "_" + op3;
					op = Opcode.getOpcodeByName(test);
					if (op == null) {
						Opcode opp = Opcode.getOpcodeByName(op1);
						if (opp != null) {
							op = opp;
							marker = op2;
							partsOrg[2] = null;
						} else {
							err = true;
							errs = Message.ERR_OPCODE_NOT_FOUND;
						}
					}
					if (op != null) {
						if (op.getSize() >= 3) {
							if ((arg1 == null || arg2 == null) && marker == null) {
								System.out.println("err 1");
								err = true;
								errs = Message.ERR_MISSING_ARG;
							}
						} else if (op.getSize() >= 2) {
							if (arg1 == null && marker == null) {
								System.out.println("err 2");
								err = true;
								errs = Message.ERR_MISSING_ARG;
							}
							if (arg2 != null) {
								System.out.println("err 3");
								err = true;
								errs = Message.ERR_TOO_MANY_ARGS;
							}
						}
					}
				}
			} else {
				err = true;
				errs = Message.ERR_MISSING_OPCODE;
			}
			if (err) System.out.println(errs);
		}
		@Override
		public void org(boolean arg) {
			if (err) {
				offset+=rawback.length()+1;
				return;
			}
			if (!op1.startsWith("@")) {
				if (arg) {
					mem.set(address, (byte) Integer.parseInt(op.getHex(), 16), offset, rawback.length() + 1, id);
					offset+=rawback.length()+1;
				}
				if (label != null) labelvals.put(label, ByteUtil.toHex(address, 4));
				if (marker != null) {
					if (labelvals.containsKey(marker + ":")) {
						String val = labelvals.get(marker + ":");
						if (val.length() == 4) {
							arg1 = val.substring(0,2);
							arg2 = val.substring(2);
						} else {
							arg1 = val;
						}
						if (missingvars.containsKey(address)) {
							missingvars.remove(address);
						}
					} else {
						if (arg) {
							missingvars.put(address, this);
							address += op.getSize();
						} else {
							partsOrg[3] = marker;
							marker = null;
							err = true;
							errs = Message.ERR_MISSING_MARKER;
						}
						return;
					}
				}
				address++;
				if (arg1 != null) {
					mem.set(arg2==null?address:address+1, (byte) Integer.parseInt(arg1, 16));
					address++;
				}
				if (arg2 != null) {
					mem.set(arg1!=null?address-1:address, (byte) Integer.parseInt(arg2, 16));
					address++;
				}
			} else  {
				offset+=rawback.length()+1;
				if (op1.equalsIgnoreCase("@begin")) {
					if (arg1 == null || arg2 == null) {
						err = true;
						errs = Message.ERR_MISSING_ARG;
					} else {
						address = Integer.parseInt(arg1+arg2);
						SystemHandler.getExecuter().setStartAddress(address);
					}
				} else if (op1.equalsIgnoreCase("@equ")) {
					if (label == null) {
						err = true;
						errs = Message.ERR_MISSING_LABEL;
					} else if (arg1 == null) {
						err = true;
						errs = Message.ERR_MISSING_ARG;
					} else if (arg2 == null) {
						labelvals.put(label, arg1);
					} else {
						labelvals.put(label, arg1+arg2);
					}
				} else if (op1.equalsIgnoreCase("@next")) {
					if (arg1 == null || arg2 == null) {
						err = true;
						errs = Message.ERR_MISSING_ARG;
					} else {
						address = Integer.parseInt(arg1+arg2,16);
					}
				} else {
					err = true;
					errs = Message.ERR_DIRECTIVE_NOT_FOUND;
				}
			}
		}
		@Override
		public void paint() {
			StyleConfig style = new StyleConfig(offset, rawback.length());
			System.out.println("Start Painting: " + rawParts.size());
			if (err) problems.add(line + ": " + errs);
			for (String part : rawParts) {
				int lineindex = 0;
				int OPCODE = StyleConfig.OPCODE, OPARG = StyleConfig.OPARG;
				if (comments.contains(part)) {
					System.out.println("Prepare Comment");
					int index = rawback.indexOf(part, lineindex);
					if (index >= 0) {
						System.out.println("Paint Comment");
						style.addStyle(StyleConfig.COMMENT, offset + index, part.length());
						lineindex = index + part.length();
					}
				} else if (err) {
					System.out.println("Prepare Err");
					OPARG = StyleConfig.ERROR;
					OPCODE = OPARG;
				} 
				{
					System.out.println("Check Label");
					if (label != null) {
						int index = rawback.indexOf(label, lineindex);
						if (index >= 0) {
							System.out.println("Paint Label");
							style.addStyle(StyleConfig.LABEL, offset + index, label.length());
							lineindex = index + label.length();
							label = null;
						}
					}
					System.out.println("Check Op1");
					if (op1 != null) {
						int index = rawback.indexOf(op1, lineindex);
						if (index >= 0) {
							System.out.println("Paint Op1");
							style.addStyle(OPCODE, offset + index, op1.length());
							lineindex = index + op1.length();
							op1 = null;
						}
					}
					System.out.println("Check Args");
					for (int i = 2; i < 6; i++) {
						System.out.println("Check Arg" +i);
						if (partsOrg[i] != null) {
							System.out.println("Part !null Arg" +i);
							int index = rawback.indexOf(partsOrg[i], lineindex);
							if (index >= 0) {
								System.out.println("Paint Arg" +i);
								style.addStyle(OPARG, offset + index, partsOrg[i].length());
								lineindex = index + partsOrg[i].length();
								//System.out.println(partsOrg[i] + " : " + partsOrg[i].length() + " : " + lineindex);
								partsOrg[i] = null;
							}
						}
					}
					
					if (marker != null) {
						int index = rawback.indexOf(marker, lineindex);
						if (index >= 0) {
							System.out.println("Paint Op1");
							style.addStyle(StyleConfig.LABEL, offset + index, marker.length());
							lineindex = index + marker.length();
							marker = null;
						}
					}
				}
			}
			offset+=rawback.length()+1;
			editor.updateStyle(style);
		}
	}
	
	private class CodeComment implements CodePart { 
		String rawback;
		public CodeComment(String rawback) {
			this.rawback = rawback;
		}

		@Override
		public void org(boolean arg) {
			offset+=rawback.length()+1;
		}

		@SuppressWarnings("resource")
		@Override
		public void paint() {
			StyleConfig style = new StyleConfig(offset, rawback.length());
			if (new Scanner(rawback).hasNext()) {
				style.addStyle(StyleConfig.COMMENT, offset, rawback.length());
			} else style.addStyle(StyleConfig.DEFAULT, offset, rawback.length());
			editor.updateStyle(style);
			offset+=rawback.length()+1;
		}
	}
}
