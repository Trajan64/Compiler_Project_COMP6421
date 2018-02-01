
public class Token {

	private int    	type;
	private String  lexeme;
	private boolean	erroenous;
		
	
	private int col;
	private int line;
	
	public final static int T_IDENTIFIER						= 0;
	public final static int T_FLOAT								= 1;
	public final static int T_INTEGER							= 2;
	public final static int T_COMMENT							= 3;
	public final static int T_UNKNOWN							= 4;
	public final static int T_EOF								= 5;
	public final static int T_KEYWORD_IF						= 6;
	public final static int T_KEYWORD_THEN						= 7;
	public final static int T_KEYWORD_ELSE						= 8;
	public final static int T_KEYWORD_FOR						= 9;
	public final static int T_KEYWORD_CLASS						= 10;
	public final static int T_KEYWORD_INT						= 11;
	public final static int T_KEYWORD_FLOAT						= 12;
	public final static int T_KEYWORD_GET						= 13;
	public final static int T_KEYWORD_PUT						= 14;
	public final static int T_KEYWORD_RETURN					= 15;
	public final static int T_KEYWORD_AND						= 16;
	public final static int T_KEYWORD_NOT						= 17;
	public final static int T_KEYWORD_OR						= 18;
	public final static int T_KEYWORD_PROGRAM					= 19;
	public final static int T_OPERATOR_DIV						= 20;
	public final static int T_OPERATOR_EQ						= 21;
	public final static int T_OPERATOR_GREATERTHAN_OR_EQ		= 22;
	public final static int T_OPERATOR_GREATERTHAN_OR_LESSERTHAN= 23;
	public final static int T_OPERATOR_GREATERTHAN				= 24;
	public final static int T_OPERATOR_LESSERTHAN_OR_EQ			= 25;
	public final static int T_OPERATOR_LESSERTHAN				= 26;
	public final static int T_OPERATOR_PLUS						= 27;
	public final static int T_OPERATOR_MINUS					= 28;
	public final static int T_OPERATOR_MULTIPLY					= 29;
	public final static int T_DELIMITER_QUOTE					= 30;
	public final static int T_DELIMITER_ASSIGN					= 31;
	public final static int T_DELIMITER_SEMICOLON				= 32;
	public final static int T_DELIMITER_DOT						= 33;
	public final static int T_DELIMITER_LEFT_PARENTHESIS		= 34;
	public final static int T_DELIMITER_RIGHT_PARENTHESIS		= 35;
	public final static int T_DELIMITER_LEFT_BRACKET			= 36;
	public final static int T_DELIMITER_RIGHT_BRACKET			= 37;
	public final static int T_DELIMITER_LEFT_SQ_BRACKET			= 38;
	public final static int T_DELIMITER_RIGHT_SQ_BRACKET		= 39;
	public final static int T_EPSILON							= 40;
	public final static int T_DELIMITER_COMMA					= 41;
	
	
	
	public Token(int type, String lexeme, boolean erroneous, int line, int col) {
		
		this.type = type;
		this.lexeme = lexeme;
		this.erroenous = erroneous;
		this.line = line;
		this.col = col;
		
	}
	
	public static String getTypeString(int type) {
		
		switch(type) {
		
			case T_IDENTIFIER: 			return "T_IDENTIFIER";
			case T_FLOAT: 				return "T_FLOAT";
			case T_INTEGER: 			return "T_INTEGER";
			case T_COMMENT: 			return "T_COMMENT";
			case T_UNKNOWN: 			return "T_UNKNOWN";
			case T_EOF:					return "T_EOF";
			case T_KEYWORD_IF: 			return "T_KEYWORD_IF";
			case T_KEYWORD_THEN: 		return "T_KEYWORD_THEN";
			case T_KEYWORD_ELSE: 		return "T_KEYWORD_ELSE";
			case T_KEYWORD_FOR: 		return "T_KEYWORD_FOR";
			case T_KEYWORD_CLASS: 		return "T_KEYWORD_CLASS";
			case T_KEYWORD_INT: 		return "T_KEYWORD_INT";
			case T_KEYWORD_FLOAT: 		return "T_KEYWORD_FLOAT";
			case T_KEYWORD_GET: 		return "T_KEYWORD_GET";
			case T_KEYWORD_PUT: 		return "T_KEYWORD_PUT";
			case T_KEYWORD_RETURN: 		return "T_KEYWORD_RETURN";
			case T_KEYWORD_AND: 		return "T_KEYWORD_AND";
			case T_KEYWORD_NOT: 		return "T_KEYWORD_NOT";
			case T_KEYWORD_OR: 			return "T_KEYWORD_OR";
			case T_KEYWORD_PROGRAM: 	return "T_KEYWORD_PROGRAM";

			case T_OPERATOR_DIV: 							return "T_OPERATOR_DIV";
			case T_OPERATOR_EQ: 							return "T_OPERATOR_EQ";
			case T_OPERATOR_GREATERTHAN_OR_EQ: 				return "T_OPERATOR_GREATERTHAN_OR_EQ";
			case T_OPERATOR_GREATERTHAN_OR_LESSERTHAN: 		return "T_OPERATOR_GREATERTHAN_OR_LESSERTHAN";
			case T_OPERATOR_GREATERTHAN: 					return "T_OPERATOR_GREATERTHAN";
			case T_OPERATOR_LESSERTHAN_OR_EQ: 				return "T_OPERATOR_LESSERTHAN_OR_EQ";
			case T_OPERATOR_LESSERTHAN: 					return "T_OPERATOR_LESSERTHAN";
			case T_OPERATOR_PLUS: 							return "T_OPERATOR_PLUS";
			case T_OPERATOR_MINUS: 							return "T_OPERATOR_MINUS";
			case T_OPERATOR_MULTIPLY: 						return "T_OPERATOR_MULTIPLY";
			
			
			case T_DELIMITER_QUOTE: 				return "T_DELIMITER_QUOTE";
			case T_DELIMITER_ASSIGN: 				return "T_DELIMITER_ASSIGN";
			case T_DELIMITER_SEMICOLON: 			return "T_DELIMITER_SEMICOLON";
			case T_DELIMITER_DOT: 					return "T_DELIMITER_DOT";
			case T_DELIMITER_LEFT_PARENTHESIS: 		return "T_DELIMITER_LEFT_PARENTHESIS";
			case T_DELIMITER_RIGHT_PARENTHESIS: 	return "T_DELIMITER_RIGHT_PARENTHESIS";
			case T_DELIMITER_LEFT_BRACKET: 			return "T_DELIMITER_LEFT_BRACKET";
			case T_DELIMITER_RIGHT_BRACKET: 		return "T_DELIMITER_RIGHT_BRACKET";
			case T_DELIMITER_LEFT_SQ_BRACKET: 		return "T_DELIMITER_LEFT_SQ_BRACKET";
			case T_DELIMITER_RIGHT_SQ_BRACKET: 		return "T_DELIMITER_RIGHT_SQ_BRACKET";
			case T_DELIMITER_COMMA:					return "T_DELIMITER_COMMA";
			
			default:
				// We should not go through here.
				System.out.println("Error: unknown token value " + type);
				return "";

			

		}
		
	}
	
