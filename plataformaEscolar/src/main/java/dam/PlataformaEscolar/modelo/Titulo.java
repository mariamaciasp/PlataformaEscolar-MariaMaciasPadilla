package dam.PlataformaEscolar.modelo;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Titulo {

    @Id @GeneratedValue
    private long id;

    private String nombre;
    // se podría poner también asociación con el tutor del curso, con profesor, pero ya si eso
    private String abreviatura;

    // asociación de composición con curso
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "titulo",/* cascade = CascadeType.ALL, orphanRemoval = true*/ fetch = FetchType.EAGER)
    private List <Curso> cursos = new ArrayList<>();

    public void addCurso (Curso c) {
        c.setTitulo(this);
        this.cursos.add(c);
    }

    public void removeCurso (Curso c) {
        this.cursos.remove(c);
        c.setTitulo(null);
    }

    public Titulo(String nombre, String abreviatura) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
    }
}
