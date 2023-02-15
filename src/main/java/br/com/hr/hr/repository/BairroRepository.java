package br.com.hr.hr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hr.hr.model.Bairro;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
	Page<Bairro> findByNome(String nome, Pageable pageable);
}
