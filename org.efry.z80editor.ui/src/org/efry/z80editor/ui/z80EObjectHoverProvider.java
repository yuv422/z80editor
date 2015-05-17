package org.efry.z80editor.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.hover.html.DefaultEObjectHoverProvider;
import org.efry.z80editor.Z80OpCodes;
import org.efry.z80editor.z80.Operation;


public class z80EObjectHoverProvider extends DefaultEObjectHoverProvider {

	@Override
	protected String getFirstLine(EObject o) {
		if(o instanceof Operation) {
			String opCode = ((Operation)o).getOpcode();
			if(opCode != null) {
				try {
				Z80OpCodes opEnum = Z80OpCodes.valueOf(opCode.toUpperCase());
				return opEnum.toString() + ": " + opEnum.getDescription();
				} catch (IllegalArgumentException e) {
					return "Unknown opcode";
				}
			}
		}

		return super.getFirstLine(o);
	}
	
	@Override
	protected boolean hasHover(EObject o) {
		if(o instanceof Operation) {
			return true;
		}

		return super.hasHover(o);
	}
}
