package dam.PlataformaEscolar.modelo;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Curso {

    @Id @GeneratedValue
    private long id;

    private String nombre;
    // ya si eso ampliar la asociación para poner profesor con el curso
    private String abreviatura;

    @ManyToOne(fetch = FetchType.EAGER)
    private Titulo titulo;

    public Curso(String nombre, String abreviatura, Titulo titulo) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.titulo = titulo;
    }

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "curso")
    private List <Alumno> alumnos = new ArrayList<>();

    // helpers
    public void addAlumno(Alumno a) {
        this.alumnos.add(a);
        a.setCurso(this);
    }

    public void removeAlumno(Alumno a) {
        this.alumnos.remove(a);
        a.setCurso(null);
    }

    // Asociación con Asignatura
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
    private List<Asignatura> asignaturas = new ArrayList<>();

    // helpers
    public void addAsignatura (Asignatura a) {
        this.asignaturas.add(a);
        a.setCurso(this);
    }

    public void removeAsignatura (Asignatura a) {
        this.asignaturas.remove(a);
        a.setCurso(null);
    }

    public Curso(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }
}
