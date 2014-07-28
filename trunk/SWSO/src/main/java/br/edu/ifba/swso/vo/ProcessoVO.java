package br.edu.ifba.swso.vo;

public class ProcessoVO {
	private Long id;
	private String nome;
	private Integer nInstrucoes;
	private String estado;
	
	public ProcessoVO(Long id, String nome, Integer nInstrucoes, String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.nInstrucoes = nInstrucoes;
		this.estado = estado;
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

	public Integer getnInstrucoes() {
		return nInstrucoes;
	}

	public void setnInstrucoes(Integer nInstrucoes) {
		this.nInstrucoes = nInstrucoes;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
