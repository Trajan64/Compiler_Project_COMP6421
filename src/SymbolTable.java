import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


public class SymbolTable {

	private String						m_name;
	private SymbolTable 				m_parent;
	private ArrayList<SemanticRecord>	m_records;
	private boolean						m_visited;
	private SemanticRecord				m_parentRecord;
	private boolean						m_forLoop;
	
	
	public SymbolTable(String name, SymbolTable parent, SemanticRecord parentRecord) {
		
		m_name = name;
		m_parent = parent;
		m_records = new ArrayList<SemanticRecord>();
		m_parentRecord = parentRecord;
		m_visited = false;
		m_forLoop = false;
		
	}
	
	public SemanticRecord search(String recordToSearch, int kind) {
		
		for (int i = 0; i < m_records.size(); i++) {
			
			if (m_records.get(i).getName().equals(recordToSearch) && (kind == m_records.get(i).getKind())) {
				return m_records.get(i);
			}
			
		}
		
		return null;
		
	}
	
	public void visit() {
		m_visited = true;
	}
	
	public boolean visited() {
		return m_visited;
	}
	
	public void addRecord(SemanticRecord record) {
		m_records.add(record);
	}
	
	public SemanticRecord getParentRecord() {
		return m_parentRecord;
	}
	
	public String getName() {
		return m_name;
	}
	public void setForLoop() {
		m_forLoop = true;
	}
	public boolean isForLoop() {
		return m_forLoop;
	}
	
	public SymbolTable getParent() {
		return m_parent;
	}
	
	public int getSize() {
		return m_records.size();
	}
	
	
	public SemanticRecord get(int index) {
		return m_records.get(index);
	}
	
	public ArrayList<SemanticRecord> getParameters() {
		ArrayList<SemanticRecord> parameters = new ArrayList<SemanticRecord>();
		
		for (int i = 0; i < m_records.size(); i++) {
			if (m_records.get(i).getKind() == SemanticRecord.KIND_PARAMETER) { parameters.add(m_records.get(i)); }
		}
		
		return parameters;
	}
	
	
	public void outputTable(OutputStream output) {
		
		try { output.write(new String("================================ " + m_name + " ================================\n").getBytes()); } catch (IOException e) { e.printStackTrace(); }
		
		for (int i = 0; i < m_records.size(); i++) {
			try { output.write(new String(m_records.get(i).print() + "\n").getBytes()); } catch (IOException e) { e.printStackTrace(); }

		}
		
		try { output.write(new String("------------------------------------------------------------------\n\n\n").getBytes()); } catch (IOException e) { e.printStackTrace(); }
		
	}
	
    public void resetVisit() {
    	
    	m_visited = false;
    	
    }
	
	
}
