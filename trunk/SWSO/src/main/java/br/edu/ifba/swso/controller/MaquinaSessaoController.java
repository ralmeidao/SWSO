package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.edu.ifba.swso.algorithms.impl.disk.FCFS;
import br.edu.ifba.swso.algorithms.impl.disk.SSTF;
import br.edu.ifba.swso.algorithms.interfaces.IDiskScheduler;
import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.abstractions.FileInput;
import br.edu.ifba.swso.business.abstractions.Word;
import br.edu.ifba.swso.business.so.OperatingSystem;
import br.edu.ifba.swso.business.so.filemanager.IFileSystem;
import br.edu.ifba.swso.business.virtualmachine.CoreVirtualMachine;
import br.edu.ifba.swso.util.Constantes;
import br.edu.ifba.swso.util.Util;

/**
 * @author Ramon
 */
@Named
@SessionScoped
public class MaquinaSessaoController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	//BUSSINESS - START
	private CoreVirtualMachine coreVirtualMachine;
	
	private OperatingSystem operatingSystem;
	//BUSSINESS - END
	
	//DATE OF VIEW - START
	@Inject
	private ApplicationController applicationController;
	
	private String name;
	
	private UploadedFile uploadFile;
	
	private String color;
	
	private String colorPersonalizar;
	
	private String movimentacaoSimulada;
	
	private int activeAba;
	
	private IDiskScheduler diskSchedule;
	
	private File file;
	
	private IDiskScheduler[] arrayDiskSchedule = {new SSTF(), new FCFS()};
	// DATE OF VIEW - END

	@PreDestroy
	public void destroy() {
		applicationController.getMaquinasAtivas().remove(name);
	}
	
	public String initSimulation() {
		if(validarName()) {
			diskSchedule = new SSTF();
			coreVirtualMachine = new CoreVirtualMachine();
			operatingSystem = new OperatingSystem(coreVirtualMachine);
			operatingSystem.setDiskSchedule(diskSchedule);

			applicationController.getMaquinasAtivas().put(name, this);
			
			return includeRedirect("/paginas/simulacao/simulacao");
		}
		
		return "";
	}
	
	public String searchSimulation() {
		if(searchMachine()) {
			MaquinaSessaoController maquina = applicationController.getMaquinasAtivas().get(name);
			diskSchedule = maquina.getDiskSchedule();
			coreVirtualMachine = maquina.getCoreVirtualMachine();
			operatingSystem = maquina.getOperatingSystem();
			return includeRedirect("/paginas/simulacao/simulacao-view-aluno");
		}
		
		return "";	
	}
	
	public void executarCiclo() {
		operatingSystem.execute();
		coreVirtualMachine.getCentralProcessingUnit().execute();
	}
	
    public void doUploadFile() {
    	if (validarUploadArquivo()) {
    		FileInput fileInput = criarInputFile();
    		operatingSystem.getFileSystem().allocateFile(fileInput, getOperatingSystem().getDiskSchedule());
    		clearUpload();
    		updateComponentes(":formSimulacao");
    	}
    }
    
    public void newProcess() {
    	operatingSystem.criarProcesso(file);
    	file = null;
    	updateComponentes(":formSimulacao");
    }
    
    public void salvarConfiguracoes() {
    	getOperatingSystem().setDiskSchedule(diskSchedule);
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

	public void simularMovimentacao() {
		operatingSystem.getFileSystem().simularMovimentacao(movimentacaoSimulada, getOperatingSystem().getDiskSchedule());
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
	
	private boolean validarName() {
		String message = "";
		if(Util.isNullOuVazio(name)) {
			message += "É necessário informar o nome da máquina!";
			facesMessager.addMessageError(message);
			return false;
		} else if (applicationController.getMaquinasAtivas().containsKey(name)) {
			message += "Já existe uma máquina executando com o nome informado.";
			facesMessager.addMessageError(message);
			return false;
		}
		return true;
	}
	
	private boolean searchMachine() {
		String message = "";
		if(!applicationController.getMaquinasAtivas().containsKey(name)) {
			message += "A máquina informada não existe!";
			facesMessager.addMessageError(message);
			return false;
		} 
		return true;
	}
	
	public void updateComboColor() {
		updateComponentes(":uploadForm:selectColors");
	}

	//MÉT0D0S DE ACESSO
	public CoreVirtualMachine getCoreVirtualMachine() {
		return coreVirtualMachine;
	}
	
	public IFileSystem getSistemaArquivo() {
		return operatingSystem.getFileSystem();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public int getActiveAba() {
		return activeAba;
	}

	public void setActiveAba(int activeAba) {
		this.activeAba = activeAba;
	}
	
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

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
	
}