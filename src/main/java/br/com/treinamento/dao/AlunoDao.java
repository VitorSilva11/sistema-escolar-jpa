package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Aluno;
import br.com.treinamento.model.Unidade;

public class AlunoDao {
	
	private EntityManager em;
	
	
	public AlunoDao(EntityManager em) {
		this.em = em;
	}
	
	
	
	public void save(Aluno aluno) {
		
		try {
			
			em.getTransaction().begin();
			if(aluno.getId() != null) {
				em.persist(aluno);
				System.out.println("Atualizado com Sucesso");
			}else {
				em.persist(aluno);
				System.out.println("Salvo com sucesso!");
			}
			
			em.getTransaction().commit();
			
		}catch (Exception e) {
			System.out.println("Erro ao Salvar");
		}
		
	}
	
	public void remove(Aluno aluno) {
		
		try {
			em.getTransaction().begin();
			em.merge(aluno);
			em.remove(aluno);
			em.getTransaction().commit();
			
		}catch (Exception e) {
			System.out.println("Erro ao Remover");
		}

	}
	
	
	public List<Aluno> findAll() {
		String jpql = "SELECT a FROM Aluno a";
		return em.createQuery(jpql, Aluno.class).getResultList();
	}
	
	
	public Aluno findById(Long id) {
		
		String jpql = "SELECT a FROM Aluno a WHERE a.id = :id";
		return em.createQuery(jpql, Aluno.class).setParameter("id", id).getSingleResult();
		
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
