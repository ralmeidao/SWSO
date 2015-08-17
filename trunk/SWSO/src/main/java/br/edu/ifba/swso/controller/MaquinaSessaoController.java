package br.edu.ifba.swso.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifba.swso.business.VirtualMachineParameters;
import br.edu.ifba.swso.business.so.OperatingSystem;
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
	
	private OperatingSystem operatingSystem;
	
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
			operatingSystem = new OperatingSystem(coreVirtualMachine);
			applicationController.getMaquinasAtivas().put(virtualMachineParameters.getName(), this);
			return includeRedirect("/paginas/simulacao/simulacao");
		}
		
		return "";
	}
	
	public String searchSimulation() {
		if(searchMachine()) {
			MaquinaSessaoController maquina = applicationController.getMaquinasAtivas().get(virtualMachineParameters.getName());
			coreVirtualMachine = maquina.getCoreVirtualMachine();
			operatingSystem = maquina.getOperatingSystem();
			return includeRedirect("/paginas/simulacao/simulacao-view-aluno");
		}
		
		return "";	
	}
	
	public void executarCiclo() {
		operatingSystem.execute();
		coreVirtualMachine.getCentralProcessingUnit().execute();
		timelineDisplay.incrementList(operatingSystem.getProcessManager().getEmExecucao());
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

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public TimelineDisplay getTimelineDisplay() {
		return timelineDisplay;
	}

}