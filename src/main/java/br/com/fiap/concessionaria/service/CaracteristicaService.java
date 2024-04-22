package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.dto.request.CaracteristicaRequest;
import br.com.fiap.concessionaria.dto.response.CaracteristicaResponse;
import br.com.fiap.concessionaria.entity.Caracteristica;
import br.com.fiap.concessionaria.repository.CaracteristicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CaracteristicaService implements ServiceDTO<Caracteristica, CaracteristicaRequest, CaracteristicaResponse> {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private CaracteristicaRepository repo;

    @Override
    public Caracteristica toEntity(CaracteristicaRequest m){
        var veiculo = veiculoService.findById(m.veiculo().id());
        return Caracteristica.builder()
                .nome(m.nome() )
                .descricao(m.descricao())
                .build();
    }
    @Override
    public CaracteristicaResponse toResponse(Caracteristica w){
        return CaracteristicaResponse.builder()
                .id(w.getId())
                .nome(w.getNome())
                .descricao(w.getDescricao())
                .veiculo(veiculoService.toResponse(w.getVeiculo() ))
                .build();
    }
    @Override
    public Caracteristica findById(Long id){
        return repo.findById(id).orElse(null);
    }
    @Override
    public Collection<Caracteristica> findAll(Example<Caracteristica> example){
        return repo.findAll(example);
    }

    @Override
    public Caracteristica save(Caracteristica w){
        return repo.save(w);
    }







}
