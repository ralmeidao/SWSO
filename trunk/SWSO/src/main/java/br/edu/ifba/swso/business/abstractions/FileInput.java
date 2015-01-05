package br.edu.ifba.swso.business.abstractions;

import java.util.ArrayList;
import java.util.List;

public class FileInput {
	private String fileName;
	private List<ByteSWSO> listaInstrucoes;
	
	private String color;

	public FileInput(String fileName, String color) {
		super();
		this.fileName = fileName;
		this.color = color;
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
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
