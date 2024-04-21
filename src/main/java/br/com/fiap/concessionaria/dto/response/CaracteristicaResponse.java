package br.com.fiap.concessionaria.dto.response;

import lombok.Builder;

@Builder
public record CaracteristicaResponse(
        Long id,
        String nome,

        String descricao,

        VeiculoResponse veiculo
) {
}