	public static String getTypeReadableFormatString(int type) {
		
		switch(type) {
		
			case T_IDENTIFIER: 			return "id";
			case T_FLOAT: 				return "float";
			case T_INTEGER: 			return "integer";
			case T_COMMENT: 			return "comment";
			case T_EOF:					return "end of file";
			case T_UNKNOWN: 			return "unknown token";
			case T_KEYWORD_IF: 			return "'if'";
			case T_KEYWORD_THEN: 		return "'then'";
			case T_KEYWORD_ELSE: 		return "'else'";
			case T_KEYWORD_FOR: 		return "'for'";
			case T_KEYWORD_CLASS: 		return "'class'";
			case T_KEYWORD_INT: 		return "'int'";
			case T_KEYWORD_FLOAT: 		return "'float'";
			case T_KEYWORD_GET: 		return "'get'";
			case T_KEYWORD_PUT: 		return "'put'";
			case T_KEYWORD_RETURN: 		return "'return'";
			case T_KEYWORD_AND: 		return "'and'";
			case T_KEYWORD_NOT: 		return "'not'";
			case T_KEYWORD_OR: 			return "'or'";
			case T_KEYWORD_PROGRAM: 	return "'program'";

			case T_OPERATOR_DIV: 							return "'/'";
			case T_OPERATOR_EQ: 							return "'=='";
			case T_OPERATOR_GREATERTHAN_OR_EQ: 				return "'>=";
			case T_OPERATOR_GREATERTHAN_OR_LESSERTHAN: 		return "'<>'";
			case T_OPERATOR_GREATERTHAN: 					return "'>'";
			case T_OPERATOR_LESSERTHAN_OR_EQ: 				return "'<='";
			case T_OPERATOR_LESSERTHAN: 					return "'<'";
			case T_OPERATOR_PLUS: 							return "'+'";
			case T_OPERATOR_MINUS: 							return "'-'";
			case T_OPERATOR_MULTIPLY: 						return "'*'";
			
			
			case T_DELIMITER_QUOTE: 				return "\"\'\"";
			case T_DELIMITER_ASSIGN: 				return "'='";
			case T_DELIMITER_SEMICOLON: 			return "';'";
			case T_DELIMITER_DOT: 					return "'.'";
			case T_DELIMITER_LEFT_PARENTHESIS: 		return "'('";
			case T_DELIMITER_RIGHT_PARENTHESIS: 	return "')'";
			case T_DELIMITER_LEFT_BRACKET: 			return "'{'";
			case T_DELIMITER_RIGHT_BRACKET: 		return "'}'";
			case T_DELIMITER_LEFT_SQ_BRACKET: 		return "'['";
			case T_DELIMITER_RIGHT_SQ_BRACKET: 		return "']'";
			case T_DELIMITER_COMMA:					return "','";
			
			default:
				// We should not go through here.
				System.out.println("Error: unknown token value " + type);
				return "";

			

		}
		
	}
	
	
	public int getType() {
		return type;
	}
	
	public String getLexeme() {
		return lexeme;
	}
	
	
	public int getLine() {
		return line;
	}
	
	public int getColumn() {
		return col;
	}
	

	
	public String toString() {
		
		String errString = "";
		if (erroenous == true) {
			errString = " ERRONEOUS";
			
		}
		
		return "<" + '(' + Integer.toString(line) + ',' + Integer.toString(col) + ')' + ' ' + getTypeString(type) + " '" + lexeme + "'" + errString + ">";
		
	}
	
}
