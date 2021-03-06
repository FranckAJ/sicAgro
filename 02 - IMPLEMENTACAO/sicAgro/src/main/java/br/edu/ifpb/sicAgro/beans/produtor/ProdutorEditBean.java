package br.edu.ifpb.sicAgro.beans.produtor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.sicAgro.enumerations.CivelState;
import br.edu.ifpb.sicAgro.enumerations.ProdutoType;
import br.edu.ifpb.sicAgro.enumerations.UserRole;
import br.edu.ifpb.sicAgro.exceptions.SicAgroException;
import br.edu.ifpb.sicAgro.exceptions.SicAgroExceptionHandler;
import br.edu.ifpb.sicAgro.model.Conta;
import br.edu.ifpb.sicAgro.model.Endereco;
import br.edu.ifpb.sicAgro.model.Produtor;
import br.edu.ifpb.sicAgro.services.ContaService;
import br.edu.ifpb.sicAgro.services.EnderecoService;
import br.edu.ifpb.sicAgro.services.ProdutorService;
import br.edu.ifpb.sicAgro.util.jsf.JSFUtils;
import br.edu.ifpb.sicAgro.util.messages.MessageUtils;

/**
 * Bean responsável por gerenciar a criação e edição de um produtor.
 * 
 * @author <a href="https://github.com/FranckAJ">Franck Aragão</a>
 *
 */
@Named
@ViewScoped
public class ProdutorEditBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutorService produtorService;
	
	@Inject
	private EnderecoService enderecoService;

	@Inject
	private ContaService contaService;
	
	private List<ProdutoType> produtoTypes = new ArrayList<ProdutoType>();
	private List<CivelState> civelStates = new ArrayList<>();

	private Produtor produtor;

	public void preRenderView() {
		if (produtor == null) {
			produtor = new Produtor();
			produtor.setEndereco(new Endereco());
		}else{
			this.loadCities();
		}

	}

	@PostConstruct
	public void init() {
		produtoTypes = Arrays.asList(ProdutoType.values());
		civelStates = Arrays.asList(CivelState.values());
	}

	public void save() throws SicAgroException {
		

		if (isProdutorEdited()) {
			produtorService.update(produtor);
			MessageUtils.messageSucess("Produtor atualizado com sucesso.");

		} else {
			produtor.setAcount(createDefaultAcount());
			produtorService.add(produtor);
			MessageUtils.messageSucess("Produtor salvo com sucesso.");
		}
		JSFUtils.rederTo("produtorView.xhtml");
		JSFUtils.setParam("produtorToDetail", produtor);
	}
	
	/**
	 * Cria conta default para produtor.
	 * 
	 * @return conta padrão
	 * @throws SicAgroExceptionHandler
	 */
	private Conta createDefaultAcount() throws SicAgroExceptionHandler{
		Conta conta = new Conta();
		conta.setUserName(produtor.getCpf());
		conta.setPassword(produtor.getCpf());
		conta.setMail("seuemail@mail.com");
		conta.setUserRole(UserRole.PRODUTOR);
		conta.setProdutor(produtor);
		contaService.criptografarSenha(conta);
		return conta;
	}

	public boolean isProdutorEdited() {
		return produtor.getId() != null;
	}
	
	/**
	 * Carrega lista de cidades de acordo com o estado escolhido.
	 * 
	 */
	public void loadCities(){
		if(produtor.getEndereco().getState() != null)
			enderecoService.getCities(produtor.getEndereco().getState(), produtor.getEndereco().getState().getCodigo());
	}
	
	/**
	 * Método auxiliar utilizado para resolver o problema do auto preenchimento
	 * de campos de endereço, caso um endereço digitado pelo usuário já exista.
	 * Já que usando o autocomplete com para o campo endereço, quando não
	 * existir um endereço já cadastrado seja possível continuar o cadastro
	 * 
	 * @param queryAddress
	 */
	public void updateCamposEnderecos(String queryAddress) {
		List<Endereco> address = enderecoService.findByAddress(queryAddress);
		if(address != null){
			this.produtor.setEndereco(address.get(0));
			this.produtor.getEndereco().setId(null);
		}
	}

	public Produtor getProdutor() {
		return produtor;
	}

	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}

	public List<ProdutoType> getProdutoTypes() {
		return produtoTypes;
	}

	public List<CivelState> getCivelStates() {
		return civelStates;
	}
}
