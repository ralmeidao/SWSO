package br.edu.ifba.swso.business.so.memorymanager.exception;

public class MemoryFullException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6459917187776354775L;

	public MemoryFullException() {
		super("Memoria cheia iniciar algoritmo de substituição de página");
	}
}
