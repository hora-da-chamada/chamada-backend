package br.edu.ufam.chamada_api.dto;

import br.edu.ufam.chamada_api.domain.Usuario;
import java.util.UUID;

public record UsuarioResponseDTO(
    UUID id,
    String nome,
    String email,
    String matricula,
    String tipo
) {
    public static UsuarioResponseDTO fromEntity(Usuario usuario) {
        if (usuario == null) return null;
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getMatricula(),
            usuario.getTipo()
        );
    }
}