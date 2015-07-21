package br.edu.ifba.swso.business.so.memorymanager;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifba.swso.business.so.memorymanager.exception.InvalidPositionException;
import br.edu.ifba.swso.business.so.memorymanager.exception.PageNotFoundException;
import br.edu.ifba.swso.business.so.memorymanager.exception.VirtualMemoryFullException;
import br.edu.ifba.swso.business.so.processmanager.Process;
import br.edu.ifba.swso.business.virtualmachine.cpu.RandomAccessMemory;
import br.edu.ifba.swso.util.Constantes;

public class MemoryManager {
	
	private Map<Integer, PageTable> pageList;
	
	private VirtualMemory virtualMemory;

	private RandomAccessMemory ram;
	
	public MemoryManager(RandomAccessMemory ram){
		this.ram = ram;
		pageList = new HashMap<Integer, PageTable>();
		virtualMemory = new VirtualMemory();
	}
	
	public Map<Integer, PageTable> getPageList() {
		return pageList;
	}

	public VirtualMemory getVirtualMemory() {
		return virtualMemory;
	}

	public void alocaProcesso(Process process) throws PageNotFoundException, InvalidPositionException, VirtualMemoryFullException {
		PageTable pagetable = new PageTable(process.getPid());
		
		int nPaginas = (process.getQuantidadeInstrucoes()*2)/Constantes.BYTE_PER_PAGE;
		
		if ((process.getQuantidadeInstrucoes()*2) % Constantes.BYTE_PER_PAGE != 0) {
			nPaginas++;
		}
		
		for (int i = 0; i < nPaginas; i++) {
			pagetable.getListaEtp().add(new ETP(i, virtualMemory.foundFreePosition()));
		}
		
		pageList.put(process.getPid(), pagetable);

	}    
}
