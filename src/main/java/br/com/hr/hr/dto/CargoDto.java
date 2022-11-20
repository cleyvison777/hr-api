package br.com.hr.hr.dto;

import org.springframework.data.domain.Page;

import br.com.hr.hr.model.Cargo;

public class CargoDto {
	private String nome;

	public CargoDto(Cargo nome) {
		this.nome = nome.getNome();
	}

	public String getNome() {
		return nome;
	}

	public static Page<CargoDto> converter(Page<Cargo> cargo){
		return cargo.map(CargoDto::new);
	}

}
