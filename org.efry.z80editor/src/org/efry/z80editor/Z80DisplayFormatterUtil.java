package org.efry.z80editor;

import org.efry.z80editor.z80.NUMBER;

public final class Z80DisplayFormatterUtil {

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
