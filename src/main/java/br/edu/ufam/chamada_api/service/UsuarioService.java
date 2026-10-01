package br.edu.ufam.chamada_api.service;
import br.edu.ufam.chamada_api.domain.Usuario;
import br.edu.ufam.chamada_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrar(Usuario usuario) {
        if (usuario.getMatricula() != null && repository.findByMatricula(usuario.getMatricula()).isPresent()) {
            throw new RuntimeException("Já existe um usuário com esta matrícula");
        }
        return repository.save(usuario);
    }
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
}