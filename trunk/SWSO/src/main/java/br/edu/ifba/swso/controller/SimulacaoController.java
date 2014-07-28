package br.edu.ifba.swso.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.edu.ifba.swso.vo.SimulacaoProcessoVO;

/**
 * Classe de controle de acesso
 * @author Ramon
 *
 */
@Named
@SessionScoped
public class SimulacaoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DualListModel<String> simulacoesDualList;
	
	private List<String> simulacoesSelecionadas;
	
	private List<String> simulacoesDisponiveis;
	
	private boolean simulaProcessos;
	private boolean simulaMemoria;
	private boolean simulaDisco;
	
	private SimulacaoProcessoVO simulacaoProcesso;
	
	@PostConstruct
	public void init() {
		simulacaoProcesso = new SimulacaoProcessoVO();
		
		simulacoesDisponiveis = new ArrayList<String>();
		simulacoesSelecionadas = new ArrayList<String>();

		simulacoesDisponiveis.add("Processos");
		simulacoesDisponiveis.add("Memória");
		simulacoesDisponiveis.add("Disco");
	         
		simulacoesDualList = new DualListModel<String>(simulacoesDisponiveis, simulacoesSelecionadas);
	}

	public DualListModel<String> getSimulacoesDualList() {
		return simulacoesDualList;
	}

	public void setSimulacoesDualList(DualListModel<String> simulacoesDualList) {
		this.simulacoesDualList = simulacoesDualList;
	}

	public void atualizaDualList() {
		simulaProcessos = false;
		simulaMemoria = false;
		simulaDisco = false;
		
		for(Object item : simulacoesDualList.getTarget()) {
		    String tipoSimulacao = (String) item;
		    if (tipoSimulacao.equals("Processos")) simulaProcessos = true;
		    if (tipoSimulacao.equals("Memória")) simulaMemoria = true;
		    if (tipoSimulacao.equals("Disco")) simulaDisco = true;
		}
    }

	public boolean isSimulaProcessos() {
		return simulaProcessos;
	}

	public boolean isSimulaMemoria() {
		return simulaMemoria;
	}

	public boolean isSimulaDisco() {
		return simulaDisco;
	}

	public boolean isExibeTrocaContexto() {
		String algoritmo = simulacaoProcesso.getAlgoritmo();
		if (algoritmo != null) {
			return algoritmo.equals("3") || algoritmo.equals("4");
		}
		return false;
	}

	public boolean isExibeTempoCorte() {
		String algoritmo = simulacaoProcesso.getAlgoritmo();
		if (algoritmo != null) {
			return algoritmo.equals("4");
		}
		return false;
	}
	
	//MÉTODOS DE ACESSO
	public SimulacaoProcessoVO getSimulacaoProcesso() {
		return simulacaoProcesso;
	}

	public void setSimulacaoProcesso(SimulacaoProcessoVO simulacaoProcesso) {
		this.simulacaoProcesso = simulacaoProcesso;
	}

}


