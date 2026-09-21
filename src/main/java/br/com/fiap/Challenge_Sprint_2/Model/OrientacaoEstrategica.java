package br.com.fiap.Challenge_Sprint_2.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "orientacoes_estrategicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrientacaoEstrategica {

    @Id
    private String id;

    private String categoria;
    private String campanha;
    private String descricaoObjetivo;
    private LocalDate data;
}