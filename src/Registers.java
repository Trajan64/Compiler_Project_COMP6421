import java.util.ArrayList;


public class Registers {

	
	// r13 = stack pointer
	// r14 = stack frame pointer
	
	private class Register {
		
		int 	m_id;
		boolean m_allocated;
		
		Register(int id) {
			m_id = id;
			m_allocated = false;
		}
		
		public int getId() {
			return m_id;
		}
		
		public boolean isAllocated() {
			if (m_allocated == true) {
				return true;
			}
			return false;
		}
		
		public void allocate() {
			m_allocated = true;
		}
		
		public void unallocate() {
			m_allocated = false;
		}
		
	}	
	
	private ArrayList<Register> m_registers;
	private	Register			m_stackPointer;
	private Register			m_stackFramePointer;
	private Register			m_methodPointer;
	private Register			m_returnPointer;
	private	CodeGenerator		m_codeGenerator;
	
	public Registers(int nRegisters, CodeGenerator codeGenerator) {
		
		m_registers = new ArrayList<Register>();
		
		for (int i = 0; i < nRegisters; i++) {
			m_registers.add(new Register(i+1));
		}
		
		m_codeGenerator = codeGenerator;
		
		m_methodPointer = new Register(nRegisters + 1);
		m_stackPointer = new Register(nRegisters + 2);
		m_stackFramePointer = new Register(nRegisters + 3);
		m_returnPointer = new Register(nRegisters + 4);
				
	}
	
	public int allocate() {
		
		for (int i = 0; i < m_registers.size(); i++) {
			if (!m_registers.get(i).isAllocated()) {
				m_registers.get(i).allocate();
				int id = m_registers.get(i).getId();
				m_codeGenerator.addInstruction(null, "sub", "r" + id, "r" + id, "r" + id);
				return id;
			}
		}
		
		// All registers have already been allocated.
		System.out.println("No more registers to allocate!");
		return -1;
		
	}
	
	public void unallocate(int registerId) {
		
		m_registers.get(registerId-1).unallocate();
		
	}
	
	
	public void increaseStackPointer(int offset) {
		
		m_codeGenerator.addInstruction("", "addi", "r" + m_stackPointer.getId(), "r" + m_stackPointer.getId(), "-" + offset, " Increasing stack pointer by " + offset);
		
	}
	
	public void decreaseStackPointer(int offset) {
		
		m_codeGenerator.addInstruction("", "subi", "r" + m_stackPointer.getId(), "r" + m_stackPointer.getId(), "-" + offset);		
		
	}
	
	public int getStackPointerRegister() {
		
		return m_stackPointer.getId();
		
	}
	
	
	
	public int getStackFramePointerRegister() {
		
		return m_stackFramePointer.getId();
		
	}
	
	
	public void setStackFramePointer(int address) {
		
		m_codeGenerator.addInstruction("", "add", "r" + m_stackFramePointer.getId(), "r0", "r" + m_stackPointer.getId(), "Setting stack frame pointer");
		//increaseStackPointer(4);
		
	}
	
	public void backupGeneralRegisters() {
		
		int offset = 0;
		for (int i = 0; i < m_registers.size(); i++) {
			m_codeGenerator.addInstruction("", "sw", offset + "(r" + m_stackPointer.getId() + ")", "r" + m_registers.get(i).getId(), null, "Backing up r" + m_registers.get(i).getId());
			offset -= 4;
		}
		
		m_codeGenerator.addInstruction("", "addi", "r" + getStackPointerRegister(), "r" + getStackPointerRegister(), "" + offset);
		
		//increaseStackPointer(offset);
		
	}
	
	public void backupStackFramePointer() {
		
		m_codeGenerator.addInstruction("", "sw", "0(r" + m_stackPointer.getId() + ")", "r" + m_stackFramePointer.getId(), null, "Backing up stack frame pointer");
		increaseStackPointer(4);
		
	}
	
	public void setReturnAddress(int offset) {
		
		m_codeGenerator.addInstruction("", "addi", "r" + m_returnPointer.getId(), "r" + m_returnPointer.getId(), "" + offset);
		//m_codeGenerator.addInstruction("", "lw", "r" + m_returnPointer.getId(), "" + labelAddress + "(r0)", null);

	}
	
