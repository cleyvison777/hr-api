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

import br.com.hr.hr.dto.BairroDTO;
import br.com.hr.hr.form.AtualizaBairroForm;
import br.com.hr.hr.form.BairroForm;
import br.com.hr.hr.model.Bairro;
import br.com.hr.hr.repository.BairroRepository;

@RestController
@RequestMapping("/bairro")
public class BairroController {
	@Autowired
	private BairroRepository bairroRepository;

	@GetMapping
	public Page<BairroDTO> listar(@RequestParam int size, @RequestParam int page,
			@RequestParam(required = false) String nome) {
		Pageable pageable = PageRequest.of(page, size);
		if (nome == null) {
			Page<Bairro> bairro = bairroRepository.findAll(pageable);
			return BairroDTO.converter(bairro);

		} else {
			Page<Bairro> bairro = bairroRepository.findByNome(nome, pageable);
			return BairroDTO.converter(bairro);
		}

	}

	@PostMapping
	@Transactional
	public ResponseEntity<BairroDTO> cadastrar(@RequestBody @Valid BairroForm form, UriComponentsBuilder builder) {
		Bairro bairro = form.converter();
		bairroRepository.save(bairro);
		URI uri = builder.path("/bairro/{id}").buildAndExpand(bairro.getId()).toUri();
		return ResponseEntity.created(uri).body(new BairroDTO(bairro));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		Optional<Bairro> optional = bairroRepository.findById(id);
		if (optional.isPresent()) {
			bairroRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<BairroDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizaBairroForm form) {
		Optional<Bairro> optional = bairroRepository.findById(id);
		if (optional.isPresent()) {
			Bairro bairro = form.atuializa(id, bairroRepository);
			return ResponseEntity.ok(new BairroDTO(bairro));
		}
		return ResponseEntity.notFound().build();
	}

}
