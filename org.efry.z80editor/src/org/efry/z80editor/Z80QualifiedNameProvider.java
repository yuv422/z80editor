package org.efry.z80editor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.efry.z80editor.z80.VarName;

public class Z80QualifiedNameProvider extends IQualifiedNameProvider.AbstractImpl {

	@Override
	public QualifiedName getFullyQualifiedName(EObject obj) {
		if(obj instanceof VarName) {
			return QualifiedName.create(((VarName) obj).getName());
		}

		return null;
	}

}
