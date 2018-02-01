import java.io.OutputStream;
import java.util.LinkedList;


public class SymbolTables {

	SymbolTable				m_globalTable;
	LinkedList<SymbolTable>	m_tables;
	
	public SymbolTables() {
		m_tables = new LinkedList<SymbolTable>();
		
		// Create global table.
		m_globalTable = new SymbolTable("global", null, null);
		m_tables.add(m_globalTable);
		
	}
	
	public SymbolTable getGlobalTable() {
		return m_globalTable;
	}
	
	
	public SymbolTable create(String tableName, SymbolTable parentTable, SemanticRecord parentRecord) {
	
		SymbolTable newSymbolTable = new SymbolTable(tableName, parentTable, parentRecord);
		
		m_tables.add(newSymbolTable);
		
		return newSymbolTable;
	}
	
	public SymbolTable findTable(String tableName) {
		
		for (int i = 0; i < m_tables.size(); i++) {
			if (m_tables.get(i).getName().equals(tableName)) {
				return m_tables.get(i);
			}
		}
		
		return null;
		
	}
	
	public SymbolTable findTable(SymbolTable tableScope, String tableName) {
		
		for (int i = 0; i < tableScope.getSize(); i++) {
			
			if (tableScope.get(i).hasChild()) {
				SymbolTable childTable = tableScope.get(i).getChildTable();
				
				if (childTable.getName().equals(tableName)) {
					return childTable;
				}
			}
			
		}
		
		return null;
		
	}
	
	public SemanticRecord search(SymbolTable table, String id, int type, boolean goUpward) {
				
		SymbolTable current = table;
		
		SemanticRecord entry = null;
		while (current != null) {
			
			entry = current.search(id, type);
			if (entry == null) {
				if (!goUpward) { return null; }
				current = current.getParent();
			}
			else {
				// Entry found.
				return entry;
			}
		}
		
		// Entry not found; We have recursed on all the possible parent tables.
		return null;
						
	}
	
	public SemanticRecord insert(String tableName, SemanticRecord identifier) {
		
		// Locate table.
		SymbolTable target = findTable(tableName);
		if (target == null) { return null; }
		
		// If the kind is of type function, then we will have to create a child table at this entry.
		if ((identifier.getKind() == SemanticRecord.KIND_CLASS) || (identifier.getKind() == SemanticRecord.KIND_FUNCTION)) {
			String childTableName = tableName + ":" + identifier.getName();
			SymbolTable child = new SymbolTable(childTableName, target, identifier);
			m_tables.add(child);
			identifier.setChild(child);
		}
		
		target.addRecord(identifier);
		return identifier;
	}
	
	public void delete(String tableName) {
		
		SymbolTable target = findTable(tableName);
				
		m_tables.remove(target);
		
	}
	
	public void outputTables(OutputStream output) {
		
		for (int i = 0; i < m_tables.size(); i++) {
			
			m_tables.get(i).outputTable(output);
		
		}
		
	}
	
	public SymbolTable getTable(int index) {
		
		return m_tables.get(index);
		
	}
	
	public int getSize() {
		
		return m_tables.size();
		
	}
	
}
