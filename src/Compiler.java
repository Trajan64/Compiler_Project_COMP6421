import java.io.InputStream;
import java.io.OutputStream;


public class Compiler {


	private SyntacticAnalyzer 	m_syntacticAnalyzer;
	private LexicalAnalyzer 	m_lexicalAnalyzer;
	private SemanticAnalyzer 	m_semanticAnalyzer;
	private CodeGenerator		m_codeGenerator;
	
	
	public Compiler(InputStream program, OutputStream symbolOutput, OutputStream derivationOutput, OutputStream codeOutput, OutputStream errorOutput, OutputStream tokenOutput, String programName) {
	
		// Setup.
		m_lexicalAnalyzer = new LexicalAnalyzer(program, errorOutput, tokenOutput, programName);
		m_syntacticAnalyzer = new SyntacticAnalyzer(symbolOutput, derivationOutput, errorOutput);
		m_semanticAnalyzer = new SemanticAnalyzer(symbolOutput, errorOutput);
		m_codeGenerator = new CodeGenerator(codeOutput, m_semanticAnalyzer);
		
		m_syntacticAnalyzer.setLexicalAnalyzer(m_lexicalAnalyzer);
		m_syntacticAnalyzer.setSemanticAnalyzer(m_semanticAnalyzer);
		m_syntacticAnalyzer.setCodeGenerator(m_codeGenerator);
	}
		
	
	public void compile() {
				
		boolean parsedFully;
		
		System.out.println("\tFirst pass beginning..");
		
		// Start first pass.
		m_syntacticAnalyzer.parse();
				
		// Rewind and lock outputs for second pass.
		m_syntacticAnalyzer.lockOutput();
		m_lexicalAnalyzer.rewindProgram();
		m_lexicalAnalyzer.lockOutput();
		
		m_semanticAnalyzer.setPhaseTwo();
		
		System.out.println("\tSecond pass beginning..");
		
		// Start second pass.
		parsedFully = m_syntacticAnalyzer.parse();
		
		//	// Sizing generation.
		if (!m_semanticAnalyzer.uknownTypesEncountered()) {
			m_semanticAnalyzer.generateSizes();
			m_semanticAnalyzer.generateAddresses();
		}

		// Print the table to file.
		m_semanticAnalyzer.printSymbolTables();

		
		int lexicalErrors = m_lexicalAnalyzer.getErrorCount();
		int syntacticErrors = m_syntacticAnalyzer.getErrorCount();
		int semanticErrors = m_semanticAnalyzer.getErrorCount();
		int totalErrors = lexicalErrors + syntacticErrors + semanticErrors;
		
		// Output error notifications.
		if (totalErrors == 0) {
			System.out.println("\tNo errors encountered.");
		}
		else {
			System.out.println( "\tError(s): "+ lexicalErrors + " lexical error(s); " + syntacticErrors + " syntactic error(s); " + semanticErrors + " semantic error(s).");
		}
		
		if (parsedFully == false) {
			System.out.println("\tError(s) prevented the syntactic analyzer to parse the source file fully.");
		}
		
		if (totalErrors != 0) {
			System.out.println( "\tCannot proceed to code generation as error(s) exist in source file.");
			return;
		}
		
		
		// Start code generation.
		System.out.println("\tGenerating code..");
		
		m_lexicalAnalyzer.rewindProgram();
		m_codeGenerator.enable();
		m_semanticAnalyzer.resetVisits();
		m_semanticAnalyzer.resetScope();
		m_semanticAnalyzer.disable();

		m_syntacticAnalyzer.parse();
		
				
	}
	
	
}
