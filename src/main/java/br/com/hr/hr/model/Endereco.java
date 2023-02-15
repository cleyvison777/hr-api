package br.com.hr.hr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private int numero;
	@ManyToOne
	@JoinColumn
	private Bairro bairro;
	@ManyToOne
	@JoinColumn
	private Cidade cidade;

	public Endereco() {

	}

	public Endereco(String rua, int numero, Long bairro, Long cidade) {
		this.rua = rua;
		this.numero = numero;
		this.bairro = new Bairro(bairro);
		this.cidade = new Cidade(cidade);
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Long bairro) {
		this.bairro = new Bairro(bairro);
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Long cidade) {
		this.cidade = new Cidade(cidade);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
