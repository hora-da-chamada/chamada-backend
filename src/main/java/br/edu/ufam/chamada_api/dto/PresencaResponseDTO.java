package br.edu.ufam.chamada_api.dto;

import br.edu.ufam.chamada_api.domain.Presenca;
import java.time.LocalDateTime;
import java.util.UUID;

public record PresencaResponseDTO(
    UUID id,
    UUID sessaoId,
    String nomeDisciplina,
    UUID alunoId,
    String nomeAluno,
    LocalDateTime dataHoraRegistro
) {
    public static PresencaResponseDTO fromEntity(Presenca presenca) {
        if (presenca == null) return null;
        return new PresencaResponseDTO(
            presenca.getId(),
            presenca.getSessao() != null ? presenca.getSessao().getId() : null,
            presenca.getSessao() != null ? presenca.getSessao().getNomeDisciplina() : null,
            presenca.getAluno() != null ? presenca.getAluno().getId() : null,
            presenca.getAluno() != null ? presenca.getAluno().getNome() : null,
            presenca.getDataHoraRegistro()
        );
    }
}