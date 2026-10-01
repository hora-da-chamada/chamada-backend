package br.edu.ufam.chamada_api.controller;

import br.edu.ufam.chamada_api.domain.SessaoChamada;
import br.edu.ufam.chamada_api.service.SessaoChamadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessoes")
public class SessaoChamadaController {

    private final SessaoChamadaService service;

    public SessaoChamadaController(SessaoChamadaService service) {
        this.service = service;
    }

    public record AbrirSessaoRequest(UUID professorId, String nomeDisciplina) {}
    @PostMapping
    public ResponseEntity<SessaoChamada> abrirSessao(@RequestBody AbrirSessaoRequest request) {
        SessaoChamada novaSessao = service.abrirSessao(request.professorId(), request.nomeDisciplina());
        return ResponseEntity.ok(novaSessao);
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<SessaoChamada>> listarAtivas() {
        return ResponseEntity.ok(service.listarSessoesAtivas());
    }
}