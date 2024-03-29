package br.com.hr.hr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hr.hr.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	Page<Endereco> findByRua(String rua, Pageable pageable);

}
