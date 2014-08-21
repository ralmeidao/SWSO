package br.edu.ifba.swso.negocio.abstracoes;

import java.util.ArrayList;
import java.util.List;


public class File {
    private String fileName;
    private int iniSector;
    private int fileSize;
    private int fileID;
    private List<Integer> allocatedSectors;
	
    public File(String fileName) {
		this.fileName = fileName;
		allocatedSectors = new ArrayList<Integer>();
	}
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getIniSector() {
        return iniSector;
    }

    public void setIniSector(int iniSector) {
        this.iniSector = iniSector;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    
    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }
    
    public List<Integer> getAllocatedSectors() {
        return this.allocatedSectors;
    }
}
