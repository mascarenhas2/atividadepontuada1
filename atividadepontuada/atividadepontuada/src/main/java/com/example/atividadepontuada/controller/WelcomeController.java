package com.example.atividadepontuada.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class WelcomeController {
        @GetMapping("/")
        public String welcome() {
            return "Ola! Seja bem vindo ao banco de dados de funcionarios!";

        }
        @GetMapping("/Dev")
        public String getDev(){
        return "Desenvolvedor:Caio Mascarenhas";
        }

}


