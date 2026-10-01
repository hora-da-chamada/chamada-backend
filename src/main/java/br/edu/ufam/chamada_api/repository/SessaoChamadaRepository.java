package br.edu.ufam.chamada_api.repository;

import br.edu.ufam.chamada_api.domain.SessaoChamada;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SessaoChamadaRepository extends JpaRepository<SessaoChamada, UUID> {
    List<SessaoChamada> findByAtivaTrue();
}