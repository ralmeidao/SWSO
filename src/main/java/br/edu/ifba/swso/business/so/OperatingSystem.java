package br.edu.ifba.swso.business.so;

import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.filemanager.IFileSystem;
import br.edu.ifba.swso.business.memorymanager.IMemoryManager;
import br.edu.ifba.swso.business.processmanager.ProcessManager;
import br.edu.ifba.swso.business.processmanager.Processo;

public class OperatingSystem {
	
	private IFileSystem fileSystem;
	
	private IMemoryManager memoryManager;
	
	private ProcessManager processManager;
	
	//CHAMADAS AO SISTEMA
	public void criarProcesso(File file) {
		Processo process = processManager.criarProcesso(file);
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
	
	
}
