package br.edu.ifba.swso.business.so.memorymanager;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.swso.business.so.memorymanager.exception.MemoryFullException;
import br.edu.ifba.swso.util.Constantes;

public class RealMemory {
	
	private char[] realMemory;
	private int[] processAlloc;
	
	public RealMemory() {
		realMemory = new char[Constantes.MEMORY_SIZE*Constantes.BYTE_PER_PAGE];
		processAlloc = new int[Constantes.MEMORY_SIZE*Constantes.BYTE_PER_PAGE];
		for (int i = 0; i < realMemory.length; i++) {
			realMemory[i] = '0';
			processAlloc[i] = -1;
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
	
	public void blockPosition(int pos, int pid) {
		realMemory[pos] = '1';
		processAlloc[pos] = pid;
	}

	//MÉTHODS OF ACCESS
	public List<Integer> getRealMemory() {
		List<Integer> lista = new ArrayList<Integer>();
		for (int i = 0; i < processAlloc.length; i++) {
			lista.add(processAlloc[i]);
		}
		return lista;
	}

}
