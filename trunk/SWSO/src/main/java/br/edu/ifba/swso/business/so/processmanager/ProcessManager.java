package br.edu.ifba.swso.business.so.processmanager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifba.swso.algorithms.IProcessesScheduler;
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
		tempoOcisoso.setNome("Tempo Ocioso");;
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
    
    public Process escalonamento(IProcessesScheduler processesScheduler) {
    	if(emExecucao.getPid() != -1 && !emExecucao.isBlocked() && !emExecucao.isEnding()) {
			emExecucao.setState(ProcessStateEnum.PRONTO);
			listaPronto.add(emExecucao);
		}
    	
    	Process proximo = escolherProximo(processesScheduler);

    	if(emExecucao.getPid() != proximo.getPid()) {
    		executaTrocaDeContexto(proximo);
    	}
    	listaPronto.remove(proximo);
    	proximo.setState(ProcessStateEnum.EXECUTANDO);
    	
    	return emExecucao;
    }

    public void bloquearProcesso() {
    	emExecucao.setState(ProcessStateEnum.BLOQUEADO);
		listaBloqueado.add(emExecucao);
    }
    
    public void desbloquearProcesso(int pid) {
    	Process desbloqueado = null;
    	for (Process process : listaBloqueado) {
			if (process.getPid() == pid) {
				desbloqueado = process;
				break;
			}
		}
    	desbloqueado.setState(ProcessStateEnum.PRONTO);
		listaBloqueado.remove(desbloqueado);
		listaPronto.add(desbloqueado);
    }
    
    
    private int getNewIdProcess() {
    	return ultimoId++;
    }

	private void executaTrocaDeContexto(Process proximo) {
		emExecucao.setRi(coreVirtualMachine.getCentralProcessingUnit().getRegisters().getInstructionRegister().realValue());
		emExecucao.setPc(coreVirtualMachine.getCentralProcessingUnit().getRegisters().getProgramCounter().realValue());
		coreVirtualMachine.getCentralProcessingUnit().getRegisters().getInstructionRegister().refreshInstruction(proximo.getRi());
		coreVirtualMachine.getCentralProcessingUnit().getRegisters().getProgramCounter().modifyRealValue(proximo.getPc());
		emExecucao = proximo;
		coreVirtualMachine.getCentralProcessingUnit().getRegisters().setPid(emExecucao.getPid());
	}
	
    private Process escolherProximo(IProcessesScheduler processesScheduler) {
    	if (listaPronto.size() > 0) {
    		return processesScheduler.escalonar(listaPronto);
		} else {
			return tempoOcisoso;
		}
    }
    
	public void incrementarTimeWaitingPronto() {
		for (Process processo : listaPronto) {
			processo.incrementTimeWaiting();
		}
	}
    
	public void finalizarProcesso(Process process) {
		process.setState(ProcessStateEnum.FINALIZADO);
		process.setTimeFinishCpu(coreVirtualMachine.getCentralProcessingUnit().getCpuTime());
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
