package com.avaliacao.empacotador_api.services;

import org.springframework.stereotype.Component;

import com.avaliacao.empacotador_api.models.Caixa;
import com.avaliacao.empacotador_api.models.Produto;

@Component
public class ValidadorCaixa {

    public boolean produtoCabeNaCaixa(Produto produto, Caixa caixa) {
        return suportaDimensoes(produto, caixa) &&
               suportaVolume(produto, caixa);
    }

    public boolean suportaDimensoes(Produto produto, Caixa caixa) {
        return produto.getAltura() <= caixa.getAltura() &&
               produto.getLargura() <= caixa.getLargura() &&
               produto.getComprimento() <= caixa.getComprimento();
    }

    public boolean suportaVolume(Produto produto, Caixa caixa) {
        return produto.getVolume() <= caixa.getVolumeDisponivel();
    }
}

