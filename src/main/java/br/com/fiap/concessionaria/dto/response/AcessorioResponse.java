package br.com.fiap.concessionaria.dto.response;

import lombok.Builder;

@Builder
public record AcessorioResponse (
        Long id,
        String nome,
        String cor,
        Double preco,
        short cilindradas,
        String modelo,
        String palavraDeEfeito
){
}
