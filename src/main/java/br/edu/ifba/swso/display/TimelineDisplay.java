package br.edu.ifba.swso.display;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifba.swso.business.so.processmanager.Process;

public class TimelineDisplay {
	
	private List<Integer> listaIds;
	
	private Map<Integer, Process> listaProcesso;
	
	public TimelineDisplay(){
		listaIds = new ArrayList<Integer>();
		listaProcesso = new HashMap<Integer, Process>();
	}
	
	public List<Integer> getListaIds() {
		return listaIds;
	}
	
	public List<Process> getListaProcesso() {
		return new ArrayList<Process>(listaProcesso.values());
	}

	public void incrementList(Process processo) {
		listaIds.add(processo.getPid());
		if (!listaProcesso.containsKey(processo.getPid())) {
			listaProcesso.put(processo.getPid(), processo);
		}
	}
	
}
