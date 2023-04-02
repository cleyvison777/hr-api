package br.com.hr.hr.service;

import br.com.hr.hr.dto.DepartamentoDTO;
import br.com.hr.hr.form.AtualizarDepartamentoForm;
import br.com.hr.hr.model.Departamento;
import br.com.hr.hr.repository.DepartamentoRepository;
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
public class DepartamentoService {
    private static final Logger log = LoggerFactory.getLogger(DepartamentoService.class);
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public void cadastrar(Departamento departamento) {
        //validar o cadastro
        if (departamento.getNome() == null || departamento.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do Departamento  é obrigatorio");

        }
        departamentoRepository.save(departamento);


    }

    public Page<DepartamentoDTO> buscarDepartamento(String nome, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Departamento> departamentos;
        if (StringUtils.hasText(nome)) {
            departamentos = departamentoRepository.findByNome(nome, pageable);
        } else {
            departamentos = departamentoRepository.findAll(pageable);
        }
        return DepartamentoDTO.converter(departamentos);
    }

    @Transactional
    public boolean remover(Long idDepartamento) {
        Optional<Departamento> optional = departamentoRepository.findById(idDepartamento);
        if (optional.isPresent()) {
            departamentoRepository.deleteById(idDepartamento);
            log.info("Departamento removido com sucesso. ID: {}", idDepartamento);
            return true;
        }
        log.warn("Não foi possivel remover o departamento. ID: {}", idDepartamento);
        return false;
    }

    @Transactional
    public Departamento atualizaDepartamento(Long id, AtualizarDepartamentoForm form) {
        Optional<Departamento> optional = departamentoRepository.findById(id);
        if (optional.isPresent()) {
            Departamento departamento = form.atualizar(id, departamentoRepository);
            return departamentoRepository.save(departamento);
        }
        throw new EntityNotFoundException("ENTIDADE NÃO ENCONTRADA" + id);
    }
}

