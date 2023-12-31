package com.teste.scrd.service;

import com.teste.scrd.enums.Errors;
import com.teste.scrd.exceptions.AssembleiaException;
import com.teste.scrd.model.Assembleia;
import com.teste.scrd.repository.AssembleiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssembleiaService {

    private AssembleiaRepository assembleiaRepository;

    public AssembleiaService(AssembleiaRepository assembleiaRepository) {
        this.assembleiaRepository = assembleiaRepository;
    }

    public Page<Assembleia> get(Pageable pageable) {
        List<Assembleia> assembleiaList = assembleiaRepository.findAll();
        List<Assembleia> pageList = assembleiaList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        final Page<Assembleia> page = new PageImpl<>(pageList, pageable, assembleiaList.size());

        return page;
    }

    public Assembleia add(Assembleia assembleia) {
        return assembleiaRepository.save(assembleia);
    }

    public Assembleia update(Assembleia assembleia) throws AssembleiaException {
        Assembleia assembleiaData = assembleiaRepository.findByIdAssembleia(assembleia.getIdAssembleia())
                .orElseThrow(()-> new AssembleiaException(Errors.ASSEMBLEIA_NOT_FOUND));
        return assembleiaRepository.save(Assembleia.builder()
                .idAssembleia(assembleiaData.getIdAssembleia())
                .descricao(assembleia.getDescricao())
                .build());
    }

    public void delete(Long id) throws AssembleiaException {
        Assembleia assembleiaData = assembleiaRepository.findByIdAssembleia(id)
                .orElseThrow(()-> new AssembleiaException(Errors.ASSEMBLEIA_NOT_FOUND));
        assembleiaRepository.deleteByIdAssembleia(assembleiaData.getIdAssembleia());
    }
}
