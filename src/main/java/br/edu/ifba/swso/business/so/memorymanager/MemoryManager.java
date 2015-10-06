package br.edu.ifba.swso.business.so.memorymanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifba.swso.business.VirtualMachineParameters;
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
	
	private int paginaSubstituir;
	
	public MemoryManager(CoreVirtualMachine coreVirtualMachine) {
		this.pageList = new HashMap<Integer, PageTable>();
		this.realMemory = new RealMemory(coreVirtualMachine.getVirtualMachineParameters().getMemorySize());
		this.virtualMemory = new VirtualMemory(coreVirtualMachine.getVirtualMachineParameters().getVirtualMemorySize());
		this.coreVirtualMachine = coreVirtualMachine;
	}
	
	public Map<Integer, PageTable> getPageList() {
		return pageList;
	}

	public VirtualMemory getVirtualMemory() {
		return virtualMemory;
	}
	
	public RealMemory getRealMemory() {
		return realMemory;
	}

	public void alocaProcesso(Process process) throws PageNotFoundException, InvalidPositionException, VirtualMemoryFullException, MemoryFullException {
		PageTable pagetable = new PageTable(process.getPid());
		
		int nPaginas = (process.getQuantidadeInstrucoes()*2)/getVirtualMachineParameters().getBytePerPage();
		
		int setoresPorPaginas = getVirtualMachineParameters().getBytePerPage()/getVirtualMachineParameters().getSectorSize();
		
		if ((process.getQuantidadeInstrucoes()*2) % getVirtualMachineParameters().getBytePerPage() != 0) {
			nPaginas++;
		}
		
		for (int nPagina = 0; nPagina < nPaginas; nPagina++) {
			List<Integer> allocatedSectorsPerPage = new ArrayList<Integer>(setoresPorPaginas);
			
			for (int nSetor = 0; nSetor < setoresPorPaginas; nSetor++) {
				if (process.getFile().getAllocatedSectors().size() > ((nPagina*setoresPorPaginas)+nSetor)) {
					allocatedSectorsPerPage.add(process.getFile().getAllocatedSectors().get((nPagina*setoresPorPaginas)+nSetor));
				}
			}
			
			int virtualPosition = virtualMemory.foundFreePosition();
			pagetable.getListaEtp().add(new ETP(nPagina, allocatedSectorsPerPage));
			virtualMemory.blockPosition(virtualPosition, process.getPid());
		}
		
		pageList.put(process.getPid(), pagetable);

		alocaPaginaMemoriaReal(process.getPid(), 0);

	}
	
	public void alocaPaginaMemoriaReal(int pid, int pageNumber) {
		PageTable pageTableProcess = pageList.get(pid);
		ETP etp = pageTableProcess.getListaEtp().get(pageNumber);
		if (etp.getBitV() == '0') {
			
			int realPosition;
			try {
				realPosition = realMemory.foundFreePosition();
			} catch (MemoryFullException e) {
				realPosition =  encontrarPaginaParaSubstituir();
			}
			
			realMemory.blockPosition(realPosition, pid);
			etp.setPpr(realPosition);
			copiarDoDiscoParaRAM(etp.getAllocatedSectors(), realPosition);
			etp.setBitV('1');
		}
	}
	
	//TODO RODAR ALGORITMO DE SUBSTITUIÇÃO DE PÁGINA
	public int encontrarPaginaParaSubstituir() {
		if (paginaSubstituir >= realMemory.getRealMemory().size()) {
			paginaSubstituir = 0;
		}
		return paginaSubstituir++;
	}
	
	public void updatePageTable() {
		int pidAtual = this.coreVirtualMachine.getCentralProcessingUnit().getRegisters().getPid();
		this.coreVirtualMachine.getCentralProcessingUnit().setPageTable(pageList.get(pidAtual));  
	}
	
	public void copiarDoDiscoParaRAM(List<Integer> setores, int realPosition) {
		int ini = realPosition*coreVirtualMachine.getVirtualMachineParameters().getBytePerPage();
		for (Integer nSector : setores) {
			this.coreVirtualMachine.getRandomAccessMemory().alloc(ini, coreVirtualMachine.getHardDisk().getData(nSector));
			ini+=Constantes.SECTOR_SIZE;
		}
	}
	
	public VirtualMachineParameters getVirtualMachineParameters() {
		return coreVirtualMachine.getVirtualMachineParameters();
	}
}
