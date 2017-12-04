package br.edu.ctp.ads.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.core.ApplicationPart;

import br.edu.ctp.ads.dao.ClienteFisicoDao;

import br.edu.ctp.ads.entity.ClienteFisico;


@ManagedBean(name = "mBeanCPF")
public class MBeanCPF {

	private static ArrayList<ClienteFisico> clientes = new ArrayList<ClienteFisico>();

	private Integer id;
	private String nome;
	private String sobrenome;
	private String cidade;
	private String estado;
	private String cep;
	private String cpf;
	private String cpfBusca;
	private String email;
	private String senha;

	private ApplicationPart imagem;

	@PostConstruct
	public void carregar() {
		clientes = new ClienteFisicoDao().listar();
	}

	public void Salvar() {

		ClienteFisico cliente = new ClienteFisico();
		cliente.setId(this.id);
		cliente.setNome(nome);
		cliente.setSobrenome(sobrenome);
		cliente.setCidade(cidade);
		cliente.setEstado(estado);
		cliente.setCep(cep);
		cliente.setCpf(cpf);
		cliente.setEmail(email);
		cliente.setSenha(senha);

		if (this.id == null) {
			new ClienteFisicoDao().inserir(cliente);
		} else {
			new ClienteFisicoDao().alterar(cliente);
		}

		clientes = new ClienteFisicoDao().listar();
	}

	public void buscar() {
		ClienteFisico cliente = new ClienteFisicoDao().buscar(cpfBusca);
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.cidade = cliente.getCidade();
		this.estado = cliente.getEstado();
		this.cep = cliente.getCep();
		this.cpf = cliente.getCpf();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
	}

	public void alterar(ClienteFisico cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.cidade = cliente.getCidade();
		this.estado = cliente.getEstado();
		this.cep = cliente.getCep();
		this.cpf = cliente.getCpf();
		this.email = cliente.getEmail();
		this.senha = cliente.getSenha();
	}

	public void excluir(ClienteFisico cliente) {
		new ClienteFisicoDao().excluir(cliente.getId());
	}
	
	public String login() {

		ClienteFisico cliente = new ClienteFisicoDao().logar(email, senha);

		if (cliente == null) {
			FacesContext.getCurrentInstance().addMessage("",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos!", ""));

			return "";
		}

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		req.getSession().setAttribute("cliente", cliente);

		return "" + req.getSession().getAttribute("pagina");
	}

	public ArrayList<ClienteFisico> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<ClienteFisico> clientes) {
		MBeanCPF.clientes = clientes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpfBusca() {
		return cpfBusca;
	}

	public void setCpfBusca(String cpfBusca) {
		this.cpfBusca = cpfBusca;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ApplicationPart getImagem() {
		return imagem;
	}

	public void setImagem(ApplicationPart imagem) {
		this.imagem = imagem;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
