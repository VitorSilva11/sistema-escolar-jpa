package br.com.treinamento.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "qtd_horas_a_fazer")
	private Integer qtdHorasAFazer;
	
	
	
	@ManyToMany(mappedBy = "cursos")
	private List<Unidade> unidades;
	
	@OneToMany(mappedBy = "curso", cascade = CascadeType.PERSIST)
	private List<Periodo> periodos;
	
	
	
	public Curso() {}
	

	public Curso(String nome, Integer qtdHorasAFazer) {
		this.nome = nome;
		this.qtdHorasAFazer = qtdHorasAFazer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtdHorasAFazer() {
		return qtdHorasAFazer;
	}

	public void setQtdHorasAFazer(Integer qtdHorasAFazer) {
		this.qtdHorasAFazer = qtdHorasAFazer;
	}


	public List<Unidade> getUnidades() {
		return unidades;
	}


	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}


	public List<Periodo> getPeriodos() {
		return periodos;
	}


	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}


}
