package br.com.hr.hr.form;

import javax.validation.constraints.NotNull;

import br.com.hr.hr.model.Bairro;

public class BairroForm {
	@NotNull
	private String nome;
	@NotNull
	private Long cidade;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCidade(Long cidade) {
		this.cidade = cidade;
	}
	
	public Bairro converter() {
		return new Bairro(nome, cidade);
	}

}
