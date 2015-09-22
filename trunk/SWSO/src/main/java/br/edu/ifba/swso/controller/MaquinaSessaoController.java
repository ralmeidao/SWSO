package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.business.VirtualMachineParameters;
import br.edu.ifba.swso.business.so.KernelOperatingSystem;
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
	
	//BUSINESS - START
	private CoreVirtualMachine coreVirtualMachine;
	
	private KernelOperatingSystem kernelOperatingSystem;
	
	private VirtualMachineParameters virtualMachineParameters;
	
	private TimelineDisplay timelineDisplay;
	
	//DATA OF VIEW - START
	private int activeAba;
	
	//BUSINESS METHODS
	@PostConstruct
	public void init() {
		virtualMachineParameters = new VirtualMachineParameters();
		timelineDisplay = new TimelineDisplay();
	}
	
	@PreDestroy
	public void destroy() {
		applicationController.getMaquinasAtivas().remove(virtualMachineParameters.getName());
	}
	
	public String initSimulation() {
		if(validarName()) {
			coreVirtualMachine = new CoreVirtualMachine(virtualMachineParameters);
			kernelOperatingSystem = new KernelOperatingSystem(coreVirtualMachine);
			applicationController.getMaquinasAtivas().put(virtualMachineParameters.getName(), this);
			return includeRedirect("/paginas/simulacao/simulacao");
		}
		
		return "";
	}
	
	public String searchSimulation() {
		if(searchMachine()) {
			MaquinaSessaoController maquina = applicationController.getMaquinasAtivas().get(virtualMachineParameters.getName());
			coreVirtualMachine = maquina.getCoreVirtualMachine();
			kernelOperatingSystem = maquina.getOperatingSystem();
			return includeRedirect("/paginas/simulacao/simulacao-view-aluno");
		}
		
		return "";	
	}
	
	public void executarCiclo() {
		kernelOperatingSystem.execute();
		coreVirtualMachine.getCentralProcessingUnit().execute();
		timelineDisplay.incrementList(kernelOperatingSystem.getProcessManager().getEmExecucao());
		
		if (coreVirtualMachine.getCentralProcessingUnit().getRegisters().getProgramCounter().realValue() == (kernelOperatingSystem.getProcessManager().getEmExecucao().getQuantidadeInstrucoes()*2)) {
			kernelOperatingSystem.finalizarProcesso(kernelOperatingSystem.getProcessManager().getEmExecucao());	
		}
	}
	
	
	private boolean validarName() {
		String message = "";
		if(Util.isNullOuVazio(virtualMachineParameters.getName())) {
			message += "É necessário informar o nome da máquina!";
			facesMessager.addMessageError(message);
			return false;
		} else if (applicationController.getMaquinasAtivas().containsKey(virtualMachineParameters.getName())) {
			message += "Já existe uma máquina executando com o nome informado.";
			facesMessager.addMessageError(message);
			return false;
		}
		return true;
	}
	
	private boolean searchMachine() {
		String message = "";
		if(!applicationController.getMaquinasAtivas().containsKey(virtualMachineParameters.getName())) {
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

}