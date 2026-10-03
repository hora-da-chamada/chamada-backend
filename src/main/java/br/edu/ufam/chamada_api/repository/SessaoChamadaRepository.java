package br.edu.ufam.chamada_api.repository;

import br.edu.ufam.chamada_api.domain.SessaoChamada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SessaoChamadaRepository extends JpaRepository<SessaoChamada, UUID> {
    
    @EntityGraph(attributePaths = {"professor"})
    Page<SessaoChamada> findByAtivaTrue(Pageable pageable);
}