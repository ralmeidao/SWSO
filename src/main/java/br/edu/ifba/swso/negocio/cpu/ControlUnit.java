package br.edu.ifba.swso.negocio.cpu;

import br.edu.ifba.swso.negocio.abstracoes.Word;

public class ControlUnit {

	private InstructionDecoder instructionDecoder;
	private Registers registers;
	private RandomAccessMemory ram;
	private ArithmeticLogicUnit arithmeticLogicUnit;
	
	public ControlUnit(RandomAccessMemory ram, Registers registers, ArithmeticLogicUnit arithmeticLogicUnit) {
		this.instructionDecoder = new InstructionDecoder();
		this.registers = registers;
		this.ram = ram;
		this.arithmeticLogicUnit = arithmeticLogicUnit;
	}

	public Word seekInstruction() {
		System.out.println("Buscando Instrução");
		Word instruction = ram.getWord(registers.getProgramCounter().realValue());
		
		System.out.println("Atualizando RI");
		registers.getRegisterInstruction().refreshInstruction(instruction);
		
		System.out.println("Atualizando PC");
		registers.getProgramCounter().nextInstruction();
		
		return instruction;
	}
	
	public int decode(Word instruction) {
		instructionDecoder.decode(instruction);
		return 1;
	}

	public void seekOperators() {
		System.out.println("Buscando Operadores");
	}

	public void execute() {
		arithmeticLogicUnit.execute();
	}
	
	public void storeResults() {
		System.out.println("Armazena Resultado");
	}

}
