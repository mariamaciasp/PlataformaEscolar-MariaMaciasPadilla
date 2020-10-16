package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import javax.persistence.*;

@Entity
@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class Asignatura {

    @Id @GeneratedValue
    private long id;

    private String nombre;

    @ElementCollection // elemento de una colección, el par día hora
    private Pair<String,Integer> horario; // sería un par día, hora y convertirlo con
    // JPA CONVERTER
    // https://docs.spring.io/spring-data/data-commons/docs/current/api/org/springframework/data/util/Pair.html
    // para ver como funciona, mirar documentación y ver también lo de @ElementCollection bien cómo es

    @ManyToOne
    private Curso curso;


}
