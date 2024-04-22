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
@Table(name = "TB_ACESSORIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_ACESSORIO_NOME", columnNames = {"NM_ACESSORIO"})
})

public class Acessorio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACESSORIO")
    @SequenceGenerator(name = "SQ_ACESSORIO", sequenceName = "SQ_ACESSORIO", allocationSize = 1)

    @Column(name = "ID_ACESSORIO")
    private Long id;

    @Column(name = "NOME_ACESSORIO")
    private String nome;

    @Column(name = "PRECO_ACESSORIO")
    private Double preco;
}
