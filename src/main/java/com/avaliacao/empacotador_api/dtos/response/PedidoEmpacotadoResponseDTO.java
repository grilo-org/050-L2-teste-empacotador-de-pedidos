package com.avaliacao.empacotador_api.dtos.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoEmpacotadoResponseDTO {
    private int pedidoId;
    private List<CaixaEmpacotadaResponseDTO> caixas;
}
