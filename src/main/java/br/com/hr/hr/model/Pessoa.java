package br.com.hr.hr.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "funcionario")
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Long id;
	@NotNull
	private String nome;
	@NotNull
	private double salario;
	@Embedded
	private DocumentacaoPessoal documentacaoPessoal;
	@ManyToOne
	@JoinColumn
	private Departamento departamento;
	@ManyToOne
	@JoinColumn
	private Cargo cargo;
	@ManyToOne
	@JoinColumn
	private Categoria categoria;

	public Pessoa() {

	}

	public Pessoa(String nome, String cpf, double salario, String rg, Long departamento, Long cargo, Long categoria) {
		this.nome = nome;
		this.salario = getBonificacao(salario, categoria);
		this.documentacaoPessoal = new DocumentacaoPessoal(cpf, rg);
		this.departamento = new Departamento(departamento);
		this.cargo = new Cargo(cargo);
		this.categoria = new Categoria(categoria);
	}

	public Cargo getCargo() {
		return cargo;
	}

	// Aumento de o salario do funcionario por categoria
	public double getBonificacao(double salario, Long categoria) {
		switch (categoria.toString()) {
		case "2":
			return this.salario = salario + (salario * 0.1);
		case "3":
			return this.salario = salario + (salario * 0.2);
		default:
			System.out.println("Funcionario sem categoria para Bonus salarial");
		}
		return this.salario;

	}

	public void setCargo(Long cargo) {
		this.cargo = new Cargo(cargo);
	}

	public java.lang.Object getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public DocumentacaoPessoal getDocumentacaoPessoal() {
		return documentacaoPessoal;
	}

	public void setRg(String rg) {
		this.documentacaoPessoal.setRg(rg);
	}

	public void setCpf(String cpf) {
		this.documentacaoPessoal.setCpf(cpf);
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setIdDepartamento(Long departamento) {
		this.departamento = new Departamento(departamento);
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Long categoria) {
		this.categoria = new Categoria(categoria);
	}

}
