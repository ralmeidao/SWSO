package br.edu.ifba.swso.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifba.swso.arquitetura.util.Util;
import br.edu.ifba.swso.security.Credentials;

public class Usuario {

	private Long id;
	private String nome;
	private String sobrenome;
	private String login;
	private String senha;
	private List<Perfil> listaPerfil;

	public Usuario() {
	}

	public Usuario(Credentials credentials) {
		this.login = credentials.getLogin();
		this.senha = credentials.getSenha();
	}

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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Perfil> getListaPerfil() {
		if (listaPerfil == null) {
			listaPerfil = new ArrayList<Perfil>();
		}
		return listaPerfil;
	}

	public void setListaPerfil(List<Perfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}
	
	public List<Menu> getListaMenu() {
		Map<Long, Menu> listaMenuSemRepetirItem = new HashMap<Long, Menu>();
		if (!Util.isNullOuVazio(listaPerfil)){
			for (Perfil perfil : listaPerfil) {
				for (Menu menu : perfil.getListaMenu()) {
					listaMenuSemRepetirItem.put(menu.getId(), menu);
				}
			}
		}
		return new ArrayList<Menu>(listaMenuSemRepetirItem.values());
	}

}
