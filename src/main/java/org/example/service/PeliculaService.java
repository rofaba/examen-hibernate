package org.example.service;

import org.example.dao.OpinionRepository;
import org.example.dao.PeliculaRepository;
import org.example.modelo.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class PeliculaService {
   PeliculaRepository pelirepo = new PeliculaRepository();
   OpinionRepository opirepo = new OpinionRepository();

    //agregar opinion a una pelicula
    public void registrarOpinionEnPelicula(Integer peliculaId, String comentario, Integer calificacion, String usuario) {
        var pelicula = pelirepo.findById(peliculaId);
        if (pelicula != null) {

            // Validación  puntuación
            if (calificacion == null || calificacion < 1 || calificacion > 5) {
                throw new IllegalArgumentException("La puntuación debe estar entre 1 y 5.");
            }

            var opinion = new org.example.modelo.Opinion();
            opinion.setPelicula(pelicula);
            opinion.setDescripcion(comentario);
            opinion.setPuntuacion(calificacion);
            opinion.setUsuario(usuario);

            opirepo.save(opinion);
        } else {
            System.out.println("La película con ID " + peliculaId + " no existe.");
        }
    }

    public Pelicula crearPelicula(String titulo) {
        var pelicula = new Pelicula();
        pelicula.setTitulo(titulo);
        pelirepo.save(pelicula);
        return pelicula;
    }
    public List<Pelicula> obtenerPeliculasConBajaPuntuacion() {
        var peliculas = pelirepo.findAll();
        var resultado = new ArrayList<Pelicula>();
        for (var pelicula : peliculas) {
            var opiniones = opirepo.findByPeliculaId(pelicula.getId());
            double promedio = opiniones.stream()
                    .mapToInt(org.example.modelo.Opinion::getPuntuacion)
                    .average()
                    .orElse(0.0);
            if (promedio < 3.0) {
                pelicula.setOpiniones(opiniones);
                resultado.add(pelicula);

            }
        }
        return resultado;
    }
}
