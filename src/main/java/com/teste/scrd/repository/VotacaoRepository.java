package com.teste.scrd.repository;

import com.teste.scrd.model.Votacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {
    Optional<Votacao> deleteByIdVotacao(Long id);

    Optional<Votacao> findByIdVotacao(Long idVotacao);
}
