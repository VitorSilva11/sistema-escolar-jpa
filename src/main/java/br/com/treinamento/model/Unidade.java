package br.com.treinamento.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Unidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false)
	private String bairro;
	
	
	@Column(nullable = false, length = 11)
	private String cep;
	
	@Column(nullable = false, length = 2)
	private String estado;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "unidade_aluno")
	private List<Aluno> alunos;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "unidade_professor")
	private List<Professor> professores;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "cursos_oferecidos")
	private List<Curso> cursos;
	
	
	public Unidade() {}
	

	public Unidade(String logradouro, String bairro, String cep, String estado) {
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cep = cep;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public List<Aluno> getAlunos() {
		return alunos;
	}


	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}


	public List<Professor> getProfessores() {
		return professores;
	}


	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}


	public List<Curso> getCursos() {
		return cursos;
	}


	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	
	
}
