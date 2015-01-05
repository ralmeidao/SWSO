package br.edu.ifba.swso.business.harddisk;

import java.util.LinkedList;
import java.util.List;

import br.edu.ifba.swso.business.abstractions.ByteSWSO;
import br.edu.ifba.swso.display.MovimentoCabecoteHD;
import br.edu.ifba.swso.util.Constantes;

public class HardDisk {

	private final Plate[] hd;
	private final List<MovimentoCabecoteHD> listMoveReaderHead;
	private int positionReaderHead = 0;
	
	public HardDisk() {
		listMoveReaderHead = new LinkedList<MovimentoCabecoteHD>();
		hd = new Plate[Constantes.DISK_SIZE];
		for (int i = 0; i < hd.length; i++) {
			hd[i] = new Plate();
		}
	}

	public void allocateData(ByteSWSO[] data, int nSector) {
		getSector(nSector).addData(data);
	}

	public ByteSWSO[] getData(int nSector) {
		return getSector(nSector).getData();
	}
	
	public Plate[] getHd() {
		return hd;
	}

	private int getPlate(int nSector) {
		return  nSector / (Constantes.TRACK_SIZE * Constantes.PLATE_SIZE);
	}
	
	private int getTrack(int sector) {
		return (sector % (Constantes.TRACK_SIZE * Constantes.PLATE_SIZE))/Constantes.TRACK_SIZE;
	}

	private int getPosition(int sector) {
		return (sector % (Constantes.TRACK_SIZE * Constantes.PLATE_SIZE))%Constantes.TRACK_SIZE;
	}
	
	private Sector getSector(int nSector) {
		int nPlate = getPlate(nSector);
		int nTrack = getTrack(nSector);
		int position = getPosition(nSector);
		moveReaderHead(nTrack);
		
		return hd[nPlate].getTracks()[positionReaderHead].getSectors()[position];
	}
	
	public void moveReaderHead(int nTrack) {
		if (nTrack != positionReaderHead) {
			System.out.println("Move of " + positionReaderHead + " to " + nTrack);
			listMoveReaderHead.add(new MovimentoCabecoteHD(positionReaderHead, nTrack));
			positionReaderHead = nTrack;
		}
	}

	public List<MovimentoCabecoteHD> getListMoveReaderHead() {
		return listMoveReaderHead;
	}

	public int getPositionReaderHead() {
		return positionReaderHead;
	}
	
}
