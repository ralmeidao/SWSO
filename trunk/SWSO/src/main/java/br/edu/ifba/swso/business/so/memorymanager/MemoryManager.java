package br.edu.ifba.swso.business.so.memorymanager;

import java.util.HashMap;
import java.util.Map;

import br.edu.ifba.swso.business.so.memorymanager.exception.InvalidPositionException;
import br.edu.ifba.swso.business.so.memorymanager.exception.MemoryFullException;
import br.edu.ifba.swso.business.so.memorymanager.exception.PageNotFoundException;
import br.edu.ifba.swso.business.so.memorymanager.exception.VirtualMemoryFullException;
import br.edu.ifba.swso.business.so.processmanager.Process;
import br.edu.ifba.swso.business.virtualmachine.CoreVirtualMachine;
import br.edu.ifba.swso.util.Constantes;

public class MemoryManager {
	
	private Map<Integer, PageTable> pageList;
	
	private VirtualMemory virtualMemory;

	private CoreVirtualMachine coreVirtualMachine;
	
	private RealMemory realMemory;
	
	public MemoryManager(CoreVirtualMachine coreVirtualMachine){
		pageList = new HashMap<Integer, PageTable>();
		realMemory = new RealMemory();
		virtualMemory = new VirtualMemory();
	}
	
	public Map<Integer, PageTable> getPageList() {
		return pageList;
	}

	public VirtualMemory getVirtualMemory() {
		return virtualMemory;
	}

	public void alocaProcesso(Process process) throws PageNotFoundException, InvalidPositionException, VirtualMemoryFullException, MemoryFullException {
		PageTable pagetable = new PageTable(process.getPid());
		
		int nPaginas = (process.getQuantidadeInstrucoes()*2)/Constantes.BYTE_PER_PAGE;
		
		if ((process.getQuantidadeInstrucoes()*2) % Constantes.BYTE_PER_PAGE != 0) {
			nPaginas++;
		}
		
		for (int i = 0; i < nPaginas; i++) {
			int virtualPosition = virtualMemory.foundFreePosition();
			pagetable.getListaEtp().add(new ETP(i, virtualPosition));
			virtualMemory.blockPosition(virtualPosition);
		}
		
		//ALOCA APENAS A PRIMEIRA PAGINA DO PROCESSO NA MEMORIA REAL
		alocaPaginaMemoriaReal(process.getPid(), 0);
		
		pageList.put(process.getPid(), pagetable);

	}
	
	
	public void alocaPaginaMemoriaReal(int pid, int pageNumber) throws MemoryFullException {
		//OBTER ETP referente à pagina informada
		
		//SE A PAGINA ESTIVER NA MEMORIA REAL VOLTA
		
		//CASO CONTRARIO ENCONTRA UMA POSICAO LIVRE NA MEMORIA REAL
		
		//ADICIONA UMA LINHA NA TABELA DE TRADUCAO(VIRTUAL to REAL)
		
		//SALVA O CONTEUDO DOS SETORES DA ETP NA MEMORIA REAL 

		//int paginaReal = realMemory.foundFreePosition();
		 
	}
	
	public void updatePageTable() {
		int pidAtual = this.coreVirtualMachine.getCentralProcessingUnit().getRegisters().getPid();
		this.coreVirtualMachine.getCentralProcessingUnit().setPageTable(pageList.get(pidAtual));  
	}
	
}
