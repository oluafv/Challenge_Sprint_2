package br.com.fiap.Challenge_Sprint_2.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "projeto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Projeto {

    @Id
    private String id;

    private String titulo;
    private String descricao;
    private String ideiaOrigemId;
    private String responsavelId;

    private String responsavelNome;
    private String estrategiaId;

    private StatusProjeto status;
    private Integer progresso;
    private Double investimento;
    private Double retornoFinanceiro;
    private Double reducaoCustos;
    private Double ganhoProdutividade;
    private Integer prazoDias;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}