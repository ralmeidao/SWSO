package br.edu.ifba.swso.business.so.processmanager;

import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.util.Constantes;

public class Processo {
	private int pid;
	private String nome;

	private int prioridade;
	private int estado; // TODO CRIAR ENUM
	private int tempoCpu;

	private int timeSlice;
	private int timeRunning;

	private int pc;
	private Word ri;

	private int tamanhoProcessoByte;
	private int tamanhoProcessoBit;
	private int quantidadeInstrucoes;

	public Processo() {
		this.prioridade = Constantes.PRIORITY_INITIAL;
		this.estado = 0;
		this.tempoCpu = 0;
		this.timeSlice = 5;
		this.timeRunning = 0;
		this.pc = 0;
		this.ri = null;
	}

	public void incrementTimeCPU() {
		tempoCpu++;
	}

	public void incrementTimeRunning() {
		this.timeRunning++;
	}

	public void clearTimeRunning() {
		this.timeRunning = 0;
	}

	// METHODS OF ACCESS
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

	public int getTimeSlice() {
		return timeSlice;
	}

	public void setTimeSlice(int timeSlice) {
		this.timeSlice = timeSlice;
	}

	public int getTimeRunning() {
		return timeRunning;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public Word getRi() {
		return ri;
	}

	public void setRi(Word ri) {
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
