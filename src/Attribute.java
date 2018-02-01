import java.util.ArrayList;

public class Attribute {

    private int                 				m_intValue;
    private String                 				m_strValue;
    private ArrayList<Integer>   				m_integerList;
    private Attribute							m_next;
    private ArrayList<AbstractSyntaxTree>		m_expressionTrees;
    private	boolean								m_isFunction;
    
    private	int						m_line;
    private	int						m_column;
   
   
    public Attribute() {
       m_integerList = new ArrayList<Integer>();
       m_expressionTrees = new ArrayList<AbstractSyntaxTree>();
       
    }
    
    public void setFunction() {
    	m_isFunction = true;
    }
    
    public boolean isFunction() {
    	return m_isFunction;
    }
    
    public int getIntValue() {
    	return m_intValue;
    }
   
    public String getStrValue() {
    	return m_strValue;
    }
    
    public int getIntegerListSize() {
    	return m_integerList.size();
    }
    
    public ArrayList<Integer> getIntegerList() {
    	return m_integerList;
    }
    
    public Attribute getNext() {
    	return m_next;
    }
    
    public void addIntegerToList(int value) {
    	m_integerList.add(value);
    }
    
    public void setIntValue(int value) {
    	m_intValue = value;
    }
    
    public void setStrValue(String value) {
    	m_strValue = value;
    }
    
    public void setNext(Attribute attribute) {
    	m_next = attribute;
    }
    
    public void setLine(int line) {
    	m_line = line;
    }
   
    public void setColumn(int column) {
    	m_column = column;
    }
    
    public void addExpressionTree(AbstractSyntaxTree expression) {
    	
    	m_expressionTrees.add(expression);
    	
    }
    
    public ArrayList<AbstractSyntaxTree> getExpressionTrees() {
    	
    	return m_expressionTrees;
    	
    }
    
    public int getExpressionTreeArraySize() {
    	
    	return m_expressionTrees.size();
    	
    }
    
    public int getLine() {
    	return m_line;
    }
    
    public int getColumn() {
    	return m_column;
    }
    
    public void removeExpressionTreeArray() {
    	m_expressionTrees = new ArrayList<AbstractSyntaxTree>();
    }
    
}