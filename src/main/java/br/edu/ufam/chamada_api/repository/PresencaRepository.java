package br.edu.ufam.chamada_api.repository;

import br.edu.ufam.chamada_api.domain.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface PresencaRepository extends JpaRepository<Presenca, UUID> {
    List<Presenca> findBySessaoId(UUID sessaoId);
    boolean existsBySessaoIdAndAlunoId(UUID sessaoId, UUID alunoId);
}