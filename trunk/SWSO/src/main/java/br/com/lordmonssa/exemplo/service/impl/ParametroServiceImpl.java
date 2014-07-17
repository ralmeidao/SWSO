package br.com.lordmonssa.exemplo.service.impl;

import javax.inject.Inject;

import br.com.lordmonssa.exemplo.dao.interfaces.ParametroDAO;
import br.com.lordmonssa.exemplo.service.interfaces.ParametroService;

public class ParametroServiceImpl implements ParametroService {

	private static final long serialVersionUID = -4772351261806672679L;
	
	@Inject
	private ParametroDAO parametroDAO;


	public ParametroDAO getParametroDAO() {
		return parametroDAO;
	}
	
}
