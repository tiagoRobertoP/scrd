package com.teste.scrd.controller;

import com.teste.scrd.exceptions.AssembleiaException;
import com.teste.scrd.model.Assembleia;
import com.teste.scrd.service.AssembleiaService;
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
@RequestMapping("/api/assembleia")
public class AssembleiaController {

    private static final Logger LOG = LogManager.getLogger(AssembleiaController.class);

    @Autowired
    private AssembleiaService assembleiaService;

    @GetMapping
    public Page<Assembleia> get(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        LOG.info("Assembleia list");
        return assembleiaService.get(paging);
    }

    @PostMapping
    public ResponseEntity<Assembleia> add(@RequestBody Assembleia assembleia) {
        LOG.info("Creating Assembleia");
        return new ResponseEntity<>(assembleiaService.add(assembleia), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Assembleia> update (@RequestBody Assembleia assembleia) throws AssembleiaException {
        LOG.info("Updating Assembleia - ID: {}", assembleia.getIdAssembleia());
        return new ResponseEntity<>(assembleiaService.update(assembleia), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws AssembleiaException {
        LOG.info("Deleting Assembleia ID: {}", id);
        assembleiaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
