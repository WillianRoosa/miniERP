package org.domain;

import org.exceptions.EstoqueInsuficienteException;

public class Produto {
    private String nome;
    private double preco;
    private int estoque;

    public Produto(String nome, double preco, int estoque) {
        setNome(nome);
        setPreco(preco);
        setEstoque(estoque);
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("preço deve pode ser maior que 0.");
        }
        this.preco = preco;
    }

    public void setEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Valor do estoque não pode ser negativo!");
        }
        this.estoque = estoque;
    }

    public void reduzirEstoque(int quantidade) throws EstoqueInsuficienteException {
        if (quantidade > estoque) {
            throw new EstoqueInsuficienteException("Quantidade solicitada maior que o estoque disponível.");
        }
        estoque -= quantidade;
    }
}
