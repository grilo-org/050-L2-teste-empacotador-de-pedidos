package com.avaliacao.empacotador_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.avaliacao.empacotador_api.dtos.request.DimensoesRequestDTO;
import com.avaliacao.empacotador_api.dtos.request.ProdutoRequestDTO;
import com.avaliacao.empacotador_api.models.Caixa;
import com.avaliacao.empacotador_api.models.Produto;

@ExtendWith(MockitoExtension.class)
class EmpacotadorDeProdutosServiceTest {

    @InjectMocks
    private EmpacotadorDeProdutosService empacotador;

    @Mock
    private ValidadorCaixa validadorCaixa;

    @Test
    void deveEmpacotarProdutoEmNovaCaixaQuandoNenhumaCaixaEmUsoServe() {
        DimensoesRequestDTO dimensoes = new DimensoesRequestDTO();
        dimensoes.setAltura(3);
        dimensoes.setLargura(30);
        dimensoes.setComprimento(20);

        ProdutoRequestDTO dto = new ProdutoRequestDTO();
        dto.setProdutoId("Notebook");
        dto.setDimensoes(dimensoes);

        when(validadorCaixa.suportaDimensoes(any(), any())).thenReturn(true);
        when(validadorCaixa.suportaVolume(any(), any())).thenReturn(true);

        List<Caixa> caixas = empacotador.empacotarProdutos(List.of(dto));

        assertEquals(1, caixas.size());
        assertEquals("Notebook", caixas.get(0).getProdutos().get(0).getId());
        assertNull(caixas.get(0).getObservacao());
    }

    @Test
    void deveCriarCaixaErroParaProdutoIncompativel() {
        ProdutoRequestDTO dto = new ProdutoRequestDTO("Cadeira Gamer",
            new DimensoesRequestDTO(200, 200, 200)); 

        List<Caixa> caixas = empacotador.empacotarProdutos(List.of(dto));

        assertEquals(1, caixas.size());
        assertEquals("Cadeira Gamer", caixas.get(0).getProdutos().get(0).getId());
        assertEquals("Produto não cabe em nenhuma caixa disponível.", caixas.get(0).getObservacao());
    }

    @Test
    void deveEmpacotarProdutoNaMesmaCaixaExistente() {
        DimensoesRequestDTO dimensoes = new DimensoesRequestDTO(10, 10, 10);

        ProdutoRequestDTO produtoA = new ProdutoRequestDTO("Mouse", dimensoes);
        ProdutoRequestDTO produtoB = new ProdutoRequestDTO("Cabo USB", dimensoes);

        when(validadorCaixa.produtoCabeNaCaixa(any(), any())).thenReturn(true);
        when(validadorCaixa.suportaDimensoes(any(), any())).thenReturn(true);
        when(validadorCaixa.suportaVolume(any(), any())).thenReturn(true);

        List<Caixa> caixas = empacotador.empacotarProdutos(List.of(produtoA, produtoB));

        assertEquals(1, caixas.size());
        List<String> ids = caixas.get(0).getProdutos().stream().map(Produto::getId).toList();
        assertTrue(ids.contains("Mouse"));
        assertTrue(ids.contains("Cabo USB"));
    }

}
