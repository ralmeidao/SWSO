package br.edu.ifba.swso.business.so.memorymanager;

public class ETP {
	private int ppv;
	private int ppr;
	private char bitV;
	private char bitM;
	
	//GUARDAR LISTA DE SETORES ONDE ENCOTRAM-SE A PÁGINA
	
	public ETP(int ppv, int ppr) {
		this.ppv = ppv;
		this.ppr = ppr;
		bitV = '0';
		bitM = '0';
	}
	public int getPpr() {
		return ppr;
	}
	public void setPpr(int ppr) {
		this.ppr = ppr;
	}
	public int getPpv() {
		return ppv;
	}
	public void setPpv(int ppv) {
		this.ppv = ppv;
	}
	public char getBitV() {
		return bitV;
	}
	public void setBitV(char bitV) {
		this.bitV = bitV;
	}
	public char getBitM() {
		return bitM;
	}
	public void setBitM(char bitM) {
		this.bitM = bitM;
	}
}
