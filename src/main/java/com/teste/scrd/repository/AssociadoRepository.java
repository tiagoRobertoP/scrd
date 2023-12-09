package com.teste.scrd.repository;

import com.teste.scrd.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Optional<Associado> findByIdAssociado(Long idAssociado);

    Optional<Associado> deleteByIdAssociado(Long id);
}
