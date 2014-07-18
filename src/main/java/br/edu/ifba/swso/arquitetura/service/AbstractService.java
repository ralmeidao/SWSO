package br.edu.ifba.swso.arquitetura.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.edu.ifba.swso.arquitetura.dao.BaseDAO;
import br.edu.ifba.swso.arquitetura.exception.BusinessException;
import br.edu.ifba.swso.arquitetura.exception.RequiredException;
import br.edu.ifba.swso.arquitetura.util.Util;


/**
 * Classe utilizada por todo serviço EJB que faz operação de CRUD e/ou precisa implementar
 * regras de campos obrigatórios e regras negócio.
 * Contém métodos comuns que devem ser implementados, assim como métodos utilitários.
 * 
 * @author cagalvaom
 *
 * @param <T>
 */
public abstract class AbstractService<T> implements BaseService<T> {

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected Logger logger;
	
	protected List<String> mensagens;
	
	private boolean editando;
	
	// =======================================================================================================
	// Template methods
	// =======================================================================================================

	protected abstract void validaCamposObrigatorios(T entity);

	protected abstract void validaRegras(T entity);

	protected abstract void validaRegrasExcluir(T entity);
	
	protected abstract BaseDAO<T> getDAO();

	// =======================================================================================================
	// Template methods Final
	// =======================================================================================================
	
	/**
	 * Retorna todos do banco.
	 * @return
	 */
	public List<T> findAll() {
		return getDAO().findAll();
	}

	/**
	 * Retorna a entidade por ID.
	 * @param id
	 * @return
	 */
	public T findById(Long id) {
		return getDAO().findById(id);
	}
	
	/**
	 * Chamado pelo controller para salvar alguma entidade.
	 * 
	 * @param entity
	 * @return Retorna a entidade salva.
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public T salvar(T entity) throws RequiredException, BusinessException, Exception {
		
		processValidations(entity, false);
		return saveImpl(entity);
	}

	/**
	 * Chamado pelo controller para atualizar alguma entidade.
	 * 
	 * @param entity
	 * @return Retorna a entidade atualizada.
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public T update(T entity) throws RequiredException, BusinessException, Exception {
		processValidations(entity, true);
		return updateImpl(entity);
	}
	
	/**
	 * Chamado pelo controller para atualizar uma lista de alguma entidade.
	 * 
	 * @param listEntity
	 * @return Retorna a lista das entidades atualizadas.
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	public List<T> update(Collection<T> listEntity) throws RequiredException, BusinessException, Exception {
		processValidations(listEntity, true);
		return updateLote(listEntity);
	}
	
	/**
	 * Chamado pelo controller para remover alguma entidade.
	 * 
	 * @param entity
	 * @throws BusinessException
	 * @throws Exception
	 */
	public void remove(T entity) throws BusinessException, Exception {
		processDeleteValidations(entity);
		removeImpl(entity);
	}
	
	// =======================================================================================================
	// IMPLEMENTAÇÕES DEFAULT
	// =======================================================================================================
	/**
	 * Método que salva uma entidade.
	 * Se necessário, ele será sobrescrito para realizar
	 * algo antes e/ou depois da operação de save.
	 * 
	 * @param entity
	 * @return
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	protected T saveImpl(T entity) throws RequiredException, BusinessException, Exception {
		getDAO().save(entity);
		logger.info("Persistindo "+ entity.getClass().getSimpleName() + " -> " + entity.toString());
		return entity;
	}
	
	/**
	 * Método que atualiza uma entidade.
	 * Se necessário, ele será sobrescrito para realizar
	 * algo antes e/ou depois da operação de update.
	 * 
	 * @param entity
	 * @return
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	protected T updateImpl(T entity) throws RequiredException, BusinessException, Exception {
		getDAO().update(entity);
		logger.info("Atualizando "+ entity.getClass().getSimpleName() + " -> " + entity.toString());
		return entity;
	}
	
	/**
	 * Implementação default do do método para fazer update em lote
	 * 
	 * @param listEntity
	 * @return Retorna uma lista das entidades salvas.
	 * @throws RequiredException
	 * @throws BusinessException
	 * @throws Exception
	 */
	protected List<T> updateLote(Collection<T> listEntity) throws RequiredException, BusinessException, Exception {
		processValidations(listEntity, true);
		List<T> listReturn = new ArrayList<T>();
		for (T entity : listEntity) {
			listReturn.add(updateImpl(entity));
		}
		return listReturn;
	}

	/**
	 * Método que remove uma entidade.
	 * Se necessário, ele será sobrescrito para realizar
	 * algo antes e/ou depois da operação de delete.
	 * 
	 * @param entity
	 * @throws BusinessException
	 * @throws Exception
	 */
	protected void removeImpl(T entity) throws BusinessException, Exception {
		try {
			getDAO().delete(entity);
			logger.info("Removendo "+ entity.getClass().getSimpleName() + " -> " + entity.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("classe: " + e.getClass());
		}
	}

	/**
	 * Processa todas as validações implementadas no validaCamposObrigatorios e
	 * no validaRegras durante o save e o update.
	 * 
	 * @param entity Entidade a ser validada.
	 * @param editando Parametro que será usado nos métodos das RNs.
	 * @throws RequiredException Quando algum campo obrigatório não foi preenchido.
	 * @throws BusinessException Quando alguma RN não foi atendida.
	 */
	protected void processValidations(T entity, boolean editando) throws RequiredException, BusinessException {
		
		setEditando(editando);
		mensagens = new ArrayList<String>();
		
		validaCamposObrigatorios(entity);
		if (!Util.isNullOuVazio(mensagens)) throw new RequiredException(mensagens.get(0));

		validaRegras(entity);
		if (!Util.isNullOuVazio(mensagens)) throw new BusinessException(mensagens.get(0));
	}

	/**
	 * Processa todas as validações implementadas no validaCamposObrigatorios e
	 * no validaRegras durante o save e o update.
	 * 
	 * @param listEntity lista da Entidade a ser validada.
	 * @param editando Parametro que será usado nos métodos das RNs.
	 * @throws RequiredException Quando algum campo obrigatório não foi preenchido.
	 * @throws BusinessException Quando alguma RN não foi atendida.
	 */
	protected void processValidations(Collection<T> listEntity, boolean editando) throws RequiredException, BusinessException {
		setEditando(editando);
		mensagens = new ArrayList<String>();
		
		for (T entity : listEntity) {
			validaCamposObrigatorios(entity);
		}
		if (!Util.isNullOuVazio(mensagens)) throw new RequiredException("Campos Obrigatórios não preenchidos.", mensagens);

		for (T entity : listEntity) {
			validaRegras(entity);
		}
		if (!Util.isNullOuVazio(mensagens)) throw new BusinessException("Regras de negócio não atendidas.", mensagens);
	}
	
	/**
	 * Processa as RNs implementadas no validaRegrasExcluir durante
	 * o remove.
	 * 
	 * @param entity Entidade a ser validada.
	 * @throws BusinessException Se alguma regra não foi atendida.
	 */
	protected void processDeleteValidations(T entity) throws BusinessException {
		mensagens = new ArrayList<String>();
		validaRegrasExcluir(entity);
		if (!Util.isNullOuVazio(mensagens)) throw new BusinessException("Regras de negócio não atendidas.", mensagens);
	}
	
	// =======================================================================================================
	// MÉT0D0S DE ACESSO
	// =======================================================================================================
	protected boolean isEditando() {
		return editando;
	}

	protected void setEditando(boolean editando) {
		this.editando = editando;
	}
}
