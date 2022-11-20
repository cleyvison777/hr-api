package br.com.hr.hr.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.hr.hr.model.Categoria;
import br.com.hr.hr.repository.CategoriaRepository;

public class CategoriaAtualizaForm {
	@NotNull
	@NotEmpty
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria atualizar(Long id, CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.getById(id);
		categoria.setNome(nome);
		return categoria;
	}

}
