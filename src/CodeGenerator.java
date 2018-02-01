import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class CodeGenerator {
	
	private boolean 			m_enabled;
	private SemanticAnalyzer 	m_semanticAnalyzer;
	private boolean				m_outputLabel;
	private Registers			m_registers;
	private SymbolTables		m_symbolTables;
	private OutputStream		m_output;
	private String				m_label;
	private Function			m_currentFunction;
	private int					m_pc;
	private int					m_ifCounter;
	private int					m_forCounter;
	
	
	public final static int TYPE_CATEGORY_INT = 0;
	public final static int TYPE_CATEGORY_FLOAT = 1;
	public final static int TYPE_CATEGORY_OBJECT = 2;
	
	
	public CodeGenerator(OutputStream output, SemanticAnalyzer semanticAnalyzer) {
		
		m_output = output;
		
		m_enabled = false;
		m_outputLabel = false;
		
		m_registers = new Registers(11, this);
		m_semanticAnalyzer = semanticAnalyzer;
		m_symbolTables = m_semanticAnalyzer.getTables();
		
		//setupStack();
		
	}
	
	
	private String columnize(String string, int colSize) {
		
		int stringSize = string.length();
		int nSpaces = colSize - stringSize;
		
		if (nSpaces < 0) { return string; }
		
		for (int i = 0; i < nSpaces; i++) { string += " "; }
		
		return string;
		
	}
	
		
	
	public void enable() {
		
		m_enabled = true;
		
	}
	
	
	void backupGeneralRegisters() {
		
		// Write registers here.
		
	}
	
	public void addInstruction(String label, String operation, String operator1, String operator2, String operator3, String comment) {
				
		String instruction = "";
		
		if (m_outputLabel) {
			instruction += columnize(" " + m_label, 9);
			m_outputLabel = false;
			
		}
		
		else {
			
			instruction += columnize("", 9);
			
		}
		
		instruction += columnize(operation, 6);
		if (operator1 != null) { instruction += " " + operator1; }
		if (operator2 != null) { instruction += ", " + operator2; }
		if (operator3 != null) { instruction += ", " + operator3; }
		
		instruction += " % " + comment + '\n';
		
		byte b[] = instruction.getBytes();
		
		// Write token to output
		try {
			m_output.write(b, 0, instruction.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		m_pc += 4;


		
	}

	
	
	public void addInstruction(String label, String operation, String operator1, String operator2, String operator3) {
		
		String instruction = "";
		
		if (m_outputLabel) {
			instruction += columnize(" " + m_label, 9);
			m_outputLabel = false;
			
		}
		
		else {
			instruction += columnize("", 9);
		}
		
		instruction += columnize(operation, 6);
		if (operator1 != null) { instruction += " " + operator1; }
		if (operator2 != null) { instruction += ", " + operator2; }
		if (operator3 != null) { instruction += ", " + operator3; }
		
		instruction += '\n';
		
		byte b[] = instruction.getBytes();
		
		// Write token to output
		try {
			m_output.write(b, 0, instruction.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		m_pc += 4;

		
	}
	
	// Expression can be:
		// Object
		// Array?
		// Integer
		// Float
	
	public void store(Attribute destination, int registerWithAddress, int registerWithResult, boolean isSourceOject, int size) {
		
		// Store register to word at address
		if (isAttribute(destination)) {
			// This is an attribute.
			addInstruction(null, "add", "r" + registerWithAddress, "r" + registerWithAddress, "r" + m_registers.getMethodRegister(), "Attribute assignment");
		}
		else {
			addInstruction(null, "add", "r" + registerWithAddress, "r" + registerWithAddress, "r" + m_registers.getStackFramePointerRegister());
		}
		
		
		if (isSourceOject) {
			
			//TODO: INSTANCE CAN BE REFERRED AS AN ATTRIBUTE!
			// Copying.
			copy(registerWithResult, registerWithAddress, size);
		}
		
		else {
		
			addInstruction(null, "sw", "0" + "(r" + registerWithAddress + ")", "r" + registerWithResult, null);
		}
		
		m_registers.unallocate(registerWithAddress);
		m_registers.unallocate(registerWithResult);
		
	}
	
	/*
	public boolean getc(Attribute destination) {
		
		if (!m_enabled) {
			return true;
		}

		VariableInformation variableInformation = getVariableAddress(destination, 0, null, -1, null, false);
		int registerWithAddress = variableInformation.getRegisterId();
		
		SemanticRecord record = variableInformation.getRecord();
		int size = record.getSize();
		int registerWithResult;

		// Allocate a register. This register will hold the captured character.
		registerWithResult = m_registers.allocate();
		
		addInstruction("", "getc", "r" + registerWithResult, null, null);
		
		store(destination, registerWithAddress, registerWithResult, false, size);
		
		return true;
		
	}
	*/
	
		
	
	public boolean getc(Attribute destination) {
		
		if (!m_enabled) {
			return true;
		}

		VariableInformation variableInformation = getVariableAddress(destination, 0, null, -1, null, false);
		int registerWithAddress = variableInformation.getRegisterId();
		
		SemanticRecord record = variableInformation.getRecord();
		int size = record.getSize();
		
		addInstruction("", "sw", "0(r" + m_registers.getStackPointerRegister() + ")", "r" + m_registers.getReturnRegister(), null);
		m_registers.increaseStackPointer(4);
		
		
		addInstruction("", "sw", "0(r" + m_registers.getStackPointerRegister() + ")", "r" + registerWithAddress, null);
		m_registers.increaseStackPointer(4);
		
		m_registers.unallocate(registerWithAddress);
		
		addInstruction("", "jl", "r" + m_registers.getReturnRegister(), "get", null);
		
		int registerWithResult = m_registers.allocate();
		addInstruction("", "lw", "r" + registerWithResult, "0(r" + m_registers.getStackPointerRegister() + ")", null);

		m_registers.decreaseStackPointer(4);
		
		registerWithAddress = m_registers.allocate();
		addInstruction("", "lw", "r" + registerWithAddress, "0(r" + m_registers.getStackPointerRegister() + ")", null);

		m_registers.decreaseStackPointer(4);
		addInstruction("", "lw", "r" + m_registers.getReturnRegister(), "0(r" + m_registers.getStackPointerRegister() + ")", null);
		
		
		store(destination, registerWithAddress, registerWithResult, false, size);
		
		return true;
	}
	
	
	//TODO: Factorize code. Call store() within this method.
	public void assign(Attribute destination, AbstractSyntaxTree tree) {
		
		if (!m_enabled) {
			return;
		}
		
		
		// Get address of variable.
		VariableInformation variableInformation = getVariableAddress(destination, 0, null, -1, null, false);
		int registerWithAddress = variableInformation.getRegisterId();
		
		SemanticRecord record = variableInformation.getRecord();
		int size = record.getSize();
		int registerWithResult;
		
		if (record.getTypeName() != null) {
			
			// Result will be held as an address point to it in a register.
			registerWithResult = m_registers.allocate();
			getValue(tree.getRoot(), registerWithResult, true, 0, true);
			
		}
		
		else {
			
			// Result will be held in a regsiter.
			registerWithResult = mathEvaluate(tree.getRoot());
			
		}
				
		// Store register to word at address
		if (isAttribute(destination)) {
			// This is an attribute.
			addInstruction(null, "add", "r" + registerWithAddress, "r" + registerWithAddress, "r" + m_registers.getMethodRegister(), "Attribute assignment");
			
		}
		else {
			
			addInstruction(null, "add", "r" + registerWithAddress, "r" + registerWithAddress, "r" + m_registers.getStackFramePointerRegister());
		
		}
		
		
		if (record.getTypeName() != null) {
			
			//TODO: INSTANCE CAN BE REFERRED AS AN ATTRIBUTE!
			
			// Copying.
			
			copy(registerWithResult, registerWithAddress, size);

		}
		
		else {
		
			addInstruction(null, "sw", "0" + "(r" + registerWithAddress + ")", "r" + registerWithResult, null);
	
		}
		
		m_registers.unallocate(registerWithAddress);
		m_registers.unallocate(registerWithResult);
		
		// If an object is inside, then no need to evaluate it.
		// We only have to copy its content from variable source to variable destination.
		
	}
	
	
	public int getAddress(String name, int kind, SymbolTable searchScope, boolean searchUpward) {
				
		SemanticRecord variableDeclaration = m_symbolTables.search(searchScope, name, kind, searchUpward);
		
		if (variableDeclaration == null) {
			
			// Could not find variable.
			return -1;
		}
		
		return variableDeclaration.getAddress();
		
	}
	
	
	private int evaluateIndexExpression(AbstractSyntaxTree tree, int objectSize, int accumulator, int indexRegister) {
		
		
		// If first:
			// tmpReg = index * accumulator * objectSize
			// indexReg = indexReg + tmpReg
		// Otherwise:
			// tmpReg = index * accumulator
			// indexReg = indexReg + tmpReg
				
		int registerWithResult = mathEvaluate(tree.getRoot());
				
		accumulator *= objectSize;
		
		
		addInstruction(null, "muli", "r" + registerWithResult, "r" + registerWithResult, "" + accumulator);
		addInstruction(null, "add", "r" + indexRegister, "r" + indexRegister, "r" + registerWithResult);
		
		m_registers.unallocate(registerWithResult);
		
		return indexRegister;
		
		
	}
	
	
	private int evaluateIndexes(Attribute identifier, int arrayRegister, int objectSize, ArrayList<Integer> indexes) {
		
		if (identifier.getExpressionTreeArraySize() > 0) {
			
			if (arrayRegister == -1) {
				arrayRegister = m_registers.allocate();
			}
						
			ArrayList<AbstractSyntaxTree> treeArray = identifier.getExpressionTrees();
			for (int i = 0; i < treeArray.size(); i++) {
				int accumulator = 1;
				if (indexes != null) { for (int j = i + 1; j < indexes.size(); j++) { accumulator *= indexes.get(j); } }
				arrayRegister = evaluateIndexExpression(treeArray.get(i), objectSize, accumulator, arrayRegister);
			}
			
			return arrayRegister;
		}
		
		return arrayRegister;
		
	}
	
	
	public int getUnitSize(SemanticRecord record) {
		
		if (record.getTypeName() != null) {
			// Record is an object.
			
			// Find size of the class.
			SemanticRecord relatedClassRecord = m_symbolTables.search(m_symbolTables.getGlobalTable(), record.getTypeName(), SemanticRecord.KIND_CLASS, false);
			return relatedClassRecord.getSize();
			
		}
		
		else {
			
			// Its a primitive type.
			
			if (record.getType() == Token.T_KEYWORD_FLOAT) { return 4; }
			if (record.getType() == Token.T_KEYWORD_INT) { return 4; }
			
			return 0;
			
		}
		
	}
	
	
	
	private class VariableInformation {
		
		int 	m_registerId;
		String 	m_parentClassName;
		SemanticRecord m_record;
		
		public VariableInformation(int registerId, String parentClassName, SemanticRecord record) {
			
			m_registerId = registerId;
			m_parentClassName = parentClassName;
			m_record = record;
			
		}
		
		public int getRegisterId() { return m_registerId; }
		public String getParentClassName() { return m_parentClassName; }
		public SemanticRecord getRecord() { return m_record; }
		
	}
	
	public VariableInformation getVariableAddress(Attribute identifier, int address, String parentClassName, int arrayRegister, SemanticRecord currentRecord, boolean isFunction) {
				
		int objectSize = 0;
		
		// Check if its the end
		if (identifier == null) {
			
			outputComment("identifier == null debug");
			
			if (arrayRegister == -1) {
			
				// We have never encountered an index during the evaluation of the id/idnest.
				int registerWithAddress = m_registers.allocate();
				addInstruction(null, "addi", "r" + registerWithAddress, "r0", "" + -address, "debug3");
				
				return new VariableInformation(registerWithAddress, parentClassName, currentRecord);
			}
			
			else {
				
				//TODO: What about address value?
				
				// Set the accumulated index address to a negative value.
				int temporaryRegister = m_registers.allocate();
				addInstruction(null, "addi", "r" + arrayRegister, "r" + arrayRegister, "" + address);
				addInstruction(null, "muli", "r" + temporaryRegister, "r" + arrayRegister, "2", "debug1");
				addInstruction(null, "sub", "r" + arrayRegister, "r" + arrayRegister, "r" + temporaryRegister, "debug2");
				m_registers.unallocate(temporaryRegister);
				
				return new VariableInformation(arrayRegister, parentClassName, currentRecord);
			}
			
		}
		
		if (isFunction && (identifier.getNext() == null)) {
			
			// The identifier nest passed represents a method call and we are at the tail of the nest.
			return getVariableAddress(identifier.getNext(), address, parentClassName, arrayRegister, currentRecord, isFunction);

			
		}
		
		
		if (parentClassName == null) {
			
			outputComment("debug4");
			
			address = getAddress(identifier.getStrValue(), SemanticRecord.KIND_PARAMETER, m_semanticAnalyzer.getCurrentScope(), false);
			SemanticRecord target;
			
			if (address == -1) {
				
				// Its a real variable and not a parameter.
				address = getAddress(identifier.getStrValue(), SemanticRecord.KIND_VARIABLE, m_semanticAnalyzer.getCurrentScope(), true);
				target = m_symbolTables.search(m_semanticAnalyzer.getCurrentScope(), identifier.getStrValue(), SemanticRecord.KIND_VARIABLE, true);
				objectSize = getUnitSize(target);

			}
			else {
				target = m_symbolTables.search(m_semanticAnalyzer.getCurrentScope(), identifier.getStrValue(), SemanticRecord.KIND_PARAMETER, true);
				objectSize = getUnitSize(target);
			}
			
			
			// In the case that an array access was specified, we need to multiply the index with the element size.
			
			arrayRegister = evaluateIndexes(identifier, arrayRegister, objectSize, target.getDimensionArray());
			
			if (identifier.getNext() != null) {	parentClassName = target.getTypeName(); }
						
			return getVariableAddress(identifier.getNext(), address, parentClassName, arrayRegister, target, isFunction);
				
		
			

		}
		else {
						
			// Find the class definition.
			SemanticRecord classRecord = m_symbolTables.search(m_symbolTables.getGlobalTable(), parentClassName, SemanticRecord.KIND_CLASS, false);
			SymbolTable classTable = classRecord.getChildTable();
			
			// Find attribute within class table.
			SemanticRecord classAttributeRecord = classTable.search(identifier.getStrValue(), SemanticRecord.KIND_VARIABLE);
			
			int subAddress = classAttributeRecord.getAddress();
						
			//subAddress += classAttributeRecord.getSize() * arrayAccess; 
			outputComment("array register evaluation begin 2");
			arrayRegister = evaluateIndexes(identifier, arrayRegister, getUnitSize(classAttributeRecord), classAttributeRecord.getDimensionArray());
			outputComment("array register evaluation end 2");
			
			address += subAddress;
			
			// We need to recurse.
			return getVariableAddress(identifier.getNext(), address, classAttributeRecord.getTypeName(), arrayRegister, classAttributeRecord, isFunction);
				
		}
						
	}
	
	
	
	private class Parameter {
		
		int 				m_type;
		SemanticRecord 		m_classRecord;
		int					m_size;
		
		public Parameter(int type, SemanticRecord classRecord, int size) {

			m_type = type;
			m_classRecord = classRecord;
			m_size = size;
			
		}
		
		public int getType() { return m_type; }
		public SemanticRecord getClassRecord() { return m_classRecord; }
		public int getSize() { return m_size; }
	
	}
	
	private class Function {
		
		String m_name;
		ArrayList<Parameter> m_parameters;
		int m_returnType;
		String m_returnTypeString;
		SemanticRecord m_returnRecord;
		
		
		public ArrayList<Parameter> getParameters() { return m_parameters; }
		public String getName() { return m_name; }
		public int getReturnType() { return m_returnType; }
		
		public int getReturnSize() {
			
			switch(m_returnType) {
			
			case TYPE_CATEGORY_OBJECT:
				
				return m_returnRecord.getSize();
			
			case TYPE_CATEGORY_INT:
				
				return SemanticRecord.SIZE_INT;
				
			case TYPE_CATEGORY_FLOAT:
				
				return SemanticRecord.SIZE_FLOAT;
				
			default:
				
				return -1;
				
			}
		}

		
		
		public Function(String name, String className) {
						
			SemanticRecord functionRecord;
			m_parameters = new ArrayList<Parameter>();
			
			if (className != null) {
				// This is a member function.
				// Search in class record.
				SemanticRecord classRecord = m_symbolTables.search(m_symbolTables.getGlobalTable(), className, SemanticRecord.KIND_CLASS, false);
				functionRecord = m_symbolTables.search(classRecord.getChildTable(), name, SemanticRecord.KIND_FUNCTION, false);
				
				m_name = classRecord.getName() + "_" + name;
			}
			
			else {
				
				//functionRecord = m_symbolTables.search(m_symbolTables.getGlobalTable(),name, SemanticRecord.KIND_FUNCTION, false);
				functionRecord = m_symbolTables.search(m_semanticAnalyzer.getCurrentScope(),name, SemanticRecord.KIND_FUNCTION, true);
				if (functionRecord.isMethod()) {
					// This is a call to a method defined within a class.
					m_name = m_semanticAnalyzer.getCurrentScope().getParent().getName() + "_" + name;
				}
				else {
					m_name = name;
				}
				
			}
			
			// Get return type.
			String typeName = functionRecord.getTypeName();
			int type = functionRecord.getType();
			
			
			switch(type) {
			
			case Token.T_KEYWORD_INT:
				m_returnType = TYPE_CATEGORY_INT;
				break;
				
			case Token.T_KEYWORD_FLOAT:
				m_returnType = TYPE_CATEGORY_FLOAT;
				break;
				
			default:
				
				SemanticRecord returnClassRecord;
				
				// The function returns an object.
				m_returnType = TYPE_CATEGORY_OBJECT;
				m_returnTypeString = typeName;
				
				// Search record of return type.
				 returnClassRecord = m_symbolTables.search(m_symbolTables.getGlobalTable(), typeName, SemanticRecord.KIND_CLASS, false);

				m_returnRecord = returnClassRecord;

			}
						
			
			// Get parameters
			SymbolTable functionTable = functionRecord.getChildTable();
			for (int i = 0; i < functionTable.getSize(); i++) {
				
				SemanticRecord parameterRecord = functionTable.get(i);
				if (parameterRecord.getKind() == SemanticRecord.KIND_PARAMETER) {
					
					
					// Get type.
					
					int parameterType = parameterRecord.getType();
					SemanticRecord parameterClassRecord;
					
					String parameterTypeString = parameterRecord.getTypeName();
					if (parameterTypeString != null) {
						// Its a class type.
						type = TYPE_CATEGORY_OBJECT;
						
						// Get class record.
						parameterClassRecord = m_symbolTables.search(m_symbolTables.getGlobalTable(), parameterTypeString, SemanticRecord.KIND_CLASS, false);
						
						m_parameters.add(new Parameter(type, parameterClassRecord, parameterClassRecord.getSize()));
					}
					
					else {
						if (parameterType == Token.T_KEYWORD_INT) { 
							type = TYPE_CATEGORY_INT; 
							m_parameters.add(new Parameter(type, null, SemanticRecord.SIZE_INT));
						}
						if (parameterType == Token.T_KEYWORD_FLOAT) { 
							type = TYPE_CATEGORY_FLOAT; 
							m_parameters.add(new Parameter(type, null, SemanticRecord.SIZE_FLOAT));
						}
						
					}
					
					
				}
				
			}
			
			
		}		
		
		
		
	}
		
	
	
	// evaluate will be called when: using return; using putc; using getc; 
	private int evaluate(AbstractSyntaxTree tree, int expectedType, boolean outputToAddress, int addressRegisterId, int offset) {
		
		switch(expectedType) {
		
		case TYPE_CATEGORY_OBJECT:
			
			// If the type is an object, then we do not need to parse the expression further than the firt element.
			// The node can then be evaluated.
			// Also, as its an object we must have an address for the output. No need to check for outputToAddress value.
			getValue(tree.getRoot(), -1, true, addressRegisterId, true);
			return -1;
		
		case TYPE_CATEGORY_INT:
		case TYPE_CATEGORY_FLOAT:
			
			
			int registerId = mathEvaluate(tree.getRoot());
			
			if (outputToAddress) {
				addInstruction(null, "sw", offset + "(r" + addressRegisterId + ")", "r" + registerId, null, "Saving value found at the address inside right hand side register to address in left hand side.");
				if (addressRegisterId < m_registers.getGeneralSize()) {  m_registers.unallocate(addressRegisterId); }
				return -1;
			}
			
			else {
				return registerId;
			}
			
			
		default:
			System.out.println("default?: " + expectedType);
			return -1;
		
		
		}

	}
	
	public void outputComment(String comment) {
		
		comment = '\n' + columnize("", 9) + "% " + comment + '\n';
		
		// Write token to output
		try {
			m_output.write(comment.getBytes(), 0, comment.length());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void copy(int registerSource, int registerDestination, int size) {
		
		outputComment("Copying object with size " + size);
		
		int registerIntermediate = m_registers.allocate(); 
		for (int i = 0; i < (size/4); i++) {
			
			// Load
			addInstruction("", "lw", "r" + registerIntermediate, -(i*4) + "(r" + registerSource + ")", null, "Copying.. (loading)");
			
			// Save
			addInstruction("", "sw", -(i*4) + "(r" + registerDestination + ")", "r" + registerIntermediate, null, "Copying..(saving)");
			
			
		}
		
		m_registers.unallocate(registerIntermediate);
		
	}
	
		
	public void callFunction(String name, String className, ArrayList<AbstractSyntaxTree> parameters, int registerIdWithObjectAddress) {
							
		// Create function object.
		Function function = new Function(name, className);
		
		outputComment("Allocating space for return value");
		// Allocate space for return value.
		m_registers.increaseStackPointer(function.getReturnSize());
		
		outputComment("Backing up registers the 11 general registers + 1 stack frame pointer");
		// Back up all general registers.
		m_registers.backupGeneralRegisters();
		m_registers.backupReturnPointer();
		m_registers.backupMethodPointer();
		
		// OLD:
		m_registers.backupStackFramePointer();
		
		// Backup register context.
		RegisterContext currentRegisterContext = m_registers.getRegisterContext();
		m_registers.freeAll();
		
		if (className != null) {
			
			m_registers.setClassReferenceRegister(registerIdWithObjectAddress);
			m_registers.unallocate(registerIdWithObjectAddress);
		
		}
		
		
		
		// Evaluate each of the parameters and place them onto the stack.
		ArrayList<Parameter> parameterSignatures = function.getParameters();
		int parameterSpaceSize = 0;
		for (int i = 0; i < parameters.size(); i++) {
			
			outputComment("Setting up parameter number " + i);
			int registerIdForParameterDestination = m_registers.allocate();
			
			addInstruction("", "add", "r" + registerIdForParameterDestination, "r" + m_registers.getStackPointerRegister(), "r0", "Setting up address for parameter " + i);
			evaluate(parameters.get(i), parameterSignatures.get(i).getType(), true, m_registers.getStackPointerRegister(), 0);
			m_registers.increaseStackPointer(parameterSignatures.get(i).getSize());
			parameterSpaceSize += parameterSignatures.get(i).getSize();
			m_registers.unallocate(registerIdForParameterDestination);
		}
		
		outputComment("Placing the stack frame pointer");
		
		// Load new
		int newStackFramePointeOffset = parameterSpaceSize;
		
		addInstruction("", "addi", "r" + m_registers.getStackFramePointerRegister(), "r" + m_registers.getStackPointerRegister(), "" + newStackFramePointeOffset, "here");
		
		outputComment("Stack frame pointer will now point to top of stack.");
		
		outputComment("Load up the code return address");

		
		// Jump to code.
		addInstruction("", "jl", "r" + m_registers.getReturnRegister(), function.getName(), null);
		
		m_registers.decreaseStackPointer(4);
		
		// Restore stackPointer so that it gets the value it had before entering the function. stackPointer = stackFramePointer.
		m_registers.restoreStackPointer();
				
		// Restore stack frame pointer and general registers.
		m_registers.restoreRegisters();
		
		// We decrease the stack pointer so that the return value will sit after the stack pointer.
		m_registers.decreaseStackPointer(function.getReturnSize());
		
		// Restore context.
		m_registers.restoreContext(currentRegisterContext);
		
		outputComment("Returned procedure over.");
		
		m_currentFunction = function;

	}
	
	
	public boolean functionReturn(AbstractSyntaxTree expression) {
		
		if (!m_enabled) {
			return true;
		}
		
		// Construct Function object.
		
		SemanticRecord functionRecord = null;
		String className = null;
		SymbolTable currentScope = m_semanticAnalyzer.getCurrentScope();
		
		//TODO: We can be in the scope of a for loop! Add a case that checks if the name starts with "#".
		
		if (currentScope.getParent() != m_symbolTables.getGlobalTable()) {
			
			SymbolTable methodTable = currentScope;
			SymbolTable classTable = currentScope.getParent();
			// It's a method.
			// We need to analyze each of the semantic records until we can find one that has a child table pointer pointing to currentScope.
			
			for (int i = 0; i < classTable.getSize(); i++) {
				
				if (classTable.get(i).hasChild()) {
					if (classTable.get(i).getChildTable() == methodTable) {
						// Semantic record found.
						functionRecord = classTable.get(i);
					}
				}
				
			}
			
			// We need the name of the class.
			SemanticRecord classRecord = m_symbolTables.search(m_symbolTables.getGlobalTable(), classTable.getName(), SemanticRecord.KIND_CLASS, false);
			className = classRecord.getName();
		}
		
		else {
			
			// Its a free function.
			functionRecord = m_symbolTables.search(currentScope.getParent(), currentScope.getName(), SemanticRecord.KIND_FUNCTION, false);
			
		}
		
		Function function = new Function(functionRecord.getName(), className);
				
		// Evaluate expression, and put into the return address space.
		int  addressRegisterId = m_registers.allocate();
		
		outputComment("Calculating address for return space (stackFramePointer  + bypassing backuped registers).");
		// Calculate return address (general_regs + stack_frame_pointer_backup + return size + return pointer size + method pointer + 4 (stack pointer always point to the next free space)
		int address = m_registers.getGeneralSize() * 4 + 4 + 4 + 4 + function.getReturnSize();
		addInstruction("", "addi", "r" + addressRegisterId, "r" + m_registers.getStackFramePointerRegister(), "" + address, "Setting up register holding address to return space");
		
		if (function.getReturnType() == TYPE_CATEGORY_OBJECT) {
			// If an object is returned, we need to copy this object to the return space.
			
			int registerSourceAddress = m_registers.allocate();
			getValue(expression.getRoot(), registerSourceAddress, true, 0, true);
			
			copy(registerSourceAddress, addressRegisterId, function.getReturnSize());
		}
		
		else {
		
			// Evaluate and put the result in the return address specified by the register.
			evaluate(expression, function.getReturnType(), true, addressRegisterId, 0);
			
		}
		
				
		// Return to function in code.
		addInstruction("", "jr", "r" + m_registers.getReturnRegister(), null, null);
		
		return true;
		
	}
	
	
	public boolean isAttribute(Attribute identifier) {
		
		String name = identifier.getStrValue();
		
		SymbolTable currentScope = m_semanticAnalyzer.getCurrentScope();
		
		// Search variable in current scope with upWard set to false.
		SemanticRecord variableDeclaration = m_symbolTables.search(currentScope, name, SemanticRecord.KIND_PARAMETER, false);
		if (variableDeclaration == null) {
			// Let's try again, but this time with KIND_VARIABLE as kind.
			variableDeclaration = m_symbolTables.search(currentScope, name, SemanticRecord.KIND_VARIABLE, true);
		}
		
		if (variableDeclaration.isAttribute()) {
			return true;
		}
		
		return false;
		
		
		
	}
	
	
	public void getValue(AbstractSyntaxTreeNode node, int registerId, boolean functionOutputToAddress, int address, boolean isObjectExpected) {
		
		Attribute attribute = node.getValue();
		int kind = node.getKind();
		
		switch(kind) {
		
		case AbstractSyntaxTreeNode.NODE_CONSTANT:
			
			addInstruction(null, "addi", "r" + registerId, "r" + registerId, "" + (int) Double.parseDouble(attribute.getStrValue()));
			return;
			
		case AbstractSyntaxTreeNode.NODE_FUNCTION:
			
			outputComment("Function evaluation begin.");
			
			if (attribute.getNext() != null) {
				// This is a method call.
				// We need to extract the idnest.
				
				Attribute currentAttribute = attribute;
				Attribute functionCallAttribute;

				while (true) {
					if ((currentAttribute.getNext().getNext() != null)) {
						
						currentAttribute = currentAttribute.getNext();
						continue;
						
					}
					
					else {
						
						// The next element contains the functionCall attribute.
						
						functionCallAttribute = currentAttribute.getNext();
						/*
						// Export expression trees from first identifier to tail of idnest.
						ArrayList<AbstractSyntaxTree> expressionTreeToExport = attribute.getExpressionTrees();
						for (int i = 0; i < attribute.getExpressionTreeArraySize(); i++) {
							functionCallAttribute.addExpressionTree(expressionTreeToExport.get(i));
						}
						// Remove expression tree from first identifier.
						attribute.removeExpressionTreeArray();
						*/
						
						//currentAttribute.setNext(null);
						break;
						
					}
				}
				
				// Debug
				currentAttribute = attribute;
				while(true) {
					if (currentAttribute != null) {
						String sample;
						if (currentAttribute.getExpressionTreeArraySize() > 0) {
							AbstractSyntaxTree expressionTreeSample = currentAttribute.getExpressionTrees().get(0);
							sample = expressionTreeSample.toString();
						}
						else { sample = ""; }
						currentAttribute = currentAttribute.getNext();
					}
					else { break; }
				}
				
				// Now get the address of the object which the method belongs to.
				VariableInformation idnest = getVariableAddress(attribute, 0, null, -1, null, true);
				String className = idnest.getParentClassName();
				
				outputComment("Function evaluation ends.");
				
				// For method calls, we have to include reference to the object instance.
				
				//TODO
				//m_registers.setClassReferenceRegister(idnest.getRegisterId());
				
				callFunction(functionCallAttribute.getStrValue(), className, functionCallAttribute.getExpressionTrees(), idnest.getRegisterId());				
				
			}
			
			else {
								
				// This is a free function.
				callFunction(attribute.getStrValue(), null, attribute.getExpressionTrees(), 0);
				
			}
			
			// Post-call.
			if (isObjectExpected) {
				
				// We are expecting an object for copying purposes. The given register must then hold a reference to that object.
				addInstruction("", "add", "r" + registerId, "r" + m_registers.getStackPointerRegister(), "r0", "Loading up address of function's return value (function returned an object.)");
				
			}
			
			
			// Now fetch the returned value.
			if (!functionOutputToAddress) {
				addInstruction("", "lw", "r" + registerId, "0(r" + m_registers.getStackPointerRegister() + ")", null, "Loading up the function returned value into a register");
			}
						
			return;
			
		case AbstractSyntaxTreeNode.NODE_VARIABLE:
			
			VariableInformation variableInformation = getVariableAddress(attribute, 0, null, -1, null, false);
			int registerWithAddress = variableInformation.getRegisterId();
		
			// Check if the first identifier in the idnest has been declared in the function or if its an attribute access.
			
			boolean isAttribute = isAttribute(attribute);
			
			if (isAttribute) {
				
				// Register 15 should be holding a reference to the instance. Using an offset will get us the address of the attribute.
				addInstruction(null, "add", "r" + registerWithAddress, "r" + registerWithAddress, "r" + m_registers.getMethodRegister(), "Calculate address to variable and set it up into a register (With method register as offset");
				
			}
			
			else {
				
				// Now we have to calculate with the stackFramePointer offset.
				addInstruction(null, "add", "r" + registerWithAddress, "r" + registerWithAddress, "r" + m_registers.getStackFramePointerRegister(), "Calculate address to variable and set it up into a register");
				
			}
			
			if (isObjectExpected) {
				
				// This is an object. We do not need to load anything. As long as we have setup the address of the object in a register, this register will enable the copying of the object.
				addInstruction(null, "add", "r" + registerId, "r" + registerWithAddress, "r0", "Load variable");
				return;
			}
			
			//addInstruction(null, "lw", "r" + registerId, "r" + registerWithResult + "(" + "r" + m_registers.getStackFramePointerRegister() + ")", null);
			addInstruction(null, "lw", "r" + registerId, "0" + "(" + "r" + registerWithAddress + ")", null, "Load variable");
			
			
			m_registers.unallocate(registerWithAddress);

			return;
		
		case AbstractSyntaxTreeNode.NODE_OPERATOR:
			
			// This is a unary operator.
			
			if (node.getLeft().getRight() != null) {
				// The child node is not another unary operator but a binary one.
				int registerWithResult = mathEvaluate(node.getLeft());
				
				addInstruction("", "add", "r" + registerId, "r" + registerWithResult, "r0");
				
				m_registers.unallocate(registerWithResult);
				
			}
			
			else {
			
				 getValue(node.getLeft(), registerId, functionOutputToAddress, address, false);
				 
			}
			 createUnaryOperation(attribute.getIntValue(), registerId);
			
			return;
			
		default:
			
			return;
			
		}
		
	}
	
	
	private boolean nodeHasValue(AbstractSyntaxTreeNode node) {
		
		if (node.isLeaf() || ((node.getKind() == AbstractSyntaxTreeNode.NODE_OPERATOR) && (node.getRight() == null))) {
			return true;
		}
		
		return false;
				
	}
	
	
	private void createUnaryOperation(int tokenOperation, int registerLeft) {
		
		int registerIntermediate = m_registers.allocate();
		
		switch(tokenOperation) {
		
		case Token.T_KEYWORD_NOT:
			
			addInstruction(null, "not", "r" + registerIntermediate, "r" + registerLeft, null);
			int jumpFalse = m_pc + 4*2; 
			addInstruction(null, "bz", "r" + registerIntermediate, "" + jumpFalse, null);
			addInstruction(null, "addi", "r" + registerLeft, "r0", "1");
			int jumpTrue = m_pc + 4;
			addInstruction(null, "j", "" + jumpTrue, null, null);
			addInstruction(null, "addi", "r" + registerLeft, "r0", "0");

			break;
			
			
		case Token.T_OPERATOR_MINUS:
			
			addInstruction(null, "muli", "r" + registerIntermediate, "r" + registerLeft, "2");
			addInstruction(null, "sub", "r" + registerLeft, "r" + registerLeft, "r" + registerIntermediate);
		
			break;
			
			
		case Token.T_OPERATOR_PLUS:
			// Not necessary.
			
			break;
			
			
		default:
			break;

			
		}
		
		m_registers.unallocate(registerIntermediate);

		
	}
	
	
	
	private void createBinaryOperation(int tokenOperation, int registerLeft, int registerRight, int registerResult) {
		
		
		int jumpFalse;
		int jumpTrue;
		
		switch(tokenOperation) {
		
		
		// Mathematical
		
		case Token.T_OPERATOR_DIV:
			addInstruction(null, "div", "r" + registerResult, "r" + registerLeft, "r" + registerRight);
			break;
			
		case Token.T_OPERATOR_MULTIPLY:
			addInstruction(null, "mul", "r" + registerResult, "r" + registerLeft, "r" + registerRight);
			break;
			
		case Token.T_OPERATOR_PLUS:
			addInstruction(null, "add", "r" + registerResult, "r" + registerLeft, "r" + registerRight);
			break;
		
		case Token.T_OPERATOR_MINUS:
			addInstruction(null, "sub", "r" + registerResult, "r" + registerLeft, "r" + registerRight);
			break;
			
		
		// Logical	
		
		case Token.T_OPERATOR_EQ:
			addInstruction(null, "ceq", "r" + registerResult, "r" + registerLeft, "r" + registerRight);
			break;
			
			
		case Token.T_OPERATOR_GREATERTHAN_OR_EQ:
			addInstruction(null, "cge", "r" + registerResult, "r" + registerLeft, "r" + registerRight);

			break;
			
		
		
		case Token.T_OPERATOR_GREATERTHAN_OR_LESSERTHAN:
			
			// if greater: set result to 1
			// if 0: jump to 'if lower'
			// jump to end
			// if lower: set result to 1.
			// if 1: jump afterward to success
			
			addInstruction(null, "cgt", "r" + registerResult, "r" + registerLeft, "r" + registerRight);
			jumpFalse = m_pc + 4; 
			addInstruction(null, "bz", "r" + registerResult, "" + jumpFalse, null);
			jumpTrue = m_pc + 4;
			addInstruction(null, "j", "" + jumpTrue, null, null);
			addInstruction(null, "clt", "r" + registerResult, "r" + registerLeft, "r" + registerRight);
		

			break;
			
		case Token.T_OPERATOR_GREATERTHAN:
			addInstruction(null, "cgt", "r" + registerResult, "r" + registerLeft, "r" + registerRight);

			break;

		case Token.T_OPERATOR_LESSERTHAN_OR_EQ:
			addInstruction(null, "cle", "r" + registerResult, "r" + registerLeft, "r" + registerRight);

			break;

		case Token.T_OPERATOR_LESSERTHAN:
			addInstruction(null, "clt", "r" + registerResult, "r" + registerLeft, "r" + registerRight);

			break;

		case Token.T_KEYWORD_AND:
			addInstruction(null, "and", "r" + registerResult, "r" + registerLeft, "r" + registerRight);
			jumpFalse = m_pc + 4*2; 
			addInstruction(null, "bz", "r" + registerResult, "" + jumpFalse, null);
			addInstruction(null, "addi", "r" + registerResult, "r0", "1");
			jumpTrue = m_pc + 4;
			addInstruction(null, "j", "" + jumpTrue, null, null);
			addInstruction(null, "addi", "r" + registerResult, "r0", "0");
			
			break;


		case Token.T_KEYWORD_OR:
			//TODO
			addInstruction(null, "or", "r" + registerResult, "r" + registerLeft, "r" + registerRight);
			jumpFalse = m_pc + 4*2; 
			addInstruction(null, "bz", "r" + registerResult, "" + jumpFalse, null);
			addInstruction(null, "addi", "r" + registerResult, "r0", "1");
			jumpTrue = m_pc + 4;
			addInstruction(null, "j", "" + jumpTrue, null, null);
			addInstruction(null, "addi", "r" + registerResult, "r0", "0");

			break;

					
			
		default:
			break;
		
		}
		
	}
	
	
	
	public int mathEvaluate(AbstractSyntaxTreeNode node) {
		
		int registerResult;
		
		// Check if its a single child node or if its a unary operator.
		if (nodeHasValue(node)) {
			
			registerResult = m_registers.allocate();
			getValue(node, registerResult, false, -1, false);
			
			return registerResult;
			
		}
		
		if (!m_enabled) {
			return 0;
		}
		
		int registerLeft = -1;
		int registerRight = -1;
				
		boolean calculateLeft;
		boolean calculateRight;
		
		// If left and right are leaves:
		if (nodeHasValue(node.getLeft()) && nodeHasValue(node.getRight())) {
						
			calculateLeft = true;
			calculateRight = true;

		}
		
		else {
		
			// Otherwise, we will have to wait until we get values for both:
	
			
			// Left
			if (nodeHasValue(node.getLeft())) {
				calculateLeft = true;
			}
			else {
				registerLeft = mathEvaluate(node.getLeft());
				calculateLeft = false;
			}
			
			
			// Right
			if (nodeHasValue(node.getRight())) {
				calculateRight = true;
			}
			
			else {
				registerRight = mathEvaluate(node.getRight());
				calculateRight = false;
			}
									
		}
				
		registerResult = m_registers.allocate();
		
		if (calculateLeft) { 
			registerLeft  = m_registers.allocate();
			getValue(node.getLeft(), registerLeft, false, -1, false); 
		}
			
		if (calculateRight) { 
			registerRight = m_registers.allocate();
			getValue(node.getRight(), registerRight, false, -1, false); 
		}
				
		//addInstruction(null, operation, "r" + registerResult, "r" + registerLeft, "r" + registerRight);
		
		createBinaryOperation(node.getValue().getIntValue(), registerLeft, registerRight, registerResult);
		
		m_registers.unallocate(registerLeft);
		m_registers.unallocate(registerRight);
		
		return registerResult;
		
		
	}
	
	private static final String PREFIX_IF_IFEND  = "endif";
	private static final String PREFIX_IF_ELSE  = "else";
	private static final String PREFIX_FOR_LOOPING = "gofor";
	private static final String PREFIX_FOR_OUT = "foren";
	
	public boolean createIfEnd(int ifId) {
		
		if (!m_enabled) {
			return true;
		}
		
		String labelIfEnd = PREFIX_IF_IFEND + ifId;
		
		addInstruction("", "j", labelIfEnd, null, null);

		return true;
		
	}
	
	
	public boolean createElseBegin(int ifId) {
		
		if (!m_enabled) {
			return true;
		}
		
		String labelIfEnd = PREFIX_IF_ELSE + ifId;
		
		m_outputLabel = true;
		m_label = labelIfEnd;
		
		return true;
		
	}
	
	public boolean createElseEnd(int ifId) {
		
		if (!m_enabled) {
			return true;
		}
		
		String labelElse = PREFIX_IF_IFEND + ifId;

		m_outputLabel = true;
		m_label = labelElse;

		return true;
		
	}
		
	public int createIfBegin(AbstractSyntaxTree predicateTree) {
		
		if (!m_enabled) {
			return 0;
		}
		
		// Generate a unique if label.
				
		int currentIfCounter = m_ifCounter;
		
		String labelElse = PREFIX_IF_ELSE + currentIfCounter;
		
		// Produce predicate evaluation code.
		int registerPredicateResult = mathEvaluate(predicateTree.getRoot());
		
		// Produce code that will decide which of the branches we should go to.
		addInstruction("", "bz", "r" + registerPredicateResult, labelElse, null);
		m_registers.unallocate(registerPredicateResult);
		
		m_ifCounter++;
		
		return currentIfCounter;
		
	}
	
	
	
	public boolean createFunctionBegin(Attribute name, Attribute type, boolean isMethod) {
	
		if (!m_enabled) {
			return true;
		}
				
		// The first instruction in our function must bear a label.
		m_outputLabel = true;
		
		// Set label
		if (isMethod) {
			
			// Get class name.
			SymbolTable classTable = m_semanticAnalyzer.getCurrentScope();
			
			// Setup className:functionName
			m_label = classTable.getName().replace(':', '_');
			
		}
		
		else {
			
			m_label = name.getStrValue();
			
		}
				
		return true;
		
	}
	
	public boolean addVariableDeclaration(Attribute name, Attribute type) {
		
		if (!m_enabled) {
			return true;
		}
				
		// Locate variable in symbol table.
		SymbolTable currentScope = m_semanticAnalyzer.getCurrentScope();
		
		SemanticRecord variableDeclaration = m_symbolTables.search(currentScope, name.getStrValue(), SemanticRecord.KIND_VARIABLE, true);
		
		// Get size of variable
		int size = variableDeclaration.getSize();
		
		// Add variable ounto the stack.
		m_registers.increaseStackPointer(size);
		
		return true;
		
	}
	
	
	
	public void createProgramFunction() {
		
		if (!m_enabled) {
			return;
		}
		
		addInstruction(null, "entry", null, null, null);
		
		// Initialize stack pointer.
	    addInstruction(null, "addi", "r" + m_registers.getStackPointerRegister(), "r0", "topaddr");

	    // Initialize stack frame pointer.
	    addInstruction(null, "addi", "r" + m_registers.getStackFramePointerRegister(), "r0", "topaddr");
	    
	}
	
	
	// This method is meant to be called when we know a label may have set for output for an intruction, but no operation were provided to output it. 
	public void labelCheck() {
		
		if (!m_enabled) {
			return;
		}
		
		if (m_outputLabel) {
			
			addInstruction("", "nop", null, null, null);
			
		}
	}
		
	
	
	void addIdentifierDeclaration(String name, int kind) {
				
		// Lookup attribute in symbol table.
		//SymbolTable currentScope = m_semanticAnalyzer.getCurrentScope();
		//SemanticRecord record = m_symbolTables.search(currentScope, name, kind, true);
		
		// Find size of the attribute.
		//int size = record.getSize();
		
		// Allocate on stack.
		//m_registers.increaseStackPointer(size);
		
		// To set the size on objects:		
	}
		
	
	public int createForLoopBegin(Attribute name, Attribute type, AbstractSyntaxTree assignExpression, AbstractSyntaxTree relExpression) {
		
		if (!m_enabled) {
			return 0;
		}
		
		// Find variable size.
		SymbolTable currentScope = m_semanticAnalyzer.getCurrentScope();
		SemanticRecord forVariable = currentScope.get(0);

		// Get size of variable
		int size = forVariable.getSize();

		// Allocate on stack.
		m_registers.increaseStackPointer(size);
		
		
		// Assign.
		assign(name, assignExpression);
		
		int currentForCounter = m_forCounter;
		
		// Generate label.
		String labelForLooping = PREFIX_FOR_LOOPING + currentForCounter;
		String labelForEnd = PREFIX_FOR_OUT + currentForCounter;
		
		m_outputLabel = true;
		m_label = labelForLooping;
		
		// Predicate evaluation.
		int registerPredicateResult = mathEvaluate(relExpression.getRoot());
		
		// Produce code that will decide which of the branches we should go to.
		addInstruction("", "bz", "r" + registerPredicateResult, labelForEnd, null);
		m_registers.unallocate(registerPredicateResult);
		
		m_forCounter++;
		
		return currentForCounter;
		
	}
	
	public boolean createForLoopEnd(int forId, Attribute variable, AbstractSyntaxTree tree) {
		
		if (!m_enabled) {
			return true;
		}
		
		// Produce post loop assignment
		assign(variable, tree);
		
		// Get size of the for variable.
		SymbolTable currentScope = m_semanticAnalyzer.getCurrentScope();
		SemanticRecord forVariable = currentScope.get(0);
		int size = forVariable.getSize();
		
		String labelForLooping = PREFIX_FOR_LOOPING + forId;
		String labelForEnd = PREFIX_FOR_OUT + forId;
		
		// Create jump to for loop head.
		addInstruction("", "j", labelForLooping, null, null);
		
		
		m_outputLabel = true;
		m_label = labelForEnd;
		
		// Remove for loop variable from the stack.
		m_registers.decreaseStackPointer(size);
		
		return true;
		
	}
	
	
	public boolean putc(AbstractSyntaxTree expression) {
	
		if (!m_enabled) {
			return true;
		}
		
		int registerWithResult = mathEvaluate(expression.getRoot());
		//addInstruction("", "putc", "r" + registerWithResult, null, null);
		
		// Buffer up return register.
		addInstruction("", "sw", "0(r" + m_registers.getStackPointerRegister() + ")", "r" + m_registers.getReturnRegister(), null);
		
		m_registers.increaseStackPointer(4);
		
		// Put the value on top of the stack, to be picked up by the putc sub-routine.
		addInstruction("", "sw", "0(r" + m_registers.getStackPointerRegister() + ")", "r" + registerWithResult, null);
		
		// Jump to putc sub-routine.
		addInstruction("", "jl", "r" + m_registers.getReturnRegister(), "put", null);
		
		m_registers.decreaseStackPointer(4);
		addInstruction("", "lw", "r" + m_registers.getReturnRegister(), "0(r" + m_registers.getStackPointerRegister() + ")", null);
		
		
		m_registers.unallocate(registerWithResult);
		
		return true;
		
	}
	
	
	
	
	public boolean end() {
       
		if (!m_enabled) {
			return true;
		}
		
		// hlt                    % All done!
        addInstruction(null, "hlt", null, null, null);
        
        return true;
        
	}
	
}
