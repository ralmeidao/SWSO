package br.edu.ifba.swso.business.processmanager;

import java.util.LinkedList;
import java.util.List;

import br.edu.ifba.swso.business.abstractions.File;

public class ProcessManager {
    private int ultimoId = 0;
    private List<Processo> tabelaProcesso;
    
    private LinkedList<Processo> listaPronto;
    private LinkedList<Processo> listaBloqueado;
    
    public Processo criarProcesso(File arquivo) {
        Processo processo = new Processo();
        processo.setPid(getNewIdProcess());
                          
        //TODO
        processo.setTamanhoProcessoByte(0);; 
        processo.setTamanhoProcessoBit(0);  
        processo.setQuantidadeInstrucoes(0);
        
        //TODO
        //AQUI DEVE TRATAR AS OPÇÕES DE MEMORIA
        
        
        return processo;
    }

    private int getNewIdProcess() {
    	return ultimoId++;
    }


    //MÉT0D0S DE ACESSO
	public List<Processo> getTabelaProcesso() {
		return tabelaProcesso;
	}

	public LinkedList<Processo> getListaPronto() {
		return listaPronto;
	}

	public LinkedList<Processo> getListaBloqueado() {
		return listaBloqueado;
	}
}
