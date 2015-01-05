package br.edu.ifba.swso.business.cpu;

import br.edu.ifba.swso.util.Constantes;

/**
 * @author Ramon
 */
public class ProgramCounter {
	
	private int currentValue = -1;

	protected void nextInstruction() {
		currentValue += Constantes.WORD_SIZE;
	}

	protected void modifyRealValue(Object argiNewValue) {
		currentValue = (Integer) argiNewValue;
	}
	
	public int realValue() {
		return currentValue;
	}

}
