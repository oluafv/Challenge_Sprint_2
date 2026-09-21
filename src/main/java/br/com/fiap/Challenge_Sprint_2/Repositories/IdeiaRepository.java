package br.com.fiap.Challenge_Sprint_2.Repositories;

import br.com.fiap.Challenge_Sprint_2.Model.Ideia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeiaRepository extends MongoRepository<Ideia, String> {

    List<Ideia> findByAutorId(String autorId);

    List<Ideia> findByEstrategiaId(String estrategiaId);
}