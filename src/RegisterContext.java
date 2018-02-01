import java.util.ArrayList;


public class RegisterContext {


	ArrayList<Boolean> m_allocated;
	
	
	RegisterContext(int nRegisters) {
		
		m_allocated = new ArrayList<Boolean>();
		for (int i = 0; i < nRegisters; i++) {
			m_allocated.add(false);
		}
	}
	
	public void set(int index, boolean isAllocated) {
		
		m_allocated.set(index, isAllocated);
		
	}
	
	public boolean isAllocated(int index) {
		
		return m_allocated.get(index);
		
	}
	
}
