package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;
import org.aspectj.bridge.IMessage;

public record AbstractRequest(
        @NotNull(message = "O id deve ser informado")
        Long id
) {
}
