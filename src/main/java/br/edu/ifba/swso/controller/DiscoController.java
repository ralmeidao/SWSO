package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.negocio.abstracoes.File;
import br.edu.ifba.swso.negocio.filemanager.ISistemaArquivo;
import br.edu.ifba.swso.negocio.harddisk.HardDisk;
import br.edu.ifba.swso.negocio.harddisk.Plate;
import br.edu.ifba.swso.negocio.harddisk.Track;
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
	
	public File obterArquivo(int nSector){
		int idFile = sistemaArquivo.seekIdFilePerSector(nSector);
		File arquivo = null;
		if(idFile != -1) {
			arquivo = sistemaArquivo.seekFilePerId(idFile);
		}
		
		if (arquivo == null) {
			arquivo = new File("Vazio");
			arquivo.setColor("FFFFFF");
		}
		return arquivo;
				
	}

	public String drawPlates(Plate plate, int i) {
		String script = "plateDraw(\"canvas"+i+"\"";
		int j = 0;
		for (Track track : plate.getTracks()) {
			String color = "new Array(";
			for (int k = 0; k < track.getSectors().length; k++) {
				color += "\"#"+obterArquivo((i*8) + (j*4) + k).getColor()+"\"";
				color+= (track.getSectors().length == k+1) ? ")" : ",";
			}
			script+= ", "+color;
			j++;
		}
		script += ")";
		
		return script;
		
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
	
	public int getQtdSectors(){
		return getDiskSize()/getSectorSize();
	}
}
