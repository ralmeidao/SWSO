package br.edu.ifba.swso.business.so.memorymanager;

import java.util.ArrayList;
import java.util.List;

public class PageTable {
	private int pid;
	private List<ETP> listaEtp;
	
	public PageTable(int pid) {
		this.pid = pid;
		listaEtp = new ArrayList<ETP>(); 
	}
	
	public ETP getEtp(int pageNumber) {
		return listaEtp.get(pageNumber);
	}
	
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
