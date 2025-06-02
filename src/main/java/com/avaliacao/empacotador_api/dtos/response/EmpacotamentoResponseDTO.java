package com.avaliacao.empacotador_api.dtos.response;

import java.util.List;

public class EmpacotamentoResponseDTO {
    private List<PedidoEmpacotadoResponseDTO> pedidos;

    public EmpacotamentoResponseDTO(List<PedidoEmpacotadoResponseDTO> pedidos) {
        this.pedidos = pedidos;
    }

    public List<PedidoEmpacotadoResponseDTO> getPedidos() {
        return pedidos;
    }
}

