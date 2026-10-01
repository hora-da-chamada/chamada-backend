package br.edu.ufam.chamada_api.repository;

import br.edu.ufam.chamada_api.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    // O Spring cria a query SQL automaticamente só de ler o nome do método!
    Optional<Usuario> findByMatricula(String matricula);
    Optional<Usuario> findByEmail(String email);
}