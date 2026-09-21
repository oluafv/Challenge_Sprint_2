package br.com.fiap.Challenge_Sprint_2.Services;

import br.com.fiap.Challenge_Sprint_2.Model.Ideia;
import br.com.fiap.Challenge_Sprint_2.Model.Projeto;
import br.com.fiap.Challenge_Sprint_2.Model.StatusIdeia;
import br.com.fiap.Challenge_Sprint_2.Model.StatusProjeto;
import br.com.fiap.Challenge_Sprint_2.Repositories.IdeiaRepository;
import br.com.fiap.Challenge_Sprint_2.Repositories.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IdeiaService {

    private final IdeiaRepository repository;
    private final ProjetoRepository projetoRepository;
    private final GeminiAiService geminiAiService;

    public IdeiaService(IdeiaRepository repository, ProjetoRepository projetoRepository, GeminiAiService geminiAiService) {
        this.repository = repository;
        this.projetoRepository = projetoRepository;
        this.geminiAiService = geminiAiService;
    }

    public Ideia submeterIdeia(Ideia ideia, String autorId) {
        ideia.setAutorId(autorId);
        ideia.setCriadoEm(LocalDateTime.now());
        ideia.setAtualizadoEm(LocalDateTime.now());
        ideia.setStatus(StatusIdeia.PENDENTE);
        return repository.save(ideia);
    }

    public List<Ideia> listarPorAutor(String autorId) {
        return repository.findByAutorId(autorId);
    }

    public List<Ideia> listarPorEstrategia(String estrategiaId) {
        return repository.findByEstrategiaId(estrategiaId);
    }

    public Ideia buscarPorId(String id) {
        return repository.findById(id).orElseThrow();
    }

    public Ideia aprovarIdeia(String ideiaId) {
        Ideia ideia = buscarPorId(ideiaId);

        if (ideia.getStatus() != StatusIdeia.PENDENTE) {
            throw new IllegalStateException("Apenas ideias pendentes podem ser avaliadas.");
        }

        ideia.setStatus(StatusIdeia.APROVADA);
        ideia.setAtualizadoEm(LocalDateTime.now());

        Projeto novoProjeto = new Projeto();
        novoProjeto.setTitulo(ideia.getTitulo());
        novoProjeto.setDescricao(ideia.getDescricao());
        novoProjeto.setIdeiaOrigemId(ideia.getId());
        novoProjeto.setEstrategiaId(ideia.getEstrategiaId());
        novoProjeto.setResponsavelId(ideia.getAutorId());
        novoProjeto.setStatus(StatusProjeto.PLANEJAMENTO);
        novoProjeto.setProgresso(0);
        novoProjeto.setCriadoEm(LocalDateTime.now());
        novoProjeto.setAtualizadoEm(LocalDateTime.now());

        projetoRepository.save(novoProjeto);

        return repository.save(ideia);
    }

    public Ideia rejeitarIdeia(String ideiaId) {
        Ideia ideia = buscarPorId(ideiaId);

        if (ideia.getStatus() != StatusIdeia.PENDENTE) {
            throw new IllegalStateException("Apenas ideias pendentes podem ser avaliadas.");
        }

        ideia.setStatus(StatusIdeia.REJEITADA);
        ideia.setAtualizadoEm(LocalDateTime.now());

        return repository.save(ideia);
    }

    public Ideia solicitarAvaliacaoInteligente(String ideiaId) {
        Ideia ideia = buscarPorId(ideiaId);

        if (ideia.getStatus() != StatusIdeia.PENDENTE) {
            throw new IllegalStateException("Apenas ideias pendentes podem ser enviadas para avaliação da IA.");
        }

        var analise = geminiAiService.avaliarIdeia(ideia);
        ideia.setAnaliseAi(analise);
        ideia.setAtualizadoEm(LocalDateTime.now());

        return repository.save(ideia);
    }
}