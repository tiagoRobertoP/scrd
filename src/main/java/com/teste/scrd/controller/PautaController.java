package com.teste.scrd.controller;

import com.teste.scrd.model.Pauta;
import com.teste.scrd.service.PautaService;
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

    @Autowired
    private PautaService pautaService;

    @GetMapping
    public Page<Pauta> get(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return pautaService.get(paging);
    }

    @PostMapping
    public ResponseEntity<Pauta> add(@RequestBody Pauta pauta) {
        return new ResponseEntity<>(pautaService.add(pauta), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Pauta> update (@RequestBody Pauta pauta) {
        return new ResponseEntity<>(pautaService.update(pauta), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        pautaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
