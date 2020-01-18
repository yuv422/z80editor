package org.efry.z80editor.ui.view;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.ITextRegion;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.efry.z80editor.Z80CycleCalculator;
import org.efry.z80editor.Z80Instruction;
import org.efry.z80editor.Z80OpCodes;
import org.efry.z80editor.ui.Z80OperationTypeWalker;
import org.efry.z80editor.ui.internal.Z80editorActivator;
import org.efry.z80editor.z80.Operation;

import com.google.common.io.CharStreams;

public class Z80InstructionHelpView extends ViewPart {

    private Browser label;

    private ISelectionListener listener = new ISelectionListener() {

        @Override
        public void selectionChanged(IWorkbenchPart part, ISelection sel) {
            if (sel instanceof ITextSelection) {
                getInstructionFromSelection((ITextSelection) sel);
            }
        }
    };

    @Override
    public void createPartControl(Composite parent) {
        label = new Browser(parent, SWT.NONE);
        label.setText("");
        getSite().getPage().addPostSelectionListener(listener);
    }

    @Override
    public void setFocus() {
        label.setFocus();
    }

    @Override
    public void dispose() {
        getSite().getPage().removePostSelectionListener(listener);
    }

    private Z80Instruction getInstructionFromSelection(ITextSelection sel) {
        IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (part instanceof XtextEditor) {
            final XtextEditor editor = (XtextEditor) part;
            IDocumentProvider prov = editor.getDocumentProvider();
            final IDocument doc = prov.getDocument(editor.getEditorInput());

            if (sel instanceof TextSelection) {
                ITextSelection textSel = (ITextSelection) sel;
                int line = textSel.getStartLine();
                int off = textSel.getOffset();
                int len = textSel.getLength();
                if (len == 0) {
                    try {
                        off = doc.getLineOffset(line);
                        len = doc.getLineLength(line);
                        loadHtmlOpcodeInfo(off, len, editor);
                    } catch (BadLocationException e) {
                        throw new RuntimeException("Invalid line number: " + line, e);
                    }
                } else if (editor instanceof XtextEditor) {
                    loadCycleInfo(off, len, editor, doc);

                }

            }

        }
        return null;
    }

    private void loadCycleInfo(final int offset, final int length, XtextEditor editor, final IDocument doc) {
        final ILocationInFileProvider locationInFileProvider = Z80editorActivator.getInstance().getInjector(Z80editorActivator.ORG_EFRY_Z80EDITOR_Z80).getInstance(ILocationInFileProvider.class);

        editor.getDocument().readOnly(new IUnitOfWork<Boolean, XtextResource>() {

            @Override
            public Boolean exec(XtextResource state) throws Exception {
                Z80OperationTypeWalker operationInstructions = new Z80OperationTypeWalker(state, locationInFileProvider, offset, offset + length);

                Z80CycleCalculator calc = new Z80CycleCalculator();

                for (Operation o : operationInstructions) {
                    ITextRegion region = locationInFileProvider.getFullTextRegion(o);
                    calc.addOperation(o, doc.get(region.getOffset(), region.getLength()));
                }
                label.setText(calc.getHtmlFormattedText());
                return Boolean.TRUE;
            }
        });
    }

    private void loadHtmlOpcodeInfo(final int offset, final int length, XtextEditor editor) {
        final ILocationInFileProvider locationInFileProvider = Z80editorActivator.getInstance().getInjector(Z80editorActivator.ORG_EFRY_Z80EDITOR_Z80).getInstance(ILocationInFileProvider.class);

        editor.getDocument().readOnly(new IUnitOfWork<Boolean, XtextResource>() {

            @Override
            public Boolean exec(XtextResource state) throws Exception {
                Z80OperationTypeWalker operationInstructions = new Z80OperationTypeWalker(state, locationInFileProvider, offset, offset + length);

                Iterator<Operation> itr = operationInstructions.iterator();
                if (itr.hasNext()) {
                    Operation o = itr.next();

                    InputStream in = this.getClass().getClassLoader().getResourceAsStream("resources/z80opCodes/" + o.getOpcode() + ".html");
                    if (in != null) {
                        InputStreamReader r = new InputStreamReader(in);
                        try {
                            String desc = CharStreams.toString(r);
                            r.close();
                            in.close();
                            label.setText("<h2>" + Z80OpCodes.valueOf(o).name() + ":&nbsp;" + Z80OpCodes.valueOf(o).getDescription() + "</h2>" + desc);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                return Boolean.TRUE;
            }
        });
    }
}
