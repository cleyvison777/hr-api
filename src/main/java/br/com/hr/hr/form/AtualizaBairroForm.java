package br.com.hr.hr.form;

import javax.validation.constraints.NotNull;

import br.com.hr.hr.model.Bairro;
import br.com.hr.hr.repository.BairroRepository;

public class AtualizaBairroForm {

	@NotNull
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Bairro atuializa(Long id, BairroRepository repository) {
		Bairro bairro = repository.getOne(id);
		bairro.setNome(nome);
		return bairro;
	}
}
