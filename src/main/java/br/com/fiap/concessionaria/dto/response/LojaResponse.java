package br.com.fiap.concessionaria.dto.response;

import lombok.Builder;

import java.util.Set;

@Builder
public record LojaResponse(
        Long id,
        String nome,

        Set<VeiculoResponse> veiculoComercializados


) {
}
