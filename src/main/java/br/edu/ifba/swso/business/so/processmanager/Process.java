package br.edu.ifba.swso.business.so.processmanager;

import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.enumerator.ProcessStateEnum;
import br.edu.ifba.swso.util.Constantes;

public class Process {
	private int pid;
	private String nome;

	private int priority;
	private ProcessStateEnum state;
	private int timeInitCpu;

	private int timeRunning;
	private int timeWaiting;
	private int timeFinishCpu;

	private int pc;
	private Word ri;

	private int tamanhoProcessoByte;
	private int tamanhoProcessoBit;
	private int quantidadeInstrucoes;
	
	private int numeroMaxFrames;
	
	private File File;
	
	public Process(int pid) {
		this();
		this.pid = pid;
	}
	
	public Process() {
		this.priority = Constantes.PRIORITY_INITIAL;
		this.state = ProcessStateEnum.PRONTO;
		this.timeInitCpu = 0;
		this.timeRunning = 0;
		this.pc = 0;
		this.ri = null;
		this.numeroMaxFrames = 4;
	}

	public void incrementTimeRunning() {
		this.timeRunning++;
	}

	public void incrementTimeWaiting() {
		this.timeWaiting++;
	}
	
	public int getTimeTurnaround() {
		return timeFinishCpu - timeInitCpu;
	}

	// METHODS OF ACCESS
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPidConcatNome() {
		if (pid == -1) {
			return "Processador Ocioso";
		}
		return pid + " - " +nome;
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

	public int getTimeInitCpu() {
		return timeInitCpu;
	}

	public void setTimeInitCpu(int timeInitCpu) {
		this.timeInitCpu = timeInitCpu;
	}

	public int getTimeRunning() {
		return timeRunning;
	}

	public int getTimeWaiting() {
		return timeWaiting;
	}

	public int getTimeFinishCpu() {
		return timeFinishCpu;
	}

	public void setTimeFinishCpu(int timeFinishCpu) {
		this.timeFinishCpu = timeFinishCpu;
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

	public File getFile() {
		return File;
	}

	public void setFile(File file) {
		File = file;
	}
	
	public int getNumeroMaxFrames() {
		return numeroMaxFrames;
	}

	public void setNumeroMaxFrames(int numeroMaxFrames) {
		this.numeroMaxFrames = numeroMaxFrames;
	}

	public boolean isBlocked() {
		return ProcessStateEnum.BLOQUEADO.equals(state);
	}
	
	public boolean isRunning() {
		return ProcessStateEnum.EXECUTANDO.equals(state);
	}
	
	public boolean isEnding() {
		return ProcessStateEnum.FINALIZADO.equals(state);
	}
	
	@Override
	public boolean equals(final Object other) {
	    if (this == other)
	        return true;
	    if (!(other instanceof Process))
	        return false;
	    Process castOther = (Process) other;
	    return castOther.pid == this.pid;
	}
	
}
