package org.efry.z80editor.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.hover.html.DefaultEObjectHoverProvider;
import org.efry.z80editor.z80.Jp;
import org.efry.z80editor.z80.Nop;


public class z80EObjectHoverProvider extends DefaultEObjectHoverProvider {

	@Override
	protected String getFirstLine(EObject o) {
		if(o instanceof Nop) {
			return "NOP: No Operation";
		}

		if(o instanceof Jp) {
			return "JP: jump";
		}
		
		return "Hello Hover." + o.toString();
	}
}
