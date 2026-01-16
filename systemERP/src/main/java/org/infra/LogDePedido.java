package org.infra;

public class LogDePedido implements AutoCloseable {
    String nomeArquivo;

    public LogDePedido(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void escrever(String mensagem) {
        System.out.println("[" + nomeArquivo + "]" + mensagem);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Finalizando Log: <" + nomeArquivo + ">");
    }
}
