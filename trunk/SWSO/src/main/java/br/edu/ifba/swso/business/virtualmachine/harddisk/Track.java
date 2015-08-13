package br.edu.ifba.swso.business.virtualmachine.harddisk;

import br.edu.ifba.swso.business.VirtualMachineParameters;

public class Track {
	private final Sector[] sectors;

	public Track(VirtualMachineParameters virtualMachineParameters) {
		sectors = new Sector[virtualMachineParameters.getTrackSize()];
		for (int i = 0; i < sectors.length; i++) {
			sectors[i] = new Sector(virtualMachineParameters);
		}
	}
	

	public Sector[] getSectors() {
		return sectors;
	}
	
}
