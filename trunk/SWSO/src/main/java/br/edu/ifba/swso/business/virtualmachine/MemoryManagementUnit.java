package br.edu.ifba.swso.business.virtualmachine;

import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.business.virtualmachine.cpu.RandomAccessMemory;
import br.edu.ifba.swso.util.Constantes;

public class MemoryManagementUnit {
	
	private RandomAccessMemory randomAccessMemory;
	
	public MemoryManagementUnit(RandomAccessMemory randomAccessMemory){
		this.randomAccessMemory = randomAccessMemory;
	}

	public Word getWord(int index) {
		int paginaLogica = index/Constantes.BYTE_PER_PAGE;
		
		return null;
	}
	
}
