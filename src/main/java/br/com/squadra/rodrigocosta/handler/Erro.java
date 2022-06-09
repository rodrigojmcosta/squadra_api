package br.com.squadra.rodrigocosta.handler;

public class Erro {

    public String mensagem;

    public int status;

    public Erro(String mensagem, int status) {
        this.mensagem = mensagem;
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getStatus() {
        return status;
    }

}
