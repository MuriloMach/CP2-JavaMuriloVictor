package br.com.fiap.concessionaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CARACTERISTICA")

public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_CARACTERISTICA")
    @SequenceGenerator(name = "SQ_CARACTERISTICA", sequenceName = "SQ_CARACTERISTICA", allocationSize = 1)

    @Column(name = "ID_FABRICANTE")
    private Long id;

    //30 digitos
    @Column(name = "NOME_FABRICANTE")
    private String nome;

    //20 digitos
    @Column(name = "DESCRICAO_FABRICANTE")
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "VEICULO",
            referencedColumnName = "ID_VEICULO",
            foreignKey = @ForeignKey(name = "FK_CARACTERISTICA_VEICULO"),
            nullable = false
    )
    private Veiculo veiculo;
}
