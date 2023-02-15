package br.com.hr.hr.dto;

import org.springframework.data.domain.Page;

import br.com.hr.hr.model.Endereco;

public class EnderecoDto {
	private String rua;
	private int numero;
	private String bairro;
	private String cidade;

	public EnderecoDto(Endereco endereco) {
		this.rua = endereco.getRua();
		this.numero = endereco.getNumero();
		this.bairro = endereco.getBairro().getNome();
		this.cidade = endereco.getCidade().getNome();
	}

	public String getRua() {
		return rua;
	}

	public int getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public static Page<EnderecoDto> converter(Page<Endereco> endereco) {

		return endereco.map(EnderecoDto::new);
	}

}
