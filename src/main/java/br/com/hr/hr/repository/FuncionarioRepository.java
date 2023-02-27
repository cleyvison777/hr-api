package br.com.hr.hr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hr.hr.model.Pessoa;

public interface FuncionarioRepository extends JpaRepository<Pessoa, Long> {
	Page<Pessoa> findByNome(String nome, Pageable pageable);

	Page<Pessoa> findByCategoriaNome(String nomeCategoria, Pageable pageable);

}
