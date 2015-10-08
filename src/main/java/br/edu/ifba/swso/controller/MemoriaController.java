package br.edu.ifba.swso.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.algorithms.IDiskScheduler;
import br.edu.ifba.swso.algorithms.impl.disk.CSCAN;
import br.edu.ifba.swso.algorithms.impl.disk.FCFS;
import br.edu.ifba.swso.algorithms.impl.disk.SCAN;
import br.edu.ifba.swso.algorithms.impl.disk.SSTF;
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
	
	//BUSINESS DATA - START
	private IDiskScheduler[] arrayDiskSchedule;
	
	private IDiskScheduler diskSchedule;
	
	private KernelOperatingSystem kernelOperatingSystem;
	
	//VIEW DATA - START
	
	@PostConstruct
	public void init() throws IOException {
		applicationController.put(this);
		if (maquinaSessaoController.getOperatingSystem() != null) {
			VirtualMachineParameters virtualMachineParameters = maquinaSessaoController.getVirtualMachineParameters();
			kernelOperatingSystem = maquinaSessaoController.getOperatingSystem();
			arrayDiskSchedule = new IDiskScheduler[]{new SSTF(virtualMachineParameters), 
													 new FCFS(virtualMachineParameters), 
													 new SCAN(virtualMachineParameters),
													 new CSCAN(virtualMachineParameters)};
			diskSchedule = arrayDiskSchedule[0];
			kernelOperatingSystem.setDiskSchedule(diskSchedule);
		}
	}
	
	public void restart() {
		
	}
	
    public void salvarConfiguracoesDisco() {
    	kernelOperatingSystem.setDiskSchedule(diskSchedule);
    }
    
	//ACCESS METHODS
	public IDiskScheduler getDiskSchedule() {
		return diskSchedule;
	}

	public void setDiskSchedule(IDiskScheduler diskSchedule) {
		this.diskSchedule = diskSchedule;
	}

	public IDiskScheduler[] getArrayDiskSchedule() {
		return arrayDiskSchedule;
	}

	public void setArrayDiskSchedule(IDiskScheduler[] arrayDiskSchedule) {
		this.arrayDiskSchedule = arrayDiskSchedule;
	}
	
	public VirtualMachineParameters getVirtualMachineParameters() {
		return maquinaSessaoController.getVirtualMachineParameters();
	}
	
}
