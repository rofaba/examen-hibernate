package org.example.modelo;
import javax.persistence.*;

@Entity
@Table(name = "opinion")
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Column(name = "usuario", nullable = false, length = 64)
    private String usuario;

    @Column(name = "puntuacion")
    private Integer puntuacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;


    public Opinion() {
    }


    public Opinion(String descripcion, String usuario, Integer puntuacion, Pelicula pelicula) {
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.pelicula = pelicula;
    }

    // --- Getters y Setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}