package br.edu.ifba.swso.enumerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TipoSimulacaoEnum {
	
	PROCESSOS("Processos",1L),
	MEMORIA("Memória", 2L),
	DISCO("Disco",3L);
	
	private String descricao;
	private Long valor;
	
	private TipoSimulacaoEnum(String descricao,Long valor){
		this.descricao = descricao;
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
	
	/**
	 * Método responsável por retornar todos os tipos de simulacao em forma de mapa(Valor,Descricao)
	 * @return
	 */
	public static Map<Long,String> getValues(){
		Map<Long,String> mapa = new HashMap<Long, String>();
		
		TipoSimulacaoEnum[] values = TipoSimulacaoEnum.values();
		for(int i = 0; i < values.length;i++){
			mapa.put(values[i].getValor(), values[i].getDescricao());
		}
		return mapa;
	}

	/**
	 * Método responsável por retornar todos os tipos de simulacao em forma uma Lista
	 * @return
	 */
	public static List<TipoSimulacaoEnum> getListaValues(){
		List<TipoSimulacaoEnum> lista = new ArrayList<TipoSimulacaoEnum>();
		
		TipoSimulacaoEnum[] values = TipoSimulacaoEnum.values();
		
		for (TipoSimulacaoEnum tipoSimulacaoEnum : values) {
			lista.add(tipoSimulacaoEnum);
		}
		
		return lista;
	}
}
