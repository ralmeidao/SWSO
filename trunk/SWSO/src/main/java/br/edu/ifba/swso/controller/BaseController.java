package br.edu.ifba.swso.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import br.edu.ifba.swso.util.FacesMessager;
import br.edu.ifba.swso.util.JSFUtil;
import br.edu.ifba.swso.util.MensagemUtil;
import br.edu.ifba.swso.util.ReportUtils;

/**
 * Classe base para qualquer outro controller
 */
public abstract class BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	protected FacesMessager facesMessager;

	@Inject
	protected transient Logger logger;
	
	protected String facesRedirect = "?faces-redirect=true";
	
	/**
	 * Construtor default
	 */
	public BaseController() {
		super();
	}
	
	/**
	 * Método para ordenar string ignorando utilizado no sortFuction 
	 * 
	 * @param msg1 
	 * @param msg2
	 * @return
	 */
	public int sortByString(Object msg1,Object msg2) {
		return ((String)msg1).compareToIgnoreCase(((String)msg2));
	}
	
	/**
	 * Esconde o componente do id especificado
	 * @param widgetvar
	 */
	protected void hideDialog(String idComponente) {
		RequestContext.getCurrentInstance().execute(idComponente + ".hide()");
	}
	
	/**
	 * Mostra o componente do id especificado
	 * @param widgetvar
	 */
	protected void showDialog(String idComponente) {
		RequestContext.getCurrentInstance().execute(idComponente + ".show()");
	}
	
	/**
	 * Faz update nos componentes dos respectivos ids passados como parâmetro
	 * @param idComponente
	 */
	protected void updateComponentes(String... idComponente){
		for(String id : idComponente){
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(id);
		}
	}

	/**
	 * Método usado para guardar o id do componente que chamou o evento.
	 * Esse método será usado normalmente em botões que chamam dialogs
	 */
	public void guardarIdComponente(){
		FacesContext context = FacesContext.getCurrentInstance();
	    Map<String, String> map = context.getExternalContext().getRequestParameterMap();
	    JSFUtil.setSessionMapValue("idComponente", (String) map.get("idComponente"));
	}
	
	/**
	 * Este método trabalha em conjunto com o método guardarIdComponente. Também será usado normalmente junto com os dialogs.
	 * Quando o dialog for fechado este método dará foco no botão que chamou o dialog.
	 * @throws InterruptedException 
	 */
	public void giveFocus() throws InterruptedException {
		Thread.sleep(250);
		String idComponente = (String) JSFUtil.getSessionMapValue("idComponente");
		if (idComponente != null) {
			RequestContext.getCurrentInstance().execute("giveFocus('"+idComponente+"')");
		}
	}
	
	protected String includeRedirect(String url) {
		String formatedUrl = url.concat(facesRedirect);
		return formatedUrl;
	}

	protected String getRootErrorMessage(Exception e) {
		String errorMessage = MensagemUtil.obterMensagem("geral.crud.rootErrorMessage");
		if (e == null) {
			// This shouldn't happen, but return the default messages
			return errorMessage;
		}

		facesMessager.error(errorMessage);

		// Start with the exception and recurse to find the root cause
		Throwable t = e;
		while (t != null) {
			// Get the message from the Throwable class instance
			errorMessage = t.getLocalizedMessage();
			t = t.getCause();
		}
		// This is the root cause message
		return errorMessage;
	}

	protected String createViolationResponse(
			Set<ConstraintViolation<?>> violations) {
		logger.debug("Validation completed. violations found: "
				+ violations.size());

		Map<String, String> responseObj = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(),
					violation.getMessage());
		}

		return responseObj.toString();
	}

	protected void logErroMessage() {
		logger.fatal("--------------------------------------------");
		logger.fatal("Ocorreu um erro ao Persistir o registro, verifique o log do servidor.");
		logger.fatal("--------------------------------------------");
	}

	// =======================================================================================================
	// Métodos Relatório
	// =======================================================================================================
	protected void escreveRelatorio(byte[] relatorio, String nome, boolean download) {
		try {
			this.escreveRelatorioResponse(relatorio, nome, ReportUtils.REL_TIPO_PDF, "application/pdf", download);
		} catch (Exception e) {
			getRootErrorMessage(e);
		}
	}

	protected void escreveRelatorio(byte[] relatorio, String nome, String tipo,
			String contentType, boolean download) {
		try {
			this.escreveRelatorioResponse(relatorio, nome, tipo, contentType, download);
		} catch (Exception e) {
			getRootErrorMessage(e);
		}
	}
	
	protected void escreveRelatorio(byte[] relatorio, String nome, String contentType, boolean download) throws IOException {
		try {
			this.escreveRelatorioResponse(relatorio, nome, null, contentType, download);
		} catch (Exception e) {
			getRootErrorMessage(e);
		}
	}

	private void escreveRelatorioResponse(byte[] relatorio, String nome,
			String tipo, String contentType, boolean download) throws IOException {
		try {
			String contentDisposition = download ? "attachment;filename=" : "inline;filename=";
			String nomeCompleto;
			
			if (tipo != null) {
				nomeCompleto =  contentDisposition + "\"" + nome + "." + tipo.toLowerCase() + "\"";
			} else {
				nomeCompleto =  contentDisposition + "\"" + nome + "\"";
			}
					
			JSFUtil.getServletResponse().setContentType(contentType);
			JSFUtil.getServletResponse().setHeader("Content-Disposition", nomeCompleto);
			JSFUtil.getServletResponse().setContentLength(relatorio.length);
			JSFUtil.getServletResponse().getOutputStream().write(relatorio);
		} catch (IOException e) {
			throw e;
		} finally {
			JSFUtil.getServletResponse().getOutputStream().flush();
			JSFUtil.getServletResponse().getOutputStream().close();
		}
	}
}