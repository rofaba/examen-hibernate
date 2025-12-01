package org.example.service;

import org.example.dao.OpinionRepository;
import org.example.dao.PeliculaRepository;

public class OpinionService {
    PeliculaRepository pelirepo = new PeliculaRepository();
    OpinionRepository opirepo = new OpinionRepository();

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

}
