package br.edu.ufam.chamada_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_sessao_chamada", indexes = {
    @Index(name = "idx_sessao_ativa", columnList = "ativa")
})
public class SessaoChamada {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Usuario professor;
    
    private String nomeDisciplina;
    private LocalDateTime dataHoraInicio;
    private boolean ativa = true;
}