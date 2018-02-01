import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class SemanticAnalyzer {

    private SymbolTables     	m_tables;
    private SymbolTable     	m_currentScope;
    private boolean            	m_firstPhase;
    private boolean 			m_secondPhase;
    private boolean 			m_referenceChecking;
    private SemanticRecord		m_currentFunction;
    private boolean				m_hasUknownTypeEncountered;
    
    private OutputStream		m_errorOutput;
    private OutputStream		m_symbolOutput;
   
    private int					m_errors;
    
    SemanticAnalyzer(OutputStream symbolOutput, OutputStream errorOutput) {
        m_tables = new SymbolTables();
        m_currentScope = m_tables.getGlobalTable();
        m_firstPhase = true;
        m_secondPhase = false;
        m_referenceChecking = false;
        m_errorOutput = errorOutput;
        m_symbolOutput = symbolOutput;
        m_hasUknownTypeEncountered = false;
   
        
    }
    
    // This method is meant to be used for the second phase.
    public void getScope(String name, int kind) {
    	
    	SemanticRecord recordWithChild = m_tables.search(m_currentScope, name, kind, false);
    	//System.out.println("getScope:" + name + ", currentScope:" + m_currentScope.getName());
    	SymbolTable table = recordWithChild.getChildTable();
    	
    	
    	if (table.visited() == false) {
    		
    		// Mark table so that we don't get back into it again after going out of its scope.
    		table.visit();
    		setScope(table);
    		return;
    		
    	}
    	
    	if (m_secondPhase) {
    	
	    	//System.out.println("getScope:" + "looking for duplicated entry");
	    	
	    	// The table has already been visited.
	    	// This means that we are facing to duplicated entries.
	    	// We hence need to find the approrpiate duplicated entry that has not been visited once.
	        int i = 1;
	        String duplicatedCandidate = "";
	        SemanticRecord candidateRecord = null;
	        while (true) {
	        	duplicatedCandidate = "(dup)" + name + "_" + i;
	        	candidateRecord = m_tables.search(m_currentScope, duplicatedCandidate, kind, false);
	        	if (candidateRecord.getChildTable().visited() != true) { break; }
	        	
	            // Keep searching until we find an available name
	            i++;
	        }
	    	
	        
	    	//System.out.println("getScope: unvisted duplicated entry found: " + "(dup)" + name + "_" + i);
	        
	        table = candidateRecord.getChildTable();
	        
	        table.visit();
	        setScope(table);
	        
	        
    	}
        
    			
    }
    
    
    public boolean addFunctionEntry(Attribute name, Attribute returnType) {
        
    	if (!m_firstPhase) {
    		
    		isClassTypeDefined(name, SemanticRecord.KIND_FUNCTION, returnType);
    		
    		getScope(name.getStrValue(), SemanticRecord.KIND_FUNCTION);
    		return true;
    		
    	}
        
    	if (m_firstPhase) {
    	
	        // Check for duplicated function Entry ?
	        String newName = handleDuplicatedIdentifiers(name, SemanticRecord.KIND_FUNCTION);
	        if (!newName.equals(name)) {
	            name.setStrValue(newName);
	        }
	        
	        // Create entry.
	        SemanticRecord functionEntry = new SemanticRecord(name.getStrValue());
	        functionEntry.setKind(SemanticRecord.KIND_FUNCTION);
	        functionEntry.setType(returnType.getIntValue());
	        functionEntry.setTypeName(returnType.getStrValue());
	        
	        String functionTableName = "";
	        
	        if (m_currentScope == m_tables.getGlobalTable()) {
	        	// Attribute returnType equals to -1. This means the function refers to the special case of "program"
	        	functionTableName = name.getStrValue();
	        }
	        else {
	        	functionTableName = m_currentScope.getName() + ":" + name.getStrValue();
	        }
	        
	        // Create new function table.
	        SymbolTable functionTable = m_tables.create(functionTableName, m_currentScope, functionEntry);
	
	        // Add child to semantic record.
	        functionEntry.setChild(functionTable);
	        
	        // Add class entry.
	        m_currentScope.addRecord(functionEntry);
	        
	        if (m_currentScope != m_tables.getGlobalTable()) {
	        	functionEntry.setMethod();
	        }
	        
	        // Change scope to class table.
	        setScope(functionTable);
	        m_currentFunction = functionEntry;
	        
	        return true;
	       
    	}
    	
    	return true;

    	
        
    }
    
    //TODO: Attributes should be created solely for indices as we may want to know their line, col for error reporting.
    
    
    public boolean identifierReferenceCheck(Attribute name, int kind) {
    	
    	//System.out.println("Reached: " + name.getStrValue());

    	
    	return true;
    }
    
    
    private String handleDuplicatedIdentifiers(Attribute name, int kind) {
        
        // Check for duplicated identifiers in the current scope.
    	
        String nameString = name.getStrValue();
        SemanticRecord entry = m_tables.search(m_currentScope, nameString, kind, false);
        
        if (entry != null) {
            
            // We have a duplicated identifier of the given kind.
            // This is an error, report it as such.
            outputError(name.getLine(), name.getColumn(), "Duplicated " + SemanticRecord.getKindString(kind) + " declaration '" + nameString + "'");        
            // In the case of class or function definitions, we don't want the parsing to stop here.
            // Hence we'll return a new name for this identifier that is certain not to be currently taken.
            
            String newName = "(dup)" + nameString;
            // "(" is not an alpha character . Hence, identifiers starting with "(duplicated)" will only be duplicates.
            int i = 1;
            while ((m_tables.search(m_currentScope, newName + "_" + i, kind, false) != null)) {
                // Keep searching until we find an available name
                i++;
            }
            newName += "_" + i;
            		
            return newName;
        }
        
        return nameString;
    }
    
    // We need to take care of:
    // Class variable declarations
    // Variable references.
    // 
    
    public boolean addClassEntry(Attribute name) {
    	
    	if (!m_firstPhase) {
    		
    		getScope(name.getStrValue(), SemanticRecord.KIND_CLASS);
    		return true;
    		
    	}
    	
    	if (m_firstPhase) {
    	
	        // Check for duplicated class Entry ?
	        String newName = handleDuplicatedIdentifiers(name, SemanticRecord.KIND_CLASS);
	        if (!newName.equals(name)) {
	            name.setStrValue(newName);
	        }
	        
	        // Create entry.
	        SemanticRecord classEntry = new SemanticRecord(name.getStrValue());
	        classEntry.setKind(SemanticRecord.KIND_CLASS);
	        
	        // Create new classtable.
	        SymbolTable classTable = m_tables.create(name.getStrValue(), m_currentScope, classEntry);
	
	        // Add child to semantic record.
	        classEntry.setChild(classTable);
	        
	        // Add class entry.
	        m_currentScope.addRecord(classEntry);
	        // Change scope to class table.
	        setScope(classTable);
	        
	        return true;
    	}
    	
    	return true;
        
    }
    
    private void setScope(SymbolTable symbolTable) {
        
        m_currentScope = symbolTable;
        
    }
    
    public boolean addForVariableEntry(Attribute name, Attribute type) {
    	
    	// This is a special case of variable declaration.
    	// We need to create a new scope just for this variable.
    	    	
    	if (m_firstPhase) {
    		
            int i = 1;
            while ((m_tables.findTable(m_currentScope.getName() + ":#for_" + i) != null)) {
                // Keep searching until we find an available name
                i++;
            }
            
            String forTableName = m_currentScope.getName() + ":#for_" + i;
    		
    		// Search for the right kind of id for the name of the for loop.
            SymbolTable forTable = m_tables.create(forTableName, m_currentScope, m_currentFunction);
            setScope(forTable);
            forTable.setForLoop();
            
            return addVariableEntry(name, SemanticRecord.KIND_VARIABLE, type);
            
    	}
    	
    	else {
    		
    		// Check if we have already visited this for loop
    		int i = 1;
    		SymbolTable forTable = null;
    		while (true) {
    			forTable = m_tables.findTable(m_currentScope.getName() + ":#for_" + i);
    			if (forTable.visited()) {
    				i++;
    			}
    			else { break; }
    		}
    		
    		// We found the corresponding for table.
    		// Mark this table as visited so we don't encounter it again once we go out its scope.
    		forTable.visit();
    		
    		// Get into its scope.
    		setScope(forTable);
    		
        	return true;
    	}
    	
    	
    }

    
    private boolean isClassTypeDefined(Attribute name, int kind, Attribute type) {
    	
    	
		// If we are at the second phase, we only check for uncomplete variable with a class as type.
		if (type.getIntValue() == SemanticRecord.KIND_CLASS) {
			// First off, fetch this declaration from the table
			SemanticRecord classVariableDeclaration = m_tables.search(m_currentScope, name.getStrValue(), kind, false);

			if (m_tables.search(m_tables.getGlobalTable(), type.getStrValue(), type.getIntValue(), false) == null) { 
				// Error: class type is not defined.
				m_hasUknownTypeEncountered = true;
				outputError(type.getLine(), type.getColumn(), "Class type '" + type.getStrValue() + "' does not exist");
				return false;
			}
			
			else {
				
				// The corresponding class declaration was found. This class entry is no more incomplete. Mark this semantic record as completed.
				classVariableDeclaration.complete();
				return true;
			}
		}
		
		return true;
    	
    }
    
    
    //TODO: Deal with forward dependencies
    
    public boolean addVariableEntry(Attribute name, int kind, Attribute type) {
            	
    	if (!m_firstPhase) {
    		    		
    		 isClassTypeDefined(name, kind, type);
    		 
    		 return true;
    	}
    	
    	if (m_firstPhase) {
    	
	        // Check for duplicated variable Entry ?
	        // SEARCH FOR KIND_PARAMETER TOO IF WE ARE IN FUNCTION CLASS
	        String newName = handleDuplicatedIdentifiers(name, SemanticRecord.KIND_VARIABLE);
	        if (!newName.equals(name.getStrValue())) {
	            name.setStrValue(newName);
	        }
	        else {
		        newName = handleDuplicatedIdentifiers(name, SemanticRecord.KIND_PARAMETER);
		        if (!newName.equals(name.getStrValue())) {
		            name.setStrValue(newName);
		        }
	        }
	        
	        
	        
	        SemanticRecord variableEntry = new SemanticRecord(name.getStrValue());
	        variableEntry.setKind(kind);
	        
	        int typeInt = type.getIntValue();
	        if (typeInt != Token.T_IDENTIFIER) {
	            // If it's not an identifier, then we do not care about the actual "lexeme" value.
	            variableEntry.setType(typeInt);
	        }
	        else {
	            variableEntry.setType(typeInt);
	            variableEntry.setTypeName(type.getStrValue());
	        }
	                
	        ArrayList<Integer> dimensions = type.getIntegerList();
	        if (dimensions != null) {
	            for (int i = 0; i < dimensions.size(); i++) {
	                variableEntry.addDimension(dimensions.get(i));
	            }
	        }
	        
	        if (kind != SemanticRecord.KIND_CLASS) {
	        	variableEntry.complete();
	        }
	        
	       m_currentScope.addRecord(variableEntry);
	       if (m_currentScope.getParentRecord().getKind() == SemanticRecord.KIND_CLASS) {
	    	   // This will help identify variables that are class attributes.
	    	   variableEntry.setAttribute();
	       }
    	}
    	
        return true;
        
    }
    
    
    
    //boolean setScope(String tableName) {  }
    public boolean scopeUp() { 
    	
    	m_currentScope = m_currentScope.getParent();
    	return true;
    }
    
    public void setPhaseTwo() {
    	m_firstPhase = false;
    	resetScope();
    	m_secondPhase = true;
    }
    
    public void printSymbolTables() {
    	
    	m_tables.outputTables(m_symbolOutput);
    	
    }
    
    public int getErrorCount() {
    	
    	return m_errors;
    	
    }
        
    
	private void outputError(int line, int col, String message) {
		
		// Increase error count.
		m_errors++;
		
		// Send error message to output.
		message = "Semantic error:" + " (" + Integer.toString(line) + ',' + Integer.toString(col) + ") " + message + "\r\n";
		byte b[] = message.getBytes();
		
		// Write token to output
		try {
			m_errorOutput.write(b, 0, message.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void resetScope() {
		
		m_currentScope = m_tables.getGlobalTable();
	
	}
	
	
    public boolean calculateSize(SemanticRecord record, SymbolTable parentTable) {
    	    	
    	boolean sizingFailure = false;
    	
		SemanticRecord current;
		int size;
    	
    	switch(record.getKind()) {
    	
    	case SemanticRecord.KIND_FUNCTION:
    	case SemanticRecord.KIND_CLASS:
    		// Get the child table and iterate on the semantic records.
    		// If semantic record is not 'sized', then ignore and set m_sized to false.
    		
    		SymbolTable table = record.getChildTable();
    		
    		size = 0;
    		for (int i = 0; i < table.getSize(); i++) {
    			
    			current = table.get(i);
    			
    			if (current.getKind() == SemanticRecord.KIND_FUNCTION) {
    				// If the record is a class and if we encounter a function, we ignore. We do not want its size to be added to the class size.
    				continue;
    			}
    			
    			// If the semantic record within the class has been sized, we have to add it to the class total size.
    			if (current.isSized()) {
    				size += current.getSize();
    			}
    			
    			// Otherwise, the semantic record has been sized and we can not size the class table.
    			
    			else {
    				sizingFailure = true;
    			}
    			
    		}
    		
    		if (sizingFailure == false) {
    			// Sizing was successful.
    			
    			record.setSize(size);
    			return true;
    			
    		}
    			
    		// Otherwise, it was not.
    		return false;
    		
    		    		
    	case SemanticRecord.KIND_VARIABLE:
    	case SemanticRecord.KIND_PARAMETER:
    		    		
    		int unitSize = 0;
    		
    		// We have a variable.
    		// Check if its a primitive type such as float or int (carefull of taking account of arrays too)
    		if (record.getType() == Token.T_KEYWORD_FLOAT) { unitSize = SemanticRecord.SIZE_FLOAT; }
    		else if (record.getType() == Token.T_KEYWORD_INT) { unitSize = SemanticRecord.SIZE_INT; }
    		else {
    			    		
	    		// If its a class reference, find the related semantic record.
	    		
	    		String identifier = record.getTypeName();
	    		
	    		SemanticRecord classRecord = m_tables.search(parentTable, identifier, SemanticRecord.KIND_CLASS, true);
	    		
	    		// If class record is found, we have to set the size of this variable to the size of the class.
	    		if (classRecord.isSized()) {
	    			unitSize = classRecord.getSize();
	    		}
	    		
	    		else {
	    		
	    			// We were unable to size the record.
	    			return false;
	    		}
    		}
    		
    		size = unitSize;
    		
    		// Take care of dimensions.
    		ArrayList<Integer> dimensions = record.getDimensionArray();
    		for (int i = 0; i < dimensions.size(); i++) {
    			size *= dimensions.get(i);
    		}
    		
    		record.setSize(size);
    		return true;
    		
    	default:
    		return true;
    		    	    	
    	}
    }

	
	
	
	public void generateSizes() {
				
		// For each of the symbol tables, we will calculate the size of each of the semantic records.
		int nSizingFailures = 99;
		int old_nSizingFailures = 0;
		boolean success;
		
		SymbolTable currentTable;
		SemanticRecord record;
		
		while (true) {
		
			for (int i = 0; i < m_tables.getSize(); i++) {
				
				currentTable = m_tables.getTable(i);
				
				// For each of the elements within that table.
				for (int j = 0; j < currentTable.getSize(); j++) {
					
					record = currentTable.get(j);
										
					success = calculateSize(record, currentTable);
					if (success == false) {
						nSizingFailures++;
					}
					
				}
				
			}
						
			if (nSizingFailures == 0) {
				
				// Success: all the records have been sized.
				return;
				
			}
			
			if (old_nSizingFailures == 0) {
				
				// First time.
				old_nSizingFailures = nSizingFailures;
				
			}
			else {
				
				if (old_nSizingFailures == nSizingFailures) {
					// We have been unable to compute sizing for all the records. This is due to circular or forward dependency within classes.
					outputError(0, 0, "Circular reference(s) detected within source.");
					//System.out.println("Sizing failure");
					return;
					
				}
				old_nSizingFailures = nSizingFailures;
				
			}
			
			nSizingFailures = 0;
			
			
		}
		
		
	}
	
	public void disable() {
		
		m_firstPhase = false;
		m_secondPhase = false;
		
	}
	
	
	
	public void generateAddresses() {
		
		SymbolTable currentTable;
		SemanticRecord currentRecord;
		int accumulatedSize = 0;
		int size;
		
		// Scan each tables.
		for (int i = 0; i < m_tables.getSize(); i++) {
			
			currentTable = m_tables.getTable(i);
			
			// This is a special case.
			// For loops have been granted their own scope due to the variable definition within their headers.
			// The address of this variable must be equal to the size of the parent's scope.
			if (currentTable.isForLoop()) {
				
				// Locate parent record.
				
				SymbolTable parentFunctionTable = currentTable.getParentRecord().getChildTable();
				SymbolTable tableToSearchOn = currentTable.getParent();
				while (true) {
					
					// Calculate total size.
					// We cannot rely on what the size stated by semantic records as variable declarations within for tables are not included in any records.
					for (int j = 0; j < tableToSearchOn.getSize(); j++) { accumulatedSize += tableToSearchOn.get(j).getSize(); }
					if (tableToSearchOn == parentFunctionTable) { break; }
					tableToSearchOn = tableToSearchOn.getParent();
				}
								
			}
			
			for (int j = 0; j < currentTable.getSize(); j++) {
								
				currentRecord = currentTable.get(j);
				
				if ((currentRecord.getKind() == SemanticRecord.KIND_CLASS) || (currentRecord.getKind() == SemanticRecord.KIND_FUNCTION)) {
					continue;
				}
				
				currentRecord.setAddress(accumulatedSize);
				
				size = currentRecord.getSize();
				accumulatedSize += size;
				
				
			}
			
			accumulatedSize = 0;
			
		}
	}
	
	public SymbolTable getCurrentScope() {
		
		return m_currentScope;
		
	}
	
	
	public void resetVisits() {
		
		SymbolTable currentTable;
		
		// Scan each tables.
		for (int i = 0; i < m_tables.getSize(); i++) {
			
			currentTable = m_tables.getTable(i);
			currentTable.resetVisit();
			
			
		}
		
	}
	
	
	public SymbolTables getTables() {
		
		return m_tables;
		
	}
    
	
	// Attribute will hold the KIND: Object/ float/ int
	// If object it will hold typeName
	
	private boolean typeCheck(SemanticRecord left, SemanticRecord right) {
		
		if (left.getType() == right.getType()) {
			return true;
		}
		
		if (left.getTypeName() != null) {
			if (right.getTypeName() != null) {
				if (left.getTypeName().equals(right.getTypeName())) {
					return true;
				}
			}
			return false;
		}

		return false;
	}
	
	// Structure for referenceEvaluation:
	// type
	// arguments
	// dimensions
	// erroneous
	
	// Object reference will be set to critically erroneous in the case of:
	// Identifier not found.
	// Dimension mismatch			
	private class EvaluatedReference {
		
		Attribute 						m_type;
		int								m_dimensions;
		boolean							m_criticalError;
		ArrayList<AbstractSyntaxTree> 	m_parameters;
		SemanticRecord					m_record;
		
		EvaluatedReference(Attribute type, int dimensions, boolean criticalError, ArrayList<AbstractSyntaxTree> parameters, SemanticRecord record) {
			
			m_type = type;
			m_dimensions = dimensions;
			m_criticalError = criticalError;
			m_parameters = parameters;
			m_record = record;
			
		}
		
		public Attribute getType() { return m_type; }
		public int getDimensions() { return m_dimensions; }
		public boolean isCriticallyErronenous() { return m_criticalError; }
		public boolean isObject() { if (m_type.getStrValue() != null) { return true; } return false; }
		public SemanticRecord getRecord() { return m_record; }
		public ArrayList<AbstractSyntaxTree> getParameters() { return m_parameters; }
		
	}
		
	
	
	private boolean dimensionCheck(SemanticRecord declaration, Attribute reference, boolean ignoreIfRefSmaller) {
		
		int declarationDimensionLevel = declaration.getDimensionLevel();
		int referenceDimensionLevel= reference.getExpressionTreeArraySize();

		
		if (declarationDimensionLevel == referenceDimensionLevel) {
			return true;
		}
				
		if (declarationDimensionLevel < referenceDimensionLevel) {
			// Reference is smaller.
			
			if (declarationDimensionLevel == 0) {
				outputError(reference.getLine(), reference.getColumn(), "Index specified but identifier is not an array.");
				return false;
			
			}
			outputError(reference.getLine(), reference.getColumn(), "Array dimension too big." + " Expected " + declarationDimensionLevel + ", got " + referenceDimensionLevel + ".");
			return false;
		}
		
		// Reference is bigger.
		
		if (ignoreIfRefSmaller) { return true; }
		outputError(reference.getLine(), reference.getColumn(), "Array dimension too small." + " Expected " + declarationDimensionLevel + ", got " + referenceDimensionLevel + ".");
		return false;
				
	}
	
	
	
	EvaluatedReference evaluateReference(Attribute reference, SymbolTable parentClass, Attribute firstReference, boolean isFunction) {
				
		// CASE 1: We are at the end.
			// Check if it's a function
				// Check if hasNest is set to true:
					// It's a method.
		
		// CASE 2: We are the beginning.
			// Get record
			// If record not found:
				// Output error.
				
		
		String name = reference.getStrValue();
		SemanticRecord record;
		
		if ((reference.getNext() == null) && isFunction) {
			
			// It's a function.
							
			if (parentClass == null) {
				// It's a free function. Or a method being call within another method (both methods belonging to the same class).
				record = m_tables.search(m_currentScope, name, SemanticRecord.KIND_FUNCTION, true);
			
				if (record == null) {
					outputError(reference.getLine(), reference.getColumn(), "Function " + name + " not found.");
					return new EvaluatedReference(firstReference, 0, true, null, record);
					
				}
				
			}
			
			else {
				// It's a method.
				record = m_tables.search(parentClass, name, SemanticRecord.KIND_FUNCTION, false);
				
				if (record == null) {
					outputError(reference.getLine(), reference.getColumn(), "Method " + name + " not found inside " + parentClass.getName() + " class.");
					return new EvaluatedReference(firstReference, 0, true, null, record);
				}
			}
			
			return new EvaluatedReference(firstReference, 0, false, reference.getExpressionTrees(), record);
			
		}
				
		SymbolTable classTable;
		
		if (parentClass == null) {
			
			// First level of the idnest.
			// Look-up record.
			record = m_tables.search(m_currentScope, name, SemanticRecord.KIND_PARAMETER, false);
			if (record == null) {
				// Let's try again but with variable as type this time
				record = m_tables.search(m_currentScope, name, SemanticRecord.KIND_VARIABLE, true);
			}
			if (record == null) {
				// The identifier was not found.
				outputError(reference.getLine(), reference.getColumn(), "Identifier " + reference.getStrValue() + " not found.");
				return new EvaluatedReference(firstReference, reference.getExpressionTreeArraySize(), true, null, record);
			}
			
			firstReference = reference;
		}
		
		else {
			
			// We have a nest and we are looking for an attribute within parentClass.
			record = m_tables.search(parentClass, name, SemanticRecord.KIND_VARIABLE, false);
						
			if (record == null) {
				// Could not find the attribute within the class.
				outputError(reference.getLine(), reference.getColumn(), "Could not find attribute " + reference.getStrValue() + " within class " + parentClass.getName());
				return new EvaluatedReference(firstReference, reference.getExpressionTreeArraySize(), true, null, record); 
			}
			
			// Check the type of the record. We are not at the end of the idnest.
			// So if the record is not a class type we then have an error as we won't be able to go further into the idnest.
			
		}
		// Check for dimensions.
		if (!dimensionCheck(record, reference, false)) {
			return new EvaluatedReference(firstReference, reference.getExpressionTreeArraySize(), true, null, record);
		}
		
		//TODO: Should be after ?? no
		if (reference.getNext() == null) {
			// This is the end.
			return new EvaluatedReference(firstReference, reference.getExpressionTreeArraySize(), false, null, record);
			
			
		}
		
		String className = record.getTypeName();
			
		if (className == null) {
			
			// Error 
			// Expected class attribute got something.
			outputError(reference.getLine(), reference.getColumn(), "Attribute '" + reference.getStrValue() + "' found within but class type was expected (got " + getTypeString(record) + " instead.)");
			return new EvaluatedReference(firstReference, reference.getExpressionTreeArraySize(), true, null, record);
				
		}
					
		// Find parent record
		classTable = m_tables.findTable(m_tables.getGlobalTable(), className);

				
		return evaluateReference(reference.getNext(), classTable, firstReference, isFunction);
	
	}
			
	
	
	// referenceEvaluation will check the path and dimensions.
	
	private void outputErrorTypeMismatch(int line, int col, ArrayList<SemanticRecord> expectedTypes, String erroneousTypeString, boolean isFunction) {

		
		String expectedTypeString = getTypeStrings(expectedTypes);
		
		String functionErrorString = "";
		if (isFunction) { functionErrorString = "function returning "; }
		outputError(line, col, "Expected "  + expectedTypeString + " in expression, got " + functionErrorString + erroneousTypeString + " instead.");
	}
	
	private String getTypeStrings(ArrayList<SemanticRecord> records) {
		
		String expectedTypeString = "[";
		
		for (int i = 0; i < records.size(); i++) {
			if (i > 0) { expectedTypeString += ", "; }
			expectedTypeString += getTypeString(records.get(i));
		}
		expectedTypeString += "]";
		
		return expectedTypeString;
		
	}
	
	
	private String getTypeString(SemanticRecord record) {
		
		if (record.getTypeName() != null) { 
			return "'" + record.getTypeName() + "'";
		}
		
		return Token.getTypeReadableFormatString(record.getType());

	}
	
	
	private boolean identifierTypeCheck(ArrayList<SemanticRecord> expectedTypes, Attribute targetAttribute, EvaluatedReference targetReference, boolean isFunction, boolean isObjectFound, boolean isObjectExpected) {
		
		// Object reference will be set to critically erroneous in the case of:
		// Identifier not found.
		// Dimension mismatch
		
		
		if (targetReference.isCriticallyErronenous()) { return false; }
		
		SemanticRecord targetRecord = targetReference.getRecord();
		
		for (int i = 0; i < expectedTypes.size(); i++) {
			
			// Check if we don't have a type mismatch
			if (typeCheck(expectedTypes.get(i), targetRecord)) {
				return true;
			}
					
		}
		String targetTypeString = getTypeString(targetRecord);
				
		outputErrorTypeMismatch(targetAttribute.getLine(), targetAttribute.getColumn(), expectedTypes, targetTypeString, isFunction);
		
		/*
		if (targetReference.isObject()) {
			// If its an object and isObjectFound is set to true, then we have to output an error.
			if (isObjectFound) {
				outputError(targetAttribute.getLine(), targetAttribute.getColumn(), "Expression expects a single object.");
				return false;
			}
		}
		*/
		
		return false;

		
	}
	
	
	private boolean expressionTypeCheckRecursive(ArrayList<SemanticRecord> expectedTypes, AbstractSyntaxTreeNode node, boolean isFunction, boolean isObjectFound, boolean isObjectExpected) {
		
		if (node == null) {
			// Special case. Unary operators only have a left child and not a right one.
			// If we through here, then it's because we encountered a unary operator and tried to recurse on its right child (which is set to null).
			// We can then safely just return true.
			return true;
		}
		
		Attribute element = node.getValue();
		
		int line = element.getLine();
		int col = element.getColumn();
				
		EvaluatedReference reference;
		
		switch(node.getKind()) {
		
		case AbstractSyntaxTreeNode.NODE_OPERATOR:
			
			String expectedTypeStrings = getTypeStrings(expectedTypes);
			
			if (isObjectExpected) {
				// In the presence of an object, we cannot allow operators
				outputError(line, col, "Operator " + Token.getTypeReadableFormatString(element.getIntValue()) + " is undefined in expression for expected type " + expectedTypeStrings + ".");
				return false;
			}
			
			
			// Recurse left and right.
			boolean success;
			success = expressionTypeCheckRecursive(expectedTypes, node.getLeft(), isFunction, isObjectFound, isObjectExpected);
			if (success) { 
				return expressionTypeCheckRecursive(expectedTypes, node.getRight(), isFunction, isObjectFound, isObjectExpected);
			}
			else {
				expressionTypeCheckRecursive(expectedTypes, node.getRight(), isFunction, isObjectFound, isObjectExpected);
				return false;
			}
						
					
		case AbstractSyntaxTreeNode.NODE_CONSTANT:
			
			// For clarity, change the type of the token.
			if (element.getIntValue() == Token.T_INTEGER) { element.setIntValue(Token.T_KEYWORD_INT); }
			if (element.getIntValue() == Token.T_FLOAT) { element.setIntValue(Token.T_KEYWORD_FLOAT); }
			
			// Only check the type
			for (int i = 0; i < expectedTypes.size(); i++) {
				if (expectedTypes.get(i).getType() == element.getIntValue()) {
					return true;
				}
			}
			
			outputErrorTypeMismatch(line, col, expectedTypes, Token.getTypeReadableFormatString(element.getIntValue()), false);
			return false;
			
		
		case AbstractSyntaxTreeNode.NODE_FUNCTION:
			
			// Check what the function may returns. If it's not of the expected type then output an error.
			reference = evaluateReference(element, null, null, true);
						
			if (!identifierTypeCheck(expectedTypes, element, reference, true, isObjectFound, isObjectExpected)) {
				return false;
			}
						
			// Check the parameters of the function call.
			if (!parametersTypeCheck(element, reference.getRecord(), reference.getParameters())) {
				return false;
			}
			
			return true;

			
		case AbstractSyntaxTreeNode.NODE_VARIABLE:
			
			// Check what the function may returns. If it's not of the expected type then output an error.
			reference = evaluateReference(element, null, null, false);
			
			if (!identifierTypeCheck(expectedTypes, element, reference, false, isObjectFound, isObjectExpected)) {
				return false;
			}
			
			return true;
			
		
		default:
			
			return false;
		
		}

	}
	
	// For arrays:
	// We do not allow array = array. Unless if its a function parameter.
	
	
	// For function call:
	// If the function exists:
		// Check function type
		// If function type valid:
			// Check each of the parameters.
	
	
	// Order of error reporting:
		// Reference path error
			// Check if function exists.
				// Check function return type.
					// Check each of the parameters.
	
	// Special case if the expected type is an object:
	// We only allow one element within the tree. This element can either be an object of the expected type or a function returning the expected type.

	
	private boolean expressionTypeCheck(ArrayList<SemanticRecord> expectedTypes, AbstractSyntaxTree expression, boolean isFunction) {
		
				
		boolean isObjectExpected = false;
		
		for (int i = 0; i < expectedTypes.size(); i++) {
			if ((expectedTypes.get(i).getType() != Token.T_KEYWORD_INT) && (expectedTypes.get(i).getType() != Token.T_KEYWORD_FLOAT)) { isObjectExpected = true; break; }
		}
				
		//	SemanticRecord expectedType, AbstractSyntaxTreeNode node, boolean isFunction, boolean isObjectFound, boolean isObjectExpected, String expectedTypeString) {

		
		return expressionTypeCheckRecursive(expectedTypes, expression.getRoot(), isFunction, false, isObjectExpected);
		
		

		
	}
	
	public boolean assignCheck(Attribute destination, AbstractSyntaxTree expression) {
		
    	if (!m_secondPhase) { return true; }
		
    	
		// Verify if the destination type is complete (valid).
		// If not, no need to verify the expression as it is pointless to report type mismatches if the expected type is erroneous.
		EvaluatedReference reference = evaluateReference(destination, null, null, false);
		if (reference.isCriticallyErronenous()) {
			return false;
		}
		
		// The destination is valid. We can proceed to the expression evaluation.
		expressionTypeCheck(generateSingleExpectedType(reference.getRecord()), expression, false);
		
		return true;
		
	}
	
	private boolean parametersTypeCheck(Attribute identifier, SemanticRecord functionRecord, ArrayList<AbstractSyntaxTree> parameters) {
		
		// Compare size of parameters with what is expected.
		
		// Get the parameters.
		ArrayList<SemanticRecord> expectedParameters = functionRecord.getParameters();
				
		// First: Check if we have an argument number mismatch.
		int expectedArgumentSize = expectedParameters.size();
		int passedArgumentSize;
		if (parameters == null) { passedArgumentSize = 0; }
		else { passedArgumentSize = parameters.size(); }
		
		if ((expectedArgumentSize == 0) && (passedArgumentSize == 0)) {
			return true;
		}
		
		if (passedArgumentSize < expectedArgumentSize) {
			// Not enough arguments.
			outputError(identifier.getLine(), identifier.getColumn(), "Not enough arguments passed for function " + functionRecord.getName() + ". Expected " + expectedArgumentSize + " arguments; got " + passedArgumentSize + " arguments.");
			return false;
		}
		
		if (passedArgumentSize > expectedArgumentSize) {
			// Too many arguments.
			outputError(identifier.getLine(), identifier.getColumn(), "Too many arguments passed for function " + functionRecord.getName() + ". Expected " + expectedArgumentSize + " arguments; got " + passedArgumentSize + " arguments.");
			return false;
		}
		
		boolean success = true;
		
		// Second: evaluate the parameters.
		for (int i = 0; i < parameters.size(); i++) {
			
			SemanticRecord expectedParameter = expectedParameters.get(i);
			if (!expressionTypeCheck(generateSingleExpectedType(expectedParameter), parameters.get(i), true)) {
				success = false;
			}
			
		}
		
		return success;
		
	}
	
	private ArrayList<SemanticRecord> generateSingleExpectedType(SemanticRecord record) {
		
		ArrayList<SemanticRecord> expectedTypes = new ArrayList<SemanticRecord>();
		expectedTypes.add(record);
		
		return expectedTypes;
		
	}
	
	
	public boolean returnTypeCheck(AbstractSyntaxTree expression) {
		
		if (!m_secondPhase) {
			return true;
		}
		
		// If the expected return type is not valid then do not proceed further.
		
		// Get record of function
		SemanticRecord functionRecord = m_currentScope.getParentRecord();
		
		expressionTypeCheck(generateSingleExpectedType(functionRecord), expression, false);
		
		
		return true;
	}
	
	private ArrayList<SemanticRecord> createMathTypes(boolean acceptFloat) {
		
		ArrayList<SemanticRecord> mathTypes = new ArrayList<SemanticRecord>();
		SemanticRecord intType = new SemanticRecord();
		intType.setType(Token.T_KEYWORD_INT);
		mathTypes.add(intType);
		
		if (acceptFloat) {
			SemanticRecord floatType = new SemanticRecord();
			floatType.setType(Token.T_KEYWORD_FLOAT);
			mathTypes.add(floatType);
		}

		return mathTypes;
		
	}
	
	
	public boolean mathTypeCheck(AbstractSyntaxTree expression, boolean acceptFloat) {
		
		if (!m_secondPhase) {
			return true;
		}
		
		// Create 'fake' semantic records.
		ArrayList<SemanticRecord> expectedMathTypes = createMathTypes(acceptFloat);
		
		expressionTypeCheck(expectedMathTypes, expression, false);
		
		return true;
		
	}
	
	public boolean mathVariableTypeCheck(Attribute variable, boolean acceptFloat) {
		
		if (!m_secondPhase) {
			return true;
		}
		
		EvaluatedReference evaluatedVariable = evaluateReference(variable, null, null, false);
		
		
		int evaluatedVariableType = evaluatedVariable.getRecord().getType();
		
		if (evaluatedVariableType == Token.T_KEYWORD_INT) {
			
			return true;
		}
		
		if (acceptFloat) {
			if (evaluatedVariableType == Token.T_KEYWORD_FLOAT) {
	
				return true;
			}
		}
		
		// Not the right type.
		ArrayList<SemanticRecord> mathTypes = createMathTypes(acceptFloat);
		String variableType = getTypeString(evaluatedVariable.getRecord());
		
		outputError(variable.getLine(), variable.getColumn(), "Expected variable with type " + getTypeStrings(mathTypes) + " got " + variableType + " instead.");
		
		return true;
		
	}
	
	public boolean uknownTypesEncountered() {
		return m_hasUknownTypeEncountered;
	}

	

}
