package com.avaliacao.empacotador_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.avaliacao.empacotador_api.dtos.request.ListaPedidosRequestDTO;
import com.avaliacao.empacotador_api.dtos.response.PedidoEmpacotadoResponseDTO;
import com.avaliacao.empacotador_api.services.EmpacotadorService;
import com.avaliacao.empacotador_api.utils.SwaggerExamples;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Empacotador", description = "API para empacotamento de pedidos")
public class EmpacotadorController {

    @Autowired
    EmpacotadorService service;

    @PostMapping
    @Operation(
        summary = "Empacotar pedidos", 
        description = "Recebe uma lista de pedidos e retorna as caixas necessárias",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = "application/json",
                examples = {
                    @ExampleObject(
                        name = "Exemplo Completo",
                        value = SwaggerExamples.EXEMPLO_LISTA_PEDIDOS
                    )
                }
            )
        )
    )
    public ResponseEntity<List<PedidoEmpacotadoResponseDTO>> empacotarPedidos(@RequestBody ListaPedidosRequestDTO listaPedidosDTO) {
        List<PedidoEmpacotadoResponseDTO> pedidos = service.empacotarPedidos(listaPedidosDTO);

        return ResponseEntity.ok(pedidos);
    }
}
