package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.dto.request.LojaRequest;
import br.com.fiap.concessionaria.dto.response.LojaResponse;
import br.com.fiap.concessionaria.dto.response.VeiculoResponse;
import br.com.fiap.concessionaria.entity.Loja;
import br.com.fiap.concessionaria.repository.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class LojaService implements ServiceDTO<Loja, LojaRequest, LojaResponse>{

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private LojaRepository repo;

    @Override
    public Loja toEntity(LojaRequest m){
        return Loja.builder()
                .nome( m.nome())
                .build();
    }
    @Override
    public Collection<Loja> findAll(Example<Loja> example){
        return repo.findAll(example);
    }

    @Override
    public Loja findById(Long id){
        return repo.findById(id).orElse(null);
    }

    @Override
    public Loja save(Loja w){
        return repo.save(w);
    }

    @Override
    public LojaResponse toResponse(Loja w){
        Set<VeiculoResponse> veiculoResponse = w.getVeiculosComercializados()
                        .stream()
                        .map(veiculoService::toResponse)
                        .collect(Collectors.toSet());

        return LojaResponse.builder()
                .id(w.getId())
                .nome(w.getNome())
                .veiculoComercializados(veiculoResponse)
                .build();
    }
}
