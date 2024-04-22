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
@Table(name = "TB_FABRICANTE")
public class Fabricante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FABRICANTE")
    @SequenceGenerator(name = "SQ_FABRICANTE", sequenceName = "SQ_FABRICANTE", allocationSize = 1)

    @Column(name = "ID_FABRICANTE")
    private Long id;

    @Column(name = "NOME_FABRICANTE")
    private String nome;

    @Column(name = "NOME_FANTASIA_FABRICANTE")
    private String nomeFantasia;
}
