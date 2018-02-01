import java.io.IOException;
import java.io.OutputStream;



public class Derivation {
	
	public class DerivationNode {
		
		private String m_value;
		private DerivationNode m_next;
		
		DerivationNode(String value) {
			m_value = value;
			m_next = null;
		}
		
		public void setNext(DerivationNode next) {
			
			m_next = next;
			
		}
		
		public DerivationNode getNext() {
			
			return m_next;
		
		}
		
		public String getValue() {
			
			return m_value;
			
		}
		
		public void setValue(String value) {
			
			m_value = value;
			
		}
		
	}

	
	private DerivationNode 	m_root;
	private DerivationNode 	m_cursor;
	private OutputStream   	m_output;
	private boolean 		m_lockedOutput;
	private boolean			m_enabled;

	
	Derivation(String value, OutputStream output) {
	
		m_root = new DerivationNode(value);
		m_cursor = m_root;
		m_lockedOutput = false;
		
		m_enabled = true;
		m_output = output;
		output();
		
	
	}
	
	private void output() {
		
		if (!m_enabled) { 
			return; 
		}
		
		String derivationString = toString() + '\n';
		
		try {
			m_output.write(derivationString.getBytes(), 0, derivationString.length());
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	public void disable() {
		m_enabled = false;
	}
	
	
	public void move() {
		
		if (!m_enabled) {
			return;
		}
		
		if (m_cursor == null) {
			// Safety.
			return;
		}
		// Move the cursor to the next node.
		m_cursor = m_cursor.getNext();
	}
	
	public void remove() {
		
		if (!m_enabled) {
			return;
		}
		
		DerivationNode currentNode = m_root;
		
		if (m_cursor == m_root) {
			m_root = currentNode.getNext();
			m_cursor = m_root;
		}
		
		else {
			
			while (currentNode.getNext() != m_cursor) {
				currentNode = currentNode.getNext();
			}
			
			move();
			currentNode.setNext(m_cursor);
			
		}
		
		output();
		
	}
	
	public void replace(String[] values) {
		
		if (!m_enabled) {
			return;
		}
		
		String firstValue = values[0];
		
		m_cursor.setValue(firstValue);
		
		for (int i = values.length-1; 0 < i; i--) {
			add(values[i]);
		}
		
		output();
		
	}
	
	
	private void add(String value) {
		
		if (!m_enabled) {
			return;
		}
		
		DerivationNode newNode = new DerivationNode(value);
		DerivationNode cursorOldPNextNode = m_cursor.getNext();
		newNode.setNext(cursorOldPNextNode);
		m_cursor.setNext(newNode);
		
	}
	
	public String toString() {
		
		DerivationNode currentNode = m_root;
		String buffer = "";
		
		while (currentNode != null) {
			
			buffer += currentNode.getValue() + " ";
			currentNode = currentNode.getNext();
			
		}
		
		return buffer;
		
	}
	

}
