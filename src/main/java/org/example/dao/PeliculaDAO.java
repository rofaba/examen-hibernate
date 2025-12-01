package org.example.dao;

import org.example.modelo.Pelicula;

import java.util.List;

public interface PeliculaDAO {
    Pelicula findById(Integer id);
    List<Pelicula> findAll();
    void save(Pelicula pelicula);
}