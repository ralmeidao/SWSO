package br.edu.ifba.swso.business.so;

import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;
import br.edu.ifba.swso.algorithms.interfaces.IProcessesScheduler;
import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.so.filemanager.IFileSystem;
import br.edu.ifba.swso.business.so.filemanager.XIndexedAllocation;
import br.edu.ifba.swso.business.so.memorymanager.MemoryManager;
import br.edu.ifba.swso.business.so.processmanager.Process;
import br.edu.ifba.swso.business.so.processmanager.ProcessManager;
import br.edu.ifba.swso.business.virtualmachine.CoreVirtualMachine;

public class OperatingSystem {
	
	private IFileSystem fileSystem;
	
	private IDiskScheduler diskSchedule;
	
	private int timeslice;

	private IProcessesScheduler processesScheduler;
	
	private MemoryManager memoryManager;
	
	private ProcessManager processManager;
	
	public OperatingSystem(CoreVirtualMachine coreVirtualMachine){
		fileSystem = new XIndexedAllocation(coreVirtualMachine.getHardDisk());
		processManager = new ProcessManager(coreVirtualMachine);
		memoryManager = new MemoryManager(coreVirtualMachine);
	}
	
	//CHAMADAS AO SISTEMA
	public void criarProcesso(File file) {
		try {
			Process process = processManager.criarProcesso(file);
			memoryManager.alocaProcesso(process);
			System.out.println("Processo criado - PID:" + process.getPid());
		} catch (Exception e) {

		}
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
	
	public IProcessesScheduler getProcessesScheduler() {
		return processesScheduler;
	}

	public void setProcessesScheduler(IProcessesScheduler processesScheduler) {
		this.processesScheduler = processesScheduler;
	}
	
	public int getTimeslice() {
		return timeslice;
	}

	public void setTimeslice(int timeslice) {
		this.timeslice = timeslice;
	}

	public void execute() {
		Process running = processManager.getEmExecucao();
		
		if (running.getPid() == -1 || (running.getTimeRunning() != 0 && running.getTimeRunning() % timeslice == 0)) {
			//TROCA DE CONTEXTO
			running = processManager.escalonamento();
			memoryManager.updatePageTable();
		}
		
		running.incrementTimeRunning();
		
	}
	
}
