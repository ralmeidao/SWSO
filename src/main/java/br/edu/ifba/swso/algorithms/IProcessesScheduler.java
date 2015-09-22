package br.edu.ifba.swso.algorithms;

import java.util.LinkedList;

import br.edu.ifba.swso.business.so.processmanager.Process;

public interface IProcessesScheduler {
	
	void escalonar(LinkedList<Process> listaPronto);
	boolean isPreemptivo();
	boolean isPrioridade();
}
