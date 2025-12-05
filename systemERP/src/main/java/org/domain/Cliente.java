package org.domain;

import org.exceptions.ClienteInvalidoException;

public class Cliente {
    private String nome;
    private String email;

    public Cliente(String nome, String email) {
        setNome(nome);
        setEmail(email);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new ClienteInvalidoException("Nome do cliente está vázio ou não é válido.");
        }
        this.nome = nome.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new ClienteInvalidoException("E-mail inválido.");
        }
        this.email = email.trim();
    }
}
