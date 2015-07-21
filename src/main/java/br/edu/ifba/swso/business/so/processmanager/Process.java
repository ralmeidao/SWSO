package br.edu.ifba.swso.business.so.processmanager;

import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.enumerator.ProcessStateEnum;
import br.edu.ifba.swso.util.Constantes;

public class Process {
	private int pid;
	private String nome;

	private int priority;
	private ProcessStateEnum state; // TODO CRIAR ENUM
	private long timeInitCpu;

	private int timeSlice;
	private int timeRunning;

	private int pc;
	private Word ri;

	private int tamanhoProcessoByte;
	private int tamanhoProcessoBit;
	private int quantidadeInstrucoes;

	public Process() {
		this.priority = Constantes.PRIORITY_INITIAL;
		this.timeSlice = Constantes.TIME_SLICE;
		this.state = ProcessStateEnum.PRONTO;
		this.timeInitCpu = 0;
		this.timeRunning = 0;
		this.pc = 0;
		this.ri = null;
	}

	public void incrementTimeRunning() {
		this.timeRunning++;
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public ProcessStateEnum getState() {
		return state;
	}

	public void setState(ProcessStateEnum state) {
		this.state = state;
	}

	public long getTimeInitCpu() {
		return timeInitCpu;
	}

	public void setTimeInitCpu(long timeInitCpu) {
		this.timeInitCpu = timeInitCpu;
	}

	public void setTimeRunning(int timeRunning) {
		this.timeRunning = timeRunning;
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
