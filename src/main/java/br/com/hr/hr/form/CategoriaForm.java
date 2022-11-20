package br.com.hr.hr.form;

import br.com.hr.hr.model.Categoria;

public class CategoriaForm {
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria converter() {
		return new Categoria(nome);
	}
}
