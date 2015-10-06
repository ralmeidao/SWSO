package br.edu.ifba.swso.business.virtualmachine;

import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.business.so.memorymanager.ETP;

public class MemoryManagementUnit {
	
	private RandomAccessMemory randomAccessMemory;
	private CentralProcessingUnit cpu;
	private int tamanhoPagina;
	
	public MemoryManagementUnit(RandomAccessMemory randomAccessMemory, CentralProcessingUnit cpu, int tamanhoPagina){
		this.randomAccessMemory = randomAccessMemory;
		this.cpu = cpu;
		this.tamanhoPagina = tamanhoPagina;
	}

	public Word getWord(int index) throws Exception {
		int paginaLogica = index/tamanhoPagina;
		
		ETP etp = cpu.getPageTable().getEtp(paginaLogica);
		
		if (etp.getBitV() == '0') {
			//PAGE FAULT
			throw new Exception("PAGE FAULT");
		}
		int deslocamento = index % tamanhoPagina;
		return randomAccessMemory.getWord((etp.getPpr() * tamanhoPagina) + deslocamento);
	}
	
}
