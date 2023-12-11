package com.teste.scrd.controller;

import com.teste.scrd.exceptions.AssociadoException;
import com.teste.scrd.model.Associado;
import com.teste.scrd.service.AssociadoService;
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
@RequestMapping("/api/associado")
public class AssociadoController {
    private static final Logger LOG = LogManager.getLogger(AssociadoController.class);

    @Autowired
    private AssociadoService associadoService;

    @GetMapping
    public Page<Associado> get(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        LOG.info("Listing Associados");
        return associadoService.get(paging);
    }

    @PostMapping
    public ResponseEntity<Associado> add(@RequestBody Associado associado) {
        LOG.info("Saving Associado");
        return new ResponseEntity<>(associadoService.add(associado), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Associado> update (@RequestBody Associado associado) throws AssociadoException {
        LOG.info("Updating Associado ID: {}", associado.getIdAssociado());
        return new ResponseEntity<>(associadoService.update(associado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws AssociadoException {
        associadoService.delete(id);
        LOG.info("Deleting Associado ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
