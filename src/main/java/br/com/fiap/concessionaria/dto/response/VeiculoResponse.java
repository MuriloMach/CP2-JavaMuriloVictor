package br.com.fiap.concessionaria.dto.response;

import lombok.Builder;

import java.time.Year;

@Builder
public record VeiculoResponse(
        Long id,
        String nome,
        String cor,
        Double preco,
        Short cilindradas,
        String modelo,
        String palavraDeEfeito,
        TipoVeiculoResponse tipo,
        FabricanteResponse fabricante,
        Year anoDeFabricacao
) {
}
