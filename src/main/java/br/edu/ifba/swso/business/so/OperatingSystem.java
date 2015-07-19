package br.edu.ifba.swso.business.so;

import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;
import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.so.filemanager.IFileSystem;
import br.edu.ifba.swso.business.so.filemanager.XIndexedAllocation;
import br.edu.ifba.swso.business.so.memorymanager.MemoryManager;
import br.edu.ifba.swso.business.so.processmanager.ProcessManager;
import br.edu.ifba.swso.business.so.processmanager.Processo;
import br.edu.ifba.swso.business.virtualmachine.CoreVirtualMachine;

public class OperatingSystem {
	
	private IFileSystem fileSystem;
	
	private IDiskScheduler diskSchedule;
	
	private MemoryManager memoryManager;
	
	private ProcessManager processManager;
	
	public OperatingSystem(CoreVirtualMachine coreVirtualMachine){
		fileSystem = new XIndexedAllocation(coreVirtualMachine.getHardDisk());
		processManager = new ProcessManager(coreVirtualMachine);
		memoryManager = new MemoryManager(coreVirtualMachine.getRandomAccessMemory());
	}
	
	//CHAMADAS AO SISTEMA
	public void criarProcesso(File file) {
		Processo process = processManager.criarProcesso(file);
		System.out.println("Processo criado - PID:" + process.getPid());
	}
	
	//METHODS OF ACCESS
	public IFileSystem getFileSystem() {
		return fileSystem;
	}

	public MemoryManager getMemoryManager() {
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

	public void execute() {
		Processo running = processManager.getEmExecucao();
		
		if (running.getTimeRunning() == running.getTimeSlice() || running.getPid() == -1) {
			//TROCA DE CONTEXTO
			running = processManager.escalonamento();
		}
		
		running.incrementTimeCPU();
		running.incrementTimeRunning();
		
	}
	
}
