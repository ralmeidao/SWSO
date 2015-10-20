package br.edu.ifba.swso.algorithms.impl.process;

import java.util.LinkedList;

import br.edu.ifba.swso.algorithms.IProcessesScheduler;
import br.edu.ifba.swso.business.so.processmanager.Process;

public class ShortestJobFirstPreemptive implements IProcessesScheduler {
	
	private final String nome = "Shortest Job First(preemptivo)";

	public ShortestJobFirstPreemptive() {
	}
	
	@Override
	public Process escalonar(LinkedList<Process> listaPronto) {
		int menorJob = 999999999;
		Process selected = null;
		for (Process process : listaPronto) {
			if(process.getQuantidadeInstrucoes() < menorJob) {
				menorJob = process.getQuantidadeInstrucoes();
				selected = process;
			}
		}
		return selected;
	}

	@Override
	public boolean isInterromper(LinkedList<Process> listaPronto, Process running, int timeslice) {
		if (running.getPid() == -1 || running.isBlocked() || running.isEnding()) {
			return true;
		}
		for (Process pronto : listaPronto) {
			if (pronto.getQuantidadeInstrucoes() < running.getQuantidadeInstrucoes()) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isPreemptivo() {
		return false;
	}
	
	@Override
	public boolean isPrioridade() {
		return false;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShortestJobFirstPreemptive other = (ShortestJobFirstPreemptive) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
