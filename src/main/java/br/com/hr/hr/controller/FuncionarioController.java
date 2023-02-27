package br.com.hr.hr.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import br.com.hr.hr.dto.BuscaFuncionarioDTO;
import br.com.hr.hr.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public Page<FuncionarioDTO> listar(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "10") @Min(1) int size, @Valid @ModelAttribute BuscaFuncionarioDTO busca) {
        Pageable pageable = PageRequest.of(page, size);

        if (busca.getNomeCategoria() != null) {
            return funcionarioService.buscarPorCategoria(busca.getNomeCategoria(), pageable);
        }
        if (busca.getNome() != null) {
            return funcionarioService.buscarPorNome(busca.getNome(), pageable);
        }
        return funcionarioService.buscarTodos(pageable);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<FuncionarioDTO> cadastrar(@RequestBody @Valid FuncionarioForm form, UriComponentsBuilder builder) {
        Pessoa pessoa = form.converter();
        funcionarioService.cadastrar(pessoa);
        URI uri = builder.path("/funcionario/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new FuncionarioDTO(pessoa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseStatusException remover(@PathVariable Long id) {
        Optional<Pessoa> optional = funcionarioRepository.findById(id);
        if (optional.isPresent()) {
            funcionarioRepository.deleteById(id);
            return new ResponseStatusException(HttpStatus.OK, "Funcionario Removido com sucesso!");
        }

        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Entidade não encontrada");

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizaFuncionarioDto form) {
        Pessoa pessoa = funcionarioService.atualizarFuncionario(id, form);
        return ResponseEntity.ok(new FuncionarioDTO(pessoa));
    }

}
