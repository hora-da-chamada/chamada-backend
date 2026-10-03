package br.edu.ufam.chamada_api.dto;
import java.util.UUID;
import br.edu.ufam.chamada_api.domain.SessaoChamada;
import java.time.LocalDateTime;

public record SessaoChamadaResponseDTO(
    UUID id,
    String nomeDisciplina,
    LocalDateTime dataHoraInicio,
    boolean ativa,
    UUID professorId,
    String nomeProfessor
) {
    public static SessaoChamadaResponseDTO fromEntity(SessaoChamada sessao) {
        if (sessao == null) return null;
        return new SessaoChamadaResponseDTO(
            sessao.getId(),
            sessao.getNomeDisciplina(),
            sessao.getDataHoraInicio(),
            sessao.isAtiva(),
            sessao.getProfessor() != null ? sessao.getProfessor().getId() : null,
            sessao.getProfessor() != null ? sessao.getProfessor().getNome() : null
        );
    }
}