package br.com.lordmonssa.exemplo.arquitetura.exception;

import java.util.List;

/**
 * Classe que trata todas as exceptions de campos requeridos 
 * @author jsouzaa
 *
 */
public class RequiredException extends Exception {

	private static final long serialVersionUID = -4806939480794045985L;

	private List<String> erroList;
	
    public RequiredException() {
    }
    
    /**
     * Exception que carrega todas as mensagens de validação dos campos requeridos.
     * @param message
     */
    public RequiredException(String message) {
        super(message);
    }
    
    /**
     * Exception que carrega todas as mensagens de validação dos campos requeridos.
     * @param erroList
     */
    public RequiredException(List<String> erroList) {
    	this.erroList = erroList;
    }
    
    /**
     * Exception que carrega todas as mensagens de validação dos campos requeridos.
     * @param message
     * @param erroList
     */
    public RequiredException(String message, List<String> erroList) {
    	super(message);
    	this.erroList = erroList;
    }

	public List<String> getErroList() {
		return erroList;
	}

	public void setErroList(List<String> erroList) {
		this.erroList = erroList;
	}
}
