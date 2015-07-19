package br.edu.ifba.swso.business.virtualmachine.cpu;

import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.business.virtualmachine.MemoryManagementUnit;

public class ControlUnit {

	public Word seekInstruction(Registers registers, MemoryManagementUnit mmu) {
		System.out.println("Buscando Instrução");
		Word instruction = mmu.getWord(registers.getProgramCounter().realValue());
		
		System.out.println("Atualizando RI");
		registers.getInstructionRegister().refreshInstruction(instruction);
		
		System.out.println("Atualizando PC");
		registers.getProgramCounter().nextInstruction();
		
		return instruction;
	}
	
	public int decode(Word instruction, InstructionDecoder instructionDecoder) {
		instructionDecoder.decode(instruction);
		return 1;
	}

	public void seekOperators() {
		System.out.println("Buscando Operadores");
	}

	public void execute(ArithmeticLogicUnit arithmeticLogicUnit) {
		arithmeticLogicUnit.execute();
	}
	
	public void storeResults() {
		System.out.println("Armazena Resultado");
	}

}
