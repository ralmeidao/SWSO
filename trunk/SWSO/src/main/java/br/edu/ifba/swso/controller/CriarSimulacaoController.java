package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.edu.ifba.swso.business.simulacao.Simulacao;

/**
 * Classe de controle de acesso
 * @author Ramon
 *
 */
@Named
@ViewScoped
public class CriarSimulacaoController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Simulacao simulacao;
	
	@PostConstruct
	public void init() {
		simulacao = new Simulacao();
	}

	public boolean isExibeTrocaContexto() {
		String algoritmo = simulacao.getSimulacaoProcesso().getAlgoritmo();
		if (algoritmo != null) {
			return algoritmo.equals("3") || algoritmo.equals("4");
		}
		return false;
	}

	public boolean isExibeTempoCorte() {
		String algoritmo = simulacao.getSimulacaoProcesso().getAlgoritmo();
		if (algoritmo != null) {
			return algoritmo.equals("4");
		}
		return false;
	}
	
	//MÉTODOS DE ACESSO
	public Simulacao getSimulacao() {
		return simulacao;
	}

	public void setSimulacao(Simulacao simulacao) {
		this.simulacao = simulacao;
	}
}


