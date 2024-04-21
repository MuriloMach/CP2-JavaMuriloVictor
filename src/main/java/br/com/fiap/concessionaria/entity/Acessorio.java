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
@Table(name = "TB_ACESSORIO")

public class Acessorio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACESSORIO")
    @SequenceGenerator(name = "SQ_VEICULO", sequenceName = "SQ_VEICULO", allocationSize = 1)

    @Column(name = "ID_ACESSORIO")
    private Long id;

    @Column(name = "NOME_ACESSORIO")
    private String nome;

    @Column(name = "PRECO_ACESSORIO")
    private Double preco;
}
