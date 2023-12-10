package com.erica.cinema.service;

import com.erica.cinema.model.Filme;
import com.erica.cinema.repository.FilmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmeRepository;

    public Filme criar(Filme fil) {
        fil.setId(null);
        filmeRepository.save(fil);
        return fil;
    }

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();

    }

    public Filme buscarPorId(Integer id) {
        return filmeRepository.findById(id).orElseThrow();

    }

    public void excluir(Integer id) {
        Filme filmeEncontrado = buscarPorId(id);
        filmeRepository.deleteById(filmeEncontrado.getId());

    }
     public Filme atualizar(Integer id, Filme filmeEnviado) {
        Filme filmeEncontrado = buscarPorId(id);
        filmeEncontrado.setTitulo(filmeEnviado.getTitulo());
        filmeEncontrado.setSinopse(filmeEnviado.getSinopse());
        filmeEncontrado.setGenero(filmeEnviado.getGenero());
        filmeEncontrado.setAno_lancamento(filmeEnviado.getAno_lancamento());
        filmeRepository.save(filmeEncontrado);
        return filmeEncontrado;
    }
    
}
