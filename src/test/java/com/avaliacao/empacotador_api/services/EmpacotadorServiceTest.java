package com.avaliacao.empacotador_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.avaliacao.empacotador_api.dtos.request.DimensoesRequestDTO;
import com.avaliacao.empacotador_api.dtos.request.ListaPedidosRequestDTO;
import com.avaliacao.empacotador_api.dtos.request.PedidoRequestDTO;
import com.avaliacao.empacotador_api.dtos.request.ProdutoRequestDTO;
import com.avaliacao.empacotador_api.dtos.response.PedidoEmpacotadoResponseDTO;
import com.avaliacao.empacotador_api.models.Caixa;
import com.avaliacao.empacotador_api.models.Produto;

@ExtendWith(MockitoExtension.class)
class EmpacotadorServiceTest {

    @InjectMocks
    private EmpacotadorService empacotadorService;

    @Mock
    private EmpacotadorDeProdutosService empacotadorDeProdutos;

    @Test
    void deveEmpacotarListaDePedidosComSucesso() {
        DimensoesRequestDTO dimensoes = new DimensoesRequestDTO(10, 10, 10);
        ProdutoRequestDTO produtoDTO = new ProdutoRequestDTO("Mouse", dimensoes);
        PedidoRequestDTO pedido = new PedidoRequestDTO(1, List.of(produtoDTO));
        ListaPedidosRequestDTO lista = new ListaPedidosRequestDTO(List.of(pedido));

        Produto produto = Produto.fromDTO(produtoDTO);
        Caixa caixaMock = new Caixa("Caixa 1", 20, 20, 20);
        caixaMock.adicionarProduto(produto);
        List<Caixa> caixasEmpacotadas = List.of(caixaMock);

        when(empacotadorDeProdutos.empacotarProdutos(pedido.getProdutos()))
            .thenReturn(caixasEmpacotadas);

        List<PedidoEmpacotadoResponseDTO> resposta = empacotadorService.empacotarPedidos(lista);

        assertEquals(1, resposta.size());
        PedidoEmpacotadoResponseDTO resultado = resposta.get(0);
        assertEquals(1, resultado.getPedidoId());
        assertEquals(1, resultado.getCaixas().size());
        assertEquals("Caixa 1", resultado.getCaixas().get(0).getCaixaId());
        assertTrue(resultado.getCaixas().get(0).getProdutos().contains("Mouse"));
    }
}

