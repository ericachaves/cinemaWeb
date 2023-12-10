package com.erica.cinema.controller;

import com.erica.cinema.model.Analise;
import com.erica.cinema.service.AnaliseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analise")
public class AnaliseAPIController {
    
      @Autowired
    AnaliseService analiseService;

        @PostMapping("/adicionar")
    public ResponseEntity<Analise> addAnalise(@RequestBody Analise ana) {
        var novaAnalise = analiseService.criar(ana);
        return new ResponseEntity<>(novaAnalise, HttpStatus.CREATED);
        
    }
    
    @GetMapping("/pesquisar/{idFilme}")
    public ResponseEntity<List> listarAnalise(@PathVariable Integer idFilme) {
     List<Analise> lista = analiseService.listar(idFilme);
     return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
      @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        analiseService.excluirAnalise(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
         @DeleteMapping("/excluirPorFilme/{idFilme}")
    public ResponseEntity<?> deletarPorFilme(@PathVariable Integer idFilme) {
        analiseService.excluirTodasAnalisesPorFilme(idFilme);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
