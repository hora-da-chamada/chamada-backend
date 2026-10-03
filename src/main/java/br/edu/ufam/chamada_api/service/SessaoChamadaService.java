package br.edu.ufam.chamada_api.service;

import br.edu.ufam.chamada_api.domain.SessaoChamada;
import br.edu.ufam.chamada_api.domain.Usuario;
import br.edu.ufam.chamada_api.dto.SessaoChamadaResponseDTO;
import br.edu.ufam.chamada_api.repository.SessaoChamadaRepository;
import br.edu.ufam.chamada_api.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessaoChamadaService {

    private final SessaoChamadaRepository sessaoRepository;
    private final UsuarioRepository usuarioRepository;

    public SessaoChamadaService(SessaoChamadaRepository sessaoRepository, UsuarioRepository usuarioRepository) {
        this.sessaoRepository = sessaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public SessaoChamadaResponseDTO abrirSessao(UUID professorId, String nomeDisciplina) {
        Usuario professor = usuarioRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!"PROFESSOR".equalsIgnoreCase(professor.getTipo())) {
            throw new RuntimeException("Apenas professores podem abrir sessões de chamada");
        }

        SessaoChamada sessao = new SessaoChamada();
        sessao.setProfessor(professor);
        sessao.setNomeDisciplina(nomeDisciplina.trim());
        sessao.setDataHoraInicio(LocalDateTime.now());
        sessao.setAtiva(true);

        SessaoChamada sessaoSalva = sessaoRepository.save(sessao);
        return SessaoChamadaResponseDTO.fromEntity(sessaoSalva);
    }

    @Transactional(readOnly = true)
    public Page<SessaoChamadaResponseDTO> listarSessoesAtivas(Pageable pageable) {
        return sessaoRepository.findByAtivaTrue(pageable)
                .map(SessaoChamadaResponseDTO::fromEntity);
    }
}