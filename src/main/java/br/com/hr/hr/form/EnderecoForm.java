package br.com.hr.hr.form;

import br.com.hr.hr.model.Endereco;

public class EnderecoForm {
	private String rua;
	private int numero;
	private Long bairro;
	private Long cidade;

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public void setBairro(Long bairro) {
		this.bairro = bairro;
	}

	public void setCidade(Long cidade) {
		this.cidade = cidade;
	}

	public Endereco converter() {
		return new Endereco(rua, numero, bairro, cidade);
	}

}
