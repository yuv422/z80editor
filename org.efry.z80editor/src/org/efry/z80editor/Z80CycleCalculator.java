package org.efry.z80editor;

import java.util.ArrayList;
import java.util.Formatter;

import org.efry.z80editor.z80.Line;
import org.efry.z80editor.z80.Operation;
import org.efry.z80editor.z80.Z80Model;

public class Z80CycleCalculator {
	
	private ArrayList<LineData> lines = new ArrayList<LineData>();
	private int maxLineLength = 0;
	private int oClockTotal = 0;
	private int oClockUnmetTotal = 0;

	private int sizeTotal = 0;
	
	public Z80CycleCalculator() {}
	
	static public int calculateOClockCyclesForModel(Z80Model model) {
		int cycles = 0;
		
		for(Line line : model.getCommands()) {
			if(Operation.class.isAssignableFrom(line.getClass())) {
				  cycles += calculateOClockCyclesForOperation((Operation)line);
			}
		}
		return cycles;
	}
	
	static public int calculateOClockCyclesForOperation(Operation o) {
		Z80Instruction i = Z80InstructionMapper.from(o);
		return i == null ? 0 : i.getoClock();
	}
	
	public void addOperation(Operation o, String operationText) {
		Z80Instruction i = Z80InstructionMapper.from(o);
		if(o != null) {
			if(operationText.length() > maxLineLength) {
				maxLineLength = operationText.length();
			}
			oClockTotal += i.getoClock();
			oClockUnmetTotal += i.getoClockUnmet() > 0 ? i.getoClockUnmet() : i.getoClock();
			sizeTotal += i.getSize();
			lines.add(new LineData(operationText, i));
		}
	}
	
	public String getSingleLineTotals() {
		return String.format("Clock met/unmet: %d/%d size in bytes: %d", oClockTotal, oClockUnmetTotal, sizeTotal);
	}

	public String getFormattedText() {
		final String assemblyColumnName = ";assembly";
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		int assemblyMaxLength = maxLineLength;
		if(assemblyMaxLength < assemblyColumnName.length()) {
			assemblyMaxLength = assemblyColumnName.length();
		}
		try {
			formatter.format("%-" + assemblyMaxLength + "s ; clock ,size: Op-Code\n", assemblyColumnName);
			String fmt = "%-" + assemblyMaxLength + "s ; %3d%-3s, %3d: %s\n";
	
			for(LineData data : lines) {
				Z80Instruction i = data.getInstruction();
				formatter.format(fmt, data.getLine(), i.getoClock(), i.getoClockUnmet() != 0 ? "/"+i.getoClockUnmet() : "", i.getSize(), i.getHexString());
			}
			
			formatter.format(fmt, "", oClockTotal, "", sizeTotal, "");
		} finally {
			formatter.close();
		}
		
		return sb.toString();
	}

	private class LineData {
		private String line;
		private Z80Instruction instruction;
		
		public LineData(String line, Z80Instruction instruction) {
			this.line = line;
			this.instruction = instruction;
		}

		public String getLine() {
			return line;
		}

		public Z80Instruction getInstruction() {
			return instruction;
		}
		
	}
}
