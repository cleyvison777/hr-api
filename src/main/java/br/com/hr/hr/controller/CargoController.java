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

import br.com.hr.hr.dto.CargoDto;
import br.com.hr.hr.form.AtualizarCargoForm;
import br.com.hr.hr.form.CargoForm;
import br.com.hr.hr.model.Cargo;
import br.com.hr.hr.repository.CargoRepository;

@RestController
@RequestMapping("/cargo")
public class CargoController {

	@Autowired
	private CargoRepository cargoRepository;

	@GetMapping
	public Page<CargoDto> listar(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String nome) {
		Pageable pageable = PageRequest.of(page, size);
		if (nome == null) {
			Page<Cargo> cargo = cargoRepository.findAll(pageable);
			return CargoDto.converter(cargo);

		} else {
			Page<Cargo> cargo = cargoRepository.findByNome(nome, pageable);
			return CargoDto.converter(cargo);
		}

	}

	@PostMapping
	@Transactional
	public ResponseEntity<CargoDto> cadastrar(@RequestBody @Valid CargoForm form, UriComponentsBuilder builder) {
		Cargo cargo = form.converter();
		cargoRepository.save(cargo);
		URI uri = builder.path("/cargo/{id}").buildAndExpand(cargo.getId()).toUri();
		return ResponseEntity.created(uri).body(new CargoDto(cargo));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Cargo> optional = cargoRepository.findById(id);
		if (optional.isPresent()) {
			cargoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CargoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarCargoForm form) {
		Optional<Cargo> optional = cargoRepository.findById(id);
		if (optional.isPresent()) {
			Cargo cargo = form.atualiza(id, cargoRepository);
			return ResponseEntity.ok(new CargoDto(cargo));
		}
		return ResponseEntity.notFound().build();
	}

}
