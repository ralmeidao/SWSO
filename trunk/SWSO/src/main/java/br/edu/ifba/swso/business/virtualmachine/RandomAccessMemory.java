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
	private int tamanhoPagina;

	public RandomAccessMemory(int memorySize, int tamanhoPagina) {
		memory = new ByteSWSO[memorySize * 1024];
		this.tamanhoPagina = tamanhoPagina;
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
		int fator = tamanhoPagina/8;
		
		for (int i = 0; i < memory.length; i++) {
			WordsDisplay wd = new WordsDisplay();
			
			wd.setPositionColumn01(i);
			wd.setValorColumn01(memory[i]);
			
			wd.setPositionColumn02(i+(1*fator));
			wd.setValorColumn02(memory[i+(1*fator)]);
			
			wd.setPositionColumn03(i+(2*fator));
			wd.setValorColumn03(memory[i+(2*fator)]);
			
			wd.setPositionColumn04(i+(3*fator));
			wd.setValorColumn04(memory[i+(3*fator)]);
			
			wd.setPositionColumn05(i+(4*fator));
			wd.setValorColumn05(memory[i+(4*fator)]);
			
			wd.setPositionColumn06(i+(5*fator));
			wd.setValorColumn06(memory[i+(5*fator)]);
			
			wd.setPositionColumn07(i+(6*fator));
			wd.setValorColumn07(memory[i+(6*fator)]);
			
			wd.setPositionColumn08(i+(7*fator));
			wd.setValorColumn08(memory[i+(7*fator)]);
			
			lista.add(wd);
			
			if ((i+1) % fator == 0) {
				i = i + (7*fator);
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
