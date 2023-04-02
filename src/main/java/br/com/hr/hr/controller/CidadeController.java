package br.com.hr.hr.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import br.com.hr.hr.service.CidadeService;
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
    private CidadeService cidadeService;

    @PostMapping
    @Transactional
    public ResponseEntity<CidadeDTO> cadastrar(@RequestBody @Valid CidadeForm cidadeForm,
                                               UriComponentsBuilder builder) {
        Cidade cidade = cidadeForm.converter();
        cidadeService.cadastrar(cidade);
        URI uri = builder.path("/cidade/{id}").buildAndExpand(cidade.getId()).toUri();
        return ResponseEntity.created(uri).body(new CidadeDTO(cidade));

    }

    @GetMapping
    public Page<CidadeDTO> listar(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "10") @Min(1) int size,
                                  @RequestParam(required = false) String nome) {
        return cidadeService.listar(nome, size, page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        boolean removido = cidadeService.remover(id);
        if (removido) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeDTO> atualizar(@PathVariable Long id, @RequestBody @Valid CidadeAtualizaForm form) {
        Cidade cidade = cidadeService.atualizaCidade(id, form);
        return ResponseEntity.ok(new CidadeDTO(cidade));
    }

}
