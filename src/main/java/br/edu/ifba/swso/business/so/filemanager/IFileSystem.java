package br.edu.ifba.swso.business.so.filemanager;

import java.io.Serializable;
import java.util.Collection;

import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;
import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.abstractions.FileInput;

public interface IFileSystem extends Serializable {
	public void allocateFile(FileInput fileinput, IDiskScheduler diskScheduler);
	public void deallocateFile(int index);
	public int seekIdFilePerSector(int nSector);
	public File seekFilePerId(int id);
	public Collection<File> getAllFiles();
	public void simularMovimentacao(String movimento, IDiskScheduler diskScheduler);
}
