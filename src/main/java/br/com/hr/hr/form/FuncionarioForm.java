package br.com.hr.hr.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.hr.hr.model.Pessoa;

public class FuncionarioForm {

	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String cpf;
	@NotNull
	private Long departamento;
	@NotNull
	private String rg;
	@NotNull
	private double salario;
	@NotNull
	private Long cargo;
	@NotNull
	private Long categoria;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setDepartamento(Long departamento) {
		this.departamento = departamento;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	public void setCargo(long cargo) {
		this.cargo = cargo;
	}

	public Pessoa converter() {
		return new Pessoa(nome, cpf, salario, rg, departamento, cargo, categoria);
	}

}
