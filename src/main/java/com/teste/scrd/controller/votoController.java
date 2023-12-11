package com.teste.scrd.controller;

import com.teste.scrd.exceptions.VotacaoException;
import com.teste.scrd.exceptions.VotoException;
import com.teste.scrd.model.Votacao;
import com.teste.scrd.model.Voto;
import com.teste.scrd.service.VotacaoService;
import com.teste.scrd.service.VotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

@RestController
@RequestMapping("/api/voto")
public class votoController {
    private static final Logger LOG = LogManager.getLogger(Voto.class);

    @Autowired
    private VotoService votoService;
    @Autowired
    private VotacaoService votacaoService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Voto voto) throws VotacaoException {
        LOG.info("Saving Voto");
        Votacao votacao = votacaoService.getById(voto.getVotacao().getIdVotacao());
        if ( (ChronoLocalDateTime.from(LocalDateTime.now()).isAfter(votacao.getAbertura())) &&
                !(ChronoLocalDateTime.from(LocalDateTime.now()).isAfter(votacao.getAbertura().plusMinutes(votacao.getTempoLimiteMinutos())))){
            return new ResponseEntity<>(votoService.add(voto), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("votação não aberta", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public String getResultado (@RequestBody Votacao votacao) throws VotoException {
        LOG.info("Listing resultado");
        return votoService.getResultado(votacao);
    }
}
