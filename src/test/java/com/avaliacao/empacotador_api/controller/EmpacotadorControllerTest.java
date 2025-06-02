package com.avaliacao.empacotador_api.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.springframework.http.MediaType;
import com.avaliacao.empacotador_api.controllers.EmpacotadorController;
import com.avaliacao.empacotador_api.dtos.response.CaixaEmpacotadaResponseDTO;
import com.avaliacao.empacotador_api.dtos.response.PedidoEmpacotadoResponseDTO;
import com.avaliacao.empacotador_api.services.EmpacotadorService;

@WebMvcTest(controllers = EmpacotadorController.class)
@ActiveProfiles("test")
class EmpacotadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private EmpacotadorService service;

    @Test
    void deveRetornar200QuandoEmpacotarPedidosComSucesso() throws Exception {
        String jsonRequisicao = """
            {
              "pedidos": [
                {
                  "pedido_id": 1,
                  "produtos": [
                    {
                      "produto_id": "Mouse",
                      "dimensoes": {
                        "altura": 5,
                        "largura": 5,
                        "comprimento": 5
                      }
                    }
                  ]
                }
              ]
            }
        """;

        List<PedidoEmpacotadoResponseDTO> respostaEsperada = List.of(
            new PedidoEmpacotadoResponseDTO(1,
                List.of(new CaixaEmpacotadaResponseDTO("Caixa 1", List.of("Mouse")))
            )
        );

        when(service.empacotarPedidos(any())).thenReturn(respostaEsperada);

        mockMvc.perform(post("/api/pedidos")
        .with(user("testUser").roles("USER"))  
        .with(csrf())
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonRequisicao))
        .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].pedido_id").value(1))
            .andExpect(jsonPath("$[0].caixas[0].caixa_id").value("Caixa 1"))
            .andExpect(jsonPath("$[0].caixas[0].produtos[0]").value("Mouse"));
    }
}


