package br.com.hr.hr.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.hr.hr.dto.DepartamentoDTO;
import br.com.hr.hr.form.AtualizarDepartamentoForm;
import br.com.hr.hr.form.DepartamentoForm;
import br.com.hr.hr.model.Departamento;
import br.com.hr.hr.repository.DepartamentoRepository;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@GetMapping
	public Page<DepartamentoDTO> listar(@RequestParam(required = false) String nome, @RequestParam int page,
			@RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		if (nome == null) {
			Page<Departamento> departamento = departamentoRepository.findAll(pageable);
			return DepartamentoDTO.converter(departamento);
		} else {
			Page<Departamento> departamento = departamentoRepository.findByNome(nome, pageable);
			return DepartamentoDTO.converter(departamento);
		}

	}

	@PostMapping
	@Transactional
	public ResponseEntity<DepartamentoDTO> cadastrar(@RequestBody @Valid DepartamentoForm form,
			UriComponentsBuilder builder) {
		Departamento departamento = form.converter();
		departamentoRepository.save(departamento);
		URI uri = builder.path("/departamento/{idDepartamento}").buildAndExpand(departamento.getIdDepartamento())
				.toUri();
		return ResponseEntity.created(uri).body(new DepartamentoDTO(departamento));

	}

	@PutMapping("/{idDepartamento}")
	@Transactional

	public ResponseEntity<DepartamentoDTO> atualizar(@PathVariable Long idDepartamento,
			@RequestBody @Valid AtualizarDepartamentoForm form) {
		Optional<Departamento> optional = departamentoRepository.findById(idDepartamento);
		if (optional.isPresent()) {
			Departamento departamento = form.atualizar(idDepartamento, departamentoRepository);
			return ResponseEntity.ok(new DepartamentoDTO(departamento));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{idDepartamento}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long idDepartamento) {
		Optional<Departamento> optional = departamentoRepository.findById(idDepartamento);
		if (optional.isPresent()) {
			departamentoRepository.deleteById(idDepartamento);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
