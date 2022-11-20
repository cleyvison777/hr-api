package br.com.hr.hr.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.hr.hr.model.Departamento;

public class DepartamentoForm {
	@NotNull
	@NotEmpty
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Departamento converter() {
		return new  Departamento(nome);
	}

}
