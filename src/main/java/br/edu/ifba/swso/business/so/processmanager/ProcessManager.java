package br.edu.ifba.swso.business.so.processmanager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifba.swso.business.abstractions.File;
import br.edu.ifba.swso.business.virtualmachine.CoreVirtualMachine;
import br.edu.ifba.swso.enumerator.ProcessStateEnum;

public class ProcessManager {
    private int ultimoId = 0;
    
    private List<Process> tabelaProcesso = new ArrayList<Process>();
    private LinkedList<Process> listaPronto = new LinkedList<Process>();
    private LinkedList<Process> listaBloqueado = new LinkedList<Process>();
    
    private Process emExecucao;
	private Process tempoOcisoso;
	private CoreVirtualMachine coreVirtualMachine;
	
	public ProcessManager(CoreVirtualMachine coreVirtualMachine) {
		this.coreVirtualMachine = coreVirtualMachine;
		tempoOcisoso = new Process();
		tempoOcisoso.setPid(-1);
		tempoOcisoso.setPc(-1);
		emExecucao = tempoOcisoso;
	}
    
    public Process criarProcesso(File arquivo) {
        Process process = new Process();
        process.setPid(getNewIdProcess());
        process.setTamanhoProcessoByte(arquivo.getFileSize());; 
        process.setTamanhoProcessoBit(arquivo.getFileSize() * 8);  
        process.setQuantidadeInstrucoes(arquivo.getFileSize()/2);
        process.setNome(arquivo.getFileName());
        process.setTimeInitCpu(coreVirtualMachine.getCentralProcessingUnit().getCpuTime());
        process.setFile(arquivo);
        listaPronto.add(process);
        tabelaProcesso.add(process);
        
        return process;
    }
    
    public Process escalonamento() {
    	Process proximo = escolherProximo(listaPronto);

    	if(emExecucao.getPid() != proximo.getPid()) {
    		//EXECUTA TROCA DE CONTEXTO
    		emExecucao.setRi(coreVirtualMachine.getCentralProcessingUnit().getRegisters().getInstructionRegister().realValue());
    		emExecucao.setPc(coreVirtualMachine.getCentralProcessingUnit().getRegisters().getProgramCounter().realValue());
    		
    		if(emExecucao.getPid() != -1) {
    			listaPronto.add(emExecucao);
    			emExecucao.setState(ProcessStateEnum.PRONTO);
    		}
    		
    		listaPronto.remove(proximo);
    		
    		coreVirtualMachine.getCentralProcessingUnit().getRegisters().getInstructionRegister().refreshInstruction(proximo.getRi());
    		coreVirtualMachine.getCentralProcessingUnit().getRegisters().getProgramCounter().modifyRealValue(proximo.getPc());
    		
    		emExecucao = proximo;
    		emExecucao.setState(ProcessStateEnum.EXECUTANDO);
    		
    		coreVirtualMachine.getCentralProcessingUnit().getRegisters().setPid(emExecucao.getPid());
    	}
    	
    	return emExecucao;
    }

    private int getNewIdProcess() {
    	return ultimoId++;
    }

    public Process escolherProximo(LinkedList<Process> listaPronto) {
    	if (listaPronto.size() > 0) {
    		return listaPronto.getFirst();
		} else {
			return tempoOcisoso;
		}
    }
    
    //MÉT0D0S DE ACESSO
	public List<Process> getTabelaProcesso() {
		return tabelaProcesso;
	}

	public LinkedList<Process> getListaPronto() {
		return listaPronto;
	}

	public LinkedList<Process> getListaBloqueado() {
		return listaBloqueado;
	}

	public Process getEmExecucao() {
		return emExecucao;
	}
	
}
