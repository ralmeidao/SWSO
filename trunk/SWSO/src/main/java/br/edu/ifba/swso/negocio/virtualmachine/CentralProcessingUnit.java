package br.edu.ifba.swso.negocio.virtualmachine;


import br.edu.ifba.swso.negocio.abstracoes.Word;
import br.edu.ifba.swso.negocio.cpu.ArithmeticLogicUnit;
import br.edu.ifba.swso.negocio.cpu.ControlUnit;
import br.edu.ifba.swso.negocio.cpu.RandomAccessMemory;
import br.edu.ifba.swso.negocio.cpu.Registers;


public class CentralProcessingUnit {
    
	private Registers registers;
    private ArithmeticLogicUnit arithmeticLogicUnit;
    private ControlUnit controlUnit;
    private RandomAccessMemory randomAccessMemory;
	
	public CentralProcessingUnit(RandomAccessMemory ram) {
		this.randomAccessMemory = ram;
		this.registers = new Registers();
		this.arithmeticLogicUnit = new ArithmeticLogicUnit();
		this.controlUnit = new ControlUnit(randomAccessMemory, registers, arithmeticLogicUnit);
	}
    
    public void execute() {
        Word instruction = controlUnit.seekInstruction();
        
        int tipoInstrucao = controlUnit.decode(instruction);
        
        switch (tipoInstrucao) {
			case 1:
				controlUnit.execute();
				break;
			case 2:
				controlUnit.seekOperators();
				break;
			case 3:
				controlUnit.storeResults();
				break;
		}
	}
    
}
