package br.edu.ifba.swso.security.dto;

import java.util.List;

import br.edu.ifba.swso.vo.Menu;

public class MenuItemVertical {
	
	private String nomeMenu;
	private String linkMenu;
	private int nivelMenu;
	
	private List<Menu> lista;
	
	public int getNivelMenu() {
		return nivelMenu;
	}
	public void setNivelMenu(int nivelMenu) {
		this.nivelMenu = nivelMenu;
	}
	public final String getNomeMenu() {
		return nomeMenu;
	}
	public final void setNomeMenu(String nomeMenu) {
		this.nomeMenu = nomeMenu;
	}
	public final String getLinkMenu() {
		return linkMenu;
	}
	public final void setLinkMenu(String linkMenu) {
		this.linkMenu = linkMenu;
	}
	public final List<Menu> getLista() {
		return lista;
	}
	public final void setLista(List<Menu> lista) {
		this.lista = lista;
	}
	
}
