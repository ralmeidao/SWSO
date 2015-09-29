package br.edu.ifba.swso.business.virtualmachine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.swso.business.abstractions.ByteSWSO;
import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.display.WordsDisplay;
import br.edu.ifba.swso.util.Constantes;

public class RandomAccessMemory {
	
	private ByteSWSO[] memory;

	public RandomAccessMemory() {
		memory = new ByteSWSO[Constantes.MEMORY_SIZE * 1024];
	}

	public Word getWord(int index) {
		ByteSWSO[] bytes = new ByteSWSO[Constantes.WORD_SIZE];
		int aux = 0;

		for (int i = index; i < index + Constantes.WORD_SIZE; i++) {
			bytes[aux] = memory[i];
			aux++;
		}

		return new Word(bytes);
	}

	public void alloc(int ini, ByteSWSO[] content) {
		int aux = 0;

		for (int i = ini; i < ini + content.length; i++) {
			memory[i] = content[aux];
			aux++;
		}
	}

	public List<WordsDisplay> getWordList() {
		List<WordsDisplay> lista = new ArrayList<WordsDisplay>();
		for (int i = 0; i < memory.length; i++) {
			WordsDisplay wd = new WordsDisplay();
			
			wd.setPositionColumn01(i);
			wd.setValorColumn01(memory[i]);
			
			wd.setPositionColumn02(i+(1*64));
			wd.setValorColumn02(memory[i+(1*64)]);
			
			wd.setPositionColumn03(i+(2*64));
			wd.setValorColumn03(memory[i+(2*64)]);
			
			wd.setPositionColumn04(i+(3*64));
			wd.setValorColumn04(memory[i+(3*64)]);
			
			wd.setPositionColumn05(i+(4*64));
			wd.setValorColumn05(memory[i+(4*64)]);
			
			wd.setPositionColumn06(i+(5*64));
			wd.setValorColumn06(memory[i+(5*64)]);
			
			wd.setPositionColumn07(i+(6*64));
			wd.setValorColumn07(memory[i+(6*64)]);
			
			wd.setPositionColumn08(i+(7*64));
			wd.setValorColumn08(memory[i+(7*64)]);
			
			lista.add(wd);
			
			if ((i+1) % 64 == 0) {
				i = i + (7*64);
			}
			
		}
	
		return lista;
	}
	
	public Object getMemory(int index) {
		return memory[index];
	}

	public int length() {
		return memory.length;
	}
	
	public int taxaOcupacao() {
		int posicoesOcupadas = 0;
		for (int i = 0; i < memory.length; i++) {
			if(memory[i] != null) posicoesOcupadas++;
		}
		
		return new BigDecimal(posicoesOcupadas).divide(new BigDecimal(memory.length)).multiply(new BigDecimal(100)).intValue();
	}

}
