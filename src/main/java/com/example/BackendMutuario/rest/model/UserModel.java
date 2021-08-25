package com.example.BackendMutuario.rest.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
public class UserModel {

    @Id
    private String cpf;
    private String name;
    private String email;
    private String password; // forca da senha?? pode ser <> preciso criptrogafar a senha

    public UserModel() {

    }

    public UserModel(String cpf, String name, String email, String password) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;
        UserModel user = (UserModel) o;
        return Objects.equals(getCpf(), user.getCpf()) && Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf(), getName(), getEmail(), getPassword());
    }

}
