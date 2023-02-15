package br.com.hr.hr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bairro")
public class Bairro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@JoinColumn
	@ManyToOne
	private Cidade cidade;

	public Bairro() {

	}

	public Bairro(Long id) {
		this.id = id;
	}

	public Bairro(String nome, Long cidade) {
		this.nome = nome;
		this.cidade = new Cidade(cidade);

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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Long cidade) {
		this.cidade = new Cidade(cidade);
	}

}
