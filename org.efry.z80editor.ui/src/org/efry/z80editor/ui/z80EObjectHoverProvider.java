package org.efry.z80editor.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.hover.html.DefaultEObjectHoverProvider;
import org.eclipse.xtext.ui.editor.utils.EditorUtils;
import org.eclipse.xtext.util.ITextRegion;
import org.efry.z80editor.Z80DisplayFormatterUtil;
import org.efry.z80editor.Z80OpCodes;
import org.efry.z80editor.z80.Define;
import org.efry.z80editor.z80.EnumCmd;
import org.efry.z80editor.z80.Operation;
import org.efry.z80editor.z80.VarDef;
import org.efry.z80editor.z80.VarName;

import com.google.inject.Inject;

public class z80EObjectHoverProvider extends DefaultEObjectHoverProvider {

    @Inject
    ILocationInFileProvider locationInFileProvider;
    
    @Override
    protected String getFirstLine(EObject o) {
        if (o instanceof Operation) {
            String opCode = ((Operation) o).getOpcode();
            if (opCode != null) {
                try {
                    Z80OpCodes opEnum = Z80OpCodes.valueOf(opCode.toUpperCase());
                    return opEnum.toString() + ": " + opEnum.getDescription();
                } catch (IllegalArgumentException e) {
                    return "Unknown opcode";
                }
            }
        } else if (o instanceof VarName) {

            if (o.eContainer() != null) {
                System.out.println(o.eContainer().toString());
                if(o.eContainer() instanceof Define) {
                    Define def = (Define) o.eContainer();
                    ICompositeNode node = NodeModelUtils.getNode(def.getExpr());
                    
                    if(node != null) {
                        return def.getName().getName() + ": " + node.getText();
    
                    }
                } else if(o.eContainer() instanceof VarDef) {
                    VarDef def = (VarDef)o.eContainer();
                    String parent = "";
                    if (def.eContainer() != null) {
                        System.out.println(def.eContainer().toString());
                        if(def.eContainer() instanceof EnumCmd) {
                            //FIXME parent = "enum " + Z80DisplayFormatterUtil.convertNUMBERToString(((EnumCmd)def.eContainer()).getStartAddress()) + "\n...\n";
                        }
                    }
                    ICompositeNode node = NodeModelUtils.getNode(def);
                    
                    if(node != null) {
                        return parent + node.getText();
    
                    }
                }
            }
            return o.toString();
        }

        return super.getFirstLine(o);
    }

    @Override
    protected boolean hasHover(EObject o) {
        if (o instanceof Operation) {
            return true;
        }

        if (o instanceof VarName) {
            return true;
        }

        return super.hasHover(o);
    }

}
