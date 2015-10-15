package org.efry.z80editor.tests;


import static org.junit.Assert.fail;

import java.util.Iterator;

import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.efry.z80editor.Z80CycleCalculator;
import org.efry.z80editor.Z80InjectorProvider;
import org.efry.z80editor.Z80Instruction;
import org.efry.z80editor.z80.Z80Model;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;

@InjectWith(Z80InjectorProvider.class)
@RunWith(XtextRunner.class)
public class CycleCountTests {

	  @Inject
	  private ParseHelper<Z80Model> parser;
	  
	  @Inject
	  private ValidationTestHelper validationHelper;

	  
	  private String formatInstructionDescription(String d) {
		  d = d.replace("nnnn", "$beef");
		  d = d.replace("nn", "$be");
		  d = d.replace("n", "5").toLowerCase();
		  return d;
	  }

	  @Test
	  public void testParseAllInstructions() throws Exception {
		  Iterator<Z80Instruction> iterator = Z80Instruction.getInstructionIterator();
		  while(iterator.hasNext()) {
			  Z80Instruction i = iterator.next();
			  String command = formatInstructionDescription(i.getDescription());
			  final Z80Model model = this.parser.parse("\n"+command+"\n");
			  Assert.assertNotNull(model);

			  try {
				  validationHelper.assertNoErrors(model);
			  } catch(AssertionError e) {
				  fail("Parse error: \"" + command + "\" - " + e.getMessage());
			  }
		  }
	  }

	  @Test
	  public void testAllInstructionCycles() throws Exception {
		  Iterator<Z80Instruction> iterator = Z80Instruction.getInstructionIterator();
		  while(iterator.hasNext()) {
			  Z80Instruction i = iterator.next();
			  String command = formatInstructionDescription(i.getDescription());
			  final Z80Model model = this.parser.parse("\n"+command+"\n");
			  Assert.assertNotNull(model);

			  int cycles = Z80CycleCalculator.calculateOClockCyclesForModel(model);
			  System.out.println(command + "; Cycles: " + cycles + " Expected: " + i.getoClock());
			  Assert.assertEquals(i.getoClock(), cycles);
		  }
	  }
}
