package br.edu.ifba.swso.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.ifba.swso.arquitetura.util.Util;

/**
 * Classe para convercao do CPF.
 * 
 * @author esouzaa
 *
 */

@FacesConverter("converter.CpfConverter")
public class CpfConverter implements Converter {

    /**
	 * Método para remover os pontos e hifens do CPF;
	 * 
	 */
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		String cpf = value;

		if (value!= null && !value.equals(""))
			cpf = value.replaceAll("\\.", "").replaceAll("\\-", "");

		return cpf;
	}

    /**
	 * Método para adicionar pontos e hifens no CPF além de colocar zeros a esquerda;
	 * 
	 */
	
	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		String cpf= value.toString();
		
		cpf = Util.lpad(cpf, '0', 11);
		
		if (cpf != null && cpf.length() == 11)
			cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);

		return cpf;
	}
}