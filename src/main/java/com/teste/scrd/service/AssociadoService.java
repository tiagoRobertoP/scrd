package com.teste.scrd.service;

import com.teste.scrd.model.Associado;
import com.teste.scrd.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    public Page<Associado> get(Pageable pageable) {
        List<Associado> associadoList = associadoRepository.findAll();
        List<Associado> pageList = associadoList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        final Page<Associado> page = new PageImpl<>(pageList, pageable, associadoList.size());

        return page;
    }

    public Associado add(Associado associado) {
        return associadoRepository.save(associado);
    }

    public Associado update(Associado associado) {
        Associado associadoData = associadoRepository.findByIdAssociado(associado.getIdAssociado())
                .orElseThrow(()-> new RuntimeException("Associado não encontrado"));
        return associadoRepository.save(Associado.builder()
                        .idAssociado(associadoData.getIdAssociado())
                        .cpf(associado.getCpf())
                        .nome(associado.getNome())
                .build());
    }

    public void delete(Long id) {
        associadoRepository.deleteByIdAssociado(id)
                .orElseThrow(()-> new RuntimeException("Associado não encontrado"));
    }
}
