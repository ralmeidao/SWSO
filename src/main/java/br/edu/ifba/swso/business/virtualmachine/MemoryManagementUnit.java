package br.edu.ifba.swso.business.virtualmachine;

import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.business.so.memorymanager.ETP;
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
		
		ETP etp = cpu.getPageTable().getEtp(paginaLogica);
		
		if (etp.getBitV() == '0') {
			//PAGE FAULT
		} else {
			int deslocamento = index % Constantes.BYTE_PER_PAGE;
			return randomAccessMemory.getWord((etp.getPpr() * Constantes.BYTE_PER_PAGE) + deslocamento);
		}
		
		return null;
	}
	
}
