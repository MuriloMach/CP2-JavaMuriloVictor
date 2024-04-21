package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.dto.request.AcessorioRequest;
import br.com.fiap.concessionaria.dto.response.AcessorioResponse;
import br.com.fiap.concessionaria.entity.Acessorio;
import br.com.fiap.concessionaria.repository.AcessorioRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AcessorioService implements ServiceDTO<Acessorio, AcessorioRequest, AcessorioResponse> {

    @Autowired
    private AcessorioRepository repo;

    @Override
    public Acessorio toEntity(AcessorioRequest m){
        return Acessorio.builder()
                .nome(m.nome() )
                .preco( m.preco() )
                .build();
    }
    @Override
    public AcessorioResponse toResponse(Acessorio w){
        return AcessorioResponse.builder()
                .id( w.getId() )
                .nome( w.getNome() )
                .preco( w.getPreco())
                .build();
    }
    @Override
    public Collection<Acessorio> findAll(Example<Acessorio> example) {  return  repo.findAll(example);}
    @Override
    public  Acessorio findById(Long id){return repo.findById(id).orElse(null);}
    @Override
    public Acessorio save(Acessorio w){return repo.save(w);}

}
