
import java.util.ArrayList;


public class SemanticRecord {

    public final static int KIND_CLASS        	= 0;
    public final static int KIND_VARIABLE    	= 1;
    public final static int KIND_PARAMETER    	= 2;
    public final static int KIND_FUNCTION    	= 3;
    public final static int KIND_METHOD 		= 4;
    
    public final static int SIZE_INT			= 4;
    public final static int SIZE_FLOAT			= 4;
   
    private String                 	m_name;
    private int                    	m_kind;
    private int                 	m_type;
    private String               	m_typeName;
    private ArrayList<Integer>     	m_dimensions;
    private	int						m_size;
    private	int						m_address;
    private boolean					m_sized;
    private boolean					m_isAttribute;
    private boolean					m_isMethod;
    
    private boolean					m_complete;
    
    private SymbolTable				m_child;
   
   
    public SemanticRecord() {
    	m_complete = false;
   		m_dimensions = new ArrayList<Integer>();
   		m_isAttribute = false;
   		m_isMethod = false;
    }
   
    public SemanticRecord(String name) {
    	m_name = name;
    	m_complete = false;
    	m_dimensions = new ArrayList<Integer>();
    }
    
    public void setName(String name) { m_name = name; }
    public void setKind(int kind) { m_kind = kind; }
    public void setType(int type) { m_type = type; }
    public void setTypeName(String typeName) { m_typeName = typeName; }
    public void addDimension(int arraySize) { m_dimensions.add(arraySize); }
    public void setChild(SymbolTable child) { m_child = child; }
    public void complete() { m_complete = true; }
    
    public String getName() { return m_name; }
    public int getKind() { return m_kind; }
    public int getType() { return m_type; }
    public String getTypeName() { return m_typeName; }
    public int getDimensionLevel() { return m_dimensions.size(); }
    public Integer getArraySize(int index) { return m_dimensions.get(index); }
    public SymbolTable getChildTable() { return m_child; }
    public boolean hasChild() { if (m_child != null) { return true; } return false; }
    
    public static String getKindString(int kind) {
    	
    	switch(kind) {
	    	case KIND_CLASS: 		return "class";
	    	case KIND_VARIABLE: 	return "variable";
	    	case KIND_PARAMETER: 	return "parameter";
	    	case KIND_FUNCTION: 	return "function";
	    	default: return "";
    	}
    }
    
    
    public boolean isComplete() {
    	return m_complete;
    }
        
    
    private String getTypeString(int type, String typeName) {
    	
		switch(type) {
			case Token.T_KEYWORD_INT:
				return "int";
			case Token.T_KEYWORD_FLOAT:
				return "float";
			default:
				return typeName;
		}

    }
    
    
    private String getDimensionString(ArrayList<Integer> dimensions) {
    	
    	String string = "";
    	
		for (int i = 0; i < dimensions.size(); i++) {
			string += "[" + dimensions.get(i) + "]";
		}
		
		return string;
    	
    }
    
    public ArrayList<Integer> getDimensionArray() {
    	return m_dimensions;
    }
    
    
    private String columnize(String element, int colSize) {
    	
    	int nSpaces = colSize - element.length();
    	if (nSpaces < 0) { return element; }
    	String column = element;
    	for (int i = 0; i < nSpaces; i++) { column += " "; }
    	
    	return column;
    }
    
    public int getSize() {
    	
    	return m_size;
    	
    }
    
    public void setSize(int size) {
    	
    	m_size = size;
    	m_sized = true;
    	
    }
    
    public boolean isSized() {
    	
    	return m_sized;
    	
    }
    
    public int getAddress() {
    	
    	return m_address;
    	
    }
    
    public void setAddress(int address) {
    	
    	m_address = address;
    	
    }
    
    public ArrayList<SemanticRecord> getParameters() {
    	return m_child.getParameters();
    }
        
    
    public String print() {
    	
    	String typeString = "";
    	
    	int colSize1 = 20;
    	int colSize2 = 20;
    	int colSize3 = 50;
    	int colSize4 = 20;
    	int colSize5 = 20;
    	int colSize6 = 20;
    	int colSize7 = 20;
    	
    	switch(m_kind) {
    	
	    	case KIND_CLASS:
	    		return columnize(m_name, colSize1) + columnize(getKindString(m_kind), colSize2) + columnize("", colSize3) + columnize("LINK: " + m_child.getName(), colSize4)+ columnize("HAS SIZE: " + m_sized, colSize5) + columnize("SIZE: " + m_size, colSize6);
    	
	    	case KIND_PARAMETER:
	    		
	    		typeString = getTypeString(m_type, m_typeName) + getDimensionString(m_dimensions);
	    		
	    		return columnize(m_name, colSize1) + columnize(getKindString(m_kind), colSize2) + columnize(typeString, colSize3)+ columnize("", colSize4) + columnize("HAS SIZE: " + m_sized, colSize5) + columnize("SIZE: " + m_size, colSize6) + columnize("ADDR: " + m_address, colSize7);
	    		
	    	case KIND_VARIABLE:
	    		
	    		typeString = getTypeString(m_type, m_typeName) + getDimensionString(m_dimensions);
	    			    			    		
	    		return columnize(m_name, colSize1) + columnize(getKindString(m_kind), colSize2) + columnize(typeString, colSize3)+ columnize("", colSize4) + columnize("HAS SIZE: " + m_sized, colSize5) + columnize("SIZE: " + m_size, colSize6) + columnize("ADDR: " + m_address, colSize7);
	    	
	    	case KIND_FUNCTION:
	    		
	    		typeString = getTypeString(m_type, m_typeName) + " : ";
	    		
	    		// Fetch all the parameters from the table
	    		ArrayList<SemanticRecord> parameters = m_child.getParameters();
	    		
	    		if (parameters.size() == 0) { typeString += "(no args)"; }
	    		else { 
		    		for (int i = 0; i < parameters.size(); i++) {
		    			if (i > 0) { typeString += ","; }
		    			SemanticRecord parameter = parameters.get(i);
		    			
		    			String parameterTypeString = getTypeString(parameter.getType(), parameter.getTypeName()) + getDimensionString(parameter.getDimensionArray());
		    			typeString += parameterTypeString;
		    		}
	    		}
	    		return columnize(m_name, colSize1) + columnize(getKindString(m_kind), colSize2) + columnize(typeString, colSize3) + columnize("LINK: " + m_child.getName(), colSize4) + columnize("HAS SIZE: " + m_sized, colSize5) + columnize("SIZE: " + m_size, colSize6) + columnize("ADDR: " + m_address, colSize7);
	    		
	    	default:
	    		
	    		return "Unknown identifier kind";

    	}
    }    
    
    public void setAttribute() {
    	m_isAttribute = true;
    }
    
    public boolean isAttribute() {
    	return m_isAttribute;
    }
    
    public void setMethod() {
    	m_isMethod = true;
    }
    
    public boolean isMethod() {
    	return m_isMethod;
    }
    
   
 }