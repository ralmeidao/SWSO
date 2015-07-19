package br.edu.ifba.swso.business.virtualmachine.cpu;

import java.math.BigDecimal;

import br.edu.ifba.swso.business.abstractions.ByteSWSO;
import br.edu.ifba.swso.business.abstractions.Word;
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
