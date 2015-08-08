package org.efry.z80editor.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.folding.DefaultFoldingRegionProvider;
import org.efry.z80editor.z80.EmptyLine;


public class z80FoldingRegionProvider extends DefaultFoldingRegionProvider {
/*
	@Override
	protected boolean isHandled(EObject eObject) {
		if(eObject instanceof EmptyLine) {
			return false;
		}
		
		return super.isHandled(eObject);
	}
	*/
}
