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

import br.com.hr.hr.dto.FuncionarioDTO;
import br.com.hr.hr.form.AtualizaFuncionarioDto;
import br.com.hr.hr.form.FuncionarioForm;
import br.com.hr.hr.model.Pessoa;
import br.com.hr.hr.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping
	public Page<FuncionarioDTO> listar(@RequestParam int page, @RequestParam int size,
			@RequestParam(required = false) String nome, @RequestParam(required = false) String nomeCategoria) {
		Pageable pageable = PageRequest.of(page, size);
		

		if (nomeCategoria != null) {
			Page<Pessoa> funcionario = funcionarioRepository.findByCategoriaNome(nomeCategoria, pageable);
			return FuncionarioDTO.converter(funcionario);

		}
		if (nome != null) {
			Page<Pessoa> funcionario = funcionarioRepository.findByNome(nome, pageable);
			return FuncionarioDTO.converter(funcionario);
		}
		else  {
			Page<Pessoa> funcionario = funcionarioRepository.findAll(pageable);
			return FuncionarioDTO.converter(funcionario);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<FuncionarioDTO> cadastrar(@RequestBody @Valid FuncionarioForm form,
			UriComponentsBuilder builder) {
		Pessoa pessoa = form.converter();
		funcionarioRepository.save(pessoa);
		URI uri = builder.path("/funcionario/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new FuncionarioDTO(pessoa));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Pessoa> optional = funcionarioRepository.findById(id);
		if (optional.isPresent()) {
			funcionarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaFuncionarioDto form) {
		Optional<Pessoa> optional = funcionarioRepository.findById(id);
		if (optional.isPresent()) {
			Pessoa pessoa = form.atualizar(id, funcionarioRepository);
			return ResponseEntity.ok(new FuncionarioDTO(pessoa));
		}
		return ResponseEntity.notFound().build();
	}

}
