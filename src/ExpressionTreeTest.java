
public class ExpressionTreeTest {

	//	expr 			-> arithExpr(tree) expr_tail(tree)
	//	relExpr         -> arithExpr relOp arithExpr
	//	expr_tail 		-> trelOp ADD_ROOT(relOp) arithExpr | EPSILON
	//	arithExpr       -> term(tree) arithExpr_rr
	//	arithExpr_rr	-> ADD_PARENT_CURRENT_NODE addOp SETVALUE_CURRENT_NODE(addOp) bNode = BACKUP_CURRENT_NODE term(tree) arithExpr_rr(bNode) | EPSILON
	//	sign            -> '+' | '-'
	//	term            -> factor(tree) term_rr(tree)
	//	term_rr         -> ADD_PARENT_CURRENT_NODE multOp SETVALUE_CURRENT_NODE(multOp) factor(tree) term_rr | EPSILON
	//	factor          -> variable_or_functionCall(tree)
	//	                | num ADD(num)
	//	                | '(' arithExpr(tree) ')'
	//	                | 'not' factor
	//	                | sign ADD(SIGN)factor(tree)
	
	
	private String m_input;
	
	public ExpressionTreeTest() {
		
		m_input = "4+2*3";
		
	}
	
	
	
	public void expr() {
		
		int[] FIRST = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};
		int[] FIRST_1 = {Token.T_DELIMITER_LEFT_PARENTHESIS, Token.T_FLOAT, Token.T_INTEGER, Token.T_KEYWORD_NOT, Token.T_IDENTIFIER, Token.T_OPERATOR_PLUS, Token.T_OPERATOR_MINUS};

		
		
	}
	

}
