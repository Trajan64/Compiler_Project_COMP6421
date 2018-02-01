
public class AbstractSyntaxTree {

	
	//	expr 			-> arithExpr(tree) expr_tail(tree)
	//	relExpr         -> arithExpr relOp arithExpr
	//	expr_tail 		-> trelOp ADD_ROOT(relOp) arithExpr | EPSILON
	//	arithExpr       -> term(tree) arithExpr_rr(tree)
	//	arithExpr_rr	-> ADD_PARENT_CURRENT_NODE addOp SETVALUE_CURRENT_NODE(addOp) bNode = BACKUP_CURRENT_NODE term(tree) arithExpr_rr(bNode) | EPSILON
	//	sign            -> '+' | '-'
	//	term            -> factor(tree) term_rr(tree)
	//	term_rr         -> ADD_PARENT_CURRENT_NODE multOp SETVALUE_CURRENT_NODE(multOp) factor(tree) term_rr | EPSILON
	//	factor          -> variable_or_functionCall(tree)
	//	                | num ADD(num)
	//	                | '(' arithExpr(tree) ')'
	//	                | 'not' factor
	//	                | sign ADD(SIGN)factor(tree)

	

	private AbstractSyntaxTreeNode m_root;
	private AbstractSyntaxTreeNode m_current;
	
	
	public AbstractSyntaxTree() {
		
	}
	

	public boolean moveUp() {
		
		m_current = m_current.getParent();
		
		return true;
		
	}
	
	public boolean add(Attribute attribute, int kind) {
				
		if (m_root == null) {
			
			AbstractSyntaxTreeNode root = new AbstractSyntaxTreeNode();
			root.setValue(attribute, kind);
			
			m_root = root;
			m_current = root;
			
			
			
		}
		
		else {
		
			AbstractSyntaxTreeNode node = new AbstractSyntaxTreeNode();
			node.setValue(attribute, kind);
			
			m_current.addChild(node);
			
			m_current = node;
			
		}
		
		//System.out.println(toString());
		
		return true;
		
	}
	
	public boolean addParent(Attribute attribute, int kind) {
		
		AbstractSyntaxTreeNode newParent = new AbstractSyntaxTreeNode();
		newParent.setValue(attribute, kind);
		AbstractSyntaxTreeNode oldParent = m_current.getParent();
		
		if (oldParent != null) { 
		
			if (m_current.getParent().getLeft() == m_current) {
				
				oldParent.setLeftChild(newParent);
				
				
			}
			
			else {
				
				oldParent.setRightChild(newParent);
				
			}
			
			newParent.setParent(oldParent);
			
		}
		
		else {
			
			// This is the root.
			// Update root.
			m_root = newParent;
			
		}
				
		newParent.addChild(m_current);
		toString();
		
		return true;
		
	}
	
	public AbstractSyntaxTreeNode getCurrent() {
		
		return m_current;
		
	}
	
	public boolean setCurrent(AbstractSyntaxTreeNode node) {
		
		m_current = node;
		
		return true;
		
	}
	
	
	public void moveLeft() {
		
		m_current = m_current.getLeft();
		
	}
	
	public void moveRight() {
		
		m_current = m_current.getRight();
		
	}
	
	
	public String toString() {

		return m_root.toString();
		
	}
	
	// For debugging purposes
	public int evaluate() {
		
		return m_root.evaluate();
		
	}
	
	/*
	public void setRegisterNeeds() {
	
		m_root.setRegisterNeeds();
	
	}
	*/
	
	public AbstractSyntaxTreeNode getRoot() {
		
		return m_root;
		
	}

}
