package br.edu.ifba.swso.validator;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.ifba.swso.arquitetura.util.Util;

/**
 * Classse utilizadas para a validacao de campos. 
 * 
 * @author esouzaa
 *
 */

@FacesValidator("validator.CamposValidator")
public class CamposValidator implements Validator {

	/**
	 * Método para verificar se o campo é obrigatorio. 
	 * 
	 * @author esouzaa
	 *
	 */
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (!Util.isNull(arg2) && !Util.isEmptyString(arg2.toString())) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("Campo Obrigatório!");
			refresh();
			throw new ValidatorException(message);
		}
	}

	/**
	 * Método para recarregar a página limpando os campos. 
	 * 
	 * @author esouzaa
	 *
	 */
	
	public void refresh() {
		  FacesContext context = FacesContext.getCurrentInstance();
		  Application application = context.getApplication();
		  ViewHandler viewHandler = application.getViewHandler();
		  UIViewRoot viewRoot = viewHandler.createView(context, context
		   .getViewRoot().getViewId());
		  context.setViewRoot(viewRoot);
		  context.renderResponse(); //Optional
		}	
	
}
