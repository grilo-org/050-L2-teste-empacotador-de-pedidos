package com.avaliacao.empacotador_api.models;


import java.util.ArrayList;
import java.util.List;

import com.avaliacao.empacotador_api.dtos.response.CaixaEmpacotadaResponseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Caixa {

    private String nome;
    private int altura;
    private int largura;
    private int comprimento;
    private double volumeDisponivel;
    private String observacao;
    private List<Produto> produtos = new ArrayList<>();

    public Caixa(String nome, int altura, int largura, int comprimento) {
        this.nome = nome;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.volumeDisponivel = altura * largura * comprimento;
    }

    public void adicionarProduto(Produto produto) {
        this.volumeDisponivel -= produto.getVolume();
        produtos.add(produto);
    }

    public Caixa clonarVazia() {
        return new Caixa(this.nome, this.altura, this.largura, this.comprimento);
    }

    public CaixaEmpacotadaResponseDTO converterParaDTO() {
        List<String> nomesProdutos = produtos.stream()
            .map(Produto::getId)
            .toList();

        if (this.observacao != null) {
            return new CaixaEmpacotadaResponseDTO(nome, nomesProdutos, this.observacao);
        }

        return new CaixaEmpacotadaResponseDTO(nome, nomesProdutos);
    }
}
