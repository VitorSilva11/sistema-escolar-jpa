package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class DAO<T> {

	private EntityManager em;

	private Class<T> classe;

	public DAO(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}
	
	public DAO(EntityManager em) {
		this.em = em;
	}

	public void save(T t) {

		try {

			em.getTransaction().begin();

			em.persist(t);
			System.out.println("Salvo com sucesso!");

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro ao Salvar");
		}

	}
	
	public void update(T t) {

		try {

			em.getTransaction().begin();
			em.merge(t);
			em.persist(t);
			System.out.println("Atualizado com sucesso!");

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro ao Atualizar!!");
		}

	}
	
	
	public void remove(T t) {
		
		try {
			em.getTransaction().begin();
			em.merge(t);
			em.remove(t);
			em.getTransaction().commit();
			
		}catch (Exception e) {
			System.out.println("Erro ao Remover");
		}

	}
	
	
	public List<T> findAll() {
		String jpql = "SELECT t FROM " + this.classe.getName() + " t";
		return em.createQuery(jpql, this.classe).getResultList();
	}
	
	public T findById(Long id) {
		String jpql = "SELECT t FROM " + this.classe.getName() + " t WHERE t.id = :id";
		return em.createQuery(jpql, this.classe).setParameter("id", id).getSingleResult();
	}

}
