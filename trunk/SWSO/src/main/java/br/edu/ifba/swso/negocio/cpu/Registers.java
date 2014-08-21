package br.edu.ifba.swso.negocio.cpu;


public class Registers {

	private RegisterInstruction registerInstruction;
	private ProgramCounter programCounter;

	public Registers() {
		registerInstruction = new RegisterInstruction();
		programCounter = new ProgramCounter();
	}

	public RegisterInstruction getRegisterInstruction() {
		return registerInstruction;
	}

	public ProgramCounter getProgramCounter() {
		return programCounter;
	}

}
