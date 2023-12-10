package com.erica.cinema.controller;

import com.erica.cinema.model.Filme;
import com.erica.cinema.service.FilmeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filme")
public class FilmeAPIController {

    @Autowired
    FilmeService filmeService;

    @PostMapping("/adicionar")
    public ResponseEntity<Filme> addFilme(@RequestBody Filme fil) {
        var novoFilme = filmeService.criar(fil);
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);

    }

    @GetMapping("/listar")
    public ResponseEntity<List> listar() {
        List<Filme> filmes = filmeService.listarTodos();
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Filme> pesquisar(@PathVariable Integer id) {
        Filme filmeEncontrado = filmeService.buscarPorId(id);
        return new ResponseEntity<>(filmeEncontrado, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Filme> editFilme(@PathVariable Integer id, @RequestBody Filme fil) {
        var editarFilme = filmeService.atualizar(id, fil);
        return new ResponseEntity<>(editarFilme, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        filmeService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
