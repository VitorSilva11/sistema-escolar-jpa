package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Empresa;
import br.com.treinamento.model.Unidade;

public class EmpresaDao {
	
	private EntityManager em;
	
	public EmpresaDao(EntityManager em) {
		this.em = em;
	}
	
	
	public void save(Empresa empresa) {
		
		try {
			
			em.getTransaction().begin();
			
			if(empresa.getId() != null) {
				em.persist(empresa);
				System.out.println("Alterado com Sucesso!");
			}
			else {
				em.persist(empresa);
				System.out.println("Salvo Com Sucesso!");
			}
			

			
			em.getTransaction().commit();
			
		}catch (Exception e) {
			System.out.println("Erro ao salvar");
		}
		
	}
	
	
	public void remove(Empresa empresa) {
		
		try {
			
			em.getTransaction().begin();
			em.merge(empresa);
			em.getTransaction().commit();
			
			
		}catch (Exception e) {
			System.out.println("Erro ao remover");
		}
		
		
	}
	
	
	public List<Empresa> findAll(){
		
		String jpql = "SELECT e FROM Empresa e";
		return em.createQuery(jpql, Empresa.class).getResultList();
		
	}
	
	
	public Empresa findById(Long id) {
		String jpql = "SELECT e FROM Empresa e WHERE e.id = :id";
		return em.createQuery(jpql, Empresa.class).setParameter("id", id).getSingleResult();
		
	}
	
	
	public void projecaoEmpresaUnidade() {
		String jpql = "SELECT e,u from Empresa e INNER JOIN e.unidades u";
		
		List<Object[]> lista = em.createQuery(jpql, Object[].class).getResultList();
		
		lista.forEach(arr -> {
			String out = "\nNome: " + ((Empresa) arr[0]).getNome() + " - Email: " + ((Empresa) arr[0]).getEmail();
			
			
			if(arr[1] == null) {
				out += ", NULL";
			}else {
				
				out += "\nBairro: " + ((Unidade)arr[1]).getBairro() + " - Estado: " + ((Unidade)arr[1]).getEstado();
			}
			
			System.out.println(out);
			
		});
		
	}

}
