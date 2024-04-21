package br.com.fiap.concessionaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_LOJA")

public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_LOJA")
    @SequenceGenerator(name = "SQ_VEICULO", sequenceName = "SQ_VEICULO", allocationSize = 1)

    @Column(name = "ID_LOJA")
    private Long id;

    @Column(name = "NOME_LOJA")
    private String nome;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "LOJA_VEICULO",
            joinColumns = {
                    @JoinColumn(
                            name = "VEICULO",
                            referencedColumnName = "ID_VEICULO",
                            foreignKey = @ForeignKey(name = "FK_VEICULO_LOJA"))
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "LOJA",
                            referencedColumnName = "ID_LOJA",
                            foreignKey = @ForeignKey(name = "FK_LOJA_VEICULO"))
            }
    )
    private Set<Veiculo> veiculosComercializados = new LinkedHashSet<>();

}
