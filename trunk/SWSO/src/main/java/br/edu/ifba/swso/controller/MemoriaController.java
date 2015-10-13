package br.edu.ifba.swso.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.algorithms.IPageReplacementAlgorithm;
import br.edu.ifba.swso.algorithms.impl.memory.FIFO;
import br.edu.ifba.swso.business.VirtualMachineParameters;
import br.edu.ifba.swso.business.so.KernelOperatingSystem;

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
	
	private KernelOperatingSystem kernelOperatingSystem;
	
	private IPageReplacementAlgorithm pageReplacementAlgorithm;
	
	private IPageReplacementAlgorithm[] arrayPageReplacement;
	
	//BUSINESS DATA - START
	
	//VIEW DATA - START
	
	@PostConstruct
	public void init() throws IOException {
		this.applicationController.put(this);
		this.kernelOperatingSystem = maquinaSessaoController.getOperatingSystem();
		this.arrayPageReplacement = new IPageReplacementAlgorithm[]{new FIFO()};
		this.pageReplacementAlgorithm = arrayPageReplacement[0];
		this.kernelOperatingSystem.setPageReplacement(pageReplacementAlgorithm);
	}
	
	public void restart() {
	}
	
    public void salvarConfiguracoesMemoria() {
    }
    
	//ACCESS METHODS
	public IPageReplacementAlgorithm getPageReplacement() {
		return pageReplacementAlgorithm;
	}

	public void setPageReplacement(IPageReplacementAlgorithm pageReplacementAlgorithm) {
		this.pageReplacementAlgorithm = pageReplacementAlgorithm;
	}

	public IPageReplacementAlgorithm[] getArrayPageReplacement() {
		return arrayPageReplacement;
	}

	public void setArrayPageReplacement(IPageReplacementAlgorithm[] arrayPageReplacement) {
		this.arrayPageReplacement = arrayPageReplacement;
	}
	
	public VirtualMachineParameters getVirtualMachineParameters() {
		return maquinaSessaoController.getVirtualMachineParameters();
	}

	public int getPoliticaBusca() {
		return maquinaSessaoController.getOperatingSystem().getMemoryManager().getPoliticaBusca();
	}

	public void setPoliticaBusca(int politicaBusca) {
		this.maquinaSessaoController.getOperatingSystem().getMemoryManager().setPoliticaBusca(politicaBusca);
	}

	public int getPoliticaAlocacao() {
		return maquinaSessaoController.getOperatingSystem().getMemoryManager().getPoliticaAlocacao();
	}

	public void setPoliticaAlocacao(int politicaAlocacao) {
		this.maquinaSessaoController.getOperatingSystem().getMemoryManager().setPoliticaAlocacao(politicaAlocacao);
	}

	public int getNumeroMaxFrames() {
		return maquinaSessaoController.getOperatingSystem().getMemoryManager().getNumeroMaxFrames();
	}

	public void setNumeroMaxFrames(int numeroMaxFrames) {
		this.maquinaSessaoController.getOperatingSystem().getMemoryManager().setNumeroMaxFrames(numeroMaxFrames);
	}
	
}
