package org.efry.z80editor;

import java.util.HashMap;

public class Z80Instruction {

    private Z80OpCodes opCode;
    private String description;
    private int hexCode;
    private String hexString;
    private int size;
    private int oClock;
    private int rClock;
    private int oClockUnmet;
    private int rClockUnmet;
    
    private static final HashMap<Integer, Z80Instruction> map = new HashMap<Integer, Z80Instruction>();
    
    public static Z80Instruction dummyInstruction = new Z80Instruction(Z80OpCodes.NOP, "dummy instruction", 0, "", 0, 0, 0, 0, 0);
    
    static {
        
        map.put(0x8E, new Z80Instruction(Z80OpCodes.ADC, "ADC A, (HL)", 0x8E, "8E", 1, 7, 8, 0, 0));
        map.put(0x8EDD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, (IX*)", 0x8EDD, "8EDD(*1)", 3, 19, 19, 0, 0));
        map.put(0x8EFD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, (IY*)", 0x8EFD, "8EFD(*1)", 3, 19, 19, 0, 0));
        map.put(0x8F, new Z80Instruction(Z80OpCodes.ADC, "ADC A, A", 0x8F, "8F", 1, 4, 4, 0, 0));
        map.put(0x88, new Z80Instruction(Z80OpCodes.ADC, "ADC A, B", 0x88, "88", 1, 4, 4, 0, 0));
        map.put(0x89, new Z80Instruction(Z80OpCodes.ADC, "ADC A, C", 0x89, "89", 1, 4, 4, 0, 0));
        map.put(0x8A, new Z80Instruction(Z80OpCodes.ADC, "ADC A, D", 0x8A, "8A", 1, 4, 4, 0, 0));
        map.put(0x8B, new Z80Instruction(Z80OpCodes.ADC, "ADC A, E", 0x8B, "8B", 1, 4, 4, 0, 0));
        map.put(0x8C, new Z80Instruction(Z80OpCodes.ADC, "ADC A, H", 0x8C, "8C", 1, 4, 4, 0, 0));
        map.put(0x8D, new Z80Instruction(Z80OpCodes.ADC, "ADC A, L", 0x8D, "8D", 1, 4, 4, 0, 0));
        map.put(0x8CDD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, IXH", 0x8CDD, "8CDD", 2, 0, 0, 0, 0));
        map.put(0x8CFD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, IYH", 0x8CFD, "8CFD", 2, 0, 0, 0, 0));
        map.put(0x8DDD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, IXL", 0x8DDD, "8DDD", 2, 0, 0, 0, 0));
        map.put(0x8DFD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, IYL", 0x8DFD, "8DFD", 2, 0, 0, 0, 0));
        map.put(0xCE, new Z80Instruction(Z80OpCodes.ADC, "ADC A, *", 0xCE, "CE(*1)", 2, 7, 8, 0, 0));

        map.put(0x4AED, new Z80Instruction(Z80OpCodes.ADC, "ADC HL, BC", 0x4AED, "4AED", 2, 15, 15, 0, 0));
        map.put(0x5AED, new Z80Instruction(Z80OpCodes.ADC, "ADC HL, DE", 0x5AED, "5AED", 2, 15, 15, 0, 0));
        map.put(0x6AED, new Z80Instruction(Z80OpCodes.ADC, "ADC HL, HL", 0x6AED, "6AED", 2, 15, 15, 0, 0));
        map.put(0x7AED, new Z80Instruction(Z80OpCodes.ADC, "ADC HL, SP", 0x7AED, "7AED", 2, 15, 15, 0, 0));

        map.put(0x86, new Z80Instruction(Z80OpCodes.ADD, "ADD A, (HL)", 0x86, "86", 1, 7, 8, 0, 0));
        map.put(0x86DD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, (IX*)", 0x86DD, "86DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x86FD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, (IY*)", 0x86FD, "86FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x87, new Z80Instruction(Z80OpCodes.ADD, "ADD A, A", 0x87, "87", 1, 19, 19, 0, 0));
        map.put(0x80, new Z80Instruction(Z80OpCodes.ADD, "ADD A, B", 0x80, "80", 1, 4, 4, 0, 0));
        map.put(0x81, new Z80Instruction(Z80OpCodes.ADD, "ADD A, C", 0x81, "81", 1, 4, 4, 0, 0));
        map.put(0x82, new Z80Instruction(Z80OpCodes.ADD, "ADD A, D", 0x82, "82", 1, 4, 4, 0, 0));
        map.put(0x83, new Z80Instruction(Z80OpCodes.ADD, "ADD A, E", 0x83, "83", 1, 4, 4, 0, 0));
        map.put(0x84, new Z80Instruction(Z80OpCodes.ADD, "ADD A, H", 0x84, "84", 1, 4, 4, 0, 0));
        map.put(0x85, new Z80Instruction(Z80OpCodes.ADD, "ADD A, L", 0x85, "85", 1, 4, 4, 0, 0));
        map.put(0x84DD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, IXH", 0x84DD, "84DD", 2, 0, 0, 0, 0));
        map.put(0x84FD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, IYH", 0x84FD, "84FD", 2, 0, 0, 0, 0));
        map.put(0x85DD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, IXL", 0x85DD, "85DD", 2, 0, 0, 0, 0));
        map.put(0x85FD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, IYL", 0x85FD, "85FD", 2, 0, 0, 0, 0));
        map.put(0xC6, new Z80Instruction(Z80OpCodes.ADD, "ADD A, *", 0xC6, "C6(*1)", 2, 7, 8, 0, 0));

        map.put(0x9, new Z80Instruction(Z80OpCodes.ADD, "ADD HL, BC", 0x9, "9", 1, 11, 11, 0, 0));
        map.put(0x19, new Z80Instruction(Z80OpCodes.ADD, "ADD HL, DE", 0x19, "19", 1, 11, 11, 0, 0));
        map.put(0x29, new Z80Instruction(Z80OpCodes.ADD, "ADD HL, HL", 0x29, "29", 1, 11, 11, 0, 0));
        map.put(0x39, new Z80Instruction(Z80OpCodes.ADD, "ADD HL, SP", 0x39, "39", 1, 11, 11, 0, 0));

        map.put(0x09DD, new Z80Instruction(Z80OpCodes.ADD, "ADD IX, BC", 0x09DD, "09DD", 2, 15, 15, 0, 0));
        map.put(0x19DD, new Z80Instruction(Z80OpCodes.ADD, "ADD IX, DE", 0x19DD, "19DD", 2, 15, 15, 0, 0));
        map.put(0x29DD, new Z80Instruction(Z80OpCodes.ADD, "ADD IX, IX", 0x29DD, "29DD", 2, 15, 15, 0, 0));
        map.put(0x39DD, new Z80Instruction(Z80OpCodes.ADD, "ADD IX, SP", 0x39DD, "39DD", 2, 15, 15, 0, 0));
        map.put(0x09FD, new Z80Instruction(Z80OpCodes.ADD, "ADD IY, BC", 0x09FD, "09FD", 2, 15, 15, 0, 0));
        map.put(0x19FD, new Z80Instruction(Z80OpCodes.ADD, "ADD IY, DE", 0x19FD, "19FD", 2, 15, 15, 0, 0));
        map.put(0x29FD, new Z80Instruction(Z80OpCodes.ADD, "ADD IY, IY", 0x29FD, "29FD", 2, 15, 15, 0, 0));
        map.put(0x39FD, new Z80Instruction(Z80OpCodes.ADD, "ADD IY, SP", 0x39FD, "39FD", 2, 15, 15, 0, 0));

        map.put(0xA6, new Z80Instruction(Z80OpCodes.AND, "AND (HL)", 0xA6, "A6", 1, 7, 8, 0, 0));
        map.put(0xA6DD, new Z80Instruction(Z80OpCodes.AND, "AND (IX*)", 0xA6DD, "A6DD(*1)", 3, 19, 19, 0, 0));
        map.put(0xA6FD, new Z80Instruction(Z80OpCodes.AND, "AND (IY*)", 0xA6FD, "A6FD(*1)", 3, 19, 19, 0, 0));
        map.put(0xA7, new Z80Instruction(Z80OpCodes.AND, "AND A", 0xA7, "A7", 1, 4, 4, 0, 0));
        map.put(0xA0, new Z80Instruction(Z80OpCodes.AND, "AND B", 0xA0, "A0", 1, 4, 4, 0, 0));
        map.put(0xA1, new Z80Instruction(Z80OpCodes.AND, "AND C", 0xA1, "A1", 1, 4, 4, 0, 0));
        map.put(0xA2, new Z80Instruction(Z80OpCodes.AND, "AND D", 0xA2, "A2", 1, 4, 4, 0, 0));
        map.put(0xA3, new Z80Instruction(Z80OpCodes.AND, "AND E", 0xA3, "A3", 1, 4, 4, 0, 0));
        map.put(0xA4, new Z80Instruction(Z80OpCodes.AND, "AND H", 0xA4, "A4", 1, 4, 4, 0, 0));
        map.put(0xA5, new Z80Instruction(Z80OpCodes.AND, "AND L", 0xA5, "A5", 1, 4, 4, 0, 0));
        map.put(0xA4DD, new Z80Instruction(Z80OpCodes.AND, "AND IXH", 0xA4DD, "A4DD", 2, 0, 0, 0, 0));
        map.put(0xA4FD, new Z80Instruction(Z80OpCodes.AND, "AND IYH", 0xA4FD, "A4FD", 2, 0, 0, 0, 0));
        map.put(0xA5DD, new Z80Instruction(Z80OpCodes.AND, "AND IXL", 0xA5DD, "A5DD", 2, 0, 0, 0, 0));
        map.put(0xA5FD, new Z80Instruction(Z80OpCodes.AND, "AND IYL", 0xA5FD, "A5FD", 2, 0, 0, 0, 0));
        map.put(0xE6, new Z80Instruction(Z80OpCodes.AND, "AND *", 0xE6, "E6(*1)", 2, 7, 8, 0, 0));

        map.put(0x46CB, new Z80Instruction(Z80OpCodes.BIT, "BIT *, (HL)", 0x46CB, "46CB", 2, 12, 12, 0, 0));
        map.put(0xCBDD4600, new Z80Instruction(Z80OpCodes.BIT, "BIT *, (IX*)", 0xCBDD4600, "CBDD 4600", 4, 20, 20, 0, 0));
        map.put(0xCBFD4600, new Z80Instruction(Z80OpCodes.BIT, "BIT *, (IY*)", 0xCBFD4600, "CBFD 4600", 4, 20, 20, 0, 0));
        map.put(0x47CB, new Z80Instruction(Z80OpCodes.BIT, "BIT *, A", 0x47CB, "47CB", 2, 8, 8, 0, 0));
        map.put(0x40CB, new Z80Instruction(Z80OpCodes.BIT, "BIT *, B", 0x40CB, "40CB", 2, 8, 8, 0, 0));
        map.put(0x41CB, new Z80Instruction(Z80OpCodes.BIT, "BIT *, C", 0x41CB, "41CB", 2, 8, 8, 0, 0));
        map.put(0x42CB, new Z80Instruction(Z80OpCodes.BIT, "BIT *, D", 0x42CB, "42CB", 2, 8, 8, 0, 0));
        map.put(0x43CB, new Z80Instruction(Z80OpCodes.BIT, "BIT *, E", 0x43CB, "43CB", 2, 8, 8, 0, 0));
        map.put(0x44CB, new Z80Instruction(Z80OpCodes.BIT, "BIT *, H", 0x44CB, "44CB", 2, 8, 8, 0, 0));
        map.put(0x45CB, new Z80Instruction(Z80OpCodes.BIT, "BIT *, L", 0x45CB, "45CB", 2, 8, 8, 0, 0));

        map.put(0xDC, new Z80Instruction(Z80OpCodes.CALL, "CALL C, *", 0xDC, "DC(*2)", 3, 17, 17, 1, 12));
        map.put(0xFC, new Z80Instruction(Z80OpCodes.CALL, "CALL M, *", 0xFC, "FC(*2)", 3, 17, 17, 1, 12));
        map.put(0xD4, new Z80Instruction(Z80OpCodes.CALL, "CALL NC, *", 0xD4, "D4(*2)", 3, 17, 17, 1, 12));
        map.put(0xC4, new Z80Instruction(Z80OpCodes.CALL, "CALL NZ, *", 0xC4, "C4(*2)", 3, 17, 17, 1, 12));
        map.put(0xF4, new Z80Instruction(Z80OpCodes.CALL, "CALL P, *", 0xF4, "F4(*2)", 3, 17, 17, 1, 12));
        map.put(0xEC, new Z80Instruction(Z80OpCodes.CALL, "CALL PE, *", 0xEC, "EC(*2)", 3, 17, 17, 1, 12));
        map.put(0xE4, new Z80Instruction(Z80OpCodes.CALL, "CALL PO, *", 0xE4, "E4(*2)", 3, 17, 17, 1, 12));
        map.put(0xCC, new Z80Instruction(Z80OpCodes.CALL, "CALL Z, *", 0xCC, "CC(*2)", 3, 17, 17, 1, 12));
        map.put(0xCD, new Z80Instruction(Z80OpCodes.CALL, "CALL *", 0xCD, "CD(*2)", 3, 17, 17, 0, 12));

        map.put(0x3F, new Z80Instruction(Z80OpCodes.CCF, "CCF", 0x3F, "3F", 1, 4, 4, 0, 0));

        map.put(0xBE, new Z80Instruction(Z80OpCodes.CP, "CP (HL)", 0xBE, "BE", 1, 7, 8, 0, 0));
        map.put(0xBEDD, new Z80Instruction(Z80OpCodes.CP, "CP (IX*)", 0xBEDD, "BEDD(*1)", 3, 19, 19, 0, 0));
        map.put(0xBEFD, new Z80Instruction(Z80OpCodes.CP, "CP (IY*)", 0xBEFD, "BEFD(*1)", 3, 19, 19, 0, 0));
        map.put(0xBF, new Z80Instruction(Z80OpCodes.CP, "CP A", 0xBF, "BF", 1, 4, 4, 0, 0));
        map.put(0xB8, new Z80Instruction(Z80OpCodes.CP, "CP B", 0xB8, "B8", 1, 4, 4, 0, 0));
        map.put(0xB9, new Z80Instruction(Z80OpCodes.CP, "CP C", 0xB9, "B9", 1, 4, 4, 0, 0));
        map.put(0xBA, new Z80Instruction(Z80OpCodes.CP, "CP D", 0xBA, "BA", 1, 4, 4, 0, 0));
        map.put(0xBB, new Z80Instruction(Z80OpCodes.CP, "CP E", 0xBB, "BB", 1, 4, 4, 0, 0));
        map.put(0xBC, new Z80Instruction(Z80OpCodes.CP, "CP H", 0xBC, "BC", 1, 4, 4, 0, 0));
        map.put(0xBD, new Z80Instruction(Z80OpCodes.CP, "CP L", 0xBD, "BD", 1, 4, 4, 0, 0));
        map.put(0xBCDD, new Z80Instruction(Z80OpCodes.CP, "CP IXH", 0xBCDD, "BCDD", 2, 0, 0, 0, 0));
        map.put(0xBCFD, new Z80Instruction(Z80OpCodes.CP, "CP IYH", 0xBCFD, "BCFD", 2, 0, 0, 0, 0));
        map.put(0xBDDD, new Z80Instruction(Z80OpCodes.CP, "CP IXL", 0xBDDD, "BDDD", 2, 0, 0, 0, 0));
        map.put(0xBDFD, new Z80Instruction(Z80OpCodes.CP, "CP IYL", 0xBDFD, "BDFD", 2, 0, 0, 0, 0));
        map.put(0xFE, new Z80Instruction(Z80OpCodes.CP, "CP *", 0xFE, "FE(*1)", 2, 7, 8, 0, 0));

        map.put(0xA9ED, new Z80Instruction(Z80OpCodes.CPD, "CPD", 0xA9ED, "A9ED", 2, 16, 16, 0, 0));
        map.put(0xB9ED, new Z80Instruction(Z80OpCodes.CPDR, "CPDR", 0xB9ED, "B9ED", 2, 21, 21, 0, 0));

        map.put(0xB1ED, new Z80Instruction(Z80OpCodes.CPIR, "CPIR", 0xB1ED, "B1ED", 2, 21, 21, 0, 0));
        map.put(0xA1ED, new Z80Instruction(Z80OpCodes.CPI, "CPI", 0xA1ED, "A1ED", 2, 16, 16, 0, 0));

        map.put(0x2F, new Z80Instruction(Z80OpCodes.CPL, "CPL", 0x2F, "2F", 1, 4, 4, 0, 0));

        map.put(0x27, new Z80Instruction(Z80OpCodes.DAA, "DAA", 0x27, "27", 1, 4, 4, 0, 0));

        map.put(0x35, new Z80Instruction(Z80OpCodes.DEC, "DEC (HL)", 0x35, "35", 1, 11, 11, 0, 0));
        map.put(0x35DD, new Z80Instruction(Z80OpCodes.DEC, "DEC (IX*)", 0x35DD, "35DD(*1)", 3, 23, 23, 0, 0));
        map.put(0x35FD, new Z80Instruction(Z80OpCodes.DEC, "DEC (IY*)", 0x35FD, "35FD(*1)", 3, 23, 23, 0, 0));
        map.put(0x3D, new Z80Instruction(Z80OpCodes.DEC, "DEC A", 0x3D, "3D", 1, 4, 4, 0, 0));
        map.put(0x5, new Z80Instruction(Z80OpCodes.DEC, "DEC B", 0x5, "5", 1, 4, 4, 0, 0));
        map.put(0x0D, new Z80Instruction(Z80OpCodes.DEC, "DEC C", 0x0D, "0D", 1, 4, 4, 0, 0));
        map.put(0x15, new Z80Instruction(Z80OpCodes.DEC, "DEC D", 0x15, "15", 1, 4, 4, 0, 0));
        map.put(0x1D, new Z80Instruction(Z80OpCodes.DEC, "DEC E", 0x1D, "1D", 1, 4, 4, 0, 0));
        map.put(0x25, new Z80Instruction(Z80OpCodes.DEC, "DEC H", 0x25, "25", 1, 4, 4, 0, 0));
        map.put(0x2D, new Z80Instruction(Z80OpCodes.DEC, "DEC L", 0x2D, "2D", 1, 4, 4, 0, 0));
        map.put(0x25DD, new Z80Instruction(Z80OpCodes.DEC, "DEC IXH", 0x25DD, "25DD", 2, 0, 0, 0, 0));
        map.put(0x25FD, new Z80Instruction(Z80OpCodes.DEC, "DEC IYH", 0x25FD, "25FD", 2, 0, 0, 0, 0));
        map.put(0x2DDD, new Z80Instruction(Z80OpCodes.DEC, "DEC IXL", 0x2DDD, "2DDD", 2, 0, 0, 0, 0));
        map.put(0x2DFD, new Z80Instruction(Z80OpCodes.DEC, "DEC IYL ", 0x2DFD, "2DFD", 2, 0, 0, 0, 0));

        map.put(0x0B, new Z80Instruction(Z80OpCodes.DEC, "DEC BC", 0x0B, "0B", 1, 6, 6, 0, 0));
        map.put(0x1B, new Z80Instruction(Z80OpCodes.DEC, "DEC DE", 0x1B, "1B", 1, 6, 6, 0, 0));
        map.put(0x2B, new Z80Instruction(Z80OpCodes.DEC, "DEC HL", 0x2B, "2B", 1, 6, 6, 0, 0));
        map.put(0x2BDD, new Z80Instruction(Z80OpCodes.DEC, "DEC IX", 0x2BDD, "2BDD", 2, 10, 10, 0, 0));
        map.put(0x2BFD, new Z80Instruction(Z80OpCodes.DEC, "DEC IY", 0x2BFD, "2BFD", 2, 10, 10, 0, 0));
        map.put(0x3B, new Z80Instruction(Z80OpCodes.DEC, "DEC SP", 0x3B, "3B", 1, 6, 6, 0, 0));

        map.put(0xF3, new Z80Instruction(Z80OpCodes.DI, "DI", 0xF3, "F3", 1, 4, 4, 0, 0));

        map.put(0x10, new Z80Instruction(Z80OpCodes.DJNZ, "DJNZ *", 0x10, "10(*1)", 2, 13, 13, 0, 0));

        map.put(0xFB, new Z80Instruction(Z80OpCodes.EI, "EI", 0xFB, "FB", 1, 4, 4, 0, 0));

        map.put(0xE3, new Z80Instruction(Z80OpCodes.EX, "EX (SP), HL", 0xE3, "E3", 1, 19, 19, 0, 0));
        map.put(0xE3DD, new Z80Instruction(Z80OpCodes.EX, "EX (SP), IX", 0xE3DD, "E3DD", 2, 23, 23, 0, 0));
        map.put(0xE3FD, new Z80Instruction(Z80OpCodes.EX, "EX (SP), IY", 0xE3FD, "E3FD", 2, 23, 23, 0, 0));
        map.put(0x8, new Z80Instruction(Z80OpCodes.EX, "EX AF, AF'", 0x8, "8", 1, 4, 4, 0, 0));
        map.put(0xEB, new Z80Instruction(Z80OpCodes.EX, "EX DE, HL", 0xEB, "EB", 1, 4, 4, 0, 0));

        map.put(0xD9, new Z80Instruction(Z80OpCodes.EXX, "EXX", 0xD9, "D9", 1, 4, 4, 0, 0));

        map.put(0x76, new Z80Instruction(Z80OpCodes.HALT, "HALT", 0x76, "76", 1, 4, 4, 0, 0));

        map.put(0x46ED, new Z80Instruction(Z80OpCodes.IM, "IM 0", 0x46ED, "46ED", 2, 8, 8, 0, 0));
        map.put(0x56ED, new Z80Instruction(Z80OpCodes.IM, "IM 1", 0x56ED, "56ED", 2, 8, 8, 0, 0));
        map.put(0x5EED, new Z80Instruction(Z80OpCodes.IM, "IM 2", 0x5EED, " 5EED", 2, 8, 8, 0, 0));

        map.put(0x78ED, new Z80Instruction(Z80OpCodes.IN, "IN A, (C)", 0x78ED, "78ED", 2, 12, 12, 0, 0));
        map.put(0x40ED, new Z80Instruction(Z80OpCodes.IN, "IN B, (C)", 0x40ED, "40ED", 2, 12, 12, 0, 0));
        map.put(0x48ED, new Z80Instruction(Z80OpCodes.IN, "IN C, (C)", 0x48ED, "48ED", 2, 12, 12, 0, 0));
        map.put(0x50ED, new Z80Instruction(Z80OpCodes.IN, "IN D, (c)", 0x50ED, "50ED", 2, 12, 12, 0, 0));
        map.put(0x58ED, new Z80Instruction(Z80OpCodes.IN, "IN E, (C)", 0x58ED, "58ED", 2, 12, 12, 0, 0));
        map.put(0x60ED, new Z80Instruction(Z80OpCodes.IN, "IN H, (C)", 0x60ED, "60ED", 2, 12, 12, 0, 0));
        map.put(0x68ED, new Z80Instruction(Z80OpCodes.IN, "IN L, (C)", 0x68ED, "68ED", 2, 12, 12, 0, 0));
        map.put(0xDB, new Z80Instruction(Z80OpCodes.IN, "IN A, (*)", 0xDB, "DB(*1)", 2, 12, 12, 0, 0));

        map.put(0x34, new Z80Instruction(Z80OpCodes.INC, "INC (HL)", 0x34, "34", 1, 11, 11, 0, 0));
        map.put(0x34DD, new Z80Instruction(Z80OpCodes.INC, "INC (IX*)", 0x34DD, "34DD(*1)", 3, 23, 23, 0, 0));
        map.put(0x34FD, new Z80Instruction(Z80OpCodes.INC, "INC (IY*)", 0x34FD, "34FD(*1)", 3, 23, 23, 0, 0));
        map.put(0x3C, new Z80Instruction(Z80OpCodes.INC, "INC A", 0x3C, "3C", 1, 4, 4, 0, 0));
        map.put(0x4, new Z80Instruction(Z80OpCodes.INC, "INC B", 0x4, "4", 1, 4, 4, 0, 0));
        map.put(0x0C, new Z80Instruction(Z80OpCodes.INC, "INC C", 0x0C, "0C", 1, 4, 4, 0, 0));
        map.put(0x14, new Z80Instruction(Z80OpCodes.INC, "INC D", 0x14, "14", 1, 4, 4, 0, 0));
        map.put(0x1C, new Z80Instruction(Z80OpCodes.INC, "INC E", 0x1C, "1C", 1, 4, 4, 0, 0));
        map.put(0x24, new Z80Instruction(Z80OpCodes.INC, "INC H", 0x24, "24", 1, 4, 4, 0, 0));
        map.put(0x2C, new Z80Instruction(Z80OpCodes.INC, "INC L", 0x2C, "2C", 1, 4, 4, 0, 0));
        map.put(0x24DD, new Z80Instruction(Z80OpCodes.INC, "INC IXH", 0x24DD, "24DD", 2, 0, 0, 0, 0));
        map.put(0x24FD, new Z80Instruction(Z80OpCodes.INC, "INC IYH", 0x24FD, "24FD", 2, 0, 0, 0, 0));
        map.put(0x2CDD, new Z80Instruction(Z80OpCodes.INC, "INC IXL", 0x2CDD, "2CDD", 2, 0, 0, 0, 0));
        map.put(0x2CFD, new Z80Instruction(Z80OpCodes.INC, "INC IYL", 0x2CFD, "2CFD", 2, 0, 0, 0, 0));

        map.put(0x3, new Z80Instruction(Z80OpCodes.INC, "INC BC", 0x3, "3", 1, 6, 6, 0, 0));
        map.put(0x13, new Z80Instruction(Z80OpCodes.INC, "INC DE", 0x13, "13", 1, 6, 6, 0, 0));
        map.put(0x23, new Z80Instruction(Z80OpCodes.INC, "INC HL", 0x23, "23", 1, 6, 6, 0, 0));
        map.put(0x23DD, new Z80Instruction(Z80OpCodes.INC, "INC IX", 0x23DD, "23DD", 2, 10, 10, 0, 0));
        map.put(0x23FD, new Z80Instruction(Z80OpCodes.INC, "INC IY", 0x23FD, "23FD", 2, 10, 10, 0, 0));
        map.put(0x33, new Z80Instruction(Z80OpCodes.INC, "INC SP", 0x33, "33", 1, 6, 6, 0, 0));

        map.put(0xAAED, new Z80Instruction(Z80OpCodes.IND, "IND", 0xAAED, "AAED", 2, 16, 16, 0, 0));
        map.put(0xBAED, new Z80Instruction(Z80OpCodes.INDR, "INDR", 0xBAED, "BAED", 2, 21, 21, 0, 0));

        map.put(0xA2ED, new Z80Instruction(Z80OpCodes.INI, "INI", 0xA2ED, "A2ED", 2, 16, 16, 0, 0));
        map.put(0xB2ED, new Z80Instruction(Z80OpCodes.INIR, "INIR", 0xB2ED, "B2ED", 2, 21, 21, 0, 0));

        map.put(0xE9, new Z80Instruction(Z80OpCodes.JP, "JP (HL)", 0xE9, "E9", 1, 4, 4, 0, 0));
        map.put(0xE9DD, new Z80Instruction(Z80OpCodes.JP, "JP (IX)", 0xE9DD, "E9DD", 2, 8, 8, 0, 0));
        map.put(0xE9FD, new Z80Instruction(Z80OpCodes.JP, "JP (IY)", 0xE9FD, "E9FD", 2, 8, 8, 0, 0));
        map.put(0xDA, new Z80Instruction(Z80OpCodes.JP, "JP C, *", 0xDA, "DA(*2)", 3, 10, 12, 1, 0));
        map.put(0xFA, new Z80Instruction(Z80OpCodes.JP, "JP M, *", 0xFA, "FA(*2)", 3, 10, 12, 1, 0));
        map.put(0xD2, new Z80Instruction(Z80OpCodes.JP, "JP NC, *", 0xD2, "D2(*2)", 3, 10, 12, 1, 0));
        map.put(0xC2, new Z80Instruction(Z80OpCodes.JP, "JP NZ, *", 0xC2, "C2(*2)", 3, 10, 12, 1, 0));
        map.put(0xF2, new Z80Instruction(Z80OpCodes.JP, "JP P, *", 0xF2, "F2(*2)", 3, 10, 12, 1, 0));
        map.put(0xEA, new Z80Instruction(Z80OpCodes.JP, "JP PE, *", 0xEA, "EA(*2)", 3, 10, 12, 1, 0));
        map.put(0xE2, new Z80Instruction(Z80OpCodes.JP, "JP PO, *", 0xE2, "E2(*2)", 3, 10, 12, 1, 0));
        map.put(0xCA, new Z80Instruction(Z80OpCodes.JP, "JP Z, *", 0xCA, "CA(*2)", 3, 10, 12, 1, 0));
        map.put(0xC3, new Z80Instruction(Z80OpCodes.JP, "JP *", 0xC3, "C3(*2)", 3, 10, 12, 0, 0));

        map.put(0x38, new Z80Instruction(Z80OpCodes.JR, "JR C, *", 0x38, "38(*1)", 2, 10, 10, 7, 8));
        map.put(0x30, new Z80Instruction(Z80OpCodes.JR, "JR NC, *", 0x30, "30(*1)", 2, 10, 10, 7, 8));
        map.put(0x20, new Z80Instruction(Z80OpCodes.JR, "JR NZ, *", 0x20, "20(*1)", 2, 10, 10, 7, 8));
        map.put(0x28, new Z80Instruction(Z80OpCodes.JR, "JR Z, *", 0x28, "28(*1)", 2, 10, 10, 7, 8));
        map.put(0x18, new Z80Instruction(Z80OpCodes.JR, "JR *", 0x18, "18(*1)", 2, 10, 10, 0, 0));

        map.put(0x2, new Z80Instruction(Z80OpCodes.LD, "LD (BC), A", 0x2, "2", 1, 7, 8, 0, 0));
        map.put(0x12, new Z80Instruction(Z80OpCodes.LD, "LD (DE), A", 0x12, "12", 1, 7, 8, 0, 0));
        map.put(0x77, new Z80Instruction(Z80OpCodes.LD, "LD (HL), A", 0x77, "77", 1, 7, 8, 0, 0));
        map.put(0x70, new Z80Instruction(Z80OpCodes.LD, "LD (HL), B", 0x70, "70", 1, 7, 8, 0, 0));
        map.put(0x71, new Z80Instruction(Z80OpCodes.LD, "LD (HL), C", 0x71, "71", 1, 7, 8, 0, 0));
        map.put(0x72, new Z80Instruction(Z80OpCodes.LD, "LD (HL), D", 0x72, "72", 1, 7, 8, 0, 0));
        map.put(0x73, new Z80Instruction(Z80OpCodes.LD, "LD (HL), E", 0x73, "73", 1, 7, 8, 0, 0));
        map.put(0x74, new Z80Instruction(Z80OpCodes.LD, "LD (HL), H", 0x74, "74", 1, 7, 8, 0, 0));
        map.put(0x75, new Z80Instruction(Z80OpCodes.LD, "LD (HL), L", 0x75, "75", 1, 7, 8, 0, 0));
        map.put(0x74DD, new Z80Instruction(Z80OpCodes.LD, "LD (HL), IXH", 0x74DD, "74DD", 2, 0, 0, 0, 0));
        map.put(0x74FD, new Z80Instruction(Z80OpCodes.LD, "LD (HL), IYH", 0x74FD, "74FD", 2, 0, 0, 0, 0));
        map.put(0x75DD, new Z80Instruction(Z80OpCodes.LD, "LD (HL), IXL", 0x75DD, "75DD", 2, 0, 0, 0, 0));
        map.put(0x75FD, new Z80Instruction(Z80OpCodes.LD, "LD (HL), IYL", 0x75FD, "75FD", 2, 0, 0, 0, 0));
        map.put(0x36, new Z80Instruction(Z80OpCodes.LD, "LD (HL), *", 0x36, "36(*1)", 2, 10, 12, 0, 0));

        map.put(0x77DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX*), A", 0x77DD, "77DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x70DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX*), B", 0x70DD, "70DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x71DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX*), C", 0x71DD, "71DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x72DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX*), D", 0x72DD, "72DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x73DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX*), E", 0x73DD, "73DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x74DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX*), H", 0x74DD, "74DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x75DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX*), L", 0x75DD, "75DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x36DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX*), *", 0x36DD, "36DD(*2)", 4, 19, 20, 0, 0));

        map.put(0x77FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY*), A", 0x77FD, "77FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x70FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY*), B", 0x70FD, "70FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x71FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY*), C", 0x71FD, "71FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x72FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY*), D", 0x72FD, "72FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x73FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY*), E", 0x73FD, "73FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x74FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY*), H", 0x74FD, "74FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x75FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY*), L", 0x75FD, "75FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x36FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY*), *", 0x36FD, "36FD(*2)", 4, 19, 20, 0, 0));

        map.put(0x32, new Z80Instruction(Z80OpCodes.LD, "LD (*), A", 0x32, "32(*2)", 3, 13, 16, 0, 0));
        map.put(0x43ED, new Z80Instruction(Z80OpCodes.LD, "LD (*), BC", 0x43ED, "43ED(*2)", 4, 20, 20, 0, 0));
        map.put(0x53ED, new Z80Instruction(Z80OpCodes.LD, "LD (*), DE", 0x53ED, "53ED(*2)", 4, 20, 20, 0, 0));
        map.put(0x22, new Z80Instruction(Z80OpCodes.LD, "LD (*), HL", 0x22, "22(*2)", 3, 20, 20, 0, 0));
        map.put(0x22FD, new Z80Instruction(Z80OpCodes.LD, "LD (*), IY", 0x22FD, "22FD(*2)", 4, 20, 20, 0, 0));
        map.put(0x73ED, new Z80Instruction(Z80OpCodes.LD, "LD (*), SP", 0x73ED, "73ED(*2)", 4, 20, 20, 0, 0));
        map.put(0x22DD, new Z80Instruction(Z80OpCodes.LD, "LD (*), IX", 0x22DD, "22DD(*2)", 4, 20, 20, 0, 0));

        map.put(0x0A, new Z80Instruction(Z80OpCodes.LD, "LD A, (BC)", 0x0A, "0A", 1, 7, 8, 0, 0));
        map.put(0x1A, new Z80Instruction(Z80OpCodes.LD, "LD A, (DE)", 0x1A, "1A", 1, 7, 8, 0, 0));
        map.put(0x7E, new Z80Instruction(Z80OpCodes.LD, "LD A, (HL)", 0x7E, "7E", 1, 7, 8, 0, 0));
        map.put(0x7EFD, new Z80Instruction(Z80OpCodes.LD, "LD A, (IY*)", 0x7EFD, "7EFD(*1)", 3, 19, 19, 0, 0));
        map.put(0x7EDD, new Z80Instruction(Z80OpCodes.LD, "LD A, (IX*)", 0x7EDD, "7EDD(*1)", 3, 19, 19, 0, 0));
        map.put(0x3A, new Z80Instruction(Z80OpCodes.LD, "LD A, (*)", 0x3A, "3A(*2)", 3, 13, 16, 0, 0));
        map.put(0x7F, new Z80Instruction(Z80OpCodes.LD, "LD A, A", 0x7F, "7F", 1, 4, 4, 0, 0));
        map.put(0x78, new Z80Instruction(Z80OpCodes.LD, "LD A, B", 0x78, "78", 1, 4, 4, 0, 0));
        map.put(0x79, new Z80Instruction(Z80OpCodes.LD, "LD A, C", 0x79, "79", 1, 4, 4, 0, 0));
        map.put(0x7A, new Z80Instruction(Z80OpCodes.LD, "LD A, D", 0x7A, "7A", 1, 4, 4, 0, 0));
        map.put(0x7B, new Z80Instruction(Z80OpCodes.LD, "LD A, E", 0x7B, "7B", 1, 4, 4, 0, 0));
        map.put(0x7C, new Z80Instruction(Z80OpCodes.LD, "LD A, H", 0x7C, "7C", 1, 4, 4, 0, 0));
        map.put(0x7D, new Z80Instruction(Z80OpCodes.LD, "LD A, L", 0x7D, "7D", 1, 4, 4, 0, 0));
        map.put(0x57ED, new Z80Instruction(Z80OpCodes.LD, "LD A, I", 0x57ED, "57ED", 2, 9, 9, 0, 0));
        map.put(0x5FED, new Z80Instruction(Z80OpCodes.LD, "LD A, R", 0x5FED, "5FED", 2, 9, 9, 0, 0));
        map.put(0x7CDD, new Z80Instruction(Z80OpCodes.LD, "LD A, IXH", 0x7CDD, "7CDD", 2, 0, 0, 0, 0));
        map.put(0x7CFD, new Z80Instruction(Z80OpCodes.LD, "LD A, IYH", 0x7CFD, "7CFD", 2, 0, 0, 0, 0));
        map.put(0x7DDD, new Z80Instruction(Z80OpCodes.LD, "LD A, IXL", 0x7DDD, "7DDD", 2, 0, 0, 0, 0));
        map.put(0x7DFD, new Z80Instruction(Z80OpCodes.LD, "LD A, IYL", 0x7DFD, "7DFD", 2, 0, 0, 0, 0));
        map.put(0x3E, new Z80Instruction(Z80OpCodes.LD, "LD A, *", 0x3E, "3E(*1)", 2, 7, 8, 0, 0));

        map.put(0x46, new Z80Instruction(Z80OpCodes.LD, "LD B, (HL)", 0x46, "46", 1, 7, 8, 0, 0));
        map.put(0x46DD, new Z80Instruction(Z80OpCodes.LD, "LD B, (IX*)", 0x46DD, "46DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x46FD, new Z80Instruction(Z80OpCodes.LD, "LD B, (IY*)", 0x46FD, "46FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x47, new Z80Instruction(Z80OpCodes.LD, "LD B, A", 0x47, "47", 1, 4, 4, 0, 0));
        map.put(0x40, new Z80Instruction(Z80OpCodes.LD, "LD B, B", 0x40, "40", 1, 4, 4, 0, 0));
        map.put(0x41, new Z80Instruction(Z80OpCodes.LD, "LD B, C", 0x41, "41", 1, 4, 4, 0, 0));
        map.put(0x42, new Z80Instruction(Z80OpCodes.LD, "LD B, D", 0x42, "42", 1, 4, 4, 0, 0));
        map.put(0x43, new Z80Instruction(Z80OpCodes.LD, "LD B, E", 0x43, "43", 1, 4, 4, 0, 0));
        map.put(0x44, new Z80Instruction(Z80OpCodes.LD, "LD B, H", 0x44, "44", 1, 4, 4, 0, 0));
        map.put(0x45, new Z80Instruction(Z80OpCodes.LD, "LD B, L", 0x45, "45", 1, 4, 4, 0, 0));
        map.put(0x44DD, new Z80Instruction(Z80OpCodes.LD, "LD B, IXH", 0x44DD, "44DD", 2, 0, 0, 0, 0));
        map.put(0x44FD, new Z80Instruction(Z80OpCodes.LD, "LD B, IYH", 0x44FD, "44FD", 2, 0, 0, 0, 0));
        map.put(0x45DD, new Z80Instruction(Z80OpCodes.LD, "LD B, IXL", 0x45DD, "45DD", 2, 0, 0, 0, 0));
        map.put(0x45FD, new Z80Instruction(Z80OpCodes.LD, "LD B, IYL", 0x45FD, "45FD", 2, 0, 0, 0, 0));
        map.put(0x06, new Z80Instruction(Z80OpCodes.LD, "LD B, *", 0x06, "06(*1)", 2, 7, 8, 0, 0));

        map.put(0x4E, new Z80Instruction(Z80OpCodes.LD, "LD C, (HL)", 0x4E, "4E", 1, 7, 7, 0, 0));
        map.put(0x4EDD, new Z80Instruction(Z80OpCodes.LD, "LD C, (IX*)", 0x4EDD, "4EDD(*1)", 3, 19, 19, 0, 0));
        map.put(0x4EFD, new Z80Instruction(Z80OpCodes.LD, "LD C, (IY*)", 0x4EFD, "4EFD(*1)", 3, 19, 19, 0, 0));
        map.put(0x4F, new Z80Instruction(Z80OpCodes.LD, "LD C, A", 0x4F, "4F", 1, 4, 4, 0, 0));
        map.put(0x48, new Z80Instruction(Z80OpCodes.LD, "LD C, B", 0x48, "48", 1, 4, 4, 0, 0));
        map.put(0x49, new Z80Instruction(Z80OpCodes.LD, "LD C, C", 0x49, "49", 1, 4, 4, 0, 0));
        map.put(0x4A, new Z80Instruction(Z80OpCodes.LD, "LD C, D", 0x4A, "4A", 1, 4, 4, 0, 0));
        map.put(0x4B, new Z80Instruction(Z80OpCodes.LD, "LD C, E", 0x4B, "4B", 1, 4, 4, 0, 0));
        map.put(0x4C, new Z80Instruction(Z80OpCodes.LD, "LD C, H", 0x4C, "4C", 1, 4, 4, 0, 0));
        map.put(0x4D, new Z80Instruction(Z80OpCodes.LD, "LD C, L", 0x4D, "4D", 1, 4, 4, 0, 0));
        map.put(0x4CDD, new Z80Instruction(Z80OpCodes.LD, "LD C, IXH", 0x4CDD, "4CDD", 2, 0, 0, 0, 0));
        map.put(0x4CFD, new Z80Instruction(Z80OpCodes.LD, "LD C, IYH", 0x4CFD, "4CFD", 2, 0, 0, 0, 0));
        map.put(0x4DDD, new Z80Instruction(Z80OpCodes.LD, "LD C, IXL", 0x4DDD, "4DDD", 2, 0, 0, 0, 0));
        map.put(0x4DFD, new Z80Instruction(Z80OpCodes.LD, "LD C, IYL", 0x4DFD, "4DFD", 2, 0, 0, 0, 0));
        map.put(0x0E, new Z80Instruction(Z80OpCodes.LD, "LD C, *", 0x0E, "0E(*1)", 2, 7, 8, 0, 0));

        map.put(0x56, new Z80Instruction(Z80OpCodes.LD, "LD D, (HL)", 0x56, "56", 1, 7, 8, 0, 0));
        map.put(0x56DD, new Z80Instruction(Z80OpCodes.LD, "LD D, (IX*)", 0x56DD, "56DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x56FD, new Z80Instruction(Z80OpCodes.LD, "LD D, (IY*)", 0x56FD, "56FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x57, new Z80Instruction(Z80OpCodes.LD, "LD D, A", 0x57, "57", 1, 4, 4, 0, 0));
        map.put(0x50, new Z80Instruction(Z80OpCodes.LD, "LD D, B", 0x50, "50", 1, 4, 4, 0, 0));
        map.put(0x51, new Z80Instruction(Z80OpCodes.LD, "LD D, C", 0x51, "51", 1, 4, 4, 0, 0));
        map.put(0x52, new Z80Instruction(Z80OpCodes.LD, "LD D, D", 0x52, "52", 1, 4, 4, 0, 0));
        map.put(0x53, new Z80Instruction(Z80OpCodes.LD, "LD D, E", 0x53, "53", 1, 4, 4, 0, 0));
        map.put(0x54, new Z80Instruction(Z80OpCodes.LD, "LD D, H", 0x54, "54", 1, 4, 4, 0, 0));
        map.put(0x55, new Z80Instruction(Z80OpCodes.LD, "LD D, L", 0x55, "55", 1, 4, 4, 0, 0));
        map.put(0x54DD, new Z80Instruction(Z80OpCodes.LD, "LD D, IXH", 0x54DD, "54DD", 2, 0, 0, 0, 0));
        map.put(0x54FD, new Z80Instruction(Z80OpCodes.LD, "LD D, IYH", 0x54FD, "54FD", 2, 0, 0, 0, 0));
        map.put(0x55DD, new Z80Instruction(Z80OpCodes.LD, "LD D, IXL", 0x55DD, "55DD", 2, 0, 0, 0, 0));
        map.put(0x55FD, new Z80Instruction(Z80OpCodes.LD, "LD D, IYL", 0x55FD, "55FD", 2, 0, 0, 0, 0));
        map.put(0x16, new Z80Instruction(Z80OpCodes.LD, "LD D, *", 0x16, "16(*1)", 2, 7, 8, 0, 0));

        map.put(0x5E, new Z80Instruction(Z80OpCodes.LD, "LD E, (HL)", 0x5E, "5E", 1, 7, 8, 0, 0));
        map.put(0x5EDD, new Z80Instruction(Z80OpCodes.LD, "LD E, (IX*)", 0x5EDD, "5EDD(*1)", 3, 19, 19, 0, 0));
        map.put(0x5EFD, new Z80Instruction(Z80OpCodes.LD, "LD E, (IY*)", 0x5EFD, "5EFD(*1)", 3, 19, 19, 0, 0));
        map.put(0x5F, new Z80Instruction(Z80OpCodes.LD, "LD E, A", 0x5F, "5F", 1, 4, 4, 0, 0));
        map.put(0x58, new Z80Instruction(Z80OpCodes.LD, "LD E, B", 0x58, "58", 1, 4, 4, 0, 0));
        map.put(0x59, new Z80Instruction(Z80OpCodes.LD, "LD E, C", 0x59, "59", 1, 4, 4, 0, 0));
        map.put(0x5A, new Z80Instruction(Z80OpCodes.LD, "LD E, D", 0x5A, "5A", 1, 4, 4, 0, 0));
        map.put(0x5B, new Z80Instruction(Z80OpCodes.LD, "LD E, E", 0x5B, "5B", 1, 4, 4, 0, 0));
        map.put(0x5C, new Z80Instruction(Z80OpCodes.LD, "LD E, H", 0x5C, "5C", 1, 4, 4, 0, 0));
        map.put(0x5D, new Z80Instruction(Z80OpCodes.LD, "LD E, L", 0x5D, "5D", 1, 4, 4, 0, 0));
        map.put(0x5CDD, new Z80Instruction(Z80OpCodes.LD, "LD E, IXH", 0x5CDD, "5CDD", 2, 0, 0, 0, 0));
        map.put(0x5CFD, new Z80Instruction(Z80OpCodes.LD, "LD E, IYH", 0x5CFD, "5CFD", 2, 0, 0, 0, 0));
        map.put(0x5DDD, new Z80Instruction(Z80OpCodes.LD, "LD E, IXL", 0x5DDD, "5DDD", 2, 0, 0, 0, 0));
        map.put(0x5DFD, new Z80Instruction(Z80OpCodes.LD, "LD E, IYL", 0x5DFD, "5DFD", 2, 0, 0, 0, 0));
        map.put(0x1E, new Z80Instruction(Z80OpCodes.LD, "LD E, *", 0x1E, "1E(*1)", 2, 7, 8, 0, 0));

        map.put(0x66, new Z80Instruction(Z80OpCodes.LD, "LD H, (HL)", 0x66, "66", 1, 7, 8, 0, 0));
        map.put(0x66DD, new Z80Instruction(Z80OpCodes.LD, "LD H, (IX*)", 0x66DD, "66DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x66FD, new Z80Instruction(Z80OpCodes.LD, "LD H, (IY*)", 0x66FD, "66FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x67, new Z80Instruction(Z80OpCodes.LD, "LD H, A", 0x67, "67", 1, 4, 4, 0, 0));
        map.put(0x60, new Z80Instruction(Z80OpCodes.LD, "LD H, B", 0x60, "60", 1, 4, 4, 0, 0));
        map.put(0x61, new Z80Instruction(Z80OpCodes.LD, "LD H, C", 0x61, "61", 1, 4, 4, 0, 0));
        map.put(0x62, new Z80Instruction(Z80OpCodes.LD, "LD H, D", 0x62, "62", 1, 4, 4, 0, 0));
        map.put(0x63, new Z80Instruction(Z80OpCodes.LD, "LD H, E", 0x63, "63", 1, 4, 4, 0, 0));
        map.put(0x64, new Z80Instruction(Z80OpCodes.LD, "LD H, H", 0x64, "64", 1, 4, 4, 0, 0));
        map.put(0x65, new Z80Instruction(Z80OpCodes.LD, "LD H, L", 0x65, "65", 1, 4, 4, 0, 0));
        map.put(0x26, new Z80Instruction(Z80OpCodes.LD, "LD H, *", 0x26, "26(*1)", 2, 7, 8, 0, 0));

        map.put(0x6E, new Z80Instruction(Z80OpCodes.LD, "LD L, (HL)", 0x6E, "6E", 1, 7, 7, 0, 0));
        map.put(0x6EDD, new Z80Instruction(Z80OpCodes.LD, "LD L, (IX*)", 0x6EDD, "6EDD(*1)", 3, 19, 19, 0, 0));
        map.put(0x6EFD, new Z80Instruction(Z80OpCodes.LD, "LD L, (IY*)", 0x6EFD, "6EFD(*1)", 3, 19, 19, 0, 0));
        map.put(0x6F, new Z80Instruction(Z80OpCodes.LD, "LD L, A", 0x6F, "6F", 1, 4, 4, 0, 0));
        map.put(0x68, new Z80Instruction(Z80OpCodes.LD, "LD L, B", 0x68, "68", 1, 4, 4, 0, 0));
        map.put(0x69, new Z80Instruction(Z80OpCodes.LD, "LD L, C", 0x69, "69", 1, 4, 4, 0, 0));
        map.put(0x6A, new Z80Instruction(Z80OpCodes.LD, "LD L, D", 0x6A, "6A", 1, 4, 4, 0, 0));
        map.put(0x6B, new Z80Instruction(Z80OpCodes.LD, "LD L, E", 0x6B, "6B", 1, 4, 4, 0, 0));
        map.put(0x6C, new Z80Instruction(Z80OpCodes.LD, "LD L, H", 0x6C, "6C", 1, 4, 4, 0, 0));
        map.put(0x6D, new Z80Instruction(Z80OpCodes.LD, "LD L, L", 0x6D, "6D", 1, 4, 4, 0, 0));
        map.put(0x2E, new Z80Instruction(Z80OpCodes.LD, "LD L, *", 0x2E, "2E(*1)", 2, 7, 8, 0, 0));

        map.put(0x47ED, new Z80Instruction(Z80OpCodes.LD, "LD I, A", 0x47ED, "47ED", 2, 9, 9, 0, 0));

        map.put(0x4FED, new Z80Instruction(Z80OpCodes.LD, "LD R, A", 0x4FED, "4FED", 2, 9, 9, 0, 0));

        map.put(0x66DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, (HL)", 0x66DD, "66DD", 2, 0, 0, 0, 0));
        map.put(0x67DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, A", 0x67DD, "67DD", 2, 0, 0, 0, 0));
        map.put(0x60DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, B", 0x60DD, "60DD", 2, 0, 0, 0, 0));
        map.put(0x61DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, C", 0x61DD, "61DD", 2, 0, 0, 0, 0));
        map.put(0x62DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, D", 0x62DD, "62DD", 2, 0, 0, 0, 0));
        map.put(0x63DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, E", 0x63DD, "63DD", 2, 0, 0, 0, 0));
        map.put(0x64DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, H", 0x64DD, "64DD", 2, 0, 0, 0, 0));
        map.put(0x65DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, L", 0x65DD, "65DD", 2, 0, 0, 0, 0));
        map.put(0x26DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, *", 0x26DD, "26DD(*1)", 3, 0, 0, 0, 0));

        map.put(0x66FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, (HL)", 0x66FD, "66FD", 2, 0, 0, 0, 0));
        map.put(0x67FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, A", 0x67FD, "67FD", 2, 0, 0, 0, 0));
        map.put(0x60FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, B", 0x60FD, "60FD", 2, 0, 0, 0, 0));
        map.put(0x62FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, D", 0x62FD, "62FD", 2, 0, 0, 0, 0));
        map.put(0x61FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, C", 0x61FD, "61FD", 2, 0, 0, 0, 0));
        map.put(0x63FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, E", 0x63FD, "63FD", 2, 0, 0, 0, 0));
        map.put(0x64FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, H", 0x64FD, "64FD", 2, 0, 0, 0, 0));
        map.put(0x65FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, L", 0x65FD, "65FD", 2, 0, 0, 0, 0));
        map.put(0x26FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, *", 0x26FD, "26FD(*1)", 3, 0, 0, 0, 0));

        map.put(0x6EDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, (HL)", 0x6EDD, "6EDD", 2, 0, 0, 0, 0));
        map.put(0x6FDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, A", 0x6FDD, "6FDD", 2, 0, 0, 0, 0));
        map.put(0x68DD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, B", 0x68DD, "68DD", 2, 0, 0, 0, 0));
        map.put(0x69DD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, C", 0x69DD, "69DD", 2, 0, 0, 0, 0));
        map.put(0x6ADD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, D", 0x6ADD, "6ADD", 2, 0, 0, 0, 0));
        map.put(0x6BDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, E", 0x6BDD, "6BDD", 2, 0, 0, 0, 0));
        map.put(0x6CDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, H", 0x6CDD, "6CDD", 2, 0, 0, 0, 0));
        map.put(0x6DDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, L", 0x6DDD, "6DDD", 2, 0, 0, 0, 0));
        map.put(0x2EDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, *", 0x2EDD, "2EDD(*1)", 3, 0, 0, 0, 0));

        map.put(0x6EFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, (HL)", 0x6EFD, "6EFD", 2, 0, 0, 0, 0));
        map.put(0x6FFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, A", 0x6FFD, "6FFD", 2, 0, 0, 0, 0));
        map.put(0x68FD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, B", 0x68FD, "68FD", 2, 0, 0, 0, 0));
        map.put(0x69FD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, C", 0x69FD, "69FD", 2, 0, 0, 0, 0));
        map.put(0x6AFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, D", 0x6AFD, "6AFD", 2, 0, 0, 0, 0));
        map.put(0x6BFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, E", 0x6BFD, "6BFD", 2, 0, 0, 0, 0));
        map.put(0x6CFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, H", 0x6CFD, "6CFD", 2, 0, 0, 0, 0));
        map.put(0x6DFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, L", 0x6DFD, "6DFD", 2, 0, 0, 0, 0));
        map.put(0x2EFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, *", 0x2EFD, "2EFD(*1)", 3, 0, 0, 0, 0));

        map.put(0x4BED, new Z80Instruction(Z80OpCodes.LD, "LD BC, (*)", 0x4BED, "4BED(*2)", 4, 20, 20, 0, 0));
        map.put(0x01, new Z80Instruction(Z80OpCodes.LD, "LD BC, *", 0x01, "01(*2)", 3, 10, 12, 0, 0));
        map.put(0x5BED, new Z80Instruction(Z80OpCodes.LD, "LD DE, (*)", 0x5BED, "5BED(*2)", 4, 20, 20, 0, 0));
        map.put(0x11, new Z80Instruction(Z80OpCodes.LD, "LD DE, *", 0x11, "11(*2)", 3, 10, 12, 0, 0));
        map.put(0x2A, new Z80Instruction(Z80OpCodes.LD, "LD HL, (*)", 0x2A, "2A(*2)", 3, 16, 16, 0, 0));
        map.put(0x21, new Z80Instruction(Z80OpCodes.LD, "LD HL, *", 0x21, "21(*2)", 3, 10, 10, 0, 0));
        map.put(0x2ADD, new Z80Instruction(Z80OpCodes.LD, "LD IX, (*)", 0x2ADD, "2ADD(*2)", 4, 20, 20, 0, 0));
        map.put(0x21DD, new Z80Instruction(Z80OpCodes.LD, "LD IX, *", 0x21DD, "21DD(*2)", 4, 14, 16, 0, 0));
        map.put(0x2AFD, new Z80Instruction(Z80OpCodes.LD, "LD IY, (*)", 0x2AFD, "2AFD(*2)", 4, 20, 20, 0, 0));
        map.put(0x21FD, new Z80Instruction(Z80OpCodes.LD, "LD IY, *", 0x21FD, "21FD(*2)", 4, 14, 16, 0, 0));
        map.put(0x7BED, new Z80Instruction(Z80OpCodes.LD, "LD SP, (*)", 0x7BED, "7BED(*2)", 4, 20, 20, 0, 0));
        map.put(0xF9, new Z80Instruction(Z80OpCodes.LD, "LD SP, HL", 0xF9, "F9", 1, 6, 6, 0, 0));
        map.put(0xF9DD, new Z80Instruction(Z80OpCodes.LD, "LD SP, IX", 0xF9DD, "F9DD", 2, 10, 10, 0, 0));
        map.put(0xF9FD, new Z80Instruction(Z80OpCodes.LD, "LD SP, IY", 0xF9FD, "F9FD", 2, 10, 10, 0, 0));
        map.put(0x31, new Z80Instruction(Z80OpCodes.LD, "LD SP, *", 0x31, "31(*2)", 3, 10, 12, 0, 0));

        map.put(0xA8ED, new Z80Instruction(Z80OpCodes.LDD, "LDD", 0xA8ED, "A8ED", 2, 16, 16, 0, 0));
        map.put(0xB8ED, new Z80Instruction(Z80OpCodes.LDDR, "LDDR", 0xB8ED, "B8ED", 2, 21, 21, 0, 0));
        map.put(0xA0ED, new Z80Instruction(Z80OpCodes.LDI, "LDI", 0xA0ED, "A0ED", 2, 16, 16, 0, 0));
        map.put(0xB0ED, new Z80Instruction(Z80OpCodes.LDIR, "LDIR", 0xB0ED, "B0ED", 2, 21, 21, 0, 0));

        map.put(0x44ED, new Z80Instruction(Z80OpCodes.NEG, "NEG", 0x44ED, "44ED", 2, 8, 8, 0, 0));

        map.put(0x0, new Z80Instruction(Z80OpCodes.NOP, "NOP", 0x0, "0", 1, 4, 4, 0, 0));

        map.put(0xB6, new Z80Instruction(Z80OpCodes.OR, "OR (HL)", 0xB6, "B6", 1, 7, 8, 0, 0));
        map.put(0xB6DD, new Z80Instruction(Z80OpCodes.OR, "OR (IX*)", 0xB6DD, "B6DD(*1)", 3, 19, 19, 0, 0));
        map.put(0xB6FD, new Z80Instruction(Z80OpCodes.OR, "OR (IY*)", 0xB6FD, "B6FD(*1)", 3, 19, 19, 0, 0));
        map.put(0xB7, new Z80Instruction(Z80OpCodes.OR, "OR A", 0xB7, "B7", 1, 4, 4, 0, 0));
        map.put(0xB0, new Z80Instruction(Z80OpCodes.OR, "OR B", 0xB0, "B0", 1, 4, 4, 0, 0));
        map.put(0xB1, new Z80Instruction(Z80OpCodes.OR, "OR C", 0xB1, "B1", 1, 4, 4, 0, 0));
        map.put(0xB2, new Z80Instruction(Z80OpCodes.OR, "OR D", 0xB2, "B2", 1, 4, 4, 0, 0));
        map.put(0xB3, new Z80Instruction(Z80OpCodes.OR, "OR E", 0xB3, "B3", 1, 4, 4, 0, 0));
        map.put(0xB4, new Z80Instruction(Z80OpCodes.OR, "OR H", 0xB4, "B4", 1, 4, 4, 0, 0));
        map.put(0xB5, new Z80Instruction(Z80OpCodes.OR, "OR L", 0xB5, "B5", 1, 4, 4, 0, 0));
        map.put(0xB4DD, new Z80Instruction(Z80OpCodes.OR, "OR IXH", 0xB4DD, "B4DD", 2, 0, 0, 0, 0));
        map.put(0xB4FD, new Z80Instruction(Z80OpCodes.OR, "OR IYH", 0xB4FD, "B4FD", 2, 0, 0, 0, 0));
        map.put(0xB5DD, new Z80Instruction(Z80OpCodes.OR, "OR IXL", 0xB5DD, "B5DD", 2, 0, 0, 0, 0));
        map.put(0xB5FD, new Z80Instruction(Z80OpCodes.OR, "OR IYL", 0xB5FD, "B5FD", 2, 0, 0, 0, 0));
        map.put(0xF6, new Z80Instruction(Z80OpCodes.OR, "OR *", 0xF6, "F6(*1)", 2, 7, 8, 0, 0));

        map.put(0x8BED, new Z80Instruction(Z80OpCodes.OTDR, "OTDR", 0x8BED, "8BED", 2, 21, 21, 0, 0));
        map.put(0xB3ED, new Z80Instruction(Z80OpCodes.OTIR, "OTIR", 0xB3ED, "B3ED", 2, 21, 21, 0, 0));

        map.put(0x79ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), A", 0x79ED, "79ED", 2, 12, 12, 0, 0));
        map.put(0x41ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), B", 0x41ED, "41ED", 2, 12, 12, 0, 0));
        map.put(0x49ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), C", 0x49ED, "49ED", 2, 12, 12, 0, 0));
        map.put(0x51ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), D", 0x51ED, "51ED", 2, 12, 12, 0, 0));
        map.put(0x59ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), E", 0x59ED, "59ED", 2, 12, 12, 0, 0));
        map.put(0x61ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), H", 0x61ED, "61ED", 2, 12, 12, 0, 0));
        map.put(0x69ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), L", 0x69ED, "69ED", 2, 12, 12, 0, 0));
        map.put(0xD3, new Z80Instruction(Z80OpCodes.OUT, "OUT (*), A", 0xD3, "D3(*1)", 2, 12, 12, 0, 0));

        map.put(0xABED, new Z80Instruction(Z80OpCodes.OUTD, "OUTD", 0xABED, "ABED", 2, 0, 0, 0, 0));
        map.put(0xA3ED, new Z80Instruction(Z80OpCodes.OUTI, "OUTI", 0xA3ED, "A3ED", 2, 0, 0, 0, 0));

        map.put(0xF1, new Z80Instruction(Z80OpCodes.POP, "POP AF", 0xF1, "F1", 1, 10, 10, 0, 0));
        map.put(0xC1, new Z80Instruction(Z80OpCodes.POP, "POP BC", 0xC1, "C1", 1, 10, 10, 0, 0));
        map.put(0xD1, new Z80Instruction(Z80OpCodes.POP, "POP DE", 0xD1, "D1", 1, 10, 10, 0, 0));
        map.put(0xE1, new Z80Instruction(Z80OpCodes.POP, "POP HL", 0xE1, "E1", 1, 10, 10, 0, 0));
        map.put(0xE1DD, new Z80Instruction(Z80OpCodes.POP, "POP IX", 0xE1DD, "E1DD", 2, 14, 14, 0, 0));
        map.put(0xE1FD, new Z80Instruction(Z80OpCodes.POP, "POP IY", 0xE1FD, "E1FD", 2, 14, 14, 0, 0));

        map.put(0xF5, new Z80Instruction(Z80OpCodes.PUSH, "PUSH AF", 0xF5, "F5", 1, 11, 11, 0, 0));
        map.put(0xC5, new Z80Instruction(Z80OpCodes.PUSH, "PUSH BC", 0xC5, "C5", 1, 11, 11, 0, 0));
        map.put(0xD5, new Z80Instruction(Z80OpCodes.PUSH, "PUSH DE", 0xD5, "D5", 1, 11, 11, 0, 0));
        map.put(0xE5, new Z80Instruction(Z80OpCodes.PUSH, "PUSH HL", 0xE5, "E5", 1, 11, 11, 0, 0));
        map.put(0xE5DD, new Z80Instruction(Z80OpCodes.PUSH, "PUSH IX", 0xE5DD, "E5DD", 2, 15, 15, 0, 0));
        map.put(0xE5FD, new Z80Instruction(Z80OpCodes.PUSH, "PUSH IY", 0xE5FD, "E5FD", 2, 15, 15, 0, 0));

        map.put(0x86CB, new Z80Instruction(Z80OpCodes.RES, "RES *, (HL)", 0x86CB, "86CB", 2, 12, 12, 0, 0));
        map.put(0xCBDD8600, new Z80Instruction(Z80OpCodes.RES, "RES *, (IX*)", 0xCBDD8600, "CBDD 8600", 4, 20, 20, 0, 0));
        map.put(0xCBFD8600, new Z80Instruction(Z80OpCodes.RES, "RES *, (IY*)", 0xCBFD8600, "CBFD 8600", 4, 20, 20, 0, 0));
        map.put(0x87CB, new Z80Instruction(Z80OpCodes.RES, "RES *, A", 0x87CB, "87CB", 2, 8, 8, 0, 0));
        map.put(0x80CB, new Z80Instruction(Z80OpCodes.RES, "RES *, B", 0x80CB, "80CB", 2, 8, 8, 0, 0));
        map.put(0x81CB, new Z80Instruction(Z80OpCodes.RES, "RES *, C", 0x81CB, "81CB", 2, 8, 8, 0, 0));
        map.put(0x82CB, new Z80Instruction(Z80OpCodes.RES, "RES *, D", 0x82CB, "82CB", 2, 8, 8, 0, 0));
        map.put(0x83CB, new Z80Instruction(Z80OpCodes.RES, "RES *, E", 0x83CB, "83CB", 2, 8, 8, 0, 0));
        map.put(0x84CB, new Z80Instruction(Z80OpCodes.RES, "RES *, H", 0x84CB, "84CB", 2, 8, 8, 0, 0));
        map.put(0x85CB, new Z80Instruction(Z80OpCodes.RES, "RES *, L", 0x85CB, "85CB", 2, 8, 8, 0, 0));

        map.put(0xC9, new Z80Instruction(Z80OpCodes.RET, "RET", 0xC9, "C9", 1, 10, 10, 0, 0));
        map.put(0xD8, new Z80Instruction(Z80OpCodes.RET, "RET C", 0xD8, "D8", 1, 11, 11, 5, 5));
        map.put(0xF8, new Z80Instruction(Z80OpCodes.RET, "RET M", 0xF8, "F8", 1, 11, 11, 5, 5));
        map.put(0xD0, new Z80Instruction(Z80OpCodes.RET, "RET NC", 0xD0, "D0", 1, 11, 11, 5, 5));
        map.put(0xC0, new Z80Instruction(Z80OpCodes.RET, "RET NZ", 0xC0, "C0", 1, 11, 11, 5, 5));
        map.put(0xF0, new Z80Instruction(Z80OpCodes.RET, "RET P", 0xF0, "F0", 1, 11, 11, 5, 5));
        map.put(0xE8, new Z80Instruction(Z80OpCodes.RET, "RET PE", 0xE8, "E8", 1, 11, 11, 5, 5));
        map.put(0xE0, new Z80Instruction(Z80OpCodes.RET, "RET PO", 0xE0, "E0", 1, 11, 11, 5, 5));
        map.put(0xC8, new Z80Instruction(Z80OpCodes.RET, "RET Z", 0xC8, "C8", 1, 11, 11, 5, 5));

        map.put(0x4DED, new Z80Instruction(Z80OpCodes.RETI, "RETI", 0x4DED, "4DED", 2, 14, 14, 0, 0));
        map.put(0x45ED, new Z80Instruction(Z80OpCodes.RETN, "RETN", 0x45ED, "45ED", 2, 14, 14, 0, 0));

        map.put(0x16CB, new Z80Instruction(Z80OpCodes.RL, "RL (HL)", 0x16CB, "16CB", 2, 15, 15, 0, 0));
        map.put(0xCBDD, new Z80Instruction(Z80OpCodes.RL, "RL (IX*)", 0xCBDD, "CBDD(*1)", 4, 23, 23, 0, 0));
        map.put(0xCBFD, new Z80Instruction(Z80OpCodes.RL, "RL (IY*)", 0xCBFD, "CBFD(*1)", 4, 23, 23, 0, 0));
        map.put(0x17CB, new Z80Instruction(Z80OpCodes.RL, "RL A", 0x17CB, "17CB", 2, 8, 8, 0, 0));
        map.put(0x10CB, new Z80Instruction(Z80OpCodes.RL, "RL B", 0x10CB, "10CB", 2, 8, 8, 0, 0));
        map.put(0x11CB, new Z80Instruction(Z80OpCodes.RL, "RL C", 0x11CB, "11CB", 2, 8, 8, 0, 0));
        map.put(0x12CB, new Z80Instruction(Z80OpCodes.RL, "RL D", 0x12CB, "12CB", 2, 8, 8, 0, 0));
        map.put(0x13CB, new Z80Instruction(Z80OpCodes.RL, "RL E", 0x13CB, "13CB", 2, 8, 8, 0, 0));
        map.put(0x14CB, new Z80Instruction(Z80OpCodes.RL, "RL H", 0x14CB, "14CB", 2, 8, 8, 0, 0));
        map.put(0x15CB, new Z80Instruction(Z80OpCodes.RL, "RL L", 0x15CB, "15CB", 2, 8, 8, 0, 0));

        map.put(0x17, new Z80Instruction(Z80OpCodes.RLA, "RLA", 0x17, "17", 1, 4, 4, 0, 0));

        map.put(0x06CB, new Z80Instruction(Z80OpCodes.RLC, "RLC (HL)", 0x06CB, "06CB", 2, 15, 15, 0, 0));
        map.put(0xCBDD, new Z80Instruction(Z80OpCodes.RLC, "RLC (IX*)", 0xCBDD, "CBDD(*1)", 4, 23, 23, 0, 0));
        map.put(0xCBFD, new Z80Instruction(Z80OpCodes.RLC, "RLC (IY*)", 0xCBFD, "CBFD(*1)", 4, 23, 23, 0, 0));
        map.put(0x07CB, new Z80Instruction(Z80OpCodes.RLC, "RLC A", 0x07CB, "07CB", 2, 8, 8, 0, 0));
        map.put(0x00CB, new Z80Instruction(Z80OpCodes.RLC, "RLC B", 0x00CB, "00CB", 2, 8, 8, 0, 0));
        map.put(0x01CB, new Z80Instruction(Z80OpCodes.RLC, "RLC C", 0x01CB, "01CB", 2, 8, 8, 0, 0));
        map.put(0x02CB, new Z80Instruction(Z80OpCodes.RLC, "RLC D", 0x02CB, "02CB", 2, 8, 8, 0, 0));
        map.put(0x03CB, new Z80Instruction(Z80OpCodes.RLC, "RLC E", 0x03CB, "03CB", 2, 8, 8, 0, 0));
        map.put(0x04CB, new Z80Instruction(Z80OpCodes.RLC, "RLC H", 0x04CB, "04CB", 2, 8, 8, 0, 0));
        map.put(0x05CB, new Z80Instruction(Z80OpCodes.RLC, "RLC L", 0x05CB, "05CB", 2, 8, 8, 0, 0));

        map.put(0x7, new Z80Instruction(Z80OpCodes.RLCA, "RLCA", 0x7, "7", 1, 4, 4, 0, 0));

        map.put(0x6FED, new Z80Instruction(Z80OpCodes.RLD, "RLD", 0x6FED, "6FED", 2, 18, 18, 0, 0));

        map.put(0x1ECB, new Z80Instruction(Z80OpCodes.RR, "RR (HL)", 0x1ECB, "1ECB", 2, 15, 15, 0, 0));
        map.put(0xCBDD, new Z80Instruction(Z80OpCodes.RR, "RR (IX*)", 0xCBDD, "CBDD(*1)", 4, 23, 23, 0, 0));
        map.put(0xCBFD, new Z80Instruction(Z80OpCodes.RR, "RR (IY*)", 0xCBFD, "CBFD(*1)", 4, 23, 23, 0, 0));
        map.put(0x1FCB, new Z80Instruction(Z80OpCodes.RR, "RR A", 0x1FCB, "1FCB", 2, 8, 8, 0, 0));
        map.put(0x18CB, new Z80Instruction(Z80OpCodes.RR, "RR B", 0x18CB, "18CB", 2, 8, 8, 0, 0));
        map.put(0x19CB, new Z80Instruction(Z80OpCodes.RR, "RR C", 0x19CB, "19CB", 2, 8, 8, 0, 0));
        map.put(0x1ACB, new Z80Instruction(Z80OpCodes.RR, "RR D", 0x1ACB, "1ACB", 2, 8, 8, 0, 0));
        map.put(0x1BCB, new Z80Instruction(Z80OpCodes.RR, "RR E", 0x1BCB, "1BCB", 2, 8, 8, 0, 0));
        map.put(0x1CCB, new Z80Instruction(Z80OpCodes.RR, "RR H", 0x1CCB, "1CCB", 2, 8, 8, 0, 0));
        map.put(0x1DCB, new Z80Instruction(Z80OpCodes.RR, "RR L", 0x1DCB, "1DCB", 2, 8, 8, 0, 0));

        map.put(0x1F, new Z80Instruction(Z80OpCodes.RRA, "RRA", 0x1F, "1F", 1, 4, 4, 0, 0));

        map.put(0x0ECB, new Z80Instruction(Z80OpCodes.RRC, "RRC (HL)", 0x0ECB, "0ECB", 2, 15, 15, 0, 0));
        map.put(0xCBDD, new Z80Instruction(Z80OpCodes.RRC, "RRC (IX*)", 0xCBDD, "CBDD(*1)", 4, 23, 23, 0, 0));
        map.put(0xCBFD, new Z80Instruction(Z80OpCodes.RRC, "RRC (IY*)", 0xCBFD, "CBFD(*1)", 4, 23, 23, 0, 0));
        map.put(0x0FCB, new Z80Instruction(Z80OpCodes.RRC, "RRC A", 0x0FCB, "0FCB", 2, 8, 8, 0, 0));
        map.put(0x08CB, new Z80Instruction(Z80OpCodes.RRC, "RRC B", 0x08CB, "08CB", 2, 8, 8, 0, 0));
        map.put(0x09CB, new Z80Instruction(Z80OpCodes.RRC, "RRC C", 0x09CB, "09CB", 2, 8, 8, 0, 0));
        map.put(0x0ACB, new Z80Instruction(Z80OpCodes.RRC, "RRC D", 0x0ACB, "0ACB", 2, 8, 8, 0, 0));
        map.put(0x0BCB, new Z80Instruction(Z80OpCodes.RRC, "RRC E", 0x0BCB, "0BCB", 2, 8, 8, 0, 0));
        map.put(0x0CCB, new Z80Instruction(Z80OpCodes.RRC, "RRC H", 0x0CCB, "0CCB", 2, 8, 8, 0, 0));
        map.put(0x0DCB, new Z80Instruction(Z80OpCodes.RRC, "RRC L", 0x0DCB, "0DCB", 2, 8, 8, 0, 0));

        map.put(0x0F, new Z80Instruction(Z80OpCodes.RRCA, "RRCA", 0x0F, "0F", 1, 4, 4, 0, 0));

        map.put(0x67ED, new Z80Instruction(Z80OpCodes.RRD, "RRD", 0x67ED, "67ED", 2, 18, 18, 0, 0));

        map.put(0xC7, new Z80Instruction(Z80OpCodes.RST, "RST 00H", 0xC7, "C7", 1, 11, 11, 0, 0));
        map.put(0xCF, new Z80Instruction(Z80OpCodes.RST, "RST 08H", 0xCF, "CF", 1, 11, 11, 0, 0));
        map.put(0xD7, new Z80Instruction(Z80OpCodes.RST, "RST 10H", 0xD7, "D7", 1, 11, 11, 0, 0));
        map.put(0xDF, new Z80Instruction(Z80OpCodes.RST, "RST 18H", 0xDF, "DF", 1, 11, 11, 0, 0));
        map.put(0xE7, new Z80Instruction(Z80OpCodes.RST, "RST 20H", 0xE7, "E7", 1, 11, 11, 0, 0));
        map.put(0xEF, new Z80Instruction(Z80OpCodes.RST, "RST 28H", 0xEF, "EF", 1, 11, 11, 0, 0));
        map.put(0xF7, new Z80Instruction(Z80OpCodes.RST, "RST 30H", 0xF7, "F7", 1, 11, 11, 0, 0));
        map.put(0xFF, new Z80Instruction(Z80OpCodes.RST, "RST 38H", 0xFF, "FF", 1, 11, 11, 0, 0));

        map.put(0x9E, new Z80Instruction(Z80OpCodes.SBC, "SBC A, (HL)", 0x9E, "9E", 1, 7, 7, 0, 0));
        map.put(0x9EDD, new Z80Instruction(Z80OpCodes.SBC, "SBC A, (IX*)", 0x9EDD, "9EDD(*1)", 3, 19, 19, 0, 0));
        map.put(0x9EFD, new Z80Instruction(Z80OpCodes.SBC, "SBC A, (IY*)", 0x9EFD, "9EFD(*1)", 3, 19, 19, 0, 0));
        map.put(0x9F, new Z80Instruction(Z80OpCodes.SBC, "SBC A, A", 0x9F, "9F", 1, 4, 4, 0, 0));
        map.put(0x98, new Z80Instruction(Z80OpCodes.SBC, "SBC A, B", 0x98, "98", 1, 4, 4, 0, 0));
        map.put(0x99, new Z80Instruction(Z80OpCodes.SBC, "SBC A, C", 0x99, "99", 1, 4, 4, 0, 0));
        map.put(0x9A, new Z80Instruction(Z80OpCodes.SBC, "SBC A, D", 0x9A, "9A", 1, 4, 4, 0, 0));
        map.put(0x9B, new Z80Instruction(Z80OpCodes.SBC, "SBC A, E", 0x9B, "9B", 1, 4, 4, 0, 0));
        map.put(0x9C, new Z80Instruction(Z80OpCodes.SBC, "SBC A, H", 0x9C, "9C", 1, 4, 4, 0, 0));
        map.put(0x9D, new Z80Instruction(Z80OpCodes.SBC, "SBC A, L", 0x9D, "9D", 1, 4, 4, 0, 0));
        map.put(0x9CDD, new Z80Instruction(Z80OpCodes.SBC, "SBC IXH", 0x9CDD, "9CDD", 2, 0, 0, 0, 0));
        map.put(0x9CFD, new Z80Instruction(Z80OpCodes.SBC, "SBC IYH", 0x9CFD, "9CFD", 2, 0, 0, 0, 0));
        map.put(0x9DDD, new Z80Instruction(Z80OpCodes.SBC, "SBC IXL", 0x9DDD, "9DDD", 2, 0, 0, 0, 0));
        map.put(0x9DFD, new Z80Instruction(Z80OpCodes.SBC, "SBC IYL", 0x9DFD, "9DFD", 2, 0, 0, 0, 0));
        map.put(0xDE, new Z80Instruction(Z80OpCodes.SBC, "SBC A, *", 0xDE, "DE(*1)", 2, 7, 7, 0, 0));

        map.put(0x42ED, new Z80Instruction(Z80OpCodes.SBC, "SBC HL, BC", 0x42ED, "42ED", 2, 15, 15, 0, 0));
        map.put(0x52ED, new Z80Instruction(Z80OpCodes.SBC, "SBC HL, DE", 0x52ED, "52ED", 2, 15, 15, 0, 0));
        map.put(0x62ED, new Z80Instruction(Z80OpCodes.SBC, "SBC HL, HL", 0x62ED, "62ED", 2, 15, 15, 0, 0));
        map.put(0x72ED, new Z80Instruction(Z80OpCodes.SBC, "SBC HL, SP", 0x72ED, "72ED", 2, 15, 15, 0, 0));

        map.put(0x37, new Z80Instruction(Z80OpCodes.SCF, "SCF", 0x37, "37", 1, 4, 4, 0, 0));

        map.put(0xC6CB, new Z80Instruction(Z80OpCodes.SET, "SET *, (HL)", 0xC6CB, "C6CB", 2, 15, 15, 0, 0));
        map.put(0xCBDDC600, new Z80Instruction(Z80OpCodes.SET, "SET *, (IX*)", 0xCBDDC600, "CBDD C600", 4, 23, 23, 0, 0));
        map.put(0xCBFDC600, new Z80Instruction(Z80OpCodes.SET, "SET *, (IY*)", 0xCBFDC600, "CBFD C600", 4, 23, 23, 0, 0));
        map.put(0xC7CB, new Z80Instruction(Z80OpCodes.SET, "SET *, A", 0xC7CB, "C7CB", 2, 8, 8, 0, 0));
        map.put(0xC0CB, new Z80Instruction(Z80OpCodes.SET, "SET *, B", 0xC0CB, "C0CB", 2, 8, 8, 0, 0));
        map.put(0xC1CB, new Z80Instruction(Z80OpCodes.SET, "SET *, C", 0xC1CB, "C1CB", 2, 8, 8, 0, 0));
        map.put(0xC2CB, new Z80Instruction(Z80OpCodes.SET, "SET *, D", 0xC2CB, "C2CB", 2, 8, 8, 0, 0));
        map.put(0xC3CB, new Z80Instruction(Z80OpCodes.SET, "SET *, E", 0xC3CB, "C3CB", 2, 8, 8, 0, 0));
        map.put(0xC4CB, new Z80Instruction(Z80OpCodes.SET, "SET *, H", 0xC4CB, "C4CB", 2, 8, 8, 0, 0));
        map.put(0xC5CB, new Z80Instruction(Z80OpCodes.SET, "SET *, L", 0xC5CB, "C5CB", 2, 8, 8, 0, 0));

        map.put(0x26CB, new Z80Instruction(Z80OpCodes.SLA, "SLA (HL)", 0x26CB, "26CB", 2, 15, 15, 0, 0));
        map.put(0xCBDD, new Z80Instruction(Z80OpCodes.SLA, "SLA (IX*)", 0xCBDD, "CBDD(*1)", 4, 23, 23, 0, 0));
        map.put(0xCBFD, new Z80Instruction(Z80OpCodes.SLA, "SLA (IY*)", 0xCBFD, "CBFD(*1)", 4, 23, 23, 0, 0));
        map.put(0x27CB, new Z80Instruction(Z80OpCodes.SLA, "SLA A", 0x27CB, "27CB", 2, 8, 8, 0, 0));
        map.put(0x20CB, new Z80Instruction(Z80OpCodes.SLA, "SLA B", 0x20CB, "20CB", 2, 8, 8, 0, 0));
        map.put(0x21CB, new Z80Instruction(Z80OpCodes.SLA, "SLA C", 0x21CB, "21CB", 2, 8, 8, 0, 0));
        map.put(0x22CB, new Z80Instruction(Z80OpCodes.SLA, "SLA D", 0x22CB, "22CB", 2, 8, 8, 0, 0));
        map.put(0x23CB, new Z80Instruction(Z80OpCodes.SLA, "SLA E", 0x23CB, "23CB", 2, 8, 8, 0, 0));
        map.put(0x24CB, new Z80Instruction(Z80OpCodes.SLA, "SLA H", 0x24CB, "24CB", 2, 8, 8, 0, 0));
        map.put(0x25CB, new Z80Instruction(Z80OpCodes.SLA, "SLA L", 0x25CB, "25CB", 2, 8, 8, 0, 0));

        map.put(0x2ECB, new Z80Instruction(Z80OpCodes.SRA, "SRA (HL)", 0x2ECB, "2ECB", 2, 15, 15, 0, 0));
        map.put(0xCBDD, new Z80Instruction(Z80OpCodes.SRA, "SRA (IX*)", 0xCBDD, "CBDD(*1)", 4, 23, 23, 0, 0));
        map.put(0xCBFD, new Z80Instruction(Z80OpCodes.SRA, "SRA (IY*)", 0xCBFD, "CBFD(*1)", 4, 23, 23, 0, 0));
        map.put(0x2FCB, new Z80Instruction(Z80OpCodes.SRA, "SRA A", 0x2FCB, "2FCB", 2, 8, 8, 0, 0));
        map.put(0x28CB, new Z80Instruction(Z80OpCodes.SRA, "SRA B", 0x28CB, "28CB", 2, 8, 8, 0, 0));
        map.put(0x29CB, new Z80Instruction(Z80OpCodes.SRA, "SRA C", 0x29CB, "29CB", 2, 8, 8, 0, 0));
        map.put(0x2ACB, new Z80Instruction(Z80OpCodes.SRA, "SRA D", 0x2ACB, "2ACB", 2, 8, 8, 0, 0));
        map.put(0x2BCB, new Z80Instruction(Z80OpCodes.SRA, "SRA E", 0x2BCB, "2BCB", 2, 8, 8, 0, 0));
        map.put(0x2CCB, new Z80Instruction(Z80OpCodes.SRA, "SRA H", 0x2CCB, "2CCB", 2, 8, 8, 0, 0));
        map.put(0x2DCB, new Z80Instruction(Z80OpCodes.SRA, "SRA L", 0x2DCB, "2DCB", 2, 8, 8, 0, 0));

        map.put(0x3ECB, new Z80Instruction(Z80OpCodes.SRL, "SRL (HL)", 0x3ECB, "3ECB", 2, 15, 15, 0, 0));
        map.put(0xCBDD, new Z80Instruction(Z80OpCodes.SRL, "SRL (IX*)", 0xCBDD, "CBDD(*1)", 4, 23, 23, 0, 0));
        map.put(0xCBFD, new Z80Instruction(Z80OpCodes.SRL, "SRL (IY*)", 0xCBFD, "CBFD(*1)", 4, 23, 23, 0, 0));
        map.put(0x3FCB, new Z80Instruction(Z80OpCodes.SRL, "SRL A", 0x3FCB, "3FCB", 2, 8, 8, 0, 0));
        map.put(0x38CB, new Z80Instruction(Z80OpCodes.SRL, "SRL B", 0x38CB, "38CB", 2, 8, 8, 0, 0));
        map.put(0x39CB, new Z80Instruction(Z80OpCodes.SRL, "SRL C", 0x39CB, "39CB", 2, 8, 8, 0, 0));
        map.put(0x3ACB, new Z80Instruction(Z80OpCodes.SRL, "SRL D", 0x3ACB, "3ACB", 2, 8, 8, 0, 0));
        map.put(0x3BCB, new Z80Instruction(Z80OpCodes.SRL, "SRL E", 0x3BCB, "3BCB", 2, 8, 8, 0, 0));
        map.put(0x3CCB, new Z80Instruction(Z80OpCodes.SRL, "SRL H", 0x3CCB, "3CCB", 2, 8, 8, 0, 0));
        map.put(0x3DCB, new Z80Instruction(Z80OpCodes.SRL, "SRL L", 0x3DCB, "3DCB", 2, 8, 8, 0, 0));

        map.put(0x96, new Z80Instruction(Z80OpCodes.SUB, "SUB (HL)", 0x96, "96", 1, 7, 8, 0, 0));
        map.put(0x96DD, new Z80Instruction(Z80OpCodes.SUB, "SUB (IX*)", 0x96DD, "96DD(*1)", 3, 19, 19, 0, 0));
        map.put(0x96FD, new Z80Instruction(Z80OpCodes.SUB, "SUB (IY*)", 0x96FD, "96FD(*1)", 3, 19, 19, 0, 0));
        map.put(0x97, new Z80Instruction(Z80OpCodes.SUB, "SUB A", 0x97, "97", 1, 4, 4, 0, 0));
        map.put(0x90, new Z80Instruction(Z80OpCodes.SUB, "SUB B", 0x90, "90", 1, 4, 4, 0, 0));
        map.put(0x91, new Z80Instruction(Z80OpCodes.SUB, "SUB C", 0x91, "91", 1, 4, 4, 0, 0));
        map.put(0x92, new Z80Instruction(Z80OpCodes.SUB, "SUB D", 0x92, "92", 1, 4, 4, 0, 0));
        map.put(0x93, new Z80Instruction(Z80OpCodes.SUB, "SUB E", 0x93, "93", 1, 4, 4, 0, 0));
        map.put(0x94, new Z80Instruction(Z80OpCodes.SUB, "SUB H", 0x94, "94", 1, 4, 4, 0, 0));
        map.put(0x95, new Z80Instruction(Z80OpCodes.SUB, "SUB L", 0x95, "95", 1, 4, 4, 0, 0));
        map.put(0x94DD, new Z80Instruction(Z80OpCodes.SUB, "SUB IXH", 0x94DD, "94DD", 2, 0, 0, 0, 0));
        map.put(0x94FD, new Z80Instruction(Z80OpCodes.SUB, "SUB IYH", 0x94FD, "94FD", 2, 0, 0, 0, 0));
        map.put(0x95DD, new Z80Instruction(Z80OpCodes.SUB, "SUB IXL", 0x95DD, "95DD", 2, 0, 0, 0, 0));
        map.put(0x95FD, new Z80Instruction(Z80OpCodes.SUB, "SUB IYL", 0x95FD, "95FD", 2, 0, 0, 0, 0));
        map.put(0xD6, new Z80Instruction(Z80OpCodes.SUB, "SUB *", 0xD6, "D6(*1)", 2, 7, 8, 0, 0));

        map.put(0xAE, new Z80Instruction(Z80OpCodes.XOR, "XOR (HL)", 0xAE, "AE", 1, 7, 8, 0, 0));
        map.put(0xAEDD, new Z80Instruction(Z80OpCodes.XOR, "XOR (IX*)", 0xAEDD, "AEDD(*1)", 3, 19, 19, 0, 0));
        map.put(0xAEFD, new Z80Instruction(Z80OpCodes.XOR, "XOR (IY*)", 0xAEFD, "AEFD(*1)", 3, 19, 19, 0, 0));
        map.put(0xAF, new Z80Instruction(Z80OpCodes.XOR, "XOR A", 0xAF, "AF", 1, 4, 4, 0, 0));
        map.put(0xA8, new Z80Instruction(Z80OpCodes.XOR, "XOR B", 0xA8, "A8", 1, 4, 4, 0, 0));
        map.put(0xA9, new Z80Instruction(Z80OpCodes.XOR, "XOR C", 0xA9, "A9", 1, 4, 4, 0, 0));
        map.put(0xAA, new Z80Instruction(Z80OpCodes.XOR, "XOR D", 0xAA, "AA", 1, 4, 4, 0, 0));
        map.put(0xAB, new Z80Instruction(Z80OpCodes.XOR, "XOR E", 0xAB, "AB", 1, 4, 4, 0, 0));
        map.put(0xAC, new Z80Instruction(Z80OpCodes.XOR, "XOR H", 0xAC, "AC", 1, 4, 4, 0, 0));
        map.put(0xAD, new Z80Instruction(Z80OpCodes.XOR, "XOR L", 0xAD, "AD", 1, 4, 4, 0, 0));
        map.put(0xACDD, new Z80Instruction(Z80OpCodes.XOR, "XOR IXH", 0xACDD, "ACDD", 2, 0, 0, 0, 0));
        map.put(0xACFD, new Z80Instruction(Z80OpCodes.XOR, "XOR IYH", 0xACFD, "ACFD", 2, 0, 0, 0, 0));
        map.put(0xADDD, new Z80Instruction(Z80OpCodes.XOR, "XOR IXL", 0xADDD, "ADDD", 2, 0, 0, 0, 0));
        map.put(0xADFD, new Z80Instruction(Z80OpCodes.XOR, "XOR IYL", 0xADFD, "ADFD", 2, 0, 0, 0, 0));
        map.put(0xEE, new Z80Instruction(Z80OpCodes.XOR, "XOR *", 0xEE, "EE(*1)", 2, 7, 8, 0, 0));

    }
    
    public static Z80Instruction getInstruction(int hexCode) {
        return map.get(hexCode);
    }
    
    public Z80Instruction(Z80OpCodes opCode, String description, int hexCode, String hexString, int size, int oClock, int rClock, int oClockUnmet,
            int rClockUnmet) {
        super();
        this.opCode = opCode;
        this.description = description;
        this.hexCode = hexCode;
        this.hexString = hexString;
        this.size = size;
        this.oClock = oClock;
        this.rClock = rClock;
        this.oClockUnmet = oClockUnmet;
        this.rClockUnmet = rClockUnmet;
    }


    public Z80OpCodes getOpCode() {
        return opCode;
    }


    public String getDescription() {
        return description;
    }


    public int getHexCode() {
        return hexCode;
    }


    public String getHexString() {
        return hexString;
    }


    public int getSize() {
        return size;
    }


    public int getoClock() {
        return oClock;
    }


    public int getrClock() {
        return rClock;
    }


    public int getoClockUnmet() {
        return oClockUnmet;
    }


    public int getrClockUnmet() {
        return rClockUnmet;
    }
    
    
}
