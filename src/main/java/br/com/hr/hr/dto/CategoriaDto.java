package br.com.hr.hr.dto;

import org.springframework.data.domain.Page;

import br.com.hr.hr.model.Categoria;

public class CategoriaDto {
	private String nome;

	public CategoriaDto(Categoria categoria) {
		this.nome = categoria.getNome();
	}

	public String getNome() {
		return nome;
	}

	public static Page<CategoriaDto> converter(Page<Categoria> categoria) {
		return categoria.map(CategoriaDto::new);
	}
}
