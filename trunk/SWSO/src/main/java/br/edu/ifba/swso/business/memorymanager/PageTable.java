package br.edu.ifba.swso.business.memorymanager;

import java.util.List;

public class PageTable {
	private int pid;
	private List<ETP> listaEtp;
	
	public int getPid() {
		return pid;
	}
	
	public void setPid(int pid) {
		this.pid = pid;
	}
	public List<ETP> getListaEtp() {
		return listaEtp;
	}
	
	public void setListaEtp(List<ETP> listaEtp) {
		this.listaEtp = listaEtp;
	}
}
