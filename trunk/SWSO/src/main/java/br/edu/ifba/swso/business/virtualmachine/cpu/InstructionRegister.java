/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifba.swso.business.virtualmachine.cpu;

import br.edu.ifba.swso.business.abstractions.Word;
/**
 * 
 * @author Ramon
 */
public class InstructionRegister {

	private Word instruction;

	public void refreshInstruction(Word argNewWord) {
		instruction = argNewWord;
	}

	public Word realValue() {
		return instruction;
	}

}
