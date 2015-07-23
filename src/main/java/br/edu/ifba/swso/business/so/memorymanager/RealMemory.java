package br.edu.ifba.swso.business.so.memorymanager;

import br.edu.ifba.swso.business.so.memorymanager.exception.MemoryFullException;
import br.edu.ifba.swso.util.Constantes;

public class RealMemory {
	
	private char[] realMemory;
	
	public RealMemory() {
		realMemory = new char[Constantes.MEMORY_SIZE/Constantes.BYTE_PER_PAGE];
		for (int i = 0; i < realMemory.length; i++) {
			realMemory[i] = '0';
		}
	}

	public int foundFreePosition() throws MemoryFullException {
		for (int i = 0; i < realMemory.length; i++) {
			if(realMemory[i] == '0') {
				return i;
			} 
		}
		throw new MemoryFullException();
	}

	public void liberaPosicao(int pos) {
		realMemory[pos] = '0';
	}
	
	public void blockPosition(int pos) {
		realMemory[pos] = '1';
	}

	//MÉTHODS OF ACCESS
	public char[] getVirtualMemory() {
		return realMemory;
	}

}
