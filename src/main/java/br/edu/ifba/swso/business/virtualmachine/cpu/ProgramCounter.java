package br.edu.ifba.swso.business.virtualmachine.cpu;

import br.edu.ifba.swso.util.Constantes;

/**
 * @author Ramon
 */
public class ProgramCounter {
	
	private int currentValue = -1;


	public void modifyRealValue(Integer argiNewValue) {
		currentValue =  argiNewValue;
	}
	
	public int realValue() {
		return currentValue;
	}
	
	protected void nextInstruction() {
		currentValue += Constantes.WORD_SIZE;
	}

}
