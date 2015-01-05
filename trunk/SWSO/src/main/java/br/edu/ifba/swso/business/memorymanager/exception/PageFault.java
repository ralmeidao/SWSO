package br.edu.ifba.swso.business.memorymanager.exception;

import br.edu.ifba.swso.business.memorymanager.Pagina;

public class PageFault extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6255089864958106055L;
	/**
	 * retorna os dados para tratamento quando ocorre o pageFault
	 */
	public PageFault(Pagina pg, String ri) {
		this.pg = pg;
		this.ri = ri;
	}

	public Pagina infoPageFault() {
		return pg;
	}

	public String riInfo() {
		return this.ri;
	}

	private String ri;
	private Pagina pg;
}
