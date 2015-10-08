package br.edu.ifba.swso.controller;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ApplicationController {
	
	private Map<String, MaquinaSessaoController> maquinasAtivas;
	private Map<String, DiscoController> discoControllers;
	private Map<String, ProcessoController> processoControllers;
	private Map<String, MemoriaController> memoriaControllers;

	public ApplicationController() {
		maquinasAtivas = new HashMap<String, MaquinaSessaoController>();	
		discoControllers = new HashMap<String, DiscoController>();	
		processoControllers = new HashMap<String, ProcessoController>();	
		memoriaControllers = new HashMap<String, MemoriaController>();	
	}
	
	public void put(MaquinaSessaoController m) {
		maquinasAtivas.put(m.getVirtualMachineParameters().getName(), m);
	}
	public void put(DiscoController d) {
		discoControllers.put(d.getVirtualMachineParameters().getName(), d);
	}
	public void put(ProcessoController p) {
		processoControllers.put(p.getVirtualMachineParameters().getName(), p);
	}
	public void put(MemoriaController m) {
		memoriaControllers.put(m.getVirtualMachineParameters().getName(), m);
	}
	
	public void remove(MaquinaSessaoController m){
		maquinasAtivas.remove(m.getVirtualMachineParameters().getName());
		discoControllers.remove(m.getVirtualMachineParameters().getName());
		processoControllers.remove(m.getVirtualMachineParameters().getName());
		memoriaControllers.remove(m.getVirtualMachineParameters().getName());
	}

	public MaquinaSessaoController get(String name) {
		return maquinasAtivas.get(name);
	}
	
	public boolean contemMaquina(String name) {
		return maquinasAtivas.containsKey(name);
	}

	public void remove(String s){
		maquinasAtivas.remove(s);
		discoControllers.remove(s);
		processoControllers.remove(s);
		memoriaControllers.remove(s);
	}
	
	public void restart(String name) {
		if (discoControllers.containsKey(name)) {
			discoControllers.get(name).restart(); 
		}
		if (processoControllers.containsKey(name)) {
			processoControllers.get(name).restart();
		}
		if (memoriaControllers.containsKey(name)) {
			memoriaControllers.get(name).restart();
		}
	}
}
