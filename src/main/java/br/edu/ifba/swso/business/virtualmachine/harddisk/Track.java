package br.edu.ifba.swso.business.virtualmachine.harddisk;

import br.edu.ifba.swso.util.Constantes;

public class Track {
	private final Sector[] sectors;

	public Track() {
		sectors = new Sector[Constantes.TRACK_SIZE];
		for (int i = 0; i < sectors.length; i++) {
			sectors[i] = new Sector();
		}
	}
	

	public Sector[] getSectors() {
		return sectors;
	}
	
}
