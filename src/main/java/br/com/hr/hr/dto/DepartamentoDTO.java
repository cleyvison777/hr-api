package br.com.hr.hr.dto;

import org.springframework.data.domain.Page;

import br.com.hr.hr.model.Departamento;

public class DepartamentoDTO {
	private String nome;

	public DepartamentoDTO(Departamento departamento) {
		this.nome = departamento.getNome();
	}

	public String getNome() {
		return nome;
	}
	
	public static Page<DepartamentoDTO> converter(Page<Departamento> departamento){
		return departamento.map(DepartamentoDTO::new);
		
	}

}
