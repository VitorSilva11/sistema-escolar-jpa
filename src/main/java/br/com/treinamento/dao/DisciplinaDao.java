package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Disciplina;
import br.com.treinamento.model.Periodo;

public class DisciplinaDao {
	
	
	private EntityManager em;
	
	public DisciplinaDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void save(Disciplina disciplina) {
		
		try {
			em.getTransaction().begin();
			
			if(disciplina.getId() != null) {
				em.persist(disciplina);
				System.out.println("Alterado com sucesso");
			}else {
				em.persist(disciplina);
				System.out.println("Salvo Com Sucesso");
			}
			
			em.getTransaction().commit();
			
		}catch (Exception e) {
			System.out.println("erro ao salvar");
		}
		
	}
	
	public void remover(Disciplina disciplina) {
		
		try {
			em.getTransaction().begin();
			em.merge(disciplina);
			em.remove(disciplina);
			em.getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Erro ao remover");
		}

		
	}
	
	
	public List<Disciplina> findAll(){
		
		String jpql = "SELECT d FROM Disciplina d";
		return em.createQuery(jpql, Disciplina.class).getResultList();
	
	}
	
	public Disciplina findById(Long id) {
		
		String jpql = "SELECT d FROM Disciplina d WHERE d.id = :id";
		return em.createQuery(jpql, Disciplina.class).setParameter("id", id).getSingleResult();
	}
	

	public List<Disciplina> findByPeriodo(Periodo periodo){
		String jpql = "SELECT d FROM Disciplina d WHERE d.periodo.id = :id";
		return em.createQuery(jpql, Disciplina.class).setParameter("id", periodo.getId()).getResultList();
	}
	
	
	public void projecaoDisciplinaPorPeriodo(Periodo periodo) {
		
		List<Disciplina> lista = this.findByPeriodo(periodo);
		
		lista.forEach(d -> {
			System.out.println(d.getNome());
		});
		
	}
	
	
	
	
}
