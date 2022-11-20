package br.com.hr.hr.model;

import javax.persistence.Embeddable;

@Embeddable
public class DocumentacaoPessoal {
	private String cpf;
	private String rg;
	private String cateiraTrabalho;

	public DocumentacaoPessoal() {

	}

	public DocumentacaoPessoal(String cpf, String rg) {
		this.cpf = cpf;
		this.rg = rg;
		
	
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCateiraTrabalho() {
		return cateiraTrabalho;
	}

	public void setCateiraTrabalho(String cateiraTrabalho) {
		this.cateiraTrabalho = cateiraTrabalho;
	}

}
