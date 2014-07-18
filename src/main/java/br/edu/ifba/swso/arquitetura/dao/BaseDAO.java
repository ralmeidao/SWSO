package br.edu.ifba.swso.arquitetura.dao;

import java.util.List;

public interface BaseDAO<T> {
    public T save(T entity);
    public T update(T entity);    
    public void delete(T entity);
    public List<T> findAll();
    public List<T> findAll(int first, int max);
    public T findById(Long id);
    public T find(Class<T> classe, Long id);
}
