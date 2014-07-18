package br.edu.ifba.swso.vo;

import java.util.ArrayList;
import java.util.List;

public class Perfil {
	private List<Menu> listaMenu;

	public List<Menu> getListaMenu() {
		if(listaMenu == null){
			listaMenu = new ArrayList<Menu>();
		}
		return listaMenu;
	}

	public void setListaMenu(List<Menu> listaMenu) {
		this.listaMenu = listaMenu;
	}
}
