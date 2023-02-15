package br.com.hr.hr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hr.hr.model.Cidade;

public interface CidadeRepository  extends JpaRepository<Cidade, Long>{
	Page<Cidade> findByNome(String nome, Pageable pageable);

}
