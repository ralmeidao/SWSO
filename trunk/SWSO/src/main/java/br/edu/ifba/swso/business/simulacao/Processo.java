package br.edu.ifba.swso.business.simulacao;

public class Processo {
	private int id;
	private String nome;
	private Integer nInstrucoes;
	private String estado;
	private byte[] arquivo;
	private String[] instrucoes;
			
	public Processo() {
		
	}
	
	public Processo(int id, String nome, Integer nInstrucoes, String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.nInstrucoes = nInstrucoes;
		this.estado = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
		this.instrucoes = new String(arquivo).split(";");
		this.nInstrucoes = instrucoes.length;
	}

	public String[] getInstrucoes() {
		return instrucoes;
	}
}
