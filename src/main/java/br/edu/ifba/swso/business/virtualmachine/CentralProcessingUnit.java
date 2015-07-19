package br.edu.ifba.swso.business.virtualmachine;


import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.business.virtualmachine.cpu.ArithmeticLogicUnit;
import br.edu.ifba.swso.business.virtualmachine.cpu.ControlUnit;
import br.edu.ifba.swso.business.virtualmachine.cpu.InstructionDecoder;
import br.edu.ifba.swso.business.virtualmachine.cpu.RandomAccessMemory;
import br.edu.ifba.swso.business.virtualmachine.cpu.Registers;


public class CentralProcessingUnit {
    
	private MemoryManagementUnit memoryManagementUnit;
	private Registers registers;
    private ArithmeticLogicUnit arithmeticLogicUnit;
    private ControlUnit controlUnit;
    private InstructionDecoder instructionDecoder;
    
    private long cpuTime;
    
	public CentralProcessingUnit(RandomAccessMemory ram) {
		this.memoryManagementUnit = new MemoryManagementUnit(ram);
		this.registers = new Registers();
		this.arithmeticLogicUnit = new ArithmeticLogicUnit();
		this.controlUnit = new ControlUnit();
		this.instructionDecoder = new InstructionDecoder();
		this.cpuTime = 0;
	}
    
    public void execute() {
    	if(registers.getProgramCounter().realValue() != -1) {
    		Word instruction = controlUnit.seekInstruction(registers, memoryManagementUnit);
    		int tipoInstrucao = controlUnit.decode(instruction, instructionDecoder);
	        switch (tipoInstrucao) {
				case 1:
					controlUnit.execute(arithmeticLogicUnit);
					break;
				case 2:
					controlUnit.seekOperators();
					break;
				case 3:
					controlUnit.storeResults();
					break;
			}
    	}
    	cpuTime++;
	}
    
    //MÉT0D0S DE ACESSO
	public Registers getRegisters() {
		return registers;
	}

	public ArithmeticLogicUnit getArithmeticLogicUnit() {
		return arithmeticLogicUnit;
	}

	public ControlUnit getControlUnit() {
		return controlUnit;
	}

	public MemoryManagementUnit getMemoryManagementUnit() {
		return memoryManagementUnit;
	}

	public InstructionDecoder getInstructionDecoder() {
		return instructionDecoder;
	}

	public long getCpuTime() {
		return cpuTime;
	}
    
}
