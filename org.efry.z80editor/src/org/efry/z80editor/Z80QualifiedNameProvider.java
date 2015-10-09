package org.efry.z80editor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.efry.z80editor.z80.LabelType;
import org.efry.z80editor.z80.Macro;
import org.efry.z80editor.z80.VarName;

public class Z80QualifiedNameProvider extends IQualifiedNameProvider.AbstractImpl {

	@Override
	public QualifiedName getFullyQualifiedName(EObject obj) {
		if(obj instanceof VarName) {
//			String name = ((VarName) obj).getName();
//			if(name != null) { //FIXME need to fix locally scoped labels. Make everything global for now. // && name.charAt(0) != '_') { //labels that start with an underscore are locally scoped so they should be ignored here.
//				return QualifiedName.create(name);
//			}
		} else if(obj instanceof Macro) {
          String name = ((Macro) obj).getName();
            if(name != null) {
                return QualifiedName.create(name);
            }
        }/* else if (obj instanceof Struct) {
            String name = ((StructType) obj).getName();
            if (name != null) {
                return QualifiedName.create(name);
            }
        }*/ else if (obj instanceof LabelType) {
            if(obj.eContainer() != null && obj.eContainer() instanceof Macro) {
                return null;
            }
            String name = null;
            if(((LabelType) obj).getVarName() != null) {
                name = ((LabelType) obj).getVarName().getName();
            } else if(((LabelType) obj).getName() != null) {
                name = ((LabelType) obj).getName();
            }
            if (name != null) {
                return QualifiedName.create(name);
            }
        }
		return null;
	}

}
