package br.edu.ifba.swso.algorithms.impl.disk;

import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;

public class FCFS implements IDiskScheduler {

	@Override
	public int[] reordenaLista(int[] freeSectorsList, int setorAtual) {
		return freeSectorsList;
	}

}
