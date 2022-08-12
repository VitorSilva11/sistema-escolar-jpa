package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Aluno;
import br.com.treinamento.model.Unidade;

public class AlunoDao extends DAO<Aluno>{
	
	private EntityManager em;
	
	
	public AlunoDao(EntityManager em) {
		super(em);
		this.em = em;
	}
	
	
	public void projecaoAlunoUnidade(Aluno aluno) {
		String jpql = "SELECT a,u FROM Aluno a INNER JOIN a.unidades u WHERE a.id = :id";
		
		List<Object[]> lista = em.createQuery(jpql, Object[].class).setParameter("id", aluno.getId()).getResultList();
		
		lista.forEach(arr -> {
			String out = "\nNome: " + ((Aluno) arr[0]).getNome() + " - Matricula: " + ((Aluno) arr[0]).getMatriula();
			
			
			if(arr[1] == null) {
				out += ", NULL";
			}else {
				
				out += "\nBairro: " + ((Unidade)arr[1]).getBairro() + " - Estado: " + ((Unidade)arr[1]).getEstado();
			}
			
			System.out.println(out);
			
		});
		
	}

}
