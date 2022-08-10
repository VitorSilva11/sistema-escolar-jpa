package br.com.treinamento.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.model.Curso;
import br.com.treinamento.model.Periodo;

public class CursoDao {

	private EntityManager em;

	public CursoDao(EntityManager em) {
		this.em = em;
	}

	public void save(Curso curso) {
		try {

			em.getTransaction().begin();
			if (curso.getId() != null) {
				em.persist(curso);
				System.out.println("Atualizado com Sucesso");
			} else {
				em.persist(curso);
				System.out.println("Salvo com sucesso!");
			}

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro ao Salvar");
		}
	}

	public void remove(Curso curso) {

		try {
			em.getTransaction().begin();

			em.merge(curso);
			em.remove(curso);

			em.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Erro ao remover");
		}

	}

	public List<Curso> findAll() {
		String jpql = "SELECT c FROM Curso c";
		return em.createQuery(jpql, Curso.class).getResultList();
	}

	public Curso findById(Long id) {
		String jpql = "SELECT c FROM Curso c WHERE c.id = :id";
		return em.createQuery(jpql, Curso.class).setParameter("id", id).getSingleResult();

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
