/*
 * generated by Xtext
 */
package org.efry.z80editor.ui.labeling

import com.google.inject.Inject
import org.efry.z80editor.z80.Section
import org.efry.z80editor.z80.Define
import org.efry.z80editor.z80.LabelType;
import org.efry.z80editor.z80.VarDef;

/**
 * Provides labels for EObjects.
 * 
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#label-provider
 */
class Z80LabelProvider extends org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider {

	@Inject
	new(org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	// Labels and icons can be computed like this:
	
//	def text(Greeting ele) {
//		'A greeting to ' + ele.name
//	}
//
//	def image(Greeting ele) {
//		'Greeting.gif'
//	}

	def image(Section ele) {
		's.png'
	}

	def image(Define ele) {
		'define.gif'
	}
	
	def text(Define ele) {
		ele.name.name
	}
	
	def text(VarDef ele) {
		ele.varName.name
	}
	
	def text(LabelType ele) {
		ele.varName.name
	}

	def image(LabelType ele) {
		'public_co.gif'
	}
		
}
