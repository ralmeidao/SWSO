package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.edu.ifba.swso.negocio.abstracoes.FileInput;
import br.edu.ifba.swso.negocio.abstracoes.Word;
import br.edu.ifba.swso.negocio.filemanager.ISistemaArquivo;
import br.edu.ifba.swso.negocio.filemanager.XFat;
import br.edu.ifba.swso.negocio.virtualmachine.CoreVirtualMachine;
import br.edu.ifba.swso.util.Constantes;
import br.edu.ifba.swso.util.Util;

/**
 * @author Ramon
 */
@Named
@SessionScoped
public class MaquinaSessaoController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final CoreVirtualMachine coreVirtualMachine;
	
	private ISistemaArquivo sistemaArquivo;
	
	private UploadedFile uploadFile;
	
	private String color;
	
	private String colorPersonalizar;
	
	private int activeAba;
	
	public MaquinaSessaoController() {
		coreVirtualMachine = new CoreVirtualMachine();
		sistemaArquivo = new XFat(coreVirtualMachine.getHardDisk());
	}
	
	public void executarCiclo() {
		coreVirtualMachine.getCentralProcessingUnit().execute();
	}
	
    public void doUploadFile() {
    	if (validarUploadArquivo()) {
    		FileInput fileInput = criarInputFile();
    		sistemaArquivo.allocateFile(fileInput);
    		clearUpload();
    	}
    }
    
    public void deleteFile(Integer id) {
    	sistemaArquivo.deallocateFile(id);
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
			message += "É necessário selecionar um arquivo! "; 
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
	
	
	public void updateComboColor() {
		updateComponentes(":uploadForm:selectColors");
	}

	//MÉT0D0S DE ACESSO
	public CoreVirtualMachine getCoreVirtualMachine() {
		return coreVirtualMachine;
	}
	
	public ISistemaArquivo getSistemaArquivo() {
		return sistemaArquivo;
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
}