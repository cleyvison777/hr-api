package br.com.hr.hr.dto;

import org.springframework.data.domain.Page;

import br.com.hr.hr.model.Bairro;

public class BairroDTO {

	private String cidade;
	private String bairro;

	public BairroDTO(Bairro bairro) {
		this.bairro = bairro.getNome();
		this.cidade = bairro.getCidade().getNome();
	}

	public String getCidade() {
		return cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public static Page<BairroDTO> converter(Page<Bairro> bairro) {
		return bairro.map(BairroDTO::new);
	}
	
	
}
