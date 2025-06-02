package com.avaliacao.empacotador_api.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DimensoesRequestDTO {
    public DimensoesRequestDTO() {
    }

    public DimensoesRequestDTO(int altura, int largura, int comprimento) {
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
    }

    private int altura;
    private int largura;
    private int comprimento;
}

