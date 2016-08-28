package br.edu.ifpb.sicAgro.services;

import br.edu.ifpb.sicAgro.enumerations.PedidoStatus;
import br.edu.ifpb.sicAgro.model.PedidoSolicitacao;

/**
 * 
 *
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
public interface PedidoSolicitacaoService extends Service<PedidoSolicitacao, Long>{
	
	 Long getTotalPedidosByStatutus(PedidoStatus status);
	 
	 void completarPedidoSolicitacao(PedidoSolicitacao pedidoSolicitacao);
	 
	 Long getTotalPedidosByMessages();

}
