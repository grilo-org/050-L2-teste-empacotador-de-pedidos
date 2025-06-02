package com.avaliacao.empacotador_api.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.avaliacao.empacotador_api.models.Caixa;
import com.avaliacao.empacotador_api.models.Produto;

@ExtendWith(MockitoExtension.class)
class ValidadorCaixaTest {

    private final ValidadorCaixa validador = new ValidadorCaixa();

    @Test
    void deveRetornarTrueSeProdutoCabeEmVolumeEDimensoes() {
        Produto produto = new Produto("Notebook", 10, 20, 30); 
        Caixa caixa = new Caixa("Caixa 1", 15, 25, 40); 

        assertTrue(validador.produtoCabeNaCaixa(produto, caixa));
    }

    @Test
    void deveRetornarFalseSeProdutoExcedeAlgumaDimensao() {
        Produto produto = new Produto("Monitor", 20, 60, 10);
        Caixa caixa = new Caixa("Caixa 1", 15, 50, 15); 

        assertFalse(validador.produtoCabeNaCaixa(produto, caixa));
    }

    @Test
    void deveRetornarFalseSeProdutoExcedeVolumeDisponivel() {
        Produto produto = new Produto("HD Externo", 10, 10, 10); 
        Caixa caixa = new Caixa("Caixa 2", 20, 20, 20); 

        caixa.setVolumeDisponivel(500); 

        assertFalse(validador.produtoCabeNaCaixa(produto, caixa));
    }

    @Test
    void deveValidarSomenteDimensoes() {
        Produto produto = new Produto("Fone", 5, 5, 5);
        Caixa caixa = new Caixa("Caixa", 4, 5, 5); 

        assertFalse(validador.suportaDimensoes(produto, caixa));
    }

    @Test
    void deveValidarSomenteVolume() {
        Produto produto = new Produto("SSD", 5, 5, 5);
        Caixa caixa = new Caixa("Caixa", 10, 10, 10); 
        caixa.setVolumeDisponivel(100); 

        assertFalse(validador.suportaVolume(produto, caixa));
    }
}
