package br.edu.ifba.swso.service.impl;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.swso.service.interfaces.MenuService;
import br.edu.ifba.swso.vo.Menu;

public class MenuServiceImpl implements MenuService {

	private static final long serialVersionUID = 3568354069366074689L;

	public List<Menu> consultarFilhosPorUsuario(Menu menuPai, Long idUsuario) {
		// TODO Implementar
		return new ArrayList<Menu>();
	}
	
	public Menu findById(Long id) {
		// TODO Implementar
		return new Menu();
	}

}
