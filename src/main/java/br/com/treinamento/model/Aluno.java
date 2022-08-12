package br.com.treinamento.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 20)
	private String matriula;


	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	
	@ManyToMany(mappedBy = "alunos")
	private List<Unidade> unidades;
	
	
	public Aluno() {}
	

	public Aluno(String matriula, String nome, String email) {
		this.matriula = matriula;
		this.nome = nome;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatriula() {
		return matriula;
	}

	public void setMatriula(String matriula) {
		this.matriula = matriula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public List<Unidade> getUnidades() {
		return unidades;
	}


	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}

	
	
}
