package br.edu.ifba.swso.display;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		List<Process> lista = new ArrayList<Process>(listaProcesso.values());
		Collections.sort(lista, new Comparator<Process>() {
			@Override
			public int compare(Process a, Process b) {
				return a.getNome().compareTo(b.getNome());
			}
		});
		lista.remove(new Process(-1));
		return lista;
	}

	public void incrementList(Process processo) {
		listaIds.add(processo.getPid());
		if (!listaProcesso.containsKey(processo.getPid())) {
			listaProcesso.put(processo.getPid(), processo);
		}
	}
	
}
