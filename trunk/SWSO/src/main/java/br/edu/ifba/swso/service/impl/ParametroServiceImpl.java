package br.edu.ifba.swso.service.impl;

import javax.inject.Inject;

import br.edu.ifba.swso.dao.interfaces.ParametroDAO;
import br.edu.ifba.swso.service.interfaces.ParametroService;

public class ParametroServiceImpl implements ParametroService {

	private static final long serialVersionUID = -4772351261806672679L;
	
	@Inject
	private ParametroDAO parametroDAO;


	public ParametroDAO getParametroDAO() {
		return parametroDAO;
	}
	
}
