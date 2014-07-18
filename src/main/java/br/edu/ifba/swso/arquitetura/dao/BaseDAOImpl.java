package br.edu.ifba.swso.arquitetura.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDAOImpl<T> extends AbstractBaseDAOImpl implements BaseDAO<T> {

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		this.persistentClass =
				(Class<T>) ((ParameterizedType) getClass()
						.getGenericSuperclass())
						.getActualTypeArguments()[0];
	}

	public T save(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}

	public T update(T entity) {
		return getEntityManager().merge(entity);
	}    

	public void delete(T entity) {
		T attachedEntity = getEntityManager().merge(entity);
		getEntityManager().remove(attachedEntity);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getEntityManager().createQuery("select a from " + persistentClass.getName() + " a")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(int first, int max) {
		org.hibernate.Query query =
				getSession().createQuery("select a from " + persistentClass.getName() + " a");
		query.setFirstResult(first);
		query.setFetchSize(max);
		query.setMaxResults(max);

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		return (T) getSession().get(persistentClass, id);
		/*
		 * return (T) getSession().createQuery( "select a from " +
		 * persistentClass.getName() + " a where a.id = :id ").setParameter("id", id)
		 * .uniqueResult();
		 */
	}
	
	public T find(Class<T> classe, Long id) {
		return getEntityManager().find(classe, id);
	}
}
