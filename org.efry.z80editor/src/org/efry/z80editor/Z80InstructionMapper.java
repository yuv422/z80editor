package org.efry.z80editor;

import org.efry.z80editor.z80.Operand;
import org.efry.z80editor.z80.Operation;

public final class Z80InstructionMapper {

	public static Z80Instruction from(Operation o) {
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
		case IM : return getImInstruction(o);
		case IN : return getInInstruction(o);
		case INC : return getIncInstruction(o);
		case IND : return getIndInstruction(o);
		case INDR : return getIndrInstruction(o);
		case INI : return getIniInstruction(o);
		case INIR : return getInirInstruction(o);
		case JP : return getJpInstruction(o);
		case JR : return getJrInstruction(o);
		case LD : return getLdInstruction(o);
		case LDD : return getLddInstruction(o);
		case LDDR : return getLddrInstruction(o);
		case LDI : return getLdiInstruction(o);
		case LDIR : return getLdirInstruction(o);
		case NEG : return getNegInstruction(o);
		case NOP : return getNopInstruction(o);
		case OR : return getOrInstruction(o);
		case OTDR : return getOtdrInstruction(o);
		case OTIR : return getOtirInstruction(o);
		case OUT : return getOutInstruction(o);
		case OUTD : return getOutdInstruction(o);
		case OUTI : return getOutiInstruction(o);
		case POP : return getPopInstruction(o);
		case PUSH : return getPushInstruction(o);
		case RES : return getResInstruction(o);
		case RET : return getRetInstruction(o);
		case RETI : return getRetiInstruction(o);
		case RETN : return getRetnInstruction(o);
		case RL : return getRlInstruction(o);
		case RLA : return getRlaInstruction(o);
		case RLC : return getRlcInstruction(o);
		case RLCA : return getRlcaInstruction(o);
		case RLD : return getRldInstruction(o);
		case RR : return getRrInstruction(o);
		case RRA : return getRraInstruction(o);
		case RRC : return getRrcInstruction(o);
		case RRCA : return getRrcaInstruction(o);
		case RRD : return getRrdInstruction(o);
		case RST : return getRstInstruction(o);
		case SBC : return getSbcInstruction(o);
		case SCF : return getScfInstruction(o);
		case SET : return getSetInstruction(o);
		case SLA : return getSlaInstruction(o);
		case SRA : return getSraInstruction(o);
		case SRL : return getSrlInstruction(o);
		case SUB : return getSubInstruction(o);
		case XOR : return getXorInstruction(o);
		default:
			break;
		}
		
