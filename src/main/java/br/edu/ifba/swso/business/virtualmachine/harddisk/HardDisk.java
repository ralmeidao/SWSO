package br.edu.ifba.swso.business.virtualmachine.harddisk;

import java.util.LinkedList;
import java.util.List;

import br.edu.ifba.swso.business.VirtualMachineParameters;
import br.edu.ifba.swso.business.abstractions.ByteSWSO;
import br.edu.ifba.swso.display.MovimentoCabecoteHD;

public class HardDisk {

	private final Plate[] hd;
	private final List<MovimentoCabecoteHD> listMoveReaderHead;
	private int positionReaderHead = 0;
	private VirtualMachineParameters virtualMachineParameters; 
	
	public HardDisk(VirtualMachineParameters virtualMachineParameters) {
		this.virtualMachineParameters = virtualMachineParameters;
		listMoveReaderHead = new LinkedList<MovimentoCabecoteHD>();
		hd = new Plate[virtualMachineParameters.getDiskSize()];
		for (int i = 0; i < hd.length; i++) {
			hd[i] = new Plate(virtualMachineParameters);
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
		return  nSector / (getTrackSize() * virtualMachineParameters.getPlateSize());
	}
	
	private int getTrack(int sector) {
		return (sector % (getTrackSize()* virtualMachineParameters.getPlateSize()))/getTrackSize();
	}

	private int getPosition(int sector) {
		return (sector % (getTrackSize() * virtualMachineParameters.getPlateSize()))%getTrackSize();
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

	public int getNSetores() {
		return virtualMachineParameters.getDiskSize() * virtualMachineParameters.getPlateSize() * getTrackSize();
	}

	public int getTrackSize() {
		return virtualMachineParameters.getTrackSize();
	}

	public int getSectorSize() {
		return virtualMachineParameters.getSectorSize();
	}
	
}
