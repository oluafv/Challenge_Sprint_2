package br.com.fiap.Challenge_Sprint_2.Model;

import com.ethlo.time.DateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private int autorId;
    private String autorNome;
    private StatusIdeia status;
    private int prioridade;
    private int votos;
    private DateTime criadoEm;
    private DateTime atualizadoEm;



}


