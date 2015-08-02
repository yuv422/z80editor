package org.efry.z80editor;

import org.efry.z80editor.z80.Line;
import org.efry.z80editor.z80.Operation;
import org.efry.z80editor.z80.Z80Model;

public class Z80CycleCalculator {
	
	public Z80CycleCalculator() {}
	
	public int calculateCyclesForModel(Z80Model model) {
		int cycles = 0;
		
		for(Line line : model.getCommands()) {
			if(Operation.class.isAssignableFrom(line.getClass())) {
				  Operation o = (Operation)line;
				  cycles += calculateCyclesForOperation(o);
			}
		}
		return cycles;
	}
	
	public int calculateCyclesForOperation(Operation o) {
		Z80Instruction i = Z80InstructionMapper.from(o);
		return i.getoClock();
	}
	
}
