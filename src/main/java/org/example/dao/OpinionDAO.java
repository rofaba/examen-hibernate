package org.example.dao;

import org.example.modelo.Opinion;

import java.util.List;

public interface OpinionDAO {
    Opinion findById(Integer id);
    List<Opinion> findAll();
    void save(Opinion opinion);
    List<Opinion> findByPeliculaId(Integer peliculaId);
    List<Opinion> findByUsuario(String usuario);
}
