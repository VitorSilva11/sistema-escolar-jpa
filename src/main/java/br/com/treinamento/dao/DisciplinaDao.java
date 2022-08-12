package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Disciplina;
import br.com.treinamento.model.Periodo;

public class DisciplinaDao extends DAO<Disciplina>{
	
	
	private EntityManager em;
	
	public DisciplinaDao(EntityManager em) {
		super(em);
		this.em = em;
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
