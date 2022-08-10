package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Professor;
import br.com.treinamento.model.Unidade;

public class ProfessorDao {

	
	private EntityManager em;
	
	public ProfessorDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void save(Professor professor) {
		try {
			
			em.getTransaction().begin();
			
			if(professor.getId() != null) {
				em.persist(professor);
				System.out.println("Alterado com Sucesso!");
			}
			else {
				em.persist(professor);
				System.out.println("Salvo Com Sucesso!");
			}

			
			em.getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Erro ao salvar");
		}
	}
	
	public void remove(Professor professor) {
		
		try {
			
			em.getTransaction().begin();
			
			em.merge(professor);
			em.persist(professor);
			
			em.getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Erro ao remover");
		}
		
		
	}
	
	
	public List<Professor> findAll(){
		String jpql = "SELECT p FROM Professor p";
		return em.createQuery(jpql, Professor.class).getResultList();
	}
	
	
	public Professor findById(Long id) {
		String jpql = "SELECT p FROM Professor p WHERE p.id = :id";
		return em.createQuery(jpql, Professor.class).setParameter("id", id).getSingleResult();
	}
		
	
	public void projecaoProfessorUnidade(Professor professor) {
		String jpql = "SELECT p,u FROM Professor p INNER JOIN p.unidade u WHERE p.id = :id";
		
		List<Object[]> lista = em.createQuery(jpql, Object[].class).setParameter("id", professor.getId()).getResultList();
		
		lista.forEach(arr -> {
			String out = "\nNome: " + ((Professor) arr[0]).getNome() + " - Matricula: " + ((Professor) arr[0]).getMatricula();
			
			
			if(arr[1] == null) {
				out += ", NULL";
			}else {
				
				out += "\nBairro: " + ((Unidade)arr[1]).getBairro() + " - Estado: " + ((Unidade)arr[1]).getEstado();
			}
			
			System.out.println(out);
			
		});
		
	}

	
}
