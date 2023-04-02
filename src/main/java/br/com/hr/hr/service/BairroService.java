package br.com.hr.hr.service;

import br.com.hr.hr.dto.BairroDTO;
import br.com.hr.hr.form.AtualizaBairroForm;
import br.com.hr.hr.form.AtualizarDepartamentoForm;
import br.com.hr.hr.model.Bairro;
import br.com.hr.hr.repository.BairroRepository;
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
public class BairroService {

    private static Logger log = LoggerFactory.getLogger(CargoService.class);


    @Autowired
    private BairroRepository bairroRepository;

    public void cadastrar(Bairro bairro) {
        if (bairro.getNome() == null || bairro.getNome().isEmpty()) {
            throw new IllegalArgumentException("NOME DO BAIRRO É OBRIGATORIO");

        }
        bairroRepository.save(bairro);
    }


    public Page<BairroDTO> listarBairro(String nome, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Bairro> bairros;
        if (StringUtils.hasText(nome)) {
            bairros = bairroRepository.findByNome(nome, pageable);
        } else {
            bairros = bairroRepository.findAll(pageable);
        }
        return BairroDTO.converter(bairros);
    }


    @Transactional
    public boolean remover(Long id) {
        Optional<Bairro> optional = bairroRepository.findById(id);
        if (optional.isPresent()) {
            bairroRepository.deleteById(id);
            log.info("BAIRRO REMOVIDO COM SUCESSO. ID {}", id);

        }
        log.warn("NÃO FOI POSSÍVEL REMOVER O BAIRRO. ID {}", id);
        return false;
    }

    @Transactional
    public Bairro atualizarBairro(Long id, AtualizaBairroForm form) {
        Optional<Bairro> optional = bairroRepository.findById(id);
        if (optional.isPresent()) {
            Bairro bairro = form.atuializa(id, bairroRepository);
            return bairroRepository.save(bairro);
        }

        throw new EntityNotFoundException("ENTIDADE NÃO ENCONTRADA " + id);
    }
}
