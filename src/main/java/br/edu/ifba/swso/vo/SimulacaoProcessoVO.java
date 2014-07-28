package br.edu.ifba.swso.vo;

public class SimulacaoProcessoVO {
	private Long id;
	private String algoritmo;
	private Integer trocaContexto;
	private Integer tempoCorte;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlgoritmo() {
		return algoritmo;
	}

	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}

	public Integer getTrocaContexto() {
		return trocaContexto;
	}

	public void setTrocaContexto(Integer trocaContexto) {
		this.trocaContexto = trocaContexto;
	}

	public Integer getTempoCorte() {
		return tempoCorte;
	}

	public void setTempoCorte(Integer tempoCorte) {
		this.tempoCorte = tempoCorte;
	}

}
