package br.com.fiap.Challenge_Sprint_2.Model;

import com.ethlo.time.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private int ideiaOrigemId;
    private int resposanvelId;
    private String responsavelnome;
    private StatusProjeto status;
    private int progresso;
    private Double investimento;
    private Double retornoFinanceiro;
    private Double reducaoCustos;
    private Double ganhoProdutividade;
    private int prazoDias;
    private DateTime criadoEm;
    private DateTime atualizadoEm;




}
