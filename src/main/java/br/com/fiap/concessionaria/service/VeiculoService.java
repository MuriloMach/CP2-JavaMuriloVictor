package br.com.fiap.concessionaria.service;

import br.com.fiap.concessionaria.dto.request.VeiculoRequest;
import br.com.fiap.concessionaria.dto.response.AcessorioResponse;
import br.com.fiap.concessionaria.dto.response.VeiculoResponse;
import br.com.fiap.concessionaria.entity.Veiculo;
import br.com.fiap.concessionaria.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VeiculoService implements ServiceDTO<Veiculo, VeiculoRequest, VeiculoResponse> {

    @Autowired
    private VeiculoRepository repo;

    @Autowired
    private TipoVeiculoService tipoVeiculoService;

    @Autowired
    private FabricanteService fabricanteService;

    @Autowired
    private AcessorioService acessorioService;

    @Override
    public Veiculo toEntity(VeiculoRequest m) {
        return Veiculo.builder()
                .nome(m.nome() )
                .cor(m.cor())
                .preco(m.preco())
                .cilindradas(m.cilindradas())
                .modelo(m.modelo())
                .palavraDeEfeito(m.palavraDeEfeito())
                .build();
    }
    @Override
    public VeiculoResponse toResponse(Veiculo w){
        Set<AcessorioResponse> acessorioResponse = w.getAcessorios()
                .stream()
                .map(acessorioService::toResponse)
                .collect(Collectors.toSet());
        return VeiculoResponse.builder()
                .id(w.getId())
                .nome(w.getNome())
                .cor(w.getCor())
                .preco(w.getPreco())
                .cilindradas(w.getCilindradas())
                .modelo(w.getModelo())
                .palavraDeEfeito(w.getPalavraDeEfeito())
                .fabricante(fabricanteService.toResponse(w.getFabricante()))
                .tipo(tipoVeiculoService.toResponse(w.getTipoVeiculo()))
                .build();
    }
    @Override
    public Collection<Veiculo> findAll(Example<Veiculo> example){
        return repo.findAll(example);
    }
    @Override
    public Veiculo findById(Long id){
        return repo.findById(id).orElse(null);
    }

    @Override
    public Veiculo save(Veiculo v){
        return repo.save(v);
    }


}
