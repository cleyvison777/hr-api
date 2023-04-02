package br.com.hr.hr.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import br.com.hr.hr.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

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

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {


    @Autowired
    private DepartamentoService departamentoService;


    @GetMapping
    public Page<DepartamentoDTO> listar(@RequestParam(required = false) String nome, @RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "10") @Min(1) int size) {
        return departamentoService.buscarDepartamento(nome, page, size);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<DepartamentoDTO> cadastrar(@RequestBody @Valid DepartamentoForm form, UriComponentsBuilder builder) {
        Departamento departamento = form.converter();
        departamentoService.cadastrar(departamento);
        URI uri = builder.path("/departamento/{idDepartamento}").buildAndExpand(departamento.getIdDepartamento()).toUri();
        return ResponseEntity.created(uri).body(new DepartamentoDTO(departamento));

    }

    @PutMapping("/{idDepartamento}")
    @Transactional
    public ResponseEntity<DepartamentoDTO> atualizar(@PathVariable Long idDepartamento, @RequestBody @Valid AtualizarDepartamentoForm form) {
        Departamento departamento = departamentoService.atualizaDepartamento(idDepartamento, form);
        return ResponseEntity.ok(new DepartamentoDTO(departamento));
    }

    @DeleteMapping("/{idDepartamento}")
    public ResponseEntity<?> remover(@PathVariable Long idDepartamento) {
        boolean removido = departamentoService.remover(idDepartamento);
        if (removido) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
