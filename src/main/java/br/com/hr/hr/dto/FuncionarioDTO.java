package br.com.hr.hr.dto;

import org.springframework.data.domain.Page;

import br.com.hr.hr.model.Pessoa;

public class FuncionarioDTO {
	private String nome;
	private String cpf;
	private String rg;
	private String departamento;
	private String cargo;
	private String categoria;
	private double salario;

	public FuncionarioDTO(Pessoa funcionario) {
		this.nome = funcionario.getNome();
		this.cpf = funcionario.getDocumentacaoPessoal().getCpf();
		this.rg = funcionario.getDocumentacaoPessoal().getRg();
		this.departamento = funcionario.getDepartamento().getNome();
		this.cargo = funcionario.getCargo().getNome();
		this.categoria = funcionario.getCategoria().getNome();
		this.salario = funcionario.getSalario();
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getDepartamento() {
		return departamento;
	}

	public double getSalario() {
		return salario;
	}

	public String getRg() {
		return rg;
	}

	public String getCargo() {
		return cargo;
	}

	public String getCategoria() {
		return categoria;
	}

	public static Page<FuncionarioDTO> converter(Page<Pessoa> funcioanrio) {
		return funcioanrio.map(FuncionarioDTO::new);
	}

}
