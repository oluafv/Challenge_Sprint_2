package br.com.fiap.Challenge_Sprint_2.Services;

import br.com.fiap.Challenge_Sprint_2.Model.OrientacaoEstrategica;
import br.com.fiap.Challenge_Sprint_2.Repositories.OrientacaoEstrategicaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrientacaoEstrategicaService {

    private final OrientacaoEstrategicaRepository repository;

    public OrientacaoEstrategicaService(OrientacaoEstrategicaRepository repository) {
        this.repository = repository;
    }

    public OrientacaoEstrategica criar(OrientacaoEstrategica orientacao) {
        orientacao.setData(LocalDate.now());
        return repository.save(orientacao);
    }

    public List<OrientacaoEstrategica> listarTodas() {
        return repository.findAll();
    }

    public OrientacaoEstrategica buscarPorId(String id) {
        return repository.findById(id).orElseThrow();
    }

    public OrientacaoEstrategica atualizar(String id, OrientacaoEstrategica dadosAtualizados) {
        OrientacaoEstrategica existente = buscarPorId(id);
        existente.setCategoria(dadosAtualizados.getCategoria());
        existente.setCampanha(dadosAtualizados.getCampanha());
        existente.setDescricaoObjetivo(dadosAtualizados.getDescricaoObjetivo());
        return repository.save(existente);
    }

    public void deletar(String id) {
        repository.deleteById(id);
    }
}