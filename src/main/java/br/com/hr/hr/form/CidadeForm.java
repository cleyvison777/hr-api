package br.com.hr.hr.form;

import br.com.hr.hr.model.Cidade;

public class CidadeForm {
	private String nome;


	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public  Cidade  converter() {
		return new Cidade(nome);
	}

}
