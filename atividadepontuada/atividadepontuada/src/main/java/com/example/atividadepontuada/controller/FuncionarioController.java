package com.example.atividadepontuada.controller;

import com.example.atividadepontuada.model.Funcionario;
import com.example.atividadepontuada.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public List<Funcionario>  findAll() {
        return funcionarioService.findAll();
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> salvar(@Valid @RequestBody Funcionario funcionario){
        funcionarioService.salvar(funcionario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Funcionário cadastrado com sucesso"));
    }
    @PutMapping
    public ResponseEntity<Map<String,Object>> atualizar(@Valid @RequestBody Funcionario funcionario){
        funcionarioService.atualizar(funcionario);
        return ResponseEntity
                .ok()
                .body(Map.of("mensagem", "Funcionário atualizado com sucesso"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> excluir(@PathVariable Long id){
        funcionarioService.excluir(id);
        return ResponseEntity
                .ok()
                .body(Map.of("mensagem", "Funcionário excluído com sucesso"));
    }
}
