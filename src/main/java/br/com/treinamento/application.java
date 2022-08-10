package br.com.treinamento;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.treinamento.dao.AlunoDao;
import br.com.treinamento.dao.CursoDao;
import br.com.treinamento.dao.DisciplinaDao;
import br.com.treinamento.dao.EmpresaDao;
import br.com.treinamento.dao.PeriodoDao;
import br.com.treinamento.dao.ProfessorDao;
import br.com.treinamento.dao.UnidadeDao;
import br.com.treinamento.model.Aluno;
import br.com.treinamento.model.Curso;
import br.com.treinamento.model.Disciplina;
import br.com.treinamento.model.Empresa;
import br.com.treinamento.model.Periodo;
import br.com.treinamento.model.Professor;
import br.com.treinamento.model.Unidade;
import br.com.treinamento.utils.JpaFactory;

public class application {

	public static void main(String[] args) {
		

		
		
		
	//Persistindo os dados
		EntityManager em = JpaFactory.getEntityManager();

		// Dao
		UnidadeDao unidadeDao = new UnidadeDao(em);
		AlunoDao alunoDao = new AlunoDao(em);
		CursoDao cursoDao = new CursoDao(em);
		PeriodoDao periodoDao = new PeriodoDao(em);
		DisciplinaDao disciplinaDao = new DisciplinaDao(em);
		EmpresaDao empresaDao = new EmpresaDao(em);
		ProfessorDao professorDao = new ProfessorDao(em);		
		
		
		
		
		
		
		
		
		unidadeDao.projecaoUnidadeCursos();
		

		em.close();
		JpaFactory.closeEntityManagerFactory();

	}

	public static void criarObejtos() {

		// Criando objetos
		Empresa empresa = new Empresa("UNIFACS", "32.459.768/0001-12", "unifacs@gmail.com");
		Unidade unidade = new Unidade("Rua Tancredo Neves", "stiep", "54770-012", "BA");

		Curso curso = new Curso("Ciência de dados", 2500);
		Disciplina disciplina1 = new Disciplina("Python");
		Disciplina disciplina2 = new Disciplina("Analise de dados");
		Disciplina disciplina3 = new Disciplina("Calculo1");
		Disciplina disciplina4 = new Disciplina("Calculo2");
		Disciplina disciplina5 = new Disciplina("Lógica de Programação");
		Disciplina disciplina6 = new Disciplina("Python Avançado");

		Periodo periodo1 = new Periodo("periodo1");
		Periodo periodo2 = new Periodo("periodo2");

		Aluno aluno1 = new Aluno("20225469", "Alana Nunes", "alana@gmail.com");
		Aluno aluno2 = new Aluno("20221645", "Fernanda Silva", "fernanda@gmail.com");
		Aluno aluno3 = new Aluno("20229865", "Célio Roberto", "celio@gmail.com");

		Professor professor1 = new Professor("9810235", "Ana Nunes", "ana@gmail.com");
		Professor professor2 = new Professor("9876542", "Yago Silva", "yago@gmail.com");
		Professor professor3 = new Professor("9883645", "Samara Silva", "samara@gmail.com");

		// Setando atributos importantes

		disciplina1.setPeriodo(periodo1);
		disciplina3.setPeriodo(periodo1);
		disciplina5.setPeriodo(periodo1);

		disciplina2.setPeriodo(periodo2);
		disciplina4.setPeriodo(periodo2);
		disciplina6.setPeriodo(periodo2);

		periodo1.setCurso(curso);
		periodo1.setDisciplinas(Arrays.asList(disciplina1, disciplina3, disciplina5));

		periodo2.setCurso(curso);
		periodo2.setDisciplinas(Arrays.asList(disciplina2, disciplina4, disciplina6));

		curso.setPeriodos(Arrays.asList(periodo1, periodo2));

		empresa.setUnidades(Arrays.asList(unidade));

		unidade.setAlunos(Arrays.asList(aluno1, aluno2, aluno3));
		unidade.setProfessores(Arrays.asList(professor1, professor2, professor3));
		unidade.setEmpresa(empresa);
		unidade.setCursos(Arrays.asList(curso));

	}

}
