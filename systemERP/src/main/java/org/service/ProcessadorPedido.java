package org.service;

import org.domain.ItemPedido;
import org.domain.Pedido;
import org.domain.Produto;
import org.infra.LogDePedido;

public class ProcessadorPedido {

    public void processar(Pedido pedido) {
        double total = 0;
        if (pedido == null || pedido.getCliente() == null || pedido.getItens() == null || pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Pedido inválido para processamento...");
        }

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();
            int quantidadeItem = item.getQuantidade();
            produto.reduzirEstoque(quantidadeItem);
            total += produto.getPreco() * quantidadeItem;
        }

        try (LogDePedido log = new LogDePedido("pedidos.txt")) {
            log.escrever("Pedido processado com sucesso. Total: " + total);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar pedido", e);
        }
    }
}
