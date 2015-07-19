package br.edu.ifba.swso.business.so.memorymanager;

import java.util.ArrayList;
import java.util.List;

public class PageMaker {

	private static PageMaker instance = null;
	private Pagina page;
	private List<Pagina> pageList;
	
	public Pagina criarPagina() {
		page = new Pagina();
		return page;
	}

	public List<Pagina> criarPageList() {
		pageList = new ArrayList<Pagina>();
		return pageList;
	}

	public static PageMaker getInstance() {
		if (instance == null) {
			instance = new PageMaker();
		}
		return instance;
	}


}
