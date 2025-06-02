package com.avaliacao.empacotador_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avaliacao.empacotador_api.dtos.request.ListaPedidosRequestDTO;
import com.avaliacao.empacotador_api.dtos.response.CaixaEmpacotadaResponseDTO;
import com.avaliacao.empacotador_api.dtos.response.PedidoEmpacotadoResponseDTO;
import com.avaliacao.empacotador_api.models.Caixa;

@Service
public class EmpacotadorService {

    @Autowired
    EmpacotadorDeProdutosService empacotadorDeProdutos;

    public List<PedidoEmpacotadoResponseDTO> empacotarPedidos(ListaPedidosRequestDTO pedidos) {
        return pedidos.getPedidos().stream()
            .map(pedido -> {
                List<Caixa> caixas = empacotadorDeProdutos.empacotarProdutos(pedido.getProdutos());
                return converterParaResponseDTO(pedido.getPedidoId(), caixas);
            })
            .toList();
    }

    private PedidoEmpacotadoResponseDTO converterParaResponseDTO(int pedidoId, List<Caixa> caixasUsadas) {
        List<CaixaEmpacotadaResponseDTO> caixasDTO = caixasUsadas.stream()
            .map(Caixa::converterParaDTO)
            .toList();

        return new PedidoEmpacotadoResponseDTO(pedidoId, caixasDTO);
    }
}

