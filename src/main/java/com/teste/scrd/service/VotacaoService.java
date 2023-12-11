package com.teste.scrd.service;

import com.teste.scrd.enums.Errors;
import com.teste.scrd.exceptions.VotacaoException;
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
    private VotacaoRepository votacaoRepository;

    public VotacaoService(VotacaoRepository votacaoRepository) {
        this.votacaoRepository = votacaoRepository;
    }

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

    public Votacao update(Votacao votacao) throws VotacaoException {
        Votacao votacaoData = votacaoRepository.findByIdVotacao(votacao.getIdVotacao())
                .orElseThrow(()-> new VotacaoException(Errors.VOTACAO_NOT_FOUND));
        return votacaoRepository.save(Votacao.builder()
                .idVotacao(votacaoData.getIdVotacao())
                        .pauta(votacao.getPauta())
                        .abertura(votacao.getAbertura())
                        .assembleia(votacao.getAssembleia())
                        .tempoLimiteMinutos(votacao.getTempoLimiteMinutos())
                .build());
    }

    public void delete(Long id) throws VotacaoException {
        Votacao votacaoData = votacaoRepository.findByIdVotacao(id)
                .orElseThrow(()-> new VotacaoException(Errors.VOTACAO_NOT_FOUND));
        votacaoRepository.deleteByIdVotacao(votacaoData.getIdVotacao());
    }

    public Votacao getById(Long id) throws VotacaoException {
        return votacaoRepository.findByIdVotacao(id)
                .orElseThrow(()-> new VotacaoException(Errors.VOTACAO_NOT_FOUND));
    }
}
