package br.edu.ifba.swso.negocio.harddisk;

import br.edu.ifba.swso.negocio.abstracoes.ByteSWSO;
import br.edu.ifba.swso.util.Constantes;

public class HardDisk {

	private final Plate[] hd;
	
	public HardDisk() {
		hd = new Plate[Constantes.DISK_SIZE];
		for (int i = 0; i < hd.length; i++) {
			hd[i] = new Plate();
		}
	}

	public void allocateData(ByteSWSO[] data, int nSector) {
		int plate = getPlate(nSector);
		hd[plate].allocateData(data, nSector);
	}

	public ByteSWSO[] getData(int nSector) {
		return hd[getPlate(nSector)].getData(nSector);
	}

	private int getPlate(int nSector) {
		return  nSector / (Constantes.TRACK_SIZE * Constantes.PLATE_SIZE);
	}

	public Plate[] getHd() {
		return hd;
	}
	
}
