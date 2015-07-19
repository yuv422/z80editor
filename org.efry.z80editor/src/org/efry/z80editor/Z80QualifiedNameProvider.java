package org.efry.z80editor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.efry.z80editor.z80.VarName;

public class Z80QualifiedNameProvider extends IQualifiedNameProvider.AbstractImpl {

	@Override
	public QualifiedName getFullyQualifiedName(EObject obj) {
		if(obj instanceof VarName) {
			String name = ((VarName) obj).getName();
			if(name != null) { //FIXME need to fix locally scoped labels. Make everything global for now. // && name.charAt(0) != '_') { //labels that start with an underscore are locally scoped so they should be ignored here.
				return QualifiedName.create(name);
			}
		}

		return null;
	}

}
