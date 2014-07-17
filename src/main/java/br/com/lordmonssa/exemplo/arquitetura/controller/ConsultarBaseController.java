package br.com.lordmonssa.exemplo.arquitetura.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.component.datatable.DataTable;

import br.com.lordmonssa.exemplo.arquitetura.exception.BusinessException;
import br.com.lordmonssa.exemplo.arquitetura.exception.RequiredException;
import br.com.lordmonssa.exemplo.arquitetura.service.BaseService;
import br.com.lordmonssa.exemplo.arquitetura.util.MensagemUtil;
import br.com.lordmonssa.exemplo.arquitetura.util.Util;

/**
 * Classe que concentra o fluxo de uma tela de consulta que pode redirecionar para cadastro,edição.
 * Sendo possível excluir e filtrar
 *  
 * @author wfjesus
 *
 * @param <T>
 */
public abstract class ConsultarBaseController<T> extends BaseController {

	private static final long serialVersionUID = 4674809733896204945L;

	private T instanceFilter;
	
	private List<T>  lista;
	
	private T instanceExcluir;
	
	protected static final String DATA_TABLE_CRUD = ":consultaForm:dataTableCrud";

	@Inject
	protected ContextoController contextoController;

	/**
	 * Método responsável por criar a instancia que será utilzado pelo filtro da consulta
	 * @return
	 */	
	protected abstract T newInstance();
	
	/**
	 * Método responsável por vincular o service da Entidade parametrizada
	 * @return
	 */
	protected abstract BaseService<T> getService();
	
	/**
	 * Sufixo do nome tela que o controller está vinculada, Isso serve
	 * Para tornar possível voltar para tela chamadora.
	 * @return
	 */
	protected abstract String getTelaOrigem();
	
	protected abstract String getSufixoTela();
	
	/**
	 *Método que concentra toda inilização necessário ao funcionamento da tela 
	 */
	protected abstract void continuarInicializacao();
	
	@PostConstruct
	public void inicializar(){
		lista = new ArrayList<T>();
		instanceFilter = newInstance();
		continuarInicializacao();		
	}
	
	/**
	 * 
	 * @return Retorna a instância utilizada para filtrar a consulta
	 */
	public T getInstanceFilter() {
		if (instanceFilter == null) {
			instanceFilter = newInstance();
		}
		return instanceFilter;
	}

	public void setInstanceFilter(T instanceFilter) {
		this.instanceFilter = instanceFilter;
	}
	
	/**
	 * Método responsável por limpar os campos de filtro
	 */
	public void limparFiltro(){
		instanceFilter = newInstance();
		lista = new ArrayList<T>();
	}
	
	/**
	 * Redireciona para tela de cadastro
	 * @return
	 */
	public String novo(){
		contextoController.limpar();
		contextoController.setTelaOrigem(getTelaOrigem());
		return includeRedirect("cadastro".concat(getSufixoTela()));
	}
	
	/**
	 * Método responsável por redirecionara para tela que edita o registro selecionado
	 * @param t - Objeto que representa a linha selecionada
	 * @return
	 */
	public String editar(T t){
		this.contextoController.limpar();
		contextoController.setObject(t);
		contextoController.setTelaOrigem("consulta".concat(getSufixoTela()));
		return includeRedirect("cadastro".concat(getSufixoTela()));		
	}
	
	/**
	 * Método responsável por consultar os registros de acordo aos filtros informados
	 * @throws Exception
	 */
	public void consultar() {
		try {
			lista = getService().consultar(instanceFilter);
			contextoController.setObjectFilter(instanceFilter);
			if (Util.isNullOuVazio(lista)){
				facesMessager.addMessageError("geral.crud.noItemFound");
			}
		} catch (RequiredException re) {
			facesMessager.addMessageError(re.getMessage());
		} catch (BusinessException be) {
			facesMessager.addMessageError(be.getMessage());
		}
	}
	
	/**
	 * Método responsável por excluir o registro selecionado
	 * @param t -  Objeto que repesenta a linha selecionada
	 * @throws BusinessException
	 * @throws Exception
	 */
	public void excluir() throws BusinessException, Exception {
		
		try {
			getService().remove(instanceExcluir);
			lista.remove(instanceExcluir);			
			facesMessager.addMessageInfo("geral.crud.excluido");
		}
		catch (BusinessException be) {
			facesMessager.addMessageError(MensagemUtil.obterMensagem("pergunta.exclusao.integridade"));
		}
		catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	/**
	 * Método utilizado na tag metadata do tipo preRenderView da tela de lista.
	 */
	@SuppressWarnings("unchecked")
	public void prepareToResult() throws Exception {
		
		if (contextoController.getCrudMessage() != null) {
			facesMessager.info(contextoController.getCrudMessage());
			contextoController.setCrudMessage(null);
		}
		if (!Util.isNullOuVazio(contextoController.getObjectFilter())) {
			this.setInstanceFilter((T)contextoController.getObjectFilter());
			lista = getService().consultar(instanceFilter);
		}
	}

	/**
	 * limpa a ordenação e paginação da data table
	 */
	public void resetDatatable() {
	    final DataTable table = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(getIdDataTableCrud());
	    if (table!=null) {
	    	table.reset();
	    	table.setSortBy(null);
		}
	}
	
	/**
	 * Lista exibida na grid de resultados da consulta
	 * @return List<T>
	 */
	public List<T> getLista() {
		return lista;
	}

	/**
	 * Guarda a lista que será exibida na grid de resultados da consulta
	 */
	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	/**
	 * Retorna a instância do objeto que será excluído
	 * @return T
	 */
	public T getInstanceExcluir() {
		return instanceExcluir;
	}

	/**
	 * Guarda uma instância do objeto que será excluído
	 */
	public void setInstanceExcluir(T instanceExcluir) {
		this.instanceExcluir = instanceExcluir;
	}
	
	/**
	 * Método responsável por retornar o id do data table que guarda o resultado da consulta
	 * @return
	 */
	protected String getIdDataTableCrud() {
		return DATA_TABLE_CRUD; 
	}
}
