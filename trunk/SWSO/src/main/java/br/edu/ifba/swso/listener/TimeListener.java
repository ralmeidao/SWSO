package br.edu.ifba.swso.listener;

import java.util.Calendar;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.edu.ifba.swso.arquitetura.util.Util;
import br.edu.ifba.swso.security.JsfUtil;

/**
 * Classe que atualiza a hora do servidor
 * @author jsouzaa
 *
 */
public class TimeListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	/**
     * Metodo que verifica se o usuario logado e o mesmo do login
     * @param arg0
     */
	@Override
	public void afterPhase(PhaseEvent arg0) {
		JsfUtil.setSessionMapValue("dataDiaExtenso", getDiaDaSemana());
	}
	
	/**
	 * Método obtém a data corrente com horas e minutos
	 */
	private String getDiaDaSemana() {
		return Util.formatData(Calendar.getInstance().getTime(), "EEEE, dd/MM/yyyy HH:mm");
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
