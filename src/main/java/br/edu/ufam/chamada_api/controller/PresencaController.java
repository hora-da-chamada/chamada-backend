package br.edu.ufam.chamada_api.controller;

import br.edu.ufam.chamada_api.domain.Presenca;
import br.edu.ufam.chamada_api.service.PresencaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/presencas")
public class PresencaController {

    private final PresencaService service;

    public PresencaController(PresencaService service) {
        this.service = service;
    }
    public record RegistrarPresencaRequest(UUID sessaoId, UUID alunoId) {}

    @PostMapping
    public ResponseEntity<Presenca> registrar(@RequestBody RegistrarPresencaRequest request) {
        Presenca novaPresenca = service.registrarPresenca(request.sessaoId(), request.alunoId());
        return ResponseEntity.ok(novaPresenca);
    }
}