package br.edu.ifba.swso.negocio.abstracoes;

import java.util.ArrayList;
import java.util.List;

public class FileInput {
	private String fileName;
	private List<ByteSWSO> listaInstrucoes;

	public FileInput(String fileName) {
		super();
		this.fileName = fileName;
		listaInstrucoes = new ArrayList<ByteSWSO>();
	}
	public String getFileName() {
		return fileName;
	}
	public List<ByteSWSO> getListaInstrucoes() {
		return listaInstrucoes;
	}

	public void writeBytes(ByteSWSO b) {
		listaInstrucoes.add(b);
	}
}
