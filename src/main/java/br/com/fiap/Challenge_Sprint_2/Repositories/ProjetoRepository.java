package br.com.fiap.Challenge_Sprint_2.Repositories;

import br.com.fiap.Challenge_Sprint_2.Model.Projeto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends MongoRepository<Projeto, String> {

    List<Projeto> findByEstrategiaId(String estrategiaId);
}