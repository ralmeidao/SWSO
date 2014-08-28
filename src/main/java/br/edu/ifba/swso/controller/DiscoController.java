package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.display.MovimentoCabecoteHD;
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
	
	private HardDisk hardDisk;
	private ISistemaArquivo sistemaArquivo;
	
	@PostConstruct
	private void init(){
		hardDisk = maquinaSessaoController.getCoreVirtualMachine().getHardDisk();
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
		String script = "plateDraw(\"canvas"+i+"\", new Array(";
		int j = 0;
		for (Track track : plate.getTracks()) {
			String color = "new Array(";
			for (int k = 0; k < track.getSectors().length; k++) {
				color += "\"#"+obterArquivo((i * Constantes.PLATE_SIZE * Constantes.TRACK_SIZE ) + (j*Constantes.TRACK_SIZE) + k).getColor()+"\"";
				color+= (track.getSectors().length == k+1) ? ")" : ",";
			}
			script+= color + ", ";
			j++;
		}
		script = script.subSequence(0, script.length() - 2) + "))";
		
		return script;
		
	}
	public String drawCanvasMoveReaderHead() {
		String script = "";
		if (hardDisk.getListMoveReaderHead().size() > 0) {
			script = "movesDraw(\"canvasMoveReaderHead\", new Array(";
			
			for (MovimentoCabecoteHD move : hardDisk.getListMoveReaderHead()) {
				script += "new Array( \"" + move.getPosicaoDe() + "\", \"" + move.getPosicaoPara() + "\"), "; 
			}
			script = script.subSequence(0, script.length() - 2) + "))";
		}
		
		return script;
		
	}
	
	public HardDisk getHardDisk() {
		return hardDisk;
	}

	public int getDiskSizeKB(){
		return getDiskSize()/1024;
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
	
	public int getCanvasHeight(){
		return 50 + getHardDisk().getListMoveReaderHead().size() * 20;
	}
}
