package br.edu.ifba.swso.business.memorymanager;

import java.util.HashMap;


public class MemoryManager {
	
	private HashMap<Integer, PageTable> pageList;
	
	private VirtualMemory virtualMemory;

	public HashMap<Integer, PageTable> getPageList() {
		return pageList;
	}

	public VirtualMemory getVirtualMemory() {
		return virtualMemory;
	}

}
