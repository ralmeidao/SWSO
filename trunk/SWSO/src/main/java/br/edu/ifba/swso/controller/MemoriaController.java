package br.edu.ifba.swso.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.algorithms.IPageReplacementAlgorithm;
import br.edu.ifba.swso.algorithms.impl.memory.FIFO;
import br.edu.ifba.swso.business.VirtualMachineParameters;
import br.edu.ifba.swso.business.so.KernelOperatingSystem;
import br.edu.ifba.swso.business.so.memorymanager.ETP;
import br.edu.ifba.swso.business.so.memorymanager.PageTable;
import br.edu.ifba.swso.business.so.processmanager.Process;
import br.edu.ifba.swso.enumerator.PoliticaAlocacaoEnum;
import br.edu.ifba.swso.enumerator.PoliticaBuscaEnum;

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
    
    public Integer obterQuantidadePaginasProcesso(int pid){
    	PageTable pageTable = kernelOperatingSystem.getMemoryManager().getPageList().get(pid);
    	return pageTable.getListaEtp().size();
    }
    
    public Integer obterQuantidadeFramesProcesso(int pid){
    	PageTable pageTable = kernelOperatingSystem.getMemoryManager().getPageList().get(pid);
    	int qtdFrames = 0;
    	for (ETP etp : pageTable.getListaEtp()) {
    		if (etp.getBitV() == '1') qtdFrames++;
    	}
    	return qtdFrames;
    }
    
	public String obterProcessFontColor(int pid) {
		String color = obterProcessBackgroundColor(pid);
		
		int r = Integer.valueOf( color.substring( 0, 2 ), 16 );
        int g = Integer.valueOf( color.substring( 2, 4 ), 16 );
        int b = Integer.valueOf( color.substring( 4, 6 ), 16 ); 
        int grayLevel = (r + g + b) / 3;
        if (grayLevel < 128) {
        	return "FFFFFF";
        } else {
        	return "000000";
        }
		
    }
    
	public String obterProcessBackgroundColor(int pid) {
		String color = "FFFFFF";
		for (Process process : kernelOperatingSystem.getProcessManager().getTabelaProcesso()) {
			if (process.getPid() == pid) {
				color = process.getFile().getColor();	
			}
		}
		return color;
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

	public PoliticaBuscaEnum getPoliticaBusca() {
		return maquinaSessaoController.getOperatingSystem().getMemoryManager().getPoliticaBusca();
	}

	public void setPoliticaBusca(PoliticaBuscaEnum politicaBusca) {
		this.maquinaSessaoController.getOperatingSystem().getMemoryManager().setPoliticaBusca(politicaBusca);
	}

	public PoliticaAlocacaoEnum getPoliticaAlocacao() {
		return maquinaSessaoController.getOperatingSystem().getMemoryManager().getPoliticaAlocacao();
	}

	public void setPoliticaAlocacao(PoliticaAlocacaoEnum politicaAlocacao) {
		this.maquinaSessaoController.getOperatingSystem().getMemoryManager().setPoliticaAlocacao(politicaAlocacao);
	}

	public int getNumeroMaxFrames() {
		return maquinaSessaoController.getOperatingSystem().getMemoryManager().getNumeroMaxFrames();
	}

	public void setNumeroMaxFrames(int numeroMaxFrames) {
		this.maquinaSessaoController.getOperatingSystem().getMemoryManager().setNumeroMaxFrames(numeroMaxFrames);
	}
	
	public Integer getTamanhoMemoriaReal() {
		return this.maquinaSessaoController.getOperatingSystem().getMemoryManager().getRealMemory().size();
	}
	
	public List<PoliticaBuscaEnum> getPoliticasBusca() {
		return PoliticaBuscaEnum.getListaValues();
	}
	
	public List<PoliticaAlocacaoEnum> getPoliticasAlocacao() {
		return PoliticaAlocacaoEnum.getListaValues();
	}
	
	public boolean isPoliticaAlocacaoFixa() { 
		return PoliticaAlocacaoEnum.FIXA.equals(getPoliticaAlocacao());
	}
}
