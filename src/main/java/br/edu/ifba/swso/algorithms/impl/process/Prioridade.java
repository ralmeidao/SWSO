package br.edu.ifba.swso.algorithms.impl.process;

import java.util.LinkedList;

import br.edu.ifba.swso.algorithms.IProcessesScheduler;
import br.edu.ifba.swso.business.so.processmanager.Process;

public class Prioridade implements IProcessesScheduler {
	
	private final String nome = "Prioridade (não preemptivo)";
	
	public Prioridade() {
	}
	
	@Override
	public Process escalonar(LinkedList<Process> listaPronto) {
		int maiorPrioridade = 99;
		Process selected = null;
		for (Process process : listaPronto) {
			if(process.getPriority() < maiorPrioridade) {
				maiorPrioridade = process.getPriority();
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
			if (pronto.getPriority() > running.getPriority()) {
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
		return true;
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
		Prioridade other = (Prioridade) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
