package br.edu.ifba.swso.business.processmanager;

import br.edu.ifba.swso.util.Constantes;

public class Processo {
	private int pid;
	private String nome;

	private int prioridade;
	private int estado; //TODO CRIAR ENUM
	private int tempoCpu;
	private int pc;
	private int ri;
	private int tamanhoProcessoByte;
	private int tamanhoProcessoBit;
	private int quantidadeInstrucoes;
	
	public Processo(){
		this.prioridade = Constantes.PRIORITY_INITIAL;          
		this.estado = 0;
		this.tempoCpu = 0;            
		this.pc = 0;                  
		this.ri = 0;
	}
	
	//METHODS OF ACCESS
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getTempoCpu() {
		return tempoCpu;
	}
	public void setTempoCpu(int tempoCpu) {
		this.tempoCpu = tempoCpu;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getRi() {
		return ri;
	}
	public void setRi(int ri) {
		this.ri = ri;
	}
	public int getTamanhoProcessoByte() {
		return tamanhoProcessoByte;
	}
	public void setTamanhoProcessoByte(int tamanhoProcessoByte) {
		this.tamanhoProcessoByte = tamanhoProcessoByte;
	}
	public int getTamanhoProcessoBit() {
		return tamanhoProcessoBit;
	}
	public void setTamanhoProcessoBit(int tamanhoProcessoBit) {
		this.tamanhoProcessoBit = tamanhoProcessoBit;
	}
	public int getQuantidadeInstrucoes() {
		return quantidadeInstrucoes;
	}
	public void setQuantidadeInstrucoes(int quantidadeInstrucoes) {
		this.quantidadeInstrucoes = quantidadeInstrucoes;
	}
	
	
}
