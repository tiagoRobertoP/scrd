package com.teste.scrd.controller;

import com.teste.scrd.exceptions.VotacaoException;
import com.teste.scrd.model.Votacao;
import com.teste.scrd.service.VotacaoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOG = LogManager.getLogger(VotacaoController.class);

    @Autowired
    private VotacaoService votacaoService;

    @GetMapping
    public Page<Votacao> get(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        LOG.info("Listing Votacao");
        return votacaoService.get(paging);
    }

    @PostMapping
    public ResponseEntity<Votacao> start(@RequestBody Votacao votacao) {
        LOG.info("Saving Votacao");
        return new ResponseEntity<>(votacaoService.add(votacao), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Votacao> update (@RequestBody Votacao votacao) throws VotacaoException {
        LOG.info("Updating Votacao ID: {}", votacao.getIdVotacao());
        return new ResponseEntity<>(votacaoService.update(votacao), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws VotacaoException {
        votacaoService.delete(id);
        LOG.info("Deleting Votacao ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
