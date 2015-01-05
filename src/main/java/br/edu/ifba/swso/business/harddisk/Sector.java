package br.edu.ifba.swso.business.harddisk;

import br.edu.ifba.swso.business.abstractions.ByteSWSO;
import br.edu.ifba.swso.util.Constantes;

public class Sector {
	private final ByteSWSO[] sector;
	
	private boolean empty;

	public Sector() {
		sector = new ByteSWSO[Constantes.SECTOR_SIZE];
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
