package br.edu.ifba.swso.arquitetura.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import br.edu.ifba.swso.arquitetura.exception.BusinessException;
import br.edu.ifba.swso.arquitetura.exception.RequiredException;


/**
 * Interface contendo operações de consulta e manutenção ao banco de dados
 *
 * @param <T> Entidade sobre a qual serão realizadas as operações de consulta e manutenção.
 */
public interface BaseService<T> extends Serializable {

	/**
	 * Recupera a entidade a partir de seu identificador
	 * 
	 * @param id O identificador da entidade
	 * @return A entidade com seus dados preenchidos a partir do banco de dados
	 * @throws BusinessException 
	 */
	T findById(Long id) throws Exception;
	
	/**
	 * Consulta as entidades que correspondam aos filtros preenchidos na entidade passada como parâmetro.
	 * 
	 * @param entidade Entidade com os dados de filtro para a consulta preenchidos.
	 * @return Lista de entidades que possuem os dados correspondentes aos do filtro passado como parâmetro.
	 * @throws RequiredException 
	 */
	List<T> consultar(T entidade) throws RequiredException, BusinessException;
	
	/**
	 * Lista todas as entidades cadastradas na tabela que reprensenta a entidade anotada nesta interface. 
	 * 
	 * @return Coleção contendo todas as entidades cadastradas na tabela que reprensenta a entidade anotada nesta interface.  
	 */
	List<T> findAll();
	
	/**
	 * Insere a entidade no banco de dados.
	 * 
	 * @param entidade Entidade a ser inserida.
	 * @return A entidade que está persistida no banco.
	 * @throws BusinessException
	 * @throws RequiredException 
	 */
	T salvar(T entity) throws RequiredException, BusinessException, Exception;
	
	/**
	 * Atualiza os valores da entidade no banco de dados.
	 * 
	 * @param entidade Entidade que terá os valores atualizados.
	 * @return A entidade que está persistida no banco. 
	 * @throws BusinessException
	 * @throws RequiredException 
	 */
	T update(T entity) throws RequiredException, BusinessException, Exception;
	
	/**
	 * Atualiza os valores de uma lista de objetos de determinada entidade.
	 * 
	 * @param listEntity
	 * @return Retorna a lista das entidades atualizadas.
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	List<T> update(Collection<T> listEntity) throws RequiredException, BusinessException, Exception;
	
	/**
	 * Exclui a entidade do banco de dados.
	 * @param entidade Entidade a ser excluída.
	 * @throws BusinessException
	 * @throws RequiredException 
	 */
	void remove(T entity) throws BusinessException, Exception;
	
}
