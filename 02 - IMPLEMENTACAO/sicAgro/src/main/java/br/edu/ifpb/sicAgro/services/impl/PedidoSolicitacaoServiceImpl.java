package br.edu.ifpb.sicAgro.services.impl;

import javax.inject.Inject;

import br.edu.ifpb.sicAgro.dao.PedidoSolicitacaoDAO;
import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;
import br.edu.ifpb.sicAgro.services.PedidoSolicitacaoService;
import br.edu.ifpb.sicAgro.util.jpa.Transactional;

/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public class PedidoSolicitacaoServiceImpl extends GenericServiceImpl<PedidoSolicitacao, Long> implements PedidoSolicitacaoService {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor responsável por ejetar o dao correspodente a entidade no
	 * service generico
	 * 
	 * @param dao DAO referente a entidade
	 */
	@Inject
	public PedidoSolicitacaoServiceImpl(PedidoSolicitacaoDAO dao) {
		this.dao = dao;
	}

	public PedidoSolicitacaoServiceImpl() {

	}
	
	@Override
	@Transactional
	public void add(PedidoSolicitacao entity) {
		entity.setStatus(PedidoStatus.PROGRESS);
		dao.add(entity);

	}

	@Override
	public Long getTotalPedidosByStatutus(PedidoStatus status) {
		PedidoSolicitacaoDAO pedidoSolicitacaoDAO = (PedidoSolicitacaoDAO) this.dao;
		return pedidoSolicitacaoDAO.getTotalPedidosByStatutus(status);
	}

	@Transactional
	@Override
	public void completarPedidoSolicitacao(PedidoSolicitacao pedidoSolicitacao) {
		pedidoSolicitacao.setStatus(PedidoStatus.COMPLETED);
		this.update(pedidoSolicitacao);
		
	}

	@Override
	public Long getTotalPedidosByMessages() {
		PedidoSolicitacaoDAO dao = (PedidoSolicitacaoDAO) this.dao;
		return dao.getTotalPedidosByMessage();
	}
}