package br.edu.ifpb.sicAgro.dao;

import java.util.List;

import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.model.ItemEntrega;

/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface ItemEntregaDAO extends DAO<ItemEntrega, Long>{
	
	/**
	 * 
	 * @return
	 */
	List<Object[]> getTotalPorProduto() throws SicAgroException;

}
