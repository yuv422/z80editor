# z80editor
A Z80 eclipse editor plugin targeting the WLA-DX assembler

Some features of the editor

* code complete for labels/defines
* z80 instruction reference info shown when hovering mouse over commands.
* z80 instruction reference info displayed while you type. (In the Z80 Instruction View)
* z80 instruction cycle/byte counting
* as you type syntax validation
* syntax highlighting
* code folding
* jump to label/define definition
* label quick reference outline view
* plus lots of other standard eclipse IDE functions. :)

![Z80 Editor Screenshot](https://raw.githubusercontent.com/yuv422/z80editor/master/z80editor.png)

### Eclipse Update Site

To install from Eclipse navigate to the menu

  --> Help --> Install New Software... --> Add...

Enter the update site details

https://dl.bintray.com/yuv422/EclipseZ80Editor 

### Building from source

You can compile with maven. `mvn package` should result in an update site zip being created in the repository project.
