package br.edu.ifba.swso.business.harddisk;

import br.edu.ifba.swso.util.Constantes;

public class Plate {
	private final Track[] tracks;
	
	public Plate() {
		tracks = new Track[Constantes.PLATE_SIZE];
		for (int i = 0; i < tracks.length; i++) {
			tracks[i] = new Track();
		}
	}

	public Track[] getTracks() {
		return tracks;
	}
}
