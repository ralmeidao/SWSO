package br.edu.ifba.swso.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.ifba.swso.vo.ProcessoVO;

/**
 * Classe de controle de acesso
 * @author Ramon
 *
 */
@Named
@SessionScoped
public class ProcessosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProcessoVO> listaProcessos;
	
	@PostConstruct
	public void init() {
		listaProcessos = new ArrayList<ProcessoVO>();
		listaProcessos.add(new ProcessoVO(1L, "Firefox", 52, "Executando"));
		listaProcessos.add(new ProcessoVO(2L, "Chrome", 160, "Pronto"));
		listaProcessos.add(new ProcessoVO(3L, "Safari", 12, "Pronto"));
		listaProcessos.add(new ProcessoVO(4L, "Opera", 70, "Suspenso"));
	}
	
	public List<ProcessoVO> getListaProcessos() {
		return listaProcessos;
	}

	public void setListaProcessos(List<ProcessoVO> listaProcessos) {
		this.listaProcessos = listaProcessos;
	}
	
	

}
