package org.efry.z80editor.tests

import org.efry.z80editor.tests.Z80InjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.junit.runner.RunWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import com.google.inject.Inject
import org.efry.z80editor.z80.Z80Model
import org.junit.Test

import static org.junit.Assert.*
import org.eclipse.xtext.testing.validation.ValidationTestHelper

@InjectWith(Z80InjectorProvider)
@RunWith(XtextRunner)
class Z80ParserTests {

@Inject
  ParseHelper<Z80Model> parser
@Inject
  ValidationTestHelper validationHelper
    
  @Test
	def void parseIndirectOffsetRangeValidation() {
		val model = parser.parse('
			or (ix + 127)
        ')
        assertNotNull(model)
        validationHelper.assertNoErrors(model)
        
	}
}