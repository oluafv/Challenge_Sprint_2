package br.com.fiap.Challenge_Sprint_2.Model;

import br.com.fiap.Challenge_Sprint_2.DTOs.AnaliseAiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ideias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ideia {

    @Id
    private String id;

    private String titulo;
    private String descricao;
    private String categoria;
    private String autorId;
    private String autorNome;
    private String estrategiaId;
    private StatusIdeia status;
    private Integer prioridade;
    private Integer votos;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
    private AnaliseAiResponse analiseAi;
}