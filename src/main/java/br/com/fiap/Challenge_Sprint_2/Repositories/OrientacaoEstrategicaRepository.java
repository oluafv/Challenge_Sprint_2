package br.com.fiap.Challenge_Sprint_2.Repositories;

import br.com.fiap.Challenge_Sprint_2.Model.OrientacaoEstrategica;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrientacaoEstrategicaRepository extends MongoRepository<OrientacaoEstrategica, String> {
}