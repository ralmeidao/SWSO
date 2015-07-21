package br.edu.ifba.swso.business.virtualmachine.cpu;

import br.edu.ifba.swso.business.so.processmanager.Process;

public class Registers {

	private InstructionRegister registerInstruction;
	private ProgramCounter programCounter;
	private Process process;

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

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}

}
