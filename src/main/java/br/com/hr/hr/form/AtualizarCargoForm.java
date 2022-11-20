package br.com.hr.hr.form;

import javax.validation.constraints.NotNull;

import br.com.hr.hr.model.Cargo;
import br.com.hr.hr.repository.CargoRepository;

public class AtualizarCargoForm {
	@NotNull
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cargo atualiza(Long id, CargoRepository cargoRepository) {
		Cargo cargo = cargoRepository.getOne(id);
		cargo.setNome(nome);
		return cargo;
	}

}
