package org.efry.z80editor.ui;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.efry.z80editor.Z80CycleCalculator;
import org.efry.z80editor.z80.Operation;

import com.google.inject.Inject;

public class InstructionCounterHandler extends AbstractHandler {

    @Inject
    ILocationInFileProvider locationInFileProvider;
    
    @Override
    public Object execute(ExecutionEvent arg0) throws ExecutionException {

        IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (part instanceof ITextEditor)
        {
            final ITextEditor editor = (ITextEditor) part;
            IDocumentProvider prov = editor.getDocumentProvider();
            final IDocument doc = prov.getDocument(editor.getEditorInput());
            ISelection sel = editor.getSelectionProvider().getSelection();
            if (sel instanceof TextSelection) {
                ITextSelection textSel = (ITextSelection) sel;

                final int offset = textSel.getOffset();
                final int length = textSel.getLength();
                
                if (part instanceof XtextEditor) {
                    ((XtextEditor)part).getDocument().readOnly(new IUnitOfWork<Boolean, XtextResource>() {
                     
                        @Override
                        public Boolean exec(XtextResource state)
                                throws Exception {
                            Z80CycleCalculator calc = new Z80CycleCalculator();
                            Z80OperationTypeWalker operationInstructions = new Z80OperationTypeWalker(state, locationInFileProvider, offset, offset + length);
                            
                            calc = new Z80CycleCalculator();
                            for(Operation o : operationInstructions) {
                            	ITextRegion region = locationInFileProvider.getFullTextRegion(o);
                                calc.addOperation(o, doc.get(region.getOffset(), region.getLength()));
                            }
                            System.out.println(calc.getFormattedText());
                            System.out.println(calc.getSingleLineTotals());
                            return Boolean.TRUE;
                        }
                    });
                     
                }
            }
            

        }
        return null;
    }



}
