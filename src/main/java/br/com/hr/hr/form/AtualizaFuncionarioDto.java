package br.com.hr.hr.form;

import javax.validation.constraints.NotNull;

import br.com.hr.hr.model.Pessoa;
import br.com.hr.hr.repository.FuncionarioRepository;

public class AtualizaFuncionarioDto {
	@NotNull
	private String nome;
	@NotNull
	private String cpf;
	@NotNull
	private String rg;
	@NotNull
	private Long departamento;
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

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setDepartamento(Long departamento) {
		this.departamento = departamento;
	}

	public void setCargo(Long cargo) {
		this.cargo = cargo;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Pessoa atualizar(Long id, FuncionarioRepository funcionarioRepository) {
		Pessoa funcionario = funcionarioRepository.getOne(id);
		funcionario.setNome(nome);
		funcionario.setIdDepartamento(departamento);
		funcionario.setCargo(cargo);
		funcionario.setCpf(cpf);
		funcionario.setRg(rg);
		funcionario.setSalario(salario);
		funcionario.setCategoria(categoria);

		return funcionario;
	}

}
