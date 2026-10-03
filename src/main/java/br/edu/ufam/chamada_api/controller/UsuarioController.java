package br.edu.ufam.chamada_api.controller;

import br.edu.ufam.chamada_api.dto.UsuarioRegistroDTO;
import br.edu.ufam.chamada_api.dto.UsuarioResponseDTO;
import br.edu.ufam.chamada_api.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioRegistroDTO dto) {
        UsuarioResponseDTO novoUsuario = service.cadastrar(dto); 
        return ResponseEntity.ok(novoUsuario);
    }
}