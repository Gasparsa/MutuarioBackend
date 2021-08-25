package com.example.BackendMutuario.rest.dto;

import lombok.Data;

@Data
public class UserOutputDto {
    private String name;
    private String cpf;
    private String email;

    public UserOutputDto() {
    }

    public UserOutputDto(String name, String cpf, String email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }
}
