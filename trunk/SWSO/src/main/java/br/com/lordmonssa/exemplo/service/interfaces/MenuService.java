package br.com.lordmonssa.exemplo.service.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.lordmonssa.exemplo.vo.Menu;

public interface MenuService extends Serializable{
	List<Menu> consultarFilhosPorUsuario(Menu menuPai, Long idUsuario);
	
	Menu findById(Long id);

}
