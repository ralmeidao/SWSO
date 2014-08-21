package br.edu.ifba.swso.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
/*import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;*/


import org.apache.log4j.Logger;
/*import org.hibernate.Session;*/


import br.edu.ifba.swso.exception.SystemException;
import br.edu.ifba.swso.repository.ConfigurationRepository;
/*import br.edu.ifba.swso.repository.DataRepository;*/

/**
 * Classe responsavel pela producao de recursos uteis para utilizacao na aplicacao
 */
public class Resources {

	/*@Produces
	@DataRepository
    @PersistenceContext(unitName="swsoPU")
	private EntityManager em;*/

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
	
	@Produces
	@RequestScoped
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}

/*	@Produces
	public Session produceSession() {
		return em.unwrap(Session.class);
	}*/
	
	 @Produces @ConfigurationRepository("")
	 public Properties produceProperties(InjectionPoint ip) {
		 
	   Properties p = new Properties();
	   
	   String value = ip.getAnnotated().getAnnotation(ConfigurationRepository.class).value();

	   if(value == null || value.equals("")) {
	       return p;
	   }

	   String propValue = System.getProperty(value);
	   
	   if(propValue != null) {
	       value = propValue;
	   }

	   File f = new File(value);
	   
	   if(f.exists() && !f.isDirectory()) {
	       try{
	           FileInputStream fis = new FileInputStream(f);
	           p.load(fis);
	       } catch (IOException e) {
	    	   new SystemException(e.getMessage(), e);
	       }
	   }
	   
	   try{
	       
		   InputStream is = this.getClass().getClassLoader().getResourceAsStream(value);
	       p.load(is);
	       
	   } catch (Exception e) {
		   new SystemException(e.getMessage(), e);
	   }
	   return p;
	 }
}