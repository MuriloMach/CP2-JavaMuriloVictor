package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CaracteristicaRequest(

        @Valid
        AbstractRequest veiculo,

        @NotNull(message = "O nome da Característica é Obrigatório!!!")
        String nome,
        @NotNull(message = "A descrição é Obrigatória!!!")
        String descricao

) {
}
