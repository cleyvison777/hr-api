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

import br.com.hr.hr.dto.CidadeDTO;
import br.com.hr.hr.form.CidadeAtualizaForm;
import br.com.hr.hr.form.CidadeForm;
import br.com.hr.hr.model.Cidade;
import br.com.hr.hr.repository.CidadeRepository;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<CidadeDTO> cadastrar(@RequestBody @Valid CidadeForm cidadeForm,
			UriComponentsBuilder builder) {
		Cidade cidade = cidadeForm.converter();
		cidadeRepository.save(cidade);
		URI uri = builder.path("/cidade/{id}").buildAndExpand(cidade.getId()).toUri();
		return ResponseEntity.created(uri).body(new CidadeDTO(cidade));

	}

	@GetMapping
	public Page<CidadeDTO> listar(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String nome) {
		Pageable pageable = PageRequest.of(page, size);
		if (nome == null) {
			Page<Cidade> cidade = cidadeRepository.findAll(pageable);
			return CidadeDTO.converter(cidade);
		} else {
			Page<Cidade> cidade = cidadeRepository.findByNome(nome, pageable);
			return CidadeDTO.converter(cidade);
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Cidade> optional = cidadeRepository.findById(id);
		if (optional.isPresent()) {
			cidadeRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CidadeDTO> atualizar(@PathVariable Long id, @RequestBody @Valid CidadeAtualizaForm form) {
		Optional<Cidade> optional = cidadeRepository.findById(id);
		if (optional.isPresent()) {
			Cidade cidade = form.atualiza(cidadeRepository, id);
			return ResponseEntity.ok(new CidadeDTO(cidade));
		}
		return ResponseEntity.notFound().build();
	}

}
