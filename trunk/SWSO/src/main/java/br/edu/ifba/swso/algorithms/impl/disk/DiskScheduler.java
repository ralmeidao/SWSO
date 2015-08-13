package br.edu.ifba.swso.algorithms.impl.disk;

import br.edu.ifba.swso.business.VirtualMachineParameters;

public class DiskScheduler {
	protected VirtualMachineParameters virtualMachineParameters;
	
	protected DiskScheduler(VirtualMachineParameters virtualMachineParameters) {
		this.virtualMachineParameters = virtualMachineParameters;
	}
	
	protected int getTrackSize() {
		return virtualMachineParameters.getTrackSize();	
	}
	
	protected int getPlateSize() {
		return virtualMachineParameters.getPlateSize();	
	}
}
