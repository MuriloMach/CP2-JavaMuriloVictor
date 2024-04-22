package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;

public record LojaRequest(

        @NotNull(message = "O nome da loja é Obrigatório!")
        String nome
) {
}
