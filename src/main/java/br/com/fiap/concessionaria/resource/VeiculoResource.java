package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.VeiculoRequest;
import br.com.fiap.concessionaria.dto.response.VeiculoResponse;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Year;
import java.util.Collection;

@RestController
@RequestMapping(value = "/Veiculo")
public class VeiculoResource implements ResourceDTO<Veiculo, VeiculoRequest, VeiculoResponse>{

    @Autowired
    private VeiculoService service;

    @GetMapping
    public ResponseEntity<Collection<VeiculoResponse>> findAll(

            @RequestParam(name = "nome", required = false) String nome,

            @RequestParam(name = "anoDeFabricacao", required = false) Year anoDeFabricacao,

            @RequestParam(name = "cor", required = false) String cor,

            @RequestParam(name = "preco", required = false) Double preco,

            @RequestParam(name = "cilindradas", required = false) Short cilindradas,

            @RequestParam(name = "modelo", required = false) String modelo,

            @RequestParam(name = "palavraDeEfeito", required = false) String palavraDeEfeito




    ) {
        var veiculo = Veiculo.builder()
                .nome(  nome )
                .anoDeFabricacao(anoDeFabricacao)
                .cor(cor)
                .preco(preco)
                .cilindradas(cilindradas)
                .modelo(modelo)
                .palavraDeEfeito(palavraDeEfeito)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Veiculo> example = Example.of( veiculo, matcher );

        var encontrados = service.findAll( example );
        var resposta = encontrados.stream()
                .map( service::toResponse )
                .toList();
        return ResponseEntity.ok( resposta );
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<VeiculoResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById( id );
        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<VeiculoResponse> save(@RequestBody @Valid VeiculoRequest r) {
        var entity = service.toEntity( r );
        var saved = service.save( entity );
        var resposta = service.toResponse( saved );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path( "/{id}" )
                .buildAndExpand( saved.getId() )
                .toUri();

        return ResponseEntity.created( uri ).body( resposta );
    }
}
