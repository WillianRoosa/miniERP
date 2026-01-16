package org.service;

import org.domain.Cliente;
import org.domain.ItemPedido;
import org.domain.Pedido;
import org.domain.Produto;
import org.exceptions.ClienteInvalidoException;
import org.exceptions.EstoqueInsuficienteException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProcessadorPedidoTest {
    @Test
    void deveLancarExcessaoQuandoPedidoForNulo() {
        ProcessadorPedido processador = new ProcessadorPedido();

        assertThrows(
                IllegalArgumentException.class,
                () -> processador.processar(null)
        );
    }


    @Test
    void deveFalharQuandoPedidoNaoTemItens() {
        Pedido pedido = new Pedido(new Cliente("Willian", "willian@gmail.com"));
        ProcessadorPedido processador = new ProcessadorPedido();

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> processador.processar(pedido)
        );
    }

    @Test
    void deveReduzirEstoqueAoProcessarPedido() {
        Produto produto = new Produto("Notebook", 3000.0, 10);
        ItemPedido item = new ItemPedido(produto, 2);
        Pedido pedido = new Pedido(new Cliente("Willian", "willian@gmail.com"));

        pedido.adicionarItem(item);

        ProcessadorPedido processador = new ProcessadorPedido();
        processador.processar(pedido);

        Assertions.assertEquals(8, produto.getEstoque());
    }

    @Test
    void deveLancarExcecaoQuandoEstoqueForInsuficiente() {
        Produto produto = new Produto("Mouse", 100.0, 1);
        ItemPedido item = new ItemPedido(produto, 5);

        Cliente cliente = new Cliente("Willian", "willian@gmail.com");
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(item);

        ProcessadorPedido processador = new ProcessadorPedido();

        Assertions.assertThrows(
                EstoqueInsuficienteException.class,
                () -> processador.processar(pedido)
        );
    }

    @Test
    void deveLancarExcecaoQuandoClienteForInvalido() {
        Assertions.assertThrows(
                ClienteInvalidoException.class,
                () -> new Cliente("", "emailInvalido")
        );
    }
}
