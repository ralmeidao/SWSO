package br.edu.ifba.swso.negocio.virtualmachine;

import br.edu.ifba.swso.negocio.cpu.RandomAccessMemory;
import br.edu.ifba.swso.negocio.harddisk.HardDisk;

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
