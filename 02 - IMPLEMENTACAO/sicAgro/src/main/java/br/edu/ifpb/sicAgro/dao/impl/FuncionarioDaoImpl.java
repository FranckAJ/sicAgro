package br.edu.ifpb.sicAgro.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.sicAgro.dao.FuncionarioDAO;
import br.edu.ifpb.sicAgro.enumerations.UserRole;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.Funcionario;

/**
 * 
 * @author franck
 *
 */
public class FuncionarioDaoImpl extends GenericDaoImpl<Funcionario, Long> implements FuncionarioDAO{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Funcionario> findByName(String name) {
		
		Query query = entityManager.createNamedQuery("Funcionario.findByName");
		query.setParameter("name", "%" + name + "%");

		@SuppressWarnings("unchecked")
		List<Funcionario> result = query.getResultList();
		return result;
	}

	@Override
	public List<Funcionario> findDriversByName(String name, UserRole userRole) throws SicAgroException {
		List<Funcionario> result = null;
		try {
			TypedQuery<Funcionario> query = entityManager.createNamedQuery("Funcionario.findDriverByName", Funcionario.class);
			query.setParameter("name", "%" + name.toLowerCase() + "%");
			query.setParameter("role", userRole);
			result = query.getResultList();
		} catch (PersistenceException e) {
			throw new SicAgroException("erro ao tentar recuperar os motoristas pelo nome "+ e.getMessage());
		}
		
		return result;
	}

}
