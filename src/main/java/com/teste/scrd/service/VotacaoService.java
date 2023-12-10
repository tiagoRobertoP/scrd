package com.teste.scrd.service;

import com.teste.scrd.model.Pauta;
import com.teste.scrd.model.Votacao;
import com.teste.scrd.repository.PautaRepository;
import com.teste.scrd.repository.VotacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VotacaoService {
    @Autowired
    private VotacaoRepository votacaoRepository;

    public Page<Votacao> get(Pageable pageable) {
        List<Votacao> votacaoList = votacaoRepository.findAll();
        List<Votacao> pageList = votacaoList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        final Page<Votacao> page = new PageImpl<>(pageList, pageable, votacaoList.size());

        return page;
    }

    public Votacao add(Votacao votacao) {
        return votacaoRepository.save(votacao);
    }

    public Votacao update(Votacao votacao) {
        Votacao votacaoData = votacaoRepository.findByIdVotacao(votacao.getIdVotacao())
                .orElseThrow(()-> new RuntimeException("Votacao não encontrada"));
        return votacaoRepository.save(Votacao.builder()
                .idVotacao(votacaoData.getIdVotacao())
                        .pauta(votacao.getPauta())
                        .abertura(votacao.getAbertura())
                        .assembleia(votacao.getAssembleia())
                        .tempoLimiteMinutos(votacao.getTempoLimiteMinutos())
                .build());
    }

    public void delete(Long id) {
        votacaoRepository.deleteByIdVotacao(id)
                .orElseThrow(()-> new RuntimeException("Votacao não encontrada"));
    }

    public Votacao getById(Long id) {
        return votacaoRepository.findByIdVotacao(id)
                .orElseThrow(()-> new RuntimeException("Votacao não encontrada"));
    }
}
