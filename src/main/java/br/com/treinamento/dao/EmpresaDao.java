package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Empresa;
import br.com.treinamento.model.Unidade;

public class EmpresaDao extends DAO<Empresa>{
	
	private EntityManager em;
	
	public EmpresaDao(EntityManager em) {
		super(em);
		this.em = em;
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