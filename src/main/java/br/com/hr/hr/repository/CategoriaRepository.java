package br.com.hr.hr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hr.hr.model.Categoria;

public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{
	Page<Categoria> findByNome(String nome, Pageable pageable);

}
