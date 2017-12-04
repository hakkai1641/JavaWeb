package br.edu.ctp.ads.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.catalina.core.ApplicationPart;

import br.edu.ctp.ads.dao.CarroDao;
import br.edu.ctp.ads.entity.Carro;

@ManagedBean(name = "mBeanCar")
public class MBeanCarro {

	private static ArrayList<Carro> carros = new ArrayList<Carro>();

	private Integer id;
	private String marca;
	private String modelo;
	private String Cor;
	private Integer ano;
	private String descricao;
	private String caminhoImagem;
	private BigDecimal valor;

	private ApplicationPart imagem;

	@PostConstruct
	public void carregar() {
		carros = new CarroDao().listar();
	}

	public void Salvar() {

		if (imagem != null && !imagem.getSubmittedFileName().equals("")) {
			try {
				caminhoImagem = "C:\\Users\\Diego Patrick\\Pictures\\javaweb" + imagem.getSubmittedFileName();

				// cria um espaço de memória que vai armazenar o conteúdo da imagem
				byte[] bytesImagem = new byte[(int) imagem.getSize()];
				// lê o conteudo da imagem e joga dentro do array de bytes
				imagem.getInputStream().read(bytesImagem);
				// cria uma referência para o arquivo que será criado no lado do server
				File f = new File(caminhoImagem);
				// cria o objeto que irá manipular o arquivo criado
				FileOutputStream fos = new FileOutputStream(f);
				// escreve o conteúdo da imagem (upload) dentro do arquivo no servidor
				fos.write(bytesImagem);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		Carro carro = new Carro();
		carro.setId(this.id);
		carro.setMarca(marca);
		carro.setModelo(modelo);
		carro.setAno(ano);
		carro.setCor(Cor);
		carro.setDescricao(descricao);
		carro.setCaminhoImagem(caminhoImagem);
		carro.setValor(valor);

		if (this.id == null) {
			new CarroDao().inserir(carro);
		} else {
			new CarroDao().alterar(carro);
		}

		carros = new CarroDao().listar();
	}

	public void buscar() {
		Carro carro = new CarroDao().buscar(id);
		this.id = carro.getId();
		this.marca = carro.getMarca();
		this.modelo = carro.getModelo();
		this.Cor = carro.getCor();
		this.ano = carro.getAno();
		this.descricao = carro.getDescricao();
		this.caminhoImagem = carro.getCaminhoImagem();
		this.valor = carro.getValor();
	}

	public void alterar(Carro carro) {
		this.id = carro.getId();
		this.marca = carro.getMarca();
		this.modelo = carro.getModelo();
		this.Cor = carro.getCor();
		this.ano = carro.getAno();
		this.descricao = carro.getDescricao();
		this.caminhoImagem = carro.getCaminhoImagem();
		this.valor = carro.getValor();
	}

	public String carregarCarro(Carro carro) {
		this.id = carro.getId();
		this.marca = carro.getMarca();
		this.modelo = carro.getModelo();
		this.Cor = carro.getCor();
		this.ano = carro.getAno();
		this.descricao = carro.getDescricao();
		this.caminhoImagem = carro.getCaminhoImagem();
		this.valor = carro.getValor();

		return "detalhesCarro.jsf";
	}

	public void excluir(Carro carro) {
		new CarroDao().excluir(carro.getId());
		carros = new CarroDao().listar();
	}

	public ArrayList<Carro> getCarros() {
		return carros;
	}

	public void setCarros(ArrayList<Carro> carros) {
		MBeanCarro.carros = carros;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return Cor;
	}

	public void setCor(String cor) {
		Cor = cor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ApplicationPart getImagem() {
		return imagem;
	}

	public void setImagem(ApplicationPart imagem) {
		this.imagem = imagem;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
