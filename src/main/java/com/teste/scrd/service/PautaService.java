package com.teste.scrd.service;

import com.teste.scrd.enums.Errors;
import com.teste.scrd.exceptions.PautaException;
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
    private PautaRepository pautaRepository;
    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

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

    public Pauta update(Pauta pauta) throws PautaException {
        Pauta pautaData = pautaRepository.findByIdPauta(pauta.getIdPauta())
                .orElseThrow(()-> new PautaException(Errors.PAUTA_NOT_FOUND));
        return pautaRepository.save(Pauta.builder()
                .idPauta(pautaData.getIdPauta())
                .descricao(pauta.getDescricao())
                .build());
    }

    public Boolean delete(Long id) throws PautaException {
        Pauta pautaData = pautaRepository.findByIdPauta(id)
                .orElseThrow(()-> new PautaException(Errors.PAUTA_NOT_FOUND));
        pautaRepository.deleteByIdPauta(pautaData.getIdPauta());

        return true;
    }
}
