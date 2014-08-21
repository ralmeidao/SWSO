package br.edu.ifba.swso.exception;

import javax.inject.Inject;

/**
 * Classe que trata as exceptions do swso 
 * @author jsouzaa
 *
 */
import org.apache.log4j.Logger;

public class SystemException extends Exception{

	private static final long serialVersionUID = 5516323585662799581L;
	
	@Inject
	private Logger logger;	
	
	public SystemException(){
		super();
	}
	
    /**
     * Exception que carrega todas as mensagens de exception do swso.
     * @param e
     */
	public SystemException(String e) {
		super(e);
		logger.error(e);
	}
	
    /**
     * Exception que carrega todas as mensagens de exception do swso.
     * @param e
     */
	public SystemException(Throwable e) {
		super(e);
		logger.error(e);
		logger.error(e.getStackTrace());
		e.printStackTrace();
	}
	
    /**
     * Exception que carrega todas as mensagens de exception do swso.
     * @param message
     * @param e
     */
	public SystemException(String mensagem, Exception e) {		
		super(mensagem);
		logger.error(mensagem+" - "+e.getMessage());
		logger.error(e.getStackTrace());
		e.printStackTrace();
	}

}
