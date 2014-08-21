package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.negocio.filemanager.ISistemaArquivo;
import br.edu.ifba.swso.negocio.harddisk.HardDisk;
import br.edu.ifba.swso.util.Constantes;

/**
 * @author Ramon
 */
@Named
@ViewScoped
public class DiscoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MaquinaSessaoController maquinaSessaoController;
	
	private HardDisk hardDisck;
	private ISistemaArquivo sistemaArquivo;
	
	@PostConstruct
	private void init(){
		hardDisck = maquinaSessaoController.getCoreVirtualMachine().getHardDisk();
		sistemaArquivo = maquinaSessaoController.getSistemaArquivo();
	}
	
	public String obterNomeArquivo(int nSector){
		int idFile = sistemaArquivo.seekIdFilePerSector(nSector);
		String nomeArquivo = null;
		if(idFile != -1) {
			nomeArquivo = sistemaArquivo.seekFilePerId(idFile).getFileName();
		}
		if (nomeArquivo != null) {
			return nomeArquivo;
		} else {
			return "Vazio";
		}
	}

	public HardDisk getHardDisck() {
		return hardDisck;
	}

	public int getDiskSize(){
		return Constantes.DISK_SIZE * Constantes.PLATE_SIZE * Constantes.TRACK_SIZE * Constantes.SECTOR_SIZE;
	}
	public int getSectorSize(){
		return Constantes.SECTOR_SIZE;
	}
}
