package br.edu.ctp.ads.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ctp.ads.entity.Carro;

public class CarroDao {

	public void inserir(Carro carro) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(carro);
		em.getTransaction().commit();
	}

	public Carro buscar(Integer id) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Carro carro = em.find(Carro.class, id);

		return (carro);
	}

	public ArrayList<Carro> listar() {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("from Carro");

		return new ArrayList<Carro>(q.getResultList());
	}

	public void alterar(Carro carro) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(carro);
		em.getTransaction().commit();
	}

	public void excluir(Integer id) {

		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		Carro carro = em.find(Carro.class, id);
		em.remove(carro);
		em.getTransaction().commit();
	}
}
