package br.edu.ifba.swso.business.so.memorymanager;

import br.edu.ifba.swso.business.so.memorymanager.exception.VirtualMemoryFullException;
import br.edu.ifba.swso.util.Constantes;

public class VirtualMemory {
	
	private char[] virtualMemory;
	
	public VirtualMemory() {
		virtualMemory = new char[Constantes.MEMORY_SIZE*2];
		for (int i = 0; i < virtualMemory.length; i++) {
			virtualMemory[i] = '0';
		}
	}

	public int foundFreePosition() throws VirtualMemoryFullException {
		for (int i = 0; i < virtualMemory.length; i++) {
			if(virtualMemory[i] == '0') {
				return i;
			} 
		}
		throw new VirtualMemoryFullException();	
	}

	public void liberaPosicao(int pos) {
		virtualMemory[pos] = '0';
	}

	//MÉTHODS OF ACCESS
	public char[] getVirtualMemory() {
		return virtualMemory;
	}

}
