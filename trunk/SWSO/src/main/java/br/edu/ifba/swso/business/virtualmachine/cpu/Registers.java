package br.edu.ifba.swso.business.virtualmachine.cpu;


public class Registers {

	private InstructionRegister registerInstruction;
	private ProgramCounter programCounter;

	public Registers() {
		registerInstruction = new InstructionRegister();
		programCounter = new ProgramCounter();
	}

	public InstructionRegister getInstructionRegister() {
		return registerInstruction;
	}

	public ProgramCounter getProgramCounter() {
		return programCounter;
	}

}
