package br.edu.ifba.swso.negocio.cpu;

import br.edu.ifba.swso.negocio.abstracoes.ByteSWSO;
import br.edu.ifba.swso.negocio.abstracoes.Word;
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

	public void alloc(int ini, Object[] content) {
		int aux = 0;

		for (int i = ini; i < ini + content.length; i++) {
			memory[i] = (ByteSWSO) content[aux];
			aux++;
		}
	}

	public Object getMemory(int index) {
		return memory[index];
	}

	public int length() {
		return memory.length;
	}

}
