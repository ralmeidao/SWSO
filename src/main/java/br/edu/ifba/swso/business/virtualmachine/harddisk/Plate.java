package br.edu.ifba.swso.business.virtualmachine.harddisk;

import br.edu.ifba.swso.business.VirtualMachineParameters;

public class Plate {
	private final Track[] tracks;
	
	public Plate(VirtualMachineParameters virtualMachineParameters) {
		tracks = new Track[virtualMachineParameters.getPlateSize()];
		for (int i = 0; i < tracks.length; i++) {
			tracks[i] = new Track(virtualMachineParameters);
		}
	}

	public Track[] getTracks() {
		return tracks;
	}
}
