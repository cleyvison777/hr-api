package br.com.hr.hr.form;

import br.com.hr.hr.model.Cargo;

public class CargoForm {
	
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Cargo converter() {
		return new Cargo(nome);
	}

}
