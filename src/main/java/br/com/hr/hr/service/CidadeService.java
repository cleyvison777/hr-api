package br.com.hr.hr.service;

import br.com.hr.hr.dto.CidadeDTO;
import br.com.hr.hr.form.CidadeAtualizaForm;
import br.com.hr.hr.model.Cidade;
import br.com.hr.hr.repository.CidadeRepository;
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
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;
    private static final Logger log = LoggerFactory.getLogger(CidadeService.class);

    public void cadastrar(Cidade cidade) {
        if (cidade.getNome() == null || cidade.getNome().isEmpty()) {
            throw new IllegalArgumentException("NOME DA CIDADE É OBRIGATORIO");
        }
        cidadeRepository.save(cidade);
    }

    public Page<CidadeDTO> listar(String nome, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cidade> cidades;
        if (StringUtils.hasText(nome)) {
            cidades = cidadeRepository.findByNome(nome, pageable);
        } else {
            cidades = cidadeRepository.findAll(pageable);
        }
        return CidadeDTO.converter(cidades);
    }

    @Transactional
    public boolean remover(Long id) {
        Optional<Cidade> optional = cidadeRepository.findById(id);
        if (optional.isPresent()) {
            cidadeRepository.deleteById(id);
            log.info("Cidade removido com sucesso. ID: {}", id);
            return true;
        }
        log.warn("Não foi possivel remover o Cidade. ID: {}", id);
        return false;
    }

    @Transactional
    public Cidade atualizaCidade(Long id, CidadeAtualizaForm form) {
        Optional<Cidade> optional = cidadeRepository.findById(id);
        if (optional.isPresent()) {
            Cidade cidade = form.atualiza(cidadeRepository, id);
            return cidadeRepository.save(cidade);
        }
        throw new EntityNotFoundException("ENTIDADE NÃO ENCONTRADA" + id);

    }


}
