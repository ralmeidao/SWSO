package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.algorithms.impl.process.FIFO;
import br.edu.ifba.swso.algorithms.interfaces.IProcessesScheduler;
import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.so.OperatingSystem;
import br.edu.ifba.swso.business.so.memorymanager.PageTable;

/**
 * @author Ramon
 */
@Named
@SessionScoped
public class ProcessoController extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MaquinaSessaoController maquinaSessaoController;
	
	private OperatingSystem operatingSystem;
	
	private IProcessesScheduler processesScheduler;

	private IProcessesScheduler[] arrayProcessesScheduler;
	
	private int timeslice;

	private File file;
	
	private PageTable pageTable;
	
	@PostConstruct
	public void init() {
		this.timeslice = 5;
		this.operatingSystem = maquinaSessaoController.getOperatingSystem();
		this.arrayProcessesScheduler = new IProcessesScheduler[]{new FIFO()};
		this.operatingSystem.setTimeslice(timeslice);
		this.processesScheduler = arrayProcessesScheduler[0];
	}

	public void salvarConfiguracoesProcesso() {
    	operatingSystem.setProcessesScheduler(processesScheduler);
    	operatingSystem.setTimeslice(timeslice);
    }
	
    public void newProcess() {
    	operatingSystem.criarProcesso(file);
    	file = null;
    	updateComponentes(":formSimulacao");
    }
    
	public void abrirModalPbc(int pid) {
		  this.pageTable = operatingSystem.getMemoryManager().getPageList().get(pid);
		  updateComponentes("modalPbcForm");
		  showDialog("modalPbc");
	}

	//ACCESS METHODS
	public IProcessesScheduler getProcessesScheduler() {
		return processesScheduler;
	}

	public void setProcessesScheduler(IProcessesScheduler processesScheduler) {
		this.processesScheduler = processesScheduler;
	}
	
	public IProcessesScheduler[] getArrayProcessesScheduler() {
		return arrayProcessesScheduler;
	}

	public void setArrayProcessesScheduler(IProcessesScheduler[] arrayProcessesScheduler) {
		this.arrayProcessesScheduler = arrayProcessesScheduler;
	}

	public int getTimeslice() {
		return timeslice;
	}

	public void setTimeslice(int timeslice) {
		this.timeslice = timeslice;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public PageTable getPageTable() {
		return pageTable;
	}

	public void setPageTable(PageTable pageTable) {
		this.pageTable = pageTable;
	}

}
