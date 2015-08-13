package br.edu.ifba.swso.business.virtualmachine.harddisk;

import br.edu.ifba.swso.business.VirtualMachineParameters;
import br.edu.ifba.swso.business.abstractions.ByteSWSO;

public class Sector {
	private final ByteSWSO[] sector;
	
	private boolean empty;

	public Sector(VirtualMachineParameters virtualMachineParameters) {
		sector = new ByteSWSO[virtualMachineParameters.getSectorSize()];
		empty = true;
	}

	public void addData(ByteSWSO[] data) {
		for (int i = 0; i < sector.length; i++) {
			sector[i] = data[i];
		}
		empty = false;
	}

	public ByteSWSO[] getData() {
		return this.sector;
	}

	public ByteSWSO[] getSector() {
		return sector;
	}

	public boolean isEmpty() {
		return empty;
	}
	
	public void clear() {
		for (int i = 0; i < sector.length; i++) {
			sector[i] = null;
		}
		empty = true;
	}

}
