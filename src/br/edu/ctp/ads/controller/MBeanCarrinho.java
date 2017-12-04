package br.edu.ctp.ads.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.edu.ctp.ads.dao.CarroDao;
import br.edu.ctp.ads.dao.PedidoDao;
import br.edu.ctp.ads.entity.Item;
import br.edu.ctp.ads.entity.Carro;
import br.edu.ctp.ads.entity.Pedido;
import br.edu.ctp.ads.entity.ClienteFisico;

@ManagedBean(name = "mBeanCarrinho")
@SessionScoped
public class MBeanCarrinho {

	private ArrayList<Item> itens = new ArrayList<Item>();

	public String salvarPedido() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		ClienteFisico u = (ClienteFisico) req.getSession().getAttribute("cliente");

		Pedido p = new Pedido();
		p.setData(new Date());
		p.setItens(itens);
		p.setCliente(u);

		for (Item i : itens) {
			i.setPedido(p);
		}

		new PedidoDao().inserir(p);

		return "";
	}

	public void adicionar(Integer idCarro) throws IOException {
		Carro carro = new CarroDao().buscar(idCarro);

		Item item = new Item();
		item.setCarro(carro);
		item.setQuantidade(1);

		itens.add(item);

		FacesContext.getCurrentInstance().getExternalContext().redirect("telaCarrinhoDeCompras.jsf");

	}

	public ArrayList<Item> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}

}
