package org.efry.z80editor.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.documentation.IEObjectDocumentationProvider;
import org.efry.z80editor.z80.Nop;

public class z80EObjectDocumentationProvider implements
		IEObjectDocumentationProvider {

	@Override
	public String getDocumentation(EObject o) {
		if(o instanceof Nop) {
			return "Ok here.";
		}
		return null;
	}

}
