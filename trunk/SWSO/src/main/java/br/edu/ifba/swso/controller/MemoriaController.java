package br.edu.ifba.swso.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.business.VirtualMachineParameters;

/**
 * @author Ramon
 */
@Named
@SessionScoped
public class MemoriaController extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ApplicationController applicationController;
	
	@Inject
	private MaquinaSessaoController maquinaSessaoController;
	
	//BUSINESS DATA - START
	
	//VIEW DATA - START
	
	@PostConstruct
	public void init() throws IOException {
		applicationController.put(this);
	}
	
	public void restart() {
	}
	
    public void salvarConfiguracoesDisco() {
    }
    
	//ACCESS METHODS
	public VirtualMachineParameters getVirtualMachineParameters() {
		return maquinaSessaoController.getVirtualMachineParameters();
	}
	
}
