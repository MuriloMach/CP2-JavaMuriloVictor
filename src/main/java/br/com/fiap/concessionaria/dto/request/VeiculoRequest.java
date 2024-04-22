package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.Year;

public record VeiculoRequest(

        @NotNull(message = "O Nome é Primordial!!!")
        String nome,
        String cor,
        @NotNull(message = "O Preço é Primordial!!!")
        Double preco,
        Short cilindradas,
        @NotNull(message = "O Modelo Também é Primordial!!!")
        String modelo,
        String palavraDeEfeito,
        Year anoDeFabricacao,
        @Valid
        AbstractRequest fabricante,
        @Valid
        AbstractRequest tipo
) {
}
