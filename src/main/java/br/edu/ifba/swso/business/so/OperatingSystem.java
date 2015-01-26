package br.edu.ifba.swso.business.so;

import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;
import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.filemanager.IFileSystem;
import br.edu.ifba.swso.business.filemanager.XIndexedAllocation;
import br.edu.ifba.swso.business.memorymanager.IMemoryManager;
import br.edu.ifba.swso.business.processmanager.ProcessManager;
import br.edu.ifba.swso.business.virtualmachine.CoreVirtualMachine;

public class OperatingSystem {
	
	private IFileSystem fileSystem;
	
	private IDiskScheduler diskSchedule;
	
	private IMemoryManager memoryManager;
	
	private ProcessManager processManager;
	
	
	public OperatingSystem(CoreVirtualMachine coreVirtualMachine){
		fileSystem = new XIndexedAllocation(coreVirtualMachine.getHardDisk());
	}
	
	//CHAMADAS AO SISTEMA
	public void criarProcesso(File file) {
		//Processo process = processManager.criarProcesso(file);
	}
	
	//METHODS OF ACCESS
	public IFileSystem getFileSystem() {
		return fileSystem;
	}

	public IMemoryManager getMemoryManager() {
		return memoryManager;
	}

	public ProcessManager getProcessManager() {
		return processManager;
	}

	//ALGORITHMS
	public IDiskScheduler getDiskSchedule() {
		return diskSchedule;
	}

	public void setDiskSchedule(IDiskScheduler diskSchedule) {
		this.diskSchedule = diskSchedule;
	}
	
}
