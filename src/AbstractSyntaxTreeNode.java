
public class AbstractSyntaxTreeNode {

	public static final int NODE_FUNCTION = 0;
	public static final int NODE_VARIABLE = 1;
	public static final int NODE_OPERATOR = 2;
	public static final int NODE_CONSTANT = 3;
	
	private AbstractSyntaxTreeNode m_left;
	private AbstractSyntaxTreeNode m_right;
	private AbstractSyntaxTreeNode m_parent;
	
	private Attribute 	m_value;
	private int			m_kind;
	
	
	public AbstractSyntaxTreeNode() {
		
	}
	
	public void setParent(AbstractSyntaxTreeNode node) {
		
		m_parent = node;
		
	}
	
	public AbstractSyntaxTreeNode getParent() {
		
		return m_parent;
		
	}
	
	
	public void addChild(AbstractSyntaxTreeNode node) {
		
		node.setParent(this);
		
		if (m_left == null) {
			m_left = node;
		}
		else {
			m_right = node;
		}
		
		
	}
	
	public void setLeftChild(AbstractSyntaxTreeNode node) {
		
		m_left = node;
		
	}
	
	public void setRightChild(AbstractSyntaxTreeNode node) {
		
		m_right = node;
		
	}
	
	public AbstractSyntaxTreeNode getLeft() {
		
		return m_left;
		
	}
	
	public AbstractSyntaxTreeNode getRight() {
		
		return m_right;
		
	}
	
	public void setValue(Attribute value, int kind) {
		
		m_value = value;
		m_kind = kind;
		
	}
	
	
	public Attribute getValue() {
		
		return m_value;
		
	}
	
	public int getKind() {
		
		return m_kind;
		
	}
	
	
	public String toString() {
		
		String string = "(";
		
		if (m_left != null) {
			string += m_left.toString();
		}
		
		if (m_value.getStrValue() == null) {
		
			string += Token.getTypeReadableFormatString(m_value.getIntValue());
		
		}
		
		
		else {
			
			string += m_value.getStrValue();
			
		}
		
		if (m_right != null) {
			string += m_right.toString();
		}
		
		return string + ")";
		
	}
	
	public int evaluate() {
		
		switch(m_value.getIntValue()) {
		case Token.T_INTEGER:
			return Integer.parseInt(m_value.getStrValue());
		
		case Token.T_OPERATOR_MULTIPLY:
			return m_left.evaluate() * m_right.evaluate();
			
		case Token.T_OPERATOR_DIV:
			return m_left.evaluate() / m_right.evaluate();

		case Token.T_OPERATOR_PLUS:
			if (m_right == null) {
				// unary operator
				return + m_left.evaluate();
			}
			return m_left.evaluate() + m_right.evaluate();
			
		case Token.T_OPERATOR_MINUS:
			if (m_right == null) {
				// unary operator
				return - m_left.evaluate();
			}
			return m_left.evaluate() - m_right.evaluate();
			
		}
		
		return -200;
		
	}
	
	
	public boolean isLeaf() {
		
		if ((m_left == null) && (m_right == null)) {
		
			return true;
			
		}
		
		return false;
			
	}
	
	/*
	public void setRegisterNeeds() {
		
		
		if ((m_value.getIntValue() == Token.T_INTEGER) || (m_value.getIntValue() == Token.T_FLOAT) || (m_value.getIntValue() == Token.T_IDENTIFIER)) {
			
			m_registerCount = 1;
			
		}
		
		else {
			
			if ((m_right == null) && (m_value.getIntValue() == Token.T_OPERATOR_MINUS) || (m_value.getIntValue() == Token.T_OPERATOR_PLUS) || (m_value.getIntValue() == Token.T_KEYWORD_NOT)) {
				
				m_left.setRegisterNeeds();
				return;
			}
			
			m_left.setRegisterNeeds();
			
			
		}
		
		
	}
	
	*/
	

}