	public int getGeneralSize() {
		
		return m_registers.size();
		
	}
	
	
	//TODO
	public void setClassReferenceRegister(int registerIdWithObjectAddress) {
		
	//m_codeGenerator.addInstruction("", "sub", "r" +  m_methodPointer.getId(), "r" +  m_methodPointer.getId(), "r" +  m_methodPointer.getId(), "The function that is about to be called is a method. We need to put a pointer to the instance in a regsiter");
		m_codeGenerator.addInstruction("", "add", "r" +  m_methodPointer.getId(), "r" + m_stackFramePointer.getId(), "r" + registerIdWithObjectAddress, "The function that is about to be called is a method. We need to put a pointer to the instance in a regsiter"); 
		
	}
	
	
	public int getReturnRegister() {
		
		return m_returnPointer.getId();
		
	}
	
	public void restoreStackPointer() {
		
		m_codeGenerator.addInstruction("", "add", "r" + m_stackPointer.getId(), "r" + m_stackFramePointer.getId(), "r0", "Restoring stack pointer. stack point = stack frame pointer");
		
	}
	
	public int getMethodRegister() {

		return m_methodPointer.getId();
		
	}
	
	
	public void restoreRegisters() {
		
		//m_codeGenerator.addInstruction("", "lw", "r" + m_stackFramePointer.getId(), "4(r" + m_stackPointer.getId() + ")", null, "Restoring stack frame pointer");
		//decreaseStackPointer(4);
		
		int offset = 4;
		
		m_codeGenerator.addInstruction("", "lw", "r" + m_stackFramePointer.getId(), offset + "(r" + m_stackPointer.getId() + ")", null, "Restoring stack frame pointer");
		
		offset += 4;
		
		m_codeGenerator.addInstruction("", "lw", "r" + m_methodPointer.getId(), offset + "(r" + m_stackPointer.getId() + ")", null, "Restoring method pointer");
		
		offset += 4;
		
		m_codeGenerator.addInstruction("", "lw", "r" + m_returnPointer.getId(), offset + "(r" + m_stackPointer.getId() + ")", null, "Restoring return pointer");

		
		int backwardIndex = m_registers.size() - 1;
		for (int i = 0; i < m_registers.size(); i++) {
			
			offset += 4;
			//m_codeGenerator.addInstruction("", "lw", "r" + m_registers.get(backwardIndex).getId(), "4(r" + m_stackPointer.getId() + ")", null, "Restoring general register r" + m_registers.get(backwardIndex).getId());
			//decreaseStackPointer(4);
			
			m_codeGenerator.addInstruction("", "lw", "r" + m_registers.get(backwardIndex).getId(), offset + "(r" + m_stackPointer.getId() + ")", null, "Restoring general register r" + m_registers.get(backwardIndex).getId());
			backwardIndex--;
		}
		
		//offset += 4;
		m_codeGenerator.addInstruction("", "addi", "r" + m_stackPointer.getId(), "r" + m_stackPointer.getId(), "" + offset);

	}
	
	
	public RegisterContext getRegisterContext() {
		
		RegisterContext context = new RegisterContext(m_registers.size());
		
		for (int i = 0; i < m_registers.size(); i++) {
			
			context.set(i, m_registers.get(i).isAllocated());
			
		}
		
		return context;
		
	}
	
	public void restoreContext(RegisterContext context) {
		
		for (int i = 0; i < m_registers.size(); i++) {
			
			if (context.isAllocated(i)) {
				m_registers.get(i).allocate();
			}
			else {
				m_registers.get(i).unallocate();
			}
		}
		
	}
	
	public void freeAll() {
		
		for (int i = 0; i < m_registers.size(); i++) {
			m_registers.get(i).unallocate();
		}
		
	}
	
	public void backupReturnPointer() {
		
		m_codeGenerator.addInstruction("", "sw", "0(r" + m_stackPointer.getId() + ")", "r" + m_returnPointer.getId(), null, "Backing up return pointer");
		increaseStackPointer(4);
		
	}
	
	public void backupMethodPointer() {
		
		m_codeGenerator.addInstruction("", "sw", "0(r" + m_stackPointer.getId() + ")", "r" + m_methodPointer.getId(), null, "Backing up method pointer");
		increaseStackPointer(4);

	}
		
	
}
