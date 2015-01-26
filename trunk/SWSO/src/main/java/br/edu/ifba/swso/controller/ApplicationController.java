package br.edu.ifba.swso.controller;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ApplicationController {
	
	private Map<String, MaquinaSessaoController> maquinasAtivas;

	public ApplicationController() {
		maquinasAtivas = new HashMap<String, MaquinaSessaoController>();	
	}
	
	public Map<String, MaquinaSessaoController> getMaquinasAtivas() {
		return maquinasAtivas;
	}

}
