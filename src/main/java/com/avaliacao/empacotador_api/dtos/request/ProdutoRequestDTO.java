package com.avaliacao.empacotador_api.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRequestDTO {
    public ProdutoRequestDTO(String produtoId, DimensoesRequestDTO dimensoes) {
        this.produtoId = produtoId;
        this.dimensoes = dimensoes;
    }
    
    public ProdutoRequestDTO() {}

    private String produtoId;
    private DimensoesRequestDTO dimensoes;
}

