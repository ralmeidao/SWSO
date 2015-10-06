package br.edu.ifba.swso.business.so.memorymanager;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.swso.business.so.memorymanager.exception.VirtualMemoryFullException;
import br.edu.ifba.swso.display.PageDisplay;

public class VirtualMemory {
	
	private char[] virtualMemory;
	private int[] processAlloc;
	
	public VirtualMemory(int virtualMemorySize) {
		virtualMemory = new char[virtualMemorySize];
		processAlloc = new int[virtualMemorySize];
		for (int i = 0; i < virtualMemory.length; i++) {
			virtualMemory[i] = '0';
			processAlloc[i] = -1;
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
		processAlloc[pos] = -1;
	}

	public void blockPosition(int pos, int pid) {
		virtualMemory[pos] = '1';
		processAlloc[pos] = pid;
	}

	//MÉTHODS OF ACCESS
	public char[] getVirtualMemory() {
		return virtualMemory;
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

}
