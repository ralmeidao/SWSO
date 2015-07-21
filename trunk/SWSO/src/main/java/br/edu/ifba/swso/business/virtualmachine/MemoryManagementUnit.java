package br.edu.ifba.swso.business.virtualmachine;

import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.business.so.processmanager.Process;
import br.edu.ifba.swso.business.virtualmachine.cpu.RandomAccessMemory;
import br.edu.ifba.swso.util.Constantes;

public class MemoryManagementUnit {
	
	private RandomAccessMemory randomAccessMemory;
	private CentralProcessingUnit cpu;
	
	public MemoryManagementUnit(RandomAccessMemory randomAccessMemory, CentralProcessingUnit cpu){
		this.randomAccessMemory = randomAccessMemory;
		this.cpu = cpu;
	}

	public Word getWord(int index) {
		int paginaLogica = index/Constantes.BYTE_PER_PAGE;
		Process process = cpu.getRegisters().getProcess();
		
		
		return null;
	}
	
}
