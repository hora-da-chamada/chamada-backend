package br.edu.ufam.chamada_api.service;

import br.edu.ufam.chamada_api.domain.Usuario;
import br.edu.ufam.chamada_api.dto.UsuarioRegistroDTO;
import br.edu.ufam.chamada_api.dto.UsuarioResponseDTO;
import br.edu.ufam.chamada_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioRegistroDTO dto) {
        String emailSanitizado = dto.email().trim().toLowerCase();
        String matriculaSanitizada = dto.matricula().trim();

        if (repository.findByMatricula(matriculaSanitizada).isPresent()) {
            throw new RuntimeException("Já existe um usuário com esta matrícula");
        }
        if (repository.findByEmail(emailSanitizado).isPresent()) { 
            throw new RuntimeException("Já existe um usuário com este e-mail");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome().trim());
        usuario.setEmail(emailSanitizado);
        usuario.setMatricula(matriculaSanitizada);
        usuario.setTipo("ALUNO"); 

        Usuario usuarioSalvo = repository.save(usuario);
        return UsuarioResponseDTO.fromEntity(usuarioSalvo);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }
}