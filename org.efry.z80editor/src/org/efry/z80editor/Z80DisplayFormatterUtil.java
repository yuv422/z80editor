package org.efry.z80editor;

import org.efry.z80editor.z80.NUMBER;
import org.efry.z80editor.z80.NumericLiteral;

public final class Z80DisplayFormatterUtil {

    public static Integer convertNumericLiteralToInt(NumericLiteral n) {
        if(n == null) {
            return null;
        }
        if(n instanceof NUMBER) {
            if(((NUMBER)n).getStr() != null) {
                String s = ((NUMBER)n).getStr();
                if(s.startsWith("$")) {
                    return Integer.valueOf(s.substring(1), 16);
                } else if(s.startsWith("%")) {
                    return Integer.valueOf(s.substring(1), 2);
                }
            } else {
                return Integer.valueOf(((NUMBER)n).getI());
            }
        }
        
        return null;
    }
    public static String convertNumericLiteralToString(NumericLiteral n) {
        if(n == null) {
            return null;
        }
        if(n instanceof NUMBER) {
            return convertNUMBERToString((NUMBER)n); 
        } 

        return null;
    }
    
    public static String convertNUMBERToString(NUMBER n) {
        if(n == null) {
            return null;
        }
        
        if(n.getStr() != null) {
            return n.getStr();
        }
        
        return Integer.toString(n.getI());
    }
}
