package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.algorithms.IProcessesScheduler;
import br.edu.ifba.swso.algorithms.impl.process.FIFO;
import br.edu.ifba.swso.algorithms.impl.process.RoundRobin;
import br.edu.ifba.swso.business.VirtualMachineParameters;
import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.so.KernelOperatingSystem;
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
	private ApplicationController applicationController;
	
	@Inject
	private MaquinaSessaoController maquinaSessaoController;
	
	private KernelOperatingSystem kernelOperatingSystem;
	
	private IProcessesScheduler processesScheduler;

	private IProcessesScheduler[] arrayProcessesScheduler;
	
	private int timeslice;

	private File file;
	
	private PageTable pageTable;
	
	private int priority;
	
	@PostConstruct
	public void init() {
		applicationController.put(this);
		this.timeslice = 5;
		this.priority = 5;
		this.kernelOperatingSystem = maquinaSessaoController.getOperatingSystem();
		this.arrayProcessesScheduler = new IProcessesScheduler[]{new FIFO(), new RoundRobin()};
		this.kernelOperatingSystem.setTimeslice(timeslice);
		this.processesScheduler = arrayProcessesScheduler[0];
		this.kernelOperatingSystem.setProcessesScheduler(processesScheduler);
	}
	
	public void restart() {
		this.timeslice = 5;
		this.priority = 5;
		this.kernelOperatingSystem = maquinaSessaoController.getOperatingSystem();
		this.kernelOperatingSystem.setTimeslice(timeslice);
		this.processesScheduler = arrayProcessesScheduler[0];
		this.kernelOperatingSystem.setProcessesScheduler(processesScheduler);
	}

	public void salvarConfiguracoesProcesso() {
    	kernelOperatingSystem.setProcessesScheduler(processesScheduler);
    	kernelOperatingSystem.setTimeslice(timeslice);
    }
	
    public void newProcess() {
    	kernelOperatingSystem.criarProcesso(file, priority);
    	file = null;
    	updateComponentes(":formSimulacao");
    }
    
	public void abrirModalPbc(int pid) {
		  this.pageTable = kernelOperatingSystem.getMemoryManager().getPageList().get(pid);
		  updateComponentes("modalPbcForm");
		  showDialog("modalPbc");
	}
	
	public void bloquearProcesso() {
		kernelOperatingSystem.getProcessManager().bloquearProcesso();
	}
	
	public void desbloquearProcesso(int pid) {
		kernelOperatingSystem.getProcessManager().desbloquearProcesso(pid);
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
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public PageTable getPageTable() {
		return pageTable;
	}

	public void setPageTable(PageTable pageTable) {
		this.pageTable = pageTable;
	}
	
	public VirtualMachineParameters getVirtualMachineParameters() {
		return maquinaSessaoController.getVirtualMachineParameters();
	}

}
