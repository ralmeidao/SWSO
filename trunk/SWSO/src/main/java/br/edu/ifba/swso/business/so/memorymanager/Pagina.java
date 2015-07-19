package br.edu.ifba.swso.business.so.memorymanager;

public class Pagina {
	private int npr;// número da página real
	private int npv;// número da página virtual
	private String processId;// id do processo

	public int getNpr() {
		return npr;
	}

	public void setNpr(int npr) {
		this.npr = npr;
	}

	public int getNpv() {
		return npv;
	}

	public void setNpv(int npv) {
		this.npv = npv;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

}
