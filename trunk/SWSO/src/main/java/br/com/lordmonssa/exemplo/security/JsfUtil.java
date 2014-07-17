package br.com.lordmonssa.exemplo.security;



import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.SelectItem;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.webapp.MultipartRequest;


public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static String getIP(){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	    String ip = request.getLocalAddr();  
	    return ip;
	}
    
    public static Flash flashScope (){
    	return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
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
    
    public static String getRequestParameter(String name) {
        return (String) getContext().getExternalContext().getRequestParameterMap().get(name);
    }
	
    public static HttpServletResponse getServletResponse() {
		FacesContext context = getContext();

		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		return response;
	}

	public static FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
    
    /**
	 * Finds our MultipartRequestServletWrapper in case application contains other RequestWrappers
	 */
	public static MultipartRequest getMultipartRequestInChain() {
		Object request = getContext().getExternalContext().getRequest();
		
		while(request instanceof ServletRequestWrapper) {
			if(request instanceof MultipartRequest) {
				return (MultipartRequest) request;
			} else {
				request = ((ServletRequestWrapper) request).getRequest();
			}
		}
		
		return null;
	}
    
}