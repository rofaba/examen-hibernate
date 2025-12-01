package org.example.service;

import org.example.dao.OpinionRepository;
import org.example.dao.PeliculaRepository;
import org.example.modelo.Pelicula;

public class PeliculaService {
   PeliculaRepository pelirepo = new PeliculaRepository();
   OpinionRepository opirepo = new OpinionRepository();

    //agregar opinion a una pelicula
    public void registrarOpinionEnPelicula(Integer peliculaId, String comentario, Integer calificacion) {
        var pelicula = pelirepo.findById(peliculaId);
        if (pelicula != null) {
            var opinion = new org.example.modelo.Opinion();
            opinion.setPelicula(pelicula);
            opinion.setDescripcion(comentario);
            opinion.setPuntuacion(calificacion);
            opinion.setUsuario("UsuarioDePrueba");
            opirepo.save(opinion);
        } else {
            System.out.println("La película con ID " + peliculaId + " no existe.");
        }
    }
    public void eliminarOpinionDePelicula(Integer opinionId) {
        var opinion = opirepo.findById(opinionId);
        if (opinion != null) {
            opirepo.delete(opinion);
        } else {
            System.out.println("La opinión con ID " + opinionId + " no existe.");
        }
    }

    public void actualizarOpinionDePelicula(Integer opinionId, String nuevoComentario, Integer nuevaCalificacion) {
        var opinion = opirepo.findById(opinionId);
        if (opinion != null) {
            opinion.setDescripcion(nuevoComentario);
            opinion.setPuntuacion(nuevaCalificacion);
            opirepo.save(opinion);
        } else {
            System.out.println("La opinión con ID " + opinionId + " no existe.");
        }
    }
    public Pelicula obtenerPeliculaConOpiniones(Integer peliculaId) {
        var pelicula = pelirepo.findById(peliculaId);
        if (pelicula != null) {
            var opiniones = opirepo.findByPeliculaId(peliculaId);
            pelicula.setOpiniones(opiniones);
        } else {
            System.out.println("La película con ID " + peliculaId + " no existe.");
        }
        return pelicula;
    }
    public Pelicula crearPelicula(String titulo) {
        var pelicula = new Pelicula();
        pelicula.setTitulo(titulo);
        pelirepo.save(pelicula);
        return pelicula;
    }
}
