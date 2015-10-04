package org.efry.z80editor;

import org.efry.z80editor.z80.LabelType;
import org.efry.z80editor.z80.NUMBER;
import org.efry.z80editor.z80.NumericLiteral;
import org.efry.z80editor.z80.VarName;

public final class Z80DisplayFormatterUtil {

    public static String convertNumericLiteralToString(NumericLiteral n) {
        if(n == null) {
            return null;
        }
        if(n instanceof NUMBER) {
            return convertNUMBERToString((NUMBER)n); 
        } else if(n.getReferencedObj() != null && n.getReferencedObj() instanceof VarName) {
            VarName label = (VarName)n.getReferencedObj();
            return label.getName();
            
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
