package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.dto.request.FabricanteRequest;
import br.com.fiap.concessionaria.dto.response.FabricanteResponse;
import br.com.fiap.concessionaria.entity.Fabricante;
import br.com.fiap.concessionaria.repository.FabricanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FabricanteService implements ServiceDTO<Fabricante, FabricanteRequest, FabricanteResponse> {


    @Autowired
    private FabricanteRepository repo;

    @Override
    public Fabricante toEntity(FabricanteRequest m) {
        return Fabricante.builder()
                .nome( m.nome() )
                .nomeFantasia(m.nomeFantasia())
                .build();
    }
    @Override
    public  FabricanteResponse toResponse(Fabricante w){
        return FabricanteResponse.builder()
                .id(w.getId())
                .nome( w.getNome() )
                .nomeFantasia( w.getNomeFantasia())
                .build();
    }
    @Override
    public Collection<Fabricante> findAll(Example<Fabricante> example) {  return  repo.findAll(example);}
    @Override
    public  Fabricante findById(Long id){return repo.findById(id).orElse(null);}
    @Override
    public Fabricante save(Fabricante w){return repo.save(w);}

}
