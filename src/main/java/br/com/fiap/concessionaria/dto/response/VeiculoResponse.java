package br.com.fiap.concessionaria.dto.response;

import lombok.Builder;

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
        FabricanteResponse fabricante
) {
}
