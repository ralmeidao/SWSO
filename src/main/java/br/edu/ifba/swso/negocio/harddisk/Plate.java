package br.edu.ifba.swso.negocio.harddisk;

import br.edu.ifba.swso.negocio.abstracoes.ByteSWSO;
import br.edu.ifba.swso.util.Constantes;

public class Plate {
	private final Track[] tracks;

	public Plate() {
		tracks = new Track[Constantes.PLATE_SIZE];
		for (int i = 0; i < tracks.length; i++) {
			tracks[i] = new Track();
		}
	}

	public void allocateData(ByteSWSO[] data, int nSector) {
		int track = getTrack(nSector);
		tracks[track].allocateData(data, nSector);
	}

	public ByteSWSO[] getData(int sector) {
		return this.tracks[getTrack(sector)].getData(sector);
	}

	private int getTrack(int sector) {
		return (sector % (Constantes.TRACK_SIZE * Constantes.PLATE_SIZE))/Constantes.TRACK_SIZE;
	}

	public Track[] getTracks() {
		return tracks;
	}
	
}
