package br.edu.ifba.swso.algorithms.impl.process;

import java.util.LinkedList;

import br.edu.ifba.swso.algorithms.IProcessesScheduler;
import br.edu.ifba.swso.business.so.processmanager.Process;

public class FilasMultiplas implements IProcessesScheduler {
	
	private final String nome = "Filas Multiplas (Preemptivo)";

	public FilasMultiplas() {
	}
	
	@Override
	public void escalonar(LinkedList<Process> listaPronto) {
		
	}

	@Override
	public boolean isPreemptivo() {
		return true;
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
		FilasMultiplas other = (FilasMultiplas) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
