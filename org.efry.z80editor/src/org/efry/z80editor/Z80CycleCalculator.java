package org.efry.z80editor;

import org.efry.z80editor.z80.Line;
import org.efry.z80editor.z80.Operand;
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
		Z80Instruction i = getInstructionForOperation(o);
		return i.getoClock();
	}
	
	private Z80Instruction getInstructionForOperation(Operation o) {
		Z80OpCodes opCode = Z80OpCodes.valueOf(o);
		if(opCode == null) {
			return Z80Instruction.dummyInstruction;
		}
		
		switch(opCode) {
		case ADC : return getAdcInstruction(o);
		case ADD : return getAddInstruction(o);
		case AND : return getAndInstruction(o);
		case BIT : return getBitInstruction(o);
		case CALL : return getCallInstruction(o);
		case CCF : return getCcfInstruction(o);
		case CP : return getCpInstruction(o);
		case CPD : return getCpdInstruction(o);
		case CPDR : return getCpdrInstruction(o);
		case CPIR : return getCpirInstruction(o);
		case CPI : return getCpiInstruction(o);
		case CPL : return getCplInstruction(o);
		case DAA : return getDaaInstruction(o);
		case DEC : return getDecInstruction(o);
		case DI : return getDiInstruction(o);
		case DJNZ : return getDjnzInstruction(o);
		case EI : return getEiInstruction(o);
		case EX : return getExInstruction(o);
		case EXX : return getExxInstruction(o);
		case HALT : return getHaltInstruction(o);
		default:
			break;
		}
		
		return Z80Instruction.dummyInstruction;
	}
	

	private Z80OperandType getOperandEnumType(String value) {
		try {
			return Z80OperandType.valueOf(value.toUpperCase());
		} catch(IllegalArgumentException ex) {
			return Z80OperandType.UNKNOWN;
		}
	}
	
	private Z80OperandType getIndirectOp(Operand op) {
		String reg = op.getReg().toUpperCase() + "_INDIRECT";
		return getOperandEnumType(reg);
	}

	private Z80OperandType getLeftOperand(Operation o) {
		if(o.getReg() != null) {
			return getOperandEnumType(o.getReg());
		}
		if(o.getOperand() != null) {
			return getOperand(o.getOperand());
		}
		
		if(o.getLeftOp() != null) {
			return getOperand(o.getLeftOp());
		}
		
		if(o.getIndirectOp() != null) {
			return getIndirectOp(o.getIndirectOp());
		}
		
		return Z80OperandType.UNKNOWN;
	}
	
	private Z80OperandType getRightOperand(Operation o) {
		
		if(o.getRightOp() != null) {
			return getOperand(o.getRightOp());
		}
		return Z80OperandType.UNKNOWN;
	}
	
	private Z80OperandType getOperand(Operand op) {
		
		if(op.getReg() != null) {
			return getOperandEnumType(op.getReg());
		}
		
		if(op.getOp() != null) {
			return getOperand(op.getOp());
		}
		
		if(op.getIndirectOp() != null) {
			return getIndirectOp(op.getIndirectOp());
		}

		if(op.getIndexPlusOffset() != null) {
			return getIndexPlusOffsetType(op.getIndexPlusOffset());
		}

		return Z80OperandType.NUMERIC;
	}

	private Z80OperandType getIndexPlusOffsetType(Operand op) {
		if(op.getReg() != null) {
			return getOperandEnumType(op.getReg().toUpperCase() + "_OFFSET_INDIRECT");
		}
		return Z80OperandType.UNKNOWN;
	}
	
	private Z80Instruction getAdcInstruction(Operation o) {
		Z80OperandType leftOp = getLeftOperand(o);
		if(leftOp == Z80OperandType.HL) {
			switch(getRightOperand(o)) {
			case BC : return Z80Instruction.getInstruction(0x4aed);
			case DE : return Z80Instruction.getInstruction(0x5aed);
			case HL : return Z80Instruction.getInstruction(0x6aed);
			case SP : return Z80Instruction.getInstruction(0x7aed);
			default : return Z80Instruction.dummyInstruction;
			}
		}
		
		if(leftOp == Z80OperandType.A) {
			switch(getRightOperand(o)) {
			case HL_INDIRECT : return Z80Instruction.getInstruction(0x8e);
			case A : return Z80Instruction.getInstruction(0x8f);
			case B : return Z80Instruction.getInstruction(0x88);
			case C : return Z80Instruction.getInstruction(0x89);
			case D : return Z80Instruction.getInstruction(0x8a);
			case E : return Z80Instruction.getInstruction(0x8b);
			case H : return Z80Instruction.getInstruction(0x8c);
			case L : return Z80Instruction.getInstruction(0x8d);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x8edd);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x8efd);
			case NUMERIC : return Z80Instruction.getInstruction(0xce);
			default : return Z80Instruction.dummyInstruction;
				
			}
		}
		return Z80Instruction.dummyInstruction;
	}

	private Z80Instruction getAddInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case A :
			switch(getRightOperand(o)) {
			case A : return Z80Instruction.getInstruction(0x87);
			case B : return Z80Instruction.getInstruction(0x80);
			case C : return Z80Instruction.getInstruction(0x81);
			case D : return Z80Instruction.getInstruction(0x82);
			case E : return Z80Instruction.getInstruction(0x83);
			case H : return Z80Instruction.getInstruction(0x84);
			case L : return Z80Instruction.getInstruction(0x85);
			case HL_INDIRECT : return Z80Instruction.getInstruction(0x86);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x86dd);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x86fd);
			case NUMERIC : return Z80Instruction.getInstruction(0xc6);
			default: break;
			}
		case HL :
			switch(getRightOperand(o)) {
			case BC : return Z80Instruction.getInstruction(0x9);
			case DE : return Z80Instruction.getInstruction(0x19);
			case HL : return Z80Instruction.getInstruction(0x29);
			case SP : return Z80Instruction.getInstruction(0x39);
			default : break;
			}
		case IX :
			switch(getRightOperand(o)) {
			case BC : return Z80Instruction.getInstruction(0x9dd);
			case DE : return Z80Instruction.getInstruction(0x19dd);
			case IX : return Z80Instruction.getInstruction(0x29dd);
			case SP : return Z80Instruction.getInstruction(0x39dd);
			default : break;
			}
		case IY :
			switch(getRightOperand(o)) {
			case BC : return Z80Instruction.getInstruction(0x9fd);
			case DE : return Z80Instruction.getInstruction(0x19fd);
			case IX : return Z80Instruction.getInstruction(0x29fd);
			case SP : return Z80Instruction.getInstruction(0x39fd);
			default : break;
			}
		default: break;
		}
		
		return Z80Instruction.dummyInstruction;
	}
	
	private Z80Instruction getAndInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case A : return Z80Instruction.getInstruction(0xa7);
		case B : return Z80Instruction.getInstruction(0xa0);
		case C : return Z80Instruction.getInstruction(0xa1);
		case D : return Z80Instruction.getInstruction(0xa2);
		case E : return Z80Instruction.getInstruction(0xa3);
		case H : return Z80Instruction.getInstruction(0xa4);
		case L : return Z80Instruction.getInstruction(0xa5);
		case HL_INDIRECT : return Z80Instruction.getInstruction(0xa6);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xa6dd);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xa6fd);
		case NUMERIC : return Z80Instruction.getInstruction(0xe6);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private Z80Instruction getBitInstruction(Operation o) {
		switch(getRightOperand(o)) {
		case A : return Z80Instruction.getInstruction(0x47cb);
		case B : return Z80Instruction.getInstruction(0x40cb);
		case C : return Z80Instruction.getInstruction(0x41cb);
		case D : return Z80Instruction.getInstruction(0x42cb);
		case E : return Z80Instruction.getInstruction(0x43cb);
		case H : return Z80Instruction.getInstruction(0x44cb);
		case L : return Z80Instruction.getInstruction(0x45cb);
		case HL_INDIRECT : return Z80Instruction.getInstruction(0x46cb);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbdd4600);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbfd4600);
		default : break;
		}
		return Z80Instruction.dummyInstruction;
	}

	private Z80Instruction getCallInstruction(Operation o) {
		if(o.getCondition() != null) {
			switch(getOperandEnumType(o.getCondition())) {
			case C : return Z80Instruction.getInstruction(0xdc);
			case M : return Z80Instruction.getInstruction(0xfc);
			case NC : return Z80Instruction.getInstruction(0xd4);
			case NZ : return Z80Instruction.getInstruction(0xc4);
			case P : return Z80Instruction.getInstruction(0xf4);
			case PE : return Z80Instruction.getInstruction(0xec);
			case PO : return Z80Instruction.getInstruction(0xe4);
			case Z : return Z80Instruction.getInstruction(0xcc); 
			default : break;
			}
		} else {
			return Z80Instruction.getInstruction(0xcd); // call nnnn
		}
		return Z80Instruction.dummyInstruction;
	}
	
	private Z80Instruction getCcfInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x3f);
	}
	
	private Z80Instruction getCpInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case A : return Z80Instruction.getInstruction(0xbf);
		case B : return Z80Instruction.getInstruction(0xb8);
		case C : return Z80Instruction.getInstruction(0xb9);
		case D : return Z80Instruction.getInstruction(0xba);
		case E : return Z80Instruction.getInstruction(0xbb);
		case H : return Z80Instruction.getInstruction(0xbc);
		case L : return Z80Instruction.getInstruction(0xbd);
		case HL_INDIRECT : return Z80Instruction.getInstruction(0xbe);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xbedd);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xbefd);
		case NUMERIC : return Z80Instruction.getInstruction(0xfe);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private Z80Instruction getCpdInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xa9ed);
	}
	
	private Z80Instruction getCpdrInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xb9ed);
	}
	
	private Z80Instruction getCpirInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xb1ed);
	}
	
	private Z80Instruction getCpiInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xa1ed);
	}
	
	private Z80Instruction getCplInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x2f);
	}
	
	private Z80Instruction getDaaInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x27);
	}
	
	private Z80Instruction getDecInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case A : return Z80Instruction.getInstruction(0x3d);
		case B : return Z80Instruction.getInstruction(0x05);
		case C : return Z80Instruction.getInstruction(0x0d);
		case D : return Z80Instruction.getInstruction(0x15);
		case E : return Z80Instruction.getInstruction(0x1d);
		case H : return Z80Instruction.getInstruction(0x25);
		case L : return Z80Instruction.getInstruction(0x2d);
		case HL_INDIRECT : return Z80Instruction.getInstruction(0x35);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x35dd);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x35fd);
		case BC : return Z80Instruction.getInstruction(0x0b);
		case DE : return Z80Instruction.getInstruction(0x1b);
		case HL : return Z80Instruction.getInstruction(0x2b);
		case IX : return Z80Instruction.getInstruction(0x2bdd);
		case IY : return Z80Instruction.getInstruction(0x2bfd);
		case SP : return Z80Instruction.getInstruction(0x3b);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private Z80Instruction getDiInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xf3);
	}
	
	private Z80Instruction getDjnzInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x10);
	}
	
	private Z80Instruction getEiInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xfb);
	}
	
	private Z80Instruction getExInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case SP_INDIRECT :
			switch(getRightOperand(o)) {
			case HL : return Z80Instruction.getInstruction(0xe3);
			case IX : return Z80Instruction.getInstruction(0xe3dd);
			case IY : return Z80Instruction.getInstruction(0xe3fd);
			default : break;
			}
		case AF : return Z80Instruction.getInstruction(0x08);
		case DE : return Z80Instruction.getInstruction(0xeb);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;	
	}
	
	private Z80Instruction getExxInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xd9);
	}
	
	private Z80Instruction getHaltInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x76);
	}
}
