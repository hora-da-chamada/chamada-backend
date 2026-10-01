package br.edu.ufam.chamada_api.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Presenca {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private SessaoChamada sessao;

    @ManyToOne
    private Usuario aluno;

    private LocalDateTime dataHoraRegistro;
}