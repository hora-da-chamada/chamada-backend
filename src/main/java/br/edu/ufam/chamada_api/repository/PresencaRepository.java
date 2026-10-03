package br.edu.ufam.chamada_api.repository;

import br.edu.ufam.chamada_api.domain.Presenca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PresencaRepository extends JpaRepository<Presenca, UUID> {
    
    @EntityGraph(attributePaths = {"aluno", "sessao"})
    Page<Presenca> findBySessaoId(UUID sessaoId, Pageable pageable);

    boolean existsBySessaoIdAndAlunoId(UUID sessaoId, UUID alunoId);
}