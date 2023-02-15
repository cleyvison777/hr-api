package br.com.hr.hr.form;

import javax.validation.constraints.NotNull;

import br.com.hr.hr.model.Cidade;
import br.com.hr.hr.repository.CidadeRepository;

public class CidadeAtualizaForm {

	@NotNull
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cidade atualiza(CidadeRepository cidadeRepository, Long id) {
		Cidade cidade = cidadeRepository.getOne(id);
		cidade.setNome(nome);
		return cidade;

	}

}
