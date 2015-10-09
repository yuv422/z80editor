package org.efry.z80editor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.efry.z80editor.z80.LabelType;
import org.efry.z80editor.z80.Macro;

public class Z80QualifiedNameProvider extends IQualifiedNameProvider.AbstractImpl {

	@Override
	public QualifiedName getFullyQualifiedName(EObject obj) {
		if(obj instanceof Macro) {
          String name = ((Macro) obj).getName();
            if(name != null) {
                return QualifiedName.create(name);
            }
        } else if (obj instanceof LabelType) {
            if(obj.eContainer() != null && obj.eContainer() instanceof Macro) {
                return null;
            }
            String name = ((LabelType) obj).getName();
            if (name != null) {
                return QualifiedName.create(name);
            }
        }
		return null;
	}

}
