package com.teste.scrd.controller;

import com.teste.scrd.model.Associado;
import com.teste.scrd.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/associado")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @GetMapping
    public Page<Associado> get(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return associadoService.get(paging);
    }

    @PostMapping
    public ResponseEntity<Associado> add(@RequestBody Associado associado) {
        return new ResponseEntity<>(associadoService.add(associado), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Associado> update (@RequestBody Associado associado) {
        return new ResponseEntity<>(associadoService.update(associado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        associadoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
