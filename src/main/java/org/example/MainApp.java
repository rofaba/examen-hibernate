package org.example;

import org.example.modelo.Pelicula;
import org.example.modelo.Opinion;
import org.example.service.PeliculaService;
import org.example.service.OpinionService;
import org.example.config.HibernateUtil;
import java.util.List;

public class MainApp {

    // Usamos Strings simples como nombres de usuario
    private static final String USUARIO_CRITICO = "criticitodo@mail.com";
    private static final String USUARIO_FAN = "megustatodo@mail.com";

    public static void main(String[] args) {

        System.out.println("PRUEBAS");

        // Servicios
        PeliculaService peliculaService = new PeliculaService();
        OpinionService opinionService = new OpinionService();

        Pelicula pCritica = null;
        Pelicula pBuena = null;

        //REGISTRO NUEVAS PELICULAS
        System.out.println("registro pelicula nueva");

        pCritica = peliculaService.crearPelicula("La Noche de los Zombies");
        System.out.println("Película 1 creada (ID: " + pCritica.getId() + "): " + pCritica.getTitulo());

        pBuena = peliculaService.crearPelicula("El Secreto de tus ojos");
        System.out.println("Película 2 creada (ID: " + pBuena.getId() + "): " + pBuena.getTitulo());

        //REGISTRO OPINIONES EN PELICULAS EXISTENTES
        System.out.println("registro opinion a pelicula existente");

        peliculaService.registrarOpinionEnPelicula(pCritica.getId(), "Falla de principio a fin.", 1, USUARIO_CRITICO);
        peliculaService.registrarOpinionEnPelicula(pCritica.getId(), "Sólo para fans del director.", 2, USUARIO_FAN);

        peliculaService.registrarOpinionEnPelicula(pBuena.getId(), "Película perfecta, sin bugs.", 5, USUARIO_CRITICO);
        peliculaService.registrarOpinionEnPelicula(pBuena.getId(), "La vería mil veces.", 4, USUARIO_FAN);

        System.out.println("Opiniones añadidas.");

        //OPINIONES DE UN USUARIO ESPECIFICO
        System.out.println("opiniones de un usuario especifico");

        List<Opinion> opinionesCritico = opinionService.findAllOpinionsByUser(USUARIO_CRITICO);

        System.out.println("Opiniones de '" + USUARIO_CRITICO + "': " + opinionesCritico.size());
        opinionesCritico.forEach(opinion ->
                System.out.println("  [ID:" + opinion.getId() + "] " + opinion.getDescripcion() + " -> " + opinion.getPuntuacion() + "/5")
        );

    //PELICULAS CON BAJA PUNTUACION
        System.out.println("peliculas con baja puntuacion");

        List<Pelicula> peliculasBajas = peliculaService.obtenerPeliculasConBajaPuntuacion();

        if (peliculasBajas != null && !peliculasBajas.isEmpty()) {
            System.out.println("Películas con puntuación baja encontradas:");
            peliculasBajas.forEach(peli -> {
                System.out.println("-> " + peli.getTitulo() + " (ID: " + peli.getId() + ")");
            });
        } else {
            System.out.println("No se encontraron películas con promedio de puntuación menor a 3.0 en esta sesión.");
        }

        //CIERRE SESSION FACTORY
        HibernateUtil.getSessionFactory().close();
        System.out.println("\n--- FIN DE PRUEBAS. SessionFactory cerrado. ---");
    }
}

