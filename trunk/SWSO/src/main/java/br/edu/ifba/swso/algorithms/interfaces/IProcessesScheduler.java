package br.edu.ifba.swso.algorithms.interfaces;

import java.util.LinkedList;

import br.edu.ifba.swso.business.so.processmanager.Process;

public interface IProcessesScheduler {
	
	void escalonar(LinkedList<Process> listaPronto);
	
}
