import java.io.IOException;
import java.io.OutputStream;


public class SyntacticAnalyzer {

	
	private LexicalAnalyzer m_scanner;
	private OutputStream m_derivationOutput;
	private OutputStream m_errorOutput;
	
	private Token		m_lookahead;
	
	private Derivation	m_derivation;
	
	private SemanticAnalyzer 	m_semanticAnalyzer;
	private CodeGenerator		m_codeGenerator;
	
	private boolean			m_outputSyntaxError;
	
	private	int				m_errors;

		
	SyntacticAnalyzer(OutputStream symbolOutput, OutputStream derivationOutput, OutputStream errorOutput) {
		
				
		m_errorOutput = errorOutput;
		m_derivationOutput = derivationOutput;
		m_outputSyntaxError = true;
		m_errors = 0;
					
	}
	
	
	private void outputError(int line, int col, String message) {
		
		
		if (m_outputSyntaxError == false) { return; }
		
		// Increase error count.
		m_errors++;
		
		// Send error message to output.
		message = "Syntax error:" + " (" + Integer.toString(line) + ',' + Integer.toString(col) + ") " + message + "\r\n";
		byte b[] = message.getBytes();
		
		// Write token to output
		try {
			m_errorOutput.write(b, 0, message.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

		
	public boolean parse() {
		
		if (m_derivation == null) {
			m_derivation = new Derivation("prog", m_derivationOutput);
		}
		
		m_lookahead = m_scanner.nextToken();
						
		if (rule_prog() && match(Token.T_EOF)) {
			return true;
		}
		
		return false;
				
	}
	
	
	private boolean match(int tokenType) {
		
		m_derivation.move();
		
		if (m_lookahead.getType() == tokenType) {
			m_lookahead = m_scanner.nextToken();
			return true;
		}
		
		//TODO
		outputError(m_lookahead.getLine(), m_lookahead.getColumn(), "expected [" + Token.getTypeReadableFormatString(tokenType) + "] got " + Token.getTypeReadableFormatString(m_lookahead.getType()) + " instead.");
		
		m_lookahead = m_scanner.nextToken();
		return false;
	}
	
	
	private boolean match(int tokenType, Attribute attribute) {
		
		m_derivation.move();
		
		if (m_lookahead.getType() == tokenType) {
			
			attribute.setIntValue(m_lookahead.getType());
			attribute.setStrValue(m_lookahead.getLexeme());
			attribute.setLine(m_lookahead.getLine());
			attribute.setColumn(m_lookahead.getColumn());
			
			m_lookahead = m_scanner.nextToken();
			return true;
		}
		
		//TODO
		outputError(m_lookahead.getLine(), m_lookahead.getColumn(), "expected [" + Token.getTypeReadableFormatString(tokenType) + "] got " + Token.getTypeReadableFormatString(m_lookahead.getType()) + " instead.");
		
		m_lookahead = m_scanner.nextToken();
		return false;
	}

	
	
	private boolean isLHIn(int[] tokenIdSet) {
		
		if (tokenIdSet == null) { return false; }
		
		for (int i=0; i < tokenIdSet.length; i++) {
			
			if (m_lookahead.getType() == tokenIdSet[i]) {
				
				// Match found; no need to continue.
				return true;
			}
			
		}
		
		return false;
		
	}
	
	private boolean isEpsilonIn(int[] tokenIdSet) {
		
		for (int i=0; i < tokenIdSet.length; i++) {
			
			if (Token.T_EPSILON == tokenIdSet[i]) {
				
				// Match found; no need to continue.
				return true;
			}
			
		}
		
		return false;
		
	}
	
	
	
	private boolean skipErrors(int FIRST[], int FOLLOW[]) {
		
		if (isLHIn(FIRST) || (isEpsilonIn(FIRST) && isLHIn(FOLLOW))) {
			
			return true;
		}
		// We have an error after this point.
		String stringExpectedTokens = "[";
		boolean start = true;
		for (int i = 0; i < FIRST.length; i++) {
			if (FIRST[i] == Token.T_EPSILON) { continue; }
			if (start) { stringExpectedTokens += Token.getTypeReadableFormatString(FIRST[i]); start = false; }
			else { stringExpectedTokens += ", " + Token.getTypeReadableFormatString(FIRST[i]); }
		}
		stringExpectedTokens += "]";
		
		
		outputError(m_lookahead.getLine(), m_lookahead.getColumn(), "expected " + stringExpectedTokens + " got " + Token.getTypeReadableFormatString(m_lookahead.getType()) + " instead.");
		while (!(isLHIn(FIRST) || isLHIn(FOLLOW))) {
			
			m_lookahead = m_scanner.nextToken();
			if ((isEpsilonIn(FIRST) && isLHIn(FOLLOW)) || (m_lookahead.getType() == Token.T_EOF)) {
				
				// Parsing should be aborted.
				return false;
			}
		}
		
		return true;
	}
	
	
	private boolean rule_prog() { 
		
		int[] FIRST = {Token.T_KEYWORD_CLASS, Token.T_KEYWORD_PROGRAM };
		int[] FIRST_1 = {Token.T_KEYWORD_CLASS, Token.T_KEYWORD_PROGRAM };
		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"classDecl_N", "progBody"};
			m_derivation.replace(derivations);

			
			if (rule_classDecl_N() && rule_progBody()) {
				return true;
			}
			
			return false;
		}
		
		return false;
	}

		
	private boolean rule_classDecl_N() {
		
		int[] FIRST = {Token.T_EPSILON, Token.T_KEYWORD_CLASS };
		int[] FIRST_1 = { Token.T_KEYWORD_CLASS };
		int[] FOLLOW = {Token.T_KEYWORD_PROGRAM};
		
		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"classDecl", "classDecl_N"};
			m_derivation.replace(derivations);

			
			if (rule_classDecl() && rule_classDecl_N()) {
				return true;
			}
			
			return false;
		}
				
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}

		
		return false;

		
	}
	
	
	private boolean rule_classDecl() {
		
		Attribute name = new Attribute();
		
		int[] FIRST = {Token.T_KEYWORD_CLASS};
		int[] FIRST_1 = {Token.T_KEYWORD_CLASS};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_CLASS), Token.getTypeReadableFormatString(Token.T_IDENTIFIER), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_BRACKET), "varDecl_N_or_funcDef_N", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_BRACKET), Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_KEYWORD_CLASS) &&  match(Token.T_IDENTIFIER, name) && m_semanticAnalyzer.addClassEntry(name) && match(Token.T_DELIMITER_LEFT_BRACKET) && rule_varDecl_N_or_funcDef_N() && match(Token.T_DELIMITER_RIGHT_BRACKET) && match(Token.T_DELIMITER_SEMICOLON)) {
				
				m_semanticAnalyzer.scopeUp();
				return true;
			}
			
			return false;
		}
		
		
		return false;

		
	}
	private boolean rule_varDecl_N_or_funcDef_N() { 
		int[] FIRST = {Token.T_EPSILON, Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};
		int[] FIRST_1 = {Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};
		int[] FOLLOW = {Token.T_DELIMITER_RIGHT_BRACKET};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"varDecl_N_or_funcDef", "varDecl_N_or_funcDef_N"};
			m_derivation.replace(derivations);

			
			if (rule_varDecl_N_or_funcDef() && rule_varDecl_N_or_funcDef_N()) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;

	}
	private boolean rule_varDecl_N_or_funcDef() { 
	
		int[] FIRST =  {Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};
		int[] FIRST_1 =  {Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};

		if (!skipErrors(FIRST, null)) { return false; }
		
		Attribute name = new Attribute();
		Attribute type = new Attribute();
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"type", Token.getTypeReadableFormatString(Token.T_IDENTIFIER), "varDecl_N_or_funcDef_tail"};
			m_derivation.replace(derivations);

			
			if (rule_type(type) && match(Token.T_IDENTIFIER, name) && rule_varDecl_N_or_funcDef_tail(name, type)) {
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_varDecl_N_or_funcDef_tail(Attribute name, Attribute type) {
		
		int[] FIRST = {Token.T_DELIMITER_LEFT_SQ_BRACKET, Token.T_DELIMITER_SEMICOLON, Token.T_DELIMITER_LEFT_PARENTHESIS};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_SQ_BRACKET, Token.T_DELIMITER_SEMICOLON};
		int[] FIRST_2 = {Token.T_DELIMITER_LEFT_PARENTHESIS};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"arraySize_N", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			
			if (rule_arraySize_N(type) && match(Token.T_DELIMITER_SEMICOLON) && m_semanticAnalyzer.addVariableEntry(name, SemanticRecord.KIND_VARIABLE, type)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_2)) {
						
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "fParams", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), "rule_funcBody", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			// Create function entry and table here
			if (m_semanticAnalyzer.addFunctionEntry(name, type) && m_codeGenerator.createFunctionBegin(name, type, true) && match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_fParams() && match(Token.T_DELIMITER_RIGHT_PARENTHESIS) && rule_funcBody() && match(Token.T_DELIMITER_SEMICOLON)) {
				m_semanticAnalyzer.scopeUp();
				return true;
			}
			
			return false;
		}

		
		return false;
	}
	
	
	private boolean rule_funcDef_N() {
		
		int[] FIRST = {Token.T_EPSILON, Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};
		int[] FIRST_1 = {Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};
		int[] FOLLOW = {Token.T_EOF};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"funcDef", "funcDef_N"};
			m_derivation.replace(derivations);

			
			if (rule_funcDef() && rule_funcDef_N()) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;
	}
	private boolean rule_progBody() {
		
		int[] FIRST = {Token.T_KEYWORD_PROGRAM};
		int[] FIRST_1 = {Token.T_KEYWORD_PROGRAM};

		if (!skipErrors(FIRST, null)) { return false; }
		
		Attribute program = new Attribute();
		Attribute nullType = new Attribute();
		nullType.setIntValue(-1);
		
		program.setStrValue("program");
		
		m_codeGenerator.createProgramFunction();
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_PROGRAM), "funcBody", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON), "funcDef_N"};
			m_derivation.replace(derivations);

			
			if (match(Token.T_KEYWORD_PROGRAM) && m_semanticAnalyzer.addFunctionEntry(program, nullType) && rule_funcBody() && match(Token.T_DELIMITER_SEMICOLON) && m_codeGenerator.end() && m_semanticAnalyzer.scopeUp() && rule_funcDef_N()) {
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_funcHead() { 
		
		int[] FIRST = {Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};
		int[] FIRST_1 = {Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};

		if (!skipErrors(FIRST, null)) { return false; }
		
		Attribute name = new Attribute();
		Attribute type = new Attribute();
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"type", Token.getTypeReadableFormatString(Token.T_IDENTIFIER), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "fParams", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS)};
			m_derivation.replace(derivations);

			
			if (rule_type(type) && match(Token.T_IDENTIFIER, name) &&  m_semanticAnalyzer.addFunctionEntry(name, type) && m_codeGenerator.createFunctionBegin(name, type, false) && match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_fParams() && match(Token.T_DELIMITER_RIGHT_PARENTHESIS) ) {
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_funcDef() { 
		
		int[] FIRST = {Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};
		int[] FIRST_1 = {Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"funcHead", "funcBody", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			
			if (rule_funcHead() && rule_funcBody() && match(Token.T_DELIMITER_SEMICOLON)) {
				m_semanticAnalyzer.scopeUp();
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_funcBody() {
		
		int[] FIRST = {Token.T_DELIMITER_LEFT_BRACKET};	
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_BRACKET};	

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_BRACKET), "rule_varDecl_or_statement", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_BRACKET)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_DELIMITER_LEFT_BRACKET) && rule_varDecl_or_statement() && match(Token.T_DELIMITER_RIGHT_BRACKET) ) {
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}

	private boolean rule_varDecl_or_statement() {
		
		int[] FIRST = {Token.T_EPSILON, Token.T_KEYWORD_FLOAT, Token.T_KEYWORD_INT, Token.T_KEYWORD_FOR, Token.T_KEYWORD_IF, Token.T_KEYWORD_GET, Token.T_KEYWORD_PUT, Token.T_KEYWORD_RETURN, Token.T_IDENTIFIER};
		int[] FIRST_1 = {Token.T_KEYWORD_FLOAT, Token.T_KEYWORD_INT};
		int[] FIRST_2 = {Token.T_KEYWORD_FOR, Token.T_KEYWORD_IF, Token.T_KEYWORD_GET, Token.T_KEYWORD_PUT, Token.T_KEYWORD_RETURN };
		int[] FIRST_3 = {Token.T_IDENTIFIER};
		int[] FOLLOW = {Token.T_DELIMITER_RIGHT_BRACKET};

		

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		Attribute type = new Attribute();
		
		if (isLHIn(FIRST_3)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_IDENTIFIER), "varDecl_or_statement_tail"};
			m_derivation.replace(derivations);

			
			if (match(Token.T_IDENTIFIER, type) && rule_varDecl_or_statement_tail(type)) {
				return true;
			}
			
			return false;
		}

		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"type_", "varDecl_tail", "varDecl_or_statement"};
			m_derivation.replace(derivations);

			
			if (rule_type_(type) && rule_varDecl_tail(type) && rule_varDecl_or_statement()) {
				return true;
			}
			
			return false;
		}
		

		if (isLHIn(FIRST_2)) {
			
			String[] derivations = {"statement_other", "statement_N"};
			m_derivation.replace(derivations);

			
			if (rule_statement_other() && rule_statement_N()) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}

		
		
		return false;
	}
	private boolean rule_varDecl_or_statement_tail(Attribute type) { 
		
		int[] FIRST = {Token.T_IDENTIFIER, Token.T_DELIMITER_LEFT_SQ_BRACKET, Token.T_DELIMITER_DOT, Token.T_DELIMITER_ASSIGN};
		int[] FIRST_1 = {Token.T_IDENTIFIER};
		int[] FIRST_2 = {Token.T_DELIMITER_LEFT_SQ_BRACKET, Token.T_DELIMITER_DOT, Token.T_DELIMITER_ASSIGN};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"varDecl_tail", "varDecl_or_statement"};
			m_derivation.replace(derivations);

			
			if (rule_varDecl_tail(type) && rule_varDecl_or_statement()) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_2)) {
			
			String[] derivations = {"indice_N", "idnest_N", "assignStat_tail", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON), "statement_N"};
			m_derivation.replace(derivations);

			
			if (rule_indice_N(type) && rule_idnest_N(type, null) && rule_assignStat_tail(type) && match(Token.T_DELIMITER_SEMICOLON) && rule_statement_N()) {
				return true;
			}
			
			return false;
		}
		

		
		
		return false;
	}
	private boolean rule_assignStat_tail(Attribute identifier) { 
		
		int[] FIRST = {Token.T_DELIMITER_ASSIGN};
		int[] FIRST_1 = {Token.T_DELIMITER_ASSIGN};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"assignOp", "expr"};
			m_derivation.replace(derivations);

			AbstractSyntaxTree tree = new AbstractSyntaxTree();
			
			if (rule_assignOp() && rule_expr(tree)) {
				
				
				// For debugging purposes.
				//System.out.println(tree.evaluate());
				//m_codeGenerator.treeEvaluation(tree.getRoot());
				m_semanticAnalyzer.assignCheck(identifier, tree);
				m_codeGenerator.assign(identifier, tree);
				
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_statement_other() { 
		
		int[] FIRST = {Token.T_KEYWORD_RETURN, Token.T_KEYWORD_PUT, Token.T_KEYWORD_GET, Token.T_KEYWORD_IF, Token.T_KEYWORD_FOR};
		int[] FIRST_1 = {Token.T_KEYWORD_RETURN};
		int[] FIRST_2 = {Token.T_KEYWORD_PUT};
		int[] FIRST_3 = {Token.T_KEYWORD_GET};
		int[] FIRST_4 = {Token.T_KEYWORD_IF};
		int[] FIRST_5 = {Token.T_KEYWORD_FOR};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_4)) {
						
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_IF), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "expr", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), Token.getTypeReadableFormatString(Token.T_KEYWORD_THEN), "statBlock", Token.getTypeReadableFormatString(Token.T_KEYWORD_ELSE), "statBlock", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			AbstractSyntaxTree predicateTree = new AbstractSyntaxTree();
			
			int ifId;
			
			if (match(Token.T_KEYWORD_IF) &&  match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_expr(predicateTree) && match(Token.T_DELIMITER_RIGHT_PARENTHESIS)  && m_semanticAnalyzer.mathTypeCheck(predicateTree, true) && ((ifId = m_codeGenerator.createIfBegin(predicateTree)) > -1) && match(Token.T_KEYWORD_THEN) && rule_statBlock() && m_codeGenerator.createIfEnd(ifId) && m_codeGenerator.createElseBegin(ifId) && match(Token.T_KEYWORD_ELSE) && rule_statBlock() && m_codeGenerator.createElseEnd(ifId) && match(Token.T_DELIMITER_SEMICOLON) ) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_5)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_FOR), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "type", Token.getTypeReadableFormatString(Token.T_IDENTIFIER), "assignOp", "expr", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON), "relExpr", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON), "assignStat", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), "statBlock", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			Attribute name = new Attribute();
			Attribute type = new Attribute();
			
			AbstractSyntaxTree indexTree = new AbstractSyntaxTree();
			AbstractSyntaxTree relationTree = new AbstractSyntaxTree();
			Assignment loopAssign = new Assignment(null, null);
		
			int forId;
			
			if (match(Token.T_KEYWORD_FOR) &&  match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_type(type) && match(Token.T_IDENTIFIER, name) && m_semanticAnalyzer.addForVariableEntry(name, type) && rule_assignOp() && rule_expr(indexTree) && m_semanticAnalyzer.assignCheck(name, indexTree) && match(Token.T_DELIMITER_SEMICOLON) && rule_relExpr(relationTree) && m_semanticAnalyzer.mathTypeCheck(relationTree, true) && ((forId = m_codeGenerator.createForLoopBegin(name, type, indexTree, relationTree)) > -1) && match(Token.T_DELIMITER_SEMICOLON) && rule_assignStat(loopAssign)  && match(Token.T_DELIMITER_RIGHT_PARENTHESIS) && rule_statBlock() && match(Token.T_DELIMITER_SEMICOLON) && m_codeGenerator.createForLoopEnd(forId, loopAssign.getAttribute(), loopAssign.getExpressionTree()) ) {
				m_semanticAnalyzer.scopeUp();
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_3)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_GET), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "variable", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			Attribute identifier = new Attribute();
			
			if (match(Token.T_KEYWORD_GET) &&  match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_variable(identifier) && m_semanticAnalyzer.mathVariableTypeCheck(identifier, false) && match(Token.T_DELIMITER_RIGHT_PARENTHESIS) &&  match(Token.T_DELIMITER_SEMICOLON) ) {
				
				m_codeGenerator.getc(identifier);
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_2)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_PUT), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "expr", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			AbstractSyntaxTree tree = new AbstractSyntaxTree();
			
			if (match(Token.T_KEYWORD_PUT) &&  match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_expr(tree) && m_semanticAnalyzer.mathTypeCheck(tree, false) && match(Token.T_DELIMITER_RIGHT_PARENTHESIS) &&  match(Token.T_DELIMITER_SEMICOLON) ) {
				
				m_codeGenerator.putc(tree);
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_RETURN), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "expr", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			AbstractSyntaxTree tree = new AbstractSyntaxTree();
			
			if (match(Token.T_KEYWORD_RETURN) &&  match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_expr(tree) && match(Token.T_DELIMITER_RIGHT_PARENTHESIS)  &&  match(Token.T_DELIMITER_SEMICOLON) && m_semanticAnalyzer.returnTypeCheck(tree) && m_codeGenerator.functionReturn(tree)) {
				return true;
			}
			
			return false;
		}
		

		
		
		return false;
	}
	private boolean rule_idnest_N(Attribute identifier, Attribute last) { 
		
		int[] FIRST = {Token.T_EPSILON, Token.T_DELIMITER_DOT};
		int[] FIRST_1 = {Token.T_DELIMITER_DOT};
		int[] FOLLOW = {Token.T_DELIMITER_ASSIGN, Token.T_DELIMITER_SEMICOLON, Token.T_DELIMITER_RIGHT_PARENTHESIS, Token.T_DELIMITER_COMMA, Token.T_OPERATOR_LESSERTHAN, Token.T_OPERATOR_LESSERTHAN_OR_EQ, Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN, Token.T_OPERATOR_EQ, Token.T_OPERATOR_GREATERTHAN, Token.T_OPERATOR_GREATERTHAN_OR_EQ, Token.T_DELIMITER_RIGHT_SQ_BRACKET, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS, Token.T_KEYWORD_OR, Token.T_OPERATOR_MULTIPLY, Token.T_OPERATOR_DIV, Token.T_KEYWORD_AND, Token.T_DELIMITER_LEFT_PARENTHESIS};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"idnest", "idnest_N"};
			m_derivation.replace(derivations);

			
			if (rule_idnest(identifier) && rule_idnest_N(identifier.getNext(), last)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			
			if (last != null) { last = identifier; }
			
			m_derivation.remove();
			return true;
		}
		
		return false;
	}
	private boolean rule_idnest(Attribute identifier) { 
		
		int[] FIRST = {Token.T_DELIMITER_DOT};
		int[] FIRST_1 = {Token.T_DELIMITER_DOT};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			Attribute nextIdentifier = new Attribute();
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_DOT), Token.getTypeReadableFormatString(Token.T_IDENTIFIER), "indice_N"};
			m_derivation.replace(derivations);

			
			if (match(Token.T_DELIMITER_DOT) && match(Token.T_IDENTIFIER, nextIdentifier) && rule_indice_N(nextIdentifier)) {
				identifier.setNext(nextIdentifier);
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_varDecl_tail(Attribute type) { 
		
		int[] FIRST = {Token.T_IDENTIFIER};
		int[] FIRST_1 = {Token.T_IDENTIFIER};

		if (!skipErrors(FIRST, null)) { return false; }
		
		Attribute name = new Attribute();
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_IDENTIFIER), "arraySize_N", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_IDENTIFIER, name) && rule_arraySize_N(type) && match(Token.T_DELIMITER_SEMICOLON) && m_semanticAnalyzer.addVariableEntry(name, SemanticRecord.KIND_VARIABLE, type) && m_codeGenerator.addVariableDeclaration(name, type) ) {
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_type_(Attribute type) { 
		
		int[] FIRST = {Token.T_KEYWORD_INT, Token.T_KEYWORD_FLOAT};
		int[] FIRST_1 = {Token.T_KEYWORD_INT};
		int[] FIRST_2 = {Token.T_KEYWORD_FLOAT};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_INT)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_KEYWORD_INT, type)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_2)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_FLOAT)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_KEYWORD_FLOAT, type)) {
				return true;
			}
			
			return false;
		}
		

		
		return false;
	}

	private boolean rule_statement_N() { 
		
		int[] FIRST = {Token.T_EPSILON, Token.T_KEYWORD_FOR, Token.T_KEYWORD_IF, Token.T_KEYWORD_GET, Token.T_KEYWORD_PUT, Token.T_KEYWORD_RETURN, Token.T_IDENTIFIER};	
		int[] FIRST_1 = {Token.T_KEYWORD_FOR, Token.T_KEYWORD_IF, Token.T_KEYWORD_GET, Token.T_KEYWORD_PUT, Token.T_KEYWORD_RETURN, Token.T_IDENTIFIER};
		int[] FOLLOW = {Token.T_DELIMITER_RIGHT_BRACKET};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"statement", "statement_N"};
			m_derivation.replace(derivations);

			
			if (rule_statement() && rule_statement_N()) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			
			m_codeGenerator.labelCheck();
			
			m_derivation.remove();
			return true;
		}
		
		return false;
	}
	private boolean rule_statement() {
		
		int[] FIRST = {Token.T_IDENTIFIER, Token.T_KEYWORD_RETURN, Token.T_KEYWORD_PUT, Token.T_KEYWORD_GET, Token.T_KEYWORD_IF, Token.T_KEYWORD_FOR};
		int[] FIRST_1 = {Token.T_IDENTIFIER};
		int[] FIRST_2 = {Token.T_KEYWORD_RETURN};
		int[] FIRST_3 = {Token.T_KEYWORD_PUT};
		int[] FIRST_4 = {Token.T_KEYWORD_GET};
		int[] FIRST_5 = {Token.T_KEYWORD_IF};
		int[] FIRST_6 = {Token.T_KEYWORD_FOR};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"assignStat", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			
			if (rule_assignStat(null) && match(Token.T_DELIMITER_SEMICOLON) ) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_5)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_IF), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "expr", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), Token.getTypeReadableFormatString(Token.T_KEYWORD_THEN), "statBlock", Token.getTypeReadableFormatString(Token.T_KEYWORD_ELSE), "statBlock", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			AbstractSyntaxTree predicateTree = new AbstractSyntaxTree();
			
			int ifId;
			
			if (match(Token.T_KEYWORD_IF) &&  match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_expr(predicateTree) && match(Token.T_DELIMITER_RIGHT_PARENTHESIS)  && m_semanticAnalyzer.mathTypeCheck(predicateTree, true) && ((ifId = m_codeGenerator.createIfBegin(predicateTree)) > -1) && match(Token.T_KEYWORD_THEN) && rule_statBlock() && m_codeGenerator.createIfEnd(ifId) && m_codeGenerator.createElseBegin(ifId) && match(Token.T_KEYWORD_ELSE) && rule_statBlock() && m_codeGenerator.createElseEnd(ifId) && match(Token.T_DELIMITER_SEMICOLON) ) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_6)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_FOR), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "type", Token.getTypeReadableFormatString(Token.T_IDENTIFIER), "assignOp", "expr", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON), "relExpr", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON), "assignStat", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), "statBlock", Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			Attribute name = new Attribute();
			Attribute type = new Attribute();
			
			AbstractSyntaxTree indexTree = new AbstractSyntaxTree();
			AbstractSyntaxTree relationTree = new AbstractSyntaxTree();
			Assignment loopAssign = new Assignment(null, null);
		
			int forId;
			
			if (match(Token.T_KEYWORD_FOR) &&  match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_type(type) && match(Token.T_IDENTIFIER, name) && m_semanticAnalyzer.addForVariableEntry(name, type) && rule_assignOp() && rule_expr(indexTree) && m_semanticAnalyzer.assignCheck(name, indexTree) && match(Token.T_DELIMITER_SEMICOLON) && rule_relExpr(relationTree) && m_semanticAnalyzer.mathTypeCheck(relationTree, true) && ((forId = m_codeGenerator.createForLoopBegin(name, type, indexTree, relationTree)) > -1) && match(Token.T_DELIMITER_SEMICOLON) && rule_assignStat(loopAssign)  && match(Token.T_DELIMITER_RIGHT_PARENTHESIS) && rule_statBlock() && match(Token.T_DELIMITER_SEMICOLON) && m_codeGenerator.createForLoopEnd(forId, loopAssign.getAttribute(), loopAssign.getExpressionTree()) ) {
				m_semanticAnalyzer.scopeUp();
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_4)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_GET), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "variable", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			Attribute identifier = new Attribute();
			
			if (match(Token.T_KEYWORD_GET) &&  match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_variable(identifier) && m_semanticAnalyzer.mathVariableTypeCheck(identifier, false) && match(Token.T_DELIMITER_RIGHT_PARENTHESIS) &&  match(Token.T_DELIMITER_SEMICOLON) ) {
				
				m_codeGenerator.getc(identifier);
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_3)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_PUT), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "expr", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			AbstractSyntaxTree tree = new AbstractSyntaxTree();
			
			if (match(Token.T_KEYWORD_PUT) &&  match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_expr(tree) && m_semanticAnalyzer.mathTypeCheck(tree, false) && match(Token.T_DELIMITER_RIGHT_PARENTHESIS) &&  match(Token.T_DELIMITER_SEMICOLON) ) {
				
				m_codeGenerator.putc(tree);
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_2)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_RETURN), Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "expr", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS), Token.getTypeReadableFormatString(Token.T_DELIMITER_SEMICOLON)};
			m_derivation.replace(derivations);

			AbstractSyntaxTree tree = new AbstractSyntaxTree();
			
			if (match(Token.T_KEYWORD_RETURN) &&  match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_expr(tree) && match(Token.T_DELIMITER_RIGHT_PARENTHESIS)  &&  match(Token.T_DELIMITER_SEMICOLON) && m_semanticAnalyzer.returnTypeCheck(tree) && m_codeGenerator.functionReturn(tree)) {
				return true;
			}
			
			return false;
		}
				

		
		return false;
	}
	
	private class Assignment {
		private Attribute m_variable;
		private AbstractSyntaxTree m_expressionTree;
		public Assignment(Attribute variable, AbstractSyntaxTree expressionTree) { m_variable = variable; m_expressionTree = expressionTree; }
		public void setVariable(Attribute variable) { m_variable = variable; }
		public void setExpressionTree(AbstractSyntaxTree expressionTree) { m_expressionTree = expressionTree; }
		public Attribute getAttribute() { return m_variable; }
		public AbstractSyntaxTree getExpressionTree() { return m_expressionTree; }
	}
	
	private boolean rule_assignStat(Assignment loopAssign) { 
		
		int[] FIRST = {Token.T_IDENTIFIER};
		int[] FIRST_1 = {Token.T_IDENTIFIER};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"variable", "assignOp", "expr"};
			m_derivation.replace(derivations);
			
			Attribute identifier = new Attribute();
			AbstractSyntaxTree tree = new AbstractSyntaxTree();
			
			if (rule_variable(identifier) && rule_assignOp() && rule_expr(tree)) {
				m_semanticAnalyzer.assignCheck(identifier, tree);
				if (loopAssign != null) {
					loopAssign.setVariable(identifier);
					loopAssign.setExpressionTree(tree);
				}
				else {
					m_codeGenerator.assign(identifier, tree);
				}
				
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_statBlock() { 
		
		int[] FIRST = {Token.T_EPSILON, Token.T_KEYWORD_FOR, Token.T_KEYWORD_IF, Token.T_KEYWORD_GET, Token.T_KEYWORD_PUT, Token.T_KEYWORD_RETURN, Token.T_IDENTIFIER, Token.T_DELIMITER_LEFT_BRACKET};
		int[] FIRST_1 = {Token.T_KEYWORD_FOR, Token.T_KEYWORD_IF, Token.T_KEYWORD_GET, Token.T_KEYWORD_PUT, Token.T_KEYWORD_RETURN, Token.T_IDENTIFIER};
		int[] FIRST_2 = {Token.T_DELIMITER_LEFT_BRACKET};
		int[] FOLLOW = {Token.T_DELIMITER_SEMICOLON, Token.T_KEYWORD_ELSE};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_2)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_BRACKET), "statement_N", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_BRACKET)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_DELIMITER_LEFT_BRACKET) && rule_statement_N() && match(Token.T_DELIMITER_RIGHT_BRACKET)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"statement"};
			m_derivation.replace(derivations);

			
			if (rule_statement()) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			
			m_codeGenerator.labelCheck();
			
			m_derivation.remove();
			return true;
		}

		
		return false;
	}
	private boolean rule_variable_or_functionCall(AbstractSyntaxTree tree) { 
		
		int[] FIRST = {Token.T_IDENTIFIER};
		int[] FIRST_1 = {Token.T_IDENTIFIER};

		if (!skipErrors(FIRST, null)) { return false; }
		
		Attribute name = new Attribute();
		Attribute last = new Attribute();
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_IDENTIFIER), "indice_N", "idnest_N", "variable_or_functionCall_tail"};
			m_derivation.replace(derivations);

			
			if (match(Token.T_IDENTIFIER, name) && rule_indice_N(name) && rule_idnest_N(name, last) && rule_variable_or_functionCall_tail(name, last, tree)) {
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_variable_or_functionCall_tail(Attribute identifier, Attribute last, AbstractSyntaxTree tree) { 
				
		int[] FIRST = {Token.T_EPSILON, Token.T_DELIMITER_LEFT_PARENTHESIS};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_PARENTHESIS};
		int[] FOLLOW = {Token.T_DELIMITER_SEMICOLON, Token.T_DELIMITER_RIGHT_PARENTHESIS, Token.T_DELIMITER_COMMA, Token.T_OPERATOR_LESSERTHAN, Token.T_OPERATOR_LESSERTHAN_OR_EQ, Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN, Token.T_OPERATOR_EQ, Token.T_OPERATOR_GREATERTHAN, Token.T_OPERATOR_GREATERTHAN_OR_EQ, Token.T_DELIMITER_RIGHT_SQ_BRACKET, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS, Token.T_KEYWORD_OR, Token.T_OPERATOR_MULTIPLY, Token.T_OPERATOR_DIV, Token.T_KEYWORD_AND};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
		
			last = identifier;
			while (last.getNext() != null) {
				last = last.getNext();
			}
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "aParams", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS)};
			m_derivation.replace(derivations);
			
			if (match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_aParams(last) && match(Token.T_DELIMITER_RIGHT_PARENTHESIS) && tree.add(identifier, AbstractSyntaxTreeNode.NODE_FUNCTION)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			
			if (tree.add(identifier, AbstractSyntaxTreeNode.NODE_VARIABLE)) { return true; }
		}
		
		return false;
	}
	private boolean rule_variable(Attribute name) { 
		
		int[] FIRST = {Token.T_DELIMITER_DOT, Token.T_IDENTIFIER};
		int[] FIRST_1 = {Token.T_DELIMITER_DOT, Token.T_IDENTIFIER};

		if (!skipErrors(FIRST, null)) { return false; }
		
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_IDENTIFIER), "idnest_N","indice_N"};
			m_derivation.replace(derivations);

			
			if (match(Token.T_IDENTIFIER, name) && rule_indice_N(name) && rule_idnest_N(name, null)) {
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_indice_N(Attribute identifier) { 
		
		int[] FIRST = {Token.T_EPSILON, Token.T_DELIMITER_LEFT_SQ_BRACKET};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_SQ_BRACKET};
		int[] FOLLOW = {Token.T_DELIMITER_DOT, Token.T_DELIMITER_ASSIGN, Token.T_DELIMITER_SEMICOLON, Token.T_DELIMITER_RIGHT_PARENTHESIS, Token.T_DELIMITER_COMMA, Token.T_OPERATOR_LESSERTHAN, Token.T_OPERATOR_LESSERTHAN_OR_EQ, Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN, Token.T_OPERATOR_EQ, Token.T_OPERATOR_GREATERTHAN, Token.T_OPERATOR_GREATERTHAN_OR_EQ, Token.T_DELIMITER_RIGHT_SQ_BRACKET, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS, Token.T_KEYWORD_OR, Token.T_OPERATOR_MULTIPLY, Token.T_OPERATOR_DIV, Token.T_KEYWORD_AND, Token.T_DELIMITER_LEFT_PARENTHESIS};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"indice", "indice_N"};
			m_derivation.replace(derivations);

			
			if (rule_indice(identifier) && rule_indice_N(identifier)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;
		
	}
	private boolean rule_indice(Attribute identifier) { 
		
		int[] FIRST = {Token.T_DELIMITER_LEFT_SQ_BRACKET};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_SQ_BRACKET};

		if (!skipErrors(FIRST, null)) { return false; }
		
		AbstractSyntaxTree tree = new AbstractSyntaxTree();
		identifier.addExpressionTree(tree);
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_SQ_BRACKET), "arithExpr", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_SQ_BRACKET)};
			m_derivation.replace(derivations);

			//TODO
			if (match(Token.T_DELIMITER_LEFT_SQ_BRACKET) && rule_arithExpr(tree) && match(Token.T_DELIMITER_RIGHT_SQ_BRACKET) && m_semanticAnalyzer.mathTypeCheck(tree, false)) {
				//identifier.setIntValue(identifier.getIntValue() + 1);
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_arraySize_N(Attribute type) {
		
		int[] FIRST = {Token.T_EPSILON, Token.T_DELIMITER_LEFT_SQ_BRACKET};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_SQ_BRACKET};
		int[] FOLLOW = {Token.T_DELIMITER_SEMICOLON, Token.T_DELIMITER_RIGHT_PARENTHESIS, Token.T_DELIMITER_COMMA};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"arraySize", "arraySize_N"};
			m_derivation.replace(derivations);

			
			if (rule_arraySize(type) && rule_arraySize_N(type)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;
	}
	private boolean rule_arraySize(Attribute type) { 
		
		int[] FIRST = {Token.T_DELIMITER_LEFT_SQ_BRACKET};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_SQ_BRACKET};

		if (!skipErrors(FIRST, null)) { return false; }
		
		Attribute arraySize = new Attribute();
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_SQ_BRACKET), Token.getTypeReadableFormatString(Token.T_INTEGER), Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_SQ_BRACKET)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_DELIMITER_LEFT_SQ_BRACKET) &&  match(Token.T_INTEGER, arraySize) &&  match(Token.T_DELIMITER_RIGHT_SQ_BRACKET)) {
				type.addIntegerToList(Integer.parseInt(arraySize.getStrValue()));
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_type(Attribute type) { 
		
		int[] FIRST = {Token.T_KEYWORD_INT, Token.T_IDENTIFIER, Token.T_KEYWORD_FLOAT};
		int[] FIRST_1 = {Token.T_KEYWORD_INT};
		int[] FIRST_2 = {Token.T_KEYWORD_FLOAT};
		int[] FIRST_3 = {Token.T_IDENTIFIER};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_INT)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_KEYWORD_INT, type)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_2)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_FLOAT)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_KEYWORD_FLOAT, type)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FIRST_3)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_IDENTIFIER)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_IDENTIFIER, type)) {
				return true;
			}
			
			return false;
		}
		

		
		return false;
	}
	private boolean rule_fParams() { 
		
		int[] FIRST = {Token.T_EPSILON, Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};
		int[] FIRST_1 = {Token.T_KEYWORD_FLOAT, Token.T_IDENTIFIER, Token.T_KEYWORD_INT};
		int[] FOLLOW = {Token.T_DELIMITER_RIGHT_PARENTHESIS};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		Attribute name = new Attribute();
		Attribute type = new Attribute();
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"type", Token.getTypeReadableFormatString(Token.T_IDENTIFIER), "arraySize_N", "fParamsTail_N"};
			m_derivation.replace(derivations);
			
			if (rule_type(type) && match(Token.T_IDENTIFIER, name) && rule_arraySize_N(type) && m_semanticAnalyzer.addVariableEntry(name, SemanticRecord.KIND_PARAMETER, type)&& rule_fParamsTail_N()) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;
	}
	private boolean rule_aParams(Attribute functionCall) { 
		
		int[] FIRST = {Token.T_EPSILON, Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};
		int[] FOLLOW = {Token.T_DELIMITER_RIGHT_PARENTHESIS};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {

			AbstractSyntaxTree parameter = new AbstractSyntaxTree();
			functionCall.addExpressionTree(parameter);
						
			String[] derivations = {"expr", "aParamsTail_N"};
			m_derivation.replace(derivations);

			
			if (rule_expr(parameter) && rule_aParamsTail_N(functionCall)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;
	}
	private boolean rule_fParamsTail_N() { 
		
		int[] FIRST = {Token.T_EPSILON, Token.T_DELIMITER_COMMA};
		int[] FIRST_1 = {Token.T_DELIMITER_COMMA};
		int[] FOLLOW = {Token.T_DELIMITER_RIGHT_PARENTHESIS};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"fParamsTail", "fParamsTail_N"};
			m_derivation.replace(derivations);

			
			if (rule_fParamsTail() && rule_fParamsTail_N()) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;
		
	}
	private boolean rule_fParamsTail() { 
		
		int[] FIRST = {Token.T_DELIMITER_COMMA};
		int[] FIRST_1 = {Token.T_DELIMITER_COMMA};

		if (!skipErrors(FIRST, null)) { return false; }
		
		Attribute name = new Attribute();
		Attribute type = new Attribute();
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_COMMA), "type", Token.getTypeReadableFormatString(Token.T_IDENTIFIER), "arraySize_N"};
			m_derivation.replace(derivations);

			
			if (match(Token.T_DELIMITER_COMMA) && rule_type(type) && match(Token.T_IDENTIFIER, name) && rule_arraySize_N(type) && m_semanticAnalyzer.addVariableEntry(name, SemanticRecord.KIND_PARAMETER, type)) {
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_aParamsTail_N(Attribute functionCall) { 
		
		int[] FIRST = {Token.T_EPSILON, Token.T_DELIMITER_COMMA};
		int[] FIRST_1 = {Token.T_DELIMITER_COMMA};
		int[] FOLLOW = {Token.T_DELIMITER_RIGHT_PARENTHESIS};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
				
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"aParamsTai", "aParamsTail_N"};
			m_derivation.replace(derivations);
						
			if (rule_aParamsTail(functionCall) && rule_aParamsTail_N(functionCall)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;
		
	}
	private boolean rule_aParamsTail(Attribute identifier) { 
		
		int[] FIRST = {Token.T_DELIMITER_COMMA};
		int[] FIRST_1 = {Token.T_DELIMITER_COMMA};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_COMMA), "expr"};
			m_derivation.replace(derivations);

			AbstractSyntaxTree dimension = new AbstractSyntaxTree();
			identifier.addExpressionTree(dimension);
			
			if (match(Token.T_DELIMITER_COMMA) && rule_expr(dimension)) {
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	private boolean rule_assignOp() { 
		
		int[] FIRST = {Token.T_DELIMITER_ASSIGN};
		int[] FIRST_1 = {Token.T_DELIMITER_ASSIGN};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_ASSIGN)};
			m_derivation.replace(derivations);

			
			if (match(Token.T_DELIMITER_ASSIGN)) {
				return true;
			}
			
			return false;
		}
		
		
		return false;
	}
	
	
	private boolean rule_expr(AbstractSyntaxTree tree) {
		
		
		int[] FIRST = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};
		
		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"arithExpr", "expr_tail"};
			m_derivation.replace(derivations);

			if (rule_arithExpr(tree) && rule_expr_tail(tree)) {
				return true;
			}
			
			return false;
		}
		
		return false;
		
	}
	
	
	private boolean rule_expr_tail(AbstractSyntaxTree tree) {
		int[] FIRST = {Token.T_EPSILON, Token.T_OPERATOR_LESSERTHAN, Token.T_OPERATOR_LESSERTHAN_OR_EQ, Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN, Token.T_OPERATOR_EQ, Token.T_OPERATOR_GREATERTHAN, Token.T_OPERATOR_GREATERTHAN_OR_EQ};
		
		int[] FIRST_1 = {Token.T_OPERATOR_LESSERTHAN, Token.T_OPERATOR_LESSERTHAN_OR_EQ, Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN, Token.T_OPERATOR_EQ, Token.T_OPERATOR_GREATERTHAN, Token.T_OPERATOR_GREATERTHAN_OR_EQ};
		
		int[] FOLLOW = {Token.T_DELIMITER_SEMICOLON, Token.T_DELIMITER_RIGHT_PARENTHESIS, Token.T_DELIMITER_COMMA};
		
		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		Attribute relOp = new Attribute();
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"relOp", "arithExpr"};
			m_derivation.replace(derivations);

			//TODO
			if (rule_relOp(relOp) && tree.addParent(relOp, AbstractSyntaxTreeNode.NODE_OPERATOR) && tree.setCurrent(tree.getCurrent().getParent()) && rule_arithExpr(tree)) {
				return true;
			}
			
			return false;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;
	}
	
	
	private boolean rule_relExpr(AbstractSyntaxTree tree) {
		int[] FIRST = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};
	
		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"arithExpr", "relOp", "arithExpr"};
			m_derivation.replace(derivations);
			
			Attribute relOp = new Attribute();
			
			if ( rule_arithExpr(tree) && rule_relOp(relOp) && tree.addParent(relOp, AbstractSyntaxTreeNode.NODE_OPERATOR) && tree.setCurrent(tree.getCurrent().getParent()) && rule_arithExpr(tree)) {
				
				return true;
			}
			
			return false;
		}
		
		return false;
	}


	
	private boolean rule_arithExpr(AbstractSyntaxTree tree) {
		int[] FIRST = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"term", "arithExpr_rr"};
			m_derivation.replace(derivations);
			
			if ( rule_term(tree) && rule_arithExpr_rr(tree)) {
				
				return true;
			}
			
			return false;
		}
		
		return false;
	}

	
	private boolean rule_arithExpr_rr(AbstractSyntaxTree tree) {
		int[] FIRST = {Token.T_EPSILON, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS, Token.T_KEYWORD_OR};
		int[] FIRST_1 = {Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS, Token.T_KEYWORD_OR};
		
		int[] FOLLOW = {Token.T_DELIMITER_SEMICOLON, Token.T_DELIMITER_RIGHT_PARENTHESIS, Token.T_DELIMITER_COMMA, Token.T_OPERATOR_LESSERTHAN, Token.T_OPERATOR_LESSERTHAN_OR_EQ, Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN, Token.T_OPERATOR_EQ, Token.T_OPERATOR_GREATERTHAN_OR_EQ, Token.T_OPERATOR_GREATERTHAN, Token.T_DELIMITER_RIGHT_SQ_BRACKET};
	
		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			Attribute addOp = new Attribute();
			AbstractSyntaxTreeNode nodeBackup = null;
			
			String[] derivations = {"addOp", "term", "arithExpr_rr"};
			m_derivation.replace(derivations);

			
			if ( rule_addOp(addOp) && tree.addParent(addOp, AbstractSyntaxTreeNode.NODE_OPERATOR) && tree.moveUp() && ((nodeBackup = tree.getCurrent()) != null) && rule_term(tree) && tree.setCurrent(nodeBackup) && rule_arithExpr_rr(tree) ) {
				
				return true;
			}
			
			return false;
		}	

		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;
	}

	private boolean rule_sign(Attribute attribute) {
		int[] FIRST = {Token.T_OPERATOR_MINUS, Token.T_OPERATOR_PLUS};
	
		int[] FIRST_1 = { Token.T_OPERATOR_PLUS };
		int[] FIRST_2 = { Token.T_OPERATOR_MINUS };
		
		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_PLUS)};
			m_derivation.replace(derivations);
			
			if (match(Token.T_OPERATOR_PLUS, attribute)) {
				
				return true;
			}
			return false;
		}
		
		if (isLHIn(FIRST_2)) {
			
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_MINUS)};
			m_derivation.replace(derivations);

			if (match(Token.T_OPERATOR_MINUS, attribute)) {
				
				return true;
			}
			
			return false;
		}
		
		return false;

	}
	
	private boolean rule_term(AbstractSyntaxTree tree) {
		int[] FIRST = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};
	
		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"factor", "term_rr"};
			m_derivation.replace(derivations);

			
			if ( rule_factor(tree) && rule_term_rr(tree)) {
				
				return true;
			}
			
			return false;
		}
		return false;
	}
	
	private boolean rule_term_rr(AbstractSyntaxTree tree) {
		int[] FIRST = {Token.T_EPSILON, Token.T_OPERATOR_MULTIPLY, Token.T_OPERATOR_DIV, Token.T_KEYWORD_AND};
		int[] FIRST_1 = {Token.T_OPERATOR_MULTIPLY, Token.T_OPERATOR_DIV, Token.T_KEYWORD_AND};
		
		
		int[] FOLLOW = {Token.T_DELIMITER_SEMICOLON, Token.T_DELIMITER_RIGHT_PARENTHESIS, Token.T_DELIMITER_COMMA, Token.T_OPERATOR_LESSERTHAN, Token.T_OPERATOR_LESSERTHAN_OR_EQ, Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN, Token.T_OPERATOR_EQ, Token.T_OPERATOR_GREATERTHAN, Token.T_OPERATOR_GREATERTHAN_OR_EQ, Token.T_DELIMITER_RIGHT_SQ_BRACKET, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS, Token.T_KEYWORD_OR};

		if (!skipErrors(FIRST, FOLLOW)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			
			String[] derivations = {"multOp", "factor", "term_rr"};
			m_derivation.replace(derivations);
			
			Attribute multOp = new Attribute();
			AbstractSyntaxTreeNode nodeBackup;
			
			// 			if ( rule_addOp(addOp) && tree.addParent(addOp) && tree.moveUp() && ((nodeBackup = tree.getCurrent())) && rule_term() && tree.setCurrent(nodeBackup) && rule_arithExpr_rr(tree) ) {

			
			if ( rule_multOp(multOp) && tree.addParent(multOp, AbstractSyntaxTreeNode.NODE_OPERATOR) && tree.moveUp() && ((nodeBackup = tree.getCurrent()) != null) && rule_factor(tree) && tree.setCurrent(nodeBackup) && rule_term_rr(tree)) {
				
				return true;
			}
			
			return false;
		}	

		if (isLHIn(FOLLOW)) {
			m_derivation.remove();
			return true;
		}
		
		return false;
	}
	
	private boolean rule_factor(AbstractSyntaxTree tree) {
		int[] FIRST = {Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS, Token.T_IDENTIFIER, Token.T_KEYWORD_NOT, Token.T_FLOAT, Token.T_INTEGER, Token.T_DELIMITER_LEFT_PARENTHESIS};
		
		int[] FIRST_1 = { Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS };
		int[] FIRST_5 = { Token.T_IDENTIFIER };
		int[] FIRST_2 = { Token.T_KEYWORD_NOT };
		int[] FIRST_3 = { Token.T_INTEGER, Token.T_FLOAT };
		int[] FIRST_4 = { Token.T_DELIMITER_LEFT_PARENTHESIS };
		
		if (!skipErrors(FIRST, null)) { return false; }
		
		Attribute unaryOperator = new Attribute();
		Attribute num = new Attribute();
		AbstractSyntaxTreeNode nodeBackup;
		
		if (isLHIn(FIRST_1)) { 
			String[] derivations = {"sign", "factor"}; m_derivation.replace(derivations);
			if (rule_sign(unaryOperator) && tree.add(unaryOperator, AbstractSyntaxTreeNode.NODE_OPERATOR) && ((nodeBackup = tree.getCurrent()) != null) && rule_factor(tree) && tree.setCurrent(nodeBackup)) { return true; } return false; };
		if (isLHIn(FIRST_2)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_NOT), "factor"}; m_derivation.replace(derivations);			
			if (match(Token.T_KEYWORD_NOT, unaryOperator) && tree.add(unaryOperator, AbstractSyntaxTreeNode.NODE_OPERATOR) && rule_factor(tree)) { return true; } return false; };
		if (isLHIn(FIRST_3)) {
			String[] derivations = {"num"}; m_derivation.replace(derivations);			
			if (rule_num(num) && tree.add(num, AbstractSyntaxTreeNode.NODE_CONSTANT)) { return true; } return false; };
		if (isLHIn(FIRST_4)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_DELIMITER_LEFT_PARENTHESIS), "arithExpr", Token.getTypeReadableFormatString(Token.T_DELIMITER_RIGHT_PARENTHESIS)}; m_derivation.replace(derivations);			
			if (match(Token.T_DELIMITER_LEFT_PARENTHESIS) && rule_arithExpr(tree) && match(Token.T_DELIMITER_RIGHT_PARENTHESIS)) { return true; } return false; };
		if (isLHIn(FIRST_5)) {
			String[] derivations = {"variable_or_functionCall"}; m_derivation.replace(derivations);			
			if (rule_variable_or_functionCall(tree)) { return true; } return false; };
					
		return false;
	}
	
	
	private boolean rule_relOp(Attribute relOp) {
		int[] FIRST = { Token.T_OPERATOR_LESSERTHAN, Token.T_OPERATOR_LESSERTHAN_OR_EQ, Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN, Token.T_OPERATOR_GREATERTHAN, Token.T_OPERATOR_GREATERTHAN_OR_EQ, Token.T_OPERATOR_EQ };
		
		int[] FIRST_1 = { Token.T_OPERATOR_LESSERTHAN};
		int[] FIRST_2 = { Token.T_OPERATOR_LESSERTHAN_OR_EQ};
		int[] FIRST_3 = { Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN};
		int[] FIRST_4 = { Token.T_OPERATOR_GREATERTHAN};
		int[] FIRST_5 = { Token.T_OPERATOR_GREATERTHAN_OR_EQ};
		int[] FIRST_6 = { Token.T_OPERATOR_EQ};

		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_LESSERTHAN)}; m_derivation.replace(derivations);			
			if (match(Token.T_OPERATOR_LESSERTHAN, relOp)) { return true; } return false; }
		if (isLHIn(FIRST_2)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_LESSERTHAN_OR_EQ)}; m_derivation.replace(derivations);			
			if (match(Token.T_OPERATOR_LESSERTHAN_OR_EQ, relOp)) { return true; } return false; }
		if (isLHIn(FIRST_3)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN)}; m_derivation.replace(derivations);			
			if (match(Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN, relOp)) { return true; } return false; }
		if (isLHIn(FIRST_4)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_GREATERTHAN)}; m_derivation.replace(derivations);			
			if (match(Token.T_OPERATOR_GREATERTHAN, relOp)) { return true; } return false; }
		if (isLHIn(FIRST_5)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_GREATERTHAN_OR_EQ)}; m_derivation.replace(derivations);			
			if (match(Token.T_OPERATOR_GREATERTHAN_OR_EQ, relOp)) { return true; } return false; }
		if (isLHIn(FIRST_6)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_EQ)}; m_derivation.replace(derivations);			
			if (match(Token.T_OPERATOR_EQ, relOp)) { return true; } return false; }
		
		return false;
	}
	
	private boolean rule_addOp(Attribute attribute) {
		int[] FIRST = { Token.T_KEYWORD_OR, Token.T_OPERATOR_MINUS, Token.T_OPERATOR_PLUS};

		int[] FIRST_1 = { Token.T_KEYWORD_OR };
		int[] FIRST_2 = { Token.T_OPERATOR_MINUS };
		int[] FIRST_3 = { Token.T_OPERATOR_PLUS };
		
		if (!skipErrors(FIRST, null)) { return false; }
		
		
		if (isLHIn(FIRST_1)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_OR)}; m_derivation.replace(derivations);			
			if (match(Token.T_KEYWORD_OR, attribute)) { return true; } return false; }
		if (isLHIn(FIRST_2)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_MINUS)}; m_derivation.replace(derivations);			
			if (match(Token.T_OPERATOR_MINUS, attribute)) { return true; } return false; }
		if (isLHIn(FIRST_3)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_PLUS)}; m_derivation.replace(derivations);			
			if (match(Token.T_OPERATOR_PLUS, attribute)) { return true; } return false; }
		
		return false;
	}
	
	private boolean rule_multOp(Attribute attribute) {
		int[] FIRST = { Token.T_KEYWORD_AND, Token.T_OPERATOR_DIV, Token.T_OPERATOR_MULTIPLY };
		int[] FIRST_1 = { Token.T_KEYWORD_AND };
		int[] FIRST_2 = { Token.T_OPERATOR_DIV };
		int[] FIRST_3 = { Token.T_OPERATOR_MULTIPLY };
		
		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_KEYWORD_AND)}; m_derivation.replace(derivations);			
			if (match(Token.T_KEYWORD_AND, attribute)) { return true; } return false; }
		if (isLHIn(FIRST_2)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_DIV)}; m_derivation.replace(derivations);			
			if (match(Token.T_OPERATOR_DIV, attribute)) { return true; } return false; }
		if (isLHIn(FIRST_3)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_OPERATOR_MULTIPLY)}; m_derivation.replace(derivations);		
			if (match(Token.T_OPERATOR_MULTIPLY, attribute)) { return true; } return false; }
					
		return false;
	}
	
	private boolean rule_num(Attribute attribute) {
		
		int[] FIRST = { Token.T_INTEGER, Token.T_FLOAT };
		
		int[] FIRST_1 = { Token.T_FLOAT };
		int[] FIRST_2 = { Token.T_INTEGER };
		
		if (!skipErrors(FIRST, null)) { return false; }
		
		if (isLHIn(FIRST_1)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_FLOAT)}; m_derivation.replace(derivations);			
			if (match(Token.T_FLOAT, attribute)) { return true; } return false; }
		if (isLHIn(FIRST_2)) {
			String[] derivations = {Token.getTypeReadableFormatString(Token.T_INTEGER)}; m_derivation.replace(derivations);			
			if (match(Token.T_INTEGER, attribute)) { return true; } return false; }
		
		return false;

	}	
	
	
	public void setLexicalAnalyzer(LexicalAnalyzer lexicalAnalyzer) {
		m_scanner = lexicalAnalyzer;
	}
	
	public void setSemanticAnalyzer(SemanticAnalyzer semanticAnalyzer) {
		m_semanticAnalyzer = semanticAnalyzer;
	}
	
	public void setCodeGenerator(CodeGenerator codeGenerator) {
		m_codeGenerator = codeGenerator;
	}
	
	public void lockOutput() {
		m_outputSyntaxError = false;		
		m_derivation.disable();
	}
	
	public int getErrorCount() {
		return m_errors;
	}

	
}