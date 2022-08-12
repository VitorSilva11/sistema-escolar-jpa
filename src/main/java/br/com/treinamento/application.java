package br.com.treinamento;

import javax.persistence.EntityManager;

import br.com.treinamento.utils.JpaFactory;

public class Application {

	public static void main(String[] args) {		
				
	//Persistindo os dados
		EntityManager em = JpaFactory.getEntityManager();

		/*
		 * // Dao UnidadeDao unidadeDao = new UnidadeDao(em); AlunoDao alunoDao = new
		 * AlunoDao(em); CursoDao cursoDao = new CursoDao(em); PeriodoDao periodoDao =
		 * new PeriodoDao(em); DisciplinaDao disciplinaDao = new DisciplinaDao(em);
		 * EmpresaDao empresaDao = new EmpresaDao(em); ProfessorDao professorDao = new
		 * ProfessorDao(em);
		 */
		/*
		 * Periodo periodo = periodoDao.findById(1L);
		 * 
		 * disciplinaDao.projecaoDisciplinaPorPeriodo(periodo);
		 */
		
		
		

		em.close();
		JpaFactory.closeEntityManagerFactory();

	}

	public static void criarObejtos() {



	}

}
