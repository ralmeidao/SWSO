package br.edu.ifba.swso.business.so.memorymanager.exception;

import br.edu.ifba.swso.util.MensagemUtil;

public class VirtualMemoryFullException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6459917187776354775L;

	public String infoMemoryFull() {
        return MensagemUtil.obterMensagem("virtual.memory.full.exception");
    }
}
