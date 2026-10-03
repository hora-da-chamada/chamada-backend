package br.edu.ufam.chamada_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_usuario", indexes = {
    @Index(name = "idx_usuario_matricula", columnList = "matricula"),
    @Index(name = "idx_usuario_email", columnList = "email")
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String email;
    private String matricula;
    private String tipo;
}