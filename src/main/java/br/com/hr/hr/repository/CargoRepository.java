package br.com.hr.hr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hr.hr.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
	Page<Cargo> findByNome(String nome, Pageable pageable);

}
