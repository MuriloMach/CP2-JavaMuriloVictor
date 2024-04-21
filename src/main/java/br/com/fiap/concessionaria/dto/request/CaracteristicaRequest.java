package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;

public record CaracteristicaRequest(

        String nome,
        String descricao

) {
}
