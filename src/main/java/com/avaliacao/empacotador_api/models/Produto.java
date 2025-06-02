package com.avaliacao.empacotador_api.models;

import com.avaliacao.empacotador_api.dtos.request.ProdutoRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Produto {

    private String id;
    private int altura;
    private int largura;
    private int comprimento;
    private int volume;    

    public Produto(String id, int altura, int largura, int comprimento) {
        this.id = id;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.volume = altura * largura * comprimento;
    }

    public static Produto fromDTO(ProdutoRequestDTO dto) {
        if (dto.getDimensoes() == null) {
            throw new IllegalArgumentException("Dimensões não podem ser nulas.");
        }
        
        return new Produto(
                dto.getProdutoId(),
                dto.getDimensoes().getAltura(),
                dto.getDimensoes().getLargura(),
                dto.getDimensoes().getComprimento()
        );
    }
}
