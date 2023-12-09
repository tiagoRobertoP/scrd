package com.teste.scrd.controller;

import com.teste.scrd.model.Assembleia;
import com.teste.scrd.service.AssembleiaService;
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
    @Autowired
    private AssembleiaService assembleiaService;

    @GetMapping
    public Page<Assembleia> get(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return assembleiaService.get(paging);
    }

    @PostMapping
    public ResponseEntity<Assembleia> add(@RequestBody Assembleia assembleia) {
        return new ResponseEntity<>(assembleiaService.add(assembleia), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Assembleia> update (@RequestBody Assembleia assembleia) {
        return new ResponseEntity<>(assembleiaService.update(assembleia), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        assembleiaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
