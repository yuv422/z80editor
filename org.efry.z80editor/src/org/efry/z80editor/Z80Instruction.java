package org.efry.z80editor;

import java.util.HashMap;
import java.util.Iterator;

public class Z80Instruction {

    private Z80OpCodes opCode;
    private String description;
    private int hexCode;
    private String hexString;
    private int size;
    private int oClock;
    private int oClockUnmet;
    
    private static final HashMap<Integer, Z80Instruction> map = new HashMap<Integer, Z80Instruction>();
    
    public static Z80Instruction dummyInstruction = new Z80Instruction(Z80OpCodes.NOP, "dummy instruction", 0, "", 0, 0, 0);
    
    static {
        
        map.put(0x8E, new Z80Instruction(Z80OpCodes.ADC, "ADC A, (HL)", 0x8E, "8E", 1, 7, 0));
        map.put(0x8EDD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, (IX+nn)", 0x8EDD, "DD 8E  nn", 3, 19, 0));
        map.put(0x8EFD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, (IY+nn)", 0x8EFD, "FD 8E  nn", 3, 19, 0));
        map.put(0x8F, new Z80Instruction(Z80OpCodes.ADC, "ADC A, A", 0x8F, "8F", 1, 4, 0));
        map.put(0x88, new Z80Instruction(Z80OpCodes.ADC, "ADC A, B", 0x88, "88", 1, 4, 0));
        map.put(0x89, new Z80Instruction(Z80OpCodes.ADC, "ADC A, C", 0x89, "89", 1, 4, 0));
        map.put(0x8A, new Z80Instruction(Z80OpCodes.ADC, "ADC A, D", 0x8A, "8A", 1, 4, 0));
        map.put(0x8B, new Z80Instruction(Z80OpCodes.ADC, "ADC A, E", 0x8B, "8B", 1, 4, 0));
        map.put(0x8C, new Z80Instruction(Z80OpCodes.ADC, "ADC A, H", 0x8C, "8C", 1, 4, 0));
        map.put(0x8D, new Z80Instruction(Z80OpCodes.ADC, "ADC A, L", 0x8D, "8D", 1, 4, 0));
        map.put(0x8CDD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, IXH", 0x8CDD, "DD 8C", 2, 0, 0));
        map.put(0x8CFD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, IYH", 0x8CFD, "FD 8C", 2, 0, 0));
        map.put(0x8DDD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, IXL", 0x8DDD, "DD 8D", 2, 0, 0));
        map.put(0x8DFD, new Z80Instruction(Z80OpCodes.ADC, "ADC A, IYL", 0x8DFD, "FD 8D", 2, 0, 0));
        map.put(0xCE, new Z80Instruction(Z80OpCodes.ADC, "ADC A, nn", 0xCE, "CE nn", 2, 7, 0));

        map.put(0x4AED, new Z80Instruction(Z80OpCodes.ADC, "ADC HL, BC", 0x4AED, "ED 4A", 2, 15, 0));
        map.put(0x5AED, new Z80Instruction(Z80OpCodes.ADC, "ADC HL, DE", 0x5AED, "ED 5A", 2, 15, 0));
        map.put(0x6AED, new Z80Instruction(Z80OpCodes.ADC, "ADC HL, HL", 0x6AED, "ED 6A", 2, 15, 0));
        map.put(0x7AED, new Z80Instruction(Z80OpCodes.ADC, "ADC HL, SP", 0x7AED, "ED 7A", 2, 15, 0));

        map.put(0x86, new Z80Instruction(Z80OpCodes.ADD, "ADD A, (HL)", 0x86, "86", 1, 7, 0));
        map.put(0x86DD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, (IX+nn)", 0x86DD, "DD 86 nn", 3, 19, 0));
        map.put(0x86FD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, (IY+nn)", 0x86FD, "FD 86 nn", 3, 19, 0));
        map.put(0x87, new Z80Instruction(Z80OpCodes.ADD, "ADD A, A", 0x87, "87", 1, 4, 0));
        map.put(0x80, new Z80Instruction(Z80OpCodes.ADD, "ADD A, B", 0x80, "80", 1, 4, 0));
        map.put(0x81, new Z80Instruction(Z80OpCodes.ADD, "ADD A, C", 0x81, "81", 1, 4, 0));
        map.put(0x82, new Z80Instruction(Z80OpCodes.ADD, "ADD A, D", 0x82, "82", 1, 4, 0));
        map.put(0x83, new Z80Instruction(Z80OpCodes.ADD, "ADD A, E", 0x83, "83", 1, 4, 0));
        map.put(0x84, new Z80Instruction(Z80OpCodes.ADD, "ADD A, H", 0x84, "84", 1, 4, 0));
        map.put(0x85, new Z80Instruction(Z80OpCodes.ADD, "ADD A, L", 0x85, "85", 1, 4, 0));
        map.put(0x84DD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, IXH", 0x84DD, "DD 84", 2, 0, 0));
        map.put(0x84FD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, IYH", 0x84FD, "FD 84", 2, 0, 0));
        map.put(0x85DD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, IXL", 0x85DD, "DD 85", 2, 0, 0));
        map.put(0x85FD, new Z80Instruction(Z80OpCodes.ADD, "ADD A, IYL", 0x85FD, "FD 85", 2, 0, 0));
        map.put(0xC6, new Z80Instruction(Z80OpCodes.ADD, "ADD A, nn", 0xC6, "C6 nn", 2, 7, 0));

        map.put(0x9, new Z80Instruction(Z80OpCodes.ADD, "ADD HL, BC", 0x9, "09", 1, 11, 0));
        map.put(0x19, new Z80Instruction(Z80OpCodes.ADD, "ADD HL, DE", 0x19, "19", 1, 11, 0));
        map.put(0x29, new Z80Instruction(Z80OpCodes.ADD, "ADD HL, HL", 0x29, "29", 1, 11, 0));
        map.put(0x39, new Z80Instruction(Z80OpCodes.ADD, "ADD HL, SP", 0x39, "39", 1, 11, 0));

        map.put(0x09DD, new Z80Instruction(Z80OpCodes.ADD, "ADD IX, BC", 0x09DD, "DD 09", 2, 15, 0));
        map.put(0x19DD, new Z80Instruction(Z80OpCodes.ADD, "ADD IX, DE", 0x19DD, "DD 19", 2, 15, 0));
        map.put(0x29DD, new Z80Instruction(Z80OpCodes.ADD, "ADD IX, IX", 0x29DD, "DD 29", 2, 15, 0));
        map.put(0x39DD, new Z80Instruction(Z80OpCodes.ADD, "ADD IX, SP", 0x39DD, "DD 39", 2, 15, 0));
        map.put(0x09FD, new Z80Instruction(Z80OpCodes.ADD, "ADD IY, BC", 0x09FD, "FD 09", 2, 15, 0));
        map.put(0x19FD, new Z80Instruction(Z80OpCodes.ADD, "ADD IY, DE", 0x19FD, "FD 19", 2, 15, 0));
        map.put(0x29FD, new Z80Instruction(Z80OpCodes.ADD, "ADD IY, IY", 0x29FD, "FD 29", 2, 15, 0));
        map.put(0x39FD, new Z80Instruction(Z80OpCodes.ADD, "ADD IY, SP", 0x39FD, "FD 39", 2, 15, 0));

        map.put(0xA6, new Z80Instruction(Z80OpCodes.AND, "AND (HL)", 0xA6, "A6", 1, 7, 0));
        map.put(0xA6DD, new Z80Instruction(Z80OpCodes.AND, "AND (IX+nn)", 0xA6DD, "DD A6 nn", 3, 19, 0));
        map.put(0xA6FD, new Z80Instruction(Z80OpCodes.AND, "AND (IY+nn)", 0xA6FD, "FD A6 nn", 3, 19, 0));
        map.put(0xA7, new Z80Instruction(Z80OpCodes.AND, "AND A", 0xA7, "A7", 1, 4, 0));
        map.put(0xA0, new Z80Instruction(Z80OpCodes.AND, "AND B", 0xA0, "A0", 1, 4, 0));
        map.put(0xA1, new Z80Instruction(Z80OpCodes.AND, "AND C", 0xA1, "A1", 1, 4, 0));
        map.put(0xA2, new Z80Instruction(Z80OpCodes.AND, "AND D", 0xA2, "A2", 1, 4, 0));
        map.put(0xA3, new Z80Instruction(Z80OpCodes.AND, "AND E", 0xA3, "A3", 1, 4, 0));
        map.put(0xA4, new Z80Instruction(Z80OpCodes.AND, "AND H", 0xA4, "A4", 1, 4, 0));
        map.put(0xA5, new Z80Instruction(Z80OpCodes.AND, "AND L", 0xA5, "A5", 1, 4, 0));
        map.put(0xA4DD, new Z80Instruction(Z80OpCodes.AND, "AND IXH", 0xA4DD, "DD A4", 2, 0, 0));
        map.put(0xA4FD, new Z80Instruction(Z80OpCodes.AND, "AND IYH", 0xA4FD, "FD A4", 2, 0, 0));
        map.put(0xA5DD, new Z80Instruction(Z80OpCodes.AND, "AND IXL", 0xA5DD, "DD A5", 2, 0, 0));
        map.put(0xA5FD, new Z80Instruction(Z80OpCodes.AND, "AND IYL", 0xA5FD, "FD A5", 2, 0, 0));
        map.put(0xE6, new Z80Instruction(Z80OpCodes.AND, "AND nn", 0xE6, "E6 nn", 2, 7, 0));

        map.put(0x46CB, new Z80Instruction(Z80OpCodes.BIT, "BIT n, (HL)", 0x46CB, "CB 46+8*b", 2, 12, 0)); //FIXME break this out into its individual instructions
        map.put(0xCBDD4600, new Z80Instruction(Z80OpCodes.BIT, "BIT n, (IX+nn)", 0xCBDD4600, "DD CB nn 46", 4, 20, 0)); //FIXME break this out into its individual instructions
        map.put(0xCBFD4600, new Z80Instruction(Z80OpCodes.BIT, "BIT n, (IY+nn)", 0xCBFD4600, "FD CB nn 46", 4, 20, 0)); //FIXME break this out into its individual instructions
        map.put(0x47CB, new Z80Instruction(Z80OpCodes.BIT, "BIT n, A", 0x47CB, "CB 47+8*n", 2, 8, 0));
        map.put(0x40CB, new Z80Instruction(Z80OpCodes.BIT, "BIT n, B", 0x40CB, "CB 40+8*n", 2, 8, 0));
        map.put(0x41CB, new Z80Instruction(Z80OpCodes.BIT, "BIT n, C", 0x41CB, "CB 41+8*n", 2, 8, 0));
        map.put(0x42CB, new Z80Instruction(Z80OpCodes.BIT, "BIT n, D", 0x42CB, "CB 42+8*n", 2, 8, 0));
        map.put(0x43CB, new Z80Instruction(Z80OpCodes.BIT, "BIT n, E", 0x43CB, "CB 43+8*n", 2, 8, 0));
        map.put(0x44CB, new Z80Instruction(Z80OpCodes.BIT, "BIT n, H", 0x44CB, "CB 44+8*n", 2, 8, 0));
        map.put(0x45CB, new Z80Instruction(Z80OpCodes.BIT, "BIT n, L", 0x45CB, "CB 45+8*n", 2, 8, 0));

        map.put(0xDC, new Z80Instruction(Z80OpCodes.CALL, "CALL C, nnnn", 0xDC, "DC nn nn", 3, 17, 10));
        map.put(0xFC, new Z80Instruction(Z80OpCodes.CALL, "CALL M, nnnn", 0xFC, "FC nn nn", 3, 17, 10));
        map.put(0xD4, new Z80Instruction(Z80OpCodes.CALL, "CALL NC, nnnn", 0xD4, "D4 nn nn", 3, 17, 10));
        map.put(0xC4, new Z80Instruction(Z80OpCodes.CALL, "CALL NZ, nnnn", 0xC4, "C4 nn nn", 3, 17, 10));
        map.put(0xF4, new Z80Instruction(Z80OpCodes.CALL, "CALL P, nnnn", 0xF4, "F4 nn nn", 3, 17, 10));
        map.put(0xEC, new Z80Instruction(Z80OpCodes.CALL, "CALL PE, nnnn", 0xEC, "EC nn nn", 3, 17, 10));
        map.put(0xE4, new Z80Instruction(Z80OpCodes.CALL, "CALL PO, nnnn", 0xE4, "E4 nn nn", 3, 17, 10));
        map.put(0xCC, new Z80Instruction(Z80OpCodes.CALL, "CALL Z, nnnn", 0xCC, "CC nn nn", 3, 17, 10));
        map.put(0xCD, new Z80Instruction(Z80OpCodes.CALL, "CALL nnnn", 0xCD, "CD nn nn", 3, 17, 0));

        map.put(0x3F, new Z80Instruction(Z80OpCodes.CCF, "CCF", 0x3F, "3F", 1, 4, 0));

        map.put(0xBE, new Z80Instruction(Z80OpCodes.CP, "CP (HL)", 0xBE, "BE", 1, 7, 0));
        map.put(0xBEDD, new Z80Instruction(Z80OpCodes.CP, "CP (IX+nn)", 0xBEDD, "DD BE nn", 3, 19, 0));
        map.put(0xBEFD, new Z80Instruction(Z80OpCodes.CP, "CP (IY+nn)", 0xBEFD, "FD BE nn", 3, 19, 0));
        map.put(0xBF, new Z80Instruction(Z80OpCodes.CP, "CP A", 0xBF, "BF", 1, 4, 0));
        map.put(0xB8, new Z80Instruction(Z80OpCodes.CP, "CP B", 0xB8, "B8", 1, 4, 0));
        map.put(0xB9, new Z80Instruction(Z80OpCodes.CP, "CP C", 0xB9, "B9", 1, 4, 0));
        map.put(0xBA, new Z80Instruction(Z80OpCodes.CP, "CP D", 0xBA, "BA", 1, 4, 0));
        map.put(0xBB, new Z80Instruction(Z80OpCodes.CP, "CP E", 0xBB, "BB", 1, 4, 0));
        map.put(0xBC, new Z80Instruction(Z80OpCodes.CP, "CP H", 0xBC, "BC", 1, 4, 0));
        map.put(0xBD, new Z80Instruction(Z80OpCodes.CP, "CP L", 0xBD, "BD", 1, 4, 0));
        map.put(0xBCDD, new Z80Instruction(Z80OpCodes.CP, "CP IXH", 0xBCDD, "DD BC", 2, 0, 0));
        map.put(0xBCFD, new Z80Instruction(Z80OpCodes.CP, "CP IYH", 0xBCFD, "FD BC", 2, 0, 0));
        map.put(0xBDDD, new Z80Instruction(Z80OpCodes.CP, "CP IXL", 0xBDDD, "DD BD", 2, 0, 0));
        map.put(0xBDFD, new Z80Instruction(Z80OpCodes.CP, "CP IYL", 0xBDFD, "FD BD", 2, 0, 0));
        map.put(0xFE, new Z80Instruction(Z80OpCodes.CP, "CP nn", 0xFE, "FE nn", 2, 7, 0));

        map.put(0xA9ED, new Z80Instruction(Z80OpCodes.CPD, "CPD", 0xA9ED, "ED A9", 2, 16, 0));
        map.put(0xB9ED, new Z80Instruction(Z80OpCodes.CPDR, "CPDR", 0xB9ED, "ED B9", 2, 21, 16));

        map.put(0xB1ED, new Z80Instruction(Z80OpCodes.CPIR, "CPIR", 0xB1ED, "ED B1", 2, 21, 16));
        map.put(0xA1ED, new Z80Instruction(Z80OpCodes.CPI, "CPI", 0xA1ED, "ED A1", 2, 16, 0));

        map.put(0x2F, new Z80Instruction(Z80OpCodes.CPL, "CPL", 0x2F, "2F", 1, 4, 0));

        map.put(0x27, new Z80Instruction(Z80OpCodes.DAA, "DAA", 0x27, "27", 1, 4, 0));

        map.put(0x35, new Z80Instruction(Z80OpCodes.DEC, "DEC (HL)", 0x35, "35", 1, 11, 0));
        map.put(0x35DD, new Z80Instruction(Z80OpCodes.DEC, "DEC (IX+nn)", 0x35DD, "DD 35 nn", 3, 23, 0));
        map.put(0x35FD, new Z80Instruction(Z80OpCodes.DEC, "DEC (IY+nn)", 0x35FD, "FD 35 nn", 3, 23, 0));
        map.put(0x3D, new Z80Instruction(Z80OpCodes.DEC, "DEC A", 0x3D, "3D", 1, 4, 0));
        map.put(0x5, new Z80Instruction(Z80OpCodes.DEC, "DEC B", 0x5, "05", 1, 4, 0));
        map.put(0x0D, new Z80Instruction(Z80OpCodes.DEC, "DEC C", 0x0D, "0D", 1, 4, 0));
        map.put(0x15, new Z80Instruction(Z80OpCodes.DEC, "DEC D", 0x15, "15", 1, 4, 0));
        map.put(0x1D, new Z80Instruction(Z80OpCodes.DEC, "DEC E", 0x1D, "1D", 1, 4, 0));
        map.put(0x25, new Z80Instruction(Z80OpCodes.DEC, "DEC H", 0x25, "25", 1, 4, 0));
        map.put(0x2D, new Z80Instruction(Z80OpCodes.DEC, "DEC L", 0x2D, "2D", 1, 4, 0));
        map.put(0x25DD, new Z80Instruction(Z80OpCodes.DEC, "DEC IXH", 0x25DD, "DD 25", 2, 0, 0));
        map.put(0x25FD, new Z80Instruction(Z80OpCodes.DEC, "DEC IYH", 0x25FD, "FD 25", 2, 0, 0));
        map.put(0x2DDD, new Z80Instruction(Z80OpCodes.DEC, "DEC IXL", 0x2DDD, "DD 2D", 2, 0, 0));
        map.put(0x2DFD, new Z80Instruction(Z80OpCodes.DEC, "DEC IYL ", 0x2DFD, "FD 2D", 2, 0, 0));

        map.put(0x0B, new Z80Instruction(Z80OpCodes.DEC, "DEC BC", 0x0B, "0B", 1, 6, 0));
        map.put(0x1B, new Z80Instruction(Z80OpCodes.DEC, "DEC DE", 0x1B, "1B", 1, 6, 0));
        map.put(0x2B, new Z80Instruction(Z80OpCodes.DEC, "DEC HL", 0x2B, "2B", 1, 6, 0));
        map.put(0x2BDD, new Z80Instruction(Z80OpCodes.DEC, "DEC IX", 0x2BDD, "DD 2B", 2, 10, 0));
        map.put(0x2BFD, new Z80Instruction(Z80OpCodes.DEC, "DEC IY", 0x2BFD, "FD 2B", 2, 10, 0));
        map.put(0x3B, new Z80Instruction(Z80OpCodes.DEC, "DEC SP", 0x3B, "3B", 1, 6, 0));

        map.put(0xF3, new Z80Instruction(Z80OpCodes.DI, "DI", 0xF3, "F3", 1, 4, 0));

        map.put(0x10, new Z80Instruction(Z80OpCodes.DJNZ, "DJNZ nn", 0x10, "10 nn", 2, 13, 8));

        map.put(0xFB, new Z80Instruction(Z80OpCodes.EI, "EI", 0xFB, "FB", 1, 4, 0));

        map.put(0xE3, new Z80Instruction(Z80OpCodes.EX, "EX (SP), HL", 0xE3, "E3", 1, 19, 0));
        map.put(0xE3DD, new Z80Instruction(Z80OpCodes.EX, "EX (SP), IX", 0xE3DD, "DD E3", 2, 23, 0));
        map.put(0xE3FD, new Z80Instruction(Z80OpCodes.EX, "EX (SP), IY", 0xE3FD, "FD E3", 2, 23, 0));
        map.put(0x8, new Z80Instruction(Z80OpCodes.EX, "EX AF, AF'", 0x8, "8", 1, 4, 0));
        map.put(0xEB, new Z80Instruction(Z80OpCodes.EX, "EX DE, HL", 0xEB, "EB", 1, 4, 0));

        map.put(0xD9, new Z80Instruction(Z80OpCodes.EXX, "EXX", 0xD9, "D9", 1, 4, 0));

        map.put(0x76, new Z80Instruction(Z80OpCodes.HALT, "HALT", 0x76, "76", 1, 4, 0));

        map.put(0x46ED, new Z80Instruction(Z80OpCodes.IM, "IM 0", 0x46ED, "ED 46", 2, 8, 0));
        map.put(0x56ED, new Z80Instruction(Z80OpCodes.IM, "IM 1", 0x56ED, "ED 56", 2, 8, 0));
        map.put(0x5EED, new Z80Instruction(Z80OpCodes.IM, "IM 2", 0x5EED, " ED 5E", 2, 8, 0));

        map.put(0x78ED, new Z80Instruction(Z80OpCodes.IN, "IN A, (C)", 0x78ED, "ED 78", 2, 12, 0));
        map.put(0x40ED, new Z80Instruction(Z80OpCodes.IN, "IN B, (C)", 0x40ED, "ED 40", 2, 12, 0));
        map.put(0x48ED, new Z80Instruction(Z80OpCodes.IN, "IN C, (C)", 0x48ED, "ED 48", 2, 12, 0));
        map.put(0x50ED, new Z80Instruction(Z80OpCodes.IN, "IN D, (c)", 0x50ED, "ED 50", 2, 12, 0));
        map.put(0x58ED, new Z80Instruction(Z80OpCodes.IN, "IN E, (C)", 0x58ED, "ED 58", 2, 12, 0));
        map.put(0x60ED, new Z80Instruction(Z80OpCodes.IN, "IN H, (C)", 0x60ED, "ED 60", 2, 12, 0));
        map.put(0x68ED, new Z80Instruction(Z80OpCodes.IN, "IN L, (C)", 0x68ED, "ED 68", 2, 12, 0));
        map.put(0xDB, new Z80Instruction(Z80OpCodes.IN, "IN A, (nn)", 0xDB, "DB nn", 2, 11, 0));

        map.put(0x34, new Z80Instruction(Z80OpCodes.INC, "INC (HL)", 0x34, "34", 1, 11, 0));
        map.put(0x34DD, new Z80Instruction(Z80OpCodes.INC, "INC (IX+nn)", 0x34DD, "DD 34 nn", 3, 23, 0));
        map.put(0x34FD, new Z80Instruction(Z80OpCodes.INC, "INC (IY+nn)", 0x34FD, "FD 34 nn", 3, 23, 0));
        map.put(0x3C, new Z80Instruction(Z80OpCodes.INC, "INC A", 0x3C, "3C", 1, 4, 0));
        map.put(0x4, new Z80Instruction(Z80OpCodes.INC, "INC B", 0x4, "4", 1, 4, 0));
        map.put(0x0C, new Z80Instruction(Z80OpCodes.INC, "INC C", 0x0C, "0C", 1, 4, 0));
        map.put(0x14, new Z80Instruction(Z80OpCodes.INC, "INC D", 0x14, "14", 1, 4, 0));
        map.put(0x1C, new Z80Instruction(Z80OpCodes.INC, "INC E", 0x1C, "1C", 1, 4, 0));
        map.put(0x24, new Z80Instruction(Z80OpCodes.INC, "INC H", 0x24, "24", 1, 4, 0));
        map.put(0x2C, new Z80Instruction(Z80OpCodes.INC, "INC L", 0x2C, "2C", 1, 4, 0));
        map.put(0x24DD, new Z80Instruction(Z80OpCodes.INC, "INC IXH", 0x24DD, "DD 24", 2, 0, 0));
        map.put(0x24FD, new Z80Instruction(Z80OpCodes.INC, "INC IYH", 0x24FD, "FD 24", 2, 0, 0));
        map.put(0x2CDD, new Z80Instruction(Z80OpCodes.INC, "INC IXL", 0x2CDD, "DD 2C", 2, 0, 0));
        map.put(0x2CFD, new Z80Instruction(Z80OpCodes.INC, "INC IYL", 0x2CFD, "FD 2C", 2, 0, 0));

        map.put(0x3, new Z80Instruction(Z80OpCodes.INC, "INC BC", 0x3, "3", 1, 6, 0));
        map.put(0x13, new Z80Instruction(Z80OpCodes.INC, "INC DE", 0x13, "13", 1, 6, 0));
        map.put(0x23, new Z80Instruction(Z80OpCodes.INC, "INC HL", 0x23, "23", 1, 6, 0));
        map.put(0x23DD, new Z80Instruction(Z80OpCodes.INC, "INC IX", 0x23DD, "DD 23", 2, 10, 0));
        map.put(0x23FD, new Z80Instruction(Z80OpCodes.INC, "INC IY", 0x23FD, "FD 23", 2, 10, 0));
        map.put(0x33, new Z80Instruction(Z80OpCodes.INC, "INC SP", 0x33, "33", 1, 6, 0));

        map.put(0xAAED, new Z80Instruction(Z80OpCodes.IND, "IND", 0xAAED, "ED AA", 2, 16, 0));
        map.put(0xBAED, new Z80Instruction(Z80OpCodes.INDR, "INDR", 0xBAED, "ED BA", 2, 21, 16));

        map.put(0xA2ED, new Z80Instruction(Z80OpCodes.INI, "INI", 0xA2ED, "ED A2", 2, 16, 0));
        map.put(0xB2ED, new Z80Instruction(Z80OpCodes.INIR, "INIR", 0xB2ED, "ED B2", 2, 21, 16));

        map.put(0xE9, new Z80Instruction(Z80OpCodes.JP, "JP (HL)", 0xE9, "E9", 1, 4,0));
        map.put(0xE9DD, new Z80Instruction(Z80OpCodes.JP, "JP (IX)", 0xE9DD, "DD E9", 2, 8, 0));
        map.put(0xE9FD, new Z80Instruction(Z80OpCodes.JP, "JP (IY)", 0xE9FD, "FD E9", 2, 8, 0));
        map.put(0xDA, new Z80Instruction(Z80OpCodes.JP, "JP C, nnnn", 0xDA, "DA nn nn", 3, 10, 10));
        map.put(0xFA, new Z80Instruction(Z80OpCodes.JP, "JP M, nnnn", 0xFA, "FA nn nn", 3, 10, 10));
        map.put(0xD2, new Z80Instruction(Z80OpCodes.JP, "JP NC, nnnn", 0xD2, "D2 nn nn", 3, 10, 10));
        map.put(0xC2, new Z80Instruction(Z80OpCodes.JP, "JP NZ, nnnn", 0xC2, "C2 nn nn", 3, 10, 10));
        map.put(0xF2, new Z80Instruction(Z80OpCodes.JP, "JP P, nnnn", 0xF2, "F2 nn nn", 3, 10, 10));
        map.put(0xEA, new Z80Instruction(Z80OpCodes.JP, "JP PE, nnnn", 0xEA, "EA nn nn", 3, 10, 10));
        map.put(0xE2, new Z80Instruction(Z80OpCodes.JP, "JP PO, nnnn", 0xE2, "E2 nn nn", 3, 10, 10));
        map.put(0xCA, new Z80Instruction(Z80OpCodes.JP, "JP Z, nnnn", 0xCA, "CA nn nn", 3, 10, 10));
        map.put(0xC3, new Z80Instruction(Z80OpCodes.JP, "JP nnnn", 0xC3, "C3 nn nn", 3, 10, 0));

        map.put(0x38, new Z80Instruction(Z80OpCodes.JR, "JR C, nn", 0x38, "38 nn", 2, 12, 7));
        map.put(0x30, new Z80Instruction(Z80OpCodes.JR, "JR NC, nn", 0x30, "30 nn", 2, 12, 7));
        map.put(0x20, new Z80Instruction(Z80OpCodes.JR, "JR NZ, nn", 0x20, "20 nn", 2, 12, 7));
        map.put(0x28, new Z80Instruction(Z80OpCodes.JR, "JR Z, nn", 0x28, "28 nn", 2, 12, 7));
        map.put(0x18, new Z80Instruction(Z80OpCodes.JR, "JR nn", 0x18, "18 nn", 2, 12, 0));

        map.put(0x2, new Z80Instruction(Z80OpCodes.LD, "LD (BC), A", 0x2, "2", 1, 7, 0));
        map.put(0x12, new Z80Instruction(Z80OpCodes.LD, "LD (DE), A", 0x12, "12", 1, 7, 0));
        map.put(0x77, new Z80Instruction(Z80OpCodes.LD, "LD (HL), A", 0x77, "77", 1, 7, 0));
        map.put(0x70, new Z80Instruction(Z80OpCodes.LD, "LD (HL), B", 0x70, "70", 1, 7, 0));
        map.put(0x71, new Z80Instruction(Z80OpCodes.LD, "LD (HL), C", 0x71, "71", 1, 7, 0));
        map.put(0x72, new Z80Instruction(Z80OpCodes.LD, "LD (HL), D", 0x72, "72", 1, 7, 0));
        map.put(0x73, new Z80Instruction(Z80OpCodes.LD, "LD (HL), E", 0x73, "73", 1, 7, 0));
        map.put(0x74, new Z80Instruction(Z80OpCodes.LD, "LD (HL), H", 0x74, "74", 1, 7, 0));
        map.put(0x75, new Z80Instruction(Z80OpCodes.LD, "LD (HL), L", 0x75, "75", 1, 7, 0));
        map.put(0x74DD, new Z80Instruction(Z80OpCodes.LD, "LD (HL), IXH", 0x74DD, "DD 74", 2, 0, 0));
        map.put(0x74FD, new Z80Instruction(Z80OpCodes.LD, "LD (HL), IYH", 0x74FD, "FD 74", 2, 0, 0));
        map.put(0x75DD, new Z80Instruction(Z80OpCodes.LD, "LD (HL), IXL", 0x75DD, "DD 75", 2, 0, 0));
        map.put(0x75FD, new Z80Instruction(Z80OpCodes.LD, "LD (HL), IYL", 0x75FD, "FD 75", 2, 0, 0));
        map.put(0x36, new Z80Instruction(Z80OpCodes.LD, "LD (HL), nn", 0x36, "36 nn", 2, 10, 0));

        map.put(0x77DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX+nn), A", 0x77DD, "DD 77 nn", 3, 19, 0));
        map.put(0x70DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX+nn), B", 0x70DD, "DD 70 nn", 3, 19, 0));
        map.put(0x71DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX+nn), C", 0x71DD, "DD 71 nn", 3, 19, 0));
        map.put(0x72DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX+nn), D", 0x72DD, "DD 72 nn", 3, 19, 0));
        map.put(0x73DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX+nn), E", 0x73DD, "DD 73 nn", 3, 19, 0));
        map.put(0x74DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX+nn), H", 0x74DD, "DD 74 nn", 3, 19, 0));
        map.put(0x75DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX+nn), L", 0x75DD, "DD 75 nn", 3, 19, 0));
        map.put(0x36DD, new Z80Instruction(Z80OpCodes.LD, "LD (IX+nn), nn", 0x36DD, "DD 36 nn nn", 4, 19, 0));

        map.put(0x77FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY+nn), A", 0x77FD, "FD 77 nn", 3, 19, 0));
        map.put(0x70FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY+nn), B", 0x70FD, "FD 70 nn", 3, 19, 0));
        map.put(0x71FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY+nn), C", 0x71FD, "FD 71 nn", 3, 19, 0));
        map.put(0x72FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY+nn), D", 0x72FD, "FD 72 nn", 3, 19, 0));
        map.put(0x73FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY+nn), E", 0x73FD, "FD 73 nn", 3, 19, 0));
        map.put(0x74FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY+nn), H", 0x74FD, "FD 74 nn", 3, 19, 0));
        map.put(0x75FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY+nn), L", 0x75FD, "FD 75 nn", 3, 19, 0));
        map.put(0x36FD, new Z80Instruction(Z80OpCodes.LD, "LD (IY+nn), nn", 0x36FD, "FD 36 nn nn", 4, 19, 0));

        map.put(0x32, new Z80Instruction(Z80OpCodes.LD, "LD (nnnn), A", 0x32, "32 nn nn", 3, 13, 0));
        map.put(0x43ED, new Z80Instruction(Z80OpCodes.LD, "LD (nnnn), BC", 0x43ED, "ED 43 nn nn", 4, 20, 0));
        map.put(0x53ED, new Z80Instruction(Z80OpCodes.LD, "LD (nnnn), DE", 0x53ED, "ED 53 nn nn", 4, 20, 0));
        map.put(0x22, new Z80Instruction(Z80OpCodes.LD, "LD (nnnn), HL", 0x22, "22 nn nn", 3, 16, 0));
        map.put(0x22FD, new Z80Instruction(Z80OpCodes.LD, "LD (nnnn), IY", 0x22FD, "FD 22 nn nn", 4, 20, 0));
        map.put(0x73ED, new Z80Instruction(Z80OpCodes.LD, "LD (nnnn), SP", 0x73ED, "ED 73 nn nn", 4, 20, 0));
        map.put(0x22DD, new Z80Instruction(Z80OpCodes.LD, "LD (nnnn), IX", 0x22DD, "DD 22 nn nn", 4, 20, 0));

        map.put(0x0A, new Z80Instruction(Z80OpCodes.LD, "LD A, (BC)", 0x0A, "0A", 1, 7, 0));
        map.put(0x1A, new Z80Instruction(Z80OpCodes.LD, "LD A, (DE)", 0x1A, "1A", 1, 7, 0));
        map.put(0x7E, new Z80Instruction(Z80OpCodes.LD, "LD A, (HL)", 0x7E, "7E", 1, 7, 0));
        map.put(0x7EFD, new Z80Instruction(Z80OpCodes.LD, "LD A, (IY+nn)", 0x7EFD, "FD 7E nn", 3, 19, 0));
        map.put(0x7EDD, new Z80Instruction(Z80OpCodes.LD, "LD A, (IX+nn)", 0x7EDD, "DD 7E nn", 3, 19, 0));
        map.put(0x3A, new Z80Instruction(Z80OpCodes.LD, "LD A, (nnnn)", 0x3A, "3A nn nn", 3, 13, 0));
        map.put(0x7F, new Z80Instruction(Z80OpCodes.LD, "LD A, A", 0x7F, "7F", 1, 4, 0));
        map.put(0x78, new Z80Instruction(Z80OpCodes.LD, "LD A, B", 0x78, "78", 1, 4, 0));
        map.put(0x79, new Z80Instruction(Z80OpCodes.LD, "LD A, C", 0x79, "79", 1, 4, 0));
        map.put(0x7A, new Z80Instruction(Z80OpCodes.LD, "LD A, D", 0x7A, "7A", 1, 4, 0));
        map.put(0x7B, new Z80Instruction(Z80OpCodes.LD, "LD A, E", 0x7B, "7B", 1, 4, 0));
        map.put(0x7C, new Z80Instruction(Z80OpCodes.LD, "LD A, H", 0x7C, "7C", 1, 4, 0));
        map.put(0x7D, new Z80Instruction(Z80OpCodes.LD, "LD A, L", 0x7D, "7D", 1, 4, 0));
        map.put(0x57ED, new Z80Instruction(Z80OpCodes.LD, "LD A, I", 0x57ED, "ED 57", 2, 9, 0));
        map.put(0x5FED, new Z80Instruction(Z80OpCodes.LD, "LD A, R", 0x5FED, "ED 5F", 2, 9, 0));
        map.put(0x7CDD, new Z80Instruction(Z80OpCodes.LD, "LD A, IXH", 0x7CDD, "DD 7C", 2, 0, 0));
        map.put(0x7CFD, new Z80Instruction(Z80OpCodes.LD, "LD A, IYH", 0x7CFD, "FD 7C", 2, 0, 0));
        map.put(0x7DDD, new Z80Instruction(Z80OpCodes.LD, "LD A, IXL", 0x7DDD, "DD 7D", 2, 0, 0));
        map.put(0x7DFD, new Z80Instruction(Z80OpCodes.LD, "LD A, IYL", 0x7DFD, "FD 7D", 2, 0, 0));
        map.put(0x3E, new Z80Instruction(Z80OpCodes.LD, "LD A, nn", 0x3E, "3E nn", 2, 7, 0));

        map.put(0x46, new Z80Instruction(Z80OpCodes.LD, "LD B, (HL)", 0x46, "46", 1, 7, 0));
        map.put(0x46DD, new Z80Instruction(Z80OpCodes.LD, "LD B, (IX+nn)", 0x46DD, "DD 46 nn", 3, 19, 0));
        map.put(0x46FD, new Z80Instruction(Z80OpCodes.LD, "LD B, (IY+nn)", 0x46FD, "FD 46 nn", 3, 19, 0));
        map.put(0x47, new Z80Instruction(Z80OpCodes.LD, "LD B, A", 0x47, "47", 1, 4, 0));
        map.put(0x40, new Z80Instruction(Z80OpCodes.LD, "LD B, B", 0x40, "40", 1, 4, 0));
        map.put(0x41, new Z80Instruction(Z80OpCodes.LD, "LD B, C", 0x41, "41", 1, 4, 0));
        map.put(0x42, new Z80Instruction(Z80OpCodes.LD, "LD B, D", 0x42, "42", 1, 4, 0));
        map.put(0x43, new Z80Instruction(Z80OpCodes.LD, "LD B, E", 0x43, "43", 1, 4, 0));
        map.put(0x44, new Z80Instruction(Z80OpCodes.LD, "LD B, H", 0x44, "44", 1, 4, 0));
        map.put(0x45, new Z80Instruction(Z80OpCodes.LD, "LD B, L", 0x45, "45", 1, 4, 0));
        map.put(0x44DD, new Z80Instruction(Z80OpCodes.LD, "LD B, IXH", 0x44DD, "DD 44", 2, 0, 0));
        map.put(0x44FD, new Z80Instruction(Z80OpCodes.LD, "LD B, IYH", 0x44FD, "FD 44", 2, 0, 0));
        map.put(0x45DD, new Z80Instruction(Z80OpCodes.LD, "LD B, IXL", 0x45DD, "DD 45", 2, 0, 0));
        map.put(0x45FD, new Z80Instruction(Z80OpCodes.LD, "LD B, IYL", 0x45FD, "FD 45", 2, 0, 0));
        map.put(0x06, new Z80Instruction(Z80OpCodes.LD, "LD B, nn", 0x06, "06 nn", 2, 7, 0));

        map.put(0x4E, new Z80Instruction(Z80OpCodes.LD, "LD C, (HL)", 0x4E, "4E", 1, 7, 0));
        map.put(0x4EDD, new Z80Instruction(Z80OpCodes.LD, "LD C, (IX+nn)", 0x4EDD, "DD 4E nn", 3, 19, 0));
        map.put(0x4EFD, new Z80Instruction(Z80OpCodes.LD, "LD C, (IY+nn)", 0x4EFD, "FD 4E nn", 3, 19, 0));
        map.put(0x4F, new Z80Instruction(Z80OpCodes.LD, "LD C, A", 0x4F, "4F", 1, 4, 0));
        map.put(0x48, new Z80Instruction(Z80OpCodes.LD, "LD C, B", 0x48, "48", 1, 4, 0));
        map.put(0x49, new Z80Instruction(Z80OpCodes.LD, "LD C, C", 0x49, "49", 1, 4, 0));
        map.put(0x4A, new Z80Instruction(Z80OpCodes.LD, "LD C, D", 0x4A, "4A", 1, 4, 0));
        map.put(0x4B, new Z80Instruction(Z80OpCodes.LD, "LD C, E", 0x4B, "4B", 1, 4, 0));
        map.put(0x4C, new Z80Instruction(Z80OpCodes.LD, "LD C, H", 0x4C, "4C", 1, 4, 0));
        map.put(0x4D, new Z80Instruction(Z80OpCodes.LD, "LD C, L", 0x4D, "4D", 1, 4, 0));
        map.put(0x4CDD, new Z80Instruction(Z80OpCodes.LD, "LD C, IXH", 0x4CDD, "4CDD", 2, 0, 0));
        map.put(0x4CFD, new Z80Instruction(Z80OpCodes.LD, "LD C, IYH", 0x4CFD, "4CFD", 2, 0, 0));
        map.put(0x4DDD, new Z80Instruction(Z80OpCodes.LD, "LD C, IXL", 0x4DDD, "4DDD", 2, 0, 0));
        map.put(0x4DFD, new Z80Instruction(Z80OpCodes.LD, "LD C, IYL", 0x4DFD, "4DFD", 2, 0, 0));
        map.put(0x0E, new Z80Instruction(Z80OpCodes.LD, "LD C, nn", 0x0E, "0E nn", 2, 7, 0));

        map.put(0x56, new Z80Instruction(Z80OpCodes.LD, "LD D, (HL)", 0x56, "56", 1, 7, 0));
        map.put(0x56DD, new Z80Instruction(Z80OpCodes.LD, "LD D, (IX+nn)", 0x56DD, "DD 56 nn", 3, 19, 0));
        map.put(0x56FD, new Z80Instruction(Z80OpCodes.LD, "LD D, (IY+nn)", 0x56FD, "FD 56 nn", 3, 19, 0));
        map.put(0x57, new Z80Instruction(Z80OpCodes.LD, "LD D, A", 0x57, "57", 1, 4, 0));
        map.put(0x50, new Z80Instruction(Z80OpCodes.LD, "LD D, B", 0x50, "50", 1, 4, 0));
        map.put(0x51, new Z80Instruction(Z80OpCodes.LD, "LD D, C", 0x51, "51", 1, 4, 0));
        map.put(0x52, new Z80Instruction(Z80OpCodes.LD, "LD D, D", 0x52, "52", 1, 4, 0));
        map.put(0x53, new Z80Instruction(Z80OpCodes.LD, "LD D, E", 0x53, "53", 1, 4, 0));
        map.put(0x54, new Z80Instruction(Z80OpCodes.LD, "LD D, H", 0x54, "54", 1, 4, 0));
        map.put(0x55, new Z80Instruction(Z80OpCodes.LD, "LD D, L", 0x55, "55", 1, 4, 0));
        map.put(0x54DD, new Z80Instruction(Z80OpCodes.LD, "LD D, IXH", 0x54DD, "DD 54", 2, 0, 0));
        map.put(0x54FD, new Z80Instruction(Z80OpCodes.LD, "LD D, IYH", 0x54FD, "FD 54", 2, 0, 0));
        map.put(0x55DD, new Z80Instruction(Z80OpCodes.LD, "LD D, IXL", 0x55DD, "DD 55", 2, 0, 0));
        map.put(0x55FD, new Z80Instruction(Z80OpCodes.LD, "LD D, IYL", 0x55FD, "FD 55", 2, 0, 0));
        map.put(0x16, new Z80Instruction(Z80OpCodes.LD, "LD D, nn", 0x16, "16 nn", 2, 7, 0));

        map.put(0x5E, new Z80Instruction(Z80OpCodes.LD, "LD E, (HL)", 0x5E, "5E", 1, 7, 0));
        map.put(0x5EDD, new Z80Instruction(Z80OpCodes.LD, "LD E, (IX+nn)", 0x5EDD, "DD 5E nn", 3, 19, 0));
        map.put(0x5EFD, new Z80Instruction(Z80OpCodes.LD, "LD E, (IY+nn)", 0x5EFD, "FD 5E nn", 3, 19, 0));
        map.put(0x5F, new Z80Instruction(Z80OpCodes.LD, "LD E, A", 0x5F, "5F", 1, 4, 0));
        map.put(0x58, new Z80Instruction(Z80OpCodes.LD, "LD E, B", 0x58, "58", 1, 4, 0));
        map.put(0x59, new Z80Instruction(Z80OpCodes.LD, "LD E, C", 0x59, "59", 1, 4, 0));
        map.put(0x5A, new Z80Instruction(Z80OpCodes.LD, "LD E, D", 0x5A, "5A", 1, 4, 0));
        map.put(0x5B, new Z80Instruction(Z80OpCodes.LD, "LD E, E", 0x5B, "5B", 1, 4, 0));
        map.put(0x5C, new Z80Instruction(Z80OpCodes.LD, "LD E, H", 0x5C, "5C", 1, 4, 0));
        map.put(0x5D, new Z80Instruction(Z80OpCodes.LD, "LD E, L", 0x5D, "5D", 1, 4, 0));
        map.put(0x5CDD, new Z80Instruction(Z80OpCodes.LD, "LD E, IXH", 0x5CDD, "DD 5C", 2, 0, 0));
        map.put(0x5CFD, new Z80Instruction(Z80OpCodes.LD, "LD E, IYH", 0x5CFD, "FD 5C", 2, 0, 0));
        map.put(0x5DDD, new Z80Instruction(Z80OpCodes.LD, "LD E, IXL", 0x5DDD, "DD 5D", 2, 0, 0));
        map.put(0x5DFD, new Z80Instruction(Z80OpCodes.LD, "LD E, IYL", 0x5DFD, "FD 5D", 2, 0, 0));
        map.put(0x1E, new Z80Instruction(Z80OpCodes.LD, "LD E, nn", 0x1E, "1E nn", 2, 7, 0));

        map.put(0x66, new Z80Instruction(Z80OpCodes.LD, "LD H, (HL)", 0x66, "66", 1, 7, 0));
        map.put(0x66DD, new Z80Instruction(Z80OpCodes.LD, "LD H, (IX+nn)", 0x66DD, "DD 66 nn", 3, 19, 0));
        map.put(0x66FD, new Z80Instruction(Z80OpCodes.LD, "LD H, (IY+nn)", 0x66FD, "FD 66 nn", 3, 19, 0));
        map.put(0x67, new Z80Instruction(Z80OpCodes.LD, "LD H, A", 0x67, "67", 1, 4, 0));
        map.put(0x60, new Z80Instruction(Z80OpCodes.LD, "LD H, B", 0x60, "60", 1, 4, 0));
        map.put(0x61, new Z80Instruction(Z80OpCodes.LD, "LD H, C", 0x61, "61", 1, 4, 0));
        map.put(0x62, new Z80Instruction(Z80OpCodes.LD, "LD H, D", 0x62, "62", 1, 4, 0));
        map.put(0x63, new Z80Instruction(Z80OpCodes.LD, "LD H, E", 0x63, "63", 1, 4, 0));
        map.put(0x64, new Z80Instruction(Z80OpCodes.LD, "LD H, H", 0x64, "64", 1, 4, 0));
        map.put(0x65, new Z80Instruction(Z80OpCodes.LD, "LD H, L", 0x65, "65", 1, 4, 0));
        map.put(0x26, new Z80Instruction(Z80OpCodes.LD, "LD H, nn", 0x26, "26 nn", 2, 7, 0));

        map.put(0x6E, new Z80Instruction(Z80OpCodes.LD, "LD L, (HL)", 0x6E, "6E", 1, 7, 0));
        map.put(0x6EDD, new Z80Instruction(Z80OpCodes.LD, "LD L, (IX+nn)", 0x6EDD, "DD 6E nn", 3, 19, 0));
        map.put(0x6EFD, new Z80Instruction(Z80OpCodes.LD, "LD L, (IY+nn)", 0x6EFD, "FD 6E nn", 3, 19, 0));
        map.put(0x6F, new Z80Instruction(Z80OpCodes.LD, "LD L, A", 0x6F, "6F", 1, 4, 0));
        map.put(0x68, new Z80Instruction(Z80OpCodes.LD, "LD L, B", 0x68, "68", 1, 4, 0));
        map.put(0x69, new Z80Instruction(Z80OpCodes.LD, "LD L, C", 0x69, "69", 1, 4, 0));
        map.put(0x6A, new Z80Instruction(Z80OpCodes.LD, "LD L, D", 0x6A, "6A", 1, 4, 0));
        map.put(0x6B, new Z80Instruction(Z80OpCodes.LD, "LD L, E", 0x6B, "6B", 1, 4, 0));
        map.put(0x6C, new Z80Instruction(Z80OpCodes.LD, "LD L, H", 0x6C, "6C", 1, 4, 0));
        map.put(0x6D, new Z80Instruction(Z80OpCodes.LD, "LD L, L", 0x6D, "6D", 1, 4, 0));
        map.put(0x2E, new Z80Instruction(Z80OpCodes.LD, "LD L, nn", 0x2E, "2E nn", 2, 7, 0));

        map.put(0x47ED, new Z80Instruction(Z80OpCodes.LD, "LD I, A", 0x47ED, "ED 47", 2, 9, 0));

        map.put(0x4FED, new Z80Instruction(Z80OpCodes.LD, "LD R, A", 0x4FED, "ED 4F", 2, 9, 0));
/*
        map.put(0x66DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, (HL)", 0x66DD, "66DD", 2, 0, 0));
        map.put(0x67DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, A", 0x67DD, "67DD", 2, 0, 0));
        map.put(0x60DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, B", 0x60DD, "60DD", 2, 0, 0));
        map.put(0x61DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, C", 0x61DD, "61DD", 2, 0, 0));
        map.put(0x62DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, D", 0x62DD, "62DD", 2, 0, 0));
        map.put(0x63DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, E", 0x63DD, "63DD", 2, 0, 0));
        map.put(0x64DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, H", 0x64DD, "64DD", 2, 0, 0));
        map.put(0x65DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, L", 0x65DD, "65DD", 2, 0, 0));
        map.put(0x26DD, new Z80Instruction(Z80OpCodes.LD, "LD IXH, nn", 0x26DD, "26DD nn", 3, 0, 0));

        map.put(0x66FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, (HL)", 0x66FD, "66FD", 2, 0, 0));
        map.put(0x67FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, A", 0x67FD, "67FD", 2, 0, 0));
        map.put(0x60FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, B", 0x60FD, "60FD", 2, 0, 0));
        map.put(0x62FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, D", 0x62FD, "62FD", 2, 0, 0));
        map.put(0x61FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, C", 0x61FD, "61FD", 2, 0, 0));
        map.put(0x63FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, E", 0x63FD, "63FD", 2, 0, 0));
        map.put(0x64FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, H", 0x64FD, "64FD", 2, 0, 0));
        map.put(0x65FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, L", 0x65FD, "65FD", 2, 0, 0));
        map.put(0x26FD, new Z80Instruction(Z80OpCodes.LD, "LD IYH, nn", 0x26FD, "26FD nn", 3, 0, 0));

        map.put(0x6EDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, (HL)", 0x6EDD, "6EDD", 2, 0, 0));
        map.put(0x6FDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, A", 0x6FDD, "6FDD", 2, 0, 0));
        map.put(0x68DD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, B", 0x68DD, "68DD", 2, 0, 0));
        map.put(0x69DD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, C", 0x69DD, "69DD", 2, 0, 0));
        map.put(0x6ADD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, D", 0x6ADD, "6ADD", 2, 0, 0));
        map.put(0x6BDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, E", 0x6BDD, "6BDD", 2, 0, 0));
        map.put(0x6CDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, H", 0x6CDD, "6CDD", 2, 0, 0));
        map.put(0x6DDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, L", 0x6DDD, "6DDD", 2, 0, 0));
        map.put(0x2EDD, new Z80Instruction(Z80OpCodes.LD, "LD IXL, nn", 0x2EDD, "2EDD nn", 3, 0, 0));

        map.put(0x6EFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, (HL)", 0x6EFD, "6EFD", 2, 0, 0));
        map.put(0x6FFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, A", 0x6FFD, "6FFD", 2, 0, 0));
        map.put(0x68FD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, B", 0x68FD, "68FD", 2, 0, 0));
        map.put(0x69FD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, C", 0x69FD, "69FD", 2, 0, 0));
        map.put(0x6AFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, D", 0x6AFD, "6AFD", 2, 0, 0));
        map.put(0x6BFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, E", 0x6BFD, "6BFD", 2, 0, 0));
        map.put(0x6CFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, H", 0x6CFD, "6CFD", 2, 0, 0));
        map.put(0x6DFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, L", 0x6DFD, "6DFD", 2, 0, 0));
        map.put(0x2EFD, new Z80Instruction(Z80OpCodes.LD, "LD IYL, nn", 0x2EFD, "2EFD nn", 3, 0, 0));
*/
        map.put(0x4BED, new Z80Instruction(Z80OpCodes.LD, "LD BC, (nnnn)", 0x4BED, "ED 4B nn nn", 4, 20, 0));
        map.put(0x01, new Z80Instruction(Z80OpCodes.LD, "LD BC, nnnn", 0x01, "01 nn nn", 3, 10, 0));
        map.put(0x5BED, new Z80Instruction(Z80OpCodes.LD, "LD DE, (nnnn)", 0x5BED, "ED 5B nn nn", 4, 20, 0));
        map.put(0x11, new Z80Instruction(Z80OpCodes.LD, "LD DE, nnnn", 0x11, "11 nn nn", 3, 10, 0));
        map.put(0x2A, new Z80Instruction(Z80OpCodes.LD, "LD HL, (nnnn)", 0x2A, "2A nn nn", 3, 16, 0));
        map.put(0x21, new Z80Instruction(Z80OpCodes.LD, "LD HL, nnnn", 0x21, "21 nn nn", 3, 10, 0));
        map.put(0x2ADD, new Z80Instruction(Z80OpCodes.LD, "LD IX, (nnnn)", 0x2ADD, "DD 2A nn nn", 4, 20, 0));
        map.put(0x21DD, new Z80Instruction(Z80OpCodes.LD, "LD IX, nnnn", 0x21DD, "DD 21 nn nn", 4, 14, 0));
        map.put(0x2AFD, new Z80Instruction(Z80OpCodes.LD, "LD IY, (nnnn)", 0x2AFD, "FD 2A nn nn", 4, 20, 0));
        map.put(0x21FD, new Z80Instruction(Z80OpCodes.LD, "LD IY, nnnn", 0x21FD, "FD 21 nn nn", 4, 14, 0));
        map.put(0x7BED, new Z80Instruction(Z80OpCodes.LD, "LD SP, (nnnn)", 0x7BED, "ED 7B nn nn", 4, 20, 0));
        map.put(0xF9, new Z80Instruction(Z80OpCodes.LD, "LD SP, HL", 0xF9, "F9", 1, 6, 0));
        map.put(0xF9DD, new Z80Instruction(Z80OpCodes.LD, "LD SP, IX", 0xF9DD, "DD F9", 2, 10, 0));
        map.put(0xF9FD, new Z80Instruction(Z80OpCodes.LD, "LD SP, IY", 0xF9FD, "FD F9", 2, 10, 0));
        map.put(0x31, new Z80Instruction(Z80OpCodes.LD, "LD SP, nnnn", 0x31, "31 nn nn", 3, 10, 0));

        map.put(0xA8ED, new Z80Instruction(Z80OpCodes.LDD, "LDD", 0xA8ED, "ED A8", 2, 16, 0));
        map.put(0xB8ED, new Z80Instruction(Z80OpCodes.LDDR, "LDDR", 0xB8ED, "ED B8", 2, 21, 16));
        map.put(0xA0ED, new Z80Instruction(Z80OpCodes.LDI, "LDI", 0xA0ED, "ED A0", 2, 16, 0));
        map.put(0xB0ED, new Z80Instruction(Z80OpCodes.LDIR, "LDIR", 0xB0ED, "ED B0", 2, 21, 16));

        map.put(0x44ED, new Z80Instruction(Z80OpCodes.NEG, "NEG", 0x44ED, "ED 44", 2, 8, 0));

        map.put(0x0, new Z80Instruction(Z80OpCodes.NOP, "NOP", 0x0, "0", 1, 4, 0));

        map.put(0xB6, new Z80Instruction(Z80OpCodes.OR, "OR (HL)", 0xB6, "B6", 1, 7, 0));
        map.put(0xB6DD, new Z80Instruction(Z80OpCodes.OR, "OR (IX+nn)", 0xB6DD, "DD B6 nn", 3, 19, 0));
        map.put(0xB6FD, new Z80Instruction(Z80OpCodes.OR, "OR (IY+nn)", 0xB6FD, "FD B6 nn", 3, 19, 0));
        map.put(0xB7, new Z80Instruction(Z80OpCodes.OR, "OR A", 0xB7, "B7", 1, 4, 0));
        map.put(0xB0, new Z80Instruction(Z80OpCodes.OR, "OR B", 0xB0, "B0", 1, 4, 0));
        map.put(0xB1, new Z80Instruction(Z80OpCodes.OR, "OR C", 0xB1, "B1", 1, 4, 0));
        map.put(0xB2, new Z80Instruction(Z80OpCodes.OR, "OR D", 0xB2, "B2", 1, 4, 0));
        map.put(0xB3, new Z80Instruction(Z80OpCodes.OR, "OR E", 0xB3, "B3", 1, 4, 0));
        map.put(0xB4, new Z80Instruction(Z80OpCodes.OR, "OR H", 0xB4, "B4", 1, 4, 0));
        map.put(0xB5, new Z80Instruction(Z80OpCodes.OR, "OR L", 0xB5, "B5", 1, 4, 0));
        map.put(0xB4DD, new Z80Instruction(Z80OpCodes.OR, "OR IXH", 0xB4DD, "DD B4", 2, 0, 0));
        map.put(0xB4FD, new Z80Instruction(Z80OpCodes.OR, "OR IYH", 0xB4FD, "FD B4", 2, 0, 0));
        map.put(0xB5DD, new Z80Instruction(Z80OpCodes.OR, "OR IXL", 0xB5DD, "DD B5", 2, 0, 0));
        map.put(0xB5FD, new Z80Instruction(Z80OpCodes.OR, "OR IYL", 0xB5FD, "FD B5", 2, 0, 0));
        map.put(0xF6, new Z80Instruction(Z80OpCodes.OR, "OR nn", 0xF6, "F6 nn", 2, 7, 0));

        map.put(0x8BED, new Z80Instruction(Z80OpCodes.OTDR, "OTDR", 0x8BED, "ED 8B", 2, 21, 16));
        map.put(0xB3ED, new Z80Instruction(Z80OpCodes.OTIR, "OTIR", 0xB3ED, "ED B3", 2, 21, 16));

        map.put(0x79ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), A", 0x79ED, "ED 79", 2, 12, 0));
        map.put(0x41ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), B", 0x41ED, "ED 41", 2, 12, 0));
        map.put(0x49ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), C", 0x49ED, "ED 49", 2, 12, 0));
        map.put(0x51ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), D", 0x51ED, "ED 51", 2, 12, 0));
        map.put(0x59ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), E", 0x59ED, "ED 59", 2, 12, 0));
        map.put(0x61ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), H", 0x61ED, "ED 61", 2, 12, 0));
        map.put(0x69ED, new Z80Instruction(Z80OpCodes.OUT, "OUT (C), L", 0x69ED, "ED 69", 2, 12, 0));
        map.put(0xD3, new Z80Instruction(Z80OpCodes.OUT, "OUT (nn), A", 0xD3, "D3 nn", 2, 11, 0));

        map.put(0xABED, new Z80Instruction(Z80OpCodes.OUTD, "OUTD", 0xABED, "ED AB", 2, 16, 0));
        map.put(0xA3ED, new Z80Instruction(Z80OpCodes.OUTI, "OUTI", 0xA3ED, "ED A3", 2, 16, 0));

        map.put(0xF1, new Z80Instruction(Z80OpCodes.POP, "POP AF", 0xF1, "F1", 1, 10, 0));
        map.put(0xC1, new Z80Instruction(Z80OpCodes.POP, "POP BC", 0xC1, "C1", 1, 10, 0));
        map.put(0xD1, new Z80Instruction(Z80OpCodes.POP, "POP DE", 0xD1, "D1", 1, 10, 0));
        map.put(0xE1, new Z80Instruction(Z80OpCodes.POP, "POP HL", 0xE1, "E1", 1, 10, 0));
        map.put(0xE1DD, new Z80Instruction(Z80OpCodes.POP, "POP IX", 0xE1DD, "DD E1", 2, 14, 0));
        map.put(0xE1FD, new Z80Instruction(Z80OpCodes.POP, "POP IY", 0xE1FD, "DF E1", 2, 14, 0));

        map.put(0xF5, new Z80Instruction(Z80OpCodes.PUSH, "PUSH AF", 0xF5, "F5", 1, 11, 0));
        map.put(0xC5, new Z80Instruction(Z80OpCodes.PUSH, "PUSH BC", 0xC5, "C5", 1, 11, 0));
        map.put(0xD5, new Z80Instruction(Z80OpCodes.PUSH, "PUSH DE", 0xD5, "D5", 1, 11, 0));
        map.put(0xE5, new Z80Instruction(Z80OpCodes.PUSH, "PUSH HL", 0xE5, "E5", 1, 11, 0));
        map.put(0xE5DD, new Z80Instruction(Z80OpCodes.PUSH, "PUSH IX", 0xE5DD, "DD E5", 2, 15, 0));
        map.put(0xE5FD, new Z80Instruction(Z80OpCodes.PUSH, "PUSH IY", 0xE5FD, "FD E5", 2, 15, 0));

        map.put(0x86CB, new Z80Instruction(Z80OpCodes.RES, "RES n, (HL)", 0x86CB, "CB 86+8*n", 2, 15, 0)); //FIXME expand instructions out maybe?
        map.put(0xCBDD8600, new Z80Instruction(Z80OpCodes.RES, "RES n, (IX+nn)", 0xCBDD8600, "DD CB nn 86+8*n", 4, 23, 0));
        map.put(0xCBFD8600, new Z80Instruction(Z80OpCodes.RES, "RES n, (IY+nn)", 0xCBFD8600, "FD CB nn 86+8*n", 4, 23, 0));
        map.put(0x87CB, new Z80Instruction(Z80OpCodes.RES, "RES n, A", 0x87CB, "CB 87", 2, 8, 0));
        map.put(0x80CB, new Z80Instruction(Z80OpCodes.RES, "RES n, B", 0x80CB, "CB 80", 2, 8, 0));
        map.put(0x81CB, new Z80Instruction(Z80OpCodes.RES, "RES n, C", 0x81CB, "CB 81", 2, 8, 0));
        map.put(0x82CB, new Z80Instruction(Z80OpCodes.RES, "RES n, D", 0x82CB, "CB 82", 2, 8, 0));
        map.put(0x83CB, new Z80Instruction(Z80OpCodes.RES, "RES n, E", 0x83CB, "CB 83", 2, 8, 0));
        map.put(0x84CB, new Z80Instruction(Z80OpCodes.RES, "RES n, H", 0x84CB, "CB 84", 2, 8, 0));
        map.put(0x85CB, new Z80Instruction(Z80OpCodes.RES, "RES n, L", 0x85CB, "CB 85", 2, 8, 0));

        map.put(0xC9, new Z80Instruction(Z80OpCodes.RET, "RET", 0xC9, "C9", 1, 10, 0));
        map.put(0xD8, new Z80Instruction(Z80OpCodes.RET, "RET C", 0xD8, "D8", 1, 11, 5));
        map.put(0xF8, new Z80Instruction(Z80OpCodes.RET, "RET M", 0xF8, "F8", 1, 11, 5));
        map.put(0xD0, new Z80Instruction(Z80OpCodes.RET, "RET NC", 0xD0, "D0", 1, 11, 5));
        map.put(0xC0, new Z80Instruction(Z80OpCodes.RET, "RET NZ", 0xC0, "C0", 1, 11, 5));
        map.put(0xF0, new Z80Instruction(Z80OpCodes.RET, "RET P", 0xF0, "F0", 1, 11, 5));
        map.put(0xE8, new Z80Instruction(Z80OpCodes.RET, "RET PE", 0xE8, "E8", 1, 11, 5));
        map.put(0xE0, new Z80Instruction(Z80OpCodes.RET, "RET PO", 0xE0, "E0", 1, 11, 5));
        map.put(0xC8, new Z80Instruction(Z80OpCodes.RET, "RET Z", 0xC8, "C8", 1, 11, 5));

        map.put(0x4DED, new Z80Instruction(Z80OpCodes.RETI, "RETI", 0x4DED, "ED 4D", 2, 14, 0));
        map.put(0x45ED, new Z80Instruction(Z80OpCodes.RETN, "RETN", 0x45ED, "ED 45", 2, 14, 0));

        map.put(0x16CB, new Z80Instruction(Z80OpCodes.RL, "RL (HL)", 0x16CB, "CB 16", 2, 15, 0));
        map.put(0xCBDD1600, new Z80Instruction(Z80OpCodes.RL, "RL (IX+nn)", 0xCBDD1600, "DD CB nn 16", 4, 23, 0));
        map.put(0xCBFD1600, new Z80Instruction(Z80OpCodes.RL, "RL (IY+nn)", 0xCBFD1600, "FD CB nn 16", 4, 23, 0));
        map.put(0x17CB, new Z80Instruction(Z80OpCodes.RL, "RL A", 0x17CB, "CB 17", 2, 8, 0));
        map.put(0x10CB, new Z80Instruction(Z80OpCodes.RL, "RL B", 0x10CB, "CB 10", 2, 8, 0));
        map.put(0x11CB, new Z80Instruction(Z80OpCodes.RL, "RL C", 0x11CB, "CB 11", 2, 8, 0));
        map.put(0x12CB, new Z80Instruction(Z80OpCodes.RL, "RL D", 0x12CB, "CB 12", 2, 8, 0));
        map.put(0x13CB, new Z80Instruction(Z80OpCodes.RL, "RL E", 0x13CB, "CB 13", 2, 8, 0));
        map.put(0x14CB, new Z80Instruction(Z80OpCodes.RL, "RL H", 0x14CB, "CB 14", 2, 8, 0));
        map.put(0x15CB, new Z80Instruction(Z80OpCodes.RL, "RL L", 0x15CB, "CB 15", 2, 8, 0));

        map.put(0x17, new Z80Instruction(Z80OpCodes.RLA, "RLA", 0x17, "17", 1, 4, 0));

        map.put(0x06CB, new Z80Instruction(Z80OpCodes.RLC, "RLC (HL)", 0x06CB, "CB 06", 2, 15, 0));
        map.put(0xCBDD0600, new Z80Instruction(Z80OpCodes.RLC, "RLC (IX+nn)", 0xCBDD0600, "DD CB nn 06", 4, 23, 0));
        map.put(0xCBFD0600, new Z80Instruction(Z80OpCodes.RLC, "RLC (IY+nn)", 0xCBFD0600, "FD CB nn 06", 4, 23, 0));
        map.put(0x07CB, new Z80Instruction(Z80OpCodes.RLC, "RLC A", 0x07CB, "CB 07", 2, 8, 0));
        map.put(0x00CB, new Z80Instruction(Z80OpCodes.RLC, "RLC B", 0x00CB, "CB 00", 2, 8, 0));
        map.put(0x01CB, new Z80Instruction(Z80OpCodes.RLC, "RLC C", 0x01CB, "CB 01", 2, 8, 0));
        map.put(0x02CB, new Z80Instruction(Z80OpCodes.RLC, "RLC D", 0x02CB, "CB 02", 2, 8, 0));
        map.put(0x03CB, new Z80Instruction(Z80OpCodes.RLC, "RLC E", 0x03CB, "CB 03", 2, 8, 0));
        map.put(0x04CB, new Z80Instruction(Z80OpCodes.RLC, "RLC H", 0x04CB, "CB 04", 2, 8, 0));
        map.put(0x05CB, new Z80Instruction(Z80OpCodes.RLC, "RLC L", 0x05CB, "CB 05", 2, 8, 0));

        map.put(0x7, new Z80Instruction(Z80OpCodes.RLCA, "RLCA", 0x7, "07", 1, 4, 0));

        map.put(0x6FED, new Z80Instruction(Z80OpCodes.RLD, "RLD", 0x6FED, "ED 6F", 2, 18, 0));

        map.put(0x1ECB, new Z80Instruction(Z80OpCodes.RR, "RR (HL)", 0x1ECB, "CB 1E", 2, 15, 0));
        map.put(0xCBDD1E00, new Z80Instruction(Z80OpCodes.RR, "RR (IX+nn)", 0xCBDD1E00, "DD CB nn 1E", 4, 23, 0));
        map.put(0xCBFD1E00, new Z80Instruction(Z80OpCodes.RR, "RR (IY+nn)", 0xCBFD1E00, "FD CB nn 1E", 4, 23, 0));
        map.put(0x1FCB, new Z80Instruction(Z80OpCodes.RR, "RR A", 0x1FCB, "1FCB", 2, 8, 0));
        map.put(0x18CB, new Z80Instruction(Z80OpCodes.RR, "RR B", 0x18CB, "18CB", 2, 8, 0));
        map.put(0x19CB, new Z80Instruction(Z80OpCodes.RR, "RR C", 0x19CB, "19CB", 2, 8, 0));
        map.put(0x1ACB, new Z80Instruction(Z80OpCodes.RR, "RR D", 0x1ACB, "1ACB", 2, 8, 0));
        map.put(0x1BCB, new Z80Instruction(Z80OpCodes.RR, "RR E", 0x1BCB, "1BCB", 2, 8, 0));
        map.put(0x1CCB, new Z80Instruction(Z80OpCodes.RR, "RR H", 0x1CCB, "1CCB", 2, 8, 0));
        map.put(0x1DCB, new Z80Instruction(Z80OpCodes.RR, "RR L", 0x1DCB, "1DCB", 2, 8, 0));

        map.put(0x1F, new Z80Instruction(Z80OpCodes.RRA, "RRA", 0x1F, "1F", 1, 4, 0));

        map.put(0x0ECB, new Z80Instruction(Z80OpCodes.RRC, "RRC (HL)", 0x0ECB, "CB 0E", 2, 15, 0));
        map.put(0xCBDD0E00, new Z80Instruction(Z80OpCodes.RRC, "RRC (IX+nn)", 0xCBDD0E00, "DD CB nn 0E", 4, 23, 0));
        map.put(0xCBFD0E00, new Z80Instruction(Z80OpCodes.RRC, "RRC (IY+nn)", 0xCBFD0E00, "FD CB nn 0E", 4, 23, 0));
        map.put(0x0FCB, new Z80Instruction(Z80OpCodes.RRC, "RRC A", 0x0FCB, "CB 0F", 2, 8, 0));
        map.put(0x08CB, new Z80Instruction(Z80OpCodes.RRC, "RRC B", 0x08CB, "CB 08", 2, 8, 0));
        map.put(0x09CB, new Z80Instruction(Z80OpCodes.RRC, "RRC C", 0x09CB, "CB 09", 2, 8, 0));
        map.put(0x0ACB, new Z80Instruction(Z80OpCodes.RRC, "RRC D", 0x0ACB, "CB 0A", 2, 8, 0));
        map.put(0x0BCB, new Z80Instruction(Z80OpCodes.RRC, "RRC E", 0x0BCB, "CB 0B", 2, 8, 0));
        map.put(0x0CCB, new Z80Instruction(Z80OpCodes.RRC, "RRC H", 0x0CCB, "CB 0C", 2, 8, 0));
        map.put(0x0DCB, new Z80Instruction(Z80OpCodes.RRC, "RRC L", 0x0DCB, "CB 0D", 2, 8, 0));

        map.put(0x0F, new Z80Instruction(Z80OpCodes.RRCA, "RRCA", 0x0F, "0F", 1, 4, 0));

        map.put(0x67ED, new Z80Instruction(Z80OpCodes.RRD, "RRD", 0x67ED, "ED 67", 2, 18, 10));

        map.put(0xC7, new Z80Instruction(Z80OpCodes.RST, "RST 00H", 0xC7, "C7", 1, 11, 0));
        map.put(0xCF, new Z80Instruction(Z80OpCodes.RST, "RST 08H", 0xCF, "CF", 1, 11, 0));
        map.put(0xD7, new Z80Instruction(Z80OpCodes.RST, "RST 10H", 0xD7, "D7", 1, 11, 0));
        map.put(0xDF, new Z80Instruction(Z80OpCodes.RST, "RST 18H", 0xDF, "DF", 1, 11, 0));
        map.put(0xE7, new Z80Instruction(Z80OpCodes.RST, "RST 20H", 0xE7, "E7", 1, 11, 0));
        map.put(0xEF, new Z80Instruction(Z80OpCodes.RST, "RST 28H", 0xEF, "EF", 1, 11, 0));
        map.put(0xF7, new Z80Instruction(Z80OpCodes.RST, "RST 30H", 0xF7, "F7", 1, 11, 0));
        map.put(0xFF, new Z80Instruction(Z80OpCodes.RST, "RST 38H", 0xFF, "FF", 1, 11, 0));

        map.put(0x9E, new Z80Instruction(Z80OpCodes.SBC, "SBC A, (HL)", 0x9E, "9E", 1, 7, 0));
        map.put(0x9EDD, new Z80Instruction(Z80OpCodes.SBC, "SBC A, (IX+nn)", 0x9EDD, "DD 9E nn", 3, 19, 0));
        map.put(0x9EFD, new Z80Instruction(Z80OpCodes.SBC, "SBC A, (IY+nn)", 0x9EFD, "FD 9E nn", 3, 19, 0));
        map.put(0x9F, new Z80Instruction(Z80OpCodes.SBC, "SBC A, A", 0x9F, "9F", 1, 4, 0));
        map.put(0x98, new Z80Instruction(Z80OpCodes.SBC, "SBC A, B", 0x98, "98", 1, 4, 0));
        map.put(0x99, new Z80Instruction(Z80OpCodes.SBC, "SBC A, C", 0x99, "99", 1, 4, 0));
        map.put(0x9A, new Z80Instruction(Z80OpCodes.SBC, "SBC A, D", 0x9A, "9A", 1, 4, 0));
        map.put(0x9B, new Z80Instruction(Z80OpCodes.SBC, "SBC A, E", 0x9B, "9B", 1, 4, 0));
        map.put(0x9C, new Z80Instruction(Z80OpCodes.SBC, "SBC A, H", 0x9C, "9C", 1, 4, 0));
        map.put(0x9D, new Z80Instruction(Z80OpCodes.SBC, "SBC A, L", 0x9D, "9D", 1, 4, 0));
        map.put(0x9CDD, new Z80Instruction(Z80OpCodes.SBC, "SBC IXH", 0x9CDD, "DD 9C", 2, 0, 0));
        map.put(0x9CFD, new Z80Instruction(Z80OpCodes.SBC, "SBC IYH", 0x9CFD, "FD 9C", 2, 0, 0));
        map.put(0x9DDD, new Z80Instruction(Z80OpCodes.SBC, "SBC IXL", 0x9DDD, "DD 9D", 2, 0, 0));
        map.put(0x9DFD, new Z80Instruction(Z80OpCodes.SBC, "SBC IYL", 0x9DFD, "FD 9D", 2, 0, 0));
        map.put(0xDE, new Z80Instruction(Z80OpCodes.SBC, "SBC A, nn", 0xDE, "DE nn", 2, 7, 0));

        map.put(0x42ED, new Z80Instruction(Z80OpCodes.SBC, "SBC HL, BC", 0x42ED, "ED 42", 2, 15, 0));
        map.put(0x52ED, new Z80Instruction(Z80OpCodes.SBC, "SBC HL, DE", 0x52ED, "ED 52", 2, 15, 0));
        map.put(0x62ED, new Z80Instruction(Z80OpCodes.SBC, "SBC HL, HL", 0x62ED, "ED 62", 2, 15, 0));
        map.put(0x72ED, new Z80Instruction(Z80OpCodes.SBC, "SBC HL, SP", 0x72ED, "ED 72", 2, 15, 0));

        map.put(0x37, new Z80Instruction(Z80OpCodes.SCF, "SCF", 0x37, "37", 1, 4, 0));

        map.put(0xC6CB, new Z80Instruction(Z80OpCodes.SET, "SET n, (HL)", 0xC6CB, "CB C6+8*n", 2, 15, 0));
        map.put(0xCBDDC600, new Z80Instruction(Z80OpCodes.SET, "SET n, (IX+nn)", 0xCBDDC600, "DD CB nn C6+8*n", 4, 23, 0));
        map.put(0xCBFDC600, new Z80Instruction(Z80OpCodes.SET, "SET n, (IY+nn)", 0xCBFDC600, "FD CB nn C6+8*n", 4, 23, 0));
        map.put(0xC7CB, new Z80Instruction(Z80OpCodes.SET, "SET n, A", 0xC7CB, "CB C7+8*n", 2, 8, 0));
        map.put(0xC0CB, new Z80Instruction(Z80OpCodes.SET, "SET n, B", 0xC0CB, "CB C0+8*n", 2, 8, 0));
        map.put(0xC1CB, new Z80Instruction(Z80OpCodes.SET, "SET n, C", 0xC1CB, "CB C1+8*n", 2, 8, 0));
        map.put(0xC2CB, new Z80Instruction(Z80OpCodes.SET, "SET n, D", 0xC2CB, "CB C2+8*n", 2, 8, 0));
        map.put(0xC3CB, new Z80Instruction(Z80OpCodes.SET, "SET n, E", 0xC3CB, "CB C3+8*n", 2, 8, 0));
        map.put(0xC4CB, new Z80Instruction(Z80OpCodes.SET, "SET n, H", 0xC4CB, "CB C4+8*n", 2, 8, 0));
        map.put(0xC5CB, new Z80Instruction(Z80OpCodes.SET, "SET n, L", 0xC5CB, "CB C5+8*n", 2, 8, 0));

        map.put(0x26CB, new Z80Instruction(Z80OpCodes.SLA, "SLA (HL)", 0x26CB, "CB 26", 2, 15, 0));
        map.put(0xCBDD2600, new Z80Instruction(Z80OpCodes.SLA, "SLA (IX+nn)", 0xCBDD2600, "DD CB nn 26", 4, 23, 0));
        map.put(0xCBFD2600, new Z80Instruction(Z80OpCodes.SLA, "SLA (IY+nn)", 0xCBFD2600, "FD CB nn 26", 4, 23, 0));
        map.put(0x27CB, new Z80Instruction(Z80OpCodes.SLA, "SLA A", 0x27CB, "CB 27", 2, 8, 0));
        map.put(0x20CB, new Z80Instruction(Z80OpCodes.SLA, "SLA B", 0x20CB, "CB 20", 2, 8, 0));
        map.put(0x21CB, new Z80Instruction(Z80OpCodes.SLA, "SLA C", 0x21CB, "CB 21", 2, 8, 0));
        map.put(0x22CB, new Z80Instruction(Z80OpCodes.SLA, "SLA D", 0x22CB, "CB 22", 2, 8, 0));
        map.put(0x23CB, new Z80Instruction(Z80OpCodes.SLA, "SLA E", 0x23CB, "CB 23", 2, 8, 0));
        map.put(0x24CB, new Z80Instruction(Z80OpCodes.SLA, "SLA H", 0x24CB, "CB 24", 2, 8, 0));
        map.put(0x25CB, new Z80Instruction(Z80OpCodes.SLA, "SLA L", 0x25CB, "CB 25", 2, 8, 0));

        map.put(0x2ECB, new Z80Instruction(Z80OpCodes.SRA, "SRA (HL)", 0x2ECB, "CB 2E", 2, 15, 0));
        map.put(0xCBDD2E00, new Z80Instruction(Z80OpCodes.SRA, "SRA (IX+nn)", 0xCBDD2E00, "DD CB nn 2E", 4, 23, 0));
        map.put(0xCBFD2E00, new Z80Instruction(Z80OpCodes.SRA, "SRA (IY+nn)", 0xCBFD2E00, "FD CB nn 2E", 4, 23, 0));
        map.put(0x2FCB, new Z80Instruction(Z80OpCodes.SRA, "SRA A", 0x2FCB, "CB 2F", 2, 8, 0));
        map.put(0x28CB, new Z80Instruction(Z80OpCodes.SRA, "SRA B", 0x28CB, "CB 28", 2, 8, 0));
        map.put(0x29CB, new Z80Instruction(Z80OpCodes.SRA, "SRA C", 0x29CB, "CB 29", 2, 8, 0));
        map.put(0x2ACB, new Z80Instruction(Z80OpCodes.SRA, "SRA D", 0x2ACB, "CB 2A", 2, 8, 0));
        map.put(0x2BCB, new Z80Instruction(Z80OpCodes.SRA, "SRA E", 0x2BCB, "CB 2B", 2, 8, 0));
        map.put(0x2CCB, new Z80Instruction(Z80OpCodes.SRA, "SRA H", 0x2CCB, "CB 2C", 2, 8, 0));
        map.put(0x2DCB, new Z80Instruction(Z80OpCodes.SRA, "SRA L", 0x2DCB, "CB 2D", 2, 8, 0));

        map.put(0x3ECB, new Z80Instruction(Z80OpCodes.SRL, "SRL (HL)", 0x3ECB, "CB 3E", 2, 15, 0));
        map.put(0xCBDD3E00, new Z80Instruction(Z80OpCodes.SRL, "SRL (IX+nn)", 0xCBDD3E00, "DD CB nn 3E", 4, 23, 0));
        map.put(0xCBFD3E00, new Z80Instruction(Z80OpCodes.SRL, "SRL (IY+nn)", 0xCBFD3E00, "FD CB nn 3E", 4, 23, 0));
        map.put(0x3FCB, new Z80Instruction(Z80OpCodes.SRL, "SRL A", 0x3FCB, "3FCB", 2, 8, 0));
        map.put(0x38CB, new Z80Instruction(Z80OpCodes.SRL, "SRL B", 0x38CB, "38CB", 2, 8, 0));
        map.put(0x39CB, new Z80Instruction(Z80OpCodes.SRL, "SRL C", 0x39CB, "39CB", 2, 8, 0));
        map.put(0x3ACB, new Z80Instruction(Z80OpCodes.SRL, "SRL D", 0x3ACB, "3ACB", 2, 8, 0));
        map.put(0x3BCB, new Z80Instruction(Z80OpCodes.SRL, "SRL E", 0x3BCB, "3BCB", 2, 8, 0));
        map.put(0x3CCB, new Z80Instruction(Z80OpCodes.SRL, "SRL H", 0x3CCB, "3CCB", 2, 8, 0));
        map.put(0x3DCB, new Z80Instruction(Z80OpCodes.SRL, "SRL L", 0x3DCB, "3DCB", 2, 8, 0));

        map.put(0x96, new Z80Instruction(Z80OpCodes.SUB, "SUB (HL)", 0x96, "96", 1, 7,0));
        map.put(0x96DD, new Z80Instruction(Z80OpCodes.SUB, "SUB (IX+nn)", 0x96DD, "DD 96 nn", 3, 19, 0));
        map.put(0x96FD, new Z80Instruction(Z80OpCodes.SUB, "SUB (IY+nn)", 0x96FD, "FD 96 nn", 3, 19, 0));
        map.put(0x97, new Z80Instruction(Z80OpCodes.SUB, "SUB A", 0x97, "97", 1, 4, 0));
        map.put(0x90, new Z80Instruction(Z80OpCodes.SUB, "SUB B", 0x90, "90", 1, 4, 0));
        map.put(0x91, new Z80Instruction(Z80OpCodes.SUB, "SUB C", 0x91, "91", 1, 4, 0));
        map.put(0x92, new Z80Instruction(Z80OpCodes.SUB, "SUB D", 0x92, "92", 1, 4, 0));
        map.put(0x93, new Z80Instruction(Z80OpCodes.SUB, "SUB E", 0x93, "93", 1, 4, 0));
        map.put(0x94, new Z80Instruction(Z80OpCodes.SUB, "SUB H", 0x94, "94", 1, 4, 0));
        map.put(0x95, new Z80Instruction(Z80OpCodes.SUB, "SUB L", 0x95, "95", 1, 4, 0));
        map.put(0x94DD, new Z80Instruction(Z80OpCodes.SUB, "SUB IXH", 0x94DD, "DD 94", 2, 0, 0));
        map.put(0x94FD, new Z80Instruction(Z80OpCodes.SUB, "SUB IYH", 0x94FD, "FD 94", 2, 0, 0));
        map.put(0x95DD, new Z80Instruction(Z80OpCodes.SUB, "SUB IXL", 0x95DD, "DD 95", 2, 0, 0));
        map.put(0x95FD, new Z80Instruction(Z80OpCodes.SUB, "SUB IYL", 0x95FD, "FD 95", 2, 0, 0));
        map.put(0xD6, new Z80Instruction(Z80OpCodes.SUB, "SUB nn", 0xD6, "D6 nn", 2, 7, 0));

        map.put(0xAE, new Z80Instruction(Z80OpCodes.XOR, "XOR (HL)", 0xAE, "AE", 1, 7, 0));
        map.put(0xAEDD, new Z80Instruction(Z80OpCodes.XOR, "XOR (IX+nn)", 0xAEDD, "DD AE nn", 3, 19, 0));
        map.put(0xAEFD, new Z80Instruction(Z80OpCodes.XOR, "XOR (IY+nn)", 0xAEFD, "FD AE nn", 3, 19, 0));
        map.put(0xAF, new Z80Instruction(Z80OpCodes.XOR, "XOR A", 0xAF, "AF", 1, 4, 0));
        map.put(0xA8, new Z80Instruction(Z80OpCodes.XOR, "XOR B", 0xA8, "A8", 1, 4, 0));
        map.put(0xA9, new Z80Instruction(Z80OpCodes.XOR, "XOR C", 0xA9, "A9", 1, 4, 0));
        map.put(0xAA, new Z80Instruction(Z80OpCodes.XOR, "XOR D", 0xAA, "AA", 1, 4, 0));
        map.put(0xAB, new Z80Instruction(Z80OpCodes.XOR, "XOR E", 0xAB, "AB", 1, 4, 0));
        map.put(0xAC, new Z80Instruction(Z80OpCodes.XOR, "XOR H", 0xAC, "AC", 1, 4, 0));
        map.put(0xAD, new Z80Instruction(Z80OpCodes.XOR, "XOR L", 0xAD, "AD", 1, 4, 0));
        map.put(0xACDD, new Z80Instruction(Z80OpCodes.XOR, "XOR IXH", 0xACDD, "DD AC", 2, 0, 0));
        map.put(0xACFD, new Z80Instruction(Z80OpCodes.XOR, "XOR IYH", 0xACFD, "FD AC", 2, 0, 0));
        map.put(0xADDD, new Z80Instruction(Z80OpCodes.XOR, "XOR IXL", 0xADDD, "DD AD", 2, 0, 0));
        map.put(0xADFD, new Z80Instruction(Z80OpCodes.XOR, "XOR IYL", 0xADFD, "FD AD", 2, 0, 0));
        map.put(0xEE, new Z80Instruction(Z80OpCodes.XOR, "XOR nn", 0xEE, "EE nn", 2, 7, 0));

    }
    
    public static Z80Instruction getInstruction(int hexCode) {
        return map.get(hexCode);
    }
    
    public static Iterator<Z80Instruction> getInstructionIterator() {
    	return map.values().iterator();
    }
    
    public Z80Instruction(Z80OpCodes opCode, String description, int hexCode, String hexString, int size, int oClock, int oClockUnmet) {
        super();
        this.opCode = opCode;
        this.description = description;
        this.hexCode = hexCode;
        this.hexString = hexString;
        this.size = size;
        this.oClock = oClock;
        //this.rClock = rClock;
        this.oClockUnmet = oClockUnmet;
        //this.rClockUnmet = rClockUnmet;
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


    public int getoClockUnmet() {
        return oClockUnmet;
    }  
    
}
