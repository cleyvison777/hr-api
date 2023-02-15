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

import br.com.hr.hr.dto.EnderecoDto;
import br.com.hr.hr.form.EnderecoAtualizaForm;
import br.com.hr.hr.form.EnderecoForm;
import br.com.hr.hr.model.Endereco;
import br.com.hr.hr.repository.EnderecoRepository;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@GetMapping
	public Page<EnderecoDto> listar(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String nome) {
		Pageable pageable = PageRequest.of(page, size);
		if (nome == null) {
			Page<Endereco> endereco = enderecoRepository.findAll(pageable);
			return EnderecoDto.converter(endereco);
		} else {
			Page<Endereco> endereco = enderecoRepository.findByRua(nome, pageable);
			return EnderecoDto.converter(endereco);
		}

	}

	@PostMapping
	@Transactional
	public ResponseEntity<EnderecoDto> cadastrar(@Valid @RequestBody EnderecoForm form, UriComponentsBuilder builder) {
		Endereco endereco = form.converter();
		enderecoRepository.save(endereco);
		URI uri = builder.path("/endereco/{id}").buildAndExpand(endereco.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnderecoDto(endereco));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Endereco> optional = enderecoRepository.findById(id);
		if (optional.isPresent()) {
			enderecoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EnderecoDto> alterar(@PathVariable Long id, @Valid @RequestBody EnderecoAtualizaForm form) {
		Optional<Endereco> optional = enderecoRepository.findById(id);
		if (optional.isPresent()) {
			Endereco endereco = form.atualiza(id, enderecoRepository);
			return ResponseEntity.ok(new EnderecoDto(endereco));
		}
		return ResponseEntity.notFound().build();
	}

}
