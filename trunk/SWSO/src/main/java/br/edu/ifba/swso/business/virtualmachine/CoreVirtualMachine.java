package br.edu.ifba.swso.business.virtualmachine;

import br.edu.ifba.swso.business.VirtualMachineParameters;
import br.edu.ifba.swso.business.virtualmachine.harddisk.HardDisk;

public class CoreVirtualMachine  {

	private final RandomAccessMemory randomAccessMemory;
	
	private final CentralProcessingUnit centralProcessingUnit;
	
	private final HardDisk hardDisk;
	
	private final VirtualMachineParameters virtualMachineParameters;
	
    public CoreVirtualMachine(VirtualMachineParameters virtualMachineParameters) {
    	this.virtualMachineParameters = virtualMachineParameters;
        randomAccessMemory = new RandomAccessMemory();
        centralProcessingUnit = new CentralProcessingUnit(randomAccessMemory);
        hardDisk = new HardDisk(virtualMachineParameters); 
    }

    public RandomAccessMemory getRandomAccessMemory() {
        return randomAccessMemory;
    }

    public CentralProcessingUnit getCentralProcessingUnit() {
        return centralProcessingUnit;
    }

	public HardDisk getHardDisk() {
		return hardDisk;
	}

	public VirtualMachineParameters getVirtualMachineParameters() {
		return virtualMachineParameters;
	}
	
}
