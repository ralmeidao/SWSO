package br.edu.ifba.swso.arquitetura.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.edu.ifba.swso.repository.DataRepository;

public abstract class AbstractBaseDAOImpl {

	@Inject
	@DataRepository
	private EntityManager em;
	
    protected Session getSession() {
		return em.unwrap(Session.class);
	}

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }
}
