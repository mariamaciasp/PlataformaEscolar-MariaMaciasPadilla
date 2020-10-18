package dam.PlataformaEscolar.modelo;

import lombok.*;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Asignatura {

    @Id @GeneratedValue
    private long id;

    private String nombre;
/*
    @ElementCollection // elemento de una colección, el par día hora
    @CollectionTable(name="asignatura_horario")
    @MapKeyJoinColumn(name="asignatura_id")
    @Column(name="horario")
    private List<Pair<String,Integer>> horarios = new ArrayList<Pair<String,Integer>>();
    */
    // sería un par día, hora y convertirlo con JPA CONVERTER
    // https://docs.spring.io/spring-data/data-commons/docs/current/api/org/springframework/data/util/Pair.html
    // para ver como funciona, mirar documentación y ver también lo de @ElementCollection bien cómo es

    @ManyToOne
    private Curso curso;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy="asignatura", fetch = FetchType.EAGER)
    private List<SituacionExcepcional> situcionesExcepcionales = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy="asignatura", fetch = FetchType.EAGER)
    private List<SolicitudAmpliacionMatricula> solicitudAmpliacionMatriculas = new ArrayList<>();

}
