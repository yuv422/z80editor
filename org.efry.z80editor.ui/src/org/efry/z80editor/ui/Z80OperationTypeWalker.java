package org.efry.z80editor.ui;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.efry.z80editor.z80.Operation;

public class Z80OperationTypeWalker implements Iterable<Operation> {
	
	private final XtextResource resource;
	private int startOffset = 0;
	private int endOffset = -1;
	private ILocationInFileProvider locationInFileProvider;
	
	public Z80OperationTypeWalker(XtextResource resource) {
		this.resource = resource;
	}

	public Z80OperationTypeWalker(XtextResource resource, ILocationInFileProvider l, int startOffset, int endOffset) {
		this.resource = resource;
		this.locationInFileProvider = l;
		this.startOffset = startOffset;
		this.endOffset = endOffset;
	}

	@Override
	public Iterator<Operation> iterator() {
		return new OperationSelectionIterator(resource, locationInFileProvider, startOffset, endOffset);
	}

	public class OperationSelectionIterator implements Iterator<Operation> {

		private ILocationInFileProvider locationInFileProvider;
		
		private final XtextResource resource;
		private int startOffset = 0;
		private int endOffset = -1;
		
		private Operation nextOp = null;
		private Boolean hasAnotherOp = null;
		
		public OperationSelectionIterator(XtextResource resource, ILocationInFileProvider locationInFileProvider, int startOffset, int endOffset) {
			this.resource = resource;
			this.locationInFileProvider = locationInFileProvider;
			this.startOffset = startOffset;
			this.endOffset = endOffset;
		}
		
		@Override
		public boolean hasNext() {
			if(hasAnotherOp == null) {
				fetchNext();
			}
			
			return hasAnotherOp;
		}

		@Override
		public Operation next() {
			if(hasNext()) {
				hasAnotherOp = null;
				return nextOp;
			}
			return null;
		}

		@Override
		public void remove() {
			
		}

		private void fetchNext() {
			hasAnotherOp = false;
			EObject obj;
        	IParseResult parseResult = resource.getParseResult();
    		if (parseResult != null) {
    			ILeafNode leaf = null;

    			do {
    				
    				leaf = NodeModelUtils.findLeafNodeAtOffset(parseResult.getRootNode(), startOffset);
        			if (leaf != null) {
        				
        				if(!leaf.isHidden()) {
            				obj = NodeModelUtils.findActualSemanticObjectFor(leaf);
            				
            				if(obj != null && Operation.class.isAssignableFrom(obj.getClass())) {
            					startOffset += locationInFileProvider.getFullTextRegion(obj).getLength();
            					nextOp = (Operation)obj;
            					hasAnotherOp = true;
            					return;
            				}
        				}
        				
        				startOffset += leaf.getText().length();
        			}
        			if(endOffset != -1 && startOffset > endOffset) {
        				return;
        			}
    			} while(leaf != null);
    		}
		}
	}
}
