package br.edu.ifba.swso.business;

public class VirtualMachineParameters {
	
	private String name;
	
	//PARAMETROS INICIAIS
	public int sectorSize; // tamanho do setor.
	public int diskSize; // tamanho do disco.
	public int plateSize; // tamanho do prato. 
	public int trackSize;// tamanho da trilha.
	public int memorySize; // quantidade total da memória.
	public int bytePerPage; // quantidade de bytes por páginas
	
	//GERENCIA DO PROCESSADOR	
	public static int NUM_QUATUM = 64; // quatum é medido por palavras (neste caso).
	
	//GERENCIA DE PROCESSOS
	public static int PRIORITY_INITIAL = 5;
	public static int TIME_SLICE = 5;
	
	//GERENCIA DE MEMORIA
	//public static int VIRTUAL_MEMORY_SIZE = MEMORY_SIZE*2; // quantidade total da memória virtual.
	public static int RAM_TAXA_DE_OCUPACAO_MAX = 80; //De 0 à 100%
	
	public VirtualMachineParameters() {
		this.bytePerPage = 512;
		this.sectorSize = 256; 
		this.memorySize = 32;
		
		this.diskSize = 9;
		this.plateSize = 10; 
		this.trackSize = 8;
	}
	
	//ACCESS METHODS
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSectorSize() {
		return sectorSize;
	}

	public void setSectorSize(int sectorSize) {
		this.sectorSize = sectorSize;
	}

	public int getDiskSize() {
		return diskSize;
	}

	public void setDiskSize(int diskSize) {
		this.diskSize = diskSize;
	}

	public int getPlateSize() {
		return plateSize;
	}

	public void setPlateSize(int plateSize) {
		this.plateSize = plateSize;
	}

	public int getTrackSize() {
		return trackSize;
	}

	public void setTrackSize(int trackSize) {
		this.trackSize = trackSize;
	}

	public int getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}

	public int getBytePerPage() {
		return bytePerPage;
	}

	public void setBytePerPage(int bytePerPage) {
		this.bytePerPage = bytePerPage;
	}
	
}
