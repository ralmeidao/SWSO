package br.edu.ifba.swso.business.simulacao;


public class Simulacao {
	
	private Long id;
	private SimulacaoProcessoVO simulacaoProcesso;
	
	public Simulacao() {
		simulacaoProcesso = new SimulacaoProcessoVO();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SimulacaoProcessoVO getSimulacaoProcesso() {
		return simulacaoProcesso;
	}

	public void setSimulacaoProcesso(SimulacaoProcessoVO simulacaoProcesso) {
		this.simulacaoProcesso = simulacaoProcesso;
	}

}
