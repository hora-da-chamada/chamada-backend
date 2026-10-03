package br.edu.ufam.chamada_api.controller;

import br.edu.ufam.chamada_api.dto.PresencaResponseDTO;
import br.edu.ufam.chamada_api.service.PresencaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/presencas")
public class PresencaController {

    private final PresencaService service;

    public PresencaController(PresencaService service) {
        this.service = service;
    }

    public record RegistrarPresencaRequest(UUID sessaoId) {}

    @PostMapping
    public ResponseEntity<PresencaResponseDTO> registrar(@RequestBody RegistrarPresencaRequest request, Principal principal) {
        UUID alunoId = UUID.fromString(principal.getName()); 
        PresencaResponseDTO novaPresenca = service.registrarPresenca(request.sessaoId(), alunoId);
        return ResponseEntity.ok(novaPresenca);
    }
}