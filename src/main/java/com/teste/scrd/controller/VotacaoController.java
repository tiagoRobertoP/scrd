package com.teste.scrd.controller;

import com.teste.scrd.model.Votacao;
import com.teste.scrd.service.VotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votacao")
public class VotacaoController {
    @Autowired
    private VotacaoService votacaoService;

    @GetMapping
    public Page<Votacao> get(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return votacaoService.get(paging);
    }

    @PostMapping
    public ResponseEntity<Votacao> start(@RequestBody Votacao votacao) {
        return new ResponseEntity<>(votacaoService.add(votacao), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Votacao> update (@RequestBody Votacao votacao) {
        return new ResponseEntity<>(votacaoService.update(votacao), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        votacaoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
