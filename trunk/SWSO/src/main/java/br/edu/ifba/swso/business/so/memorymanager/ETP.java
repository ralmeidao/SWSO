package br.edu.ifba.swso.business.so.memorymanager;

public class ETP {
	private int ppr;
	private int ppv;
	private char bitV;
	private char bitM;
	
	public ETP(int ppr, int ppv) {
		this.ppr = ppr;
		this.ppv = ppv;
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
