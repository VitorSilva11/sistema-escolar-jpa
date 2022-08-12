package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Professor;
import br.com.treinamento.model.Unidade;

public class ProfessorDao extends DAO<Professor>{

	
	private EntityManager em;
	
	public ProfessorDao(EntityManager em) {
		super(em);
		this.em = em;
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
