package br.edu.ifba.swso.enumerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PoliticaBuscaEnum {
	
	ANTECIPADA("Antecipada", 0),
	POR_DEMANDA("Por demanda", 1);
	
	private String descricao;
	private Integer valor;
	
	private PoliticaBuscaEnum(String descricao,Integer valor){
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
	/**
	 * Método responsável por retornar todos os tipos de simulacao em forma de mapa(Valor,Descricao)
	 * @return
	 */
	public static Map<Integer,String> getValues(){
		Map<Integer,String> mapa = new HashMap<Integer, String>();
		
		PoliticaBuscaEnum[] values = PoliticaBuscaEnum.values();
		for(int i = 0; i < values.length;i++){
			mapa.put(values[i].getValor(), values[i].getDescricao());
		}
		return mapa;
	}

	/**
	 * Método responsável por retornar todos os tipos de simulacao em forma uma Lista
	 * @return
	 */
	public static List<PoliticaBuscaEnum> getListaValues(){
		List<PoliticaBuscaEnum> lista = new ArrayList<PoliticaBuscaEnum>();
		
		PoliticaBuscaEnum[] values = PoliticaBuscaEnum.values();
		
		for (PoliticaBuscaEnum tipoSimulacaoEnum : values) {
			lista.add(tipoSimulacaoEnum);
		}
		
		return lista;
	}
}
