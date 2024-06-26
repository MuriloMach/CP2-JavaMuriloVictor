package br.com.fiap.concessionaria.resource;

import br.com.fiap.concessionaria.dto.request.CaracteristicaRequest;
import br.com.fiap.concessionaria.dto.response.CaracteristicaResponse;
import br.com.fiap.concessionaria.entity.Caracteristica;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.service.CaracteristicaService;
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
@RequestMapping(value = "/caracteristica")
public class CaracteristicaResource implements  ResourceDTO<Caracteristica, CaracteristicaRequest, CaracteristicaResponse>{

    @Autowired
    private CaracteristicaService service;

    @GetMapping
    public ResponseEntity<Collection<CaracteristicaResponse>> findAll(
            @RequestParam(name = "caracteristica.nome",required = false) String nome,
            @RequestParam(name = "caracteristica.descricao",required = false) String descricao
            ){
        Caracteristica caracteristica = Caracteristica.builder()
                .nome(nome)
                .descricao(descricao)
                .build();

        ExampleMatcher matcher =  ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Caracteristica> example = Example.of(caracteristica,matcher);

        var encontrado = service.findAll(example);
        if (encontrado.isEmpty()) return ResponseEntity.notFound().build();
        var resposta = encontrado.stream()
                .map(service::toResponse)
                .toList();
        return ResponseEntity.ok(resposta);
    }
    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<CaracteristicaResponse> findById(@PathVariable Long id) {
        var encontrados = service.findById(id);
        if (encontrados == null) return ResponseEntity.notFound().build();
        var resposta = service.toResponse(encontrados);
        return ResponseEntity.ok(resposta);
    }



    @Override
    @Transactional
    @PostMapping
    public ResponseEntity<CaracteristicaResponse> save(@RequestBody @Valid CaracteristicaRequest r) {
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
}
