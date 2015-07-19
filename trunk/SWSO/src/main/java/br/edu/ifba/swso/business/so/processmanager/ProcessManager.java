package br.edu.ifba.swso.business.so.processmanager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.virtualmachine.CoreVirtualMachine;

public class ProcessManager {
    private int ultimoId = 0;
    
    private List<Processo> tabelaProcesso = new ArrayList<Processo>();
    private LinkedList<Processo> listaPronto = new LinkedList<Processo>();
    private LinkedList<Processo> listaBloqueado = new LinkedList<Processo>();
    
    private Processo emExecucao;
	private Processo tempoOcisoso;
	private CoreVirtualMachine coreVirtualMachine;
	
	public ProcessManager(CoreVirtualMachine coreVirtualMachine) {
		this.coreVirtualMachine = coreVirtualMachine;
		tempoOcisoso = new Processo();
		tempoOcisoso.setPid(-1);
		tempoOcisoso.setPc(-1);
		emExecucao = tempoOcisoso;
	}
    
    public Processo criarProcesso(File arquivo) {
        Processo processo = new Processo();
        processo.setPid(getNewIdProcess());
        processo.setTamanhoProcessoByte(arquivo.getFileSize());; 
        processo.setTamanhoProcessoBit(arquivo.getFileSize() * 8);  
        processo.setQuantidadeInstrucoes(arquivo.getFileSize()/2);
        listaPronto.add(processo);
        tabelaProcesso.add(processo);
        
        return processo;
    }
    
    public Processo escalonamento() {
    	Processo proximo = escolherProximo(listaPronto);

    	if(emExecucao.getPid() != proximo.getPid()) {
    		//EXECUTA TROCA DE CONTEXTO
    		emExecucao.setRi(coreVirtualMachine.getCentralProcessingUnit().getRegisters().getInstructionRegister().realValue());
    		emExecucao.setPc(coreVirtualMachine.getCentralProcessingUnit().getRegisters().getProgramCounter().realValue());
    		
    		if(emExecucao.getPid() != -1) {
    			listaPronto.add(emExecucao);
    		}
    		
    		listaPronto.remove(proximo);
    		
    		coreVirtualMachine.getCentralProcessingUnit().getRegisters().getInstructionRegister().refreshInstruction(proximo.getRi());
    		coreVirtualMachine.getCentralProcessingUnit().getRegisters().getProgramCounter().modifyRealValue(proximo.getPc());
    		
    		emExecucao = proximo;
    	}
    	
    	return emExecucao;
    }

    private int getNewIdProcess() {
    	return ultimoId++;
    }

    public Processo escolherProximo(LinkedList<Processo> listaPronto) {
    	if (listaPronto.size() > 0) {
    		return listaPronto.getFirst();
		} else {
			return tempoOcisoso;
		}
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

	public Processo getEmExecucao() {
		return emExecucao;
	}
	
}
