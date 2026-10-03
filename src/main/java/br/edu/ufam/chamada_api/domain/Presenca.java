package br.edu.ufam.chamada_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_presenca", uniqueConstraints = {
    @UniqueConstraint(name = "uk_sessao_aluno", columnNames = {"sessao_id", "aluno_id"})
})
public class Presenca {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id")
    private SessaoChamada sessao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Usuario aluno;

    private LocalDateTime dataHoraRegistro;
}