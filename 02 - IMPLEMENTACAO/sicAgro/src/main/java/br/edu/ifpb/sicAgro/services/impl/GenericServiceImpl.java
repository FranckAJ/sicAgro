package br.edu.ifpb.sicAgro.services.impl;

import java.io.Serializable;
import java.util.List;

import br.edu.ifpb.sicAgro.dao.DAO;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.services.Service;
import br.edu.ifpb.sicAgro.util.jpa.Transactional;

/**
 * Classe generica serve para implementar caracterisiticas comuns dos services,
 * para que suas classes filhas usem.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 * @param <T>
 * @param <K>
 */
public class GenericServiceImpl<T, K> implements Service<T, K>, Serializable {

	private static final long serialVersionUID = 1026846993733369843L;

	protected DAO<T, K> dao;

	/**
	 * Método generico para salvar um entidade.
	 * 
	 */
	@Override
	@Transactional
	public void add(T entity) throws SicAgroException {
		dao.add(entity);
	}

	/**
	 * Atualiza um entidade qualquer.
	 * 
	 */
	@Override
	@Transactional
	public T update(T entity){
		return dao.update(entity);
	}

	/**
	 * Remove uma entidade.
	 */
	@Override
	@Transactional
	public void remove(T entity) throws SicAgroException {
		dao.remove(entity);
	}

	/**
	 * 
	 */
	@Override
	public T findById(K id) {
		return dao.findById(id);
	}

	/**
	 * 
	 */
	@Override
	public List<T> findAll() {
		return dao.findAll();
	}
}
