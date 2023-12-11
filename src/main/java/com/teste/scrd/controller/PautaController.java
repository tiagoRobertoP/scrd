package com.teste.scrd.controller;

import com.teste.scrd.exceptions.PautaException;
import com.teste.scrd.model.Pauta;
import com.teste.scrd.service.PautaService;
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
@RequestMapping("/api/pauta")
public class PautaController {
    private static final Logger LOG = LogManager.getLogger(PautaController.class);

    @Autowired
    private PautaService pautaService;

    @GetMapping
    public Page<Pauta> get(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        LOG.info("Listing Pauta");
        return pautaService.get(paging);
    }

    @PostMapping
    public ResponseEntity<Pauta> add(@RequestBody Pauta pauta) {
        LOG.info("Saving Pauta ID");
        return new ResponseEntity<>(pautaService.add(pauta), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Pauta> update (@RequestBody Pauta pauta) throws PautaException {
        LOG.info("Updating Pauta ID: {}", pauta.getIdPauta());
        return new ResponseEntity<>(pautaService.update(pauta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws PautaException {
        pautaService.delete(id);
        LOG.info("Deleting Pauta ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
