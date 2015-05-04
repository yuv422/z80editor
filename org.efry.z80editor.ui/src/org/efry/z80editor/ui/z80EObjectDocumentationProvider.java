package org.efry.z80editor.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.efry.z80editor.z80.Operation;

import com.google.common.io.CharStreams;

public class z80EObjectDocumentationProvider implements
		IEObjectDocumentationProvider {

	@Override
	public String getDocumentation(EObject o) {
		if(o instanceof Operation) {
			String opCode = ((Operation)o).getOpcode();
			if("nop".equalsIgnoreCase(opCode)) {
				return nopCommandDesc();
			} else if("jp".equalsIgnoreCase(opCode)) {
				return "JP: Jump";
			} else if("adc".equalsIgnoreCase(opCode)) {
				InputStream in = this.getClass().getClassLoader().getResourceAsStream("resources/z80opCodes/adc.txt");
				InputStreamReader r = new InputStreamReader(in);
				try {
					String desc = CharStreams.toString(r);
					r.close();
					in.close();
					return desc;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			} else {
				return loadHtmlDescription(opCode);
			}
		}
		
		return null;
	}

	private String nopCommandDesc() {
		return "<b>Opcode:</b> 00<br><b>Size (In Bytes):</b> 1<br><b>Time (Clock Cycles)</b>: 4";
	}
	
	private String getStyleSheet() {
		return "<style> table { border-collapse: collapse; } table, td, th { border: 1px solid black; } </style>";
	}
	
	private String loadHtmlDescription(String opCode) {
		if(opCode != null) {
			opCode = opCode.toLowerCase();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("resources/z80opCodes/"+opCode+".html");
			if(in != null) {
				InputStreamReader r = new InputStreamReader(in);
				try {
					String desc = CharStreams.toString(r);
					r.close();
					in.close();
					
					return getStyleSheet() + desc + "<br><p>Instruction set info sourced from <a href=\"http://z80-heaven.wikidot.com/instructions-set\">Z80-heaven</a></p><br>";
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}
}
