package com.example.atividadepontuada.service;

import com.example.atividadepontuada.exception.EmailJaCadastradoException;
import com.example.atividadepontuada.model.Funcionario;
import com.example.atividadepontuada.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioRepository salvar(@Valid Funcionario funcionario) {
        if (funcionarioRepository.findByEmail(funcionario.getEmail()).isPresent()) {
            throw new EmailJaCadastradoException("Email já cadastrado");
        }
        return (FuncionarioRepository) funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(@Valid Funcionario funcionario) {
        Funcionario funcionarioAtualizar = funcionarioRepository.findById(funcionario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
        funcionarioAtualizar.setNome(funcionario.getNome());
        funcionarioAtualizar.setEmail(funcionario.getEmail());
        funcionarioAtualizar.setSalario(funcionario.getSalario());

        return funcionarioRepository.save(funcionarioAtualizar);
    }

    public void excluir (Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));

        funcionarioRepository.delete(funcionario);
    }


}