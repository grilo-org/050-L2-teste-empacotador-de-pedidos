package com.avaliacao.empacotador_api.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avaliacao.empacotador_api.dtos.request.ProdutoRequestDTO;
import com.avaliacao.empacotador_api.enums.TipoCaixa;
import com.avaliacao.empacotador_api.models.Caixa;
import com.avaliacao.empacotador_api.models.Produto;

@Component
public class EmpacotadorDeProdutosService {

    @Autowired
    ValidadorCaixa validadorCaixa;
    
    private static final List<TipoCaixa> CAIXAS_DISPONIVEIS = List.of(TipoCaixa.values());

    public List<Caixa> empacotarProdutos(List<ProdutoRequestDTO> produtosDTO) {
        List<Produto> produtosOrdenados = produtosDTO.stream()
            .map(Produto::fromDTO)
            .sorted(Comparator.comparingDouble(Produto::getVolume).reversed())
            .toList();

        List<Caixa> caixasEmUso = new ArrayList<>();

        for (Produto produto : produtosOrdenados) {
            if (!produtoEhCompativelComQualquerCaixa(produto)) {
                caixasEmUso.add(this.criarCaixaErroParaProduto(produto));
                continue;
            }

            if (!tentarEmpacotarEmCaixaExistente(produto, caixasEmUso)) {
                this.empacotarEmNovaCaixa(produto, caixasEmUso);
            }
        }

        return caixasEmUso;
    }

    private boolean produtoEhCompativelComQualquerCaixa(Produto produto) {
        return CAIXAS_DISPONIVEIS.stream().anyMatch(tipo ->
            produto.getAltura() <= tipo.getAltura() &&
            produto.getLargura() <= tipo.getLargura() &&
            produto.getComprimento() <= tipo.getComprimento() &&
            produto.getVolume() <= tipo.getVolumeTipoCaixa()
        );
    }

    private boolean tentarEmpacotarEmCaixaExistente(Produto produto, List<Caixa> caixasEmUso) {
        for (Caixa caixa : caixasEmUso) {
            if (validadorCaixa.produtoCabeNaCaixa(produto, caixa)) {
                caixa.adicionarProduto(produto);
                return true;
            }
        }
        return false;
    }

    private void empacotarEmNovaCaixa(Produto produto, List<Caixa> caixasEmUso) {
        for (TipoCaixa tipo : CAIXAS_DISPONIVEIS) {
            Caixa novaCaixa = tipo.novaCaixaVazia();
            if (validadorCaixa.suportaDimensoes(produto, novaCaixa) && validadorCaixa.suportaVolume(produto, novaCaixa)) {
                novaCaixa.adicionarProduto(produto);
                caixasEmUso.add(novaCaixa);
                break;
            }
        }
    }

    private Caixa criarCaixaErroParaProduto(Produto produto) {
        Caixa erro = new Caixa(null, 0, 0, 0);
        erro.adicionarProduto(produto);
        erro.setObservacao("Produto não cabe em nenhuma caixa disponível.");
        return erro;
    }
}
