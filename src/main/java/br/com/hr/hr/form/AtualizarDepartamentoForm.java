package br.com.hr.hr.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.hr.hr.model.Departamento;
import br.com.hr.hr.repository.DepartamentoRepository;

public class AtualizarDepartamentoForm {

	@NotNull
	@NotEmpty
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento atualizar(Long idDepartamento, DepartamentoRepository departamentoRepository) {
		Departamento departamento = departamentoRepository.getOne(idDepartamento);
		departamento.setNome(nome);
		return departamento;
	}

}
