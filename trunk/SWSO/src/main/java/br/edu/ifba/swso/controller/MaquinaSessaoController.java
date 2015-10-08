package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.business.VirtualMachineParameters;
import br.edu.ifba.swso.business.so.KernelOperatingSystem;
import br.edu.ifba.swso.business.so.memorymanager.exception.PageFault;
import br.edu.ifba.swso.business.virtualmachine.CoreVirtualMachine;
import br.edu.ifba.swso.display.TimelineDisplay;
import br.edu.ifba.swso.util.Util;

/**
 * @author Ramon
 */
@Named
@SessionScoped
public class MaquinaSessaoController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ApplicationController applicationController;
	
	private Integer qtdCiclos;
	
	//BUSINESS - START
	private CoreVirtualMachine coreVirtualMachine;
	
	private KernelOperatingSystem kernelOperatingSystem;
	
	private VirtualMachineParameters virtualMachineParameters;
	//DATA OF VIEW - START
	
	private TimelineDisplay timelineDisplay;
	private int activeAba;
	
	//BUSINESS METHODS
	@PostConstruct
	public void init() {
		activeAba = 0;
		qtdCiclos = 1;
		coreVirtualMachine = null;
		kernelOperatingSystem = null;
		virtualMachineParameters = new VirtualMachineParameters();
		timelineDisplay = new TimelineDisplay();
	}
	
	@PreDestroy
	public void destroy() {
		applicationController.remove(this);
	}
	
	public String initSimulation() {
		if(validarName()) {
			coreVirtualMachine = new CoreVirtualMachine(virtualMachineParameters);
			kernelOperatingSystem = new KernelOperatingSystem(coreVirtualMachine);
			applicationController.put(this);
			return includeRedirect("/paginas/simulacao/simulacao");
		}
		return "";
	}
	
	public void restart() {
		coreVirtualMachine = new CoreVirtualMachine(virtualMachineParameters);
		kernelOperatingSystem = new KernelOperatingSystem(coreVirtualMachine);
		timelineDisplay = new TimelineDisplay();
		applicationController.restart(virtualMachineParameters.getName());
	}
	
	public String searchSimulation() {
		if(searchMachine()) {
			MaquinaSessaoController maquina = applicationController.get(virtualMachineParameters.getName());
			coreVirtualMachine = maquina.getCoreVirtualMachine();
			kernelOperatingSystem = maquina.getOperatingSystem();
			return includeRedirect("/paginas/simulacao/simulacao-view-aluno");
		}
		
		return "";	
	}
	
	public void executarCiclo() throws Exception {
		int loop = qtdCiclos != null ? qtdCiclos : 1;
		try {
			for (int contadorDeLoop = 0; contadorDeLoop < loop; contadorDeLoop++) {
				kernelOperatingSystem.execute();
				coreVirtualMachine.getCentralProcessingUnit().execute();
				timelineDisplay.incrementList(kernelOperatingSystem.getProcessManager().getEmExecucao());
				
				if (coreVirtualMachine.getCentralProcessingUnit().getRegisters().getProgramCounter().realValue() == (kernelOperatingSystem.getProcessManager().getEmExecucao().getQuantidadeInstrucoes()*2)) {
					kernelOperatingSystem.finalizarProcesso(kernelOperatingSystem.getProcessManager().getEmExecucao());	
				}
			}
		} catch (PageFault pageFault) {
			kernelOperatingSystem.tratarPageFault(kernelOperatingSystem.getProcessManager().getEmExecucao().getPid(), pageFault.getPage());
		}
	}

	private boolean validarName() {
		String message = "";
		if(Util.isNullOuVazio(virtualMachineParameters.getName())) {
			message += "É necessário informar o nome da máquina!";
			facesMessager.addMessageError(message);
			return false;
		} else if (applicationController.contemMaquina(virtualMachineParameters.getName())) {
			message += "Já existe uma máquina executando com o nome informado.";
			facesMessager.addMessageError(message);
			return false;
		}
		return true;
	}
	
	private boolean searchMachine() {
		String message = "";
		if(!applicationController.contemMaquina(virtualMachineParameters.getName())) {
			message += "A máquina informada não existe!";
			facesMessager.addMessageError(message);
			return false;
		} 
		return true;
	}
	
	//MÉT0D0S DE ACESSO
	public CoreVirtualMachine getCoreVirtualMachine() {
		return coreVirtualMachine;
	}
	
	public VirtualMachineParameters getVirtualMachineParameters() {
		return virtualMachineParameters;
	}

	public void setVirtualMachineParameters(VirtualMachineParameters virtualMachineParameters) {
		this.virtualMachineParameters = virtualMachineParameters;
	}

	public String getName() {
		return virtualMachineParameters.getName();
	}

	public void setName(String name) {
		this.virtualMachineParameters.setName(name);
	}

	public int getActiveAba() {
		return activeAba;
	}

	public void setActiveAba(int activeAba) {
		this.activeAba = activeAba;
	}

	public KernelOperatingSystem getOperatingSystem() {
		return kernelOperatingSystem;
	}

	public TimelineDisplay getTimelineDisplay() {
		return timelineDisplay;
	}

	public Integer getQtdCiclos() {
		return qtdCiclos;
	}

	public void setQtdCiclos(Integer qtdCiclos) {
		this.qtdCiclos = qtdCiclos;
	}
	
}