package com.avaliacao.empacotador_api.enums;

import com.avaliacao.empacotador_api.models.Caixa;

import lombok.Getter;

@Getter
public enum TipoCaixa {

    CAIXA_1("Caixa 1", 30, 40, 80),
    CAIXA_2("Caixa 2", 80, 50, 40),
    CAIXA_3("Caixa 3", 50, 80, 60);

    private final String nome;
    private final int altura;
    private final int largura;
    private final int comprimento;
    private double volumeTipoCaixa;

    TipoCaixa(String nome, int altura, int largura, int comprimento) {
        this.nome = nome;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.volumeTipoCaixa = altura * largura * comprimento;
    }

    public Caixa novaCaixaVazia() {
        return new Caixa(nome, altura, largura, comprimento);
    }
}

