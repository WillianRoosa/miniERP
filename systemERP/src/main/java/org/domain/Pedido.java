package org.domain;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<ItemPedido>();

    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Pedido não pode ser gerado se o cliente estiver nulo...");
        }
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(ItemPedido item) {
        if (item == null) {
            throw new IllegalArgumentException("Nenhum item foi selecionado...");
        }
        for (ItemPedido itemExistente : itens) {
            if (itemExistente.getProduto().getNome().equals(item.getProduto().getNome()) == true) {
                var itemNovo = itemExistente.getQuantidade() + item.getQuantidade();
                itemExistente.setQuantidade(itemNovo);
                return;
            }
        }
        itens.add(item);
    }
}
