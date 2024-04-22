package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;

public record FabricanteRequest(

        @NotNull(message = "É de extrema importancia o Nome da Loja!!!")
        String nome,
        @NotNull(message = "Obrigatório Nome Fantasia!!!")
        String nomeFantasia
) {
}
