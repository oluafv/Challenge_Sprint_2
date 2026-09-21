package br.com.fiap.Challenge_Sprint_2.Controllers;

import br.com.fiap.Challenge_Sprint_2.Model.Ideia;
import br.com.fiap.Challenge_Sprint_2.Model.User;
import br.com.fiap.Challenge_Sprint_2.Services.IdeiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ideias")
public class IdeiaController {

    private final IdeiaService service;

    public IdeiaController(IdeiaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Ideia> submeter(@RequestBody Ideia ideia, @AuthenticationPrincipal User usuarioLogado) {
        return ResponseEntity.ok(service.submeterIdeia(ideia, usuarioLogado.getId()));
    }

    @GetMapping("/minhas")
    public ResponseEntity<List<Ideia>> listarMinhasIdeias(@AuthenticationPrincipal User usuarioLogado) {
        return ResponseEntity.ok(service.listarPorAutor(usuarioLogado.getId()));
    }

    @GetMapping("/estrategia/{estrategiaId}")
    public ResponseEntity<List<Ideia>> listarPorEstrategia(@PathVariable String estrategiaId) {
        return ResponseEntity.ok(service.listarPorEstrategia(estrategiaId));
    }

    @PatchMapping("/{id}/avaliar-ia")
    public ResponseEntity<Ideia> avaliarComIa(@PathVariable String id) {
        return ResponseEntity.ok(service.solicitarAvaliacaoInteligente(id));
    }
}