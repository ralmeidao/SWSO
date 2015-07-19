package br.edu.ifba.swso.business.so.memorymanager.exception;

public class PageNotFoundException extends Exception{
    /**
	 */
	private static final long serialVersionUID = -9004568717933230309L;

	public String notFound(){
        return "pagina não encontrada";
    }
}
