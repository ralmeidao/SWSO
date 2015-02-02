package br.edu.ifba.swso.business.virtualmachine;

import br.edu.ifba.swso.business.virtualmachine.cpu.RandomAccessMemory;
import br.edu.ifba.swso.business.virtualmachine.harddisk.HardDisk;

public class CoreVirtualMachine  {

	private final RandomAccessMemory randomAccessMemory;
	
	private final CentralProcessingUnit centralProcessingUnit;
	
	private final HardDisk hardDisk;
	
    public CoreVirtualMachine() {
        randomAccessMemory = new RandomAccessMemory();
        centralProcessingUnit = new CentralProcessingUnit(randomAccessMemory);
        hardDisk = new HardDisk(); 
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

}
