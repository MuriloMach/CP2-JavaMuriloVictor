package br.com.fiap.concessionaria.dto.response;

import lombok.Builder;

@Builder
public record AcessorioResponse (
        Long id,
        String nome,
        Double preco

){
}
