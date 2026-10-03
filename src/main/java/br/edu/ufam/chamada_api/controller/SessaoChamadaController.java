package br.edu.ufam.chamada_api.controller;

import br.edu.ufam.chamada_api.dto.SessaoChamadaResponseDTO;
import br.edu.ufam.chamada_api.service.SessaoChamadaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/sessoes")
public class SessaoChamadaController {

    private final SessaoChamadaService service;

    public SessaoChamadaController(SessaoChamadaService service) {
        this.service = service;
    }

    public record AbrirSessaoRequest(String nomeDisciplina) {}

    @PostMapping
    public ResponseEntity<SessaoChamadaResponseDTO> abrirSessao(@RequestBody AbrirSessaoRequest request, Principal principal) {
        UUID professorId = UUID.fromString(principal.getName());
        SessaoChamadaResponseDTO novaSessao = service.abrirSessao(professorId, request.nomeDisciplina());
        return ResponseEntity.ok(novaSessao);
    }

    @GetMapping("/ativas")
    public ResponseEntity<Page<SessaoChamadaResponseDTO>> listarAtivas(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(service.listarSessoesAtivas(pageable));
    }
}