		return Z80Instruction.dummyInstruction;
	}
	
	private static Z80OperandType getOperandEnumType(String value) {
		try {
			return Z80OperandType.valueOf(value.toUpperCase());
		} catch(IllegalArgumentException ex) {
			return Z80OperandType.UNKNOWN;
		}
	}
	
	private static Z80OperandType getIndirectOp(Operand op) {
		if(op.getIndexPlusOffset() != null) {
			return getOperandEnumType(op.getIndexPlusOffset().getReg().toUpperCase() + "_OFFSET_INDIRECT");
		}
		
		if(op.getReg() != null) {
			return getOperandEnumType(op.getReg().toUpperCase() + "_INDIRECT");
		}
		
		return Z80OperandType.NUMERIC_INDIRECT;
	}

	private static Z80OperandType getLeftOperand(Operation o) {
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
	
	private static Z80OperandType getRightOperand(Operation o) {
		
		if(o.getRightOp() != null) {
			return getOperand(o.getRightOp());
		}
		return Z80OperandType.UNKNOWN;
	}
	
	private static Z80OperandType getOperand(Operand op) {
		
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

	private static Z80OperandType getIndexPlusOffsetType(Operand op) {
		if(op.getReg() != null) {
			return getOperandEnumType(op.getReg().toUpperCase() + "_OFFSET_INDIRECT");
		}
		return Z80OperandType.UNKNOWN;
	}
	
	private static Z80Instruction getAdcInstruction(Operation o) {
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

	private static Z80Instruction getAddInstruction(Operation o) {
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
			case IY : return Z80Instruction.getInstruction(0x29fd);
			case SP : return Z80Instruction.getInstruction(0x39fd);
			default : break;
			}
		default: break;
		}
		
		return Z80Instruction.dummyInstruction;
	}
	
	private static Z80Instruction getAndInstruction(Operation o) {
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
	
	private static Z80Instruction getBitInstruction(Operation o) {
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

	private static Z80Instruction getCallInstruction(Operation o) {
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
	
	private static Z80Instruction getCcfInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x3f);
	}
	
	private static Z80Instruction getCpInstruction(Operation o) {
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
	
	private static Z80Instruction getCpdInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xa9ed);
	}
	
	private static Z80Instruction getCpdrInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xb9ed);
	}
	
	private static Z80Instruction getCpirInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xb1ed);
	}
	
	private static Z80Instruction getCpiInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xa1ed);
	}
	
	private static Z80Instruction getCplInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x2f);
	}
	
	private static Z80Instruction getDaaInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x27);
	}
	
	private static Z80Instruction getDecInstruction(Operation o) {
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
	
	private static Z80Instruction getDiInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xf3);
	}
	
	private static Z80Instruction getDjnzInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x10);
	}
	
	private static Z80Instruction getEiInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xfb);
	}
	
	private static Z80Instruction getExInstruction(Operation o) {
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
	
	private static Z80Instruction getExxInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xd9);
	}
	
	private static Z80Instruction getHaltInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x76);
	}
	
	private static Z80Instruction getImInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x46ed); //FIXME need to handle im 0, 1, 2
	}
	
	private static Z80Instruction getInInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case A :
			switch(getRightOperand(o)) {
			case NUMERIC_INDIRECT : return Z80Instruction.getInstruction(0xdb);
			case C_INDIRECT : return Z80Instruction.getInstruction(0x78ed);
			default : break;
			}
		case B : return Z80Instruction.getInstruction(0x40ed);
		case C : return Z80Instruction.getInstruction(0x48ed);
		case D : return Z80Instruction.getInstruction(0x50ed);
		case E : return Z80Instruction.getInstruction(0x58ed);
		case H : return Z80Instruction.getInstruction(0x60ed);
		case L : return Z80Instruction.getInstruction(0x68ed);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;	
	}
	
	private static Z80Instruction getIncInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case A : return Z80Instruction.getInstruction(0x3c);
		case B : return Z80Instruction.getInstruction(0x04);
		case C : return Z80Instruction.getInstruction(0x0c);
		case D : return Z80Instruction.getInstruction(0x14);
		case E : return Z80Instruction.getInstruction(0x1c);
		case H : return Z80Instruction.getInstruction(0x24);
		case L : return Z80Instruction.getInstruction(0x2c);
		case HL_INDIRECT : return Z80Instruction.getInstruction(0x34);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x34dd);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x34fd);
		case BC : return Z80Instruction.getInstruction(0x03);
		case DE : return Z80Instruction.getInstruction(0x13);
		case HL : return Z80Instruction.getInstruction(0x23);
		case IX : return Z80Instruction.getInstruction(0x23dd);
		case IY : return Z80Instruction.getInstruction(0x23fd);
		case SP : return Z80Instruction.getInstruction(0x33);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getIndInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xaaed);
	}
	
	private static Z80Instruction getIndrInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xbaed);
	}
	
	private static Z80Instruction getIniInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xa2ed);
	}
	
	private static Z80Instruction getInirInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xb2ed);
	}
	
	private static Z80Instruction getJpInstruction(Operation o) {
		if(o.getCondition() != null) {
			switch(getOperandEnumType(o.getCondition())) {

			case C : return Z80Instruction.getInstruction(0xda);
			case M : return Z80Instruction.getInstruction(0xfa);
			case NC : return Z80Instruction.getInstruction(0xd2);
			case NZ : return Z80Instruction.getInstruction(0xc2);
			case P : return Z80Instruction.getInstruction(0xf2);
			case PE : return Z80Instruction.getInstruction(0xea);
			case PO : return Z80Instruction.getInstruction(0xe2);
			case Z : return Z80Instruction.getInstruction(0xca); 
			default : break;
			}
		} else if (o.getIndirectOp() != null) {
			switch(getIndirectOp(o.getIndirectOp())) {
			case HL_INDIRECT : return Z80Instruction.getInstruction(0xe9);
			case IX_INDIRECT : return Z80Instruction.getInstruction(0xe9dd);
			case IY_INDIRECT : return Z80Instruction.getInstruction(0xe9fd);
			default : break;
			}
		}
		
		return Z80Instruction.getInstruction(0xc3); // jp nnnn
	}
	
	private static Z80Instruction getJrInstruction(Operation o) {
		if(o.getCondition() != null) {
			switch(getOperandEnumType(o.getCondition())) {

			case C : return Z80Instruction.getInstruction(0x38);
			case NC : return Z80Instruction.getInstruction(0x30);
			case NZ : return Z80Instruction.getInstruction(0x20);
			case Z : return Z80Instruction.getInstruction(0x28); 
			default : break;
			}
		}
		
		return Z80Instruction.getInstruction(0x18); // jr nnnn
	}
	
	private static Z80Instruction getLdInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case BC_INDIRECT : return Z80Instruction.getInstruction(0x02);
		case DE_INDIRECT : return Z80Instruction.getInstruction(0x12);
		case HL_INDIRECT :
			switch(getRightOperand(o)) {
			case A : return Z80Instruction.getInstruction(0x77);
			case B : return Z80Instruction.getInstruction(0x70);
			case C : return Z80Instruction.getInstruction(0x71);
			case D : return Z80Instruction.getInstruction(0x72);
			case E : return Z80Instruction.getInstruction(0x73);
			case H : return Z80Instruction.getInstruction(0x74);
			case L : return Z80Instruction.getInstruction(0x75);
			case NUMERIC : return Z80Instruction.getInstruction(0x36);
			default : break;
			}
			break;
		case IX_OFFSET_INDIRECT :
			switch(getRightOperand(o)) {
			case A : return Z80Instruction.getInstruction(0x77dd);
			case B : return Z80Instruction.getInstruction(0x70dd);
			case C : return Z80Instruction.getInstruction(0x71dd);
			case D : return Z80Instruction.getInstruction(0x72dd);
			case E : return Z80Instruction.getInstruction(0x73dd);
			case H : return Z80Instruction.getInstruction(0x74dd);
			case L : return Z80Instruction.getInstruction(0x75dd);
			case NUMERIC : return Z80Instruction.getInstruction(0x36dd);
			default : break;
			}
			break;
		case IY_OFFSET_INDIRECT :
			switch(getRightOperand(o)) {
			case A : return Z80Instruction.getInstruction(0x77fd);
			case B : return Z80Instruction.getInstruction(0x70fd);
			case C : return Z80Instruction.getInstruction(0x71fd);
			case D : return Z80Instruction.getInstruction(0x72fd);
			case E : return Z80Instruction.getInstruction(0x73fd);
			case H : return Z80Instruction.getInstruction(0x74fd);
			case L : return Z80Instruction.getInstruction(0x75fd);
			case NUMERIC : return Z80Instruction.getInstruction(0x36fd);
			default : break;
			}
			break;
		case NUMERIC_INDIRECT :
			switch(getRightOperand(o)) {
			case A : return Z80Instruction.getInstruction(0x32);
			case BC : return Z80Instruction.getInstruction(0x43ed);
			case DE : return Z80Instruction.getInstruction(0x53ed);
			case HL : return Z80Instruction.getInstruction(0x22);
			case IY : return Z80Instruction.getInstruction(0x22fd);
			case SP : return Z80Instruction.getInstruction(0x73ed);
			case IX : return Z80Instruction.getInstruction(0x22dd);
			default : break;
			}
			break;
		case A :
			switch(getRightOperand(o)) {

			case BC_INDIRECT : return Z80Instruction.getInstruction(0x0a);
			case DE_INDIRECT : return Z80Instruction.getInstruction(0x1a);
			case HL_INDIRECT : return Z80Instruction.getInstruction(0x7e);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x7efd);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x7edd);
			case NUMERIC_INDIRECT : return Z80Instruction.getInstruction(0x3a);
			case A : return Z80Instruction.getInstruction(0x7f);
			case B : return Z80Instruction.getInstruction(0x78);
			case C : return Z80Instruction.getInstruction(0x79);
			case D : return Z80Instruction.getInstruction(0x7a);
			case E : return Z80Instruction.getInstruction(0x7b);
			case H : return Z80Instruction.getInstruction(0x7c);
			case L : return Z80Instruction.getInstruction(0x7d);
			case I : return Z80Instruction.getInstruction(0x57ed);
			case R : return Z80Instruction.getInstruction(0x5fed);
			case NUMERIC : return Z80Instruction.getInstruction(0x3e);
			default : break;
			}
			break;
		case B :
			switch(getRightOperand(o)) {

			case HL_INDIRECT : return Z80Instruction.getInstruction(0x46);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x46dd);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x46fd);
			case A : return Z80Instruction.getInstruction(0x47);
			case B : return Z80Instruction.getInstruction(0x40);
			case C : return Z80Instruction.getInstruction(0x41);
			case D : return Z80Instruction.getInstruction(0x42);
			case E : return Z80Instruction.getInstruction(0x43);
			case H : return Z80Instruction.getInstruction(0x44);
			case L : return Z80Instruction.getInstruction(0x45);
			case NUMERIC : return Z80Instruction.getInstruction(0x06);
			default : break;
			}
			break;
		case C :
			switch(getRightOperand(o)) {

			case HL_INDIRECT : return Z80Instruction.getInstruction(0x4e);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x4edd);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x4efd);
			case A : return Z80Instruction.getInstruction(0x4f);
			case B : return Z80Instruction.getInstruction(0x48);
			case C : return Z80Instruction.getInstruction(0x49);
			case D : return Z80Instruction.getInstruction(0x4a);
			case E : return Z80Instruction.getInstruction(0x4b);
			case H : return Z80Instruction.getInstruction(0x4c);
			case L : return Z80Instruction.getInstruction(0x4d);
			case NUMERIC : return Z80Instruction.getInstruction(0x0e);
			default : break;
			}
			break;
		case D :
			switch(getRightOperand(o)) {

			case HL_INDIRECT : return Z80Instruction.getInstruction(0x56);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x56dd);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x56fd);
			case A : return Z80Instruction.getInstruction(0x57);
			case B : return Z80Instruction.getInstruction(0x50);
			case C : return Z80Instruction.getInstruction(0x51);
			case D : return Z80Instruction.getInstruction(0x52);
			case E : return Z80Instruction.getInstruction(0x53);
			case H : return Z80Instruction.getInstruction(0x54);
			case L : return Z80Instruction.getInstruction(0x55);
			case NUMERIC : return Z80Instruction.getInstruction(0x16);
			default : break;
			}
			break;
		case E :
			switch(getRightOperand(o)) {

			case HL_INDIRECT : return Z80Instruction.getInstruction(0x5e);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x5edd);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x5efd);
			case A : return Z80Instruction.getInstruction(0x5f);
			case B : return Z80Instruction.getInstruction(0x58);
			case C : return Z80Instruction.getInstruction(0x59);
			case D : return Z80Instruction.getInstruction(0x5a);
			case E : return Z80Instruction.getInstruction(0x5b);
			case H : return Z80Instruction.getInstruction(0x5c);
			case L : return Z80Instruction.getInstruction(0x5d);
			case NUMERIC : return Z80Instruction.getInstruction(0x1e);
			default : break;
			}
			break;
		case H :
			switch(getRightOperand(o)) {

			case HL_INDIRECT : return Z80Instruction.getInstruction(0x66);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x66dd);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x66fd);
			case A : return Z80Instruction.getInstruction(0x67);
			case B : return Z80Instruction.getInstruction(0x60);
			case C : return Z80Instruction.getInstruction(0x61);
			case D : return Z80Instruction.getInstruction(0x62);
			case E : return Z80Instruction.getInstruction(0x63);
			case H : return Z80Instruction.getInstruction(0x64);
			case L : return Z80Instruction.getInstruction(0x65);
			case NUMERIC : return Z80Instruction.getInstruction(0x26);
			default : break;
			}
			break;
		case L:
			switch(getRightOperand(o)) {

			case HL_INDIRECT : return Z80Instruction.getInstruction(0x6e);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x6edd);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x6efd);
			case A : return Z80Instruction.getInstruction(0x6f);
			case B : return Z80Instruction.getInstruction(0x68);
			case C : return Z80Instruction.getInstruction(0x69);
			case D : return Z80Instruction.getInstruction(0x6a);
			case E : return Z80Instruction.getInstruction(0x6b);
			case H : return Z80Instruction.getInstruction(0x6c);
			case L : return Z80Instruction.getInstruction(0x6d);
			case NUMERIC : return Z80Instruction.getInstruction(0x2e);
			default : break;
			}
			break;
		case I : return Z80Instruction.getInstruction(0x47ed);
		case R : return Z80Instruction.getInstruction(0x4fed);
		case BC:
			switch(getRightOperand(o)) {
			case NUMERIC_INDIRECT : return Z80Instruction.getInstruction(0x4bed);
			case NUMERIC : return Z80Instruction.getInstruction(0x01);
			default : break;
			}
			break;
		case DE:
			switch(getRightOperand(o)) {
			case NUMERIC_INDIRECT : return Z80Instruction.getInstruction(0x5bed);
			case NUMERIC : return Z80Instruction.getInstruction(0x11);
			default : break;
			}
			break;
		case HL:
			switch(getRightOperand(o)) {
			case NUMERIC_INDIRECT : return Z80Instruction.getInstruction(0x2a);
			case NUMERIC : return Z80Instruction.getInstruction(0x21);
			default : break;
			}
			break;
		case IX:
			switch(getRightOperand(o)) {
			case NUMERIC_INDIRECT : return Z80Instruction.getInstruction(0x2add);
			case NUMERIC : return Z80Instruction.getInstruction(0x21dd);
			default : break;
			}
			break;
		case IY:
			switch(getRightOperand(o)) {
			case NUMERIC_INDIRECT : return Z80Instruction.getInstruction(0x2afd);
			case NUMERIC : return Z80Instruction.getInstruction(0x21fd);
			default : break;
			}
			break;
		case SP:
			switch(getRightOperand(o)) {
			case NUMERIC_INDIRECT : return Z80Instruction.getInstruction(0x7bed);
			case HL : return Z80Instruction.getInstruction(0xf9);
			case IX : return Z80Instruction.getInstruction(0xf9dd);
			case IY : return Z80Instruction.getInstruction(0xf9fd);
			case NUMERIC : return Z80Instruction.getInstruction(0x31);
			default : break;
			}
			break;
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;
	}
	
	private static Z80Instruction getLddInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xa8ed);
	}
	
	private static Z80Instruction getLddrInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xb8ed);
	}
	
	private static Z80Instruction getLdiInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xa0ed);
	}
	
	private static Z80Instruction getLdirInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xb0ed);
	}
	
	private static Z80Instruction getNegInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x44ed);
	}
	
	private static Z80Instruction getNopInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x0);
	}
	
	private static Z80Instruction getOrInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case A : return Z80Instruction.getInstruction(0xb7);
		case B : return Z80Instruction.getInstruction(0xb0);
		case C : return Z80Instruction.getInstruction(0xb1);
		case D : return Z80Instruction.getInstruction(0xb2);
		case E : return Z80Instruction.getInstruction(0xb3);
		case H : return Z80Instruction.getInstruction(0xb4);
		case L : return Z80Instruction.getInstruction(0xb5);
		case HL_INDIRECT : return Z80Instruction.getInstruction(0xb6);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xb6dd);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xb6fd);
		case NUMERIC : return Z80Instruction.getInstruction(0xf6);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getOtdrInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x8bed);
	}
	
	private static Z80Instruction getOtirInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xb3ed);
	}
	
	private static Z80Instruction getOutInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case NUMERIC_INDIRECT : return Z80Instruction.getInstruction(0xd3);
		case C_INDIRECT :
			switch(getRightOperand(o)) {
			case A : return Z80Instruction.getInstruction(0x79ed);
			case B : return Z80Instruction.getInstruction(0x41ed);
			case C : return Z80Instruction.getInstruction(0x49ed);
			case D : return Z80Instruction.getInstruction(0x51ed);
			case E : return Z80Instruction.getInstruction(0x59ed);
			case H : return Z80Instruction.getInstruction(0x61ed);
			case L : return Z80Instruction.getInstruction(0x69ed);
			default : break;
			}
			break;
		default : break;
		}
		return Z80Instruction.dummyInstruction;
	}
	
	private static Z80Instruction getOutdInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xabed);
	}
	
	private static Z80Instruction getOutiInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xa3ed);
	}
	
	private static Z80Instruction getPopInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case AF : return Z80Instruction.getInstruction(0xf1);
		case BC : return Z80Instruction.getInstruction(0xc1);
		case DE : return Z80Instruction.getInstruction(0xd1);
		case HL : return Z80Instruction.getInstruction(0xe1);
		case IX : return Z80Instruction.getInstruction(0xe1dd);
		case IY : return Z80Instruction.getInstruction(0xe1fd);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getPushInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case AF : return Z80Instruction.getInstruction(0xf5);
		case BC : return Z80Instruction.getInstruction(0xc5);
		case DE : return Z80Instruction.getInstruction(0xd5);
		case HL : return Z80Instruction.getInstruction(0xe5);
		case IX : return Z80Instruction.getInstruction(0xe5dd);
		case IY : return Z80Instruction.getInstruction(0xe5fd);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getResInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case NUMERIC :
			switch(getRightOperand(o)) {
			case HL_INDIRECT : return Z80Instruction.getInstruction(0x86cb);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbdd8600);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbfd8600);
			case A : return Z80Instruction.getInstruction(0x87cb);
			case B : return Z80Instruction.getInstruction(0x80cb);
			case C : return Z80Instruction.getInstruction(0x81cb);
			case D : return Z80Instruction.getInstruction(0x82cb);
			case E : return Z80Instruction.getInstruction(0x83cb);
			case H : return Z80Instruction.getInstruction(0x84cb);
			case L : return Z80Instruction.getInstruction(0x85cb);
			default : break;
			}

		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getRetInstruction(Operation o) {
		if(o.getCondition() != null) {
			switch(getOperandEnumType(o.getCondition())) {

			case C : return Z80Instruction.getInstruction(0xd8);
			case M : return Z80Instruction.getInstruction(0xf8);
			case NC : return Z80Instruction.getInstruction(0xd0);
			case NZ : return Z80Instruction.getInstruction(0xc0);
			case P : return Z80Instruction.getInstruction(0xf0);
			case PE : return Z80Instruction.getInstruction(0xe8);
			case PO : return Z80Instruction.getInstruction(0xe0);
			case Z : return Z80Instruction.getInstruction(0xc8); 
			default : break;
			}
		}
		
		return Z80Instruction.getInstruction(0xc9); // ret
	}
	
	private static Z80Instruction getRetiInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x4ded);
	}
	
	private static Z80Instruction getRetnInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x45ed);
	}
	
	private static Z80Instruction getRlInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case HL_INDIRECT : return Z80Instruction.getInstruction(0x16cb);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbdd1600);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbfd1600);
		case A : return Z80Instruction.getInstruction(0x17cb);
		case B : return Z80Instruction.getInstruction(0x10cb);
		case C : return Z80Instruction.getInstruction(0x11cb);
		case D : return Z80Instruction.getInstruction(0x12cb);
		case E : return Z80Instruction.getInstruction(0x13cb);
		case H : return Z80Instruction.getInstruction(0x14cb);
		case L : return Z80Instruction.getInstruction(0x15cb);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getRlaInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x17);
	}
	
	private static Z80Instruction getRlcInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case HL_INDIRECT : return Z80Instruction.getInstruction(0x6cb);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbdd0600);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbfd0600);
		case A : return Z80Instruction.getInstruction(0x7cb);
		case B : return Z80Instruction.getInstruction(0xcb);
		case C : return Z80Instruction.getInstruction(0x1cb);
		case D : return Z80Instruction.getInstruction(0x2cb);
		case E : return Z80Instruction.getInstruction(0x3cb);
		case H : return Z80Instruction.getInstruction(0x4cb);
		case L : return Z80Instruction.getInstruction(0x5cb);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getRlcaInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x7);
	}
	
	private static Z80Instruction getRldInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x6fed);
	}
	
	private static Z80Instruction getRrInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case HL_INDIRECT : return Z80Instruction.getInstruction(0x1ecb);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbdd1e00);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbfd1e00);
		case A : return Z80Instruction.getInstruction(0x1fcb);
		case B : return Z80Instruction.getInstruction(0x18cb);
		case C : return Z80Instruction.getInstruction(0x19cb);
		case D : return Z80Instruction.getInstruction(0x1acb);
		case E : return Z80Instruction.getInstruction(0x1bcb);
		case H : return Z80Instruction.getInstruction(0x1ccb);
		case L : return Z80Instruction.getInstruction(0x1dcb);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getRraInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x1f);
	}
	
	private static Z80Instruction getRrcInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case HL_INDIRECT : return Z80Instruction.getInstruction(0xecb);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbdd0e00);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbfd0e00);
		case A : return Z80Instruction.getInstruction(0xfcb);
		case B : return Z80Instruction.getInstruction(0x8cb);
		case C : return Z80Instruction.getInstruction(0x9cb);
		case D : return Z80Instruction.getInstruction(0xacb);
		case E : return Z80Instruction.getInstruction(0xbcb);
		case H : return Z80Instruction.getInstruction(0xccb);
		case L : return Z80Instruction.getInstruction(0xdcb);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getRrcaInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xf);
	}
	
	private static Z80Instruction getRrdInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x67ed);
	}
	
	private static Z80Instruction getRstInstruction(Operation o) {
		return Z80Instruction.getInstruction(0xc7); //FIXME need to handle the different rst instructions
	}
	
	private static Z80Instruction getSbcInstruction(Operation o) {
		Z80OperandType leftOp = getLeftOperand(o);
		
		if(leftOp == Z80OperandType.A) {
			switch(getRightOperand(o)) {
			case HL_INDIRECT : return Z80Instruction.getInstruction(0x9e);
			case A : return Z80Instruction.getInstruction(0x9f);
			case B : return Z80Instruction.getInstruction(0x98);
			case C : return Z80Instruction.getInstruction(0x99);
			case D : return Z80Instruction.getInstruction(0x9a);
			case E : return Z80Instruction.getInstruction(0x9b);
			case H : return Z80Instruction.getInstruction(0x9c);
			case L : return Z80Instruction.getInstruction(0x9d);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x9edd);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x9efd);
			case NUMERIC : return Z80Instruction.getInstruction(0xde);
			default : return Z80Instruction.dummyInstruction;
				
			}
		}

		if(leftOp == Z80OperandType.HL) {
			switch(getRightOperand(o)) {
			case BC : return Z80Instruction.getInstruction(0x42ed);
			case DE : return Z80Instruction.getInstruction(0x52ed);
			case HL : return Z80Instruction.getInstruction(0x62ed);
			case SP : return Z80Instruction.getInstruction(0x72ed);
			default : return Z80Instruction.dummyInstruction;
			}
		}

		return Z80Instruction.dummyInstruction;
	}
	
	private static Z80Instruction getScfInstruction(Operation o) {
		return Z80Instruction.getInstruction(0x37);
	}
	
	private static Z80Instruction getSetInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case NUMERIC :
			switch(getRightOperand(o)) {
			case HL_INDIRECT : return Z80Instruction.getInstruction(0xc6cb);
			case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbddc600);
			case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbfdc600);
			case A : return Z80Instruction.getInstruction(0xc7cb);
			case B : return Z80Instruction.getInstruction(0xc0cb);
			case C : return Z80Instruction.getInstruction(0xc1cb);
			case D : return Z80Instruction.getInstruction(0xc2cb);
			case E : return Z80Instruction.getInstruction(0xc3cb);
			case H : return Z80Instruction.getInstruction(0xc4cb);
			case L : return Z80Instruction.getInstruction(0xc5cb);
			default : break;
			}

		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getSlaInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case HL_INDIRECT : return Z80Instruction.getInstruction(0x26cb);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbdd2600);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbfd2600);
		case A : return Z80Instruction.getInstruction(0x27cb);
		case B : return Z80Instruction.getInstruction(0x20cb);
		case C : return Z80Instruction.getInstruction(0x21cb);
		case D : return Z80Instruction.getInstruction(0x22cb);
		case E : return Z80Instruction.getInstruction(0x23cb);
		case H : return Z80Instruction.getInstruction(0x24cb);
		case L : return Z80Instruction.getInstruction(0x25cb);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getSraInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case HL_INDIRECT : return Z80Instruction.getInstruction(0x2ecb);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbdd2e00);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbfd2e00);
		case A : return Z80Instruction.getInstruction(0x2fcb);
		case B : return Z80Instruction.getInstruction(0x28cb);
		case C : return Z80Instruction.getInstruction(0x29cb);
		case D : return Z80Instruction.getInstruction(0x2acb);
		case E : return Z80Instruction.getInstruction(0x2bcb);
		case H : return Z80Instruction.getInstruction(0x2ccb);
		case L : return Z80Instruction.getInstruction(0x2dcb);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getSrlInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case HL_INDIRECT : return Z80Instruction.getInstruction(0x3ecb);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbdd3e00);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xcbfd3e00);
		case A : return Z80Instruction.getInstruction(0x3fcb);
		case B : return Z80Instruction.getInstruction(0x38cb);
		case C : return Z80Instruction.getInstruction(0x39cb);
		case D : return Z80Instruction.getInstruction(0x3acb);
		case E : return Z80Instruction.getInstruction(0x3bcb);
		case H : return Z80Instruction.getInstruction(0x3ccb);
		case L : return Z80Instruction.getInstruction(0x3dcb);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getSubInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case HL_INDIRECT : return Z80Instruction.getInstruction(0x96);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x96dd);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0x96fd);
		case A : return Z80Instruction.getInstruction(0x97);
		case B : return Z80Instruction.getInstruction(0x90);
		case C : return Z80Instruction.getInstruction(0x91);
		case D : return Z80Instruction.getInstruction(0x92);
		case E : return Z80Instruction.getInstruction(0x93);
		case H : return Z80Instruction.getInstruction(0x94);
		case L : return Z80Instruction.getInstruction(0x95);
		case NUMERIC : return Z80Instruction.getInstruction(0xd6);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
	
	private static Z80Instruction getXorInstruction(Operation o) {
		switch(getLeftOperand(o)) {
		case HL_INDIRECT : return Z80Instruction.getInstruction(0xae);
		case IX_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xaedd);
		case IY_OFFSET_INDIRECT : return Z80Instruction.getInstruction(0xaefd);
		case A : return Z80Instruction.getInstruction(0xaf);
		case B : return Z80Instruction.getInstruction(0xa8);
		case C : return Z80Instruction.getInstruction(0xa9);
		case D : return Z80Instruction.getInstruction(0xaa);
		case E : return Z80Instruction.getInstruction(0xab);
		case H : return Z80Instruction.getInstruction(0xac);
		case L : return Z80Instruction.getInstruction(0xad);
		case NUMERIC : return Z80Instruction.getInstruction(0xee);
		default : break;
		}
		
		return Z80Instruction.dummyInstruction;		
	}
}
