package br.edu.ifba.swso.negocio.harddisk;

import br.edu.ifba.swso.negocio.abstracoes.ByteSWSO;
import br.edu.ifba.swso.util.Constantes;

public class Track {
	private final Sector[] sectors;

	public Track() {
		sectors = new Sector[Constantes.TRACK_SIZE];
		for (int i = 0; i < sectors.length; i++) {
			sectors[i] = new Sector();
		}
	}

	public void allocateData(ByteSWSO[] data, int nSector){
		int position = (nSector % (Constantes.TRACK_SIZE * Constantes.PLATE_SIZE))%Constantes.TRACK_SIZE;
    	sectors[position].addData(data);
	}
	
	public ByteSWSO[] getData(int sector) {
		return this.sectors[sector].getData();
	}

	public Sector[] getSectors() {
		return sectors;
	}
	
}
