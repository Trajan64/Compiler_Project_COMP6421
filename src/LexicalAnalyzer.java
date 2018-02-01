import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LexicalAnalyzer {

	private InputStream 	input;
	private OutputStream 	errorOutput;
	private OutputStream	m_tokenOutput;
	
	private boolean 		backupFlag = false;
	private boolean 		scanningCompleted = false;
	private int				currentByte;
	private int				col = 0;
	private int				line = 1;
	private int				refLine;
	private int				refCol;
	
	private	int				errors;
			
	private final static char EOF = 0;
	
	private boolean			m_lockedOutput = false;
	private String			m_programName;
	private int				m_tokenLineBuffer;
	
	
	public LexicalAnalyzer(InputStream stream, OutputStream errorOutput, OutputStream tokenOutput, String programName) {
		
		input = stream;
		this.errorOutput = errorOutput;
		m_programName = programName;
		m_tokenOutput = tokenOutput;
		
		m_tokenLineBuffer = 1;
		
	}	
	
	public void lockOutput() {
		
		m_lockedOutput = true;
		
	}
	
	
	public Token nextToken() {
		
		Token nextToken = extractToken();
		
		outputToken(nextToken);
		
		return nextToken;
		
	}
	
	
	// Returns the next token in the pre-specified input stream
	public Token extractToken() {
				
		char c = nextChar();
		String stringBuffer = new String();
		
		// Ignore all white spaces.
		while (Character.isWhitespace(c)) {
			c = nextChar();
		}
		
		
		refLine = line;
		refCol = col;
		
		// Check if we have processed all the characters in the program.
		if (c == EOF) {
			scanningCompleted = true;
			return createToken(Token.T_EOF, null, false);
		}
		
		if (Character.isLetter(c)) {
			stringBuffer += c;
			c = nextChar();
			while (Character.isLetter(c) || Character.isDigit(c) || c == '_') {
				stringBuffer += c;
				c = nextChar();
			}
			backChar();
			// Check if string is a reserved keyword.
			
			if (stringBuffer.equals("if")) 			{ return createToken(Token.T_KEYWORD_IF, 		stringBuffer, false); }
			if (stringBuffer.equals("then")) 		{ return createToken(Token.T_KEYWORD_THEN, 		stringBuffer, false); }
			if (stringBuffer.equals("else"))		{ return createToken(Token.T_KEYWORD_ELSE, 		stringBuffer, false); }
			if (stringBuffer.equals("for"))			{ return createToken(Token.T_KEYWORD_FOR, 		stringBuffer, false); }
			if (stringBuffer.equals("class")) 		{ return createToken(Token.T_KEYWORD_CLASS, 		stringBuffer, false); }
			if (stringBuffer.equals("int")) 		{ return createToken(Token.T_KEYWORD_INT, 		stringBuffer, false); }
			if (stringBuffer.equals("float")) 		{ return createToken(Token.T_KEYWORD_FLOAT, 		stringBuffer, false); }
			if (stringBuffer.equals("get")) 		{ return createToken(Token.T_KEYWORD_GET, 		stringBuffer, false); }
			if (stringBuffer.equals("put")) 		{ return createToken(Token.T_KEYWORD_PUT, 		stringBuffer, false); }
			if (stringBuffer.equals("return")) 		{ return createToken(Token.T_KEYWORD_RETURN, 		stringBuffer, false); }
			if (stringBuffer.equals("and"))			{ return createToken(Token.T_KEYWORD_AND, 		stringBuffer, false); }
			if (stringBuffer.equals("not"))			{ return createToken(Token.T_KEYWORD_NOT, 		stringBuffer, false); }
			if (stringBuffer.equals("or"))			{ return createToken(Token.T_KEYWORD_OR, 		stringBuffer, false); }
			if (stringBuffer.equals("program"))		{ return createToken(Token.T_KEYWORD_PROGRAM, 		stringBuffer, false); }

			// If the accumulated string does not match any of the reserved keywords, we then have an identifier.
			return createToken(Token.T_IDENTIFIER, stringBuffer, false);
		}
		
		if (Character.isDigit(c)) {
			stringBuffer += c;
			
			if (c == '0') {
				c = nextChar();
			}
			
			else {
				c = nextChar();
				while (Character.isDigit(c)) {
					stringBuffer += c;
					c = nextChar();
				}
			}
			
			if (c == '.') {
				
				stringBuffer += c;
				// it may be a float.
				c = nextChar();
				// Check if it's followed by a digit.
				if (Character.isDigit(c)) {
					stringBuffer += c;
					c = nextChar();
					// Capture all characters that are non-zero digits.
					while (Character.isDigit(c)) {
						stringBuffer += c;
						c = nextChar();
					}
					// Non digit character: we have processed a full float token.
					backChar();
					// Create float token.
					return createToken(Token.T_FLOAT, stringBuffer, false);
				}
				else {
					stringBuffer += c;
					// Error: the floating point should have been followed by at least a single digit.
					outputError(refLine, refCol, "badly formed float; expected digit after '.' got '" + c + "' instead");
					return createToken(Token.T_FLOAT, stringBuffer, true);
					
				}
			}
			backChar();
			// Create integer token
			return createToken(Token.T_INTEGER, stringBuffer, false);					
		}
		
		
		if (c == '/') {
			c = nextChar();
			if (c == '/') {
				c = nextChar();
				while (c != '\n' && c != EOF) {
					c = nextChar();
				}
				backChar();
				createToken(Token.T_COMMENT, null, false);
				// Ignore comment token and fetch the following token.
				return nextToken();
			}
			else if (c == '*') {
				c = nextChar();
				while (c != EOF) {
					if (c == '*') {
						c = nextChar();
						if (c == '/') {
							// Create comment token
							 createToken(Token.T_COMMENT, null, false);	
							 return nextToken();
						}
						if (c == EOF) {
							// Error: multiline comment missing closure.
							outputError(refLine, refCol, "unclosed multi line comment.");
							createToken(Token.T_COMMENT, null, true);	
							return nextToken();
						}
					}
					c = nextChar();
				}
				
				// Error: multi-line comment missing closure.
				outputError(refLine, refCol, "unclosed multi-line comment.");
				createToken(Token.T_COMMENT, null, true);
				return nextToken();
			}
			else {
				// It's a division operator
				backChar();
				return createToken(Token.T_OPERATOR_DIV, null, false);												
			}
		}
		
		if (c == '=') {
			c = nextChar();
			if (c == '=') {
				return createToken(Token.T_OPERATOR_EQ, null, false);												
			}
			else {
				backChar();
				return createToken(Token.T_DELIMITER_ASSIGN, null, false);												
			}
		}
		
		if (c == '<') {
			c = nextChar();
			if (c == '=') {
				return createToken(Token.T_OPERATOR_LESSERTHAN_OR_EQ, null, false);												
			}
			if (c == '>') {
				return createToken(Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN, null, false);												
			}
			
			backChar();
			return createToken(Token.T_OPERATOR_LESSERTHAN, null, false);
		}
		
		if (c == '>') {
			c = nextChar();
			if (c == '=') {
				// Create greater than or eq token
				return createToken(Token.T_OPERATOR_GREATERTHAN_OR_EQ, null, false);
			}
			backChar();
			// Create greater than token
			return createToken(Token.T_OPERATOR_GREATERTHAN, null, false);
			
		}
		
		if (c == '\'') { return createToken(Token.T_DELIMITER_QUOTE, null, false);	}
		if (c == ';') {  return createToken(Token.T_DELIMITER_SEMICOLON, null, false); }
		if (c == '.') {  return createToken(Token.T_DELIMITER_DOT, null, false); }
		if (c == '+') {  return createToken(Token.T_OPERATOR_PLUS, null, false); }
		if (c == '-') {  return createToken(Token.T_OPERATOR_MINUS, null, false); }
		if (c == '*') {  return createToken(Token.T_OPERATOR_MULTIPLY, null, false); }
		if (c == '(') {  return createToken(Token.T_DELIMITER_LEFT_PARENTHESIS, null, false); }
		if (c == ')') {  return createToken(Token.T_DELIMITER_RIGHT_PARENTHESIS, null, false); }
		if (c == '{') {  return createToken(Token.T_DELIMITER_LEFT_BRACKET, null, false); }
		if (c == '}') {  return createToken(Token.T_DELIMITER_RIGHT_BRACKET, null, false); }
		if (c == '[') {  return createToken(Token.T_DELIMITER_LEFT_SQ_BRACKET, null, false); }
		if (c == ']') {  return createToken(Token.T_DELIMITER_RIGHT_SQ_BRACKET, null, false); }
		if (c == ',') {  return createToken(Token.T_DELIMITER_COMMA, null, false); }
		
		// Error: unknown token.
		stringBuffer += c;
		outputError(refLine, refCol, "unknown token.");
		return createToken(Token.T_UNKNOWN, stringBuffer, true);
		
	}

	
	
	
	// Returns the next character in the input stream.
	private char nextChar() {
		
		// There is no need to continue if the scanner has completed scanning the entire program.
		if (scanningCompleted) {
			return EOF;
		}
		
		// If backupFlag is set then we must return the previously read character.
		if (backupFlag) {			
			backupFlag = false;
		}
		else {
			// Read the next character available that we need to scan.
			try {
				currentByte = input.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// Modify cursor position.
			if (currentByte == '\n' || currentByte == '\t') {
				if (currentByte == '\n') { col = 0; line += 1; }			
				if (currentByte == '\t') { col += 4; }
			}
			else { col += 1; }
			
		}
		
		if (currentByte == -1) {
			// The end of the source program has been reached.
			return EOF;
		}
		
		
		return (char) currentByte;
		
	}
		
	
	
	private void backChar() {
		
		backupFlag = true;
		
	}
	
	public boolean isScanningComplete() {
		return scanningCompleted;
	}
	
	
	public int getErrorCount() {
		return errors;
	}
	
	
	private void outputError(int line, int col, String message) {
		
		errors++;
		
		if (m_lockedOutput) { return; }
		
		// Send error message to output.
		message = "Lexical error:" + " (" + Integer.toString(line) + ',' + Integer.toString(col) + ") " + message + "\r\n";
		byte b[] = message.getBytes();
		
		// Write token to output
		try {
			errorOutput.write(b, 0, message.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private Token createToken(int type, String value, boolean erroneous) {		
		
		Token token = new Token(type, value, erroneous, refLine, refCol);
		return token;
		
	}
	
	public void rewindProgram() {
		
		
		backupFlag = false;
		scanningCompleted = false;
		col = 0;
		line = 1;
		refLine = 0;
		refCol = 0;
		
		m_tokenLineBuffer = 1;
		
		FileInputStream freshInputStream = null;
		try {
			freshInputStream = new FileInputStream(m_programName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		input = freshInputStream;
		
	}
	
	private void outputToken(Token token) {
		
		/* We add newlines characters in order for the output string token to match the line number of the token's input string in the source program. 
		This is done so as to ease the visual comparison between the source program and the token output file.
		*/
		
		if (m_lockedOutput) { return; }
		
		int tokenLine = token.getLine();
		String newlines = "";
		int i;
		for (i = 0; i < (tokenLine - m_tokenLineBuffer); i++) {
			newlines += "\r\n";
		}
		
		String sToken = newlines + token.toString() + ' ';
		byte b[] = sToken.getBytes();
		
		// Write token to output
		try {
			m_tokenOutput.write(b, 0, sToken.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		m_tokenLineBuffer = tokenLine;

	}
	
	
}
