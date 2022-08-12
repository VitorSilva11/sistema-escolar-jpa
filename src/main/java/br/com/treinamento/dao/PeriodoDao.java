package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Curso;
import br.com.treinamento.model.Periodo;

public class PeriodoDao extends DAO<Periodo>{
	
	private EntityManager em;
	
	
	public PeriodoDao(EntityManager em) {
		super(em);
		this.em = em;
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
