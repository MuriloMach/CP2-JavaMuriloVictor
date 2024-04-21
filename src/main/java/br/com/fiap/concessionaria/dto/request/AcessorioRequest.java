package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;

public record AcessorioRequest(

        @NotNull(message = "O preço é obrigatório!!!!")
        Double preco,

        @NotNull(message = "O nome é obrigatório!!!!")
        String nome

) {
}
