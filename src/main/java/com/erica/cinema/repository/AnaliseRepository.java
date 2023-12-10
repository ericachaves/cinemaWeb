package com.erica.cinema.repository;

import com.erica.cinema.model.Analise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Integer>{

    List<Analise> findByFilmeId(Integer id);
        
 
}