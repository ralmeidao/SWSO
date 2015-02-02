package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.filemanager.IFileSystem;
import br.edu.ifba.swso.business.virtualmachine.harddisk.HardDisk;
import br.edu.ifba.swso.business.virtualmachine.harddisk.Plate;
import br.edu.ifba.swso.business.virtualmachine.harddisk.Track;
import br.edu.ifba.swso.display.MovimentoCabecoteHD;
import br.edu.ifba.swso.util.Constantes;

/**
 * @author Ramon
 */
@Named
@RequestScoped
public class DiscoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MaquinaSessaoController maquinaSessaoController;
	
	/**
	 * Utilizado para obter o arquivo por meio do número inicial do setor
	 * 
	 * @param nSector
	 * @return File
	 */
	private File obterArquivo(int nSector){
		int idFile = getMaquinaSessaoController().seekIdFilePerSector(nSector);
		File arquivo = null;
		if(idFile != -1) {
			arquivo = getMaquinaSessaoController().seekFilePerId(idFile);
		}
		
		if (arquivo == null) {
			arquivo = new File("Vazio");
			arquivo.setColor("FFFFFF");
		}
		return arquivo;
	}

	/**
	 * Usado pela camada web para desenhar os pratos
	 * 
	 * @param plate
	 * @param i
	 * @return String
	 */
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
	
	/**
	 * Utilizado para desenhar o movimento do cabeçote
	 * 
	 * @return String
	 */
	public String drawCanvasMoveReaderHead() {
		String script = "";
		if (getHardDisk().getListMoveReaderHead().size() > 0) {
			script = "movesDraw(\"canvasMoveReaderHead\", new Array(";
			
			for (MovimentoCabecoteHD move : getHardDisk().getListMoveReaderHead()) {
				script += "new Array( \"" + move.getPosicaoDe() + "\", \"" + move.getPosicaoPara() + "\"), "; 
			}
			script = script.subSequence(0, script.length() - 2) + "))";
		}
		
		return script;
		
	}
	
	/**
	 * Método utilizado para limpar a lista de movimentos
	 */
	public void limparListMoveReaderHead() {
		getHardDisk().getListMoveReaderHead().clear();
	}
	
	/**
	 * Obtém ao HD
	 * 
	 * @return HardDisk
	 */
	public HardDisk getHardDisk() {
		return maquinaSessaoController.getCoreVirtualMachine().getHardDisk();
	}

	/**
	 * Obtém SistemaArquivo
	 * 
	 * @return ISistemaArquivo
	 */
	public IFileSystem getMaquinaSessaoController() {
		return maquinaSessaoController.getSistemaArquivo();
	}
		
	/**
	 * Obtém o tamanho do disco em KB
	 * 
	 * @return
	 */
	public int getDiskSizeKB(){
		return getDiskSize()/1024;
	}
	
	/**
	 * Obtém o tamanho do disco em bytes
	 * 
	 * @return
	 */
	public int getDiskSize(){
		return Constantes.DISK_SIZE * Constantes.PLATE_SIZE * Constantes.TRACK_SIZE * Constantes.SECTOR_SIZE;
	}
	
	
	/**
	 * Obtém o tamanho do setor em bytes
	 * 
	 * @return
	 */
	public int getSectorSize(){
		return Constantes.SECTOR_SIZE;
	}
	
	/**
	 * Obtém a quantidade de setores do disco
	 * 
	 * @return
	 */
	public int getQtdSectors(){
		return getDiskSize()/getSectorSize();
	}
	
	/**
	 * Obtém a altura do canvas baseado na quantidade de movimentos
	 * 
	 * @return
	 */
	public int getCanvasHeight(){
		return 50 + getHardDisk().getListMoveReaderHead().size() * 20;
	}
	
}
