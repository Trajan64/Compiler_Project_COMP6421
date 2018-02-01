# Description #

The directory `./COMP6421_Assign4` contains a project that can be directly imported into Eclipse.
To run the program, the file Assign4Main.java should be launched as a Java Application.

# Usage #

A run configuration will need to be set in order to supply appropriate arguments to the program.
The different possible arguments are: 

1. `-run <source program>;`

Compile a program and output everything onto the console.
	
2. `-run <source program> <derivation output file> <symbol output file> <error output file> <code output file> <token output file>`

Compile a program and output derivations, symbol table, error messages, generated code and tokens to separate files.
Example: 
	`-run ./test/programs/codegen_recursion.txt ./results/codegen_recursion_derivations.txt ./results/codegen_recursion_symboltable ./results/codegen_recursion_errors.txt ./results/codegen_recursion_code.m ./results/professor_sample_tokens.txt`
	
3. `-test`

Parse all the files within the directory `./test/programs/` and output derivations, symbol table, possible errors, 		   generated codee and tokens  into the directory `./test/results/`

# Notes #

Sample test programs can be found under the directory `./test/programs/`

IMPORTANT: To run the generated code, the moon executable must be called with the path of the generated code plus the path to the library "io.m" which can be found at the root directory of this project.
Example (with a command line interface opened and the current directory set as the root of this project): `moon.o ./test/results/codegen_recursion/code.m io.m`
