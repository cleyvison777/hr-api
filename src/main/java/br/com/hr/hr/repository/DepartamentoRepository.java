package br.com.hr.hr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hr.hr.model.Departamento;

public interface DepartamentoRepository  extends JpaRepository<Departamento, Long>{
	Page<Departamento> findByNome(String nome, Pageable pageable);

}
