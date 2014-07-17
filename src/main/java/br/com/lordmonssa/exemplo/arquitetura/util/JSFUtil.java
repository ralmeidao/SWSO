package br.com.lordmonssa.exemplo.arquitetura.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.webapp.MultipartRequest;

public class JSFUtil {
	
	/*@Inject
	private ComponentLookup componentLookup;*/
	
	public static String getRequestParameter(String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }
	
	public static Object getSessionMapValue(String key) {
		if (FacesContext.getCurrentInstance() != null) {
			return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
		} else {
			return null;
		}
    }

    public static void setSessionMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }
    
    public static Object getApplicationMapValue(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(key);
    }

    public static void setApplicationMapValue(String key, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put(key, value);
    }
    
    public static HttpServletResponse getServletResponse() {
		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		return response;
	}
    
    /**
	 * Finds our MultipartRequestServletWrapper in case application contains other RequestWrappers
	 */
	public static MultipartRequest getMultipartRequestInChain() {
		Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		while(request instanceof ServletRequestWrapper) {
			if(request instanceof MultipartRequest) {
				return (MultipartRequest) request;
			} else {
				request = ((ServletRequestWrapper) request).getRequest();
			}
		}
		
		return null;
	}
	
//	/**
//	 * Método utilitário que limpa os filhos do componente passado como parametro tomando-se por base o ViewRoot do faces.
//	 * 
//	 * @param pIdComponente
//	 */
//	public void clearTreeComponent(String pIdComponente) {
//		UIComponent ui = componentLookup.lookup(pIdComponente, FacesContext.getCurrentInstance().getViewRoot());
//		ui.getChildren().clear();
//	}
//
//	/**
//	 * Limpa os dados dos componentes de edição e de seus filhos, recursivamente. Checa se o componente é uma instância de
//	 * EditableValueHolder e 'reseta' suas propriedades.
//	 * @param pIdComponente - Id do componente alvo da limpeza - (Id que será buscado no ViewRoot)
//	 */
//	public void cleanSubmittedValues(String pIdComponente) {
//		UIComponent ui = componentLookup.lookup(pIdComponente, FacesContext.getCurrentInstance().getViewRoot());
//		cleanSubmittedValues(ui);
//	}
//
//	/**
//	 * Limpa os dados dos componentes de edição e de seus filhos, recursivamente. Checa se o componente é uma instância de
//	 * EditableValueHolder e 'reseta' suas propriedades.
//	 * @param component - Componente alvo da limpeza
//	 */
//	private void cleanSubmittedValues(UIComponent component) {
//		if (component instanceof EditableValueHolder) {
//			EditableValueHolder evh = (EditableValueHolder) component;
//			evh.setSubmittedValue(null);
//			evh.setValue(null);
//			evh.setLocalValueSet(false);
//			evh.setValid(true);
//		}
//		if (component.getChildCount() > 0) {
//			for (UIComponent child : component.getChildren()) {
//				cleanSubmittedValues(child);
//			}
//		}
//	}

	/**
	 * Método que busca o bean passado como parametro
	 * @param pNomeBean
	 * @return
	 */
	/*public static Object getManagedBean(String pNomeBean) {
		FacesContext context = FacesContext.getCurrentInstance();
		ELResolver resolver = context.getApplication().getELResolver();
		return resolver.getValue(context.getELContext(), null, pNomeBean);
	}*/

}
