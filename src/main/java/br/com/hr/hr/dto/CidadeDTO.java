package br.com.hr.hr.dto;

import org.springframework.data.domain.Page;

import br.com.hr.hr.model.Cidade;

public class CidadeDTO {
	private String nome;

	
	public CidadeDTO(Cidade cidade) {
		this.nome = cidade.getNome();
	}


	public String getNome() {
		return nome;
	}
	
	public static Page<CidadeDTO> converter(Page<Cidade> cidade ){
		return cidade.map(CidadeDTO::new);
	}
 }
