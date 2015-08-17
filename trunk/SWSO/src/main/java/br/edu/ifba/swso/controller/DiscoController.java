package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.edu.ifba.swso.algorithms.impl.disk.FCFS;
import br.edu.ifba.swso.algorithms.impl.disk.SSTF;
import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;
import br.edu.ifba.swso.business.VirtualMachineParameters;
import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.abstractions.FileInput;
import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.business.so.OperatingSystem;
import br.edu.ifba.swso.business.so.filemanager.IFileSystem;
import br.edu.ifba.swso.business.virtualmachine.harddisk.HardDisk;
import br.edu.ifba.swso.business.virtualmachine.harddisk.Plate;
import br.edu.ifba.swso.business.virtualmachine.harddisk.Track;
import br.edu.ifba.swso.display.MovimentoCabecoteHD;
import br.edu.ifba.swso.util.Constantes;
import br.edu.ifba.swso.util.Util;

/**
 * @author Ramon
 */
@Named
@SessionScoped
public class DiscoController extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MaquinaSessaoController maquinaSessaoController;
	
	//BUSINESS DATA - START
	private IDiskScheduler[] arrayDiskSchedule;
	
	private IDiskScheduler diskSchedule;
	
	private OperatingSystem operatingSystem;
	
	//VIEW DATA - START
	private UploadedFile uploadFile;
	
	private String color;
	
	private String colorPersonalizar;
	
	private String movimentacaoSimulada;
	
	
	@PostConstruct
	public void init() {
		VirtualMachineParameters virtualMachineParameters = maquinaSessaoController.getVirtualMachineParameters();
		operatingSystem = maquinaSessaoController.getOperatingSystem();
		arrayDiskSchedule = new IDiskScheduler[]{new SSTF(virtualMachineParameters), new FCFS(virtualMachineParameters)};
		diskSchedule = arrayDiskSchedule[0];
		operatingSystem.setDiskSchedule(diskSchedule);
		arrayDiskSchedule = new IDiskScheduler[]{new SSTF(virtualMachineParameters), new FCFS(virtualMachineParameters)};
	}
	
    public void salvarConfiguracoesDisco() {
    	operatingSystem.setDiskSchedule(diskSchedule);
    }
    
    public void doUploadFile() {
    	if (validarUploadArquivo()) {
    		FileInput fileInput = criarInputFile();
    		operatingSystem.getFileSystem().allocateFile(fileInput, operatingSystem.getDiskSchedule());
    		clearUpload();
    		updateComponentes(":formSimulacao");
    	}
    }
    
    public void updateComboColor() {
		updateComponentes(":uploadForm:selectColors");
	}
    
    public void simularMovimentacao() {
    	operatingSystem.getFileSystem().simularMovimentacao(movimentacaoSimulada, operatingSystem.getDiskSchedule());
    }
    
    public void deleteFile(Integer id) {
    	operatingSystem.getFileSystem().deallocateFile(id);
    }
    
	private FileInput criarInputFile() {
		byte[] arquivo = uploadFile.getContents();
		int lastIndexOf = uploadFile.getFileName().lastIndexOf('.');
		
		String colorReal = "XXXXXX".equals(color) ? colorPersonalizar : color;
		
		FileInput fileInput = new FileInput(uploadFile.getFileName().substring(0, lastIndexOf), colorReal);
		
		String arqString = new String(arquivo);
		arqString = arqString.replace("\r\n", "");
		arqString = arqString.replace(" ", "");
		for(String instrucao : arqString.split(";")){
			Word word = new Word(instrucao);
			for (int i = 0; i < Constantes.WORD_SIZE; i++) {
				fileInput.writeBytes(word.getIoWord()[i]);
			}
		}
		return fileInput;
	}
	
	private void clearUpload() {
		uploadFile = null;
		color = null;
	}
	
	private boolean validarUploadArquivo(){
		String message = "";
		if(uploadFile == null || Util.isNullOuVazio(uploadFile.getFileName())) {
			message += "É necessário selecionar um arquivo! <br/>";
		} 
		if (color == null || color.equals("")
				|| (color.equals("XXXXXX") && (colorPersonalizar == null || colorPersonalizar.equals("")))) {
			message += "É necessário selecionar uma cor!";
		}
		if (!Util.isNullOuVazio(message)) {
			facesMessager.addMessageError(message);
		}
		return Util.isNullOuVazio(message);
	}
    
	//ACCESS METHODS
	public IDiskScheduler getDiskSchedule() {
		return diskSchedule;
	}

	public void setDiskSchedule(IDiskScheduler diskSchedule) {
		this.diskSchedule = diskSchedule;
	}

	public IDiskScheduler[] getArrayDiskSchedule() {
		return arrayDiskSchedule;
	}

	public void setArrayDiskSchedule(IDiskScheduler[] arrayDiskSchedule) {
		this.arrayDiskSchedule = arrayDiskSchedule;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String cor) {
		this.color = cor;
	}

	public String getColorPersonalizar() {
		return colorPersonalizar;
	}

	public void setColorPersonalizar(String colorPersonalizar) {
		this.colorPersonalizar = colorPersonalizar;
	}

	public String getMovimentacaoSimulada() {
		return movimentacaoSimulada;
	}

	public void setMovimentacaoSimulada(String movimentacaoSimulada) {
		this.movimentacaoSimulada = movimentacaoSimulada;
	}

    //PARTE GRAFICA
	/**
	 * Utilizado para obter o arquivo por meio do número inicial do setor
	 * 
	 * @param nSector
	 * @return File
	 */
	private File obterArquivo(int nSector){
		int idFile = getSistemaArquivo().seekIdFilePerSector(nSector);
		File arquivo = null;
		if(idFile != -1) {
			arquivo = getSistemaArquivo().seekFilePerId(idFile);
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
				color += "\"#"+obterArquivo((i * getVirtualMachineParameters().getPlateSize() * getVirtualMachineParameters().getTrackSize()) + (j*getVirtualMachineParameters().getTrackSize()) + k).getColor()+"\"";
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
	public IFileSystem getSistemaArquivo() {
		return operatingSystem.getFileSystem();
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
		return getVirtualMachineParameters().getDiskSize() * getVirtualMachineParameters().getPlateSize()* getVirtualMachineParameters().getTrackSize() * getSectorSize();
	}
	
	
	/**
	 * Obtém o tamanho do setor em bytes
	 * 
	 * @return
	 */
	public int getSectorSize(){
		return getVirtualMachineParameters().getSectorSize();
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
	
	private VirtualMachineParameters getVirtualMachineParameters() {
		return maquinaSessaoController.getVirtualMachineParameters();
	}
	
}
