package com.teste.scrd.service;

import com.teste.scrd.model.Pauta;
import com.teste.scrd.repository.PautaRepository;
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
public class PautaService {
    @Autowired
    private PautaRepository pautaRepository;

    public Page<Pauta> get(Pageable pageable) {
        List<Pauta> pautaList = pautaRepository.findAll();
        List<Pauta> pageList = pautaList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        final Page<Pauta> page = new PageImpl<>(pageList, pageable, pautaList.size());

        return page;
    }

    public Pauta add(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public Pauta update(Pauta pauta) {
        Pauta pautaData = pautaRepository.findByIdPauta(pauta.getIdPauta())
                .orElseThrow(()-> new RuntimeException("Pauta não encontrada"));
        return pautaRepository.save(Pauta.builder()
                .idPauta(pautaData.getIdPauta())
                .descricao(pauta.getDescricao())
                .build());
    }

    public void delete(Long id) {
        pautaRepository.deleteByIdPauta(id)
                .orElseThrow(()-> new RuntimeException("Pauta não encontrada"));
    }
}
