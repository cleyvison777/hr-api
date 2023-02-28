package br.com.hr.hr.service;

import br.com.hr.hr.dto.CargoDto;
import br.com.hr.hr.model.Cargo;
import br.com.hr.hr.repository.CargoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CargoService {
    private static Logger log = LoggerFactory.getLogger(CargoService.class);
    @Autowired
    private CargoRepository cargoRepository;

    public void cadastrar(Cargo cargo) {
        //validar o cadastro
        if (cargo.getNome() == null || cargo.getNome().isEmpty()) {
            throw new IllegalArgumentException("NOME DO CARGO É OBRIGATORIO");
        }
        cargoRepository.save(cargo);
    }

    public Page<CargoDto> buscarCargo(String nome, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cargo> cargos;
        if (StringUtils.hasText(nome)) {
            cargos = cargoRepository.findByNome(nome, pageable);
        } else {
            cargos = cargoRepository.findAll(pageable);
        }
        return CargoDto.converter(cargos);
    }

    @Transactional
    public boolean remover(Long id) {
        Optional<Cargo> optional = cargoRepository.findById(id);
        if (optional.isPresent()) {
            cargoRepository.deleteById(id);
            log.info("Cargo removido com sucesso. ID: {}", id);
            return true;
        }
        log.warn("Não foi possivel remover o departamento. ID: {}" + id);
        return false;

    }

}
