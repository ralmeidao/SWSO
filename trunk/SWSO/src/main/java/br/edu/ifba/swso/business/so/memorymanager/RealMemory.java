package br.edu.ifba.swso.business.so.memorymanager;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.swso.business.so.memorymanager.exception.MemoryFullException;
import br.edu.ifba.swso.display.PageDisplay;

public class RealMemory {
	
	private char[] realMemory;
	private int[] processAlloc;
	
	public RealMemory(int memorySize) {
		realMemory = new char[memorySize];
		processAlloc = new int[memorySize];
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
	
	public List<PageDisplay> getWordList() {
		List<PageDisplay> lista = new ArrayList<PageDisplay>();
		int fator = processAlloc.length/4;
		
		for (int i = 0; i < processAlloc.length; i++) {
			PageDisplay wd = new PageDisplay();
			
			wd.setPositionColumn01(i);
			wd.setValorColumn01(processAlloc[i]);
			
			wd.setPositionColumn02(i+(1*fator));
			wd.setValorColumn02(processAlloc[i+(1*fator)]);
			
			wd.setPositionColumn03(i+(2*fator));
			wd.setValorColumn03(processAlloc[i+(2*fator)]);
			
			wd.setPositionColumn04(i+(3*fator));
			wd.setValorColumn04(processAlloc[i+(3*fator)]);
			
			lista.add(wd);
			
			if ((i+1) % fator == 0) {
				i = i + (3*fator);
			}
		}
	
		return lista;
	}

	public int size() {
		return realMemory.length;
	}
	
}
