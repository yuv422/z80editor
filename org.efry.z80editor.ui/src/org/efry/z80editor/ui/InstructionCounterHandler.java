package org.efry.z80editor.ui;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
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
                            popUp(calc.getSingleLineTotals(), calc.getFormattedText());
                            System.out.println(calc.getSingleLineTotals());
                            System.out.println(calc.getFormattedText());
                            return Boolean.TRUE;
                        }
                    });
                     
                }
            }
            

        }
        return null;
    }

    private void popUp(final String title, final String description){
 
    	  new PopupDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),PopupDialog.INFOPOPUPRESIZE_SHELLSTYLE,true,false,false,false,false,title,null){
    	    private static final int CURSOR_SIZE=15;
    	    private  Label label = null;
    	    protected Point getInitialLocation(Point initialSize){
    	      Display display=getShell().getDisplay();
    	      Point location=display.getCursorLocation();
    	      location.x+=CURSOR_SIZE;
    	      location.y+=CURSOR_SIZE;
    	      return location;
    	    }
    	    
    	    protected Control createDialogArea(    Composite parent){
    	      Font terminalFont = JFaceResources.getFont(JFaceResources.TEXT_FONT);
    	      label=new Label(parent,SWT.WRAP);
    	      label.setText(description);
    	      label.setFont(terminalFont);
    	      /*
    	      label.addFocusListener(new FocusAdapter(){
    	        public void focusLost(        FocusEvent event){
    	        	System.out.println("focus lost.");
    	          close();
    	        }
    	        @Override
    	        public void focusGained(FocusEvent e) {
    	        	super.focusGained(e);
    	        	System.out.println("focus gained.");
    	        }
    	      }
    	);
    	      label.addKeyListener(new KeyAdapter() {
    	    	  public void keyPressed(KeyEvent e) {
    	    		  close();
    	    	  }
			});*/
    	      GridData gd=new GridData(GridData.BEGINNING | GridData.FILL_BOTH);
    	      gd.horizontalIndent=PopupDialog.POPUP_HORIZONTALSPACING;
    	      gd.verticalIndent=PopupDialog.POPUP_VERTICALSPACING;
    	      label.setLayoutData(gd);
    	      return label;
    	    }
    	  }
    	.open();

    	}

}
