package com.teste.scrd.controller;

import com.teste.scrd.model.Votacao;
import com.teste.scrd.model.Voto;
import com.teste.scrd.repository.VotacaoRepository;
import com.teste.scrd.service.VotacaoService;
import com.teste.scrd.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

@RestController
@RequestMapping("/api/voto")
public class votoController {
    @Autowired
    private VotoService votoService;
    @Autowired
    private VotacaoService votacaoService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Voto voto) {
        Votacao votacao = votacaoService.getById(voto.getVotacao().getIdVotacao());
        if ( (ChronoLocalDateTime.from(LocalDateTime.now()).isAfter(votacao.getAbertura())) &&
                !(ChronoLocalDateTime.from(LocalDateTime.now()).isAfter(votacao.getAbertura().plusMinutes(votacao.getTempoLimiteMinutos())))){
            return new ResponseEntity<>(votoService.add(voto), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("votação não aberta", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public String getResultado (@RequestBody Votacao votacao){
        return votoService.getResultado(votacao);
    }
}
