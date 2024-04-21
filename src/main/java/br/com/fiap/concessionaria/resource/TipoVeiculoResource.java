package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.TipoVeiculoRequest;
import br.com.fiap.concessionaria.dto.response.TipoVeiculoResponse;
import br.com.fiap.concessionaria.entity.TipoVeiculo;
import br.com.fiap.concessionaria.service.TipoVeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping(value = "/TipoVeiculo")
public class TipoVeiculoResource implements ResourceDTO<TipoVeiculo, TipoVeiculoRequest, TipoVeiculoResponse>{

    @Autowired
    private TipoVeiculoService service;

    @GetMapping
    public ResponseEntity<Collection<TipoVeiculoResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome
    ) {
        var tipoVeiculo = TipoVeiculo.builder()
                .nome( nome )
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<TipoVeiculo> example = Example.of( tipoVeiculo, matcher );

        var encontrados = service.findAll( example );
        var resposta = encontrados.stream()
                .map( service::toResponse )
                .toList();
        return ResponseEntity.ok( resposta );
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<TipoVeiculoResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById( id );
        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<TipoVeiculoResponse> save(@RequestBody @Valid TipoVeiculoRequest r) {
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
