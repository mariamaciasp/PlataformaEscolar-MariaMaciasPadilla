package dam.PlataformaEscolar.modelo;

import lombok.*;
//import org.springframework.data.util.Pair;

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
    private String abreviatura;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "asignatura", fetch = FetchType.EAGER, orphanRemoval = true, cascade={CascadeType.REMOVE})
    private List<Horario> horarios = new ArrayList<>();

    //@CollectionTable(name="asignatura_horario")
    //@MapKeyJoinColumn(name="asignatura_id")
    //@Column(name="horario")
    //private List <Pair<String,Integer>> horarios = new ArrayList<Pair<String,Integer>>();
    /*@ElementCollection // elemento de una colección, el par día hora
    private List <Pair<String,String>> horarios  = new ArrayList<>();*/


    // sería un par día, hora y convertirlo con JPA CONVERTER
    // https://docs.spring.io/spring-data/data-commons/docs/current/api/org/springframework/data/util/Pair.html
    // para ver como funciona, mirar documentación y ver también lo de @ElementCollection bien cómo es

    @ManyToOne
    private Curso curso;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy="asignatura", fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/, orphanRemoval = true
            , cascade={CascadeType.REMOVE})
    private List<SituacionExcepcional> situcionesExcepcionales = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy="asignatura", fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/, orphanRemoval = true
            , cascade={CascadeType.REMOVE})
    private List<SolicitudAmpliacionMatricula> solicitudAmpliacionMatriculas = new ArrayList<>();


    public Asignatura(String nombre, Curso curso) {
        this.nombre = nombre;
        this.curso = curso;
    }

    public Asignatura(String nombre, String abreviatura, Curso curso) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.curso = curso;
    }


    // helpers de horario
    public void addHorario(Horario h) {
        this.horarios.add(h);
        h.setAsignatura(this);
    }

    public void removeHorario(Horario h) {
        this.horarios.remove(h);
        h.setAsignatura(null);
    }


    /*
    public Asignatura(String nombre, String abreviatura, List<Pair<String, String>> horarios, Curso curso) {
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.horarios = horarios;
        this.curso = curso;
    }*/

}
