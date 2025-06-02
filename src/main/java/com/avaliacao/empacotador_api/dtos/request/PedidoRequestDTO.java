package com.avaliacao.empacotador_api.dtos.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO {
    private int pedidoId;    
    private List<ProdutoRequestDTO> produtos;
}

