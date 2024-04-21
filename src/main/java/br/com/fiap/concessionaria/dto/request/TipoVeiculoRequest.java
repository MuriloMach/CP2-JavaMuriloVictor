package br.com.fiap.concessionaria.dto.request;

import lombok.Builder;

@Builder
public record TipoVeiculoRequest(
        String nome
) {
}
