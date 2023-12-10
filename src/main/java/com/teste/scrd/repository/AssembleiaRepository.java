package com.teste.scrd.repository;

import com.teste.scrd.model.Assembleia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssembleiaRepository extends JpaRepository<Assembleia, Long> {
    Optional<Assembleia> findByIdAssembleia(Long idAssembleia);

    Optional<Assembleia> deleteByIdAssembleia(Long id);
}
