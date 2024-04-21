package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.FabricanteRequest;
import br.com.fiap.concessionaria.dto.response.FabricanteResponse;
import br.com.fiap.concessionaria.entity.Fabricante;
import br.com.fiap.concessionaria.service.FabricanteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping(value = "/acessorio")
public class FabricanteResource implements ResourceDTO<Fabricante, FabricanteRequest, FabricanteResponse> {

    @Autowired
    private FabricanteService service;



    @GetMapping
    public ResponseEntity<Collection<FabricanteResponse>> findAll(
            @RequestParam(name = "fabricante.nomeFantasia", required = false, defaultValue = "false") String nomeFantasia,
            @RequestParam(name = "fabricante.nome", required = false)String nome



    ) {
        Fabricante fabricante= Fabricante.builder()
                .nomeFantasia(nomeFantasia)
                .nome(nome)
                .build();
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Fabricante> example = Example.of( fabricante, matcher );

        var encontrados = service.findAll( example );

        if (encontrados.isEmpty()) return ResponseEntity.notFound().build();

        var resposta = encontrados.stream()
                .map( service::toResponse )
                .toList();

        return ResponseEntity.ok( resposta );
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<FabricanteResponse> findById(Long id) {
        var encontrado = service.findById( id );
        if (encontrado == null) return ResponseEntity.notFound().build();
        var resposta = service.toResponse( encontrado );
        return ResponseEntity.ok( resposta );
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<FabricanteResponse> save(@RequestBody @Valid FabricanteRequest r) {
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

