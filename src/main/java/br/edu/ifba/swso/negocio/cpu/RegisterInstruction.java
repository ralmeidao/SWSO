/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifba.swso.negocio.cpu;

import br.edu.ifba.swso.negocio.abstracoes.Word;
import br.edu.ifba.swso.negocio.abstracoes.ByteSWSO;
/**
 * 
 * @author ICAROJ
 */
public class RegisterInstruction {

	private static Word instruction;

	public void refreshInstruction(Word argNewWord) {
		instruction = argNewWord;
	}

	public void modifyValue(Object newValue) {
		instruction = new Word((ByteSWSO[]) newValue);
	}
	
	public Word realValue() {
		return instruction;
	}

}
