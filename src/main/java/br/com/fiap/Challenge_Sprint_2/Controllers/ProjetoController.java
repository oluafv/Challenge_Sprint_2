package br.com.fiap.Challenge_Sprint_2.Controllers;

import br.com.fiap.Challenge_Sprint_2.Model.Projeto;
import br.com.fiap.Challenge_Sprint_2.Services.ProjetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoService service;

    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    @GetMapping("/estrategia/{estrategiaId}")
    public ResponseEntity<List<Projeto>> listarPorEstrategia(@PathVariable String estrategiaId) {
        return ResponseEntity.ok(service.listarPorEstrategia(estrategiaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/progresso")
    public ResponseEntity<Projeto> atualizarProgresso(
            @PathVariable String id,
            @RequestParam int novoProgresso) {
        return ResponseEntity.ok(service.atualizarProgresso(id, novoProgresso));
    }

    @PutMapping("/{id}/resultados")
    public ResponseEntity<Projeto> registrarResultados(
            @PathVariable String id,
            @RequestBody Projeto dadosFinanceiros) {
        return ResponseEntity.ok(service.registrarResultados(id, dadosFinanceiros));
    }
}