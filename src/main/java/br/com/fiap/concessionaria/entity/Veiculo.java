package br.com.fiap.concessionaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_VEICULO")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SQ_VEICULO")
    @SequenceGenerator(name = "SQ_VEICULO", sequenceName = "SQ_VEICULO", allocationSize = 1)

    @Column(name = "ID_VEICULO")
    private Long id;

    @Column(name = "NOME_VEICULO")
    private String nome;

    @Column(name = "ANOFABRI_VEICULO")
    private Year anoDeFabricacao;

    @Column(name = "COR_VEICULO")
    private String cor;

    @Column(name = "PRECO_VEICULO")
    private Double preco;

    @Column(name = "CILINDRADAS_VEICULO")
    private Short cilindradas;

    @Column(name = "MODELO_VEICULO")
    private String modelo;

    //15 digitos
    @Column(name = "PALAVRA_EFEITO_VEICULO")
    private String palavraDeEfeito;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "FABRICANTE",
            referencedColumnName = "ID_FABRICANTE",
            foreignKey = @ForeignKey(
                    name = "FK_VEICULO_FABRICANTE"
            )
    )
    private Fabricante fabricante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "TIPO_VEICULO",
            referencedColumnName = "ID_TIPO_VEICULO",
            foreignKey = @ForeignKey(
                    name = "FK_VEICULO_TIPO_VEICULO"
            )
    )
    private TipoVeiculo tipoVeiculo;
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "ACESSORIO_VEICULO",
            joinColumns = {
                    @JoinColumn(
                            name = "VEICULO",
                            referencedColumnName = "ID_VEICULO",
                            foreignKey = @ForeignKey(name = "FK_VEICULO_ACESSORIO"))
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ACESSORIO",
                            referencedColumnName = "ID_ACESSORIO",
                            foreignKey = @ForeignKey(name = "FK_ACESSORIO_VEICULO"))
            }
    )
    private Set<Acessorio> acessorios = new LinkedHashSet<>();
}
