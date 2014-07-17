package br.com.lordmonssa.exemplo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.Conversation;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import br.com.lordmonssa.exemplo.arquitetura.controller.BaseController;
import br.com.lordmonssa.exemplo.arquitetura.controller.ContextoController;
import br.com.lordmonssa.exemplo.arquitetura.exception.BusinessException;
import br.com.lordmonssa.exemplo.arquitetura.exception.RequiredException;
import br.com.lordmonssa.exemplo.arquitetura.util.MensagemUtil;



/**
 * Classe base para CRUD de paginas que envolvem um numero consideravel de
 * submits, evitando problemas ocasionados pelo escopo de request
 * 
 * @param <T>
 */
public abstract class AbstractCRUD<T> extends BaseController implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long defaultConversationTimeout = Long.valueOf(1800000);

	protected Long customConversationTimeout;

	@Inject
	protected Conversation conversation;

	@Inject
	protected ContextoController contextoController;

	@Inject
	private Validator validator;

	private boolean visualizando = false;
	private boolean editando = false;
	private boolean fromResults = false;

	private Long id;
	private String caminhoAlternativo;
	protected T instance;

	private T instanceConsulta;

	protected List<T> allInstance = new ArrayList<T>();

	/**
	 * Construtor default
	 */
	public AbstractCRUD() {
	}

	public T getInstance() {
		if (instance == null) {
			if (id != null) {
				instance = loadInstance();
			} else {
				instance = newInstance();
			}
		} else if ((id != null) && (id != getEntityId(instance))) {
			instance = loadInstance();
		}
		return instance;
	}

	public void setInstance(T instance) {
		this.instance = instance;
	}

	public T getInstanceConsulta() {
		if (instanceConsulta == null) {
			instanceConsulta = newInstance();
		}
		return instanceConsulta;
	}

	public void setInstanceConsulta(T instanceConsulta) {
		this.instanceConsulta = instanceConsulta;
	}

	/**
	 * Carrega a instancia da base de dados
	 * 
	 * @return
	 */
	public T loadInstance() {
		try {
			return load(getId());
		} catch (Exception e) {
			getRootErrorMessage(e);
		}
		return null;
	}

	/**
	 * Indica se a instancia e nova, ou uma ja existente
	 * 
	 * @return
	 */
	public boolean isManaged() {
		return getEntityId(instance) != null && getEntityId(instance) != 0;
	}

	/**
	 * Persiste ou atualiza uma instancia na base de dados.
	 * 
	 * @return
	 */
	public void save(ActionEvent event) {
		try {
			
			if (isManaged()) {
				updateImpl(getInstance());
			} else {
				saveImpl(getInstance());
			}

			atualizarOrigemCadastro("detalhar");
			reconsultaAllInstance();
		} catch (RequiredException re) {
			/*facesMessager.warn(Util.formatRequiredMessage(re));*/
		} catch (BusinessException be) {
			/*facesMessager.warn(Util.formatBusinessMessage(be));*/
		} catch (Exception e) {
			getRootErrorMessage(e);
			logErroMessage();
		}
	}

	/**
	 * Persiste ou atualiza uma instancia na base de dados e mantém a
	 * conversation iniciada, caso seja um salvar/atualizar de dentro de outra
	 * funcionalidade.
	 * 
	 * @return
	 */
	public void saveAndkeepConversation(ActionEvent event) {
		try {
			if (isManaged()) {
				updateImpl(getInstance());
			} else {
				saveImpl(getInstance());
			}
			instance = newInstance();
			contextoController.setCrudMessage(null);
		} catch (RequiredException re) {
			/*facesMessager.warn(Util.formatRequiredMessage(re));*/
		} catch (BusinessException be) {
			/*facesMessager.warn(Util.formatBusinessMessage(be));*/
		} catch (Exception e) {
			getRootErrorMessage(e);
			logErroMessage();
		}
	}

	/**
	 * Remove uma instancia da base de dados.
	 * 
	 * @return
	 */
	public String delete() {
		try {
			deleteImpl(instance);
			removeInstanceInAllInstance();
			limparForm();
			return redirectTelaResultado();
		} catch (BusinessException be) {
			/*facesMessager.warn(Util.formatBusinessMessage(be));*/
		} catch (Exception e) {
			getRootErrorMessage(e);
			logErroMessage();
		}

		return "";
	}

	/**
	 * Utilizado na consulta principal de um CRUD.
	 * 
	 * @return
	 */
	public String consultar() {
		try {
			consultarImpl();
			return redirectTelaResultado();
		} catch (RequiredException re) {
			/*facesMessager.warn(Util.formatRequiredMessage(re));*/
		} catch (BusinessException be) {
			/*facesMessager.warn(Util.formatBusinessMessage(be));*/
		} catch (Exception e) {
			getRootErrorMessage(e);
			logErroMessage();
		}
		return "";
	}

	private void reconsultaAllInstance() {
		try {
			consultarImpl();
		} catch (Exception e) {
			getRootErrorMessage(e);
			e.printStackTrace();
		}
	}

	private void removeInstanceInAllInstance() {
		allInstance.remove(instance);
	}

	/**
	 * Inicia um escopo de conversacao.
	 * 
	 */
	protected void initConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
			conversation
					.setTimeout(customConversationTimeout != null ? customConversationTimeout
							: defaultConversationTimeout);
			logger.info("CDI Conversacao " + "[" + conversation.getId() + "]"
					+ " inicializada com sucesso");
		}
	}

	/**
	 * Finaliza um escopo de conversacao.
	 * 
	 */
	protected void endConversation() {
		if (!conversation.isTransient()) {
			logger.info("CDI Conversacao " + "[" + conversation.getId() + "]"
					+ " finalizada com sucesso");
			conversation.end();
		}
	}

	protected List<T> getAll() {
		return allInstance;
	}

	public void start() {
		try {
			allInstance = loadAllInstance();
		} catch (Exception e) {
			getRootErrorMessage(e);
		}
	}

	protected boolean conversationIsTransient() {
		return conversation.isTransient();
	}

	/**
	 * @return
	 * 
	 *         Método que retorna o nome do header do panel dos parametros da
	 *         consulta. Pode ser Consultar + o nome da entidade, ou quantidade
	 *         de itens da lista + Itens encontrados.
	 */
	public String getHeaderMainPanel() {
		return allInstance.size() > 0 ? allInstance.size() + " "
				+ (allInstance.size() == 1 ?
						MensagemUtil.obterMensagem("geral.crud.itemFound")
						: MensagemUtil.obterMensagem("geral.crud.itemsFound"))
				: MensagemUtil.obterMensagem("geral.crud.noItemFound");
	}

	protected boolean isConversationInitiated() {
		return conversation.getId() != null ? true : false;
	}

	/**
	 * @param event
	 * 
	 * Método utilizado no actionListener do botao limpar.
	 */
	public void limparFormEvent(ActionEvent event) {
		limparFormConsulta();
	}

	/**
	 * @param event
	 * 
	 * Método utilizado no actionListener do botao nova consulta.
	 */
	public void novaConsultaEvent(ActionEvent event) {
		limparLista();
	}

	/**
	 * Método utilizado para limpar a lista dos resultados na tela de consulta.
	 * Ao limpar a lista, a grade some e o parametros de consulta aparecem.
	 */
	protected void limparLista() {
		allInstance.clear();
	}

	/**
	 * Método utilizado para limpar o formulario.
	 */
	protected void limparForm() {
		instance = newInstance();
		setId(null);
	}

	/**
	 * Método utilizado para limpar o formulario na tela de consulta.
	 */
	protected void limparFormConsulta() {
		instanceConsulta = newInstance();
	}

	/**
	 * Método utilizado na tag metadata do tipo preRenderView da tela de
	 * cadastro.
	 */
	public void prepareToCreate() {
		if (instance == null) {
			instance = newInstance();
		}

		initConversation();
	}

	/**
	 * Método utilizado na tag metadata do tipo preRenderView da tela de lista.
	 */
	public void prepareToResult() {
		if (contextoController.getCrudMessage() != null) {
			facesMessager.info(contextoController.getCrudMessage());
			contextoController.setCrudMessage(null);
		}
	}

	/**
	 * Método utilizado na tag metadata do tipo preRenderView da tela de
	 * consulta.
	 */
	public void prepareToSearch() {
		if (instanceConsulta == null) {
			instanceConsulta = newInstance();
		}
		atualizarOrigemCadastro("consulta");

		// endConversation();
		initConversation();
	}

	protected void validateInstance(T instance)
			throws ConstraintViolationException {
		// Create a bean validator and check for issues.
		Set<ConstraintViolation<T>> violations = validator.validate(instance);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(
					new HashSet<ConstraintViolation<?>>(violations));
		}
	}

	/**
	 * Redireciona para a tela de consulta informando se limpa o fomulario ou
	 * nao. true para volta da tela de cadastro. false para volta da tela de
	 * resultado.
	 * 
	 * @param clearInstance
	 * @return
	 */
	public String redirectTelaConsulta(boolean clearInstance) {
		/*
		 * if (clearInstance) {TODO Remover instance = newInstance(); }
		 */

		// endConversation();
		return includeRedirect("consulta");
	}

	/**
	 * Redireciona para a tela de resultado.
	 * 
	 * @return
	 */
	public String redirectTelaResultado() {
		return includeRedirect("lista");
	}

	/**
	 * Redireciona para a tela de cadastro.
	 * 
	 * Se está visualizando ou editando, carrega os dados que precisam aparecer
	 * na tela e que não possuem campos na entidade.
	 * 
	 * Se não está visualizando nem editando, limpa os dados.
	 * 
	 * @return
	 */
	public String redirectTelaCadastro(String origem) {
		initConversation();

		atualizarOrigemCadastro(origem);

		if (getId() != null) {
			try {
				setInstance(load(getId()));
			} catch (Exception e) {
				getRootErrorMessage(e);
			}
		}

		if (!isVisualizando() && !isEditando()) {
			limparForm();
		}

		if(caminhoAlternativo != null){
			return includeRedirect(caminhoAlternativo);
		}
		return includeRedirect("cadastro");
	}

	/**
	 * Atualiza os Booleanos que indicam de onde o usuário chamou o cadastro.
	 * 
	 * @param origem
	 */
	private void atualizarOrigemCadastro(String origem) {
		if ("detalhar".equals(origem)) {
			setVisualizando(true);
			setEditando(false);
			setFromResults(false);
		} else if ("editar".equals(origem)) {
			setVisualizando(false);
			setEditando(true);
			setFromResults(false);
		} else if ("lista".equals(origem)) {
			setVisualizando(false);
			setEditando(false);
			setFromResults(true);
		} else if ("consulta".equals(origem)) {
			setVisualizando(false);
			setEditando(false);
			setFromResults(false);
		}
	}

	/**
	 * Se no cancelar estamos visualizando, editando ou viemos do resultado,
	 * volta pra tela de resultado, pois ela foi a origem da chamada. Senão,
	 * retorna para a tela de consulta.
	 * 
	 * @return
	 */
	public String redirectBotaoCancelar() {
		if (isEditando() || isVisualizando() || isFromResults()) {
			return redirectTelaResultado();
		} else {
			// endConversation();
			return redirectTelaConsulta(true);
		}
	}

	/**
	 * Método utilizado no Link do Botão Alterar da tela de Detalhe. Atualiza a
	 * tela para Editando.
	 */
	public void preparaAlterar() {
		atualizarOrigemCadastro("editar");
	}

	public String getHeaderCadastro() {
		if (isEditando()) {
			return "Atualizar";
		} else if (isVisualizando()) {
			return "Detalhes";
		} else {
			return "Cadastrar";
		}
	}

	/**
	 * By Carlos Final
	 */

	// =======================================================================================================
	// Template method
	// =======================================================================================================

	protected abstract Long getEntityId(T referenceValue);

	protected abstract T load(Long id) throws Exception;

	protected abstract void deleteImpl(T referenceValue)
			throws BusinessException, Exception;

	protected abstract void saveImpl(T referenceValue)
			throws RequiredException, BusinessException, Exception;

	protected abstract void updateImpl(T referenceValue)
			throws RequiredException, BusinessException, Exception;

	protected abstract void consultarImpl() throws BusinessException, Exception;

	protected abstract T newInstance();

	protected abstract List<T> loadAllInstance() throws Exception;

	// =======================================================================================================
	// Getters/Setters
	// =======================================================================================================

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public List<T> getAllInstance() {
		return allInstance;
	}

	public void setAllInstance(List<T> allInstance) {
		this.allInstance = allInstance;
	}

	public boolean isVisualizando() {
		return visualizando;
	}

	public void setVisualizando(boolean visualizando) {
		this.visualizando = visualizando;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public boolean isFromResults() {
		return fromResults;
	}

	public void setFromResults(boolean fromResults) {
		this.fromResults = fromResults;
	}

	public void setCaminhoAlternativo(String caminhoAlternativo) {
		this.caminhoAlternativo = caminhoAlternativo;
	}
	
	
}