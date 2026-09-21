package br.com.fiap.Challenge_Sprint_2.Controllers;

import br.com.fiap.Challenge_Sprint_2.Model.OrientacaoEstrategica;
import br.com.fiap.Challenge_Sprint_2.Services.OrientacaoEstrategicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estrategias")
public class OrientacaoEstrategicaController {

    private final OrientacaoEstrategicaService service;

    public OrientacaoEstrategicaController(OrientacaoEstrategicaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrientacaoEstrategica> criar(@RequestBody OrientacaoEstrategica orientacao) {
        return ResponseEntity.ok(service.criar(orientacao));
    }

    @GetMapping
    public ResponseEntity<List<OrientacaoEstrategica>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrientacaoEstrategica> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrientacaoEstrategica> atualizar(@PathVariable String id, @RequestBody OrientacaoEstrategica orientacao) {
        return ResponseEntity.ok(service.atualizar(id, orientacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}