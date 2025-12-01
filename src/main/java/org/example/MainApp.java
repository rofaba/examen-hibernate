package org.example;

import org.example.modelo.Pelicula;
import org.example.service.PeliculaService;
import org.example.service.OpinionService;
import org.example.config.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {


        System.out.println("--- INICIO DE PRUEBAS ---");

        // Instanciación de servicios.
        PeliculaService peliculaService = new PeliculaService();
        OpinionService opinionService = new OpinionService();

        // CREACIÓN DE DATOS
        System.out.println("\n--- CREACIÓN DE DATOS ---");

        // Crear Película 1
        Pelicula p1 = peliculaService.crearPelicula("El Guardián del Tiempo");
        System.out.println("Película creada: " + p1.getTitulo() + " (ID: " + p1.getId() + ")");

        // Crear Película 2
        Pelicula p2 = peliculaService.crearPelicula("Sueños de Neon");
        System.out.println("Película creada: " + p2.getTitulo() + " (ID: " + p2.getId() + ")");

        // Registrar Opiniones para P1
        peliculaService.registrarOpinionEnPelicula(p1.getId(), "Una trama espectacular.", 5);
        peliculaService.registrarOpinionEnPelicula(p1.getId(), "Le faltó ritmo al inicio.", 3);

        // Registrar Opinión para P2
        peliculaService.registrarOpinionEnPelicula(p2.getId(), "Visualmente impresionante.", 4);

        System.out.println("Opiniones registradas.");


        // CONSULTA Y LECTURA
        System.out.println("\n--- CONSULTA DE DATOS ---");

        // Obtener la película P1 con sus opiniones
        Pelicula p1ConOpiniones = peliculaService.obtenerPeliculaConOpiniones(p1.getId());

        if (p1ConOpiniones != null) {
            System.out.println("Película encontrada: " + p1ConOpiniones.getTitulo());
            System.out.println("Opiniones asociadas:");

            p1ConOpiniones.getOpiniones().forEach(opinion ->
                    System.out.println("  -> ID: " + opinion.getId() + ", Puntuación: " + opinion.getPuntuacion() + ", Comentario: " + opinion.getDescripcion())
            );
        }

        // ACTUALIZACIÓN DE DATOS
        System.out.println("\n--- ACTUALIZACIÓN DE DATOS ---");

        // Obtener la ID de la primera opinión de P1 para actualizarla
        Integer opinionIdParaActualizar = p1ConOpiniones.getOpiniones().stream().findFirst().get().getId();

        opinionService.actualizarOpinionDePelicula(
                opinionIdParaActualizar,
                "La he visto de nuevo, ¡es un 10!",
                5
        );
        System.out.println("Opinión ID " + opinionIdParaActualizar + " actualizada.");

        // ELIMINACIÓN DE DATOS
        System.out.println("\n--- ELIMINACIÓN DE DATOS ---");

        // Eliminar la segunda opinión de P1
        Integer opinionIdParaEliminar = p1ConOpiniones.getOpiniones().get(1).getId();
        opinionService.eliminarOpinionDePelicula(opinionIdParaEliminar);
        System.out.println("Opinión ID " + opinionIdParaEliminar + " eliminada.");

        // Verificación después de la eliminación
        Pelicula p1Verificada = peliculaService.obtenerPeliculaConOpiniones(p1.getId());
        System.out.println("Opiniones restantes para " + p1Verificada.getTitulo() + ": " + p1Verificada.getOpiniones().size());


        // CIERRE
        // Cierra el SessionFactory para liberar recursos de la base de datos
        HibernateUtil.getSessionFactory().close();
        System.out.println("\n--- FIN DE PRUEBAS. Hibernate SessionFactory cerrado. ---");
    }
}