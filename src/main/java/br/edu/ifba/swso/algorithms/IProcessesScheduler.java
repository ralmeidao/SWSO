package br.edu.ifba.swso.algorithms;

import java.util.LinkedList;

import br.edu.ifba.swso.business.so.processmanager.Process;

public interface IProcessesScheduler {
	
	Process escalonar(LinkedList<Process> listaPronto);
	boolean isPreemptivo();
	boolean isPrioridade();
	boolean isInterromper(LinkedList<Process> listaPronto, Process emExecucao, int timeslice);
}
