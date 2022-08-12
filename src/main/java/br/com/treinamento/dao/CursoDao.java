package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Curso;
import br.com.treinamento.model.Periodo;

public class CursoDao extends DAO<Curso>{

	private EntityManager em;

	public CursoDao(EntityManager em) {
		super(em);
		this.em = em;
	}

	public void projecaoCursoPeriodo() {

		String jpql = "SELECT c,p FROM Curso c INNER JOIN c.periodos p";

		List<Object[]> listaCurso = em.createQuery(jpql, Object[].class).getResultList();

		listaCurso.forEach(arr -> {
			String out = "Nome Curso: " + ((Curso) arr[0]).getNome();

			if (arr[1] == null) {
				out += ", NULL";
			} else {
				out += "\nPeridos: " + ((Periodo) arr[1]).getNome();
			}

			System.out.println(out);

		});
	}

}
