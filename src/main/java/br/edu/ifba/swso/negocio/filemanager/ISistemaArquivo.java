package br.edu.ifba.swso.negocio.filemanager;

import java.io.Serializable;
import java.util.Collection;

import br.edu.ifba.swso.negocio.abstracoes.File;
import br.edu.ifba.swso.negocio.abstracoes.FileInput;

public interface ISistemaArquivo extends Serializable {
	public void allocateFile(FileInput fileinput);
	public void deallocateFile(int index);
	public int seekIdFilePerSector(int nSector);
	public File seekFilePerId(int id);
	public Collection<File> getAllFiles();
}
