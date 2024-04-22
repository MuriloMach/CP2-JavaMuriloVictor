package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.VeiculoRequest;
import br.com.fiap.concessionaria.dto.response.AcessorioResponse;
import br.com.fiap.concessionaria.dto.response.VeiculoResponse;
import br.com.fiap.concessionaria.entity.Acessorio;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.repository.AcessorioRepository;
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
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/Veiculo")
public class VeiculoResource implements ResourceDTO<Veiculo, VeiculoRequest, VeiculoResponse>{

    @Autowired
    private VeiculoService service;

    @Autowired
    private AcessorioRepository acessorioRepository;

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
        Veiculo veiculo = Veiculo.builder()
                .nome(nome)
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

        if(encontrados.isEmpty()) return ResponseEntity.notFound().build();

        var resposta = encontrados.stream()
                .map( service::toResponse )
                .toList();
        return ResponseEntity.ok( resposta );
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<VeiculoResponse> findById(@PathVariable Long id){
        var encontrados = service.findById(id);
        if (encontrados ==null) return ResponseEntity.notFound().build();
        var resposta = service.toResponse(encontrados);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/{id}/acessorios")
    public ResponseEntity<List<Acessorio>> findAcessoriosByVeiculoId(@PathVariable Long id){
        Veiculo veiculo = service.findById(id);
        if(veiculo == null){
            return ResponseEntity.notFound().build();
        }
        List<Acessorio> acessorios = (List<Acessorio>) veiculo.getAcessorios();
        return ResponseEntity.ok(acessorios);
    }

    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<VeiculoResponse> save (@RequestBody @Valid VeiculoRequest r){
        var entity = service.toEntity(r);
        var saved = service.save(entity);
        var resposta = service.toResponse(saved);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

    @Transactional
    @PostMapping(value = "/{id}/acessorios")
    public VeiculoResponse save(@PathVariable Long id, @RequestBody @Valid AcessorioResponse acessorio) {
        if (Objects.isNull(acessorio)) return null;
        Veiculo veiculo = service.findById(id);
        Acessorio acessorioEntity = null;
        if (Objects.nonNull(acessorio.id())) {
            acessorioEntity = acessorioRepository.findById(acessorio.id()).orElseThrow();
        }
        veiculo.getAcessorios().add(acessorioEntity);
        return service.toResponse(veiculo);
    }

}
