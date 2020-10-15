package dam.PlataformaEscolar.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class Curso {

    private String nombre;
    // ya si eso ampliar la asociación para poner profesor con el curso
}
