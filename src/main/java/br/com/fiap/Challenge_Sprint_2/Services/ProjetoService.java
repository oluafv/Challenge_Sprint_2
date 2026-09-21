package br.com.fiap.Challenge_Sprint_2.Services;

import br.com.fiap.Challenge_Sprint_2.Model.Projeto;
import br.com.fiap.Challenge_Sprint_2.Model.StatusProjeto;
import br.com.fiap.Challenge_Sprint_2.Repositories.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjetoService {

    private final ProjetoRepository repository;

    public ProjetoService(ProjetoRepository repository) {
        this.repository = repository;
    }

    public List<Projeto> listarPorEstrategia(String estrategiaId) {
        return repository.findByEstrategiaId(estrategiaId);
    }

    public Projeto buscarPorId(String id) {
        return repository.findById(id).orElseThrow();
    }

    public Projeto atualizarProgresso(String id, int novoProgresso) {
        Projeto projeto = buscarPorId(id);

        if (novoProgresso < 0 || novoProgresso > 100) {
            throw new IllegalArgumentException("O progresso deve estar entre 0 e 100.");
        }

        projeto.setProgresso(novoProgresso);

        if (novoProgresso == 100) {
            projeto.setStatus(StatusProjeto.CONCLUIDO);
        } else if (novoProgresso > 0 && projeto.getStatus() == StatusProjeto.PLANEJAMENTO) {
            projeto.setStatus(StatusProjeto.EM_ANDAMENTO);
        }

        projeto.setAtualizadoEm(LocalDateTime.now());
        return repository.save(projeto);
    }

    public Projeto registrarResultados(String id, Projeto dadosFinanceiros) {
        Projeto projeto = buscarPorId(id);

        projeto.setInvestimento(dadosFinanceiros.getInvestimento());
        projeto.setRetornoFinanceiro(dadosFinanceiros.getRetornoFinanceiro());
        projeto.setReducaoCustos(dadosFinanceiros.getReducaoCustos());
        projeto.setGanhoProdutividade(dadosFinanceiros.getGanhoProdutividade());
        projeto.setAtualizadoEm(LocalDateTime.now());

        return repository.save(projeto);
    }
}