package com.erica.cinema.service;

import com.erica.cinema.model.Analise;
import com.erica.cinema.model.Filme;
import com.erica.cinema.repository.AnaliseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnaliseService {

    @Autowired
    AnaliseRepository analiseRepository;

    public Analise criar(Analise ana) {
        ana.setId(null);
        analiseRepository.save(ana);
        return ana;
    }

    public List<Analise> listar(Integer id) {
        return analiseRepository.findByFilmeId(id);
    }

    public Analise buscarPorId(Integer id) {
        return analiseRepository.findById(id).orElseThrow();

    }

    public void excluirAnalise(Integer id) {
        Analise objEncontrado = buscarPorId(id);
        analiseRepository.deleteById(objEncontrado.getId());
              
    }
    
    public void excluirTodasAnalisesPorFilme(Integer id){
        for(Analise reg : listar(id)){
            excluirAnalise(reg.getId());
        }
    }
}
