package br.edu.ifba.swso.arquitetura.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;


/**
 * Classse utilizadas para colocar o objeto na sessão e ser utilizado no questionário de perguntas. 
 * 
 * @author jteixeira
 *
 */


@SessionScoped
@Named
public class ContextoController implements Serializable {
	
	private static final long serialVersionUID = 2834328468141778864L;
	
	private Object object;
	
	private String crudMessage;
		
	private Object objectFilter;	
	
	private String idComponente;
	
	private String telaOrigem;
	
	private byte[] byteArray;
	
	private ServletContext scontext;
	
	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		scontext = (ServletContext) facesContext.getExternalContext().getContext();
		
		System.out.println("Sessão iniciada!");
	}
	
	@PreDestroy
	public void detroy() {
		System.out.println("Sessão expirada!");
	}

	public void limpar() {
		this.crudMessage=null;
		this.idComponente =null;
		this.object = null;
		this.telaOrigem = null;		
	}
	
	public void clean(){
		this.object = null;
	}

	/**
	 * Restringe o tamanho de um atributo
	 * 
	 * @param value
	 * @param maximo
	 * @return
	 */
	public String truncaValor(String value, int maximo) {
		if (value.trim().length() > maximo) {
			return value.substring(0, maximo);
		}

		return value;
	}
	
	/**
	 * Método usado para guardar o id do componente que chamou o evento.
	 * Esse método será usado normalmente em botões que chamam dialogs
	 */
	public void guardarIdComponente(){
		FacesContext context = FacesContext.getCurrentInstance();
	    Map<String, String> map = context.getExternalContext().getRequestParameterMap();
	    idComponente = (String) map.get("idComponente");
	}
	
	/**
	 * Este método trabalha em conjunto com o método guardarIdComponente. Também será usado normalmente junto com os dialogs.
	 * Quando o dialog for fechado este método dará foco no botão que chamou o dialog.
	 * @throws InterruptedException 
	 */
	public void giveFocus() throws InterruptedException {
		Thread.sleep(250);
		RequestContext.getCurrentInstance().execute("giveFocus('"+idComponente+"')");
	}
	
	//MÉT0D0S DE ACESSO 
	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getCrudMessage() {
		return crudMessage;
	}

	public void setCrudMessage(String crudMessage) {
		this.crudMessage = crudMessage;
	}

	public Object getObjectFilter() {
		return objectFilter;
	}

	public void setObjectFilter(Object objectFilter) {
		this.objectFilter = objectFilter;
	}

	public String getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(String idComponente) {
		this.idComponente = idComponente;
	}

	public String getTelaOrigem() {
		return telaOrigem;
	}

	public void setTelaOrigem(String telaOrigem) {
		this.telaOrigem = telaOrigem;
	}

	public DefaultStreamedContent getImage() {
		if(byteArray != null) {
			return new DefaultStreamedContent(new ByteArrayInputStream(byteArray), "image/jpeg");	
		}
		return null;
	}

	public byte[] getByteArray() {
		return byteArray;
	}

	public void setByteArray(byte[] byteArray) {
		this.byteArray = byteArray;
	}
	
	public ServletContext getServletContext() {
		return scontext;
	}

}
