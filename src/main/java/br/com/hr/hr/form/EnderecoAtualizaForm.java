package br.com.hr.hr.form;

import javax.validation.constraints.NotNull;

import br.com.hr.hr.model.Endereco;
import br.com.hr.hr.repository.EnderecoRepository;

public class EnderecoAtualizaForm {
	@NotNull
	private String rua;
	@NotNull
	private int numero;
	@NotNull
	private Long bairro;
	@NotNull
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

	public Endereco atualiza(Long id, EnderecoRepository enderecoRepository) {
		Endereco endereco = enderecoRepository.getOne(id);
		endereco.setRua(rua);
		endereco.setNumero(numero);
		endereco.setBairro(bairro);
		endereco.setCidade(cidade);
		return endereco;
	}

}
