package br.edu.ifba.swso.business.so.memorymanager.exception;

public class PageFault extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6255089864958106055L;

	
	private int page;
	
	public PageFault(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}
