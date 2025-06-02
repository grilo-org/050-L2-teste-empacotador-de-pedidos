package com.avaliacao.empacotador_api.dtos.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
public class CaixaEmpacotadaResponseDTO {
    private String caixaId;
    private List<String> produtos;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String observacao; 

    public CaixaEmpacotadaResponseDTO(String caixaId, List<String> produtos) {
        this.caixaId = caixaId;
        this.produtos = produtos;
    }

    public CaixaEmpacotadaResponseDTO(String caixaId, List<String> produtos, String observacao) {
        this.caixaId = caixaId;
        this.produtos = produtos;
        this.observacao = observacao;
    }
}
