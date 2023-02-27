package br.com.hr.hr.service;

import br.com.hr.hr.dto.FuncionarioDTO;
import br.com.hr.hr.form.AtualizaFuncionarioDto;
import br.com.hr.hr.model.Pessoa;
import br.com.hr.hr.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void cadastrar(Pessoa pessoa) {
        // validar os dados antes de salvar
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do funcionario é obrigatório");
        }
        funcionarioRepository.save(pessoa);
    }


    public Page<FuncionarioDTO> buscarPorCategoria(String nomeCategoria, Pageable pageable) {
        Page<Pessoa> funcionarios = funcionarioRepository.findByCategoriaNome(nomeCategoria, pageable);
        return FuncionarioDTO.converter(funcionarios);
    }

    public Page<FuncionarioDTO> buscarPorNome(String nome, Pageable pageable) {
        Page<Pessoa> funcionarios = funcionarioRepository.findByNome(nome, pageable);

        return FuncionarioDTO.converter(funcionarios);
    }

    public Page<FuncionarioDTO> buscarTodos(Pageable pageable) {
        Page<Pessoa> funcionarios = funcionarioRepository.findAll(pageable);
        return FuncionarioDTO.converter(funcionarios);
    }

    @Transactional
    public Pessoa atualizarFuncionario(long id, AtualizaFuncionarioDto form) {
        Optional<Pessoa> optional = funcionarioRepository.findById(id);
        if (optional.isPresent()) {
            Pessoa pessoa = form.atualizar(id, funcionarioRepository);
            return funcionarioRepository.save(pessoa);

        }
        throw new EntityNotFoundException("Entidade não encontrada");

    }
}
