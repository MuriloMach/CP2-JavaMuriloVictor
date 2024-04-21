package br.com.fiap.concessionaria.dto.request;

public record VeiculoRequest(

        String nome,
        String cor,
        Double preco,
        Short cilindradas,
        String modelo,
        String palavraDeEfeito
) {
}
