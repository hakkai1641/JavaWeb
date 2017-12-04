package br.edu.ctp.ads.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.edu.ctp.ads.entity.ClienteFisico;


public class ClienteFisicoDao {
	
	public ClienteFisico logar(String email, String senha) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("select u from ClienteFisico u " + "where u.email = :email and u.senha = :senha");
		q.setParameter("email", email);
		q.setParameter("senha", senha);

		try {
			return (ClienteFisico) q.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	public void inserir(ClienteFisico cliente) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
	}

	public ClienteFisico buscar(String cpf) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("select c from ClienteFisico c where c.cpf = ?");
		q.setParameter(1, cpf);

		return (ClienteFisico) q.getSingleResult();
	}

	public ArrayList<ClienteFisico> listar() {
		EntityManager em = Conexao.getInstance().createEntityManager();
		Query q = em.createQuery("from ClienteFisico");

		return new ArrayList<ClienteFisico>(q.getResultList());
	}

	public void alterar(ClienteFisico cliente) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
	}

	public void excluir(Integer id) {
		EntityManager em = Conexao.getInstance().createEntityManager();
		em.getTransaction().begin();
		ClienteFisico cliente = em.find(ClienteFisico.class, id);		
		em.remove(cliente);
		em.getTransaction().commit();

	}

}
