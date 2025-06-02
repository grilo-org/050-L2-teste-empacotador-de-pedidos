package com.avaliacao.empacotador_api.dtos.request;

import java.util.List;

import lombok.Getter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListaPedidosRequestDTO {
    private List<PedidoRequestDTO> pedidos;
}

