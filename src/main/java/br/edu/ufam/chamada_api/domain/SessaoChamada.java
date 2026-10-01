package br.edu.ufam.chamada_api.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class SessaoChamada {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Usuario professor;
    
    private String nomeDisciplina;
    private LocalDateTime dataHoraInicio;
    private boolean ativa = true;
}