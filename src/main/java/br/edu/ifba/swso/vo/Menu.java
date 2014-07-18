package br.edu.ifba.swso.vo;

import java.util.List;

public class Menu {
	private Long id;
	private String nome;
	private String link;
	private List<Menu> listaFilhos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Menu> getListaFilhos() {
		return listaFilhos;
	}

	public void setListaFilhos(List<Menu> listaFilhos) {
		this.listaFilhos = listaFilhos;
	}
	
}
