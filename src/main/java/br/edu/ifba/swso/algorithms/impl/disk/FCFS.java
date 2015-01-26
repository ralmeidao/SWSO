package br.edu.ifba.swso.algorithms.impl.disk;

import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;

public class FCFS implements IDiskScheduler {

	private final String nome = "FCFS";
	
	@Override
	public int[] reordenaLista(int[] freeSectorsList, int setorAtual) {
		return freeSectorsList;
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
		FCFS other = (FCFS) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
