package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Aluno;
import br.com.treinamento.model.Curso;
import br.com.treinamento.model.Empresa;
import br.com.treinamento.model.Professor;
import br.com.treinamento.model.Unidade;

public class UnidadeDao {
	
	private EntityManager em;
	
	public UnidadeDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void save(Unidade unidade) {
		try {
			
			em.getTransaction().begin();
			
			if(unidade.getId() != null) {
				em.persist(unidade);
				System.out.println("Alterado com Sucesso!");
			}
			else {
				em.persist(unidade);
				System.out.println("Salvo Com Sucesso!");
			}

			
			em.getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Erro ao salvar");
		}
	}
	
	public void remove(Unidade unidade) {
		try {
			
			em.getTransaction().begin();
			
			em.merge(unidade);
			em.persist(unidade);
			
			em.getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Erro ao remover");
		}
		
	}
	
	
	public Unidade update(Long id) {
		
		return em.find(Unidade.class, id);
		
	}
	
	
	public List<Unidade> findAll(){
		String jpql = "SELECT u FROM Unidade u";
		return em.createQuery(jpql, Unidade.class).getResultList();
	}
	
	public Unidade findById(Long id) {
		String jpql = "SELECT u FROM Unidade u WHERE u.id = :id";
		return em.createQuery(jpql, Unidade.class).setParameter("id", id).getSingleResult();
	}
	
	
	public void projecaoUnidadeEmpresa() {
		String jpql = "SELECT u,e from Unidade u RIGHT JOIN u.empresa e";
		
		List<Object[]> lista = em.createQuery(jpql, Object[].class).getResultList();
		
		lista.forEach(arr -> {
			String out = "Bairro: " + ((Unidade)arr[0]).getBairro() + " - Estado: " + ((Unidade)arr[0]).getEstado();
			
			if(arr[1] == null) {
				out += ", NULL";
			}else {
				
				out += "\nNome: " + ((Empresa) arr[1]).getNome() + " - Email: " + ((Empresa) arr[1]).getEmail();
			}
			
			System.out.println(out);
			
		});
		
	}
	
	
	public void projecaoUnidadeAlunos() {
		String jpql = "SELECT u,a from Unidade u INNER JOIN u.alunos a ";
		
		List<Object[]> lista = em.createQuery(jpql, Object[].class).getResultList();
		
		lista.forEach(arr -> {
			String out = "\nNome: " + ((Aluno) arr[1]).getNome() + " - Matricula: " + ((Aluno) arr[1]).getMatriula();
			
			if(arr[1] == null) {
				out += ", NULL";
			}else {
				
				out += "\nBairro: " + ((Unidade)arr[0]).getBairro() + " - Estado: " + ((Unidade)arr[0]).getEstado();
			}
			
			System.out.println(out);			
		});
		
	}
	
	
	public void projecaoUnidadeProfessores() {
		String jpql = "SELECT u,p from Unidade u INNER JOIN u.professores p ";
		
		List<Object[]> lista = em.createQuery(jpql, Object[].class).getResultList();
		
		lista.forEach(arr -> {
			String out = "\nNome: " + ((Professor) arr[1]).getNome() + " - Matricula: " + ((Professor) arr[1]).getMatricula();
			
			if(arr[1] == null) {
				out += ", NULL";
			}else {
				
				out += "\nBairro: " + ((Unidade)arr[0]).getBairro() + " - Estado: " + ((Unidade)arr[0]).getEstado();
			}
			
			System.out.println(out);			
		});
		
	}
	
	
	public void projecaoUnidadeCursos() {
		String jpql = "SELECT u,c from Unidade u INNER JOIN u.cursos c ";
		
		List<Object[]> lista = em.createQuery(jpql, Object[].class).getResultList();
		
		lista.forEach(arr -> {
			String out = "\nNome: " + ((Curso) arr[1]).getNome() + " - Quantidade De Horas: : " + ((Curso) arr[1]).getQtdHorasAFazer();
			
			if(arr[1] == null) {
				out += ", NULL";
			}else {
				
				out += "\nBairro: " + ((Unidade)arr[0]).getBairro() + " - Estado: " + ((Unidade)arr[0]).getEstado();
			}
			
			System.out.println(out);			
		});
		
	}

}
