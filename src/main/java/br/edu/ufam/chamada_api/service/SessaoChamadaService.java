package br.edu.ufam.chamada_api.service;

import br.edu.ufam.chamada_api.domain.SessaoChamada;
import br.edu.ufam.chamada_api.domain.Usuario;
import br.edu.ufam.chamada_api.repository.SessaoChamadaRepository;
import br.edu.ufam.chamada_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SessaoChamadaService {

    private final SessaoChamadaRepository sessaoRepository;
    private final UsuarioRepository usuarioRepository;

    // Repare que injetamos os dois repositórios aqui!
    public SessaoChamadaService(SessaoChamadaRepository sessaoRepository, UsuarioRepository usuarioRepository) {
        this.sessaoRepository = sessaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public SessaoChamada abrirSessao(UUID professorId, String nomeDisciplina) {
        // 1. Verifica se o usuário existe no banco
        Usuario professor = usuarioRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 2. Regra de Negócio de Segurança: Garante que um ALUNO não abra uma sessão
        if (!"PROFESSOR".equalsIgnoreCase(professor.getTipo())) {
            throw new RuntimeException("Apenas professores podem abrir sessões de chamada");
        }
        
        SessaoChamada sessao = new SessaoChamada();
        sessao.setProfessor(professor);
        sessao.setNomeDisciplina(nomeDisciplina);
        sessao.setDataHoraInicio(LocalDateTime.now());
        sessao.setAtiva(true);

        return sessaoRepository.save(sessao);
    }

    public List<SessaoChamada> listarSessoesAtivas() {
        return sessaoRepository.findByAtivaTrue(); // Aquele método mágico que criamos na interface
    }
}