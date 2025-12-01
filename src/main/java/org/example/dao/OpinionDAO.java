package org.example.dao;

import org.example.modelo.Opinion;

import java.util.List;

public interface OpinionDAO {
    Opinion findById(Integer id);
    List<Opinion> findAll();
    void save(Opinion opinion);
    void delete(Opinion opinion);

    // Método específico: Buscar todas las opiniones para una película
    List<Opinion> findByPeliculaId(Integer peliculaId);
}
