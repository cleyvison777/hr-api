package br.com.hr.hr.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import br.com.hr.hr.service.CategoriaService;
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

import br.com.hr.hr.dto.CategoriaDto;
import br.com.hr.hr.form.CategoriaAtualizaForm;
import br.com.hr.hr.form.CategoriaForm;
import br.com.hr.hr.model.Categoria;
import br.com.hr.hr.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public Page<CategoriaDto> listar(@RequestParam(required = false) String nome, @RequestParam(defaultValue = "10") @Min(1) int size,
                                     @RequestParam(defaultValue = "0") @Min(0) int page) {
        return categoriaService.listarCategoria(nome, size, page);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form,
                                                  UriComponentsBuilder builder) {
        Categoria categoria = form.converter();
        categoriaService.cadastrar(categoria);
        URI uri = builder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaDto(categoria));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        boolean removido = categoriaService.remover(id);
        if (removido) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaAtualizaForm form) {
        Categoria categoria = categoriaService.atualizaDepartamento(id, form);
        return ResponseEntity.ok(new CategoriaDto(categoria));
    }
}
