package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Curso;
import br.com.treinamento.model.Periodo;

public class PeriodoDao {
	
	private EntityManager em;
	
	
	public PeriodoDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void save(Periodo periodo) {
try {
			
			em.getTransaction().begin();
			if(periodo.getId() != null) {
				em.persist(periodo);
				System.out.println("Atualizado com Sucesso");
			}else {
				em.persist(periodo);
				System.out.println("Salvo com sucesso!");
			}
			
			em.getTransaction().commit();
			
		}catch (Exception e) {
			System.out.println("Erro ao Salvar");
		}
	}
	
	public void remove(Periodo periodo) {
		try {
			em.getTransaction().begin();
			
			em.merge(periodo);
			em.remove(periodo);
			
			em.getTransaction().commit();
			
		}catch (Exception e) {
			System.out.println("Erro ao remover");
		}
	
	}
	
	public Periodo update(Long id) {
		return em.find(Periodo.class, id);
	}
	
	
	public List<Periodo> findAll(){
		
		String jpql = "SELECT p FROM Periodo p";
		
		return em.createQuery(jpql, Periodo.class).getResultList();
		
	}
	
	
	public Periodo findById(Long id) {
		
		String jpql = "SELECT p FROM Periodo p WHERE p.id = :id";
		return em.createQuery(jpql, Periodo.class).setParameter("id", id).getSingleResult();
	}
	
	public List<Periodo> findByCurso(Curso curso){
		
		String jpql = "SELECT p FROM Periodo p WHERE p.curso.id = :id";
		return em.createQuery(jpql, Periodo.class).setParameter("id", curso.getId()).getResultList();
		
	}
	
	
	
	public void projecaoPeriodoCurso(Curso curso) {
		
		List<Periodo> lista = this.findByCurso(curso);
		
		lista.forEach(p -> {
			System.out.println(p.getNome());
		});
		
	}

}
