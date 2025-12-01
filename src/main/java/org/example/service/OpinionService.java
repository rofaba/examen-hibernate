package org.example.service;

import org.example.dao.OpinionRepository;
import org.example.dao.PeliculaRepository;
import org.example.modelo.Opinion;

import java.util.List;

public class OpinionService {
    PeliculaRepository pelirepo = new PeliculaRepository();
    OpinionRepository opirepo = new OpinionRepository();

    public List<Opinion> findAllOpinionsByUser(String usuario) {
        return opirepo.findByUsuario(usuario);
    }


}
