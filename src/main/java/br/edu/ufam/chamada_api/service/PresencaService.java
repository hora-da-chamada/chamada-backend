package br.edu.ufam.chamada_api.service;

import br.edu.ufam.chamada_api.domain.Presenca;
import br.edu.ufam.chamada_api.domain.SessaoChamada;
import br.edu.ufam.chamada_api.domain.Usuario;
import br.edu.ufam.chamada_api.dto.PresencaResponseDTO;
import br.edu.ufam.chamada_api.repository.PresencaRepository;
import br.edu.ufam.chamada_api.repository.SessaoChamadaRepository;
import br.edu.ufam.chamada_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PresencaService {

    private final PresencaRepository presencaRepository;
    private final SessaoChamadaRepository sessaoRepository;
    private final UsuarioRepository usuarioRepository;

    public PresencaService(PresencaRepository presencaRepository, 
                           SessaoChamadaRepository sessaoRepository, 
                           UsuarioRepository usuarioRepository) {
        this.presencaRepository = presencaRepository;
        this.sessaoRepository = sessaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public PresencaResponseDTO registrarPresenca(UUID sessaoId, UUID alunoId) {
        SessaoChamada sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));

        if (!sessao.isAtiva()) {
            throw new RuntimeException("Esta sessão de chamada já foi encerrada");
        }

        Usuario aluno = usuarioRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        if (!"ALUNO".equalsIgnoreCase(aluno.getTipo())) {
            throw new RuntimeException("Apenas alunos podem registrar presença");
        }

        if (presencaRepository.existsBySessaoIdAndAlunoId(sessaoId, alunoId)) {
            throw new RuntimeException("Presença já registrada para este aluno nesta sessão");
        }

        Presenca presenca = new Presenca();
        presenca.setSessao(sessao);
        presenca.setAluno(aluno);
        presenca.setDataHoraRegistro(LocalDateTime.now());

        Presenca presencaSalva = presencaRepository.save(presenca);
        return PresencaResponseDTO.fromEntity(presencaSalva);
    }
}