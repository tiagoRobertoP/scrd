package com.teste.scrd.repository;

import com.teste.scrd.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    Optional<List<Voto>> findByVotacaoIdVotacao(Long idVotacao);
}
