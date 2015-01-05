package br.edu.ifba.swso.business.memorymanager.exception;


public class InvalidPositionException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 7437029575530428710L;

	public String invalidPosition(){
        return "Posição inválida";
    }
}
