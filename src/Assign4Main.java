
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class Assign4Main {

	
	
	private static OutputStream setupOutput(String outputLocation, String type) {
		
		File file = new File(outputLocation);;
		OutputStream output = null;
		
		
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			System.out.println("\t" + type + " will be stored in file " + file.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			output = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return output;
		
	}
	
	
	private static void run(String programLocation, String derivationOutputLocation, String symbolsOutputLocation, String errorOutputLocation, String codeOutputLocation, String tokenOutputLocation) {
	
		
		FileInputStream program = null;
		try { program = new FileInputStream(programLocation); } 
		catch (FileNotFoundException e) { 
			System.err.println("Error: program file '" + programLocation + "' cannot be found.");
			return;
		}
		
		System.out.println(programLocation + ':');
		OutputStream symbolsOutput = null;
		OutputStream derivationsOutput = null;
		OutputStream errorOutput = null;
		OutputStream codeOutput = null;
		OutputStream tokenOutput = null;
		
		// If no locations were provided for the two outputs then this indicate that we must use the standard outputs.
		if (symbolsOutputLocation == null && derivationOutputLocation == null && errorOutputLocation == null && codeOutputLocation == null && tokenOutputLocation == null) {
			
			symbolsOutput = System.out;
			derivationsOutput = System.out;
			errorOutput = System.err;
			codeOutput = System.out;
			tokenOutput = System.out;
			
		}
		
		else {
			
			
			symbolsOutput = setupOutput(symbolsOutputLocation, "Symbol tables");
			
			// Creates outputs files as well as sub-directories if necessary.

			derivationsOutput = setupOutput(derivationOutputLocation, "Derivations");
			errorOutput = setupOutput(errorOutputLocation, "Errors");
			codeOutput = setupOutput(codeOutputLocation, "Generated code");
			tokenOutput = setupOutput(tokenOutputLocation, "Tokens");
		}
		
		Compiler compiler = new Compiler(program, symbolsOutput, derivationsOutput, errorOutput, codeOutput, tokenOutput, programLocation);
		compiler.compile();
		
	}
	
	public static void main(String[] args) {

		// option 1: -run <program>
		// option 2: -run <program> <symbolsOutput> <errorOutput>
		// option 3: -test
		// option 4: -help
		
		if (args.length < 1) {
			System.err.println("Error: not enough command line arguments supplied to the program.");
			System.err.println("Type in -help for more informations.");
			
			return;
		}
				
		
		if (args[0].equals("-run")) {
			
			if (args.length == 2 || args.length == 5) {
			
				if (args.length == 2) {
					
					// Output everything onto the console.
					run(args[1], null, null, null, null, null);
					
				}
				
				else {
					
					run(args[1], args[2], args[3], args[4], args[5], args[6]);
				}
				
				return;
			}
			
			else {
				
				System.err.println("Error: invalid arguments.");
				System.err.println("Type in -help for more informations.");
				
			}
			
			return;
		}
		
		if (args[0].equals("-test")) {
			
			File testDir = new File("./test/programs/");
			
			File[] programs = testDir.listFiles();
			
			if (programs == null) {
				System.err.println("Error: no programs found in test directory.");
				return;
			}
			
			int i;
			for (i = 0; i < programs.length; i++) {
				
				File programFile = programs[i];
				String programFileName = programFile.getName();
				String programBaseName = programFileName.substring(0,  programFileName.lastIndexOf("."));
								
				run(programFile.getPath(), "./test/results/" + programBaseName + "/derivations.txt", "./test/results/" + programBaseName + "/symbols.txt", "./test/results/" + programBaseName + "/code.m", "./test/results/" + programBaseName + "/errors.txt", "./test/results/" + programBaseName + "/tokens.txt");				
				
			}
			
			return;
			
		}
		
		
		if (args[0].equals("-help")) {
			
			System.out.println("Possible commands:");
			System.out.println("\t-run <source program>");
			System.out.println("\t\tCompile a program and output everything onto the console.");		
			System.out.println("\t-run <source program> <derivations output file> <symbols output file> <error output file> <code output file> <token output file>");
			System.out.println("\t\tCompile a program and output symbols, derivations, token, code and error messages to seperate files.");
			System.out.println("\t-test");
			System.out.println("\t\tCompile all the files within the directory './test/programs/' and output the symbols, derivations, tokens, code and possible errors into the directory './test/results/'");
			System.out.println("\t-help");
			System.out.println("\t\tDisplay this message.");
			System.out.println("");
			System.out.println("\tNOTE: sample test programs can be found under the directory './test/programs/'");
			
			return;
		}
		
			
		System.err.println("Error: unrecognized command line option.");
		System.err.println("Type in -help for more informations.");		
		
		
	}


}
