package br.com.hr.hr.service;

import br.com.hr.hr.dto.CategoriaDto;
import br.com.hr.hr.form.AtualizarDepartamentoForm;
import br.com.hr.hr.form.CategoriaAtualizaForm;
import br.com.hr.hr.model.Categoria;
import br.com.hr.hr.model.Departamento;
import br.com.hr.hr.repository.CategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategoriaService {
    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void cadastrar(Categoria categoria) {
        //validar cadastro
        if (categoria.getNome() == null || categoria.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome da Categoria é obrigatorio");
        }
        categoriaRepository.save(categoria);
    }

    public Page<CategoriaDto> listarCategoria(String nome, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Categoria> categorias;
        if (StringUtils.hasText(nome)) {
            categorias = categoriaRepository.findByNome(nome, pageable);
        } else {
            categorias = categoriaRepository.findAll(pageable);
        }
        return CategoriaDto.converter(categorias);
    }

    @Transactional
    public boolean remover(Long id) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isEmpty()) {
            categoriaRepository.deleteById(id);
            log.info("CATEGORIA REMOVIDO COM SUCESSO. ID: {}", id);
            return true;
        }
        log.warn("NÃO FOI POSSICEL REMOVER A CATEGORIA. ID: {}", id);
        return false;
    }

    @Transactional
    public Categoria atualizaDepartamento(Long id, CategoriaAtualizaForm form) {
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()) {
            Categoria categoria = form.atualizar(id, categoriaRepository);
            return categoriaRepository.save(categoria);
        }
        throw new EntityNotFoundException("ENTIDADE NÃO ENCONTRADA" + id);

    }

}
