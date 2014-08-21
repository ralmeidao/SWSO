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

/**
 * @author Ramon
 */
@Named
@SessionScoped
public class MaquinaSessaoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final CoreVirtualMachine coreVirtualMachine;
	
	private ISistemaArquivo sistemaArquivo;
	
	private UploadedFile uploadFile;
	
	private int activeAba;
	
	public MaquinaSessaoController() {
		coreVirtualMachine = new CoreVirtualMachine();
		sistemaArquivo = new XFat(coreVirtualMachine.getHardDisk());
	}
	
	public void executarCiclo() {
		coreVirtualMachine.getCentralProcessingUnit().execute();
	}
	
    public void carregarProcesso() {
    	FileInput fileInput = criarInputFile();
		sistemaArquivo.allocateFile(fileInput);
    }
    
	private FileInput criarInputFile() {
		byte[] arquivo = uploadFile.getContents();
		int lastIndexOf = uploadFile.getFileName().lastIndexOf('.');
		
		FileInput fileInput = new FileInput(uploadFile.getFileName().substring(0, lastIndexOf));
		
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

}