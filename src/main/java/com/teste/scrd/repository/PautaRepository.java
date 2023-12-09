package com.teste.scrd.repository;

import com.teste.scrd.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

    Optional<Pauta> findByIdPauta(Long idPauta);

    Optional<Pauta> deleteByIdPauta(Long id);
}
