package br.edu.ifba.swso.negocio.abstracoes;

import br.edu.ifba.swso.util.Constantes;

/**
 * @author ICAROJ
 */
public class Word {

	private ByteSWSO[] ioWord;

	public Word(ByteSWSO[] argByte) {
		ioWord = new ByteSWSO[Constantes.WORD_SIZE];

		for (int i = 0; i < Constantes.WORD_SIZE; i++) {
			ioWord[i] = argByte[i];
		}
	}
	
	public Word(String s) {
	    ioWord = new ByteSWSO[Constantes.WORD_SIZE];
		for (int i = 0; i < Constantes.WORD_SIZE*2; i +=2) {
	    	ioWord[i/2] = new ByteSWSO(s.charAt(i) + "" + s.charAt(i+1));
	    }
	}

	@Override
	public String toString() {
		String temp = "";
		for (int i = 0; i < Constantes.WORD_SIZE; i++) {
			temp += ioWord[i].toString();
		}
		return temp;
	}

	public String toHexa() {
		String temp = "";
		for (int i = 0; i < Constantes.WORD_SIZE; i++) {
			temp+= String.format("%02X ", ioWord[i]);
		}
		return temp;
	}

	public ByteSWSO[] getIoWord() {
		return ioWord;
	}
	
}
