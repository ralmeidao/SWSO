package br.edu.ifba.swso.service.interfaces;

import java.io.Serializable;
import java.util.List;

import br.edu.ifba.swso.vo.Menu;

public interface MenuService extends Serializable{
	List<Menu> consultarFilhosPorUsuario(Menu menuPai, Long idUsuario);
	
	Menu findById(Long id);

}
