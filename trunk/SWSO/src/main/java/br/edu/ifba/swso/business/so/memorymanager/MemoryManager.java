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

public class MemoryManager {
	
	private Map<Integer, PageTable> pageList;
	
	private VirtualMemory virtualMemory;

	private CoreVirtualMachine coreVirtualMachine;
	
	private RealMemory realMemory;
	
	private int politicaBusca;

	private int politicaAlocacao;
	
	private int numeroMaxFrames;
	
	private int paginaSubstituir;
	
	public MemoryManager(CoreVirtualMachine coreVirtualMachine) {
		this.numeroMaxFrames = 1;
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

	public void allocateProcess(Process process) throws PageNotFoundException, InvalidPositionException, VirtualMemoryFullException, MemoryFullException {
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
			pagetable.getListaEtp().add(new ETP(nPagina, allocatedSectorsPerPage));
		}
		
		pageList.put(process.getPid(), pagetable);

		if (politicaBusca == 0) {
			allocatePage(process.getPid(), 0);
		} else if (politicaAlocacao == 0) {
			int qtdCarregar = numeroMaxFrames < pagetable.getListaEtp().size() ? numeroMaxFrames : pagetable.getListaEtp().size();
			for (int i = 0; i < qtdCarregar; i++) {
				allocatePage(process.getPid(), i);	
			}
		} else {
			int qtdCarregar = process.getNumeroMaxFrames() < pagetable.getListaEtp().size() ? process.getNumeroMaxFrames() : pagetable.getListaEtp().size();
			for (int i = 0; i < qtdCarregar; i++) {
				allocatePage(process.getPid(), i);
			}
		}

	}
	
	public void allocatePage(int pid, int pageNumber) {
		PageTable pageTableProcess = pageList.get(pid);
		ETP etp = pageTableProcess.getListaEtp().get(pageNumber);
		if (etp.getBitV() == '0') {
			
			int realPosition;
			try {
				realPosition = realMemory.foundFreePosition();
			} catch (MemoryFullException e) {
				realPosition =  searchPageToReplace();
			}
			
			realMemory.blockPosition(realPosition, pid);
			etp.setPpr(realPosition);
			copiarDoDiscoParaRAM(etp.getAllocatedSectors(), realPosition);
			etp.setBitV('1');
		}
	}

	public int searchPageToReplace() {
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
			ini+=coreVirtualMachine.getVirtualMachineParameters().getSectorSize();
		}
	}
	
	public VirtualMachineParameters getVirtualMachineParameters() {
		return coreVirtualMachine.getVirtualMachineParameters();
	}

	public int getPoliticaBusca() {
		return politicaBusca;
	}

	public void setPoliticaBusca(int politicaBusca) {
		this.politicaBusca = politicaBusca;
	}

	public int getPoliticaAlocacao() {
		return politicaAlocacao;
	}

	public void setPoliticaAlocacao(int politicaAlocacao) {
		this.politicaAlocacao = politicaAlocacao;
	}

	public int getNumeroMaxFrames() {
		return numeroMaxFrames;
	}

	public void setNumeroMaxFrames(int numeroMaxFrames) {
		this.numeroMaxFrames = numeroMaxFrames;
	}
	
}
