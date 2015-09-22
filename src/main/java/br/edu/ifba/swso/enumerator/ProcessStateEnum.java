package br.edu.ifba.swso.enumerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ProcessStateEnum {
	
	PRONTO("Pronto",1L),
	EXECUTANDO("Executando",2L),
	BLOQUEADO("Bloqueado", 3L),
	FINALIZADO("Finalizado", 3L);
	
	private String descricao;
	private Long valor;
	
	private ProcessStateEnum(String descricao,Long valor){
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
		
		ProcessStateEnum[] values = ProcessStateEnum.values();
		for(int i = 0; i < values.length;i++){
			mapa.put(values[i].getValor(), values[i].getDescricao());
		}
		return mapa;
	}

	/**
	 * Método responsável por retornar todos os tipos de simulacao em forma uma Lista
	 * @return
	 */
	public static List<ProcessStateEnum> getListaValues(){
		List<ProcessStateEnum> lista = new ArrayList<ProcessStateEnum>();
		
		ProcessStateEnum[] values = ProcessStateEnum.values();
		
		for (ProcessStateEnum tipoSimulacaoEnum : values) {
			lista.add(tipoSimulacaoEnum);
		}
		
		return lista;
	}
}
