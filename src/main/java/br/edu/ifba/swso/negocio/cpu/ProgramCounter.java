package br.edu.ifba.swso.negocio.cpu;

import br.edu.ifba.swso.util.Constantes;

/**
 * @author ICAROJ
 */
public class ProgramCounter {
	
	private int currentValue = -1;

	public void nextInstruction() {
		currentValue += Constantes.WORD_SIZE;
	}

	public int realValue() {
		return currentValue;
	}

	public void modifyRealValue(Object argiNewValue) {
		currentValue = (Integer) argiNewValue;
	}

}
